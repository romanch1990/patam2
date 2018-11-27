package Searcher;

import java.util.LinkedList;
import java.util.Queue;

import Searcher.Point;
import Searcher.Solution;
import Searcher.State;
import Server.Pipe_Game_Board;

public class Board_Searchable<T> implements Searchable<Character> {

	private static State<Character> first;

	public Board_Searchable(Pipe_Game_Board board) {
		Board_Searchable.first = new State<>(board.getBoard(),0);
		Board_Searchable.first.setPoint(getStart());
	}
	
	@Override
	public State<Character> getFirst() {
		return first;
	}
	
	public Point getEnd()
	{
		for (int i=0; i<first.getLength(); i++)
			for (int j=0; j<first.getWidth(); j++)
				if (first.getNode(new Point(i,j)) == 'g')
					return new Point(i,j);
		return null;
	}

	@Override
	public Solution isGoal(State<Character> corrent) {
		boolean flag = false;
		Point father = corrent.getPoint();
		Character correntchar = (Character)corrent.getNode(corrent.getPoint());
		if (father.getY()-1 >= 0)
		{
			if(( correntchar == '7' || correntchar == '-' || correntchar == 'J'|| correntchar == 's' ) && corrent.getNode(new Point((int)corrent.getPoint().getX(), (int)corrent.getPoint().getY()-1))=='g')
				flag = true;
		}
		if (father.getX()-1 >= 0)
		{
			if(( correntchar == '|' || correntchar == 'L' || correntchar == 'J'|| correntchar == 's' ) && corrent.getNode(new Point((int)corrent.getPoint().getX()-1, (int)corrent.getPoint().getY()))=='g')
				flag = true;
		}	
		if (father.getY()+1 < first.getWidth())
		{
		
			if(( correntchar == 'L' || correntchar == '-' || correntchar == 'F'|| correntchar == 's' ) && corrent.getNode(new Point((int)corrent.getPoint().getX(), (int)corrent.getPoint().getY()+1))=='g')
				flag = true;
		}
		if (father.getX()+1 < first.getLength())
		{
			if(( correntchar == '7' || correntchar == '|' || correntchar == 'F'|| correntchar == 's' ) && corrent.getNode(new Point((int)corrent.getPoint().getX()+1, (int)corrent.getPoint().getY()))=='g')
				flag = true;
		}
		if(flag)
		{
			String lineS = "";
			Solution sol = new Solution();
			State<Character> tempstate = corrent;
			while(tempstate.getPrev()!= null)
			{
				lineS = (int)tempstate.getPoint().getX()+","+(int)tempstate.getPoint().getY()+","+tempstate.getRotated()+"\n";
				tempstate = tempstate.getPrev();
				sol.addtosolution(lineS);
			}
			return sol;
		}

		return null;
	}

	@Override
	public Point getStart() {
		for (int i=0; i<first.getLength(); i++) {
			for (int j=0; j<first.getWidth(); j++) {
				if (first.getNode(new Point(i,j)) == 's')
					return new Point(i,j);
			}
		}
		return null;
	}
	
	public int roatNum(char x) 
	{
		switch(x) 
		{
			case'L':
			case'F':
			case'7':
			case'J':
				return 4;
			case'|':
			case'-':
				return 2;
			default:
				return 0;
		}
	}
	
	public void spin(Point prev,State<Character> current)
	{
		Character thisChar = (Character) current.getNode(prev);
			switch(thisChar) {
			case'L':
				current.setNode(prev, 'F');
				break;
			case'F':
				current.setNode(prev, '7');
				break;
			case'7':
				current.setNode(prev, 'J');
				break;
			case'J':
				current.setNode(prev, 'L');
				break;
			case'|':
				current.setNode(prev, '-');
				break;
			case'-':
				current.setNode(prev, '|');
				break;
			default:
				break;
			}
	}

	@Override
	public Queue<State<Character>> getAllPosibleStates(State<Character> current) {
		Point prev = current.getPoint();
		Queue<State<Character>> nextStates = new LinkedList<>();
		Character stateInfo = (Character) current.getNode(current.getPoint());
		Point nextPoint;
		char verify;
		if (prev.getY()-1 >= 0)
		{
			nextPoint = new Point((int)prev.getX(),(int)prev.getY()-1);
			verify = (Character) current.getNode(nextPoint);
			for(int i = 0;i<roatNum(verify);i++)
			{
				verify = (Character)current.getNode(nextPoint);
				if((verify == 'F' || verify == 'L' || verify == '-' || verify == 'g') && (stateInfo == '-' || stateInfo == 'J' || stateInfo == 's' || stateInfo == '7'))
					nextStates.add(createNewState(nextPoint,i,current));
				spin(nextPoint, current);

			}
		}
		if (prev.getX()-1 >= 0)
		{
			nextPoint = new Point((int)prev.getX()-1,(int)prev.getY());
			verify = (Character)current.getNode(nextPoint);
			for(int i = 0;i<roatNum(verify);i++)
			{	
				verify = (Character)current.getNode(nextPoint);
				if((verify == 'F' || verify == '|' || verify == '7'|| verify == 'g') && (stateInfo == '|' || stateInfo == 'J' || stateInfo == 's' || stateInfo == 'L'))
					nextStates.add(createNewState(nextPoint,i,current));
				spin(nextPoint, current);
			}
		}	
		if (prev.getY()+1 < first.getWidth())
		{
			nextPoint = new Point((int)prev.getX(),(int)prev.getY()+1);
			verify = (Character)current.getNode(nextPoint);
			for(int i = 0;i<roatNum(verify);i++)
			{
				verify = (Character)current.getNode(nextPoint);
				if((verify == 'J' || verify == '-' || verify == '7'|| verify == 'g') && (stateInfo == '-' || stateInfo == 'F' || stateInfo == 's' || stateInfo == 'L'))
					nextStates.add(createNewState(nextPoint,i,current));
				spin(nextPoint, current);
			}
		}
		if (prev.getX()+1 < first.getLength())
		{
			nextPoint = new Point((int)prev.getX()+1,(int)prev.getY());
			verify = (Character)current.getNode(nextPoint);
			for(int i = 0;i<roatNum(verify);i++)
			{
				verify = (Character)current.getNode(nextPoint);
				if((verify == 'J' || verify == '|' || verify == 'L'|| verify == 'g') && (stateInfo == '|' || stateInfo == 'F' || stateInfo == 's' || stateInfo == '7'))
					nextStates.add(createNewState(nextPoint,i,current));
				spin(nextPoint, current);
			}
		}
		return nextStates;
	}
	
	public int getDistance(Point mypoint)
	{
		return (int) Math.abs((getEnd().getX() - mypoint.getX()) + Math.abs((getEnd().getY()-mypoint.getY()))) ;
	}

	@Override
	public State<Character> createNewState(Point point, int rotate, State<Character> prev) {
		State<Character> temp = new State<Character>(point,rotate,prev,getDistance(point));
		temp.setNode(prev.getPoint(), ' ');
		return temp;
	}
}
