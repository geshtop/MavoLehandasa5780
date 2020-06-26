
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
		Polygon main_rectangle = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0, 0), //
				new Point3D(-350, -500, position),//top left 
				new Point3D(350, -500, position),//top right
				new Point3D(350, 200, position), //bottom right
				new Point3D(-350, 200, position)); // bottom left
		Geometries main_rectangle_group =new Geometries(main_rectangle);
		
		
		Sphere center_sphare = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.5, 0.5), 100,
				new Point3D(0, -150, position+50));
		Geometries center_sphare_group =new Geometries(center_sphare);

		Sphere top_sphare = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 100,  0, 0.5), 80,
				new Point3D(50, -600, position-10));
		Geometries top_sphare_group =new Geometries(top_sphare);
		
		
		Triangle main_bottom_triangle =   new Triangle(new Color(0, 0, 20), new Material(0.8, 0.2, 300),
		          new Point3D(-350, 200, position),
		          new Point3D(350, 200, position),
		          new Point3D(0, 700, position+20));
		Geometries main_bottom_triangle_group =new Geometries(main_bottom_triangle);
		

		Polygon side_top_rectangle = new Polygon(new Color(0, 20, 20), new Material(0.5, 0.5, 300, 0, 0), //
				new Point3D(-100, -650, position-40),//top left 
				new Point3D(500, -650, position-40),//top right
				new Point3D(350, -500, position), //bottom right
				new Point3D(-350, -500, position)); // bottom left
		Geometries side_top_rectangle_group =new Geometries(side_top_rectangle);
		
		
		Polygon side_right_rectangle = new Polygon(new Color(0, 10, 20), new Material(0.5, 0.5, 300, 0, 0), //
				new Point3D(350, -500, position),//top left 
				new Point3D(500, -650, position-40),//top right
				new Point3D(500, 50, position-40), //bottom right
				new Point3D(350, 200, position)); // bottom left
		Geometries side_right_rectangle_group =new Geometries( side_right_rectangle);
		
		
		Triangle side_right_triangle =   new Triangle(new Color(0, 0, 20), new Material(0.8, 0.2, 300),
		          new Point3D(350, 200, position),
		          new Point3D(500, 50, position-40),
		          new Point3D(0, 700, position+20));
		Geometries side_right_triangle_group =new Geometries( side_right_triangle);
		
		Triangle out_triangle =   new Triangle(new Color(0, 0, 20), new Material(0.8, 0.2, 300),
		          new Point3D(-1000, 700, position),
		          new Point3D(-700, 1000, position),
		          new Point3D(-1000, 1000, position));
		
		Geometries out_triangle_group =new Geometries( out_triangle);

		
		Geometries main_group = new Geometries (main_rectangle_group,
				center_sphare_group,
				top_sphare_group,
				main_bottom_triangle_group,
				side_top_rectangle_group,
				side_right_rectangle_group,
				side_right_triangle_group,
				out_triangle,
				out_triangle_group);
		
				main_group.groupGeometries(10);
		scene.addIntersectable(
				//bg_plane_group,
				main_group
			
				);
		
		
		boolean b1 = doOverlap(main_rectangle_group, center_sphare_group);
		assertEquals("המרובע הראשי והעיגול הפנימי מכילים אחד את השני",b1, true); 

		boolean b2 = doOverlap(main_rectangle_group, out_triangle_group);
		assertEquals("המרובע הראשי והמשולש החיצוני לא חופפים",b2, false); 
		
		boolean b3 = doOverlap(main_rectangle_group, main_bottom_triangle_group);
		assertEquals("המרובע הראשי והמשולש הראשי חופפים בנקודות קצה",b3, true); 
		
		boolean b4 = doOverlap(main_rectangle_group, top_sphare_group);
		assertEquals("מרובע ראשי ועיגול עליון לא חופפים",b4, false); 
		
		boolean b5 = doOverlap(side_top_rectangle_group, top_sphare_group);
		assertEquals("מרובע עליון ועיגול עליון עם נקודות חפיפה",b5, true); 
		
		boolean b6 = doOverlap(bg_plane_group, top_sphare_group);
		assertEquals("למשטח יש נקודות חפיפה עם כל האלמנטים",b6, true); 
		
		
		
		double d1 = getDistance(main_rectangle_group, out_triangle_group);
		double d2 = getDistance(main_rectangle_group, top_sphare_group);

		Point3D pos = new Point3D(0, -160, -700);
		Color color = new Color(400, 300, 300);

		PointLight point_light = new PointLight(color, pos, 1, 0, 0);
        DirectionalLight directional_light = new DirectionalLight(color ,  new Vector(1,1,1));
        
		ImageWriter imageWriter = new ImageWriter("BVH Distance test", 2000, 2000, 1000, 1000);
		List<LightSource> lights = new ArrayList<LightSource>();
		lights.add(point_light);
		//lights.add(directional_light);
		//lights.add(new DirectionalLight(new Color(300, 150, 150), new Vector(1, 0, 1)));
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
	}






}
