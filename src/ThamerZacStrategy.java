import java.util.ArrayList;
import java.util.Random;

public class ThamerZacStrategy extends ComputerBattleshipPlayer
{


	public Position pos1;
	public Position pos2;
	public Position pos3;
	public Position pos4;

	public boolean shipHit = false;
	public int shiphitCounter = 0;
	public int topsectionshotCounter = 0;
	public int bottomsectionshotCounter = 0;

	public int topleftsectionshotCounter = 0;
	public int toprightsectionshotCounter = 0;
	public int bottomrightsectionshotCounter = 0;
	public int bottomleftsectionshotCounter = 0;

	public int shiphitRow = 0;
	public int shiphitCol = 0;


	public ArrayList <Position> strategy = new ArrayList <Position>();
	public ArrayList <Position> topMiddleSection = new ArrayList <Position>();
	public ArrayList <Position> bottomMiddleSection = new ArrayList <Position>();
	public ArrayList <Position> topLeftSection = new ArrayList <Position>();
	public ArrayList <Position> topRightSection = new ArrayList <Position>();
	public ArrayList <Position> bottomLeftSection = new ArrayList <Position>();
	public ArrayList <Position> bottomRightSection = new ArrayList <Position>();



	public int shotCounter = 1;
	public boolean hitSquare = false;
	public BattleshipGrid grid = new BattleshipGrid ();

	public ThamerZacStrategy()
	{
		Position.setMyStrat(true);
		shotSetups();
		afterPath();
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
		if(shotCounter<37)
		{
			pos = strategy.get(shotCounter);
		}
		Position.setMyStrat(true);

	
		//The last section of paths, the corner sides of the grid
		if(topsectionshotCounter >=topMiddleSection.size() && bottomsectionshotCounter >= bottomMiddleSection.size())
		{
			Random r = new Random();
			int num = r.nextInt(0)+3;
			if (num == 0)
			{
				pos = topLeftSection.get(topleftsectionshotCounter);
				topleftsectionshotCounter++;

			}
			else if (num == 1)
			{
				pos = topRightSection.get(toprightsectionshotCounter);
				toprightsectionshotCounter++;
			}
			else if (num == 2)
			{
				pos = bottomRightSection.get(bottomrightsectionshotCounter);
				bottomrightsectionshotCounter++;
			}
			else if (num == 3)
			{
				pos = bottomLeftSection.get(bottomleftsectionshotCounter);
				bottomleftsectionshotCounter++;
			}
		}

		//After the main paths, the top middle and bottom middle sections are randomly hit
		if (shotCounter >= strategy.size())
		{
			Random r = new Random();
			int num = r.nextInt(1);
			if(topMiddleSection.size()>0)
			{
				int randomNumTMS = r.nextInt(topMiddleSection.size());
				if (num == 0)
				{
					pos = topMiddleSection.get(randomNumTMS);
					topMiddleSection.remove(randomNumTMS);
					topsectionshotCounter++;

				}
			}
			if(bottomMiddleSection.size()>0)
			{
				int randomNumBMS = r.nextInt(bottomMiddleSection.size());
				if (num == 1)
				{
					pos = bottomMiddleSection.get(randomNumBMS);
					bottomMiddleSection.remove(randomNumBMS);
					bottomsectionshotCounter++;
				}


			}
		}
		else
			shotCounter++;
		
		 /*
		{
			//System.out.println("Checking for hits");
			//After hitting all of the possible squares surrounding one that has been hit it goes back to false
			if (shiphitCounter == 5)
				shipHit = false;

			//Checks all of the surrounding squares of the square that has been hit w the ship in it
			if (shipHit)
			{
				if (shiphitCounter == 1)
				{
					pos = new Position (shiphitRow +1, shiphitCol);
					removePos(pos,strategy);
					removePos(pos,topMiddleSection);
					removePos(pos,bottomMiddleSection);
					removePos(pos,topLeftSection);
					removePos(pos,topRightSection);
					removePos(pos,bottomRightSection);

				}


				else if (shiphitCounter == 2)
				{
					pos = new Position (shiphitRow -1, shiphitCol);
					removePos(pos,strategy);
					removePos(pos,topMiddleSection);
					removePos(pos,bottomMiddleSection);
					removePos(pos,topLeftSection);
					removePos(pos,topRightSection);
					removePos(pos,bottomRightSection);
				}

				else if (shiphitCounter == 3)
				{
					pos = new Position (shiphitRow, shiphitCol+1);
					removePos(pos,strategy);
					removePos(pos,topMiddleSection);
					removePos(pos,bottomMiddleSection);
					removePos(pos,topLeftSection);
					removePos(pos,topRightSection);
					removePos(pos,bottomRightSection);
				}

				else if (shiphitCounter == 4)
				{
					pos = new Position (shiphitRow, shiphitCol+1);
					removePos(pos,strategy);
					removePos(pos,topMiddleSection);
					removePos(pos,bottomMiddleSection);
					removePos(pos,topLeftSection);
					removePos(pos,topRightSection);
					removePos(pos,bottomRightSection);
				}
				shiphitCounter++;

			}



			//If the pos has a ship and is hit
			if (grid.hit(pos)&&!shipHit)
			{
				System.out.println("There has been a hit");
				shipHit = true;
				shiphitCounter = 1;
				shiphitRow = getRow(pos);
				shiphitCol = getCol(pos);
			}
		}
*/
		pos.setMyStrat(false);
		return pos;

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
		strategy.add(new Position('E', 5));
		strategy.add(new Position('E', 5));
		strategy.add(new Position('E', 6));
		strategy.add(new Position('F', 6));
		strategy.add(new Position('F', 5));

		//Red Path
		for (int i = 4; i > 0; i--)
		{
			strategy.add(new Position(i, i));
		}


		//Green Path
		int r = 4;
		for (int z = 7; z < 11; z++)
		{
			strategy.add(new Position(r, z));
			r--;
		}

		//Dark Red Path
		for (int i = 7; i < 11; i++)
		{
			strategy.add(new Position(i, i));
		}

		//Pink Path
		int r2 = 7;
		for (int i = 4; i > 0; i--)
		{
			strategy.add(new Position(r2, i));
			r2++; 
		}


		//Brown Path
		for (int i = 1; i< 5; i++)
		{
			strategy.add(new Position(5, i));
		}

		//Bright Yellow Path
		for (int i = 7; i < 11; i++)
		{
			strategy.add(new Position(5, i));
		}

		//Dark Yellow Path
		for (int i = 7; i < 11; i++)
		{
			strategy.add(new Position(6, i));
		}

		//Grey Path
		for (int i = 1; i < 5; i++)
		{
			strategy.add(new Position(6, i));
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


