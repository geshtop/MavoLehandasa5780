package elements;

import primitives.*;


/**
 * מחלקה של תאורה כיוונית
 * @author Owner
 *
 */
public class DirectionalLight extends Light implements LightSource {

	 	Vector _direction;
	    // ***************** Constructors ********************** //

	    /**
	     * constructs a directional light
	     *
	     * @param intensity - color of the light
	     * @param dir   - direction of the light
	     */
	    public DirectionalLight(Color intensity, Vector dir) {
	    	super(intensity);
	        _direction = dir.normalize();
	    }

	    // ***************** Getters/Setters ********************** //
	    @Override
	    public Color getIntensity(Point3D p) {
	        return _intensity;
	    }

	    @Override
	    public Vector getL(Point3D p) {
	        return _direction;
	    }
	
}
