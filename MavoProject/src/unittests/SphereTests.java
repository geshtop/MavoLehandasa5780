/**
 * 
 */
package unittests;


import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
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

}
