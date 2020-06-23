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
		
		Polygon chut1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-100, -250, 20), new Point3D(-90, -250, 20), new Point3D(-90, -30, 30),
				new Point3D(-100, -30, 30));
		
		Triangle t1=  new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                  new Point3D(-450, 150, 150), new Point3D(-150, 150, 150), new Point3D(-75, -75, 150));
				Triangle t2 =   new Triangle(Color.BLACK, new Material(0.8, 0.2, 300),
                  new Point3D(-550, 150, 150), new Point3D(-70, -70, 50), new Point3D(-75, -75, 150));
		
		
		Sphere sphere = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.2, 0.5), 20,
				new Point3D(0, -50, 80));
		Sphere sphere2 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300, 0.2, 0), 9,
				new Point3D(0, -10, 80));
		Sphere sphere3 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300, 0.2, 0), 10,
				new Point3D(0, 10, 80));
		Sphere sphere4 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 5,
				new Point3D(0, 30, 80));
		Sphere sphere5 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 40,
				new Point3D(0, -100, 80));
		Geometries geo1 = new Geometries(new Geometries(sphere,sphere2)
				,new Geometries(new Geometries(sphere3,sphere4),sphere5));
		Sphere sphere21 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.2, 0.5), 20,
				new Point3D(-100, -50, 80));
		Sphere sphere17 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300, 0.2, 0), 9,
				new Point3D(-100, -10, 80));
		Sphere sphere18 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300, 0.2, 0), 10,
				new Point3D(-100, 10, 80));
		Sphere sphere19 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 5,
				new Point3D(-100, 30, 80));
		Sphere sphere20 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 40,
				new Point3D(-100, -100, 80));
		Geometries geo2 = new Geometries(new Geometries(sphere21,sphere17),
				new Geometries(sphere18,sphere19),sphere20);
		Sphere sphere22 = new Sphere(new Color(0, 0, 20), new Material(0.5, 0.5, 300, 0.2, 0.5), 20,
				new Point3D(100, -50, 80));
		Sphere sphere23 = new Sphere(new Color(0, 20, 0), new Material(0.5, 0.5, 300, 0.2, 0), 9,
				new Point3D(100, -10, 30));
		Sphere sphere24 = new Sphere(new Color(20, 20, 0), new Material(0.5, 0.5, 300, 0.2, 0), 10,
				new Point3D(100, 10, 30));
		Sphere sphere25 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 5,
				new Point3D(100, 30, 30));
		Sphere sphere26 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 40,
				new Point3D(100, -100, 30));
		Geometries geo3 = new Geometries(new Geometries(sphere22,sphere23)
				,new Geometries(sphere24,sphere25,sphere26));
		Triangle triangle1 = new Triangle(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),//
				new Point3D(-100, -120, 80) ,new Point3D(0, -200, 80),
				new Point3D(100, -100, 30));
		
		
		Polygon polygon1 = new Polygon(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), //
				new Point3D(-100, -50, 20), new Point3D(-90, -50, 20), new Point3D(-90, -30, 30),
				new Point3D(-100, -30, 30));
		Polygon polygon2 = new Polygon (new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),//
				new Point3D(-120, 50, 50) ,new Point3D(-100, 50, 50),new Point3D(-100, 30, 50),
				new Point3D(-120, 30, 50)); 
		Polygon polygon3 = new Polygon (new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),//
				new Point3D(-130, 50, 30) ,new Point3D(-110, 50, 30),new Point3D(-110, 30, 30), 
				new Point3D(-130, 30, 30)); 
		Geometries geo4 = new Geometries (polygon1, polygon2, polygon3);
		Sphere sphere6 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 40,
				new Point3D(50, -280, 20));
		Sphere sphere7 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 60,
				new Point3D(50, -120, 80));
		Sphere sphere8 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 80,
				new Point3D(50, 0, -10));
		Sphere sphere9 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 100,
				new Point3D(50, 150, 80));
		Sphere sphere10 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0),80 ,
				new Point3D(70, -280, 80));
		Sphere sphere11 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 60,
				new Point3D(70, -120, 80));
		Sphere sphere12 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 40,
				new Point3D(70, -0, 80));
		Sphere sphere13 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 30,
				new Point3D(70, 50, 80));
		Sphere sphere14 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 20,
				new Point3D(70, 70, 80));
		Sphere sphere15 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 10,
				new Point3D(70, 90, 80));
		Sphere sphere16 = new Sphere(new Color(20, 20, 20), new Material(0.5, 0.5, 300, 0.2, 0), 1,
				new Point3D(70, 110, 20));
		Geometries geo5 = new Geometries(new Geometries(sphere6,sphere7,sphere8,sphere9)
				,new Geometries(sphere10,sphere11,new Geometries(sphere12,sphere13,new Geometries(sphere14
			     ,sphere15))),sphere16);
//		scene.addIntersectable(
//				triangle1,geo1,geo2,geo3,geo4,geo5 ,plane);

		
		Geometries geo32 =new Geometries(plane,chut1);
				
		Geometries geo33 = new Geometries(/*t1,t2,*/sphere26, sphere18);
		scene.addIntersectable(
				geo33,
				plane, geo32//,t1,t2
//				sphere,
//				sphere2,
//				sphere3,
//				sphere4,
//				sphere5 ,
//				sphere6,
//				sphere7,
//				sphere9,
//				sphere10,
//				sphere11,
//				sphere12,
//				sphere13,
////				sphere14,
////				sphere15,
//				sphere16,
//				polygon1, polygon2,polygon3
//				,sphere21
//				,sphere19
//				,sphere20
//				,sphere22
//				,sphere23
//				,sphere24
//				,sphere25
//				,sphere26
//				,sphere17
//				,sphere18
				//sphere8
				/*,,,,,,,
				,,,,,,
				,,,,,,,,
				,,
				,, , ,*/ );
		
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
