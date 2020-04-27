package primitives;

import static primitives.Util.isZero;

public class Ray {
//**************************************************************************//
//********************************PRIVATE***********************************//
//**************************************************************************//
 private	Point3D _POO;
 private	Vector _direction;
//**************************************************************************//
//***********************************CTOR***********************************//
//**************************************************************************//
 public Ray(Point3D POO, Vector direction) {
		_POO = POO;
		_direction = direction;
	}
 
//**************************************************************************//
//*******************************GETTER SETTER******************************//
//**************************************************************************//

public Point3D get_POO() {
	return _POO;
}

public Vector get_direction() {
	return _direction;
}

/**
 * 
 * @param length
 * @return
 */
public Point3D getPoint(double length) {
       return isZero(length ) ? _POO : _POO.add(_direction.scale(length));
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
