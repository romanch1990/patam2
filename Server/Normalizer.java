package Server;

public class Normalizer {
	public String normalizer(String string[],int row,int column)
	{
		string[1] = "";
		String newstring = "";
		for(char item : string[0].toCharArray())
		{
			if (item == '-')
			{
				newstring += '|';
				string[1] +='1';
			}
			else if (item == '7')
			{
				newstring += 'L';
				string[1] +='2';

			}
			else if (item == 'F')
			{
				newstring += 'L';
				string[1] +='3';

			}
			else if (item == 'J')
			{
				newstring += 'L';
				string[1] +='1';

			}
			else
			{
				string[1]+='0';
				newstring += item;
			}
		}
		return newstring;
	}
}
