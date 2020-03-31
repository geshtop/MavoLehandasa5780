package geometries;

import java.util.List;

import primitives.Point3D;

public class Triangle extends Polygon {
	
	//ctor
	public Triangle(Point3D point3d, Point3D point3d2, Point3D point3d3){
		List<Point3D> _temp;
		_temp.add(point3d);
		_temp.add(point3d2);
		_temp.add(point3d3);
		super(_temp);
	}

}
