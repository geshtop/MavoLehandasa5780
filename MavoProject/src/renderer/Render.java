package renderer;

import elements.Camera;
import elements.LightSource;
import geometries.BoundaryVolume;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.*;
import scene.Scene;
import primitives.Util;
import static geometries.Intersectable.GeoPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * class represents a renderer for a scene
 */
public class Render {
	private int _threads = 1;
	private final int SPARE_THREADS = 2;
	private boolean _print = false;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double Delta = 0.1;
    private boolean _usingBoundaryVolume = false;
    ImageWriter image;
    int _sampleCount;
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
	 * פיקסל עזר לשימוש בתרד
	 * Pixel is an internal helper class whose objects are associated with a Render object that
	 * they are generated in scope of. It is used for multithreading in the Renderer and for follow up
	 * its progress.<br/>
	 * There is a main follow up object and several secondary objects - one in each thread.
	 * @author Dan
	 *
	 */
	private class Pixel {
		private long _maxRows = 0;
		private long _maxCols = 0;
		private long _pixels = 0;
		public volatile int row = 0;
		public volatile int col = -1;
		private long _counter = 0;
		private int _percents = 0;
		private long _nextCounter = 0;

		/**
		 * The constructor for initializing the main follow up Pixel object
		 * @param maxRows the amount of pixel rows
		 * @param maxCols the amount of pixel columns
		 */
		public Pixel(int maxRows, int maxCols) {
			_maxRows = maxRows;
			_maxCols = maxCols;
			_pixels = maxRows * maxCols;
			_nextCounter = _pixels / 100;
			if (Render.this._print) System.out.printf("\r %02d%%", _percents);
		}

		/**
		 *  Default constructor for secondary Pixel objects
		 */
		public Pixel() {}

		/**
		 * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
		 * critical section for all the threads, and main Pixel object data is the shared data of this critical
		 * section.<br/>
		 * The function provides next pixel number each call.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
		 * finished, any other value - the progress percentage (only when it changes)
		 */
		private synchronized int nextP(Pixel target) {
			++col;
			++_counter;
			if (col < _maxCols) {
				target.row = this.row;
				target.col = this.col;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			++row;
			if (row < _maxRows) {
				col = 0;
				if (_counter == _nextCounter) {
					++_percents;
					_nextCounter = _pixels * (_percents + 1) / 100;
					return _percents;
				}
				return 0;
			}
			return -1;
		}

		/**
		 * Public function for getting next pixel number into secondary Pixel object.
		 * The function prints also progress percentage in the console window.
		 * @param target target secondary Pixel object to copy the row/column of the next pixel 
		 * @return true if the work still in progress, -1 if it's done
		 */
		public boolean nextPixel(Pixel target) {
			int percents = nextP(target);
			if (percents > 0)
				if (Render.this._print) System.out.printf("\r %02d%%", percents);
			if (percents >= 0)
				return true;
			if (Render.this._print) System.out.printf("\r %02d%%", 100);
			return false;
		}
	}
	/**
	 * This function renders image's pixel color map from the scene included with
	 * the Renderer object
	 */
	public void renderImage() {
		final int nX = image.getNx();
		final int nY = image.getNy();

		final Pixel thePixel = new Pixel(nY, nX);

		// Generate threads
		Thread[] threads = new Thread[_threads];
		for (int i = _threads - 1; i >= 0; --i) {
			threads[i] = new Thread(() -> {
				Pixel pixel = new Pixel();
				while (thePixel.nextPixel(pixel)) {
					 Ray ray = scene.getCamera().constructRayThroughPixel
		                        (image.getNx(), image.getNy(), pixel.col, pixel.row,
		                                scene.getDistance(), image.getHeight(), image.getWidth());
		                //מחזירה את הנקודה הכי קרובה
		                GeoPoint closestPoint = findClosestIntersection(ray);
		                //בודקים אם נמצאה נקודה צובעים בצבע המחושב ואם לא בצבע הרקע
		                image.writePixel(pixel.col, pixel.row, closestPoint == null ? scene.getBackground().getColor() : calcColor(closestPoint, ray).getColor());

				}
			});
		}

		// Start threads
		for (Thread thread : threads) thread.start();

		// Wait for all threads to finish
		for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
		if (_print) System.out.printf("\r100%%\n");
	}

    
    /**
     * renders the scene to a 2D picture // before add therd
     */
//    public void renderImage() {
//        // for all the Y (height) pixels in the view plane
//        for (int i = 0; i < image.getNy(); ++i) {
//            // for all the X (width) pixels in the view plane
//            for (int j = 0; j < image.getNx(); ++j) {
//                // construct ray trough pixel [j,i]
//            	//יוצרים קרן דרך נקודה
//                Ray ray = scene.getCamera().constructRayThroughPixel
//                        (image.getNx(), image.getNy(), j, i,
//                                scene.getDistance(), image.getHeight(), image.getWidth());
//                //מחזירה את הנקודה הכי קרובה
//                GeoPoint closestPoint = findClosestIntersection(ray);
//                //בודקים אם נמצאה נקודה צובעים בצבע המחושב ואם לא בצבע הרקע
//                image.writePixel(j, i, closestPoint == null ? scene.getBackground().getColor() : calcColor(closestPoint, ray).getColor());
//            }
//        }
//    }
	/**
	 * פונקציה ליצירת מולטי תרד
	 * Set multithreading <br>
	 * - if the parameter is 0 - number of coress less 2 is taken
	 * 
	 * @param threads number of threads
	 * @return the Render object itself
	 */
	public Render setMultithreading(int threads) {
		if (threads < 0)
			throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
		if (threads != 0)
			_threads = threads;
		else {
			int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
			if (cores <= 2)
				_threads = 1;
			else
				_threads = cores;
		}
		return this;
	}

	/**
	 * פונקציה להדפסת אחוזים
	 * Set debug printing on
	 * 
	 * @return the Render object itself
	 */
	public Render setDebugPrint() {
		_print = true;
		return this;
	}
	/**
	 * פונקציה להטמעת מספר הקרניים
	 * setter of the ray samples count
	 *
	 * @param sampleCount
	 */
	public Render setSampleCount(int sampleCount) {
		_sampleCount = sampleCount;
		return this;
	}
	

    /**
     * פונקציה להדפסת הגריד
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
	 * calculates the color at a certain point (pixel) by calling calculator
	 * function
	 *
	 * @param - GeoPoint in the space (pixel on the view plane)
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay) {
		return calcColor(geopoint, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(scene.getAmbient().getIntensity());
	}

    /**
     * calculates the color at a certain point (pixel)
     *
     * @param - GeoPoint in the space (pixel on the view plane)
     * @return the color of the point
     */

	/**
	 * פונקציה המקבלת נקודה ומחשבת את הצבע
	 * חישוב השקיפות
	 * הדיפיוז והספקולר
	 * calculates the color at a certain point (pixel)
	 *
	 * @param - GeoPoint in the space (pixel on the view plane)
	 * @return the color of the point
	 */
	private Color calcColor(GeoPoint geopoint, Ray inRay, int level, double k) {
		if (level == 0 || k < MIN_CALC_COLOR_K)
			return Color.BLACK;
		Color color = geopoint.geometry.getEmission(); // remove Ambient Light

		Vector v = geopoint.point.subtract(scene.getCamera().getP0()).normalize();
		Material mat = geopoint.geometry.getMaterial();
		Vector n = geopoint.geometry.getNormal(geopoint.point);
		double kd = mat.getKd();
		double ks = mat.getKs();
		int nShininess = mat.getShininess();
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
		
		double kr = mat.getKr();
		double kkr = k * kr;
		if (kkr > MIN_CALC_COLOR_K) {
			Ray reflectedRay = constructReflectedRay(n, geopoint.point, inRay);
			color = color.add(calcSampledColor(mat.getMatte(), //
					level, reflectedRay, n, kkr).scale(kr));
		}
		double kt = geopoint.geometry.getMaterial().getKt();
		double kkt = k * kt;
		if (kkt > MIN_CALC_COLOR_K) {
			Ray refractedRay = constructRefractedRay(n, geopoint.point, inRay);
			color = color.add(calcSampledColor(mat.getDiffusion(), //
					level, refractedRay, n, kkt).scale(kt));
		}
		return color;
	}
    



	public int getSampleCount() {
		return _sampleCount;
	}
	// make d can be changed
	private int d = 10;

	/**
	 * setter of parameter d
	 * 
	 * @param distance - p0pc the distance in which we make create cycle of random
	 *                 rays
	 */
	public void setterD(int distance) {
		d = distance;
	}

	private static final Random RANDOM = new Random(System.currentTimeMillis());

    
	/**
	 * פונקציה למיני פרוייקט שלב ראשון לשיפור התמונה
	 * במקום לשלוח קרן אחת לדיפיוז והספקולר
	 * לשקיפות והשתקפות
	 * מבוצע רנדומלי
	 * 
	 * calculates the matte effect by an average of the perturbed rays
	 *
	 * @return color of a pixel
	 */
	private Color calcSampledColor(double radius, int level, Ray refRay, Vector n, double k) {
		Point3D p0 = refRay.get_POO();
		Vector dir = refRay.get_direction();
		Point3D head = dir.get_head();
		double ndir = primitives.Util.alignZero(n.dotProduct(dir));
		if (ndir == 0)
			return Color.BLACK;

		GeoPoint gp = findClosestIntersection(refRay);
		Color bg = scene.getBackground();
		Color color = gp == null ? bg : calcColor(gp, refRay, level - 1, k);

		if (radius == 0)
			return color;
		int samples = this.getSampleCount();
		if (samples == 0)
			return color;

		double x = head.get_x().get();
		double y = head.get_y().get();
		double z = head.get_z().get();

		Vector v = null;
		if (x <= y) {
			if (x <= z)
				v = new Vector(0, z, -y).normalize();
			else
				v = new Vector(-y, x, 0).normalize();
		} else {
			if (y <= z)
				v = new Vector(-z, 0, x).normalize();
			else
				v = new Vector(-y, x, 0).normalize();
		}
		Vector u = v.crossProduct(dir).normalize();

		Point3D pc = p0.add(dir.scale(d));
		for (int i = this.getSampleCount() - 1; i > 0; --i) {
			Point3D p;
			Vector w;
			double nw;
			do {
				p = pc;
				x = RANDOM.nextDouble() * 2 - 1;
				y = Math.sqrt(1 - x * x);
				if (!Util.isZero(x))
					p = p.add(v.scale(x));
				if (!primitives.Util.isZero(y))
					p = p.add(u.scale(y));
				z = radius * (RANDOM.nextDouble() * 2 - 1);
				p = pc.add(p.subtract(pc).scale(z));
				w = p.subtract(p0);
				nw = primitives.Util.alignZero(n.dotProduct(w));
			} while (ndir > 0 && nw < 0 || ndir < 0 && nw > 0);

			Ray ray = new Ray(p0, w);
			gp = findClosestIntersection(ray);
			color = color.add(gp == null ? bg : calcColor(gp, ray, level - 1, k));
		}
		return color.reduce(samples + 1);
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
     * פונקציה המחזירה את השקיפות
     * כאשר 1 זה אטום
     * ו0 עד 1 זה אחוזי השקיפות
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
        
        //this is the original function that find the instection with all the geometirs
        //List<GeoPoint> intersections = scene.getGeometries().findIntersections(lightRay);
        List<GeoPoint> intersections = splitFindIntersection(lightRay);
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
	 * פונקציה המחזירה קרן שקיפות
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
		Point3D newPoint = point.add(n.scale(n.dotProduct(r) > 0 ? Delta : -Delta));
		Ray ray = new Ray(newPoint, r);
		/// if (ray.getDir().dotProduct(n) > 0)
		return ray;
		/// return null;
	}

	/**
	 * פונקציה המחזירה קרן השתקפות
	 * the function refracts the ray by Snell's law
	 *
	 * @param point - the intersection point
	 * @param inRay - the ray to the point
	 * @return the refracted ray
	 */
	private Ray constructRefractedRay(Vector n, Point3D point, Ray inRay) {
		Vector v = inRay.get_direction();
		Point3D newPoint = point.add(n.scale(n.dotProduct(v) > 0 ? Delta : -Delta));
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
    	//this is the original function before the boundry 
        //List<GeoPoint> points = scene.getGeometries().findIntersections(ray);
        List<GeoPoint> points = splitFindIntersection(ray);
        if (points == null)
            return null;
        Point3D p0 = ray.get_POO();
        return pointClosestTo(points, p0);
    }
    
    //####################################################################Boundry changes #####################################################################//
	/**
	 * Setter of boundary volume
	 * 
	 * @param usingBoundaryVolume
	 */
	public Render setUsingBoundaryVolume(boolean usingBoundaryVolume) {
		_usingBoundaryVolume = usingBoundaryVolume;
		return this;
	}
    
 // for saving the time of new list creation several times
 	private List<GeoPoint> intersectionsBVH = new ArrayList<GeoPoint>();

 	/**
 	 * travel around the geometries tree whether the ray is inside the box or not
 	 * thus , build the following intersections
 	 * 
 	 * @param geometries - Geometries
 	 */
 	public void GeometriesBVH(Geometries geometries, Ray ray) {
 		if(ray == null) return;
 		if (geometries.boundaryVolume().boundingIntersection(ray)) { //founded point with box
 			if (geometries.getAmount() == 1) {
 				BoundaryVolume boundary = geometries.boundaryVolume();
 				if (boundary.boundingIntersection(ray) || boundary == null)// if plane
 					if (geometries.findIntersections(ray) != null)
 						intersectionsBVH.addAll(geometries.findIntersections(ray));
 				return;
 			}
 			for (Intersectable geo : geometries.getShapes()) {
 				GeometriesBVH(new Geometries(geo), ray);
 				return;
 			}
 		} else {
 			return;
 		}

 	}
 	

	/**
	 * in bvh accelartion the code run up faster then for comparing the run time ,
	 * the programmer create this method
	 * 
	 * @param ray - ray in space
	 * @return list of geometry points
	 */
	public List<GeoPoint> splitFindIntersection(Ray ray) {
		Geometries geometries = scene.getGeometries();
		if (_usingBoundaryVolume) {		
			GeometriesBVH(geometries, ray);
			return intersectionsBVH;
		} else {
			return geometries.findIntersections(ray);
		}
	}
    //####################################################################Boundry changes END #####################################################################//


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
