package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere extends RadialGeometry {
	Sphere(double radius, Point3D center) {
		super(radius);
		this._center = center;
	}
	private Point3D _center;
	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}
	public Point3D get_center() {
		return _center;
	}

	

}
