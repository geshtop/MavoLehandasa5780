package primitives;

public class Vector {
	//private
	Point3D _head;
	
	public static Vector ZERO = new Vector(0,0,0);
	
	//ctor
	/**
	 * 
	 * @param x its the first coordinate
	 * @param y
	 * @param z
	 */
	
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D p = new Point3D(x,y,z);
		//if(p.equals( Point3D.ZERO))
		//	throw new  IllegalArgumentException("The vector cannot be zero");
		this._head = p;
	}
	public Vector(double x, double y, double z) {
		Point3D p = new Point3D(x,y,z);
		
		if(p.equals(Point3D.ZERO) )
			throw new  IllegalArgumentException("The vector cannot be zero");
		_head =p;
	}
	Vector(Point3D head){
		//if(head.equals(Point3D.ZERO))
		//	throw new  IllegalArgumentException("The vector cannot be zero");
		this._head = head;
	}

	public Vector(Vector v) {
		_head = v._head;
				
	}
	
	//getter setter
	
	public Point3D get_head() {
		return _head;
	}

	
	//methods
	public Vector	subtract (Vector other) {
		return new Vector(this._head.subtract(other._head));
	}

	public Vector	add (Vector other) {
		return new Vector(this._head.add(other));
	}
	public Vector	scale (int scalar) {
		double x = _head.get_x().get() * scalar;
		double y = _head.get_y().get() * scalar;
		double z = _head.get_z().get() * scalar;

		return new Vector(x,y,z);
	}
	
	public double 	dotProduct (Vector other) {
		return this._head.get_x().get() * other._head.get_x().get()
				+this._head.get_y().get() * other._head.get_y().get()
				+this._head.get_z().get() * other._head.get_z().get();
	}
	
	public Vector 	crossProduct (Vector other) {
		double x = this._head.get_y().get()* other._head.get_z().get()  - this._head.get_z().get() *  other._head.get_y().get(); 
		double y  =  this._head.get_z().get() *  other._head.get_x().get() - this._head.get_x().get() *  other._head.get_z().get(); 
		double z=  this._head.get_x().get() *  other._head.get_y().get()  -  this._head.get_y().get() * other._head.get_x().get(); 
		return new Vector(x,y,z);
		
		
	}
	
	
	public double 	lengthSquared () {
		
		return dotProduct(this);
	}
	public double 	length  () {
		return Math.sqrt(lengthSquared());
	}
	
	public Vector 	normalize () {
		double length = length();
		this._head = new Point3D(this._head.get_x().get() / length, this._head.get_y().get() / length, this._head.get_z().get() / length );
		return this;
	}
	
	public Vector 	normalized () {
		normalize();
		return this;
	}
	
	  @Override
	   public boolean equals(Object obj) {
		 
	      if (this == obj) return true;
	      if (obj == null) return false;
	      if (!(obj instanceof Vector)) return false;
	      Vector oth = (Vector)obj;
	      return _head == oth._head;
	   }

	   
	   @Override
	    public String toString() {
	        return "" + _head.toString();
	    }
	
}
