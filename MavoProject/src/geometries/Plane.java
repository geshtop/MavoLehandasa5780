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
		Vector v1 = point3d2.subtract(point3d);
		Vector v2 = point3d3.subtract(point3d);
		this._normal = v1.crossProduct(v2).normalized();

	}
	/**
	 * 
	 * @param p
	 * @param normal
	 */
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
		return _normal;
	}

	
	
}
