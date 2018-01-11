
public class ThamerZacStrategyTester extends ComputerBattleshipPlayer
{
	public static void main (String[] args) throws Exception
	{

		ThamerZacStrategy CPU = new ThamerZacStrategy();
		//true is for test cases
		// false is run normally
		PlayerEvaluator PE = new PlayerEvaluator(CPU, 50, true);

		System.out.println("Min = "+PE.minTurns());
		System.out.println("Max = "+PE.maxTurns());
		System.out.println("Average = "+PE.averageTurns());

		PE.dumpTurns();
	}
}
