package geometries;



import java.util.ArrayList;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Sphere extends RadialGeometry {
	//**************************************************************************//
	//********************************PRIVATE***********************************//
	//**************************************************************************//
	private Point3D _center;
	//**************************************************************************//
	//***********************************CTOR***********************************//
	//**************************************************************************//
	 /**
     * constructs a sphere with a radius and center point
     *
     * @param _radius, the radius of the sphere
     * @param _center, the center point of the sphere
     */
    public Sphere(double _radius, Point3D _center) {
        super(_radius);
        this._center = _center;
    }
	
	@Override
	public Vector getNormal(Point3D p) {
        return p.subtract(_center).normalized();
    }
	//**************************************************************************//
	//*******************************GETTER SETTER******************************//
	//**************************************************************************//
	public Point3D get_center() {
		return _center;
	}

	@Override
	public List<Point3D> findIntersections(Ray ray) {
		
		  List<Point3D> Intersections = new ArrayList<Point3D>();
	        Point3D basePoint = ray.get_POO();// Getting base point of ray.
	        Vector v = ray.get_direction();// Getting base point.
	        // case such that p0 and O are the same point and cause u to be an zero vector
	        // therefore this if statements avoid that.
	        if (basePoint.equals(_center)) {
	            Point3D p = basePoint.add(v.scale(this._radius));
	            Intersections.add(p);
	            return Intersections;
	        }
	        Vector u = _center.subtract(basePoint); // O - P0
	        double tm = v.dotProduct(u); //variable tm is equal to v dot u
	        double d = Math.sqrt(u.lengthSquared() - tm * tm); // distance from the ray is equal to  (|u|^2 - tm^2)^(0.5)
	        if (d > _radius)
	            return null;
	        double th = Math.sqrt(_radius * _radius - d * d);
	        double t1 = tm + th; //calculate t1 with the equation t1 = tm + th
	        if (t1 > 0) {
	            // find point with the equation : P = p0 + t1 * v
	            Point3D p = basePoint.add(v.scale(t1));
	            Intersections.add(p);
	            if (th == 0) {
	                return Intersections;
	            }
	        }
	        double t2 = tm - th; // //calculate t1 with the equation t1 = tm - th.
	        if (t2 > 0) {
	            // find point with the equation : P = p0 + t2 * v
	            Point3D p = basePoint.add(v.scale(t2));
	            Intersections.add(p);
	        }
	        if (t1 > 0 || t2 > 0){
	            return Intersections;}
	        return null;//other cases which distance from ray is not bigger than radius but there is no intersection.
	  
	}

	

}
