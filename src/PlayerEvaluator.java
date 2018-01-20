//Zac Thamer
import java.util.ArrayList;
//determines how effective your computer Battleship player is
public class PlayerEvaluator extends BattleshipPlayer
{
	//private int turns;
	private ArrayList <Integer> turnsList = new ArrayList<Integer>();
	/*
	private int max;  
	private int min;
	private int average;
	 */

	public PlayerEvaluator(ComputerBattleshipPlayer player, int runs, boolean runTestCases) throws Exception
	{
		for (int i = 0; i <runs; i++)
		{
			BattleshipGame game = new BattleshipGame(player, runTestCases);
			int turns =	game.play();
			turnsList.add(turns);
		}
	}

	//This returns the maximum number of turns it took the computer player to sink all the boats in the BattleshipGame.
	public int maxTurns()
	{
		int max = 0;
		for (int i = 0; i < turnsList.size(); i ++)
		{
			if (turnsList.get(i) > max )
			{
				max = turnsList.get(i);
			}
		}
		return max;
	}

	//This returns the minimum number of turns it took the computer player to sink all the boats in the BattleshipGame.
	public int minTurns()
	{
		int min = 999;
		for (int i = 0; i < turnsList.size(); i ++)
		{
			if (min > turnsList.get(i))
			{
				min = turnsList.get(i);
			}
		}
		return min;
	}


	//This returns the average number of turns it took the computer player to sink all the boats in the BattleshipGame.
	public float averageTurns()
	{
		float averageTurns = 0;
		for (int i = 0; i < turnsList.size(); i ++)
		{
			averageTurns+=turnsList.get(i);
		}
		return (averageTurns / turnsList.size());
	}

	
	//////////////////////////////////////////////
	//TODO prints all of the turns per each game
	public void dumpTurns()
	{
		for (int i = 0; i < turnsList.size(); i ++)
			System.out.println(turnsList.get(i));
	}
	
}



