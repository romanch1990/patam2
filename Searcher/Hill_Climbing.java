package Searcher;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class Hill_Climbing<T> extends Standard_Searcher<T> {
	private Queue<State<T>> next;
	private long time = 10000;
	
	public  Hill_Climbing() {
		next = new PriorityQueue<State<T>>((a,b)->Integer.compare(a.hillClimbParm, b.hillClimbParm));
		beenThere = new HashSet<State<T>>();
	}

	@Override
	public Solution search(Searchable<T> searchable) {
		Queue<State<T>> temp;
		State<T> check=searchable.getFirst();
		long Time0 = System.currentTimeMillis();
		while(!next.isEmpty() || System.currentTimeMillis() - Time0 < time)
		{
			beenThere.add(check);
			if(searchable.isGoal(check) != null)
			{
				return searchable.isGoal(check);
			}
			if(Math.random() < 0.7)
			{
				temp = searchable.getAllPosibleStates(check);
				while (!temp.isEmpty())
					if (!beenThere.contains(temp.peek()))
						next.add(temp.remove());
					else temp.remove();
				check = next.remove();
			}
			else
			{
				temp = searchable.getAllPosibleStates(check);
				if (!temp.isEmpty())
					check = temp.remove();
				while (beenThere.contains(check) && (!temp.isEmpty()))
						check = temp.remove();
				while (!temp.isEmpty())
					if (!beenThere .contains(temp.peek()))
						next.add(temp.remove());
					else temp.remove();
			}
		}
		return null;
	}
}
