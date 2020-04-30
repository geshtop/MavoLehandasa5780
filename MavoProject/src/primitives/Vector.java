package primitives;

public class Vector {
	
	Point3D _head;
	// ***************** Constructors ********************** //

	/**
	 * Constructs a vector by values of three coordinates of vector's head
	 *
	 * @param x coordinate value
	 * @param y coordinate value
	 * @param z coordinate value
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D p = new Point3D(x,y,z);
		if(p.equals( Point3D.ZERO))
			throw new  IllegalArgumentException("The vector cannot be zero");
		this._head = p;
	}
	
	/**
	 * Constructs a vector with three coordinates.
	 *
	 * @param x coordinate of head point
	 * @param y coordinate of head point
	 * @param z coordinate of head point
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(double x, double y, double z) {
		Point3D p = new Point3D(x,y,z);
		
		if(p.equals(Point3D.ZERO) )
			throw new  IllegalArgumentException("The vector cannot be zero");
		_head =p;
	}
	
	/**
	 * Constructs a vector with head point
	 *
	 * @param head point of the vector
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Point3D head){
		if(head.equals(Point3D.ZERO))
			throw new  IllegalArgumentException("The vector cannot be zero");
		this._head = head;
	}

	/**
	 * Construct head point of vector class with a vector
	 *
	 * @param other vector
	 * @throws newIllegalException when head is (0,0,0)
	 */
	public Vector(Vector v) {
		_head = v._head;
				
	}
	

	/**
	 * Vector from 2 3d point
	 * @param p1 point1
	 * @param p2 point2
	 */
	public Vector(Point3D p1, Point3D p2) {
		 this(p1.subtract(p2));
	}
// ***************** Getters/Setters ********************** //
/**
 * Getter of vector's head
 *
 * @return point of the head
 */
	public Point3D get_head() {
		return _head;
	}

	
	// ***************** Operations ******************** //
	/**
	 * Vector addition operation.
	 *
	 * @param other vector
	 * @return equivalent vector
	 */
	public Vector	add (Vector other) {
		return new Vector(this._head.add(other));
	}
	/**
	 * Vector subtraction operation.
	 *
	 * @param other Vector
	 * @return equivalent vector.
	 */
	public Vector	subtract (Vector other) {
		return new Vector(this._head.subtract(other._head));
	}
	/**
	 * Scale vector by num.
	 *
	 * @param num scale size
	 * @return scaled vector by num
	 */
	public Vector	scale (double scalar) {
		double x = _head.get_x().get() * scalar;
		double y = _head.get_y().get() * scalar;
		double z = _head.get_z().get() * scalar;

		return new Vector(x,y,z);
	}
	/**
	 * The dot-product function takes two 3D space vectors and return a number as
	 * the formula (a,b,c) * (h,y,k) = a*h+b*y+c*k
	 *
	 * @param other Vector
	 * @return Outcome of the formula below.
	 */
	public double 	dotProduct (Vector other) {
		return this._head.get_x().get() * other._head.get_x().get()
				+this._head.get_y().get() * other._head.get_y().get()
				+this._head.get_z().get() * other._head.get_z().get();
	}
	/**
	 * cross product multiplication define as the result of (a,b,c) cross(h,y,f) =
	 * (b*f-c*y,c*h-a*f,a*y-b*h).
	 *
	 * @param other vector .
	 * @return vector orthogonal to each one of the two vectors.
	 */
	public Vector 	crossProduct (Vector other) {
		double x = this._head.get_y().get()* other._head.get_z().get()  - this._head.get_z().get() *  other._head.get_y().get(); 
		double y  =  this._head.get_z().get() *  other._head.get_x().get() - this._head.get_x().get() *  other._head.get_z().get(); 
		double z=  this._head.get_x().get() *  other._head.get_y().get()  -  this._head.get_y().get() * other._head.get_x().get(); 
		return new Vector(x,y,z);
		
		
	}
	
	/**
	 * Calculates the length of vector squared
	 *
	 * @return squared length of vector
	 */
	public double 	lengthSquared () {
		
		return dotProduct(this);
	}
	/**
	 * Length of vector
	 *
	 * @return size of Vector
	 */
	public double 	length  () {
		return Math.sqrt(lengthSquared());
	}
	/**
	 * Makes the vector to be of length 1 keeping the direction
	 *
	 * @return normalized vector itself
	 */
	public Vector 	normalize () {
		double length = length();
		this._head = new Point3D(this._head.get_x().get() / length, this._head.get_y().get() / length, this._head.get_z().get() / length );
		return this;
	}
	/**
	 * Builds new vector of length 1 in the same direction
	 *
	 * @return normalized vector
	 */
	public Vector 	normalized () {
		normalize();
		return new Vector(this);
		//return this;
	}
	
	  @Override
	   public boolean equals(Object obj) {
		 
	      if (this == obj) return true;
	      if (obj == null) return false;
	      if (!(obj instanceof Vector)) return false;
	      Vector oth = (Vector)obj;
	      return _head.equals(oth._head);
	   }

	   
	   @Override
	    public String toString() {
	        return "" + _head.toString();
	    }
	
}
