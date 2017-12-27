
public class ThamerZacStrategyTester extends ComputerBattleshipPlayer
{
	public static void main (String[] args) throws Exception
	{
		
		//BattleshipPlayer player = new BattleshipPlayer ();
		//BattleshipGame game = new BattleshipGame (player);
		//BattleshipGame game = new BattleshipGame (CPU);
		//game.Play();
		
		ThamerZacStrategy CPU = new ThamerZacStrategy();
		//ComputerBattleshipPlayer CPU = new ComputerBattleshipPlayer();
		
		PlayerEvaluator PE = new PlayerEvaluator(CPU, 1);
		
		System.out.println("Min = "+PE.minTurns());
		System.out.println("Max = "+PE.maxTurns());
		System.out.println("Average = "+PE.averageTurns());
	}
}
