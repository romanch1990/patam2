package Searcher;

import java.util.LinkedList;

public class Solution {
	private LinkedList<String> sols;

	public Solution() {
		this.sols = new LinkedList<String>();
	}
	public void addtosolution(String sol)
	{
		sols.add(sol);
	}
	public String getSols() {
		String str = "";
		for (String string : sols) {
			str+= string;
		}
		return str;
	}

}
