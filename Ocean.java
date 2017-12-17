//Zac Thamer

public class Ocean
{
	private Boat[] myBoats = new Boat[5]; 
	private String[]boatNames = new String [5];
	private int[][] grid = new int[10][10]; 
	private int boatsPlaced = 0;

	public Ocean ()
	{
		init();
	}

	public void init()
	{
		boatsPlaced = 0;
		for(int pos=0;pos<10;pos++)
		{
			for(int pos2=0; pos2<10; pos2++) 
				grid[pos][pos2]=-1;
		}
	}

	//will place each type of boat somewhere on the ocean using the PlaceBoat method you created in the previous assignment
	/*
	if (direction == "horizontal")
	{
		int randomRow = (int) (Math.random()*10)+1;
		while(randomRow >= 10 - myBoats[i].getSize())
		{
			randomRow = (int) (Math.random()*10)+1;
		}
	}

	if (direction == "vertical")
	{
		int randomCol = (int) (Math.random()*10)+1;
		while(randomCol >= 10 - myBoats[i].getSize())
		{
			randomCol = (int) (Math.random()*10)+1;
		}
	}
	 */
	public void placeAllBoats() throws Exception
	{
		init();
		String direction;
		boatNames[0] = "Aircraft Carrier";
		boatNames[1] = "Battleship";
		boatNames[2] = "Cruiser";
		boatNames[3] = "Destroyer";
		boatNames[4] = "Submarine";

		try
		{
			for (int i = 0; i <5; i++)
			{
				//max - min + 1) + 1
				int randomDirection = (int) (Math.random()*2);	
				int randomRow = (int) (Math.random()*10);	// generates a number from 0.0 to 10.0 (less than 10), then drops the decimal to make an int
				int randomCol = (int) (Math.random()*10);

				if (randomDirection == 1)
					direction = "horizontal";
				else
					direction = "vertical";


				Position pos = new Position(randomRow,randomCol);

				placeBoat(boatNames[i], direction, pos);
			}
		}

		catch(Exception e)
		{
			placeAllBoats();
		}
		
	}
/*
	public void dumpGrid()
	{
		for(int pos=0;pos<10;pos++)
			System.out.print("   " + (pos+1));
		System.out.println();
		
		
		for(int pos=0;pos<10;pos++)
		{
			for(int pos2=0; pos2<10; pos2++)
			{
				if (pos2==0)
				{
					char c = (char) ('A' + pos);
					System.out.print(c + " ");
				}
				System.out.format("%02d  ", grid[pos][pos2]);
			}
			System.out.println();
		}
		
	}
	*/


	//place one of the five Battleship boats in a 10x10 ocean
	public void placeBoat(String boatName, String direction, Position pos) throws Exception
	{
		Boat myboat = new Boat(boatName, pos, direction); 

		if(pos.getrowIndex()<0||pos.getcolumnIndex()<0) 
			throw new Exception ("Out of Grid - negative"); 					// fail
		else
		{ 
			if(direction.equalsIgnoreCase("vertical"))
			{	// vertical case
				if(myboat.getSize()+pos.getrowIndex()>10)
					throw new Exception ("Out of Grid - too large vertical");	// fail

				for(int pos1=pos.getrowIndex();pos1<myboat.getSize()+pos.getrowIndex();pos1++) 
				{
					if(grid[pos1][pos.getcolumnIndex()]!=-1) 
						throw new Exception ("Overlapping - vertical");			// fail

				} 
				for(int pos1=pos.getrowIndex();pos1<myboat.getSize() +pos.getrowIndex();pos1++) 
				{
					grid[pos1][pos.getcolumnIndex()]=boatsPlaced;				// succeed
				}
			}
			else 
			{	// horizontal case
				if(myboat.getSize() + pos.getcolumnIndex()>10) 
					throw new Exception ("Out of Grid - too large horizontal"); // fail
				for(int pos1=pos.getcolumnIndex();pos1<myboat.getSize() + pos.getcolumnIndex();pos1++)
				{
					if(grid[pos.getrowIndex()][pos1]!=-1)
						throw new Exception("Overlapping - horizontal");		// fail
				}

				for(int pos1=pos.getcolumnIndex();pos1<myboat.getSize()+ pos.getcolumnIndex();pos1++)
				{
					grid[pos.getrowIndex()][pos1]=boatsPlaced;					// success
				}
			} 

			myBoats[boatsPlaced]=myboat; 
			boatsPlaced++;

			//	System.out.println("Placed boat #" + boatsPlaced + ", direction:" + direction + ", name:" + boatName);
		}
	}


	//shoots at a particular position in the ocean
	//If there is a boat overlapping that position, then Ocean records that the boat has been hit on that spot
	public void shootAt(Position pos)
	{
		if(grid[pos.getrowIndex()][pos.getcolumnIndex()]!=-1) 
		{
			myBoats[grid[pos.getrowIndex()][pos.getcolumnIndex()]].hit(pos);
		}
	}

	//returns true if the position has been shot at and hit, false otherwise
	public boolean hit(Position pos)
	{
		if(grid[pos.getrowIndex()][pos.getcolumnIndex()]!=-1) 
			return myBoats[grid[pos.getrowIndex()][pos.getcolumnIndex()]].isHit(pos); 

		else 
			return false;
	}

	//returns the initial of the boat being hit (‘A’,’B’,’C’,’D’,’S’) (assume it is only called on a hit boat)
	public char boatInitial(Position pos)
	{
		if(grid[pos.getrowIndex()][pos.getcolumnIndex()]!=-1) 
			return myBoats[grid[pos.getrowIndex()][pos.getcolumnIndex()]].getAbbreviation();

		else
			return ' ';
	}

	//returns the name of the boat being hit (assume it is only called on a hit boat)
	public String boatName(Position pos)
	{
		if(grid[pos.getrowIndex()][pos.getcolumnIndex()]!=-1)
			return myBoats[grid[pos.getrowIndex()][pos.getcolumnIndex()]].getName(); 
		else
			return "";
	}

	//returns true if a boat overlapping the position has been sunk, and false otherwise
	public boolean sunk(Position pos)
	{
		if(grid[pos.getrowIndex()][pos.getcolumnIndex()]!=-1) 
			return myBoats[grid[pos.getrowIndex()][pos.getcolumnIndex()]].sunk();
		else 
			return false;
	}

	//function returns true if all five boats have been sunk, and false otherwise
	public boolean allSunk()
	{
		for(int i=0; i<boatsPlaced; i++) 
		{ 
			if(myBoats[i].sunk() == false) 
				return false; 
		} 
		return true;
	}
}