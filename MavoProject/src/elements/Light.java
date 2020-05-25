/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author Owner
 *
 */
public abstract class Light {
	protected Color _intensity; 
	
	/**
	 * 
	 * @param intensity as color
	 */
	public Light(Color intensity) {
		this._intensity = intensity;
	}
	/**
     * getter for original intensity of a color
     *
     * @return intensity color
     */
    public Color getIntensity() {
        return _intensity;
    }

}
