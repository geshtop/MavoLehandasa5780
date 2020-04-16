/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import static primitives.Util.*;
import org.junit.Test;

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

}
