package geometries;


import static primitives.Util.isZero;

import java.util.List;

import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Triangle extends Polygon {
	
    // ***************** Constructor ********************** //

    /**
     * constructs a triangle from three points
     *
     * @param p1, first point
     * @param p2, second point
     * @param p3, third point
     */
	  public Triangle(Point3D p1, Point3D p2, Point3D p3) {
	        super(p1, p2, p3);
	  }

	  public Triangle(Color emission, Point3D p1, Point3D p2, Point3D p3) {
		  super(emission, p1, p2, p3);
	  }

	@Override
	    public String toString() {
	        String result = "";
	        for (Point3D p : _vertices) {
	            result += p.toString();
	        }
	        return result;
	    }

	  


}
