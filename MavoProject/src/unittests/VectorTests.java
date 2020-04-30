/**
 * 
 */
package unittests;


import static org.junit.Assert.*;
import static primitives.Util.*;
import org.junit.Test;

import primitives.*;

/**
 * Unit tests for primitives.Vector class
 * @author Owner
 *
 */
public class VectorTests {
    /**
     * Test succeed when IllegalArgumentException is thrown and fail when zero
     * vector is successfully created.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testVectorZero() {
        new Vector(Point3D.ZERO);
    }


	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	public void testSubtract() {
		Vector v1 = new Vector(1, 0, 3);
        Vector v2 = new Vector(-1,4,2);
        
        Vector result = v1.subtract(v2);
        Vector expectedResult = new Vector(2,-4,1);
        assertEquals("testSubtract() wrong result substract",result, expectedResult);

		
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	public void testAdd() {
		Vector v1 = new Vector(1, 0, 3);
        Vector v2 = new Vector(-1,4,2);
        
        Vector result = v1.add(v2);
        Vector expectedResult = new Vector(0,4,5);
        assertEquals("testAdd() wrong result add",result, expectedResult);
	}

	/**
	 * Test method for {@link primitives.Vector#scale(int)}.
	 */
	@Test
	public void testScale() {
		Vector v1 = new Vector(1, 0, 3);
       int scale = 8;
        
        Vector result = v1.scale(scale);
        Vector expectedResult = new Vector(8,0,24);
        assertEquals("testScale() wrong result scale",result, expectedResult);
	}

	/**
	 * test Dot-Product
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	public void testDotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);

        // Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v3)));

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}

	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	public void testCrossProduct() {
		  	Vector v1 = new Vector(1, 2, 3);
	        Vector v2 = new Vector(-2, -4, -6);
	        Vector v3 = new Vector(0, 3, -2);
	        try {
	        	  v1.crossProduct(v2);
	        	  fail("ERROR: crossProduct() for parallel vectors does not throw an exception");
	        } catch (IllegalArgumentException e) {
	            
	        }
	        Vector vr = v1.crossProduct(v3);
	        assertTrue("ERROR: crossProduct() wrong result length", isZero(vr.length() - v1.length() * v3.length()));
	        assertTrue("ERROR: crossProduct() result is not orthogonal to its operands", isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)));

	       
	}

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	public void testLengthSquared() {
		Vector v1 = new Vector(1, 2, 3);
		 assertTrue("lengthSquared()",isZero(v1.lengthSquared() - 14));
		
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	public void testLength() {
		 Vector v1 = new Vector(0, 3, 4);
	     assertTrue("length()",isZero(v1.length()- 5));		
	}

	/**
	 * test vector normalization vs vector length and cross-product
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	public void testNormalize() {
		  Vector v = new Vector(1, 2, 3);
	        Vector vCopy = new Vector(v);
	        Vector vCopyNormalize = vCopy.normalize();
	        assertTrue("ERROR: normalize() function creates a new vector", vCopy == vCopyNormalize);
	        assertTrue("ERROR: normalize() result is not a unit vector",isZero(vCopyNormalize.length() - 1));


	}

	/**
	 * Test method for {@link primitives.Vector#normalized()}.
	 */
	@Test
	public void testNormalized() {
		 Vector v = new Vector(1, 2, 3);
		 Vector u = v.normalized();
		 assertTrue("ERROR: normalizated() function does not create a new vector",u != v);

	}

}
