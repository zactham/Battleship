//Zac Thamer
//Position that represents a square on a battleship board
public class Position
{
	private char letter;
	private int index;
	private char[] letterArray = new char [10];
	private int rowIn;
	private int colIn;
	private static boolean myStrat = false;
	{
		letterArray[0] = 'A';
		letterArray[1] = 'B';
		letterArray[2] = 'C';
		letterArray[3] = 'D';
		letterArray[4] = 'E';
		letterArray[5] = 'F';
		letterArray[6] = 'G';
		letterArray[7] = 'H';
		letterArray[8] = 'I';
		letterArray[9] = 'J';
	}

	public static void setMyStrat(boolean s)
	{
		myStrat = s;
	}

	public Position (char posLetter, int posIndex)
	{
		//Erroneous Data
		if (posLetter != 'A' && posLetter != 'B' && posLetter != 'C' && posLetter != 'D' && posLetter != 'E' && posLetter != 'F' && posLetter != 'G' && posLetter != 'H' && posLetter != 'I' && posLetter != 'J')
		{
			System.out.println("You did not put in a letter from A-J");
		}

		////Erroneous Data
		if (posIndex < 0 || posIndex >10)
		{
			System.out.println("You did not put in a index number from 0-9");
		}


		letter = posLetter;
		index = posIndex;

		//Goes through the letterArray to find the index corresponding to the letter
		for (int i = 0; i <10; i++)
		{
			if (letterArray[i]==letter)
			{
				rowIn = i;
			}
		}

		colIn = posIndex-1;

	}

	public Position (int rowIndex, int colIndex)
	{
		if (myStrat)
		{
			letter = letterArray[rowIn];
			rowIn = rowIndex - 1;
			colIn = colIndex - 1;
			index = colIndex - 1;
		}
		else
		{

			rowIn = rowIndex;
			letter = letterArray[rowIn];
			colIn = colIndex;
			index = colIndex;
		}
	}

	//return a character (upper case letter) corresponding to the row represented in the position
	public char getRow()//
	{
		return letter;
	}

	//return an integer corresponding to the column represented in the position
	public int getCol()
	{
		return index;
	}

	//returns a string containing the character for the row, followed by a hyphen (“-“) followed by the number for the column. I.e. B-9
	public String toString ()
	{
		String position = (letter + "-" + index);
		return position;
	}

	//A should correspond to 0, B to 1, etc
	public int getrowIndex()
	{
		return rowIn;
	}

	//index should be one less than the number visible to the player
	public int getcolumnIndex()
	{
		return colIn;
	}
}
