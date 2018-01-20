import java.util.ArrayList;
import java.util.Random;

public class ThamerZacStrategy extends ComputerBattleshipPlayer
{
	private Position prevPos = null;
	public boolean shipHit = false;
	public int shiphitCounter = 0;

	public int shiphitRow = 0;
	public int shiphitCol = 0;

	public static boolean shipSunk = false;


	public ArrayList <Position> strategy = new ArrayList <Position>();
	public ArrayList <Position> neighbors = new ArrayList <Position>();

	public ThamerZacStrategy()
	{
		
	}
	
	//If a ship is sunk, it stops checking the neighboring shots because the ship is already sunk
	public static void setSunk(boolean s)
	{
		shipSunk = s;
	}


	//method will no longer need to display information to the human player (since there is none). Instead it should just make a call to UpdateGrid
	public void updatePlayer(Position pos, boolean hit, char initial)
	{
		updateGrid(pos, hit, initial);
	}

	//returns the name of the computer player
	public String playerName()
	{
		return "Zac Thamer";
	}

	private void addNeighbors(int shiphitRow, int shiphitCol)
	{
		Position pos = null;
		int r,c;
		for(shiphitCounter=1; shiphitCounter<5; shiphitCounter++)
		{
			if (shiphitCounter == 1)
			{
				c = shiphitCol;
				r = shiphitRow+1;
				if (r>10)
					r=10;
				pos = new Position (r, c);
			}
			else if (shiphitCounter == 2)
			{
				c = shiphitCol;
				r = shiphitRow-1;
				if (r<1)
					r=1;
				pos = new Position (r, c);
			}
			else if (shiphitCounter == 3)
			{
				c = shiphitCol+1;
				r = shiphitRow;
				if (c>10)
					c=10;
				pos = new Position (r, c);
			}
			else if (shiphitCounter == 4)
			{
				c = shiphitCol-1;
				r = shiphitRow;
				if (c<1)
					c=1;
				pos = new Position (r, c);
			}
			if (pos != null && grid.empty(pos))
			{
				neighbors.add(pos);
			}
		}
	}

	//returns computers shot
	public Position shoot()
	{
		Position pos = null;
		Position.setMyStrat(true);

		if(!shipSunk)
		{
			// check for hit at previous position
			if (prevPos != null && grid.hit(prevPos))
			{
				//			System.out.println("There was a hit last turn");
				shiphitRow = getRow(prevPos);
				shiphitCol = getCol(prevPos);

				// add neighbors to neighborsList
				addNeighbors(shiphitRow, shiphitCol);
			}	

			while (neighbors.size() > 0)
			{
				//			System.out.println("Getting pos from neighbors list");
				pos = neighbors.get(0);
				neighbors.remove(0);
				if (grid.empty(pos))
					break;
				else 
					pos = null;
			}
		}

			if (pos == null)
			{
				// get next empty pos from strategy list
				while (strategy.size() > 0)
				{
					//				System.out.println("Getting pos from strategy list");
					pos = strategy.get(0);
					strategy.remove(0);
					if (grid.empty(pos))
						break;
					else
						pos = null;
				}		
			}

			if (pos == null || !grid.empty(pos))
			{
				//			System.out.println("Getting random");
				Random r = new Random();
				boolean hasNoNeighbors = grid.hasNoNeighbors();
				int row, col;
				do
				{
					if (hasNoNeighbors)
					{
						row = (r.nextInt(8) + 1);
						col = (r.nextInt(8) + 1);
						if (!grid.hasNoNeighbors(row, col))
							continue;					
					}
					else
					{
						row = (r.nextInt(10));		// 0 to 9
						col = (r.nextInt(10));		// 0 to 9
					}				
					pos = new Position(row+1, col+1);
				} 
				while (pos==null || (grid.hasEmpty() && !grid.empty(pos)));
			}
		

		prevPos = pos;		// record position for next turn
		Position.setMyStrat(false);

		if (pos != null)
			removeAllLists(pos);
		return pos;
	}

	public void removeAllLists(Position pos)
	{
		removePos(pos,strategy);
	}

	public void removePos(Position pos, ArrayList<Position> posArray)
	{
		for (int i = 0; i < posArray.size(); i++)
		{
			if (pos.getcolumnIndex() == posArray.get(i).getcolumnIndex() &&
					pos.getrowIndex() == posArray.get(i).getrowIndex() )
			{
				posArray.remove(i);
				i--;
			}
		}
	}

	public int getRow(Position p)
	{
		return p.getrowIndex()+1;
	}

	public int getCol(Position p)
	{
		return p.getcolumnIndex()+1;
	}


	public void shotSetups()
	{
		//Blue Square
		strategy.add(new Position('E', 5));
		strategy.add(new Position('E', 6));
		strategy.add(new Position('F', 6));
		strategy.add(new Position('F', 5));

		//Red Path
		for (int i = 4; i > 0; i--)
		{
			strategy.add(new Position((char)('A'+i-1), i));
		}


		//Green Path
		int r = 4;
		for (int z = 7; z < 11; z++)
		{
			strategy.add(new Position((char)('A'+r-1), z));
			r--;
		}

		//Dark Red Path
		for (int i = 7; i < 11; i++)
		{
			strategy.add(new Position((char)('A'+i-1), i));
		}

		//Pink Path
		int r2 = 7;
		for (int i = 4; i > 0; i--)
		{
			strategy.add(new Position((char)('A'+r2-1), i));
			r2++; 
		}

		/*
		//Brown Path
		for (int i = 1; i< 5; i++)
		{
			strategy.add(new Position((char)('A'+5-1), i));
		}

		//Bright Yellow Path
		for (int i = 7; i < 11; i++)
		{
			strategy.add(new Position((char)('A'+5-1), i));
		}

		//Dark Yellow Path
		for (int i = 7; i < 11; i++)
		{
			strategy.add(new Position((char)('A'+6-1), i));
		}

		//Grey Path
		for (int i = 1; i < 5; i++)
		{
			strategy.add(new Position((char)('A'+6-1), i));
		}
		 */

	}


	//The startGame method needs to be updated so that it does not ask for the human playerÃ¯Â¿Â½s name.
	public void startGame()
	{
		prevPos = null;
		shipHit = false;
		shiphitCounter = 0;
		shipSunk = false;

		shiphitRow = 0;
		shiphitCol = 0;

		strategy = new ArrayList <Position>();
		neighbors = new ArrayList <Position>();

		Position.setMyStrat(true);
		shotSetups();

		System.out.println("BATTLESHIP");
		System.out.println();	

		//TODO prints all of the turns
		//for (int i=0;i<strategy.size();i++)
		//	System.out.println(i + ". " + strategy.get(i));
	}


}


