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
		_POO =  new Point3D(POO);
		_direction = new Vector(direction).normalized();
	}

//**************************************************************************//
//*******************************GETTER SETTER******************************//
//**************************************************************************//

public Point3D get_POO() {
	 return new Point3D(_POO);
}

public Vector get_direction() {
	return new Vector(_direction);
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
