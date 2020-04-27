package geometries;
import static primitives.Util.isZero;
public abstract class RadialGeometry implements Geometry{
    double  _radius;

    /**
     *
     * @param _radius
     */
    public RadialGeometry(double _radius) {
        if (isZero(_radius) || (_radius < 0.0))
            throw new IllegalArgumentException("radius "+ _radius +" is not valid");
        this._radius = _radius;
    }

    public RadialGeometry(RadialGeometry other){
        this._radius= other._radius;
    }
    public double get_radius() {
        return _radius;
    }
}