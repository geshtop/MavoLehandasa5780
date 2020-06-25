/**
 * 
 */
package geometries;

import primitives.Point3D;

/**
 * class represents a list 3D space shapes which could intersect a ray.
 * @author Owner
 *
 */

import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Geometries implements Intersectable {

    private List<Intersectable> _geometries = new ArrayList<>();
    /**
	 * Default constructor constructs the list as an empty ArrayList
	 */
    public BoundaryVolume currentBv;
	public Geometries() {
		_geometries = new ArrayList<Intersectable>();
	}
	

    /**
	 * constructs a list with geometries (one or more)
	 *
	 * @param geometries - list of geometry shapes.
	 */
    public Geometries(Intersectable... _geometries) {
        add(_geometries);
    }
    // ***************** Operations ******************** //
    /**
	 * adds geometries to the list
	 *
	 * @param geometries
	 */
    public void add(Intersectable... geometries) {

        _geometries.addAll(Arrays.asList(geometries));
    }



    /**
	 * remove geometries from the list
	 *
	 * @param geometries
	 */
    public void remove(Intersectable... intersectables) {
        for (Intersectable geo : _geometries) {
            _geometries.remove(geo);
        }
    }
	/**
	 * Gets the amount of shapes
	 * @return amount of shapes in geometries
	 */
	public int getAmount() {
		return _geometries.size();
	}
    

    
	/**
	 * getter of shapes
	 * @return geometries' _geometries 
	 */
	public List<Intersectable> getShapes() {
		// TODO Auto-generated method stub
		return _geometries;
	}

    
	/**
	 * finds intersections of the ray with the geometries that are in the list
	 *
	 * @param ray in space.
	 * @return intersection points
	 */
	public List<GeoPoint> findIntersections(Ray ray) {
		List<GeoPoint> intersections = new ArrayList<>();
		for (Intersectable shape : _geometries) {
			List<GeoPoint> ints = shape.findIntersections(ray);
			if (ints != null)
				intersections.addAll(ints);
		}
		// if no intersections were found, return null
		if (intersections.size() == 0)
			return null;
		return intersections;
	}
	
	
	/**
	 * calculate the boundaries of the geometries
	 * @return boundary of geometries
	 */
	public BoundaryVolume boundaryVolume()
    {
		if(this.currentBv != null) return this.currentBv;
    	BoundaryVolume boundary ;
    	double minX = Double.POSITIVE_INFINITY,minY = Double.POSITIVE_INFINITY,minZ = Double.POSITIVE_INFINITY;
    	double maxX = Double.NEGATIVE_INFINITY ,maxY = Double.NEGATIVE_INFINITY ,maxZ = Double.NEGATIVE_INFINITY;
    	double x=0 ,y=0 ,z =  0;
    	
    	for(Intersectable shape : _geometries)
    	{
    		if((shape instanceof Plane)){
    			minX = Double.NEGATIVE_INFINITY;
    			minY=Double.NEGATIVE_INFINITY;
    			minZ=Double.NEGATIVE_INFINITY;
    			maxX = Double.POSITIVE_INFINITY;
    			maxY=Double.POSITIVE_INFINITY;
    			maxZ=Double.POSITIVE_INFINITY;
    			 
    			 
    		}
    		else {
    		boundary = shape.boundaryVolume();
    		x = boundary.min.get_x().get();
    		y = boundary.min.get_y().get();
    		z = boundary.min.get_z().get();
    		if(x<minX)minX=x;  if(y<minY)
    	    minY=y; if(z<minZ) minZ=z;
    	    x = boundary.max.get_x().get();
    		y = boundary.max.get_y().get();
    		z = boundary.max.get_z().get();
    		if(x>maxX) maxX=x;if(y>maxY)
        	maxY=y;if(z>maxZ) maxZ=z;
    		}
    		
    	}
    	this.currentBv = new BoundaryVolume(new Point3D(minX,minY,minZ),//
        		new Point3D(maxX,maxY,maxZ));
        return this.currentBv;
    	

}
}
