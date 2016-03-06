/**
 * 
 */
package ImageProcessingByOpenCV;

/**
 * @author Emperor
 *
 */

import java.io.*;
//import java.nio.*;
import java.nio.channels.ShutdownChannelGroupException;

import org.opencv.highgui.*;
import org.opencv.features2d.*;
import org.opencv.imgproc.*;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class GraphicalHandler {
	
	private String _pathToAllFiles = "D:\\Data\\Projects\\Eclipse_Workspace\\Java\\DSPaIP\\LabWork2_Hopfield_ANN\\Perceptron\\files\\";
	
	public GraphicalHandler() {
		run();
	}
	
	public String convertFileNumberToFilename(int fileN, String endOfstr) {
    	String filename;
    	if( (fileN >= 0) && (fileN <= 9) ) {
    		filename = new String(_pathToAllFiles + new Integer(fileN).toString() + endOfstr);
    		System.out.println("-------------------------------------------------------------------------------------------");
        	System.out.println("Filename: " + filename);
        	System.out.println("-------------------------------------------------------------------------------------------");
        	return filename;
    	}
    	else {
    		/**
    		 * Hex: 0x0400 (Dec: 1024) - is offset in the Unicode for Russian Symbols
    		 */
    		//int asciiCode = fileN + 1030;
    		// Hex: 0x0041 (Dec: 65) - is offset in the Unicode
    		int asciiCode = fileN + 65;
    		String newfilename = new String(_pathToAllFiles + (char)asciiCode + endOfstr);
    		System.out.println("-------------------------------------------------------------------------------------------");
        	System.out.println("Filename: " + newfilename);
        	System.out.println("-------------------------------------------------------------------------------------------");
        	return newfilename;
    	
    	}
	}
	
	public void run() {
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		//File root = Environment.getExternalStorageDirectory();
	    //File file = new File("D:\\Data\\Projects\\Eclipse_Workspace\\Java\\DSPaIP\\LabWork2_Hopfield_ANN\\Perceptron\\files\\В.jpg");
		Mat matrix = new Mat(550, 550, CvType.CV_8UC1);
		//Mat matrix = Mat.eye(550, 550, CvType.CV_8UC1);
	    //Mat matrix = new Mat();
		//matrix = Highgui.imread(file.getCanonicalPath());
		int channels = matrix.channels();
	    for(int i=0; i<2; i++) {
	    	double[] pixel = matrix.get(0, 0);
	    	for(int j=0; j<pixel.length; j++) {
	    		System.out.println("\n pixel = " + pixel[j]);
	    	}
	    	System.out.println("\nChannels = " + channels);
	    	//matrix = Highgui.imread(convertFileNumberToFilename(i, ".jpg"));
	    	Highgui.imread()
	    	matrix = Highgui.imread("D:\\Data\\Projects\\Eclipse_Workspace\\Java\\DSPaIP\\LabWork2_Hopfield_ANN\\Perceptron\\files\\A.jpg");
	    	System.out.println( "mat = " + matrix.dump() );
	    	//Highgui.imwrite(convertFileNumberToFilename(i, "_new.jpg"), matrix);
	    	Highgui.imwrite("D:\\Data\\Projects\\Eclipse_Workspace\\Java\\DSPaIP\\LabWork2_Hopfield_ANN\\Perceptron\\files\\A_new.jpg", matrix);
	    }
		
	    //Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1 );
	    //System.out.println( "mat = " + matrix.dump() );
	}
}
