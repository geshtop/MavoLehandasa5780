package renderer;

import elements.LightSource;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import static geometries.Intersectable.GeoPoint;

import java.util.ArrayList;
import java.util.List;


/**
 * class represents a renderer for a scene
 */
public class Render {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double Delta = 0.1;
    ImageWriter image;
    Scene scene;
    // ***************** Constructors ********************** //

    /**
     * creates a renderer by the scene (3D model) and a view plane size
     *
     * @param image
     * @param scene
     */
    public Render(ImageWriter image, Scene scene) {
        this.image = image;
        this.scene = scene;
    }
    // ***************** Operations ******************** //

    /**
     * renders the scene to a 2D picture
     */
    public void renderImage() {
        // for all the Y (height) pixels in the view plane
        for (int i = 0; i < image.getNy(); ++i) {
            // for all the X (width) pixels in the view plane
            for (int j = 0; j < image.getNx(); ++j) {
                // construct ray trough pixel [j,i]
                Ray ray = scene.getCamera().constructRayThroughPixel
                        (image.getNx(), image.getNy(), j, i,
                                scene.getDistance(), image.getHeight(), image.getWidth());
                GeoPoint closestPoint = findClosestIntersection(ray);
                image.writePixel(j, i, closestPoint == null ? scene.getBackground().getColor() : calcColor(closestPoint, ray).getColor());
            }
//            double a = i;
//            if (a > 0 && a % (image.getNy() / 100) == 0)
//                System.out.println(Math.round((a / image.getNy()) * 100) + "%");
        }
    }

    /**
     * prints a 2D grid on the rendered picture
     *
     * @param interval - the number of pixels between each line
     * @param color    - the color of the grid
     */
    public void printGrid(int interval, primitives.Color color) {
        // for all the Y (height) pixels in the view plane
        for (int i = 1; i < image.getNy(); i++) {
            // for all the X (width) pixels in the view plane
            for (int j = 1; j < image.getNx(); j++)
                // paint the pixel after the interval with the wanted color
                if (i % interval == 0 || j % interval == 0)
                    image.writePixel(j, i, color.getColor());
        }
    }

    /**
     * saves a 2D image of the rendered scene
     */
    public void writeToImage() {
        image.writeToImage();
    }

    /*********** CalcColor function *************/

    /**
     * calculates the color at a certain point (pixel) by calling calculator function
     *
     * @param - GeoPoint in the space (pixel on the view plane)
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geopoint, Ray inRay) {
        return calcColor(geopoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0, new ArrayList<Ray>()).add(
                scene.getAmbient().getIntensity());
    }

    /**
     * calculates the color at a certain point (pixel)
     *
     * @param - GeoPoint in the space (pixel on the view plane)
     * @return the color of the point
     */
    private Color calcColor(GeoPoint geopoint, Ray inRay, int level, double k, ArrayList<Ray> rayList) {
        Color color = geopoint.geometry.getEmission(); // remove Ambient Light

        Vector v = geopoint.point.subtract(scene.getCamera().getP0()).normalize();
        Vector n = geopoint.geometry.getNormal(geopoint.point);
        double kd = geopoint.geometry.getMaterial().getKd();
        double ks = geopoint.geometry.getMaterial().getKs();
        int nShininess = geopoint.geometry.getMaterial().getShininess();
        for (LightSource lightSource : scene.getLights()) {
            Vector l = lightSource.getL(geopoint.point);
            double e1 = n.dotProduct(l);
            double e2 = n.dotProduct(v);
            if ((e1 > 0 && e2 > 0) || (e1 < 0 && e2 < 0)) {
                double ktr = transparency(l, n, geopoint, lightSource.getDistance(geopoint.point));
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        if (level == 1) return Color.BLACK;
        double kr = geopoint.geometry.getMaterial().getKr();
        double kkr = k * kr;
        if (kkr > MIN_CALC_COLOR_K) {
            Ray reflectedRay = constructReflectedRay(n, geopoint.point, inRay);
            color = calcMatte(color, level, rayList, geopoint, reflectedRay, kkr, kr);
        }
        double kt = geopoint.geometry.getMaterial().getKt();
       double kkt = k * kt;
        if (kkt > MIN_CALC_COLOR_K) {
            Ray refractedRay = constructRefractedRay(geopoint.point, inRay);
            color = calcMatte(color, level, rayList, geopoint, refractedRay, kkt, kt);
        }
        return color;
    }

    /**
     * calculates the matte effect by an average of the perturbed rays
     *
     * @return color of a pixel
     */
    private Color calcMatte(Color color, int level, ArrayList<Ray> rayList, GeoPoint geopoint, Ray refRay, double kk, double k) {
            GeoPoint refractedPoint = findClosestIntersection(refRay);
            if (refractedPoint != null)
                color = color.add(calcColor(refractedPoint, refRay, level - 1, kk, rayList).scale(k));
        return color;
    }






    /**
     * the function calculates the diffusion of the light on the geometry
     *
     * @param kd             - the diffusive coefficient index
     * @param l              - the direction of the light source
     * @param n              - the normal to the geometry
     * @param lightIntensity - the intensity of the light
     * @return the diffused intensity of the light
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double diffusive = kd * Math.abs(l.dotProduct(n));
        return lightIntensity.scale(diffusive);
    }

    /**
     * the function calculates the specular reflection of the light on the geometry
     *
     * @param ks             - the specular coefficient index
     * @param l              - the direction of the light
     * @param n              - the normal to the geometry
     * @param v              - the direction from the camera to the geometry
     * @param shininess      - the shininess index
     * @param lightIntensity - the intensity of the light
     * @return the specular reflection intensity
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int shininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();
        double temp = v.scale(-1).dotProduct(r);
        if (temp <= 0)
            return new Color(0, 0, 0);
        return lightIntensity.scale(ks * Math.pow(temp, shininess));
    }

    /**
     * the function calculates the transparency of the geometry ang the point
     *
     * @param l        - the direction of the light source
     * @param n        - the normal to the geometry
     * @param geopoint - the transparency in the point
     * @return the transparency intense
     */
    private double transparency(Vector l, Vector n, GeoPoint geopoint, double lightDistance) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? Delta : -Delta);
        Point3D point = geopoint.point.add(delta);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.getGeometries().findIntersections(lightRay);
        if (intersections == null) return 1;
        double ktr = 1;
        for (GeoPoint gp : intersections)
            if (gp.point.distance(geopoint.point) <= lightDistance) {
                ktr *= gp.geometry.getMaterial().getKt();
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        return ktr;
    }

    /**
     * the function calculates the reflected ray from the geometry
     *
     * @param n     - the normal to the geometry
     * @param point - the intersection point
     * @param inRay - the ray to the point
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Vector n, Point3D point, Ray inRay) {
        Vector v = inRay.get_direction();
        Vector r = v.subtract(n.scale(v.dotProduct(n) * 2));
        Point3D newPoint = point.add(r.scale(0.1));
        return new Ray(newPoint, r);
    }

    /**
     * the function refracts the ray by Snell's law
     *
     * @param point - the intersection point
     * @param inRay - the ray to the point
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Point3D point, Ray inRay) {
        Vector v = inRay.get_direction();
        Point3D newPoint = point.add(v.scale(0.1));
        return new Ray(newPoint, v);
    }



    /**
     * the function finds the closest intersection point using the getClosestPoint
     * that take a intersection list of geopoints and return the closest.
     *
     * @param ray in space
     * @return the closest geometry intersection point to the camera
     */
    private GeoPoint findClosestIntersection(Ray ray) {

        List<GeoPoint> points = scene.getGeometries().findIntersections(ray);

        if (points == null)
            return null;
        Point3D p0 = ray.get_POO();
        return pointClosestTo(points, p0);
    }

    /**
     * finds the closest point from a given list to a point
     *
     * @param points
     * @param p0
     * @return the closest point
     */
    private GeoPoint pointClosestTo(List<GeoPoint> points, Point3D p0) {
        double minValue = points.get(0).getPoint().distance(p0);
        GeoPoint minPoint = points.get(0);
        for (int i = 0; i < points.size(); ++i) {
            GeoPoint p = points.get(i);
            double d0 = p.point.distance(p0);
            if (d0 < minValue) {
                minValue = d0;
                minPoint = p;
            }
        }
        return minPoint;
    }
}
