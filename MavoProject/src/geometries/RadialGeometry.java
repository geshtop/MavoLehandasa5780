package geometries;

public abstract class RadialGeometry  implements Geometry{
	protected double _radius;

	public double get_radius() {
		return _radius;
	}
	
	//ctor
	RadialGeometry(double radius){
		this._radius = radius;
	}

}
