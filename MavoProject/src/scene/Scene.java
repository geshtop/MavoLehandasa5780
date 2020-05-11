package scene;

import elements.*;
import primitives.*;
import geometries.*;
public class Scene {

	    String _name;
	    Color _background;
	    AmbientLight _ambientLight;
	    Geometries _geometries;
	    Camera _camera;
	    double _distance;
	    
	    /**
	     * creates an empty scene with a name
	     *
	     * @param name
	     */
	    public Scene(String name) {
	    	_name = name;
	    }
	    // ***
	    
	    /**
	     * getter of the scene name
	     *
	     * @return sceneName
	     */
	    public String getSceneName() {
	        return _name;
	    }

	    /**
	     * getter of the background color
	     *
	     * @return background
	     */
	    public Color getBackground() {
	        return _background;
	    }

	    /**
	     * setter of the background color
	     *
	     * @param background
	     */
	    public void setBackground(Color background) {
	        this._background = background;
	    }

	    /**
	     * getter of the ambient light
	     *
	     * @return ambient
	     */
	    public AmbientLight getAmbient() {
	        return _ambientLight;
	    }

	    /**
	     * setter of the ambient light
	     *
	     * @param ambient
	     */
	    public void setAmbient(AmbientLight ambient) {
	        this._ambientLight = ambient;
	    }

	    /**
	     * getter of geometries list
	     *
	     * @return geometries
	     */
	    public Geometries getGeometries() {
	        return _geometries;
	    }

	    /**
	     * setter of the 3D model with the geometries list
	     *
	     * @param geometries
	     */
	    public void setGeometries(Geometries geometries) {
	        this._geometries = geometries;
	    }




	    /**
	     * getter of camera
	     *
	     * @return camera
	     */
	    public Camera getCamera() {
	        return _camera;
	    }

	    /**
	     * setter of camera
	     *
	     * @param camera
	     */
	    public void setCamera(Camera camera) {
	        this._camera = camera;
	    }

	    /**
	     * getter of distance
	     *
	     * @return distance
	     */
	    public double getDistance() {
	        return _distance;
	    }

	    /**
	     * setter of distance
	     *
	     * @param distance
	     */
	    public void setDistance(double distance) {
	        this._distance = distance;
	    }
	    // ***************** Operations ******************** //

	   
	    /**
	     * adds shapes to the 3D model
	     *
	     * @param shapes
	     */
	    public void addIntersectable(Intersectable... shapes) {
	    	if (_geometries == null)
	    		_geometries = new Geometries();
	    	_geometries.add(shapes);
	    }
}
