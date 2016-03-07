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
import org.opencv.core.*;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import MyANN.FilesProcessing.*;

public class GraphicalHandler {
	
	private String _pathToAllFiles = "D:\\Data\\Projects\\Eclipse_Workspace\\Java\\DSPaIP\\LabWork2_Hopfield_ANN\\Perceptron\\files\\";
	private int _patternsCount;
	private int[][] _binarizationMatrix;
	private int _imgWidth;
	private int _imgHeight;
	
	public String getPathToAllFiles() {
		return _pathToAllFiles;
	}
	
	public void setPathToAllFiles(String pathToAllFiles) {
		_pathToAllFiles = pathToAllFiles;
	}
	
	public int getPatternsCount() {
		return _patternsCount;
	}
	
	public void setPatternsCount(int patternsCount) {
		_patternsCount = patternsCount;
	}
	
	public int[][] getBinarizationMatrix() {
		return _binarizationMatrix;
	}
	
	public void setBinarizationMatrix(int[][] binarizationMatrix) {
		_binarizationMatrix = binarizationMatrix;
	}
	
	public int getImageWidth() {
		return _imgWidth;
	}
	
	public void setImageWidth(int imgWidth) {
		_imgWidth = imgWidth;
	}
	
	public int getImageHeight() {
		return _imgHeight;
	}
	
	public void setImageHeight(int imgHeight) {
		_imgHeight = imgHeight;
	}
	
	public GraphicalHandler(int patternsCount, int imgWidth, int imgHeight) throws FileNotFoundException, IOException, RuntimeException {
		_patternsCount = patternsCount;
		_imgWidth = imgWidth;
		_imgHeight = imgHeight;
		_binarizationMatrix = new int [_imgWidth][_imgHeight];
		
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
    		int asciiCode = fileN + 55;
    		String newfilename = new String(_pathToAllFiles + (char)asciiCode + endOfstr);
    		System.out.println("-------------------------------------------------------------------------------------------");
        	System.out.println("Filename: " + newfilename);
        	System.out.println("-------------------------------------------------------------------------------------------");
        	return newfilename;
    	
    	}
	}
	
	void sequencePatternFilesProcessing() throws FileNotFoundException, IOException, RuntimeException {
		//File root = Environment.getExternalStorageDirectory();
		//matrix = Highgui.imread(file.getCanonicalPath());
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		Mat matrix = new Mat(550, 550, CvType.CV_8UC1);
		//Mat binarizatingMatrix = new Mat(550, 550, CvType.CV_8UC1);
		for(int k=0; k<_patternsCount; k++) {
			matrix = Highgui.imread(convertFileNumberToFilename(k, ".jpg"));
			int imgWidth = matrix.width();
			int imgHeight = matrix.height();
			for(int i=0; i<imgWidth; i++) {
				for(int j=0; j<imgHeight; j++) {
					double[] pixel = matrix.get(i, j);
					if(pixel[0] == 255) { // if pixel is white
						//binarizatingMatrix.setTo(new Scalar(1));
						_binarizationMatrix[i][j] = 0;
					}
					if(pixel[0] == 0) { // if pixel is black
						//binarizatingMatrix.setTo(new Scalar(0));
						_binarizationMatrix[i][j] = 1;
					}
				}
			}
			FileWorker.writeFile(convertFileNumberToFilename(k, ".txt"), _binarizationMatrix, imgWidth, imgHeight);
			//Highgui.imwrite(convertFileNumberToFilename(k, "_binarizated.jpg"), matrix);
		}
		//Mat mat = Mat.eye( 3, 3, CvType.CV_8UC1 );
	    //System.out.println( "mat = " + matrix.dump() );
	}
	
	public void run() throws FileNotFoundException, IOException, RuntimeException {
		sequencePatternFilesProcessing();
	}
}
