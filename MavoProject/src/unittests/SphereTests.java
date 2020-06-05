/**
 * 
 */
package unittests;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import geometries.*;
import geometries.Intersectable.GeoPoint;
import primitives.*;

/**
 * Unit tests for geometries.Sphere class
 * @author Tamar Sebbag
 *
 */
public class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Point3D center = new Point3D(3,3,3);
		Sphere sphere = new Sphere(5, center);
		
		Vector n = sphere.getNormal(new Point3D(5,4,3));
		//System.out.println(n);
		Vector expectedNormal = new Vector(0.8944271909999159, 0.4472135954999579, 0.0);


		assertEquals("Bad normal to sphere",n,expectedNormal);
		
		
	}

	
	/**
	 * test Method for
	 * {@link geometries.Sphere#findIntersections (geomtries.Sphere)}
	 */
	@Test
	public void testFindIntersections() {
		Sphere sphere = new Sphere(2, new Point3D(0, 0, 0)); // x^2 + y^2 + z^2 = 4

		/****************** EP ******************/

		// ray does not intersect with the sphere
		assertEquals("Sphere findIntersection error : error shouldn't intersec ", null,
				sphere.findIntersections(new Ray(new Point3D(-4, 0, 0), new Vector(-4, -4, 0))));
		// ray intersects with the sphere at two points
		double x = 1.4142135623731;
		List<GeoPoint> intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(0, 0, 2)),
				new GeoPoint(sphere, new Point3D(-2, 0, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				sphere.findIntersections(new Ray(new Point3D(-3, 0, -1), new Vector(2, 0, 2))));
		// ray intersects with the sphere at one point, while p0 is in the sphere
		x = 1.0696938456699;
		double y = 1.6898979485566;
		intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(x, y, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				sphere.findIntersections(new Ray(new Point3D(-1, 1, 0), new Vector(3, 1, 0))));
		// ray does not intersect with the sphere but the line have two intersection
		// points
		assertEquals("Sphere findIntersection error : error shouldn't intersect ", null,
				sphere.findIntersections(new Ray(new Point3D(-3, 0, -1), new Vector(-2, 0, -2))));

		/****************** BVA ******************/

		/******* BVA case 1 *******/
		// P0 is on the sphere and the ray points to the outside
		assertEquals("Sphere findIntersection error ", null,
				sphere.findIntersections(new Ray(new Point3D(-2, 0, 0), new Vector(-1, 0, 0))));
		// P0 is outside and there is no intersection.
		assertEquals("Sphere findIntersection error ", null,
				sphere.findIntersections(new Ray(new Point3D(-3, 0, 0), new Vector(-1, 0, 0))));
		// the ray is from the center of the sphere
		intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(-2, 0, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				sphere.findIntersections(new Ray(new Point3D(0, 0, 0), new Vector(-1, 0, 0))));
		// the ray is from outside the sphere to the other side of the sphere by the
		// diameter
		intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(-2, 0, 0)),new GeoPoint(sphere,  new Point3D(2, 0, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				sphere.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(-1, 0, 0))));
		// the ray is from the sphere to the other side of the sphere by the diameter
		intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(-2, 0, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, 0, 0))));
		// the ray is from the inside of the sphere by the diameter
		intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(-2, 0, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(-1, 0, 0))));

		/******* BVA case 2 *******/
		// the ray is from the sphere to the other side of the sphere
		intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(0, 2, 0)));
		assertEquals("Sphere findIntersection error ", intersections,
				sphere.findIntersections(new Ray(new Point3D(-2, 0, 0), new Vector(1, 1, 0))));
		// ray is from the sphere
		assertEquals("Sphere findIntersection error ", null,
				sphere.findIntersections(new Ray(new Point3D(0, 2, 0), new Vector(1, 1, 0))));

		/******* BVA case 3 *******/
		// ray is tangent to the sphere
		intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(0, 2, 0)));
		assertEquals("Sphere findIntersection error ", null,
				sphere.findIntersections(new Ray(new Point3D(-2, 2, 0), new Vector(1, 0, 0))));
		// ray is from the tangent to the sphere
		assertEquals("Sphere findIntersection error ", null,
				sphere.findIntersections(new Ray(new Point3D(0, 2, 0), new Vector(1, 0, 0))));
		// ray is on the tangent line of the sphere
		intersections = Arrays.asList(new GeoPoint(sphere, new Point3D(0, 2, 0)));
		assertEquals("Sphere findIntersection error ", null,
				sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(1, 0, 0))));

		/******* BVA case 4 *******/
		// ray is orthogonal to the radius but from outside the sphere
		assertEquals("Sphere findIntersection error ", null,
				sphere.findIntersections(new Ray(new Point3D(0, 3, 0), new Vector(1, 0, 0))));
	}

}
