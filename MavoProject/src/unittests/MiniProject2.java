package unittests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.LightSource;
import elements.PointLight;
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
		Scene scene = new Scene("BVH test");
		scene.setCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.setDistance(1000);
		scene.setAmbient(new AmbientLight(new Color(255, 255, 255), 0));
		scene.setBackground(Color.BLACK);
		Plane plane = new Plane(new Color(0, 0, 0), new Material(0.5, 0.5, 300, 0.8, 0), new Point3D(0, 50, 60),
				new Vector(0, 1, 0));
		
		Polygon g1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-1000, -1000, 30),//top left 
				new Point3D(-950, -1000, 30),//top right
				new Point3D(-950, -600, 30), //bottom right
				new Point3D(-1000, -600, 30)); // bottom left
		
		Polygon g2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-970, -1000, 30),//top left
				new Point3D(-700, -1000, 30),//top right
				new Point3D(-700, -970, 30), //bottom right
				new Point3D(-970, -970, 30)// bottom left

				);
		Polygon g3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-970, -630, 30),//top left
				new Point3D(-730, -630, 30),//top right
				new Point3D(-730, -600, 30), //bottom right
				new Point3D(-970, -600, 30)// bottom left
				);
		Polygon g4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-750, -800, 30),//top left
				new Point3D(-700, -800, 30),//top right
				new Point3D(-700, -600, 30), //bottom right
				new Point3D(-750, -600, 30)// bottom left
				);
		
		Polygon g5 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-800, -830, 30),//top left
				new Point3D(-700, -830, 30),//top right
				new Point3D(-700, -800, 30), //bottom right
				new Point3D(-800, -800, 30)// bottom left
				);
		
		Geometries g_group =new Geometries(g1,g2,g3,g4,g5);
		
		
		Polygon e1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-600, -1000, 30),//top left
				new Point3D(-300, -1000, 30),//top right
				new Point3D(-300, -970, 30), //bottom right
				new Point3D(-600, -970, 30)// bottom left
				);
		Polygon e2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-600, -830, 30),//top left
				new Point3D(-400, -830, 30),//top right
				new Point3D(-400, -800, 30), //bottom right
				new Point3D(-600, -800, 30)// bottom left

				);
		Polygon e3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-600, -630, 30),//top left
				new Point3D(-300, -630, 30),//top right
				new Point3D(-300, -600, 30), //bottom right
				new Point3D(-600, -600, 30)// bottom left
				);
		Polygon e4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-600, -1000, 30), //top left
				new Point3D(-550, -1000, 30),//top right
				new Point3D(-550, -600, 30),//bottom right
				new Point3D(-600, -600, 30)); // bottom left
		
		Geometries e_group =new Geometries(e1,e2, e3, e4);

		
		
		Polygon u1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-200, -1000, 30), //top left
				new Point3D(-150, -1000, 30),//top right
				new Point3D(-150, -600, 30),//bottom right
				new Point3D(-200, -600, 30)); // bottom left
		Polygon u2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-200, -630, 30),//top left
				new Point3D(100, -630, 30),//top right
				new Point3D(100, -600, 30), //bottom right
				new Point3D(-200, -600, 30)// bottom left
				);
		Polygon u3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(50, -1000, 30), //top left
				new Point3D(100, -1000, 30),//top right
				new Point3D(100, -600, 30),//bottom right
				new Point3D(50, -600, 30)); // bottom left
		Geometries u_group =new Geometries(u1, u2 ,u3);

		
		Polygon l1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(200, -1000, 30), //top left
				new Point3D(250, -1000, 30),//top right
				new Point3D(250, -600, 30),//bottom right
				new Point3D(200, -600, 30)); // bottom left
		
		Polygon l2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(200, -630, 30),//top left
				new Point3D(500, -630, 30),//top right
				new Point3D(500, -600, 30), //bottom right
				new Point3D(200, -600, 30)// bottom left
				);
		
		Geometries l_group =new Geometries(l1, l2);
		
		
		
		Polygon a1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(600, -1000, 30), //top left
				new Point3D(650, -1000, 30),//top right
				new Point3D(650, -600, 30),//bottom right
				new Point3D(600, -600, 30)); // bottom left
		
		Polygon a2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(600, -1000, 30),//top left
				new Point3D(850, -1000, 30),//top right
				new Point3D(850, -970, 30), //bottom right
				new Point3D(600, -970, 30)// bottom left

				);
		Polygon a3 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(600, -830, 30),//top left
				new Point3D(850, -830, 30),//top right
				new Point3D(850, -800, 30), //bottom right
				new Point3D(600, -800, 30)// bottom left

				);
		Polygon a4 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(850, -1000, 30), //top left
				new Point3D(900, -1000, 30),//top right
				new Point3D(900, -600, 30),//bottom right
				new Point3D(850, -600, 30)); // bottom left
		
		Geometries a_group =new Geometries(a1, a2, a3,a4);


		Polygon t1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-1000, -450, 30),//top left
				new Point3D(-700, -450, 30),//top right
				new Point3D(-700, -420, 30), //bottom right
				new Point3D(-1000, -420, 30)// bottom left

				);
		
		Polygon t2 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-880, -450, 30), //top left
				new Point3D(-830, -450, 30), //top right
				new Point3D(-830, -50, 30), //bottom right
				new Point3D(-880, -50, 30));// bottom left
		
		
		
		
		
		
		Geometries t_group =new Geometries(t1,t2);

		
		Triangle tr1=  new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                  new Point3D(-450, 150, 150), new Point3D(-150, 150, 150), new Point3D(-75, -75, 150));
		Triangle tr2 =   new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
          new Point3D(-550, 150, 150), new Point3D(-70, -70, 50), new Point3D(-75, -75, 150));
		
		
		Sphere sphere = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.2, 0.5), 20,
				new Point3D(0, -50, 80));
		Sphere sphere2 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300, 0.2, 0), 9,
				new Point3D(0, -10, 80));

		scene.addIntersectable(
				plane,
				g_group,
				e_group,
				u_group,
				l_group,
				a_group,	
				t_group
				);
		
		Point3D pos = new Point3D(20, -50, 0);
		Color color = new Color(400, 300, 300);

		PointLight point_light = new PointLight(color, pos, 1, 0.0005, 0.000005);
        DirectionalLight directional_light = new DirectionalLight(color ,  new Vector(1,1,1));
        
		ImageWriter imageWriter = new ImageWriter("BVH test", 2000, 2000, 1000, 1000);
		List<LightSource> lights = new ArrayList<LightSource>();
		lights.add(point_light);
		lights.add(directional_light);
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
