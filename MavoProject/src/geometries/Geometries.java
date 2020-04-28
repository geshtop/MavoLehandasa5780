/**
 * 
 */
package geometries;

/**
 * class represents a list 3D space shapes which could intersect a ray.
 * @author Owner
 *
 */

import primitives.Point3D;
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
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = null;

        for (Intersectable geo : _geometries) {
            List<Point3D> tempIntersections = geo.findIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new ArrayList<>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;

    }
}
