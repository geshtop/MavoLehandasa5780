package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public abstract class  Geometry implements Intersectable  {
	 /**
     * Calculates unit vector orthogonal to the tangent plane at the point (which is
     * called - normal)
     *
     * @param p point of tangent...
     * @return normal vector
     */
	protected Color _emission;
	
	public Geometry() {
		this(Color.BLACK);
	}
	public Geometry(Color emission) {
		this._emission = emission;
	}
	
    // ***************** Getters/Setters ********************** //
    /**
 * getter of the emission light of the geometry
 *
 * @return the emission light
 */
	public Color getEmission() {
	    return _emission;
	}
	public abstract Vector getNormal(Point3D point);
}
