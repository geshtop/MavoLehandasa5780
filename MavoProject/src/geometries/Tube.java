package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
	private Ray _axisRay;
	Tube(double radius, Ray axisRay) {
		super(radius);
		this._axisRay = axisRay;
	}

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

	public Ray get_axisRay() {
		return _axisRay;
	}

	

}
