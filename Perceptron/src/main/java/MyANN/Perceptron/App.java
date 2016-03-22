package MyANN.Perceptron;

import java.io.FileNotFoundException;

import java.io.IOException;

/**
 * The main app that to start calculations
 *
 */

import MyANN.*;
import MyANN.Exceptions.*;
import MyANN.FilesProcessing.*;
import MyANN.PseudoRandomGenerators.*;

import ImageProcessingByOpenCV.GraphicalHandler;

public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException, RuntimeException {
    	
        System.out.println( "Perceptron for the Hopfield ANN is running ..." );
        
        // the count of patterns that ANN have to recognition
        int patternsCount = 36;
        // the width of the image
        int imgWidth = 550;
        // the height of the image
        int imgHeight = 550;
        
        // create FileWorker that processing the source image files and create the Neuron's GenericMatrix 
        //FileWorker fileObj = new FileWorker(patternsCount, imgWidth, imgHeight);
        //fileObj.init();
        
        // create GraphicalHandler that processing the source image patterns and "binarized" to each of them
        //GraphicalHandler obj = new GraphicalHandler(patternsCount, imgWidth, imgHeight);
        
        int percentsOfnoise = 10;
        for(int i=0; i<10; i++) {
	        // create MatrixNoiseGenerator that processing the source image patterns and add "noise" to each of them
	        MatrixNoiseGenerator noiseGen = new MatrixNoiseGenerator(patternsCount, imgWidth, imgHeight, percentsOfnoise);
	        percentsOfnoise += 10;
        }
    }
}
