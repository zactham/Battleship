import java.util.ArrayList;
import java.util.Random;

public class ThamerZacStrategy extends ComputerBattleshipPlayer
{

	private Position prevPos = null;
	
	public Position pos1;
	public Position pos2;
	public Position pos3;
	public Position pos4;

	public boolean shipHit = false;
	public int shiphitCounter = 0;

	public int shiphitRow = 0;
	public int shiphitCol = 0;


	public ArrayList <Position> strategy = new ArrayList <Position>();
	public ArrayList <Position> topMiddleSection = new ArrayList <Position>();
	public ArrayList <Position> bottomMiddleSection = new ArrayList <Position>();
	public ArrayList <Position> topLeftSection = new ArrayList <Position>();
	public ArrayList <Position> topRightSection = new ArrayList <Position>();
	public ArrayList <Position> bottomLeftSection = new ArrayList <Position>();
	public ArrayList <Position> bottomRightSection = new ArrayList <Position>();

	public ArrayList <Position> randomCorners = new ArrayList <Position>();

	public boolean hitSquare = false;	

	public ThamerZacStrategy()
	{
		Position.setMyStrat(true);
		shotSetups();
		afterPath();
		allCornerShots();

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

	//returns computers shot
	public Position shoot()
	{
		Position pos = null;
		Position.setMyStrat(true);
		
		// check for hit at previous position
		if (prevPos != null)
		{			
			if (grid.hit(prevPos))
			{
				System.out.println("There has been a hit");
				shiphitCounter = 1;		// start hit sequence
				shipHit = true;
				shiphitRow = getRow(prevPos);
				shiphitCol = getCol(prevPos);
			}	
		}
		
		if (!shipHit)
		{
			// get next empty pos from strategy list
			while (strategy.size() > 0)
			{
				pos = strategy.get(0);
				if (grid.empty(pos))
					break;
				else
				{
					strategy.remove(0);
					pos = null;
				}
			}		
		
			if (pos == null)
			{
				//The last section of paths, the corner sides of the grid
				if(0 == topMiddleSection.size() && 0 == bottomMiddleSection.size() && randomCorners.size()>0)
				{
					Random r = new Random();
					int num = r.nextInt(randomCorners.size());
					pos = randomCorners.get(num);
					randomCorners.remove(num);
					if (!grid.empty(pos))
						pos = null;
				}
				
				//After the main paths, the top middle and bottom middle sections are randomly hit
				if (pos == null)
				{
					Random r = new Random();
					int num = r.nextInt(2);
					if(bottomMiddleSection.size() == 0)
						num = 0;
					if(topMiddleSection.size() == 0)
						num = 1;
					if(topMiddleSection.size()>0)
					{
						int randomNumTMS = r.nextInt(topMiddleSection.size());
						if (num == 0)
						{
		//					System.out.println(num);
							pos = topMiddleSection.get(randomNumTMS);
							topMiddleSection.remove(randomNumTMS);
							if (!grid.empty(pos))
								pos = null;
						}
					}
					if(bottomMiddleSection.size()>0 && pos == null)
					{
						int randomNumBMS = r.nextInt(bottomMiddleSection.size());
						if (num == 1)
						{
		//					System.out.println(num);
							pos = bottomMiddleSection.get(randomNumBMS);
							bottomMiddleSection.remove(randomNumBMS);
							if (!grid.empty(pos))
								pos = null;
						}
					}
				}
			}
		}
		
		while (shipHit)
		{
			if (pos != null && grid.empty(pos))
				break;
			
			//System.out.println("Checking for hits");
			//After hitting all of the possible squares surrounding one that has been hit it goes back to false
			if (shiphitCounter == 5)
			{
				shipHit = false;
				pos = null;
				break;
			}

			//Checks all of the surrounding squares of the square that has been hit w the ship in it
			if (shiphitCounter == 1)
			{
				if (shiphitRow>9)
					shiphitRow=9;
				pos = new Position (shiphitRow +1, shiphitCol);
				removeAllLists(pos);
			}


			else if (shiphitCounter == 2)
			{
				if (shiphitRow<2)
					shiphitRow=2;
				pos = new Position (shiphitRow -1, shiphitCol);
				removeAllLists(pos);
			}

			else if (shiphitCounter == 3)
			{
				if (shiphitCol>9)
					shiphitCol=9;
				pos = new Position (shiphitRow, shiphitCol+1);
				removeAllLists(pos);
			}

			else if (shiphitCounter == 4)
			{
				if (shiphitCol<2)
					shiphitCol=2;
				pos = new Position (shiphitRow, shiphitCol-1);
				removeAllLists(pos);
			}
			shiphitCounter++;
		} 

		if (pos == null || !grid.empty(pos))
		{
			Random r = new Random();
			do
			{
				pos = new Position(r.nextInt(10) + 1, r.nextInt(10) + 1);
			} while (grid.hasEmpty() && !grid.empty(pos));
		}
		
		prevPos = pos;		// record position for next turn
		Position.setMyStrat(false);
		
		return pos;

	}

	public void removeAllLists(Position pos)
	{
		removePos(pos,strategy);
		removePos(pos,topMiddleSection);
		removePos(pos,bottomMiddleSection);
		removePos(pos,topLeftSection);
		removePos(pos,topRightSection);
		removePos(pos,bottomRightSection);
		removePos(pos,randomCorners);
	}

	public void removePos(Position pos, ArrayList posArray)
	{
		for (int i = 0; i < posArray.size(); i++)
		{
			if (pos == posArray.get(i))
				posArray.remove(i);
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
		strategy.add(new Position('E', 5));		// ignored
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


	}

	public void afterPath()
	{
		//Main Top Middle Section
		for (int i = 2; i<10; i++)
		{
			topMiddleSection.add(new Position(1,i));
		}
		for (int i = 3; i<9; i++)
		{
			topMiddleSection.add(new Position(2,i));
		}
		for (int i = 4; i<8; i++)
		{
			topMiddleSection.add(new Position(3,i));
		}
		for (int i = 5; i<7; i++)
		{
			topMiddleSection.add(new Position(4,i));
		}
		//System.out.println(topMiddleSection.size());

		//Main Bottom Middle Section
		for (int i = 5; i<7; i++)
		{
			bottomMiddleSection.add(new Position(7,i));
		}
		for (int i = 4; i<8; i++)
		{
			bottomMiddleSection.add(new Position(8,i));
		}
		for (int i = 3; i<9; i++)
		{
			bottomMiddleSection.add(new Position(9,i));
		}
		for (int i = 2; i<10; i++)
		{
			bottomMiddleSection.add(new Position(10,i));
		}



		//All side square positions
		topLeftSection.add(new Position (2, 1));
		for(int i = 1; i<3; i++)
		{
			topLeftSection.add(new Position (3, i));
		}

		for(int i = 1; i<4; i++)
		{
			topLeftSection.add(new Position (4, i));
		}



		topRightSection.add(new Position (2, 10));
		for(int i = 9; i<11; i++)
		{
			topRightSection.add(new Position (3, i));
		}

		for(int i = 8; i<11; i++)
		{
			topRightSection.add(new Position (4, i));
		}


		bottomLeftSection.add(new Position (9, 1));
		for(int i = 1; i<3; i++)
		{
			bottomLeftSection.add(new Position (8, i));
		}

		for(int i = 1; i<4; i++)
		{
			bottomLeftSection.add(new Position (7, i));
		}


		bottomRightSection.add(new Position (9, 10));
		for(int i = 9; i<11; i++)
		{
			bottomRightSection.add(new Position (8, i));
		}

		for(int i = 8; i<11; i++)
		{
			bottomRightSection.add(new Position (7, i));
		}
	}

	public void allCornerShots()
	{
		for(int i = 0; i< 6; i++)
		{
			randomCorners.add(topLeftSection.get(i));
			randomCorners.add(topRightSection.get(i));
			randomCorners.add(bottomLeftSection.get(i));
			randomCorners.add(bottomRightSection.get(i));

		}
	}

	public Position initialShot()
	{
		return pos1;
	}

	//The startGame method needs to be updated so that it does not ask for the human playerï¿½s name.
	public void startGame()
	{
		System.out.println("BATTLESHIP");
		System.out.println();	
	}


}


