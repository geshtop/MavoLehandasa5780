package geometries;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.alignZero;

public class Sphere extends RadialGeometry {
	
	private Point3D _center;

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
    /**
     * constructs a sphere with a radius, center point and a color
     *
     * @param emission the color of the sphere
     * @param _radius, the radius of the sphere
     * @param _center, the center point of the sphere
     */
    public Sphere(Color emission, double _radius, Point3D _center) {
        this(_radius, _center);
        this._emission = emission;
    }
    /**
     * constructs a sphere with a radius, center point and a color
     *
     * @param emission the color of the sphere
     * @param material the material of the sphere
     * @param _radius, the radius of the sphere
     * @param _center, the center point of the sphere
     */
    public Sphere(Color emission, Material material, double _radius, Point3D _center) {
        this(emission, _radius, _center);
        this.material = material;
    }
	

    /**
     * Gets the center of the Sphere
     *
     * @return Point3D  center
     */
	public Point3D get_center() {
		return _center;
	}
	
	
	@Override
	public Vector getNormal(Point3D p) {
        return p.subtract(_center).normalized();
    }
	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		
		
		 Point3D p0 = ray.get_POO();// Getting base point of ray.
	        Vector v = ray.get_direction();// Getting base point.
	        Vector u;
	        try {
	            u = _center.subtract(p0); // O - P0
	        } catch (Exception e) {
	            // case such that p0 and O are the same point and cause u to be an zero vector
	            // therefore this if statements avoid that.
	            return Arrays.asList(new GeoPoint(this, p0.add(v.scale(this._radius))));
	        }
	        double tm = alignZero(v.dotProduct(u)); // variable tm is equal to v dot u
	        double d = alignZero(Math.sqrt(u.lengthSquared() - tm * tm)); // distance from the ray is equal to (|u|^2 -
	        // tm^2)^(0.5)
	        if (alignZero(d - _radius) > 0)
	            return null;
	        double th = alignZero(Math.sqrt(_radius * _radius - d * d));
	        if (th == 0)
	            return null;
	        double t1 = alignZero(tm + th); // calculate t1 with the equation t1 = tm + th
	        double t2 = alignZero(tm - th); // //calculate t1 with the equation t1 = tm - th.
	        if (t1 > 0 || t2 > 0) {
	            List<GeoPoint> intersections = new ArrayList<>();
	            // find point with the equation : P = p0 + t1 * v
	            if (t1 > 0)
	                intersections.add(new GeoPoint( this,  p0.add(v.scale(t1))));
	            if (t2 > 0)
	                intersections.add(new GeoPoint( this, p0.add(v.scale(t2))));
	            return intersections;
	        }
	        return null;
		
		
		
		
		
		
		
		}

	
	
	 public BoundaryVolume boundaryVolume()
	    {
	    	double cx = _center.get_x().get();
	    	double cy = _center.get_y().get();
	    	double cz = _center.get_z().get();
	    	double r = this._radius;
	    	return new BoundaryVolume(new Point3D(cx -r ,cy-r,cz-r) 
			    	,new Point3D(cx +r ,cy+r,cz+r)) ;
	    }

}
