package geometries;

import static primitives.Util.alignZero;


import java.util.Arrays;
import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Plane implements Geometry {
	private Point3D _p;
	private Vector _normal;
	
	 /**
     * Constructs a plane using three points in the space
     *
     * @param p1 is first point
     * @param p2 is second point
     * @param p3 is third point
     */
		public Plane(Point3D p1, Point3D p2, Point3D p3) {
	        _p = new Point3D(p1);

	        Vector U = new Vector(p1, p2);
	        Vector V = new Vector(p1, p3);
	        Vector N = U.crossProduct(V);
	        N.normalize();

	        _normal = N;

	}
	    // ***************** Constructors ********************** //

	    /**
	     * Constructs a plane with a point and normal vector
	     *
	     * @param _p,      the base point of the normal
	     * @param _normal, the normal vector to the plane
	     */
	public Plane(Point3D p, Vector normal) {
		this._p = p;
		this._normal = normal.normalized();

	}

	//because polygon
	public Vector getNormal() {
		return _normal;
	}
	/**
	 * getter of point
	 * @return Point3D' _p 
	 */
	public Point3D get_p() {
		return _p;
	}
	

	@Override
	public Vector getNormal(Point3D point) {
		return _normal;
	}
	@Override
	public List<Point3D> findIntersections(Ray ray) {
		 Point3D p0 = ray.get_POO();
	        Vector v = ray.get_direction();
	        double vn = alignZero(this._normal.dotProduct(v));
	        // if the ray is ray in parallel to the plane (orthogonal to the normal) return
	        // null
	        if (vn == 0)
	            return null;
	        Vector pp0 = null;
	        try {
	            pp0 = this._p.subtract(p0);
	        } catch (Exception e) {
	            // if the ray's base is equal to the origin point of the plane return null
	            return null;
	        }
	        double t = alignZero(this._normal.dotProduct(pp0) / vn);
	        // if the plane is behind the ray, or the ray's base is on the plane return null
	        return (t <= 0) ? null : Arrays.asList(new Point3D( p0.add(v.scale(t)))); // return the intersection
	 
	}

	
	
}
