package primitives;

public class Point3D {
	//private
	private Coordinate _x;
	private Coordinate _y;
	private Coordinate _z;


	public static Point3D ZERO = new Point3D(0,0,0);
	
	
	//ctor
	public Point3D(double x, double y, double z) {
		_x = new Coordinate( x);
		_y = new Coordinate( y);
		_z =new Coordinate( z);
	}
	
	public Point3D(Coordinate x, Coordinate y, Coordinate z) {
		_x = x;
		_y =y;
		_z =z;
	}
	
	public Point3D(Point3D other) {
		_x = other._x;
		_y = other._y;
		_z =other._z;
	}
	
	//getter setter
	
	public Coordinate get_x() {
		return _x;
	}

	
	public Coordinate get_y() {
		return _y;
	}

	
	public Coordinate get_z() {
		return _z;
	}
	
	//methods
	public Vector subtract(Point3D point3d) {
		double x = this._x.get() - point3d._x.get();
		double y = this._y.get() - point3d._y.get();
		double z = this._z.get() - point3d._z.get();

		return new Vector(new Point3D(x,y,z));
	}
	
	public Point3D add(Vector vector) {
		double x = this._x.subtract(vector._head._x);
		double y = this._y.subtract(vector._head._y);
		double z = this._z.subtract(vector._head._z);
		return new Point3D(x,y,z);

	}
	
	

	public double 	distanceSquared (Point3D point3d) {
		double x = point3d._x.subtract(this._x);
		double y = point3d._y.subtract(this._y);
		double z = point3d._z.subtract(this._z);
		return (x*x + y*y + z*z);
	}
	public double 	distance (Point3D point3d) {
		return Math.sqrt(distanceSquared(point3d));
	}
	


	

	
	   @Override
	   public boolean equals(Object obj) {
	      if (this == obj) return true;
	      if (obj == null) return false;
	      if (!(obj instanceof Point3D)) return false;
	      Point3D oth = (Point3D)obj;
	      return _x.equals(oth._x) && _y.equals(oth._y)  && _z.equals(oth._z);
	   }

	   
	   @Override
	    public String toString() {
	        return "" + _x.toString() + ", " + _y.toString() + ", " + _z.toString();
	    }

	

	
	

}
