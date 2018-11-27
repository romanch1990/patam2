package Searcher;

import java.util.HashSet;

import Searcher.Searcher;
import Searcher.State;

public abstract class Standard_Searcher<T> implements Searcher<T> {

	protected HashSet<State<T>> beenThere;
}
