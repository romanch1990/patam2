package Searcher;

import java.util.HashSet;
import java.util.Queue;
import java.util.Stack;


public class DFS<T> extends Standard_Searcher<T> {

	private Stack<State<T>> next;
	
	public  DFS() {
		next = new Stack<State<T>>();
		beenThere = new HashSet<State<T>>();
	}

	@Override
	public Solution search(Searchable<T> searchable) {
		next.add(searchable.getFirst());
		State<T> check;
		while(!next.isEmpty())
		{
			check = next.pop();
			beenThere.add(check);
			if(searchable.isGoal(check) != null)
			{
				return searchable.isGoal(check);
			}
			Queue<State<T>> temp = searchable.getAllPosibleStates(check);
				while(!temp.isEmpty()) {
					check = temp.remove();
					if(!beenThere.contains(check))
						next.add(check);
				}					
		}
		return null;
	}
}
