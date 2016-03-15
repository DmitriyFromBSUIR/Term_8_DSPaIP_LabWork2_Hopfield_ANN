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
	// lists that have "noise" points for all files 
	private Point2D [][] _noisePointsLists;
	
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
	
	public Point2D generatePseudoRandomPoints(int minVal1, int maxVal1, int minVal2, int maxVal2) {
		//return Math.random() * (maxVal - minVal) + minVal; 
		
		// generate x index
		int indX = (int) (Math.floor(Math.random() * (maxVal1 - minVal1 + 1)) + minVal1); 
		// generate y index
		int indY = (int) (Math.floor(Math.random() * (maxVal2 - minVal2 + 1)) + minVal2);
		// search point value by matrix 
		int pointVal = 0;
		/*
		for(int i=0; i<_imgHeight; i++) {
			for(int j=0; j<_imgWidth; j++) {
				if( (i == indX) && (j == indY) )
					pointVal = _matrix[i][j];
			}
		}
		*/
		pointVal = _matrix[indX][indY];
		// create Point2D
		Point2D point = new Point2D(indX, indY, pointVal);
		
		return point;
	}
	
	public Vector<Point2D> generatePseudoRandomPointsList() {
		//generate the point
		Point2D point = generatePseudoRandomPoints(0, _imgHeight, 0, _imgWidth);
		//create vector
		Vector<Point2D> pointsList = new Vector<Point2D>();
		//check up for unique values in the vector
		int vectSize = pointsList.size();
		if(vectSize == 0) {
			pointsList.addElement(point);
		}
		else {
			for(int i=0; i<vectSize; i++) {
				while( pointsList.elementAt(i).isEqual(point.getX(), point.getY(), point.getPointValue()) ) {
					//regenerate the point
					point = generatePseudoRandomPoints(0, _imgHeight, 0, _imgWidth);
					// check up the vector from the start
					i = 0;
				}
			}
			// add point
			pointsList.addElement(point);
		}
		return pointsList;
	}
	
	public Point2D [][] generatePseudoRandomPointsMatrix() {
		//Vector<Point2D> pointsList = new Vector<Point2D>();
		Point2D [][] _noisePointsLists = new Point2D [_patternsCount][_pointsCount];
		
		
		return _noisePointsLists;
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
