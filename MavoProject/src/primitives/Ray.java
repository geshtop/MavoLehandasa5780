package primitives;

public class Ray {
 private	Point3D _POO;
 //ctor
 public Ray(Point3D POO, Vector direction) {
		_POO = POO;
		_direction = direction;
	}
 
 //getter setter
private	Vector _direction;
public Point3D get_POO() {
	return _POO;
}
public Vector get_direction() {
	return _direction;
}
@Override
public boolean equals(Object obj) {
   if (this == obj) return true;
   if (obj == null) return false;
   if (!(obj instanceof Ray)) return false;
   Ray oth = (Ray)obj;
   return _POO.equals(oth._POO) && _direction.equals(oth._direction);
}


@Override
 public String toString() {
     return "" + _POO.toString() + _direction.toString();
 }
	
}
