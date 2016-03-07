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
        
        //FileWorker fileObj = new FileWorker(30, 30);
        //fileObj.init();
        
        GraphicalHandler obj = new GraphicalHandler(36, 550, 550);
        
    }
}
