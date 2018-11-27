package Searcher;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import Searcher.State;

public class BFS<T> extends Standard_Searcher<T>{

	private Queue<State<T>> next;
	
	public  BFS() {
		next = new LinkedList<State<T>>();
		beenThere = new HashSet<State<T>>();
	}
	
	@Override
	public Solution search(Searchable<T> Searchable) {
		next.add(Searchable.getFirst());
		State<T> check;
		System.out.println("inside BFS search");
		while(!next.isEmpty())
		{
			check = next.remove();
			beenThere.add(check);
			if(Searchable.isGoal(check) != null)
			{
				return Searchable.isGoal(check);
			}
			Queue<State<T>> temp = Searchable.getAllPosibleStates(check);
			while(!temp.isEmpty())
			{
				check = temp.remove();
				if(!beenThere.contains(check))
					next.add(check);
			}
		}
		return null;
	}

}
