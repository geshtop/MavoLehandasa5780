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
		return new Vector(0,0,0);
	}
	
	public void add(Vector vector) {
		
		this._x.add(vector.get_head()._x);
		this._y.add(vector.get_head()._y);
		this._z.add(vector.get_head()._z);

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
