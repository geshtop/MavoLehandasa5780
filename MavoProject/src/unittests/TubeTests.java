/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;

/**
 * Unit tests for geometries.Tube class
 * @author Tamar Sebbag
 *
 */
public class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Point3D p = new Point3D(3,3,3);
		Vector v = new Vector(1,1,1);
		Ray ray = new Ray(p,v);
		Tube tube = new Tube(5, ray);
		Vector n = tube.getNormal(new Point3D(5,4,3));
		//System.out.println(n);
		Vector expectedNormal = new Vector(-0.2672612419124244, -0.5345224838248488, -0.8017837257372732);


		assertEquals("Bad normal to tube",n,expectedNormal);
	}

}
