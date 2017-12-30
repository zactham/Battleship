//Zac Thamer

public class BattleshipGrid
{
	/*
	HIT - The player has shot at this position and has hit a ship overlapping it. In this case the grid will record the initial of the ship that has been hit.

	MISS - The player has shot at this position and found that no ship overlaps it.

	EMPTY - The player has not yet shot at this position.

	 */

	public BattleshipGrid()
	{
		for (int i = 0; i< 10; i++)
		{
			for (int z = 0; z<10; z++)
			{
				shipCondition[i][z] = "empty";
			}
		}
	}

	private char[][] grid = new char[10][10]; // stores ship initials
	private String[][]shipCondition = new String [10][10]; //stores hit miss empty
	boolean shotAt = false;

	public void dumpShipConditions()
	{
		for(int r=0;r<10;r++)
		{
			for(int c=0;c<10;c++)
			{
				System.out.print(shipCondition[r][c] + "  ");
			}
			System.out.println("");
		}				
	}
	
	//called when a player has shot at a position
	//Hit is a Boolean that is true if the shot was a hit and false if it was a miss
	//Initial is the initial (A,B,C,D or S) of the ship that was hit. This value is ignored if hit is false
	public void shotAt(Position pos,boolean hit,char initial)
	{
		int row = pos.getrowIndex();
		int column = pos.getcolumnIndex();

		if (hit)
		{
			System.out.println("setting hit on " + row + "," + column);
			shipCondition[row][column] = "hit";
			grid [row][column] = initial;
		}
		else
			shipCondition[row][column] = "miss";
	}

	//returns true if the position has been shot at and is a hit, false otherwise
	public boolean hit(Position pos)
	{
		int row = pos.getrowIndex();
		int column = pos.getcolumnIndex();

		if(shipCondition[row][column].equalsIgnoreCase("hit")) 
			return true; 
		else 
			return false;
	}

	//returns true if the position has been shot at and is a miss, false otherwise
	public boolean miss(Position pos)
	{
		int row = pos.getrowIndex();
		int column = pos.getcolumnIndex();

		if (shipCondition[row][column].equals("miss"))
			return true;
		else
			return false;
	}

	//method returns true if the position has not been shot at
	public boolean empty(Position pos)
	{
		int row = pos.getrowIndex();
		int column = pos.getcolumnIndex();

		if (shipCondition[row][column].equals("empty"))
			return true;
		else
			return false;
	}

	//should only be called if the position has been shot at and is a hit. It returns the initial of the boat that has been hit
	public char boatInitial(Position pos)
	{
		int row = pos.getrowIndex();
		int column = pos.getcolumnIndex();

		return grid[row][column];
	}
	
	public boolean hasEmpty()
	{
		for (int i = 0; i< 10; i++)
		{
			for (int z = 0; z<10; z++)
			{
				if (shipCondition[i][z] == "empty")
					return true;
			}
		}
		return false;
	}
	

}
