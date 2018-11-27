package Searcher;

import java.util.Queue;

import Searcher.Point;
import Searcher.Solution;
import Searcher.State;

public interface Searchable<T> {
	public State<T> getFirst();
	 public Solution isGoal(State<T> corrent);
	 public Point getStart();
	 public Queue<State<T>> getAllPosibleStates(State<T> current);
	 public State<T> createNewState(Point mypoint, int rotated, State<T> myfather);
}
