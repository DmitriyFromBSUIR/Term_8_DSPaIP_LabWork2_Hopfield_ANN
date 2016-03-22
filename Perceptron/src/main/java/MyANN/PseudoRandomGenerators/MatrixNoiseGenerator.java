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

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;

import ImageProcessingByOpenCV.*;

public class MatrixNoiseGenerator {
	private int[][] _matrix;
	
	private int _patternsCount;
	private int _pointsCount;
	//private long _pointsCount;
	private int _percentsOfNoise;
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
		//create vector
		Vector<Point2D> pointsList = new Vector<Point2D>();
		
		
		for(int k=0; k<_pointsCount; k++) {
			//generate the point
			Point2D point = generatePseudoRandomPoints(0, _imgHeight-1, 0, _imgWidth-1);
			
			//check up for unique values in the vector
			int vectSize = pointsList.size();
			
			if(vectSize == 0) {
				pointsList.addElement(point);
			}
			else {
				for(int i=0; i<vectSize; i++) {
					while( pointsList.elementAt(i).isEqual(point.getX(), point.getY(), point.getPointValue()) ) {
						//regenerate the point
						point = generatePseudoRandomPoints(0, _imgHeight-1, 0, _imgWidth-1);
						// check up the vector from the start
						i = 0;
					}
				}
				// add point
				pointsList.addElement(point);
			}
		}
		
		return pointsList;
	}
	
	public Point2D [][] generatePseudoRandomPointsMatrix(int patternNumber) {
		//Vector<Point2D> pointsList = new Vector<Point2D>();
		
		Vector<Point2D> pointsList;
		//for(int i=0; i<_patternsCount; i++) {
			pointsList = generatePseudoRandomPointsList();
			for(int j=0; j<_pointsCount; j++) {
				_noisePointsLists[patternNumber][j] = pointsList.elementAt(j);
			}
		//}
		return _noisePointsLists;
	}
	
	public int inversePointValue(int pointValue) {
		// find inverse val and find the absolute value 
		int invPointVal = Math.abs(~pointValue);
		return invPointVal;
	}
	
	public void addNoiseToImages(int patternNumber) throws FileNotFoundException, IOException, RuntimeException {
		for(int k=0; k<_pointsCount; k++) {
			for(int i=0; i<_imgHeight; i++) {
				for(int j=0; j<_imgWidth; j++) {
					if( (i == _noisePointsLists[patternNumber][k].getX()) && (j == _noisePointsLists[patternNumber][k].getY()) ) {
						_matrix[i][j] = inversePointValue(_noisePointsLists[patternNumber][k].getPointValue());
					}
				}
			}
		}
	}
	
	public void sequencePatternImageFilesProcessing(int patternNumber) throws FileNotFoundException, IOException, RuntimeException {
		//File root = Environment.getExternalStorageDirectory();
		//matrix = Highgui.imread(file.getCanonicalPath());
		
		//create black pixel
		double[] blackPixel = {0.0, 0.0, 0.0};
		//create white pixel
		double[] whitePixel = {255.0, 255.0, 255.0};
		
		System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
		Mat matrix = new Mat(550, 550, CvType.CV_8UC3);
		//Mat binarizatingMatrix = new Mat(550, 550, CvType.CV_8UC1);
		//for(int k=0; k<_patternsCount; k++) {
			matrix = Highgui.imread(convertFileNumberToFilename(patternNumber, ".jpg"));
			int imgWidth = matrix.width();
			int imgHeight = matrix.height();
			for(int i=0; i<imgWidth; i++) {
				for(int j=0; j<imgHeight; j++) {
					/*
					double[] pixel = matrix.get(i, j);
					
					if(pixel[0] == 255) { // if pixel is white
						
					}
					if(pixel[0] == 0) { // if pixel is black
						
					}
					*/
					if(_matrix[i][j] == 1) // if pixel is black
						matrix.put(i, j, blackPixel);
					else // if pixel is white
						matrix.put(i, j, whitePixel);
				}
			}
			//generate the new filepath to img
			String newFilenameForImg = convertFileNumberToFilename(patternNumber, "_with_" + new Integer(_percentsOfNoise).toString() + "_percents_of_noise.jpg");
			//save to image file
			boolean writeResult = Highgui.imwrite(newFilenameForImg, matrix);
			//FileWorker.writeFile(convertFileNumberToFilename(patternNumber, "_noise.jpg"), matrix, imgWidth, imgHeight);
			
		//}
	}
	
	public void run() throws FileNotFoundException, IOException, RuntimeException {
		for(int k=0; k<_patternsCount; k++) {
			String filename = convertFileNumberToFilename(k, ".txt");
			_matrix = FileWorker.readFile(filename, _imgWidth, _imgHeight);
			// create the matrix
			generatePseudoRandomPointsMatrix(k);
			// create noise in the images
			addNoiseToImages(k);
			//create images with noise
			sequencePatternImageFilesProcessing(k);
			//save to txt file
			//String filenameNew = convertFileNumberToFilename(k, "_noise.txt");
			String filenameNew = convertFileNumberToFilename(k, "_with_" + new Integer(_percentsOfNoise).toString() + "_percents_of_noise.txt");
			//
			FileWorker.writeFile(filenameNew, _matrix, _imgWidth, _imgHeight);

		}
	}
	
	public int pointsCountCalc() {
		//int result = (int)( ((double)_percentsOfNoise/100) * (_imgWidth * _imgHeight));
		
		//return (int)( ((double)_percentsOfNoise/100) * (_imgWidth * _imgHeight));
		return (int) Math.round(  ((double)_percentsOfNoise/100) * (_imgWidth * _imgHeight) );
		//return Math.round( (_percentsOfNoise/100) * (_imgWidth * _imgHeight) );
	}
	
	public MatrixNoiseGenerator(int patternsCount, int imgWidth, int imgHeight, int percents) throws FileNotFoundException, IOException, RuntimeException {
		//_matrix = new int[imgWidth][imgHeight];
		_patternsCount = patternsCount;
		_percentsOfNoise = percents;
		_imgWidth = imgWidth;
		_imgHeight = imgHeight;
		// calc the count of points that we must add noise
		_pointsCount = pointsCountCalc();
		// create matrix that have format [patternNumber] --- [noise_points_list]
		_noisePointsLists = new Point2D [_patternsCount][_pointsCount];
		run();
	}
}
