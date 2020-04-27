/**
 * 
 */
package geometries;

/**
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

    public Geometries(Intersectable... _geometries) {
        add(_geometries);
    }

    public void add(Intersectable... geometries) {

        _geometries.addAll(Arrays.asList(geometries));
    }

    /**
     * Na le Hasbir befrotrot
     *
     * @param ray the ray that intersect the geometries
     * @return list of Point3D that intersect the osef
     */
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

    public void remove(Intersectable... intersectables) {
        for (Intersectable geo : _geometries) {
            _geometries.remove(geo);
        }
    }
}
