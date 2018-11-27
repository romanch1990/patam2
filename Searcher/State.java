package Searcher;

import java.util.Arrays;

import Searcher.Point;

public class State<T> {
	private T[][] mat;
	private Point point;
	public int hillClimbParm;
	private int rotated;
	private State<T> prev;
	
	@SuppressWarnings("unchecked")
	public State(T[][] mat,int hillClimbParm) 
	{
		this.prev = null;
		this.rotated = 0;
		this.mat = (T[][])new Character[mat.length][mat[0].length];	
		for (int i=0; i<mat.length; i++)
			for (int j=0; j<mat[0].length; j++)
				this.mat[i][j] = mat[i][j];	
		this.hillClimbParm = hillClimbParm;
	}
	
	@SuppressWarnings("unchecked")
	public State(Point point, int rotated, State<T> prev,int hillClimbParm) {
		super();
		this.hillClimbParm = hillClimbParm;
		this.point = new Point((int)point.getX(),(int)point.getY());
		this.rotated = rotated;
		this.prev = prev;
		mat = (T[][])new Character[prev.getLength()][prev.getWidth()];	
		for (int i=0; i<prev.getLength(); i++)
			for (int j=0; j<prev.getWidth(); j++)
				mat[i][j] = prev.getNode(new Point(i, j));
	}
	
	public T getNode(Point point)	
	{
		return mat[(int)point.getX()][(int)point.getY()];
	}
	public void setNode(Point point,T newt) 
	{
		mat[(int)point.getX()][(int)point.getY()]=newt;
	}
	public T[][] getMat() 
	{
		return mat;
	}
	public int getLength() 
	{
		return mat.length;
	}
	public int getWidth() 
	{
		return mat[0].length;
	}
	public State<T> getPrev() {
		return prev;
	}
	public int getRotated() {
		return rotated;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = new Point((int)point.getX(),(int)point.getY());
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State<?> other = (State<?>) obj;
		if (!Arrays.deepEquals(mat, other.mat))
			return false;
		if (point == null) {
			if (other.point != null)
				return false;
		} else if (!point.equals(other.point))
			return false;
		return true;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(mat);
		result = prime * result + ((point == null) ? 0 : point.hashCode());
		return result;
	}
	
}
