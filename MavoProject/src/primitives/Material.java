package primitives;

//��� ����� 
public class Material {
	double _kD;
	double _kS;
	int _nShininess;
	double _kR;
	double _kT;
	
	// ***************** Constructors ********************** //

		/**
		 * creates a material for a geometry
		 *
		 * @param kd        - diffusion attenuation coefficient
		 * @param ks        - specular attenuation coefficient
		 * @param shininess - shininess power
		 */
		public Material(double kd, double ks, int shininess) {

			this(kd, ks, shininess, 0,0);
		}
		
		/**
		 * creates a material for a geometry
		 *
		 * @param kd        - diffusion attenuation coefficient
		 * @param ks        - specular attenuation coefficient
		 * @param shininess - shininess power
		 * @param kr        - reflection coefficient
		 * @param kt        - transparency coefficient
		 */
		public Material(double kd, double ks, int shininess, double kt, double kr) {
			_kD = kd;
			_kS = ks;
			_nShininess = shininess;
			_kR = kr;
			_kT = kt;
		}

		/**
		 * getter of diffusion attenuation coefficient
		 *
		 * @return diffusion attenuation coefficient
		 */
		public double getKd() {
			return _kD;
		}

		/**
		 * getter of specular attenuation coefficient
		 *
		 * @return specular attenuation coefficient
		 */
		public double getKs() {
			return _kS;
		}

		/**
		 * getter of shininess power
		 *
		 * @return shininess power
		 */
		public int getShininess() {
			return _nShininess;
		}

		

		/**
		 * getter of reflection coefficient
		 *
		 * @return reflection coefficient
		 */
		public double getKr() {
			return _kR;
		}

		/**
		 * getter of transparency coefficient
		 *
		 * @return transparency coefficient
		 */
		public double getKt() {
			return _kT;
		}

}