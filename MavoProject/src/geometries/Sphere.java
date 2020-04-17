package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere extends RadialGeometry {
	//**************************************************************************//
	//********************************PRIVATE***********************************//
	//**************************************************************************//
	private Point3D _center;
	//**************************************************************************//
	//***********************************CTOR***********************************//
	//**************************************************************************//
	public Sphere(double radius, Point3D center) {
		super(radius);
		this._center = center;
	}
	
	@Override
	public Vector getNormal(Point3D point) {
		
		Vector v1 = new Vector( point.subtract(_center));
		return v1.normalized();
	}
	//**************************************************************************//
	//*******************************GETTER SETTER******************************//
	//**************************************************************************//
	public Point3D get_center() {
		return _center;
	}

	

}
