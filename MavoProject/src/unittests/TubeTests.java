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

    public static Point3D basePoint = new Point3D(0, 0, 0);
    public static Vector vec = new Vector(0, 1, 0);
    public static Ray asix = new Ray(basePoint, vec);

    /**
     * test Method for {@link geometries.Tube#getNormal (geomtries.Tube)}
     */
    @Test
    public void testGetNormal() {
        // Test of normal on the tube
        Tube tube = new Tube(asix, 1);
        Point3D p = new Point3D(1, 2, 0);
        Vector actual = new Vector(1, 0, 0);
        assertEquals("Normal function error", tube.getNormal(p), actual);
    }

}
