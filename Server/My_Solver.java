package Server;

import Searcher.Searcher;
import Searcher.Searchable;

public class My_Solver <T> implements Solver<T> {

	private Searcher<T> srch;
	
	public My_Solver(Searcher<T> srch) {
		this.srch = srch;
	}
	
	public Searcher<T> getSrch(){
		return srch;
	}
	
	@Override
	public String solve(Searchable<T> srchabl) {
		return srch.search(srchabl).getSols();
	}

}
