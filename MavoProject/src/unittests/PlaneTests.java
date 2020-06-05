/**
 * 
 */
package unittests;



import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import geometries.Intersectable.GeoPoint;

import geometries.*;
import primitives.*;
/**
 * Unit tests for geometries.Plane class
 * @author Owner
 *
 */
public class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		//test normal by 3 points
		Point3D point1 = new Point3D (1,2,-2);
		Point3D point2 = new Point3D (3,-2,1);
		Point3D point3 = new Point3D (5,1,-4);

		
		Vector expectedNormal = new Vector(11,16,14).normalized();
		Plane plane = new Plane (point1,point2,point3);
		
		Vector v = plane.getNormal(new Point3D (0,0,0));
		//System.out.println(v);
		
		//System.out.println(expectedNormal);
        assertEquals("Bad normal to planes",v,expectedNormal);
	
		
		//test normal by point and normal
        
        Plane plane2 = new Plane (point1,new Vector(11,16,14));
		Vector v2 = plane2.getNormal(new Point3D (0,0,0));
		assertEquals("Bad normal to planes",v2,expectedNormal);
       
        
	}

    static Point3D p = new Point3D(1, 1, 0);
    static Vector dir = new Vector(0, 0, 1);

    /**
     * test Method for {@link geometries.Plane#findIntersections (geomtries.Plane)}
     */
    @Test
    public void testFindIntersections() {
        Plane plane = new Plane(p, dir);
        Ray ray = new Ray(new Point3D(0, 0, 1), new Vector(1, 1, -1));
        List<GeoPoint> intersections = Arrays.asList(new GeoPoint(plane, new Point3D(1, 1, 0)));
        assertEquals("Find intersections function error", intersections, plane.findIntersections(ray)); // EP ray
        // intersects
        // with the
        // plane
        ray = new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 1));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // EP ray does not
        // intersect with the
        // plane
        ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 0));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray is parallel
        // and included in the
        // plane
        ray = new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 0));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray is parallel
        // and not included in
        // the plane
        ray = new Ray(new Point3D(0, 0, 1), new Vector(0, 0, 1));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray is orthogonal
        // and after p0
        ray = new Ray(new Point3D(0, 0, -1), new Vector(0, 0, 1));
        intersections = Arrays.asList(new GeoPoint(plane,new Point3D(0, 0, 0)));
        assertEquals("Find intersections function error", intersections, plane.findIntersections(ray)); // BVA ray is
        // orthogonal
        // and before p0
        ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray is orthogonal
        // and in p0
        ray = new Ray(new Point3D(0, 0, 0), new Vector(1, 1, 1));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray begins in the
        // plane
        ray = new Ray(new Point3D(1, 1, 0), new Vector(1, 1, 1));
        intersections = Arrays.asList(new GeoPoint(plane,new Point3D(1, 1, 0)));
        assertEquals("Find intersections function error", null, plane.findIntersections(ray)); // BVA ray begins in the
        // reference point of
        // the plane
    }
}
