package primitives;

public class Point3D {
	
	
	private Coordinate _x;
	private Coordinate _y;
	private Coordinate _z;


	public static Point3D ZERO = new Point3D(0,0,0);
	

	
	/**
	 * this ctor get 3 double parameter and create the point
	 * @param x - the first double paramter
	 * @param y - double parameter
	 * @param z - double paramter
	 */
	public Point3D(double x, double y, double z) {
		_x = new Coordinate( x);
		_y = new Coordinate( y);
		_z =new Coordinate( z);
	}
	
	/**
	 * this ctor get 3 coordinate
	 * @param x as coordinate
	 * @param y  as coordinate
	 * @param z  as coordinate
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		_x = x;
		_y =y;
		_z =z;
	}
	
	/**
	 * copy ctor
	 * @param other
	 */
	public Point3D(Point3D other) {
		_x = other._x;
		_y = other._y;
		_z =other._z;
	}
	
    /**
     * Getter of coordinate x of point.
     *
     * @return coordinate x.
     */
	public Coordinate get_x() {
		return _x;
	}

	   /**
     * Getter of coordinate y of point.
     *
     * @return coordinate y.
     */
	public Coordinate get_y() {
		return _y;
	}

    /**
     * Getter of coordinate z of point.
     *
     * @return coordinate z.
     */
	public Coordinate get_z() {
		return _z;
	}
	
    /**
     * 3D point subtraction operation by coordinates .
     *
     * @param other initial 3D space point.
     * @return the vector which ends in the point and begins in other .
     */
	public Vector subtract(Point3D point3d) {
		double x = this._x.get() - point3d._x.get();
		double y = this._y.get() - point3d._y.get();
		double z = this._z.get() - point3d._z.get();

		return new Vector(new Point3D(x,y,z));
	}
    /**
     * 3D point subtraction operation by coordinates .
     *
     * @param other initial 3D space point.
     * @return the vector which ends in the point and begins in other .
     */
	public Point3D subtract(Vector v) {
        return new Point3D(this._x._coord - v._head._x._coord,
                this._y._coord - v._head._y._coord,
                this._z._coord - v._head._z._coord);
    }

	 /**
     * 3D point addition operation by coordinates .
     *
     * @param other Vector
     * @return the head point .
     */
	public Point3D add(Vector vector) {
		double x = this._x.add(vector._head._x);
		double y = this._y.add(vector._head._y);
		double z = this._z.add(vector._head._z);
		return new Point3D(x,y,z);

	}
	
	 /**
     * Calculates the squared distance between the points
     *
     * @param other 3D point.
     * @return the squared distance
     */
	public double 	distanceSquared (Point3D point3d) {
		double x = point3d._x.subtract(this._x);
		double y = point3d._y.subtract(this._y);
		double z = point3d._z.subtract(this._z);
		return (x*x + y*y + z*z);
	}
    /**
     * Calculates the actual distance between points.
     *
     * @param other 3D point.
     * @return the distance
     */
	public double 	distance (Point3D point3d) {
		return Math.sqrt(distanceSquared(point3d));
	}
	


	

	
	   @Override
	   public boolean equals(Object obj) {
		  if (this == obj) return true;
	      if (obj == null) return false;
	      if (!(obj instanceof Point3D)) return false;
	      Point3D oth = (Point3D)obj;
	      return _x.equals(oth._x) &&  _y.equals(oth._y)   && _z.equals(oth._z) ;
	   }

	   
	   @Override
	    public String toString() {
	        return "" + _x.toString() + ", " + _y.toString() + ", " + _z.toString();
	    }

	

	
	

}
