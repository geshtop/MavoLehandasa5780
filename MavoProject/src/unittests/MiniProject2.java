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

public class MiniProject2 {

	@Test
	public void BVHTest() {
		System.out.println("start test without boundary" + java.time.LocalTime.now());
		Scene scene = new Scene("BVH test");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setAmbient(new AmbientLight(new Color(255, 255, 255), 0));
		scene.setBackground(Color.BLACK);
//		Plane plane = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0.8, 0), new Point3D(0, 50, 60),
//				new Vector(0, 1, 0));
		Plane plane2 = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0, 0.4, 0.05, 0), //
				new Point3D(0, 20, 100), new Vector(0, 1, 0)); // ground
		Plane plane3 = new Plane(new Color(0, 30, 30), new Material(0.5, 0.5, 1200), //
				new Point3D(0, 20, 600), new Vector(0, 0, 1)); // background plane
		int position = 550;
		Polygon g1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-1000, -1000, position),//top left 
				new Point3D(-950, -1000, position),//top right
				new Point3D(-950, -600, position), //bottom right
				new Point3D(-1000, -600, position)); // bottom left
			
		Polygon g2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-970, -1000, position),//top left
				new Point3D(-700, -1000, position),//top right
				new Point3D(-700, -970, position), //bottom right
				new Point3D(-970, -970, position)// bottom left

				);
		Polygon g3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-970, -630, position),//top left
				new Point3D(-730, -630, position),//top right
				new Point3D(-730, -600, position), //bottom right
				new Point3D(-970, -600, position)// bottom left
				);
		Polygon g4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-750, -800, position),//top left
				new Point3D(-700, -800, position),//top right
				new Point3D(-700, -600, position), //bottom right
				new Point3D(-750, -600, position)// bottom left
				);
		
		Polygon g5 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-800, -830, position),//top left
				new Point3D(-700, -830, position),//top right
				new Point3D(-700, -800, position), //bottom right
				new Point3D(-800, -800, position)// bottom left
				);
		
		Sphere sphere_g = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.2, 0.5), 30,
				new Point3D(-800, -630, -70));
	
		
		
		Polygon e1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-600, -1000, position),//top left
				new Point3D(-300, -1000, position),//top right
				new Point3D(-300, -970, position), //bottom right
				new Point3D(-600, -970, position)// bottom left
				);
		Polygon e2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-600, -830, position),//top left
				new Point3D(-400, -830, position),//top right
				new Point3D(-400, -800, position), //bottom right
				new Point3D(-600, -800, position)// bottom left

				);
		Polygon e3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-600, -630, position),//top left
				new Point3D(-300, -630, position),//top right
				new Point3D(-300, -600, position), //bottom right
				new Point3D(-600, -600, position)// bottom left
				);
		Polygon e4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-600, -1000, position), //top left
				new Point3D(-550, -1000, position),//top right
				new Point3D(-550, -600, position),//bottom right
				new Point3D(-600, -600, position)); // bottom left
		
		

		
		
		Polygon u1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-200, -1000, position), //top left
				new Point3D(-150, -1000, position),//top right
				new Point3D(-150, -600, position),//bottom right
				new Point3D(-200, -600,position)); // bottom left
		Polygon u2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-200, -630, position),//top left
				new Point3D(100, -630, position),//top right
				new Point3D(100, -600, position), //bottom right
				new Point3D(-200, -600, position)// bottom left
				);
		Polygon u3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(50, -1000, position), //top left
				new Point3D(100, -1000, position),//top right
				new Point3D(100, -600, position),//bottom right
				new Point3D(50, -600, position)); // bottom left

		
		Polygon l1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(200, -1000, position), //top left
				new Point3D(250, -1000, position),//top right
				new Point3D(250, -600, position),//bottom right
				new Point3D(200, -600, position)); // bottom left
		
		Polygon l2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(200, -630, position),//top left
				new Point3D(500, -630, position),//top right
				new Point3D(500, -600, position), //bottom right
				new Point3D(200, -600, position)// bottom left
				);
		
		
		
		
		Polygon a1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(600, -1000, position), //top left
				new Point3D(650, -1000, position),//top right
				new Point3D(650, -600, position),//bottom right
				new Point3D(600, -600, position)); // bottom left
		
		Polygon a2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(600, -1000, position),//top left
				new Point3D(850, -1000, position),//top right
				new Point3D(850, -970, position), //bottom right
				new Point3D(600, -970, position)// bottom left

				);
		Polygon a3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(600, -830, position),//top left
				new Point3D(850, -830, position),//top right
				new Point3D(850, -800, position), //bottom right
				new Point3D(600, -800, position)// bottom left

				);
		Polygon a4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(850, -1000, position), //top left
				new Point3D(900, -1000, position),//top right
				new Point3D(900, -600, position),//bottom right
				new Point3D(850, -600, position)); // bottom left
		

		// Word TAMAR: letter T
		Polygon t1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-1000, -450, position),//top left
				new Point3D(-700, -450, position),//top right
				new Point3D(-700, -420, position), //bottom right
				new Point3D(-1000, -420, position)// bottom left

				);
		
		Polygon t2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-880, -450, position), //top left
				new Point3D(-830, -450, position), //top right
				new Point3D(-830, -50, position), //bottom right
				new Point3D(-880, -50, position));// bottom left
		
		

		// Word TAMAR: first letter A
		//left bar of letter A1
		Polygon Letter_a1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(-600, -50, position),
				new Point3D(-450, -450, position),
				new Point3D(-450, -350, position),
				new Point3D(-550, -50, position)
				);
		
		//right bar of letter A1
		Polygon Letter_a2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(-450, -450, position),
				new Point3D(-300, -50, position),
				new Point3D(-350, -50, position),
				new Point3D(-450, -350, position)
				);
		
		//middle bar of letter A1
		Polygon Letter_a3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(-520, -200, position),
				new Point3D(-370, -200, position),
				new Point3D(-370, -170, position),
				new Point3D(-520, -170, position)
				);
		
		
		// Word TAMAR: letter M
		//left bar of letter M
		Polygon Letter_m1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(-200, -50, position),
				new Point3D(-150, -50, position),
				new Point3D(-150, -450, position),
				new Point3D(-200, -450, position)
				);
		
		//left angled bar of letter M
		Polygon Letter_m2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(-150, -450, position),
				new Point3D(-50, -320, position),
				new Point3D(-50, -250, position),
				new Point3D(-150, -380, position)
				);
		
		//right angled bar of letter M
		Polygon Letter_m3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(-50, -320, position),
				new Point3D(50, -450, position),
				new Point3D(50, -380, position),
				new Point3D(-50, -250, position)
				);
		
		//right bar of letter M
		Polygon Letter_m4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(100, -50, position),
				new Point3D(100, -450, position),
				new Point3D(50, -450, position),
				new Point3D(50, -50, position)
				);
		
		
		// Word TAMAR: second letter A
		//left bar of letter A2
				Polygon Letter_A1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(-600+800, -50, position),
						new Point3D(-450+800, -450, position),
						new Point3D(-450+800, -350, position),
						new Point3D(-550+800, -50, position)
						);
				
				//right bar of letter A2
				Polygon Letter_A2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(-450+800, -450, position),
						new Point3D(-300+800, -50, position),
						new Point3D(-350+800, -50, position),
						new Point3D(-450+800, -350, position)
						);
				
				//middle bar of letter A2
				Polygon Letter_A3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(-520+800, -200, position),
						new Point3D(-370+800, -200, position),
						new Point3D(-370+800, -170, position),
						new Point3D(-520+800, -170, position)
						);
				
				
				// Word TAMAR: letter R
				//left bar of letter R
				Polygon Letter_r1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(600, -50, position),
						new Point3D(600, -450, position),
						new Point3D(650, -450, position),
						new Point3D(650, -50, position)
						);
				
				//upper angled bar of letter R
				Polygon Letter_r2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(650, -450, position),
						new Point3D(870, -350, position),
						new Point3D(800, -350, position),
						new Point3D(650, -400, position)
						);
				
				//lower angled bar of letter R
				Polygon Letter_r3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(870, -350, position),
						new Point3D(800, -350, position),
						new Point3D(650, -300, position),
						new Point3D(650, -250, position)
						);
				
				//right foot of letter R
				Polygon Letter_r4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(650, -300, position),
						new Point3D(900, -50, position),
						new Point3D(850, -50, position),
						new Point3D(650, -250, position)
						);
				
		
		Triangle tr1=  new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                  new Point3D(-450, 150, 150), new Point3D(-150, 150, 150), new Point3D(-75, -75, 150));
		Triangle tr2 =   new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
          new Point3D(-550, 150, 150), new Point3D(-70, -70, 50), new Point3D(-75, -75, 150));
		
		
		Sphere sphere = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.2, 0.5), 20,
				new Point3D(0, -50, 80));
		Sphere sphere2 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300, 0.2, 0), 9,
				new Point3D(0, -10, 80));

		scene.addIntersectable(
				plane2, plane3,
				g1,g2,g3,g4,g5,
				e1,e2, e3, e4,
				u1, u2 ,u3,
				l1, l2,
				a1, a2, a3,a4,	
				t1,t2,
				Letter_a1,Letter_a2,Letter_a3,
				Letter_m1,Letter_m2,Letter_m3,Letter_m4,
				Letter_A1,Letter_A2,Letter_A3,
				Letter_r1,Letter_r2,Letter_r3,Letter_r4
				);
		
		Point3D pos = new Point3D(20, -50, 0);
		Color color = new Color(400, 300, 300);

		PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        DirectionalLight directional_light = new DirectionalLight(color ,  new Vector(1,1,1));
        
		ImageWriter imageWriter = new ImageWriter("BVH test no BV", 2000, 2000, 1000, 1000);
		List<LightSource> lights = new ArrayList<LightSource>();
		//lights.add(point_light);
		//lights.add(directional_light);
		lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(1, 0, 1)));
//		lights.add(new PointLight(new Color(500, 250, 250),
//                new Point3D(10, 10, 130),
//                1, 0.0005, 0.0005));
////		lights.add(new SpotLight(new Color(500, 250, 250),
////	                new Point3D(10, 10, 130), new Vector(-2, 2, 1),
////	                1, 0.0001, 0.000005));
		scene.setLights(lights);
		Render render = new Render(imageWriter, scene)
				//.setMultithreading(2)
				//.setSampleCount(1)
				//.setDebugPrint()
				//.setUsingBoundaryVolume(true)
				;
		render.renderImage();
		render.writeToImage();
		System.out.println("end test without boundary" + java.time.LocalTime.now());

	}


	@Test
	public void BVHTestWithBoxes() {
		System.out.println("start test with boundary" +java.time.LocalTime.now());
		Scene scene = new Scene("BVH test");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setAmbient(new AmbientLight(new Color(255, 255, 255), 0));
		scene.setBackground(Color.BLACK);
//		Plane plane = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0.8, 0), new Point3D(0, 50, 60),
//				new Vector(0, 1, 0));
		Plane plane2 = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0, 0.4, 0.05, 0), //
				new Point3D(0, 20, 100), new Vector(0, 1, 0)); // ground
		Plane plane3 = new Plane(new Color(0, 30, 30), new Material(0.5, 0.5, 1200), //
				new Point3D(0, 20, 600), new Vector(0, 0, 1)); // background plane
		int position = 550;
		Polygon g1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-1000, -1000, position),//top left 
				new Point3D(-950, -1000, position),//top right
				new Point3D(-950, -600, position), //bottom right
				new Point3D(-1000, -600, position)); // bottom left
			
		Polygon g2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-970, -1000, position),//top left
				new Point3D(-700, -1000, position),//top right
				new Point3D(-700, -970, position), //bottom right
				new Point3D(-970, -970, position)// bottom left

				);
		Polygon g3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-970, -630, position),//top left
				new Point3D(-730, -630, position),//top right
				new Point3D(-730, -600, position), //bottom right
				new Point3D(-970, -600, position)// bottom left
				);
		Polygon g4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-750, -800, position),//top left
				new Point3D(-700, -800, position),//top right
				new Point3D(-700, -600, position), //bottom right
				new Point3D(-750, -600, position)// bottom left
				);
		
		Polygon g5 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-800, -830, position),//top left
				new Point3D(-700, -830, position),//top right
				new Point3D(-700, -800, position), //bottom right
				new Point3D(-800, -800, position)// bottom left
				);
		
		Sphere sphere_g = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.2, 0.5), 30,
				new Point3D(-800, -630, -70));
		Geometries g_group =new Geometries(g1,g2,g3,g4,g5);
		
		
		Polygon e1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-600, -1000, position),//top left
				new Point3D(-300, -1000, position),//top right
				new Point3D(-300, -970, position), //bottom right
				new Point3D(-600, -970, position)// bottom left
				);
		Polygon e2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-600, -830, position),//top left
				new Point3D(-400, -830, position),//top right
				new Point3D(-400, -800, position), //bottom right
				new Point3D(-600, -800, position)// bottom left

				);
		Polygon e3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-600, -630, position),//top left
				new Point3D(-300, -630, position),//top right
				new Point3D(-300, -600, position), //bottom right
				new Point3D(-600, -600, position)// bottom left
				);
		Polygon e4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-600, -1000, position), //top left
				new Point3D(-550, -1000, position),//top right
				new Point3D(-550, -600, position),//bottom right
				new Point3D(-600, -600, position)); // bottom left
		
		
		Geometries e_group =new Geometries(e1,e2, e3, e4);

		
		
		Polygon u1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-200, -1000, position), //top left
				new Point3D(-150, -1000, position),//top right
				new Point3D(-150, -600, position),//bottom right
				new Point3D(-200, -600,position)); // bottom left
		Polygon u2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-200, -630, position),//top left
				new Point3D(100, -630, position),//top right
				new Point3D(100, -600, position), //bottom right
				new Point3D(-200, -600, position)// bottom left
				);
		Polygon u3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(50, -1000, position), //top left
				new Point3D(100, -1000, position),//top right
				new Point3D(100, -600, position),//bottom right
				new Point3D(50, -600, position)); // bottom left
		Geometries u_group =new Geometries(u1, u2 ,u3);

		
		Polygon l1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(200, -1000, position), //top left
				new Point3D(250, -1000, position),//top right
				new Point3D(250, -600, position),//bottom right
				new Point3D(200, -600, position)); // bottom left
		
		Polygon l2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(200, -630, position),//top left
				new Point3D(500, -630, position),//top right
				new Point3D(500, -600, position), //bottom right
				new Point3D(200, -600, position)// bottom left
				);
		
		Geometries l_group =new Geometries(l1, l2);
		
		
		
		Polygon a1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(600, -1000, position), //top left
				new Point3D(650, -1000, position),//top right
				new Point3D(650, -600, position),//bottom right
				new Point3D(600, -600, position)); // bottom left
		
		Polygon a2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(600, -1000, position),//top left
				new Point3D(850, -1000, position),//top right
				new Point3D(850, -970, position), //bottom right
				new Point3D(600, -970, position)// bottom left

				);
		Polygon a3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(600, -830, position),//top left
				new Point3D(850, -830, position),//top right
				new Point3D(850, -800, position), //bottom right
				new Point3D(600, -800, position)// bottom left

				);
		Polygon a4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(850, -1000, position), //top left
				new Point3D(900, -1000, position),//top right
				new Point3D(900, -600, position),//bottom right
				new Point3D(850, -600, position)); // bottom left
		
		Geometries a_group =new Geometries(a1, a2, a3,a4);

		// Word TAMAR: letter T
		Polygon t1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-1000, -450, position),//top left
				new Point3D(-700, -450, position),//top right
				new Point3D(-700, -420, position), //bottom right
				new Point3D(-1000, -420, position)// bottom left

				);
		
		Polygon t2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-880, -450, position), //top left
				new Point3D(-830, -450, position), //top right
				new Point3D(-830, -50, position), //bottom right
				new Point3D(-880, -50, position));// bottom left
		
		
		Geometries t_group =new Geometries(t1,t2);

		// Word TAMAR: first letter A
		//left bar of letter A1
		Polygon Letter_a1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(-600, -50, position),
				new Point3D(-450, -450, position),
				new Point3D(-450, -350, position),
				new Point3D(-550, -50, position)
				);
		
		//right bar of letter A1
		Polygon Letter_a2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(-450, -450, position),
				new Point3D(-300, -50, position),
				new Point3D(-350, -50, position),
				new Point3D(-450, -350, position)
				);
		
		//middle bar of letter A1
		Polygon Letter_a3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(-520, -200, position),
				new Point3D(-370, -200, position),
				new Point3D(-370, -170, position),
				new Point3D(-520, -170, position)
				);
		
		Geometries Letter_a = new Geometries (Letter_a1,Letter_a2,Letter_a3);
		
		// Word TAMAR: letter M
		//left bar of letter M
		Polygon Letter_m1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(-200, -50, position),
				new Point3D(-150, -50, position),
				new Point3D(-150, -450, position),
				new Point3D(-200, -450, position)
				);
		
		//left angled bar of letter M
		Polygon Letter_m2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(-150, -450, position),
				new Point3D(-50, -320, position),
				new Point3D(-50, -250, position),
				new Point3D(-150, -380, position)
				);
		
		//right angled bar of letter M
		Polygon Letter_m3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(-50, -320, position),
				new Point3D(50, -450, position),
				new Point3D(50, -380, position),
				new Point3D(-50, -250, position)
				);
		
		//right bar of letter M
		Polygon Letter_m4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
				new Point3D(100, -50, position),
				new Point3D(100, -450, position),
				new Point3D(50, -450, position),
				new Point3D(50, -50, position)
				);
		
		Geometries Letter_m = new Geometries (Letter_m1,Letter_m2,Letter_m3,Letter_m4);
		
		// Word TAMAR: second letter A
		//left bar of letter A2
				Polygon Letter_A1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(-600+800, -50, position),
						new Point3D(-450+800, -450, position),
						new Point3D(-450+800, -350, position),
						new Point3D(-550+800, -50, position)
						);
				
				//right bar of letter A2
				Polygon Letter_A2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(-450+800, -450, position),
						new Point3D(-300+800, -50, position),
						new Point3D(-350+800, -50, position),
						new Point3D(-450+800, -350, position)
						);
				
				//middle bar of letter A2
				Polygon Letter_A3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(-520+800, -200, position),
						new Point3D(-370+800, -200, position),
						new Point3D(-370+800, -170, position),
						new Point3D(-520+800, -170, position)
						);
				
				Geometries Letter_A = new Geometries (Letter_A1,Letter_A2,Letter_A3);
				
				// Word TAMAR: letter R
				//left bar of letter R
				Polygon Letter_r1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(600, -50, position),
						new Point3D(600, -450, position),
						new Point3D(650, -450, position),
						new Point3D(650, -50, position)
						);
				
				//upper angled bar of letter R
				Polygon Letter_r2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(650, -450, position),
						new Point3D(870, -350, position),
						new Point3D(800, -350, position),
						new Point3D(650, -400, position)
						);
				
				//lower angled bar of letter R
				Polygon Letter_r3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(870, -350, position),
						new Point3D(800, -350, position),
						new Point3D(650, -300, position),
						new Point3D(650, -250, position)
						);
				
				//right foot of letter R
				Polygon Letter_r4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),
						new Point3D(650, -300, position),
						new Point3D(900, -50, position),
						new Point3D(850, -50, position),
						new Point3D(650, -250, position)
						);
				
				Geometries Letter_r = new Geometries (Letter_r1,Letter_r2,Letter_r3,Letter_r4);
		
		Triangle tr1=  new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                  new Point3D(-450, 150, 150), new Point3D(-150, 150, 150), new Point3D(-75, -75, 150));
		Triangle tr2 =   new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
          new Point3D(-550, 150, 150), new Point3D(-70, -70, 50), new Point3D(-75, -75, 150));
		
		
		Sphere sphere = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.2, 0.5), 20,
				new Point3D(0, -50, 80));
		Sphere sphere2 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300, 0.2, 0), 9,
				new Point3D(0, -10, 80));

		Geometries geula_group = new Geometries (
				g_group,
				e_group,
				u_group,
				l_group,
				a_group	);
		Geometries tamar_group = new Geometries (
				t_group,
				Letter_a,
				Letter_m,
				Letter_A,
				Letter_r);
		scene.addIntersectable(
				plane2, plane3,
				geula_group,
				tamar_group
				);
		
		Point3D pos = new Point3D(20, -50, 0);
		Color color = new Color(400, 300, 300);

		PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        DirectionalLight directional_light = new DirectionalLight(color ,  new Vector(1,1,1));
        
		ImageWriter imageWriter = new ImageWriter("BVH test", 2000, 2000, 1000, 1000);
		List<LightSource> lights = new ArrayList<LightSource>();
		//lights.add(point_light);
		//lights.add(directional_light);
		lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(1, 0, 1)));
//		lights.add(new PointLight(new Color(500, 250, 250),
//                new Point3D(10, 10, 130),
//                1, 0.0005, 0.0005));
////		lights.add(new SpotLight(new Color(500, 250, 250),
////	                new Point3D(10, 10, 130), new Vector(-2, 2, 1),
////	                1, 0.0001, 0.000005));
		scene.setLights(lights);
		Render render = new Render(imageWriter, scene)
				//.setMultithreading(2)
				//.setSampleCount(1)
				//.setDebugPrint()
				.setUsingBoundaryVolume(true)
				;
		render.renderImage();
		render.writeToImage();
		System.out.println("end test with boundary " + java.time.LocalTime.now());
	}


}
