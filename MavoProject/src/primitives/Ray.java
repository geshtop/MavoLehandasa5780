package primitives;

import static primitives.Util.isZero;

public class Ray {

 private	Point3D _POO;
 private	Vector _direction;
 // ***************** Constructors ********************** //
 /*
  * 
  * Construct a ray with a initial point and direction vector.
  * 
  * @param POO initial point
  * @param vec of direction .
  */
 public Ray(Point3D POO, Vector direction) {
		_POO =  new Point3D(POO);
		_direction = new Vector(direction).normalized();
	}


 /**
  * Getter of initial point
  *
  * @return initial point
  */
public Point3D get_POO() {
	 return new Point3D(_POO);
}
/**
 * Getter of direction vector
 *
 * @return direction vector
 */
public Vector get_direction() {
	return new Vector(_direction);
}




/**
 * @param length
 * @return new Point3D
 */
public Point3D getPoint(double length) {
       return isZero(length ) ? _POO : _POO.add(_direction.scale(length));
}


@Override
public boolean equals(Object obj) {
    if (obj == null || !(obj instanceof Ray))
        return false;
    if (this == obj)
        return true;
    Ray other = (Ray)obj;
    return (_POO.equals(other._POO) &&
            _direction.equals(other._direction));
}


@Override
 public String toString() {
	return String.format ("point: " + _POO + ", direction: " + _direction);
 }
	
}
