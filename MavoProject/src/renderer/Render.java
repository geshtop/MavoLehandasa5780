package renderer;

import java.util.List;

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
					//יוצרים קרן אל הפיקסל
					Ray ray = scene.getCamera().constructRayThroughPixel(nx, ny, //
							j, i, distance, width, height);
					//מוצאים את נקודות החיתוך עם הגאומטריות
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
		private Color calcColor(GeoPoint point) {
			Color color = scene.getAmbient().getIntensity();
	        color = color.add(point.geometry.getEmission());
	    
			//return scene.getAmbient().getIntensity();
			return color;
		}


	/**
	 * saves a 2D image of the rendered scene
	 */
	public void writeToImage() {
		image.writeToImage();
	}
}

