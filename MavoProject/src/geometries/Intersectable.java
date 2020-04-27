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
	 * ������� ������ ��� ������� ������ ����� �� ��������
	 * @param ray
	 * @return ������ �����
	 */
	List<Point3D> findIntersections(Ray ray);

}
