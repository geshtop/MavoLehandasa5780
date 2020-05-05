package unittests;
import static org.junit.Assert.*;

import org.junit.Test;


import renderer.ImageWriter;

public class ImageWriterTest {
	

	    @Test
	    public void renderTest() {
	    	 String imagename = "ourFirstImage";
	    	 int nx = 800;
	    	 int ny = 500;
	         ImageWriter iw = new ImageWriter(imagename, 1600, 1000, nx, ny);
	         for (int col = 0; col < ny; col++) {
	             for (int row = 0; row < nx; row++) {
	                 if (col % 50 == 0 || row % 50 == 0) {
	                	 iw.writePixel(row, col, java.awt.Color.blue);
	                 }
	             }
	         }
	         iw.writeToImage();
	    }
}
