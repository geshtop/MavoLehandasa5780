package geometries;

import java.util.List;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {


	 protected final Ray _axisRay;

		/**
		 * constructs a tube from axis and radius
		 *
		 * @param axis,   the axis of the tube
		 * @param radius, the radius of the tube
		 */
		public Tube(Ray axis, double radius) {
			super(radius);
			_axisRay = axis;
		}

		 /**
	     * Gets the axis of the tube
	     *
	     * @return Ray axis 
	     */
		public Ray get_axisRay() {
			return _axisRay;
		}
		
	@Override
	public Vector getNormal(Point3D point) {
		Vector v = _axisRay.get_direction();
		Point3D P0 = _axisRay.get_POO();
	    double t= v.dotProduct(point.subtract(P0));
	    Point3D O =  P0.add(v.scale(t));
	    
	    Vector v1 = new Vector( point.subtract(O));
		return v1.normalized();
		
	}



	@Override
	public List<GeoPoint> findIntersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
