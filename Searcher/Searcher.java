package Searcher;

import Searcher.Searchable;
import Searcher.Solution;

public interface Searcher <T> {
	public Solution search(Searchable<T> mysearchable);
}
