/**
 * 
 */
package MyANN.PseudoRandomGenerators;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

/**
 * @author Emperor
 *
 */

import MyANN.FilesProcessing.*;
import org.opencv.core.Point;
import ImageProcessingByOpenCV.*;

public class MatrixNoiseGenerator {
	private int[][] _matrix;
	
	private int _patternsCount;
	private int _pointsCount;
	private int _imgWidth;
	private int _imgHeight;
	private String _pathToAllFiles = "D:\\Data\\Projects\\Eclipse_Workspace\\Java\\DSPaIP\\LabWork2_Hopfield_ANN\\Perceptron\\files\\";
	private Vector<Point2D> _pointsListWithNoise;
	
	public String convertFileNumberToFilename(int fileN, String endFilePath) {
    	String filename;
    	if( (fileN >= 0) && (fileN <= 9) ) {
    		filename = new String(_pathToAllFiles + new Integer(fileN).toString() + endFilePath);
    		System.out.println("-------------------------------------------------------------------------------------------");
        	System.out.println("Filename: " + filename);
        	System.out.println("-------------------------------------------------------------------------------------------");
        	return filename;
    	}
    	else {     		
    		/**
    		 * Hex: 0x0400 (Dec: 1024) - is offset in the Unicode for Russian Symbols
    		 */
    		int asciiCode = fileN + 55;
    		String newfilename = new String(_pathToAllFiles + (char)asciiCode + endFilePath);
    		System.out.println("-------------------------------------------------------------------------------------------");
        	System.out.println("Filename: " + newfilename);
        	System.out.println("-------------------------------------------------------------------------------------------");
        	return newfilename;
    	}
    	
    }
	
	public boolean isPointExistInList(Point2D point) {
		for(int i=0; i<_pointsCount; i++) {
			if(_pointsListWithNoise.get(i) == point) {
				return true;
			}
		}
		return false;
	}
	
	public Vector<Point2D> generatePseudoRandomPoints() {
		
		
	}
	
	public void addNoiseToImages() throws FileNotFoundException, IOException, RuntimeException {
		
	}
	
	public void run() throws FileNotFoundException, IOException, RuntimeException {
		for(int k=0; k<_patternsCount; k++) {
			String filename = convertFileNumberToFilename(k, ".txt");
			_matrix = FileWorker.readFile(filename, _imgWidth, _imgHeight);
			
		}
	}
	
	public MatrixNoiseGenerator(int patternsCount, int imgWidth, int imgHeight, int percents) throws FileNotFoundException, IOException, RuntimeException {
		//_matrix = new int[imgWidth][imgHeight];
		_patternsCount = patternsCount;
		_pointsCount = percents;
		_imgWidth = imgWidth;
		_imgHeight = imgHeight;
		
		run();
	}
}
