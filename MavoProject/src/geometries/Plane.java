package geometries;

import static primitives.Util.alignZero;


import java.util.Arrays;
import java.util.List;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Plane extends Geometry {
	private Point3D _p;
	private Vector _normal;
	
	
	  // ***************** Constructors ********************** //

    /**
     * Constructs a plane with a point and normal vector
     *
     * @param _p,      the base point of the normal
     * @param _normal, the normal vector to the plane
     */
    public Plane(Point3D p, Vector _normal) {
    	this._p = p;
		this._normal = _normal.normalized();
    }

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

    /**
     * Constructs a plane with a point, normal vector and a color
     *
     * @param emission the color of the plane
     * @param _p,      the base point of the normal
     * @param _normal, the normal vector to the plane
     */
    public Plane(Color emission, Point3D _p, Vector _normal) {
        this(_p, _normal);
        this._emission = emission;
    }

    /**
     * Constructs a plane using three points in the space and a color
     *
     * @param emission the color of the plane
     * @param p1       is first point
     * @param p2       is second point
     * @param p3       is third point
     */
    public Plane(Color emission, Point3D p1, Point3D p2, Point3D p3) {
        this(p1, p2, p3);
        this._emission = emission;
    }

    /**
     * Constructs a plane with a point, normal vector and a color
     *
     * @param emission the color of the plane
     * @param material the material of the plane
     * @param _p,      the base point of the normal
     * @param _normal, the normal vector to the plane
     */
    public Plane(Color emission, Material material, Point3D _p, Vector _normal) {
        this(emission, _p, _normal);
        this.material = material;
    }

    /**
     * Constructs a plane using three points in the space and a color
     *
     * @param emission the color of the plane
     * @param material the material of the plane
     * @param p1       is first point
     * @param p2       is second point
     * @param p3       is third point
     */
    public Plane(Color emission, Material material, Point3D p1, Point3D p2, Point3D p3) {
        this(emission, p1, p2, p3);
        this.material = material;
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
	public List<GeoPoint> findIntersections(Ray ray) {
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
	        return (t <= 0) ? null : Arrays.asList(new GeoPoint(this, p0.add(v.scale(t)))); // return the intersection
	 
	}

	@Override
	public BoundaryVolume boundaryVolume() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
