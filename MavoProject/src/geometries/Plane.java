package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry {
	//**************************************************************************//
	//********************************PRIVATE***********************************//
	//**************************************************************************//
	private Point3D _p;
	private Vector _normal;
	
	//**************************************************************************//
	//***********************************CTOR***********************************//
	//**************************************************************************//
	public Plane(Point3D point3d, Point3D point3d2, Point3D point3d3) {
		this._p = point3d;
		//TODO

	}
	
	public Plane(Point3D p, Vector normal) {
		this._p = p;
		this._normal = normal.normalized();

	}

	
	//**************************************************************************//
	//*******************************GETTER SETTER******************************//
	//**************************************************************************//
	public Vector getNormal() {
		return _normal;
	}

	public Point3D get_p() {
		return _p;
	}
	

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
