/**
 * 
 */
package MyANN;

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


import MyANN.Exceptions.*;
import MyANN.GenericMatrix;
import MyANN.FilesProcessing.FileWorker;
import MyANN.PseudoRandomGenerators.MatrixNoiseGenerator;

public class SynapseMatrix {
	
	private GenericMatrix _weights;
	private int[][] _intWeights;
	private int _patternsCount;
	private int _imgWidth;
	private int _imgHeight;
	private int _generalImageSize;
	
	private String _pathToAllFiles = "D:\\Data\\Projects\\Eclipse_Workspace\\Java\\DSPaIP\\LabWork2_Hopfield_ANN\\Perceptron\\files\\";
	
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
	
	public int[] transformMatrixToVector(int[][] imgMatrix) {
		int generalImgSize = _imgWidth*_imgHeight;
		int[] vector = new int [generalImgSize];
		for(int k=0; k<generalImgSize; k++) {
			for(int i=0; i<_imgHeight; i++) {
				for(int j=0; j<_imgWidth; j++) {
					vector[k] = imgMatrix[i][j];
				}
			}
		}
		return vector;
	}
	
	/*
	public int[][] transformVectorToMatrix() {
		
	}
	*/
	
	// learn the ANN
	public void synapsesLearning() throws FileNotFoundException, IOException, RuntimeException {
		// standard image matrix
		int[][] imgMatrix = new int [_imgWidth][_imgHeight];
		//linear view of image data
		int[] imgVector = new int [_imgWidth*_imgHeight];
		// calc the general size of img
		int generalImgSize = _imgWidth*_imgHeight;
		//load ideal image set
		/*
		for(int k=0; k<_patternsCount; k++) {
			imgMatrix = FileWorker.readFile(convertFileNumberToFilename(k, ".txt"), _imgWidth, _imgHeight);
			imgVector = transformMatrixToVector(imgMatrix);
			// find weight coef but calc ranged matrix
			for(int i = 0; i < generalImgSize; i++) {
				for(int j = 0; j <= i; j++) {
					if(i == j) {
						_weights.setNeuron(i, j, new Neuron(0, 0, 0));
					}
					else {
						_weights.setNeuron(i, j, new Neuron(imgVector[i]*imgVector[j], 0, 0));
						_weights.setNeuron(j, i, new Neuron(imgVector[i]*imgVector[j], 0, 0));
					}
				}
			}
		}
		*/
		for(int k=0; k<_patternsCount; k++) {
			imgMatrix = FileWorker.readFile(convertFileNumberToFilename(k, ".txt"), _imgWidth, _imgHeight);
			imgVector = transformMatrixToVector(imgMatrix);
			// find weight coef but calc ranged matrix
			for(int i = 0; i < generalImgSize; i++) {
				for(int j = 0; j <= i; j++) {
					
					if(i == j) {
						//_weights.add(i, j, 0);
						_intWeights[i][j] = 0;
					}
					else {
						//_weights.add(i, j, imgVector[i]*imgVector[j]);
						//_weights.add(j, i, imgVector[i]*imgVector[j]);
						_intWeights[i][j] += imgVector[i]*imgVector[j];
						// beacause the matrix is raged
						_intWeights[j][i] += imgVector[i]*imgVector[j];
					}
					
				}
			}
			
		}
		//_weights.convertToIntMatrix(0);
		//transformVectorToMatrix();
		//FileWorker.writeFile(_pathToAllFiles + "weights.txt", _weights.getIntMatrix(), _imgWidth, _imgHeight);
		FileWorker.writeFile(_pathToAllFiles + "weights.txt", _intWeights, _imgWidth, _imgHeight);
		System.out.println("Matrix Weights was written in 'weights.txt' file");
	}
	
	
	
	public SynapseMatrix(int patternsCount, int imgWidth, int imgHeight) throws FileNotFoundException, IOException, RuntimeException {
		startPreparingDataForANN();
		_patternsCount = patternsCount;
		_imgWidth = imgWidth;
		_imgHeight = imgHeight;
		//_generalImageSize = _imgWidth * _imgHeight;
		
		synapsesLearning();
		System.out.println("ANN Learning have been finished");
		
		runSynapsesTraining();
	}
	
	public void runSynapsesTraining() {
		
	}
	
	public void startPreparingDataForANN() throws FileNotFoundException, IOException, RuntimeException {
		
		System.out.println( "Perceptron for the Hopfield ANN is running ..." );
        
        // the count of patterns that ANN have to recognition
        int patternsCount = 36;
        // the width of the image
        //int imgWidth = 550;
        int imgWidth = 64;
        // the height of the image
        //int imgHeight = 550;
        int imgHeight = 64;
        
        // ========================================= run this section of code a one time =========================================
        
        // create FileWorker that processing the source image files and create the Neuron's GenericMatrix 
        //FileWorker fileObj = new FileWorker(patternsCount, imgWidth, imgHeight);
        //fileObj.init();
        /*
        // create GraphicalHandler that processing the source image patterns and "binarized" to each of them
        GraphicalHandler obj = new GraphicalHandler(patternsCount, imgWidth, imgHeight);
        
        // add noise with different percents to all files 
        int percentsOfnoise = 10;
        for(int i=9; i<10; i++) {
        	//
        	percentsOfnoise = (i+1)*10;
	        // create MatrixNoiseGenerator that processing the source image patterns and add "noise" to each of them
	        MatrixNoiseGenerator noiseGen = new MatrixNoiseGenerator(patternsCount, imgWidth, imgHeight, percentsOfnoise);
	        //percentsOfnoise += 10;
        }
        */
        // =======================================================================================================================
        
        // create matrix that have weight coefficients 
        //int generalImageSize = imgWidth*imgHeight;
        _generalImageSize = imgWidth * imgHeight;
        //_weights = new GenericMatrix(generalImageSize, generalImageSize);
        _intWeights = new int [_generalImageSize][_generalImageSize];
	}
}
