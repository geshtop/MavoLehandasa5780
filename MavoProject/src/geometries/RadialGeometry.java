package geometries;
import static primitives.Util.isZero;

import primitives.Color;


/**
 * class represents an abstract base class of radial geometries
 */
public abstract class RadialGeometry extends Geometry{
    double  _radius;

	/**
	 * 
	 * @param _radius
	 */
    public RadialGeometry(double _radius) {
        if (isZero(_radius) || (_radius < 0.0))
            throw new IllegalArgumentException("radius "+ _radius +" is not valid");
        this._radius = _radius;
    }
    public RadialGeometry(Color emission, double _radius) {
    	  this(_radius);
          this._emission = emission;
    }
    public RadialGeometry(RadialGeometry other){
        this._radius= other._radius;
    }
    
    /**
     * Gets the radius of the geometry
     *
     * @return the radius
     */
    public double get_radius() {
        return _radius;
    }
}