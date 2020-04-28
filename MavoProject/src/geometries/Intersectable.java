/**
 * 
 */
package geometries;
import primitives.*;
import java.util.List;

/**
 * @author Owner
 *
 */
public interface Intersectable {
	/**
	 * finds intersections of the ray with the Geometry
	 *
	 * @param ray
	 * @return intersection point
	 */
	List<Point3D> findIntersections(Ray ray);

}
