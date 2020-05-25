package geometries;

import primitives.*;


public abstract class  Geometry implements Intersectable  {
	 /**
     * Calculates unit vector orthogonal to the tangent plane at the point (which is
     * called - normal)
     *
     * @param p point of tangent...
     * @return normal vector
     */
	protected Color _emission;
	protected Material material;
	
	public Geometry() {
		this(Color.BLACK);
	}
	public Geometry(Color emission) {
		this._emission = emission;
		this.material = new Material(0.0,0.0, 0);
	}
	
	public Geometry(Color emission,  Material _material ) {
		this._emission = emission;
		this.material = _material;
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
	
	 /**
     * getter of the material of the geometry
     *
     * @return the material
     */
    public Material getMaterial() {
        return this.material;
    }
	public abstract Vector getNormal(Point3D point);
}
