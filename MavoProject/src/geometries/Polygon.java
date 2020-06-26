package geometries;

import java.util.Arrays;
import java.util.List;
import primitives.*;
import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * 
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point3D> _vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane _plane;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     * 
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex></li>
     *                                  </ul>
     */
    public Polygon(Point3D... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        _vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        _plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3) return; // no need for more tests for a Triangle

        Vector n = _plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (int i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }

    
    
    /**
     * Constructs a polygon from set of points - polygon's vertices and a color. NB: the points
     * must be in the same plane
     *
     * @param emission the color of the polygon
     * @param points   vertices
     * @throws IllegalArgumentException if less than 3 points or points are not in
     *                                  the same plane
     */
    public Polygon(Color emission, Point3D... points) {
        this(points);
        this._emission = emission;
       
    }
    
  

    /**
     * Constructs a polygon from set of points - polygon's vertices and a color. NB: the points
     * must be in the same plane
     *
     * @param emission the color of the polygon
     * @param material the material of the polygon
     * @param points   vertices
     * @throws IllegalArgumentException if less than 3 points or points are not in
     *                                  the same plane
     */
    public Polygon(Color emission, Material material, Point3D... points) {
        this(emission, points);
        this.material = material;
    }
    
    @Override
    public Vector getNormal(Point3D point) {
        return _plane.getNormal(point);
    }

	@Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = this._plane.findIntersections(ray);
        if (intersections == null) // if there are no intersections with the plane, or the ray's
            // base is on the triangle return null
            return null;
        Point3D p0 = ray.get_POO();
        int size = this._vertices.size();
        Vector[] v = new Vector[size];
        Vector[] n = new Vector[size];
        double[] un = new double[size];
        // vi = pi - p0
        for (int i = 0; i < size; ++i)
            v[i] = _vertices.get(i).subtract(p0);
        // Ni = Vi x Vi+1
        for (int i = 0; i < size; ++i)
            n[i] = v[i].crossProduct(v[(i < size - 1) ? i + 1 : 0]).normalize();
        Vector u = intersections.get(0).getPoint().subtract(p0);
        // uni = u*Ni
        for (int i = 0; i < size; ++i)
            if ((un[i] = alignZero(u.dotProduct(n[i]))) == 0)
                return null;
        double sign = un[0];
        for (int i = 1; i < size; ++i)
            // if the N1...Nn do not have the same sign, return null
            if ((sign < 0 && un[i] > 0) || (sign > 0 && un[i] < 0))
                return null;
        Point3D intersection = intersections.get(0).point;
        return Arrays.asList(new GeoPoint(this, intersection));
    }
	
	public BoundaryVolume boundaryVolume() {
		if(this.currentBv!= null ) return this.currentBv;
    	double minX= _vertices.get(0).get_x().get();
    	double maxX = minX;
    	double minY= _vertices.get(0).get_y().get();
    	double maxY = minY;
    	double minZ= _vertices.get(0).get_z().get();
    	double maxZ = minZ;
    	
    	double x = 0;
    	double y=0;
    	double z =0;
    	for(Point3D p : _vertices)
    	{
    		x=p.get_x().get();
    		y=p.get_y().get();
    		z=p.get_z().get();
    		if(x<minX)
    			minX=x;
    		if(x>maxX)
    			maxX=x;
    		if(y<minY)
    			minY=y;
    		if(y>maxY)
    			maxY=y;
    		if(z<minZ)
    			minZ=z;
    		if(z>maxZ)
    			maxZ=z;
    	}
    	
    	this.currentBv = new BoundaryVolume(new Point3D(minX,minY,minZ)//
    			,new Point3D(maxX,maxY,maxZ));
    	return this.currentBv;
    }
}
