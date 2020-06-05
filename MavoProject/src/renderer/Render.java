package renderer;

import java.util.List;

import elements.LightSource;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;

public class Render {

	private ImageWriter image;
	private Scene scene;
	
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
		int nx = image.getNx();
		int ny = image.getNy();
		double distance = scene.getDistance();
		double width = image.getWidth();
		double height = image.getHeight();
		java.awt.Color bg = scene.getBackground().getColor();
		// for all the Y (height) pixels in the view plane
		
		for (int i = ny - 1; i >= 0; --i) {
				for (int j = nx - 1; j >= 0; --j) {
					// construct ray trough pixel [j,i]
					//������ ��� �� ������
					Ray ray = scene.getCamera().constructRayThroughPixel(nx, ny, //
							j, i, distance, width, height);
					//������ �� ������ ������ �� ����������
					List<GeoPoint> intersectionPoints = scene.getGeometries().findIntersections(ray);
					
					  if (intersectionPoints == null)
		                    image.writePixel(j, i, scene.getBackground().getColor());
		                    // else, find the closest intersection to the camera and and paint the pixel
		                    // with the geometry's color
		                else {
		                	GeoPoint closestPoint = getClosestPoint(intersectionPoints);
		                	image.writePixel(j, i, //
									closestPoint == null ? bg : calcColor(closestPoint).getColor());
		                }
					
					
					
				}

			
		}
	}
	
	   /**
     * finds to closest intersection of the ray with geometry
     *
     * @param points - the intersection point
     * @return the closest point
     */ 	
    private GeoPoint getClosestPoint(List<GeoPoint> points) {
        Point3D p0 = scene.getCamera().getP0();
        double minValue = points.get(0).getPoint().distance(p0);
        GeoPoint minPoint = points.get(0);
        for (int i = 0; i < points.size(); ++i) {
            Point3D p = points.get(i).getPoint();
            double d0 = p.distance(p0);
            if (d0 < minValue) {
                minValue = d0;
                minPoint.point = p;
            }
        }
        return minPoint;
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
	
	/*********** CalcColor function *************/

	/**
	 * calculates the color at a certain point (pixel) by calling calculator
	 * function
	 *
	 * @param - Point3D in the space (pixel on the view plane)
	 * @return the color of the point
	 */
	
	private Color calcColor(GeoPoint intersection) {	
        Color color = scene.getAmbient().getIntensity();	
        color = color.add(intersection.geometry.getEmission());	
        Vector v = intersection.point.subtract(scene.getCamera().getP0()).normalize();	
        Vector n = intersection.geometry.getNormal(intersection.point);	
        double kd = intersection.geometry.getMaterial().getKd();	
        double ks = intersection.geometry.getMaterial().getKs();	
        int nShininess = intersection.geometry.getMaterial().getShininess();	
        for (LightSource lightSource : scene.getLights()) {	
            Vector l = lightSource.getL(intersection.point);	
            double e1 = n.dotProduct(l);	
            double e2 = n.dotProduct(v);	
            if ((e1 > 0 && e2 > 0) || (e1 < 0 && e2 < 0)) {	
                //if (unshaded(l, n, intersection)) {	
                    Color lightIntensity = lightSource.getIntensity(intersection.point);	
                    color = color.add((calcDiffusive(kd, l, n, lightIntensity)), calcSpecular(ks, l, n, v, nShininess, lightIntensity));	
               // }	
                }	
        }	
        return color;	
    }
	 private static final double EPS = 1.0;
	 
	 private boolean unshaded(Vector l, Vector n, GeoPoint geopoint) {	
	        Vector lightDirection = l.scale(-1); // from point to light source	
	        Vector epsVector = n.scale(n.dotProduct(lightDirection) > 0 ? EPS : -EPS);	
	        Point3D point = geopoint.point.add(epsVector);	
	        Ray lightRay = new Ray(point, lightDirection);	
	        List<GeoPoint> intersections = scene.getGeometries().findIntersections(lightRay);	
	        return intersections == null;	
	    }	


	    public Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {	
	        double diffusive = kd * Math.abs(l.dotProduct(n));	
	        return lightIntensity.scale(diffusive);	
	    }	

	    public Color calcSpecular(double ks, Vector l, Vector n, Vector v, int shininess, Color lightIntensity) {	
	        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2)).normalize();	
	        double temp = v.scale(-1).dotProduct(r);	
	        if (temp <= 0)	
	            return new Color(0, 0, 0);	
	        return lightIntensity.scale(ks * Math.pow(temp, shininess));	
	    }


	/**
	 * saves a 2D image of the rendered scene
	 */
	public void writeToImage() {
		image.writeToImage();
	}
}

