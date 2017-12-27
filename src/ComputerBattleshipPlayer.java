//Zac Thamer
public class ComputerBattleshipPlayer extends BattleshipPlayer
{
	//method will no longer need to display information to the human player (since there is none). Instead it should just make a call to UpdateGrid
	public void updatePlayer(Position pos, boolean hit, char initial)
	{
		updateGrid(pos, hit, initial);
	}

	//returns the name of the computer player
	public String playerName()
	{
		return "Computer Player";
	}

	//shoot method will now be substantially different.
	//For the purposes of the ComputerBattleshipPlayer class you will have the computer shoot “randomly.” 
	//It will pick a random position and look in the grid. If that slot on the grid is not empty, it will pick another one. 
	//When it finds a spot on the grid that is empty, it will return that position as its shot
	public Position shoot()
	{
		Position pos;

		do
		{
			//max - min + 1) + 1
			int randomDirection = (int) (Math.random()*2)+1;
			//TODO make this +1
			int randomRow = (int) (Math.random()*10)+0;
			int randomCol = (int) (Math.random()*10)+0;
			/*
			String direction;

			if (randomDirection == 1)
				direction = "horizontal";
			else
				direction = "vertical";
			 */

			pos = new Position (randomRow,randomCol);
		}
		while (!grid.empty(pos));
		return pos;
	}

	//The startGame method needs to be updated so that it does not ask for the human player’s name.
	public void startGame()
	{
		System.out.println("BATTLESHIP");
		System.out.println();	
	}


}
