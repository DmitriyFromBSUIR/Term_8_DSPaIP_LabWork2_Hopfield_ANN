/**
 * 
 */
package MyANN;

import java.io.Serializable;
import java.util.*;

import MyANN.Exceptions.MatrixError;
import MyANN.Neuron;

/**
 * Matrix: This class implements a mathematical matrix.  Matrix
 * math is very important to neural network processing.  Many
 * of the classes developed in this project will make use of the
 * matrix classes in this package.
 *  
 * @author Emperor
 * @version 
 */
//public class GenericMatrix<PrimitiveBoxingType> implements Cloneable, Serializable {
public class GenericMatrix implements Cloneable, Serializable {

	/**
	 * Serial id for this class.
	 */
	private static final long serialVersionUID = -7977897210426471675L;
	
	/**
	 * 
	 */
	private Vector<Vector<Neuron>> _neurons;
	//
	private int[][] _neuronsIntVals;
	private long _rowsCount;
	private long _colsCount;
	//private long _intMatrix[][];
	//private double _doubleMatrix[][];
	
	public long getRowsCount() {
		return _rowsCount;
	}
	
	public void setRowsCount(long newRowsCount) {
		_rowsCount = newRowsCount;
	}
	
	public long getColsCount() {
		return _colsCount;
	}
	
	public void setColsCount(long newColsCount) {
		_colsCount = newColsCount;
	}
	
	public Vector<Vector<Neuron>> getNeuroMatrix() {
		return _neurons;
	}
	
	public void setNeuroMatrix(Vector<Vector<Neuron>> newNeuroMatrix) {
		_neurons = newNeuroMatrix;
	}
	
	public GenericMatrix() {
		_neurons = null;
		_rowsCount = 0;
		_colsCount = 0;
	}
	
	public void MatrixMemoryAllocate(int rows, int cols) {
		_neurons = new Vector<Vector<Neuron>>(rows);
		for(int i=0; i < rows; i++) {
			Vector<Neuron> connectedVect = new Vector<Neuron>(cols);
			for(int j=0; j < cols; j++) {
				//connectedVect.setElementAt(new Neuron(0, 0, 0), j);
				connectedVect.add(j, new Neuron(0, 0, 0));
			}
			_neurons.add(i, connectedVect);
		}
	}
	
	public static Vector<Vector<Neuron>> MatrixMemoryAllocate(Vector<Vector<Neuron>> matrix, int rows, int cols) {
		matrix = new Vector<Vector<Neuron>>(rows);
		
		for(int i=0; i < rows; i++) {
			Vector<Neuron> connectedVect = new Vector<Neuron>(cols);
			for(int j=0; j < cols; j++) {
				//connectedVect.setElementAt(new Neuron(0, 0, 0), j);
				connectedVect.add(j, new Neuron(0, 0, 0));
			}
			matrix.add(i, connectedVect);
		}
		return matrix;
	}
	
	public GenericMatrix(int rows, int cols) {
		MatrixMemoryAllocate(rows, cols);
		_rowsCount = rows;
		_colsCount = cols;
	}
	
	public GenericMatrix(Vector<Vector<Neuron>> neuroMatrix) {
		_neurons = neuroMatrix;
		_rowsCount = neuroMatrix.size();
		_colsCount = neuroMatrix.elementAt(0).size();
	}
	
	public GenericMatrix(Vector<Vector<Neuron>> neuroMatrix, boolean isColVector) {
		_rowsCount = neuroMatrix.size();
		_colsCount = neuroMatrix.elementAt(0).size();
		
		if(isColVector) {
			MatrixMemoryAllocate(neuroMatrix.size(), 1);
		}
		else {
			MatrixMemoryAllocate(1, neuroMatrix.elementAt(0).size());
		}
	}
	
	public GenericMatrix(final int sourceMatrix[][], int neuronsParameterNumber) {
		//this.matrix = new double[sourceMatrix.length][sourceMatrix[0].length];
		//this._neurons = new Vector<Vector<Neuron>>(sourceMatrix.length, sourceMatrix[0].length);
		//_neurons = new Vector<Vector<Neuron>>(sourceMatrix.length, sourceMatrix[0].length);
		MatrixMemoryAllocate(sourceMatrix.length, sourceMatrix[0].length);
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				//this.set(row, col, sourceMatrix[row][col]);
				switch(neuronsParameterNumber) {
				case 0: {
					// set the value of neron's weight
					_neurons.elementAt(row).setElementAt(new Neuron(sourceMatrix[row][col], 0, 0, 0), col);
					break;
				}
				case 1: {
					// set the value of neron's dendrite
					_neurons.elementAt(row).setElementAt(new Neuron(0, sourceMatrix[row][col], 0, 0), col);
					break;
				}
				case 2: {
					// set the value of neron's axon
					_neurons.elementAt(row).setElementAt(new Neuron(0, 0, sourceMatrix[row][col], 0), col);
					break;
				}
				case 3: {
					// set the value of neron's connecting link signal strength
					_neurons.elementAt(row).setElementAt(new Neuron(0, 0, 0, sourceMatrix[row][col]), col);
					break;
				}
				default:
					throw new MatrixError("Error! Incorrect Parameter in the constructor 'GenericMatrix (5)'");
				}
			}
		}
	}
	
	public GenericMatrix(final boolean sourceMatrix[][], int neuronsParameterNumber) {
		//this._neurons = new int[sourceMatrix.length][sourceMatrix[0].length];
		//_neurons = new Vector<Vector<Neuron>>(sourceMatrix.length, sourceMatrix[0].length);
		MatrixMemoryAllocate(sourceMatrix.length, sourceMatrix[0].length);
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				switch(neuronsParameterNumber) {
				case 0: {
					if (sourceMatrix[row][col]) {
						//this.set(row, col, 1);
						_neurons.elementAt(row).setElementAt(new Neuron(1, 0, 0, 0), col);
					} else {
						//this.set(row, col, -1);
						_neurons.elementAt(row).setElementAt(new Neuron(-1, 0, 0, 0), col);
					}
					break;
				}
				case 1: {
					if (sourceMatrix[row][col]) {
						//this.set(row, col, 1);
						_neurons.elementAt(row).setElementAt(new Neuron(0, 1, 0, 0), col);
					} else {
						//this.set(row, col, -1);
						_neurons.elementAt(row).setElementAt(new Neuron(0, -1, 0, 0), col);
					}
					break;
				}
				case 2: {
					if (sourceMatrix[row][col]) {
						//this.set(row, col, 1);
						_neurons.elementAt(row).setElementAt(new Neuron(0, 0, 1, 0), col);
					} else {
						//this.set(row, col, -1);
						_neurons.elementAt(row).setElementAt(new Neuron(0, 0, -1, 0), col);
					}
					break;
				}
				case 3: {
					if (sourceMatrix[row][col]) {
						//this.set(row, col, 1);
						_neurons.elementAt(row).setElementAt(new Neuron(0, 0, 0, 1), col);
					} else {
						//this.set(row, col, -1);
						_neurons.elementAt(row).setElementAt(new Neuron(0, 0, 0, -1), col);
					}
					break;
				}
				default:
					throw new MatrixError("Error! Incorrect Parameter in the constructor 'GenericMatrix (6)'"); 
				}
			}
		}
	}

	public static GenericMatrix createColumnMatrix(final Vector<Neuron> input, int neuronsParameterNumber) {
		//final Vector<Vector<Neuron>> colVect = new Vector<Vector<Neuron>>(input.size(), 1);
		Vector<Vector<Neuron>> colVect = new Vector<Vector<Neuron>>();
		colVect = GenericMatrix.MatrixMemoryAllocate(colVect, input.size(), 1);
		long vectSize = colVect.size();
		for (int row = 0; row < vectSize; row++) {
			//colVect[row][0] = input[row];
			switch(neuronsParameterNumber) {
			case 0: {
				colVect.elementAt(row).setElementAt(new Neuron(input.elementAt(row).getWeight(), 0, 0, 0), 0);
				break;
			}
			case 1: {
				colVect.elementAt(row).setElementAt(new Neuron(0, input.elementAt(row).getDendrite(), 0, 0), 0);
				break;
			}
			case 2: {
				colVect.elementAt(row).setElementAt(new Neuron(0, 0, input.elementAt(row).getAxon(), 0), 0);
				break;
			}
			case 3: {
				colVect.elementAt(row).setElementAt(new Neuron(0, 0, 0, input.elementAt(row).getConnectingLinkStrength()), 0);
				break;
			}
			default:
				throw new MatrixError("Error! Incorrect Parameter in the function 'createColumnMatrix'");
			}
				
		}
		return new GenericMatrix(colVect, true);
	}

	public static GenericMatrix createRowMatrix(final Vector<Neuron> input) {
		int vectSize = input.size();
		//final Vector<Vector<Neuron>> rowVect = new Vector<Vector<Neuron>>(1, vectSize);
		Vector<Vector<Neuron>> rowVect = new Vector<Vector<Neuron>>();
		rowVect = GenericMatrix.MatrixMemoryAllocate(rowVect, 1, vectSize);
		System.arraycopy(input, 0, rowVect.elementAt(0), 0, vectSize);
		return new GenericMatrix(rowVect, false);
	}
	
	public void setNeuron(int row, int col, Neuron newNeuron) {
		_neurons.elementAt(row).setElementAt(newNeuron, col);
	}
	
	public Neuron getNeuron(int row, int col) {
		return _neurons.elementAt(row).elementAt(col);
	}
	
	public void convertToIntMatrix(int neuronsParameterNumber) {
		_neuronsIntVals = new int [(int)_rowsCount][(int)_colsCount];
		for(int i=0; i < (int)_rowsCount; i++) {
			for(int j=0; j < (int)_colsCount; j++) {
				switch(neuronsParameterNumber) {
					case 0: {
						_neuronsIntVals[i][j] = getNeuron(i, j).getWeight();
						break;
					}
					case 1: {
						_neuronsIntVals[i][j] = getNeuron(i, j).getDendrite();
						break;
					}
					case 2: {
						_neuronsIntVals[i][j] = getNeuron(i, j).getAxon();
						break;
					}
					case 3: {
						_neuronsIntVals[i][j] = getNeuron(i, j).getConnectingLinkStrength();
						break;
					}
				}
			}
		}
	}
	
	public int[][] getIntMatrix() {
		return _neuronsIntVals;
	}
	
	public void setIntMatrix(int[][] newIntMatrix) {
		_neuronsIntVals = newIntMatrix;
	}

	public void add(final int row, final int col, final int value) {
		validate(row, col); // check up the ranges of matrix
		//final int newValue = get(row, col) + value;
		final int newWeight = _neurons.elementAt(row).elementAt(col).getWeight() + value;
		//set(row, col, newValue);
		_neurons.elementAt(row).elementAt(col).setWeight(newWeight);
	}
	
	public int getRows() {
		return _neurons.size();
	}
	
	public int getCols() {
		return _neurons.elementAt(0).size();
	}

	public void clear() {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				setNeuron(row, col, new Neuron(0, 0, 0));
			}
		}
	}
	
	long getGeneralMatrixSize() {
		return getRows() * getCols();
	}

	@Override
	public GenericMatrix clone() {
		//Vector<Vector<Neuron>> newNeuroMatrix = new Vector<Vector<Neuron>>(getRows(), getCols());
		Vector<Vector<Neuron>> newNeuroMatrix = new Vector<Vector<Neuron>>();
		newNeuroMatrix = GenericMatrix.MatrixMemoryAllocate(newNeuroMatrix, getRows(), getCols());
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				newNeuroMatrix.elementAt(row).setElementAt(_neurons.elementAt(row).elementAt(col) , col);
			}
		}
		return new GenericMatrix(newNeuroMatrix);
	}

	public boolean equals(final GenericMatrix matrix) {

		if(matrix.getGeneralMatrixSize() != getGeneralMatrixSize()) {
			throw new MatrixError("Error! Size of source matrix and new matrix are not equal.");
		}
		
		if (matrix.getRows() != getRows()) {
			throw new MatrixError("Error! Rows of source matrix and new matrix are not equal.");
		}

		if (matrix.getCols() != getCols()) {
			throw new MatrixError("Error! Columns of source matrix and new matrix are not equal.");
		}

		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				if ( !( _neurons.elementAt(row).elementAt(col).equals(matrix.getNeuron(row, col)) ) ) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 
	 * @param array
	 * @param index
	 * @param neuronsParameterNumber - select the data in the neuron structure 
	 * @return The new index after this matrix has been read.
	 */
	public int fromOneDimArray(final int[] arr, int index, int neuronsParameterNumber) {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				switch(neuronsParameterNumber) {
				case 0: {
					_neurons.elementAt(row).setElementAt(new Neuron(arr[index++], 0, 0, 0), index);
					break;
				}
				case 1: {
					_neurons.elementAt(row).setElementAt(new Neuron(0, arr[index++], 0, 0), index);
					break;
				}
				case 2: {
					_neurons.elementAt(row).setElementAt(new Neuron(0, 0, arr[index++], 0), index);
					break;
				}
				case 3: {
					_neurons.elementAt(row).setElementAt(new Neuron(0, 0, 0, arr[index++]), index);
					break;
				}
				default:
					throw new MatrixError("Error! Incorrect Parameter in the function 'fromPackedArray'");
				}
			}
		}

		return index;
	}
	
	public int fromOneDimPackedArray(final Integer[] arr, int index, int neuronsParameterNumber) {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				switch(neuronsParameterNumber) {
				case 0: {
					_neurons.elementAt(row).setElementAt(new Neuron(arr[index++], 0, 0, 0), index);
					break;
				}
				case 1: {
					_neurons.elementAt(row).setElementAt(new Neuron(0, arr[index++], 0, 0), index);
					break;
				}
				case 2: {
					_neurons.elementAt(row).setElementAt(new Neuron(0, 0, arr[index++], 0), index);
					break;
				}
				case 3: {
					_neurons.elementAt(row).setElementAt(new Neuron(0, 0, 0, arr[index++]), index);
					break;
				}
				default:
					throw new MatrixError("Error! Incorrect Parameter in the function 'fromPackedArray'");
				}
			}
		}

		return index;
	}

	public int[] getNeuronData(final int row, final int col) {
		validate(row, col);
		int[] weight_dendrite_axon_clss = new int [4];
		weight_dendrite_axon_clss[0] = _neurons.elementAt(row).elementAt(col).getWeight();
		weight_dendrite_axon_clss[1] = _neurons.elementAt(row).elementAt(col).getDendrite();
		weight_dendrite_axon_clss[2] = _neurons.elementAt(row).elementAt(col).getAxon();
		weight_dendrite_axon_clss[3] = _neurons.elementAt(row).elementAt(col).getConnectingLinkStrength();
		
		return weight_dendrite_axon_clss;
	}
	
	public void setNeuronData(final int row, final int col, int[] weight_dendrite_axon_clss) {
		validate(row, col);

		_neurons.elementAt(row).elementAt(col).setWeight(weight_dendrite_axon_clss[0]);
		_neurons.elementAt(row).elementAt(col).setDendrite(weight_dendrite_axon_clss[1]);
		_neurons.elementAt(row).elementAt(col).setAxon(weight_dendrite_axon_clss[2]);
		_neurons.elementAt(row).elementAt(col).setConnectingLinkStrength(weight_dendrite_axon_clss[3]);
		
	}

	public GenericMatrix getCol(final int col, int neuronsParameterNumber) {
		if (col > getCols()) {
			throw new MatrixError("Can't get column #" + col + " because it does not exist.");
		}

		final int newMatrix[][] = new int [getRows()][1];

		for (int row = 0; row < getRows(); row++) {
			switch(neuronsParameterNumber) {
			case 0: // weight
			{
				newMatrix[row][0] = _neurons.elementAt(row).elementAt(col).getWeight();
				break;
			}
			case 1: // dendrite
			{
				newMatrix[row][0] = _neurons.elementAt(row).elementAt(col).getDendrite();
				break;
			}
			case 2: // axon
			{
				newMatrix[row][0] = _neurons.elementAt(row).elementAt(col).getAxon();
				break;
			}
			case 3: // connectingLinkSignalStrength
			{
				newMatrix[row][0] = _neurons.elementAt(row).elementAt(col).getConnectingLinkStrength();
				break;
			}
			default:
				throw new MatrixError("Error! Incorrect Parameter in the function 'getCol'");
			}
		}

		return new GenericMatrix(newMatrix, neuronsParameterNumber);
	}

	public GenericMatrix getRow(final int row, int neuronsParameterNumber) {
		if (row > getRows()) {
			throw new MatrixError("Can't get row #" + row + " because it does not exist.");
		}

		final int newMatrix[][] = new int [1][getCols()];

		for (int col = 0; col < getCols(); col++) {
			switch(neuronsParameterNumber) {
			case 0: // weight
			{
				newMatrix[0][col] = _neurons.elementAt(row).elementAt(col).getWeight();
				break;
			}
			case 1: // dendrite
			{
				newMatrix[0][col] = _neurons.elementAt(row).elementAt(col).getDendrite();
				break;
			}
			case 2: // axon
			{
				newMatrix[0][col] = _neurons.elementAt(row).elementAt(col).getAxon();
				break;
			}
			case 3: // connectingLinkSignalStrength
			{
				newMatrix[0][col] = _neurons.elementAt(row).elementAt(col).getConnectingLinkStrength();
				break;
			}
			default:
				throw new MatrixError("Error! Incorrect Parameter in the function 'getRow'");
			}
		}

		return new GenericMatrix(newMatrix, neuronsParameterNumber);
	}

	public boolean isVector() {
		if (getRows() == 1) {
			return true;
		} else {
			return getCols() == 1;
		}
	}

	public boolean isNeuroDataEqualToValue(int neuronsParameterNumber, int compValue) {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				switch(neuronsParameterNumber)
				{
				case 0:
				{
					if (_neurons.elementAt(row).elementAt(col).getWeight() != compValue) {
						return false;
					}
					break;
				}
				case 1:
				{
					if (_neurons.elementAt(row).elementAt(col).getDendrite() != compValue) {
						return false;
					}
					break;
				}
				case 2:
				{
					if (_neurons.elementAt(row).elementAt(col).getAxon() != compValue) {
						return false;
					}
					break;
				}
				case 3:
				{
					if (_neurons.elementAt(row).elementAt(col).getConnectingLinkStrength() != compValue) {
						return false;
					}
					break;
				}
				default:
					throw new MatrixError("Error! Incorrect Parameter in the function 'ramdomize'");
				}
			}
		}
		return true;
	}

	//public void ramdomize(final int neuronParameterNumber, final double min, final double max) {
	public void ramdomize(final int neuronParameterNumber, final int min, final int max) {
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				switch(neuronParameterNumber)
				{
				case 0: {
					_neurons.elementAt(row).setElementAt(new Neuron( (int)((Math.random() * (max - min)) + min), 0, 0, 0), col);
					break;
				}
				case 1: {
					_neurons.elementAt(row).setElementAt(new Neuron( 0, (int)((Math.random() * (max - min)) + min), 0, 0), col);
					break;
				}
				case 2: {
					_neurons.elementAt(row).setElementAt(new Neuron( 0, 0, (int)((Math.random() * (max - min)) + min), 0), col);
					break;
				}
				case 3: {
					_neurons.elementAt(row).setElementAt(new Neuron( 0, 0, 0, (int)((Math.random() * (max - min)) + min)), col);
					break;
				}
				default:
					throw new MatrixError("Error! Incorrect Parameter in the function 'ramdomize'");	
				}
				
			}
		}
	}

	public int sumOfNeuronSignals(int neuronsParameterNumber) {
		int result = 0;
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				switch(neuronsParameterNumber) {
				case 0: {
					result += _neurons.elementAt(row).elementAt(col).getWeight();
					break;
				}
				case 1: {
					result += _neurons.elementAt(row).elementAt(col).getDendrite();
					break;
				}
				case 2: {
					result += _neurons.elementAt(row).elementAt(col).getAxon();
					break;
				}
				case 3: {
					result += _neurons.elementAt(row).elementAt(col).getConnectingLinkStrength();
					break;
				}
				default: 
					throw new MatrixError("Error! Incorrect Parameter in the function 'sumOfNeuronSignals'");
				}
			}
		}
		return result;
	}
	
	public int[] toOneDimArray(int neuronsParameterNumber) {
		final int result[] = new int[(int)getGeneralMatrixSize()];

		int index = 0;
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				switch(neuronsParameterNumber) {
				case 0: {
					result[index++] = _neurons.elementAt(row).elementAt(col).getWeight();
					break;
				}
				case 1: {
					result[index++] = _neurons.elementAt(row).elementAt(col).getDendrite();
					break;
				}
				case 2: {
					result[index++] = _neurons.elementAt(row).elementAt(col).getAxon();
					break;
				}
				case 3: {
					result[index++] = _neurons.elementAt(row).elementAt(col).getConnectingLinkStrength();
					break;
				}
				default:
					throw new MatrixError("Error! Incorrect Parameter in the function 'toOneDimArray'");
				}
			}
		}

		return result;
	}

	public Integer[] toOneDimPackedArray(int neuronsParameterNumber) {
		final Integer result[] = new Integer[(int)getGeneralMatrixSize()];

		int index = 0;
		for (int row = 0; row < getRows(); row++) {
			for (int col = 0; col < getCols(); col++) {
				switch(neuronsParameterNumber) {
				case 0: {
					result[index++] = _neurons.elementAt(row).elementAt(col).getWeight();
					break;
				}
				case 1: {
					result[index++] = _neurons.elementAt(row).elementAt(col).getDendrite();
					break;
				}
				case 2: {
					result[index++] = _neurons.elementAt(row).elementAt(col).getAxon();
					break;
				}
				case 3: {
					result[index++] = _neurons.elementAt(row).elementAt(col).getConnectingLinkStrength();
					break;
				}
				default:
					throw new MatrixError("Error! Incorrect Parameter in the function 'toOneDimArray'");
				}
			}
		}

		return result;
	}

	private void validate(final int row, final int col) {
		if ((row >= getRows()) || (row < 0)) {
			throw new MatrixError("The row:" + row + " is out of range:"
					+ getRows());
		}

		if ((col >= getCols()) || (col < 0)) {
			throw new MatrixError("The col:" + col + " is out of range:"
					+ getCols());
		}
	}

}
