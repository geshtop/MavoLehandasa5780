package unittests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.LightSource;
import elements.PointLight;
import elements.SpotLight;
import geometries.Geometries;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class MinitProject1Test {

	final int samples =1;


	
	/**
	 * integration tests for reflection and refraction
	 */
	@Test
	public void testReflectionRefractedDubi() {
		Scene scene = new Scene("reflection test");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		

		scene.setDistance(1000);
		scene.setAmbient(new AmbientLight(new Color(255, 255, 255), 0));
		scene.setBackground(Color.BLACK);
		//משטח עם מקדם KR של מראה
		Plane plane = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0,0.8), new Point3D(0, 50, 60),
				new Vector(0, 1, 0));
		
		Sphere sphere = new Sphere(new Color(88, 55, 227), new Material(0.5, 0.5, 300, 0, 0.2), 40,
				new Point3D(0, 10, 80));
		//head 
		Sphere sphere2 = new Sphere(new Color(88, 55, 227), new Material(0.5, 0.5, 300, 0.4, 0.4), 20,
				new Point3D(0, -45, 80));
		//right eye
		Sphere sphere3 = new Sphere(new Color(88, 55, 0), new Material(0.5, 0.5, 300, 0, 0), 4,
				new Point3D(10, -50, 55));
		Sphere sphere3_1 = new Sphere(new Color(0,0,0), new Material(0, 0, 0, 0, 0), 1,
				new Point3D(10, -50, 50));
		//left eye
		Sphere sphere4 = new Sphere(new Color(88, 55, 0), new Material(0.5, 0.5, 300, 0, 0), 4,
				new Point3D(-10, -50, 55));
		Sphere sphere4_1 = new Sphere(new Color(0,0,0), new Material(0, 0, 0, 0, 0), 1,
				new Point3D(-10, -50, 50));
//		Sphere sphere3 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300, 0, 0.2), 10,
//				new Point3D(0, 0, 80));
//		Sphere sphere4 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0, 0.2), 5,
//				new Point3D(0, -15, 80));
		//right hand
		Sphere sphere_5 = new Sphere(new Color(88, 55, 227), new Material(0.5, 0.5, 300, 0, 0), //
				20, new Point3D(45, -10, 200)); 
		//right hand
		Sphere sphere_6 = new Sphere(new Color(88, 55, 227), new Material(0.5, 0.5, 300, 0, 0), //
				20, new Point3D(-45, -10, 200)); 
		
		//right ear
		Sphere sphere7 = new Sphere(new Color(88, 55, 227), new Material(0.5, 0.5, 300, 0.4, 0.4), 8,
				new Point3D(12, -65, 100));
		
		
		Plane plane2 = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0,0.8), new Point3D(0, 160, 80),
				new Vector(1, 0.5, 0));
		
		//left ear
		Sphere sphere8 = new Sphere(new Color(88, 55, 227), new Material(0.5, 0.5, 300, 0.4, 0.4), 8,
				new Point3D(-12, -65, 100));
		scene.addIntersectable(sphere4, sphere4_1, sphere3, sphere3_1,sphere2, plane, sphere, sphere_5, sphere_6, sphere7, sphere8);

		//Point3D pos = new Point3D(20, -50, 0);
		Point3D pos = new Point3D(0, -30, 10);

		Color color = new Color(400, 300, 300);

		PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);


		// 200, 150, 600, 450
		
		ImageWriter imageWriter = new ImageWriter("dubi building  (" + samples +")", 500, 400, 1200, 1200);
		List<LightSource> lights = new ArrayList<LightSource>();
		lights.add(point_light);

		scene.setLights(lights);
		Render render = new Render(imageWriter, scene)
				.setMultithreading(3) //
				.setDebugPrint()
				.setSampleCount(samples);

		render.renderImage();
		render.writeToImage();
	}




	/**
	 * test case for matte and glossy surfaces
	 */
	@Test
	public void testGlossySurface() {
		Scene scene = new Scene("Glossy test");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setAmbient(new AmbientLight(new Color(255, 255, 255), 0));
		scene.setBackground(Color.BLACK);
		//kt is 0.05
		Plane plane = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0, 0.4, 0.05, 0), //
				new Point3D(0, 20, 100), new Vector(0, 1, 0)); // ground
		Plane plane2 = new Plane(new Color(0, 30, 30), new Material(0.5, 0.5, 1200), //
				new Point3D(0, 20, 600), new Vector(0, 0, 1)); // background plane

		Sphere sphere = new Sphere(new Color(255, 0, 0), new Material(0.5, 0.5, 1200, 0, 0.4), //
				20, new Point3D(75, -80, 140)); // sphere 1
		Sphere sphere4 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 1200,  0, 0.4), //
				20, new Point3D(-25, -48, 140)); // sphere 4
		Sphere sphere2 = new Sphere(new Color(0, 255, 0), new Material(0.5, 0.5, 1200,  0, 0.4), //
				20, new Point3D(25, -18, 140)); // sphere 2
		Sphere sphere3 = new Sphere(new Color(0, 0, 255), new Material(0.5, 0.5, 1200,  0, 0.4), //
				20, new Point3D(-75, -33, 140)); // blue
	

		

		scene.addIntersectable(plane, plane2  //
				, sphere, sphere2, sphere3, sphere4 );

		Point3D pos = new Point3D(0, -30, 30);

		//Point3D pos = new Point3D(0, 0, 20);
		Color color = new Color(400, 300, 300);

		PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
		//ImageWriter imageWriter = new ImageWriter("Glossy test (" + samples +")",200, 150, 600, 450);
		
		ImageWriter imageWriter = new ImageWriter("Glossy test (" + samples +")",  200, 250, 600, 500);


		List<LightSource> lights = new ArrayList<LightSource>();

		lights.add(point_light);
		scene.setLights(lights);
		Render render = new Render(imageWriter, scene)
				.setMultithreading(3) //
				.setDebugPrint()
				.setSampleCount(samples);
		render.renderImage();
		render.writeToImage();

	}
	
	
	/**
	 * test case for object behind light
	 * chagne kt with kr and camera replace the vectors vup vright
	 */


	/**
	 * test case for matte and glossy surfaces
	 */
	@Test
	public void testMatteGlass() {
		Scene scene = new Scene("Matte test");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));

		scene.setDistance(1000);
		scene.setAmbient(new AmbientLight(new Color(255, 255, 255), 0));
		scene.setBackground(Color.BLACK);

		Plane plane = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0, 0.4), new Point3D(0, 20, 100),
				new Vector(0, 1, 0)); // ground Plane plane2
		Plane plane2 = new Plane(new Color(0, 30, 30), new Material(0.5, 0.5, 1200), //
				new Point3D(0, 20, 600), new Vector(0, 0, 1)); // background plane
		Material shpere_mat =  new Material(0.5, 0.5, 1200, 0, 0.5);
		Color shpere_color =new Color(0, 0, 20);
		double shpere_radius = 23;
		Sphere sphere = new Sphere(new Color(255, 0, 0),shpere_mat,	shpere_radius, new Point3D(75, -3, 140)); // sphere 1
		Sphere sphere2 = new Sphere(new Color(0, 0, 20), shpere_mat, shpere_radius, new Point3D(25, -3, 140)); // sphere 2
		Sphere sphere3 = new Sphere(new Color(0, 255, 0), shpere_mat, shpere_radius, new Point3D(-75, -3, 140)); // sphere 3
		Sphere sphere4 = new Sphere(new Color(0, 0, 255), shpere_mat, shpere_radius, new Point3D(-25, -3, 140)); // sphere 4
		
		Sphere spherex1 = new Sphere(new Color(255, 0, 0),shpere_mat,	10, new Point3D(75, -30, 140)); // sphere 1
		Sphere spherex2 = new Sphere(new Color(0, 0, 20), shpere_mat, 10, new Point3D(25, -30, 140)); // sphere 2
		Sphere spherex3 = new Sphere(new Color(0, 255, 0), shpere_mat, 10, new Point3D(-75,-30, 140)); // sphere 3
		Sphere spherex4 = new Sphere(new Color(0, 0, 255), shpere_mat, 10, new Point3D(-25, -30, 140)); // sphere 4
		
		Sphere spherex11 = new Sphere(new Color(255, 0, 0),shpere_mat,	4, new Point3D(75, -40, 140)); // sphere 1
		Sphere spherex22 = new Sphere(new Color(0, 0, 20), shpere_mat, 4, new Point3D(25, -40, 140)); // sphere 2
		Sphere spherex33 = new Sphere(new Color(0, 255, 0), shpere_mat, 4, new Point3D(-75,-40, 140)); // sphere 3
		Sphere spherex44 = new Sphere(new Color(0, 0, 255), shpere_mat, 4, new Point3D(-25, -40, 140)); // sphere 4
		
		// window for sphere 1
		//ther radius of the first window is 0.1
		Polygon square1 = new Polygon(new Color(0, 0, 0), new Material(0.1, 0.1, 300, 0.8, 0, 0, 0.1), //
				new Point3D(105, 20, 100), new Point3D(60, 20, 100), new Point3D(60, -50, 100),
				new Point3D(105, -50, 100));
		// window for sphere 2
		Polygon square2 = new Polygon(new Color(0, 0, 0), new Material(0.1, 0.1, 300, 0.8, 0, 0, 0.4), //
				new Point3D(5, 20, 100), new Point3D(50, 20, 100), new Point3D(50, -50, 100), new Point3D(5, -50, 100));
		// window for sphere 3
		Polygon square3 = new Polygon(new Color(0, 0, 0), new Material(0.1, 0.1, 300, 0.8, 0, 0, 0.7), //
				new Point3D(-105, 20, 100), new Point3D(-60, 20, 100), new Point3D(-60, -50, 100),
				new Point3D(-105, -50, 100));
		// window for sphere 4
		Polygon square4 = new Polygon(new Color(0, 0, 0), new Material(0.1, 0.1, 300, 0.8, 0, 0, 1), //
				new Point3D(-5, 20, 100), new Point3D(-50, 20, 100), new Point3D(-50, -50, 100),
				new Point3D(-5, -50, 100));
		
		
		
		scene.addIntersectable(plane, plane2, //
				sphere, sphere2, sphere3, sphere4, spherex1, spherex2, spherex3, spherex4, spherex11, spherex22, spherex33, spherex44 //
				, square1, square2, square3, square4 //
		);

		//Point3D pos = new Point3D(0, -30, 30);
		Point3D pos = new Point3D(20, -50, 20);

		Color color = new Color(400, 300, 300);

		PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);

		ImageWriter imageWriter = new ImageWriter("Matte test (" + samples + ")", 150, 200, 600, 450);
		List<LightSource> lights = new ArrayList<LightSource>();
		lights.add(point_light);
		scene.setLights(lights);
		Render render = new Render(imageWriter, scene)
				.setMultithreading(3) //
				.setDebugPrint()
				.setSampleCount(samples);
		render.renderImage();
		render.writeToImage();
	}


}
