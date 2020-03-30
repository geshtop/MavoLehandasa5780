package primitives;

import static primitives.Util.isZero;

public class Vector {
	//private
	Point3D _head;
	
	public static Vector ZERO = new Vector(0,0,0);
	
	//ctor
	Vector(Point3D head){
		if(head== Point3D.ZERO)
			throw new  IllegalArgumentException("The vector cannot be zero");
		this._head = head;
		
		
	}
	
	
	
	public Vector(int x, int y, int z) {
		Point3D p = new Point3D(x,y,z);
		if(p== Point3D.ZERO)
			throw new  IllegalArgumentException("The vector cannot be zero");
		_head =p;
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
		return null;
		}

	public Vector	add (Vector other) {
		return null;
		}
	public Vector	scale (int scalar) {
		return null;
	}
	
	public int 	dotProduct (Vector other) {
		int product = 0;
	      // product = product +_head.get_x()._coord *other._head.get_x().get(); 
		return 0;
	}
	
	public Vector 	crossProduct (Vector other) {
		return null;
	}
	
	
	public int 	lengthSquared () {
		return 0;
	}
	public int 	length  () {
		return 0;
	}
	
	public Vector 	normalize () {
		
		return this;
	}
	
	public Vector 	normalized () {
		return null;
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
