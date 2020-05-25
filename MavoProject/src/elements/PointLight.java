package elements;

import primitives.*;


public class PointLight extends Light implements LightSource {

	    Point3D _position;
	    double _kC, _kL, _kQ;
	    // ***************** Constructors ********************** //

	    /**
	     * constructs a point light
	     *
	     * @param color    - color of the light
	     * @param position - position of the light in the space
	     * @param kC
	     * @param kL
	     * @param kQ
	     */
	    public PointLight(Color color, Point3D position, double kC, double kL, double kQ) {
	    	super(color);
	        this._position = position;
	        this._kC = kC; //kc >= 1 - because the light reduces by the distance
	        this._kL = kL; //linear attenuation reduction
	        this._kQ = kQ; //quadratic attenuation reduction
	    }

	    // ***************** Getters/Setters ********************** //
	    @Override
	    public Vector getL(Point3D p) {
	        return p.subtract(_position).normalize();
	    }

	    @Override
	    public Color getIntensity(Point3D p) {
	    	//מרחק בין הנקודות
	        double d2 = p.distanceSquared(_position);
	        double d = Math.sqrt(d2);
	        return _intensity.reduce(_kC + _kL * d + _kQ * d2);
	    }
	
}