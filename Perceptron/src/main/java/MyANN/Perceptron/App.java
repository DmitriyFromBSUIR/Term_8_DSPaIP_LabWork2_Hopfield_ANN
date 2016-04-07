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
    	
    	// the count of patterns that ANN have to recognition
        int patternsCount = 36;
        // the width of the image
        int imgWidth = 550;
        // the height of the image
        int imgHeight = 550;
        //
    	//SynapseMatrix AI_Brain = new SynapseMatrix(patternsCount, imgWidth, imgHeight);
        SynapseMatrix AI_Brain = new SynapseMatrix(patternsCount, 64, 64);
    }
}
