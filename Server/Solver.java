package Server;

import Searcher.Searchable;

public interface Solver<T> {
	public String solve(Searchable<T> searchable);
}
