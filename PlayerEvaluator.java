//Zac Thamer
import java.util.ArrayList;
//determines how effective your computer Battleship player is
public class PlayerEvaluator 
{
	//private int turns;
	private ArrayList <Integer> turnsList = new ArrayList<Integer>();
	/*
	private int max;  
	private int min;
	private int average;
	 */

	public PlayerEvaluator(ComputerBattleshipPlayer player, int runs) throws Exception
	{
		for (int i = 0; i <runs; i++)
		{
			BattleshipGame game = new BattleshipGame(player);
			int turns =	game.play();
			turnsList.add(turns);
		}
	}

	//This returns the maximum number of turns it took the computer player to sink all the boats in the BattleshipGame.
	public int maxTurns()
	{
		for (int i = 0; i < turnsList.size(); i ++)
		{
			if (turnsList.get(i) > turnsList.get(0) )
			{
				turnsList.set(0, turnsList.get(i));
			}
		}
		return turnsList.get(0);
	}

	//This returns the minimum number of turns it took the computer player to sink all the boats in the BattleshipGame.
	public int minTurns()
	{
		for (int i = 0; i < turnsList.size(); i ++)
		{
			if (turnsList.get(0) > turnsList.get(i))
			{
				turnsList.set(0, turnsList.get(i));
			}
		}
		return turnsList.get(0);
	}


	//This returns the average number of turns it took the computer player to sink all the boats in the BattleshipGame.
	public float averageTurns()
	{
		float averageTurns = 0;
		for (int i = 0; i < turnsList.size(); i ++)
		{
			averageTurns+=turnsList.get(i);
		}
		return averageTurns;
	}
}
