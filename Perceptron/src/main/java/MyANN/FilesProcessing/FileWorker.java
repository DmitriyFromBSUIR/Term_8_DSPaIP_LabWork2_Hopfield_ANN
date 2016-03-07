/**
 * 
 */
package MyANN.FilesProcessing;

/**
 * @author Emperor
 *
 */

import java.io.*;
import java.util.*;
import java.nio.*;
import java.nio.charset.*;
import MyANN.Exceptions.*;
import MyANN.GenericMatrix;

public class FileWorker {
	
	/**
	 * 
	 */
	static File _file;
	
	/**
	 * 
	 */
	private GenericMatrix _matrix;
	
	/**
	 * 
	 */
	long _mHeight;
	
	/**
	 * 
	 */
	long _mWidth;
	
	/**
	 * 
	 */
	private String _filepathToSrcMatrix;
	
	/**
	 * 
	 */
	private String _filepathToDstMatrix;
	
	/**
	 * 
	 */
	private String _filepathToStartMatrix;
	
	private String _pathToAllFiles = "D:\\Data\\Projects\\Eclipse_Workspace\\Java\\DSPaIP\\LabWork2_Hopfield_ANN\\Perceptron\\files\\";
	
	private int _patternsCount;
	
	//
	public FileWorker() {
		_mHeight = 0;
		_mWidth = 0;
	}
	
	public FileWorker(int patternsCount, int matrixHeight, int matrixWidth) {
		_mHeight = matrixHeight;
		_mWidth = matrixWidth;
		_patternsCount = patternsCount;
		_matrix = new GenericMatrix(matrixHeight, matrixWidth);
		int[] neuronData = new int[4];
        createMatrix(neuronData);
	}
	
	public FileWorker(String filepath, int matrixHeight, int matrixWidth) throws IOException {
        _file.createNewFile();
        _mHeight = matrixHeight;
		_mHeight = matrixWidth;
        _matrix = new GenericMatrix(matrixHeight, matrixWidth);
        int[] neuronData = new int[4];
        createMatrix(neuronData);
    }
	
	private static void exists(String fileName) throws FileNotFoundException {
	    File file = new File(fileName);
	    if (!file.exists()){
	        throw new FileNotFoundException(file.getName());
	    }
	}
	
	public static void WriteToFile(String fileName, String text) throws IOException {
	    //Определяем файл
	    //File file = new File(fileName);
		
	 
	    try {
	        //проверяем, что если файл не существует то создаем его
	        if(!_file.exists()){
	            _file.createNewFile();
	        }
	 
	        //PrintWriter обеспечит возможности записи в файл
	        PrintWriter out = new PrintWriter(_file.getAbsoluteFile());
	 
	        try {
	            //Записываем текст в файл
	            out.print(text);
	        } finally {
	            //После чего мы должны закрыть файл
	            //Иначе файл не запишется
	            out.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public static String ReadFromFile(String fileName) throws IOException, FileNotFoundException {
	    //Этот спец. объект для построения строки
	    StringBuilder sb = new StringBuilder();
	 
	    exists(fileName);
	 
	    try {
	        //Объект для чтения файла в буфер
	        BufferedReader in = new BufferedReader(new FileReader( _file.getAbsoluteFile()));
	        try {
	            //В цикле построчно считываем файл
	            String s;
	            while ((s = in.readLine()) != null) {
	                sb.append(s);
	                sb.append("\n");
	            }
	        } finally {
	            //Также не забываем закрыть файл
	            in.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	 
	    //Возвращаем полученный текст с файла
	    return sb.toString();
	}
	
	private void createMatrix(int neuronData[]) {
		for (int i=0; i < _mHeight; i++) {
            for (int j=0; j < _mWidth; j++) {
            	_matrix.setNeuronData(i, j, neuronData);
            }
		}
    }
	
	public void metrixOutpput(int neuronOption) throws RuntimeException {
		System.out.println("\n==========================================================================");
        System.out.println("       					[DEBUG] Matrix Printing");
        System.out.println("\n==========================================================================");
        switch(neuronOption) {
        case 0: {
        	System.out.println(" -- Neuron Data [" + neuronOption + "] = 'Matrix of weights'");
        	break;
        }
        case 1: {
        	System.out.println(" -- Neuron Data [" + neuronOption + "] = 'Matrix of dendrites'");
        	break;
        }
        case 2: {
        	System.out.println(" -- Neuron Data [" + neuronOption + "] = 'Matrix of axons'");
        	break;
        }
        case 3: {
        	System.out.println(" -- Neuron Data [" + neuronOption + "] = 'Matrix of connecting link signal strength'");
        	break;
        }
        default:
        	throw new RuntimeException("Error! Incorrect parameter in the function 'metrixOutput'");
        }
        
        int[] neuronData = new int[4];
        
        for (int i=0; i < _mHeight; i++) {
            for (int j=0; j < _mWidth; j++) {
            	neuronData = _matrix.getNeuronData(i, j);
                System.out.print(neuronData[neuronOption] + " ");
            }
            System.out.print("\n");
        }
	}
    
    public void writeFile(String filename, int neuronOption) throws IOException, RuntimeException {
    	FileWriter filewriter = new FileWriter(new File(filename));
    	
    	if( (neuronOption >= 0) && (neuronOption <= 3) ) {
	        
	        int neuronData[] = new int[4];
	 
	        for (int i=0; i < _mHeight; i++) {
	            for (int j=0; j < _mWidth; j++) {
	            	neuronData = _matrix.getNeuronData(i, j);
	                filewriter.write(neuronData[neuronOption] + " ");
	            }
	        }
	        filewriter.flush();
	        filewriter.close();
    	}
    	else {
    		filewriter.close();
    		throw new RuntimeException("Error! Incorrect parameter in the function 'writeFile'");
    	}
    }
    
    public void readFile(String filename, int neuronOption) throws FileNotFoundException, IOException, RuntimeException {
    	File file = new File(filename);
    	Scanner scannerfile = new Scanner(file);
    	
    	if( (neuronOption >= 0) && (neuronOption <= 3) ) {
	    	
	    	int[] neuronData = new int[4];
	    	
	        
	        for (int i=0; i < _mHeight; i++) {
	            for (int j=0; j < _mWidth; j++) {
	                if(scannerfile.hasNextInt()) {
	                	neuronData[neuronOption] = scannerfile.nextInt();
	                	_matrix.setNeuronData(i, j, neuronData);
	                }
	            }
	 
	        }
	        scannerfile.close();
    	}
    	else {
    		scannerfile.close();
    		throw new RuntimeException("Error! Incorrect parameter in the function 'readFile'");
    	}
        
    }
    
    public static void writeFile(String filename, int[][] matrix, int imgWidth, int imgHeight) throws FileNotFoundException, IOException, RuntimeException {
    	FileWriter filewriter = new FileWriter(new File(filename));
    	
    	if( (imgWidth > 0) && (imgHeight > 0) ) {
	 
	        for (int i=0; i < imgHeight; i++) {
	            for (int j=0; j < imgWidth; j++) {
	                filewriter.write(matrix[i][j] + " ");
	            }
	            filewriter.write("\n");
	        }
	        filewriter.flush();
	        filewriter.close();
    	}
    	else {
    		filewriter.close();
    		throw new RuntimeException("Error! Incorrect parameter in the function 'writeFile'");
    	}
    }
    
    public static int[][] readFile(String filename, int imgWidth, int imgHeight) throws FileNotFoundException, IOException, RuntimeException {
    	File file = new File(filename);
    	Scanner scannerfile = new Scanner(file);
    	
    	int[][] matrixData = new int[imgWidth][imgHeight];
    	
    	if( (imgWidth > 0) && (imgHeight > 0) ) {
	        
	        for (int i=0; i < imgHeight; i++) {
	            for (int j=0; j < imgWidth; j++) {
	                if(scannerfile.hasNextInt()) {
	                	matrixData[i][j] = scannerfile.nextInt();
	                }
	            }
	 
	        }
	        scannerfile.close();
    	}
    	else {
    		scannerfile.close();
    		throw new RuntimeException("Error! Incorrect parameter in the function 'readFile'");
    	}
        
    	return matrixData;
    }
    
    public String convertFileNumberToFilename(int fileN) {
    	String filename;
    	if( (fileN >= 0) && (fileN <= 9) ) {
    		filename = new String(_pathToAllFiles + new Integer(fileN).toString() + ".txt");
    		System.out.println("-------------------------------------------------------------------------------------------");
        	System.out.println("Filename: " + filename);
        	System.out.println("-------------------------------------------------------------------------------------------");
        	return filename;
    	}
    	else { 
    		// ASCII_Code(rus "A") = 192
    		//filename = new String(_pathToAllFiles + new Integer(fileN + 182).toString() + ".txt");
    		//filename = new String(_pathToAllFiles + (char)(fileN + 182) + ".txt");
    		//ByteArrayOutputStream baos = new ByteArrayOutputStream();
    		//baos.write(fileN + 182);
    		//Charset cs;
    		//baos.toString(StandardCharsets.US_ASCII);
    		/*
    		int asciiCode = fileN + 182;
    		Integer ascii_code = new Integer((char)asciiCode);
    		//String part2 = new String((new String(ascii_code.toString())).getBytes(StandardCharsets.US_ASCII), "Cp1251");
    		String part2 = new String( new String(((ascii_code.toString()).getBytes(StandardCharsets.US_ASCII)), StandardCharsets.US_ASCII) );
    		String part1 = new String(_pathToAllFiles.getBytes(StandardCharsets.US_ASCII), StandardCharsets.US_ASCII);
    		//String part3 = new String( (".txt").getBytes(), "Cp1251");
    		String part3 = new String( (".txt").getBytes(StandardCharsets.US_ASCII), StandardCharsets.US_ASCII);
    		String newfilename = new String(part1 + part2 + part3); // convert Unicode to Windows 1251 code 
    		*/
    		//filename = new String(_pathToAllFiles + baos.toString("Cp1251") + ".txt"); // convert Unicode to Windows 1251 code 
    		//byte[] asciiCode = (byte)(fileN + 182);
    		//filename = _pathToAllFiles + new String(asciiCode, "Cp1251") + ".txt"; // convert Unicode to Windows 1251 code 
    		
    		/**
    		 * Hex: 0x0400 (Dec: 1024) - is offset in the Unicode for Russian Symbols
    		 */
    		int asciiCode = fileN + 1030;
    		String newfilename = new String(_pathToAllFiles + (char)asciiCode + ".txt");
    		System.out.println("-------------------------------------------------------------------------------------------");
        	System.out.println("Filename: " + newfilename);
        	System.out.println("-------------------------------------------------------------------------------------------");
        	return newfilename;
    	}
    	
    }
    
    public void init() throws FileNotFoundException, IOException, RuntimeException {
    	//FileWriter filewriter = new FileWriter(new File(filename));
    	
    	for(int fileN = 0; fileN < 42; fileN++) {
    		String filename = convertFileNumberToFilename(fileN);
    		FileWriter filewriter = new FileWriter(new File(filename));
    		for (int i=0; i < _mHeight; i++) {
                for (int j=0; j < _mWidth; j++) {
                	filewriter.write(0 + "  ");
                }
                filewriter.write("\n");
        	}
    		filewriter.flush();
            filewriter.close();
    	}
    	
    }
    
}
