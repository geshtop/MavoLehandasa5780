package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Cylinder extends RadialGeometry {
	//**************************************************************************//
	//********************************PRIVATE***********************************//
	//**************************************************************************//
	private double _height;
	//**************************************************************************//
	//***********************************CTOR***********************************//
	//**************************************************************************//
	Cylinder(double radius, double height) {
		super(radius);
		this._height = height;
	}

	@Override
	public Vector getNormal(Point3D point) {
		// TODO Auto-generated method stub
		return null;
	}

	//**************************************************************************//
	//*******************************GETTER SETTER******************************//
	//**************************************************************************//
	public double get_height() {
		return _height;
	}

}
