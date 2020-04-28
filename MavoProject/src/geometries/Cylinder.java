package geometries;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;



import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {
	
	private double _height;
	
	// ***************** Constructors ********************** //
		/**
		 * Constructs a cylinder with radius, height and axis ray
		 *
		 * @param double _radius, radius of the cylinder
		 * @param double _height, height of the cylinder
		 * @param Ray    _axis, axis of the cylinder
		 * @throws new IllegalException when radius is smaller than zero
		 */
	public Cylinder(double _radius, double _height, Ray _axis) {
		super(_axis, _radius);
		if (_height > 0)
			this._height = _height;
		else {
			throw new IllegalArgumentException("Radius is smaller than zero");
		}
	}


	/**
	 * getter of height
	 * @return double _height 
	 */
	public double get_height() {
		return _height;
	}
	
	 @Override
	    public Vector getNormal(Point3D point) {
	        Point3D o = _axisRay.get_POO();
	        Vector v = _axisRay.get_direction();

	        // projection of P-O on the ray:
	        double t;
	        try {
	            t = alignZero(point.subtract(o).dotProduct(v));
	        } catch (IllegalArgumentException e) { // P = O
	            return v;
	        }

	        // if the point is at a base
	        if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
	            return v;

	        o = o.add(v.scale(t));
	        return point.subtract(o).normalize();
	    }





}
