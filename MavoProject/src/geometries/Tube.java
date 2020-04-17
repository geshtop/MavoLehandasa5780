package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
	//**************************************************************************//
	//********************************PRIVATE***********************************//
	//**************************************************************************//
	private Ray _axisRay;
	//**************************************************************************//
	//***********************************CTOR***********************************//
	//**************************************************************************//
	public Tube(double radius, Ray axisRay) {
		super(radius);
		this._axisRay = axisRay;
	}

	@Override
	public Vector getNormal(Point3D point) {
		Vector v = _axisRay.get_direction();
		Point3D P0 = _axisRay.get_POO();
	    double t= v.dotProduct(point.subtract(P0));
	    Point3D O =  P0.add(v.scale(t));
	    
	    Vector v1 = new Vector( point.subtract(O));
		return v1.normalized();
		
	}

	public Ray get_axisRay() {
		return _axisRay;
	}

	

}
