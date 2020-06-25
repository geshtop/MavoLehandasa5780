/**
 * 
 */
package geometries;
import primitives.*;
import java.util.List;

/**
 * @author Owner
 *
 */
public interface Intersectable {
	/**
	 * finds intersections of the ray with the Geometry
	 *
	 * @param ray
	 * @return intersection point
	 */
	List<GeoPoint> findIntersections(Ray ray);

	BoundaryVolume boundaryVolume();

	
	public static class GeoPoint {
		public Geometry geometry;
		public Point3D point;
		// ***************** Constructors ********************** //

		/**
		 * constructor of a geoPoint
		 *
		 * @param geometry - the geometry
		 * @param point    - the point in the geometry
		 */
		public GeoPoint(Geometry geometry, Point3D point) {
			this.point = point;
			this.geometry = geometry;
		}
		// ***************** Getters/Setters ********************** //

		/**
		 * getter of the point
		 *
		 * @return the point
		 */
		public Point3D getPoint() {
			return point;
		}

		/**
		 * getter of the geometry associated with the point
		 *
		 * @return the geometry
		 */
		public Geometry getGeometry() {
			return geometry;
		}
		

		// ***************** Administration ******************** //
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (!(obj instanceof GeoPoint))
				return false;

			GeoPoint other = (GeoPoint) obj;

			return point.equals(other.point);
		}
	}



}
