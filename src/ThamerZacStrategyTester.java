
public class ThamerZacStrategyTester extends ComputerBattleshipPlayer
{
	public static void main (String[] args) throws Exception
	{
		
		ThamerZacStrategy CPU = new ThamerZacStrategy();
	
		PlayerEvaluator PE = new PlayerEvaluator(CPU, 50);
		
		System.out.println("Min = "+PE.minTurns());
		System.out.println("Max = "+PE.maxTurns());
		System.out.println("Average = "+PE.averageTurns());
	}
}
