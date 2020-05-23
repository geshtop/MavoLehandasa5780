/**
 * 
 */
package geometries;

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
}
