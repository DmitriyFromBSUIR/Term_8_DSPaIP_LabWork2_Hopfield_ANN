/**
 * 
 */
package ImageProcessingByOpenCV;

/**
 * @author Emperor
 *
 */
public class Point2D {
	
	private int _x;
	private int _y;
	private int _pointValue;
	
	private int[] _pointStruct;
	
	public void setPointValue(int pointValue) {
		_pointValue = pointValue;
	}
	
	public int getPointValue() {
		return _pointValue;
	}
	
	public void setX(int x) {
		_x = x;
	}
	
	public int getX() {
		return _x;
	}
	
	public void setY(int y) {
		_y = y;
	}
	
	public int getY() {
		return _y;
	}

	public Point2D(int x, int y, int pointValue) {
		int[] _pointStruct = new int[3];
		_x = x;
		_y = y;
		_pointValue = pointValue;
		_pointStruct[0] = _x;
		_pointStruct[1] = _y;
		_pointStruct[2] = _pointValue;
	}
	
	boolean isEqual(int x, int y, int pointValue) {
		if( (_x == x) && (_y == y) && (_pointValue == pointValue) ) {
			return true;
		}
		else
			return false;
	}
	
	public int[] getPoint() {
		
		return _pointStruct;
	}
	
	public void setPoint(int x, int y, int pointValue) {
		_pointStruct[0] = x;
		_pointStruct[1] = y;
		_pointStruct[2] = pointValue;
	}
}
