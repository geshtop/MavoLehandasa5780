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

	final int samples =15;


	/**
	 * integration tests for reflection and refraction
	 */
	@Test
	public void testReflectionRefracted() {
		Scene scene = new Scene("reflection test");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));

		scene.setDistance(1000);
		scene.setAmbient(new AmbientLight(new Color(255, 255, 255), 0));
		scene.setBackground(Color.BLACK);
		Plane plane = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0,0.8), new Point3D(0, 50, 60),
				new Vector(0, 1, 0));
		Sphere sphere = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.5, 0.2), 20,
				new Point3D(0, 30, 80));
//		Sphere sphere2 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300, 0, 0.2), 9,
//				new Point3D(0, 30, 80));
		Sphere sphere3 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300, 0, 0.2), 10,
				new Point3D(0, 0, 80));
		Sphere sphere4 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0, 0.2), 5,
				new Point3D(0, -15, 80));
		scene.addIntersectable(sphere3, sphere4,/*sphere2,*/ plane, sphere);

		Point3D pos = new Point3D(20, -50, 0);
		Color color = new Color(400, 300, 300);

		PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);

		ImageWriter imageWriter = new ImageWriter("spheres building (" + samples +")", 500, 500, 1200, 1200);
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
	 * integration tests for reflection and refraction
	 */
	@Test
	public void testReflectionRefracted2() {
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
		
		ImageWriter imageWriter = new ImageWriter("dubi building 2 (" + samples +")", 500, 400, 1200, 1200);
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
		Plane plane = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0, 0.4, 0.05, 0), //
				new Point3D(0, 20, 100), new Vector(0, 1, 0)); // ground
		Plane plane2 = new Plane(new Color(0, 30, 30), new Material(0.5, 0.5, 1200), //
				new Point3D(0, 20, 600), new Vector(0, 0, 1)); // background plane

		Sphere sphere = new Sphere(new Color(255, 0, 20), new Material(0.5, 0.5, 1200, 0, 0.5), //
				20, new Point3D(75, -3, 140)); // sphere 1
		Sphere sphere2 = new Sphere(new Color(255, 0, 20), new Material(0.5, 0.5, 1200,  0, 0.5), //
				20, new Point3D(25, -18, 140)); // sphere 2
		Sphere sphere3 = new Sphere(new Color(255, 0, 20), new Material(0.5, 0.5, 1200,  0, 0.5), //
				20, new Point3D(-75, -33, 140)); // sphere 3
		Sphere sphere4 = new Sphere(new Color(255, 0, 20), new Material(0.5, 0.5, 1200,  0, 0.5), //
				20, new Point3D(-25, -48, 140)); // sphere 4

		scene.addIntersectable(plane, plane2 //
				, sphere, sphere2, sphere3, sphere4);

		Point3D pos = new Point3D(0, -30, 30);
		Color color = new Color(400, 300, 300);

		PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);

		ImageWriter imageWriter = new ImageWriter("Glossy test (" + samples +")", 200, 150, 600, 450);
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
		double shpere_radius = 20;
		Sphere sphere = new Sphere(shpere_color,shpere_mat,	shpere_radius, new Point3D(75, -3, 140)); // sphere 1
		Sphere sphere2 = new Sphere(shpere_color, shpere_mat, shpere_radius, new Point3D(25, -3, 140)); // sphere 2
		Sphere sphere3 = new Sphere(shpere_color, shpere_mat, shpere_radius, new Point3D(-75, -3, 140)); // sphere 3
		Sphere sphere4 = new Sphere(shpere_color, shpere_mat, shpere_radius, new Point3D(-25, -3, 140)); // sphere 4
		// window for sphere 1
		Polygon square1 = new Polygon(new Color(0, 0, 0), new Material(0.1, 0.1, 300, 0.8, 0, 0, 0.1), //
				new Point3D(75, 20, 100), new Point3D(36, 20, 100), new Point3D(36, -40, 100),
				new Point3D(75, -40, 100));
		// window for sphere 2
		Polygon square2 = new Polygon(new Color(0, 0, 0), new Material(0.1, 0.1, 300, 0.8, 0, 0, 0.5), //
				new Point3D(1, 20, 100), new Point3D(34, 20, 100), new Point3D(34, -40, 100), new Point3D(1, -40, 100));
		// window for sphere 3
		Polygon square3 = new Polygon(new Color(0, 0, 0), new Material(0.1, 0.1, 300, 0.8, 0, 0, 1), //
				new Point3D(-75, 20, 100), new Point3D(-36, 20, 100), new Point3D(-36, -40, 100),
				new Point3D(-75, -40, 100));
		// window for sphere 4
		Polygon square4 = new Polygon(new Color(0, 0, 0), new Material(0.1, 0.1, 300, 0.8, 0, 0, 2), //
				new Point3D(-1, 20, 100), new Point3D(-34, 20, 100), new Point3D(-34, -40, 100),
				new Point3D(-1, -40, 100));
		scene.addIntersectable(plane, plane2, //
				sphere, sphere2, sphere3, sphere4 //
				, square1, square2, square3, square4 //
		);

		Point3D pos = new Point3D(0, -30, 30);
		Color color = new Color(400, 300, 300);

		PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);

		ImageWriter imageWriter = new ImageWriter("Matte test (" + samples + ")", 200, 150, 600, 450);
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
