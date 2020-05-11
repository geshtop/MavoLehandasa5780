package renderer;

import java.util.List;

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
					List<Point3D> intersectionPoints = scene.getGeometries().findIntersections(ray);

					Point3D closestPoint = getClosestPoint(intersectionPoints);
					image.writePixel(j, i, //
							closestPoint == null ? bg : calcColor(closestPoint).getColor());
				}

			
		}
	}
	
	/**
	 * the function finds the closest intersection point using the getClosestPoint
	 * that take a intersection list of Point3D and return the closest.
	 *
	 * @param ray in space
	 * @return the closest geometry intersection point to the camera
	 */
	private Point3D getClosestPoint(List<Point3D> points) {
		if (points == null)
			return null;
		Point3D p0 = scene.getCamera().getP0();
		double minValue = Double.POSITIVE_INFINITY;
		Point3D minPoint = null;
		try {
			for (Point3D p : points) {
				double d0 = p.distance(p0);
				if (d0 < minValue) {
					minValue = d0;
					minPoint = p;
				}
			}
		} catch (Exception e) {
			
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
		private Color calcColor(Point3D point) {
			return scene.getAmbient().getIntensity();
		}


	/**
	 * saves a 2D image of the rendered scene
	 */
	public void writeToImage() {
		image.writeToImage();
	}
}
