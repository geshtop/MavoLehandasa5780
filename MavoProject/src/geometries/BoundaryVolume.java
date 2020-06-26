package geometries;

import static primitives.Util.alignZero;


import java.lang.Math;
import primitives.Point3D;
import primitives.Ray;

public class BoundaryVolume {
	public Point3D min;
	public Point3D max;
	public BoundaryVolume(Point3D _min, Point3D _max) {
		this.min = _min;
		this.max = _max;

	}
	//פונקציה שמקבלת קרן ומחזירה נוקודת חיתוך עם הקופסה
	public boolean boundingIntersection(Ray ray) {
			
	    Point3D po = ray.get_POO();    
		double dirfra_x =  1.0f / ray.get_direction().get_head().get_x().get();
		double dirfra_y =  1.0f / ray.get_direction().get_head().get_y().get();
		double dirfra_z =  1.0f / ray.get_direction().get_head().get_z().get();


		// lb is the corner of AABB with minimal coordinates - left bottom, rt is maximal corner
		// r.org is origin of ray
		double t1 = (min.get_x().get() - po.get_x().get()) * dirfra_x;
		double t2 = (max.get_x().get() - po.get_x().get()) * dirfra_x;
		double t3 = (min.get_y().get() - po.get_y().get()) * dirfra_y;
		double t4 = (max.get_y().get() - po.get_y().get()) * dirfra_y;
		double t5 = (min.get_z().get() - po.get_z().get()) * dirfra_z;
		double t6 = (max.get_z().get() - po.get_z().get()) * dirfra_z;


		double tmin = Math.max(Math.max(Math.min(t1, t2), Math.min(t3, t4)), Math.min(t5, t6));
		double tmax = Math.min(Math.min(Math.max(t1, t2), Math.max(t3, t4)), Math.max(t5, t6));

		// if tmax < 0, ray (line) is intersecting AABB, but the whole AABB is behind us
		if (tmax < 0)
		{
		    return false;
		}
		// if tmin > tmax, ray doesn't intersect AABB
		if (tmin > tmax)
		{
		    return false;
		}
		return true;
		
	}

	
	/***
	 * 
	 * @param g1  group of geometries
	 * @param g2  group of geometries
	 * @return the distance between boxes
	 */
	public static double getDistance(Intersectable g1 , Intersectable g2) {
		BoundaryVolume box1 = g1.boundaryVolume();
		BoundaryVolume box2 = g2.boundaryVolume();
		if(doOverlap(g1,g2)) return 0;
		double dis1 = box1.min.distance(box2.min);
		double dis2 = box1.min.distance(box2.max);
		double dis3 = box1.max.distance(box2.min);
		
		double dis4 = box1.max.distance(box2.max);
		double min_disatnce = dis1;
		if(dis2 < min_disatnce) min_disatnce = dis2;
		if(dis3 < min_disatnce) min_disatnce = dis3;
		if(dis4 < min_disatnce) min_disatnce = dis4;
		
		System.out.println("The distance between [(" + box1.min.toString() +"),(" + box1.max.toString()+ ")] and [(" +  box2.min.toString() +"),(" + box2.max.toString() + ")] is " + min_disatnce ); 
		
		return min_disatnce;
		
	}
	/***
	 * 
	 * @param g1 group of geometries
	 * @param g2 group of geometries
	 * @return boolean if the 2 groups boundary are overlap
	 */
	public static boolean doOverlap(Intersectable g1 , Intersectable g2 )  
	{ 
		BoundaryVolume box1 = g1.boundaryVolume();
		BoundaryVolume box2 = g2.boundaryVolume();

		// gives bottom-left point 
	    // of intersection rectangle 
	    double x5  = Math.max(box1.min.get_x().get(), box2.min.get_x().get()); 
	    double y5  = Math.max(box1.min.get_y().get(), box2.min.get_y().get()); 
	  
	    // gives top-right point 
	    // of intersection rectangle 
	    double x6  = Math.min(box1.max.get_x().get(), box2.max.get_x().get()); 
	    double y6  = Math.min(box1.max.get_y().get(), box2.max.get_y().get()); 

	  
	    // no intersection 
	    if (x5 > x6 || y5 > y6)  
	    { 
	        System.out.println("No intersection"); 
	        return false; 
	    } 
	    
	    System.out.println("Has intersection"); 
		return true; 
	} 

	

}
