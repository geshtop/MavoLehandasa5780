
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
import geometries.BoundaryVolume;
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
import static geometries.BoundaryVolume.doOverlap;
import static geometries.BoundaryVolume.getDistance;


import static org.junit.Assert.assertEquals;
public class MiniProject2AutoBoundaryTest {



	@Test
	public void BVHDistancesTest() {
		Scene scene = new Scene("BVH Distances test");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setAmbient(new AmbientLight(new Color(255, 255, 255), 0));
		scene.setBackground(Color.BLACK);
		Plane bg_plane = new Plane(new Color(0, 30, 30), new Material(0.5, 0.5, 1200), //
				new Point3D(0, 20, 600), new Vector(0, 0, 1)); // background plane
		Geometries bg_plane_group =new Geometries(bg_plane);

		int position = 0;
		Polygon main_rectangle = new Polygon(new Color(255, 255, 255), new Material(0.5, 0.5, 300, 0, 0), //
				new Point3D(-850, 350, position),//top left 
				new Point3D(-350, 350, position),//top right
				new Point3D(-350, 800, position), //bottom right
				new Point3D(-850, 800, position)); // bottom left
		Geometries main_rectangle_group =new Geometries(main_rectangle);
		Polygon side_right_rectangle = new Polygon(new Color(255, 250, 240), new Material(0.5, 0.5, 300, 0, 0), //
				new Point3D(-350, 350, position),//top left 
				new Point3D(100, -50, position),//top right
				new Point3D(100, 300, position), //bottom right
				new Point3D(-350, 800, position)); // bottom left
		Geometries side_right_rectangle_group =new Geometries( side_right_rectangle);
		
		
		Triangle main_top_triangle =   new Triangle(new Color(255, 0, 0), new Material(0.8, 0.2, 300),
		          new Point3D(-850, 350, position),
		          new Point3D(-350, 350, position),
		          new Point3D(-550, 0, position));
		Geometries main_top_triangle_group =new Geometries(main_top_triangle);
		
		
//		Triangle second_top_triangle =   new Triangle(new Color(30, 0, 20), new Material(0.8, 0.2, 300),
//		          new Point3D(-350, 350, position),
//		          new Point3D(100, -50, position),
//		          new Point3D(-550, 0, position));
//		Geometries second_top_triangle_group =new Geometries(second_top_triangle);
		
		Polygon side_top_rectangle = new Polygon(new Color(255, 0, 0), new Material(0.5, 0.5, 300, 0, 0), //
				new Point3D(-550, 0, position),//top left 
				new Point3D(-50, -250, position),//top right
				new Point3D(100, -50, position), //bottom right
				new Point3D(-350, 350, position)); // bottom left
		Geometries side_top_rectangle_group =new Geometries(side_top_rectangle);
		
		
		
//		Sphere center_sphare = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.5, 0.5), 100,
//				new Point3D(0, -150, position+50));
//		Geometries center_sphare_group =new Geometries(center_sphare);

//		Sphere top_sphare = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 100,  0, 0.5), 80,
//				new Point3D(50, -600, position-10));
//		Geometries top_sphare_group =new Geometries(top_sphare);
		
		
		
		

	
		Geometries main_group = new Geometries (
				
				);
		for (int i = 0; i < 67 ; i++) {
			int mainx = 1000;
			Sphere sphere21 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300), 20,
					new Point3D(mainx - (i * 30.247)  , -850, 80));
			Sphere sphere17 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300), 9,
					new Point3D(mainx - (i * 30.247) , -810, 80));
			Sphere sphere18 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300), 10,
					new Point3D(mainx - (i * 30.247) , -790, 80));
			Sphere sphere19 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 5,
					new Point3D(mainx - (i * 30.247) , -770, 80));
			Sphere sphere20 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 40,
					new Point3D(mainx - (i * 30.247) , -900, 80));
			main_group.add(sphere21);
			main_group.add(sphere17);
			main_group.add(sphere18);
			main_group.add(sphere19);
			main_group.add(sphere20);
			
		}
		
		
		
		for (int i = 0; i < 67 ; i++) {
			int mainx = 1000;
			Sphere sphere21 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300), 20,
					new Point3D(mainx - (i * 30.247)  , -350, 80));
			Sphere sphere17 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300), 9,
					new Point3D(mainx - (i * 30.247) , -310, 80));
			Sphere sphere18 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300), 10,
					new Point3D(mainx - (i * 30.247) , -290, 80));
			Sphere sphere19 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 5,
					new Point3D(mainx - (i * 30.247) , -270, 80));
			Sphere sphere20 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 40,
					new Point3D(mainx - (i * 30.247) , -400, 80));
			main_group.add(sphere21);
			main_group.add(sphere17);
			main_group.add(sphere18);
			main_group.add(sphere19);
			main_group.add(sphere20);
			
		}
		
		
		for (int i = 0; i < 67 ; i++) {
			int mainx = 1000;
			Sphere sphere21 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300), 20,
					new Point3D(mainx - (i * 30.247)  , 150, 80));
			Sphere sphere17 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300), 9,
					new Point3D(mainx - (i * 30.247) , 190, 80));
			Sphere sphere18 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300), 10,
					new Point3D(mainx - (i * 30.247) , 210, 80));
			Sphere sphere19 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 5,
					new Point3D(mainx - (i * 30.247) , 230, 80));
			Sphere sphere20 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 40,
					new Point3D(mainx - (i * 30.247) , 100, 80));
			main_group.add(sphere21);
			main_group.add(sphere17);
			main_group.add(sphere18);
			main_group.add(sphere19);
			main_group.add(sphere20);
			
		}
		
		
		
		
		for (int i = 0; i < 67 ; i++) {
			int mainx = 1000;
			Sphere sphere21 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300), 20,
					new Point3D(mainx - (i * 30.247)  , 650, 80));
			Sphere sphere17 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300), 9,
					new Point3D(mainx - (i * 30.247) , 690, 80));
			Sphere sphere18 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300), 10,
					new Point3D(mainx - (i * 30.247) , 710, 80));
			Sphere sphere19 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 5,
					new Point3D(mainx - (i * 30.247) , 730, 80));
			Sphere sphere20 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 40,
					new Point3D(mainx - (i * 30.247) , 600, 80));
			main_group.add(sphere21);
			main_group.add(sphere17);
			main_group.add(sphere18);
			main_group.add(sphere19);
			main_group.add(sphere20);
			
		}
		


	
	
//		main_group.add(main_rectangle_group,
//				side_right_rectangle_group,
//				main_top_triangle_group,
//				side_top_rectangle_group);
	
		
		//main_group.groupGeometries(30);
		scene.addIntersectable(
				//bg_plane_group,
				main_group
			
				);
		
		
//		boolean b1 = doOverlap(main_rectangle_group, center_sphare_group);
//		assertEquals("המרובע הראשי והעיגול הפנימי מכילים אחד את השני",b1, true); 
//
//		boolean b2 = doOverlap(main_rectangle_group, out_triangle_group);
//		assertEquals("המרובע הראשי והמשולש החיצוני לא חופפים",b2, false); 
//		
//		boolean b3 = doOverlap(main_rectangle_group, main_bottom_triangle_group);
//		assertEquals("המרובע הראשי והמשולש הראשי חופפים בנקודות קצה",b3, true); 
//		
//		boolean b4 = doOverlap(main_rectangle_group, top_sphare_group);
//		assertEquals("מרובע ראשי ועיגול עליון לא חופפים",b4, false); 
//		
//		boolean b5 = doOverlap(side_top_rectangle_group, top_sphare_group);
//		assertEquals("מרובע עליון ועיגול עליון עם נקודות חפיפה",b5, true); 
//		
//		boolean b6 = doOverlap(bg_plane_group, top_sphare_group);
//		assertEquals("למשטח יש נקודות חפיפה עם כל האלמנטים",b6, true); 
		
		
		
		//double d1 = getDistance(main_rectangle_group, out_triangle_group);
		//double d2 = getDistance(main_rectangle_group, top_sphare_group);

		Point3D pos = new Point3D(0, -160, -700);
		Color color = new Color(400, 300, 300);

		PointLight point_light = new PointLight(color, pos, 1, 0, 0);
        DirectionalLight directional_light = new DirectionalLight(color ,  new Vector(1,1,1));
        
		ImageWriter imageWriter = new ImageWriter("BVH Distance test", 2000, 2000, 1000, 1000);
		List<LightSource> lights = new ArrayList<LightSource>();
		//lights.add(point_light);
		lights.add(directional_light);
		//lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, 1)));
//		lights.add(new PointLight(new Color(500, 250, 250),
//                new Point3D(10, 10, 130),
//                1, 0.0005, 0.0005));
//		lights.add(new SpotLight(new Color(500, 250, 250),
//	                new Point3D(10, 10, 130), new Vector(-2, 2, 1),
//	                1, 0.0001, 0.000005));
		scene.setLights(lights);
		Render render = new Render(imageWriter, scene)
				//.setMultithreading(2)
				//.setSampleCount(1)
				//.setDebugPrint()
				//.setUsingBoundaryVolume(true)
				;
		render.renderImage();
		render.writeToImage();
	}




	
	

	@Test
	public void BVHDistancesWithAutoBoundaryTest() {
		Scene scene = new Scene("BVH Distances test");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setAmbient(new AmbientLight(new Color(255, 255, 255), 0));
		scene.setBackground(Color.BLACK);
		Plane bg_plane = new Plane(new Color(0, 30, 30), new Material(0.5, 0.5, 1200), //
				new Point3D(0, 20, 600), new Vector(0, 0, 1)); // background plane
		Geometries bg_plane_group =new Geometries(bg_plane);

		int position = 0;
		Polygon main_rectangle = new Polygon(new Color(255, 255, 255), new Material(0.5, 0.5, 300, 0, 0), //
				new Point3D(-850, 350, position),//top left 
				new Point3D(-350, 350, position),//top right
				new Point3D(-350, 800, position), //bottom right
				new Point3D(-850, 800, position)); // bottom left
		Geometries main_rectangle_group =new Geometries(main_rectangle);
		Polygon side_right_rectangle = new Polygon(new Color(255, 250, 240), new Material(0.5, 0.5, 300, 0, 0), //
				new Point3D(-350, 350, position),//top left 
				new Point3D(100, -50, position),//top right
				new Point3D(100, 300, position), //bottom right
				new Point3D(-350, 800, position)); // bottom left
		Geometries side_right_rectangle_group =new Geometries( side_right_rectangle);
		
		Geometries main_group1 = new Geometries ();
		Geometries main_group2 = new Geometries ();
		Geometries main_group3 = new Geometries ();
		Geometries main_group4 = new Geometries ();

		
		Triangle main_top_triangle =   new Triangle(new Color(255, 0, 0), new Material(0.8, 0.2, 300),
		          new Point3D(-850, 350, position),
		          new Point3D(-350, 350, position),
		          new Point3D(-550, 0, position));
		Geometries main_top_triangle_group =new Geometries(main_top_triangle);
		
		
//		Triangle second_top_triangle =   new Triangle(new Color(30, 0, 20), new Material(0.8, 0.2, 300),
//		          new Point3D(-350, 350, position),
//		          new Point3D(100, -50, position),
//		          new Point3D(-550, 0, position));
//		Geometries second_top_triangle_group =new Geometries(second_top_triangle);
		
		Polygon side_top_rectangle = new Polygon(new Color(255, 0, 0), new Material(0.5, 0.5, 300, 0, 0), //
				new Point3D(-550, 0, position),//top left 
				new Point3D(-50, -250, position),//top right
				new Point3D(100, -50, position), //bottom right
				new Point3D(-350, 350, position)); // bottom left
		Geometries side_top_rectangle_group =new Geometries(side_top_rectangle);
		
		
		
//		Sphere center_sphare = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.5, 0.5), 100,
//				new Point3D(0, -150, position+50));
//		Geometries center_sphare_group =new Geometries(center_sphare);

//		Sphere top_sphare = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 100,  0, 0.5), 80,
//				new Point3D(50, -600, position-10));
//		Geometries top_sphare_group =new Geometries(top_sphare);
		
		
		
		

	
		Geometries main_group = new Geometries ();
		for (int i = 0; i < 67 ; i++) {
			int mainx = 1000;
			Sphere sphere21 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300), 20,
					new Point3D(mainx - (i * 30.247)  , -850, 80));
			Sphere sphere17 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300), 9,
					new Point3D(mainx - (i * 30.247) , -810, 80));
			Sphere sphere18 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300), 10,
					new Point3D(mainx - (i * 30.247) , -790, 80));
			Sphere sphere19 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 5,
					new Point3D(mainx - (i * 30.247) , -770, 80));
			Sphere sphere20 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 40,
					new Point3D(mainx - (i * 30.247) , -900, 80));
			main_group.add(sphere21, sphere17 , sphere18, sphere19, sphere20);

			main_group1.add(sphere21, sphere17 , sphere18, sphere19, sphere20);

			
			
			
			
		}
		
		

		for (int i = 0; i < 67 ; i++) {
			int mainx = 1000;
			Sphere sphere21 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300), 20,
					new Point3D(mainx - (i * 30.247)  , -350, 80));
			Sphere sphere17 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300), 9,
					new Point3D(mainx - (i * 30.247) , -310, 80));
			Sphere sphere18 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300), 10,
					new Point3D(mainx - (i * 30.247) , -290, 80));
			Sphere sphere19 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 5,
					new Point3D(mainx - (i * 30.247) , -270, 80));
			Sphere sphere20 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 40,
					new Point3D(mainx - (i * 30.247) , -400, 80));
			main_group.add(sphere21, sphere17 , sphere18, sphere19, sphere20);
			main_group2.add(sphere21, sphere17 , sphere18, sphere19, sphere20);

			
			
			
		}
		

		for (int i = 0; i < 67 ; i++) {
			int mainx = 1000;
			Sphere sphere21 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300), 20,
					new Point3D(mainx - (i * 30.247)  , 150, 80));
			Sphere sphere17 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300), 9,
					new Point3D(mainx - (i * 30.247) , 190, 80));
			Sphere sphere18 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300), 10,
					new Point3D(mainx - (i * 30.247) , 210, 80));
			Sphere sphere19 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 5,
					new Point3D(mainx - (i * 30.247) , 230, 80));
			Sphere sphere20 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 40,
					new Point3D(mainx - (i * 30.247) , 100, 80));
			main_group.add(sphere21, sphere17 , sphere18, sphere19, sphere20);
			main_group3.add(sphere21, sphere17 , sphere18, sphere19, sphere20);

			
		}
		
		
		

		for (int i = 0; i < 67 ; i++) {
			int mainx = 1000;
			Sphere sphere21 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300), 20,
					new Point3D(mainx - (i * 30.247)  , 650, 80));
			Sphere sphere17 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300), 9,
					new Point3D(mainx - (i * 30.247) , 690, 80));
			Sphere sphere18 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300), 10,
					new Point3D(mainx - (i * 30.247) , 710, 80));
			Sphere sphere19 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 5,
					new Point3D(mainx - (i * 30.247) , 730, 80));
			Sphere sphere20 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300), 40,
					new Point3D(mainx - (i * 30.247) , 600, 80));
			main_group.add(sphere21, sphere17 , sphere18, sphere19, sphere20);
			main_group4.add(sphere21, sphere17 , sphere18, sphere19, sphere20);
			
			
		}
		
		/////////////////////////////////////////
		/////////////////////////////////////////
		/////////////////////////////////////////
		/////////////////////////////////////////
		/// here grouping the geometries
		/////////////////////////////////////////
		/////////////////////////////////////////

		main_group.groupGeometries(200);
	

		Point3D pos = new Point3D(0, -160, -700);
		Color color = new Color(400, 300, 300);

		PointLight point_light = new PointLight(color, pos, 1, 0, 0);
        DirectionalLight directional_light = new DirectionalLight(color ,  new Vector(1,1,1));
        
//		main_group.add(main_rectangle_group,
//				side_right_rectangle_group,
//				main_top_triangle_group,
//				side_top_rectangle_group);
	
//		boolean b1 = doOverlap(main_rectangle_group, center_sphare_group);
//		assertEquals("המרובע הראשי והעיגול הפנימי מכילים אחד את השני",b1, true); 
//
//		boolean b2 = doOverlap(main_rectangle_group, out_triangle_group);
//		assertEquals("המרובע הראשי והמשולש החיצוני לא חופפים",b2, false); 
//		
//		boolean b3 = doOverlap(main_rectangle_group, main_bottom_triangle_group);
//		assertEquals("המרובע הראשי והמשולש הראשי חופפים בנקודות קצה",b3, true); 
//		
//		boolean b4 = doOverlap(main_rectangle_group, top_sphare_group);
//		assertEquals("מרובע ראשי ועיגול עליון לא חופפים",b4, false); 
//		
//		boolean b5 = doOverlap(side_top_rectangle_group, top_sphare_group);
//		assertEquals("מרובע עליון ועיגול עליון עם נקודות חפיפה",b5, true); 
//		
//		boolean b6 = doOverlap(bg_plane_group, top_sphare_group);
//		assertEquals("למשטח יש נקודות חפיפה עם כל האלמנטים",b6, true); 
//		boolean b1 = doOverlap(main_rectangle_group, center_sphare_group);
//		assertEquals("המרובע הראשי והעיגול הפנימי מכילים אחד את השני",b1, true); 
//
//		boolean b2 = doOverlap(main_rectangle_group, out_triangle_group);
//		assertEquals("המרובע הראשי והמשולש החיצוני לא חופפים",b2, false); 
//		
//		boolean b3 = doOverlap(main_rectangle_group, main_bottom_triangle_group);
//		assertEquals("המרובע הראשי והמשולש הראשי חופפים בנקודות קצה",b3, true); 
//		
//		boolean b4 = doOverlap(main_rectangle_group, top_sphare_group);
//		assertEquals("מרובע ראשי ועיגול עליון לא חופפים",b4, false); 
//		
//		boolean b5 = doOverlap(side_top_rectangle_group, top_sphare_group);
//		assertEquals("מרובע עליון ועיגול עליון עם נקודות חפיפה",b5, true); 
//		
//		boolean b6 = doOverlap(bg_plane_group, top_sphare_group);
//		assertEquals("למשטח יש נקודות חפיפה עם כל האלמנטים",b6, true); 
		scene.addIntersectable(				//bg_plane_group, 				//main_group
				main_group1, main_group2,main_group3,main_group4
				);

		//double d1 = getDistance(main_rectangle_group, out_triangle_group);
		//double d2 = getDistance(main_rectangle_group, top_sphare_group);

		ImageWriter imageWriter = new ImageWriter("BVH Distance test with auto boundary", 2000, 2000, 1000, 1000);
		List<LightSource> lights = new ArrayList<LightSource>();
		//lights.add(point_light);
		lights.add(directional_light);
		//lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(0, 0, 1)));
//		lights.add(new PointLight(new Color(500, 250, 250),
//                new Point3D(10, 10, 130),
//                1, 0.0005, 0.0005));
//		lights.add(new SpotLight(new Color(500, 250, 250),
//	                new Point3D(10, 10, 130), new Vector(-2, 2, 1),
//	                1, 0.0001, 0.000005));
		scene.setLights(lights);
		Render render = new Render(imageWriter, scene)
				//.setMultithreading(2)
				//.setSampleCount(1)
				//.setDebugPrint()
				.setUsingBoundaryVolume(true)
				;
		render.renderImage();
		render.writeToImage();
	}





	


}
