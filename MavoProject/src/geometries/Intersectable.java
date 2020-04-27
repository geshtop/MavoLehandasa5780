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
	 * פונרציה המקבלת קרן ומחזירה נקודות חיתוך עם האובייקט
	 * @param ray
	 * @return נקודות חיתוך
	 */
	List<Point3D> findIntersections(Ray ray);

}
