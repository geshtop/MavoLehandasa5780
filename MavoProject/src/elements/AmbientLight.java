package elements;

import primitives.Color;

public class AmbientLight extends Light  {
    /**
     * sets the ambient light of the picture by color and Attenuation coefficient
     *
     * @param i - Light color
     * @param k - Attenuation coefficient
     */
    public AmbientLight(Color i, double k) {
		super(i.scale(k));
        //_intensity = i.scale(k);
    }
    
    /**
     * getter for the ambient lighting
     *
     * @return light intensity
     */
   // public Color getIntensity() {
   //     return _intensity;
   //}

}
