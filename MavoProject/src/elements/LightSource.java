/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author Owner
 *
 */
public interface LightSource {
    /**
     * getter of a color intensity in a certain point
     *
     * @param p - the point
     * @return the color at the point
     */
    Color getIntensity(Point3D p);

    /**
     * getter of vector between light position and point
     *
     * @param p - the point
     * @return the distance
     */
    Vector getL(Point3D p);
    
    
    /**
     * getter of distance between light position and point
     *
     * @param p - the point
     * @return the distance
     */
    double getDistance(Point3D p);
}
