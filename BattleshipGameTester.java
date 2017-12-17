//Zac Thamer
public class BattleshipGameTester 
{	
	public static void main (String[]args) throws Exception
	{
		ComputerBattleshipPlayer cplayer = new ComputerBattleshipPlayer();
		int runs = 4;
		PlayerEvaluator pe = new PlayerEvaluator(cplayer, runs);
		System.out.println("Max Turns: " + pe.maxTurns());
		System.out.println("Min Turns: " + pe.minTurns());
		System.out.println("Average Turns: " + pe.averageTurns());
		
		
	}

}
