public class GameTester
{
	public static void main (String [] args) throws Exception
	{

		BattleshipPlayer player = new BattleshipPlayer ();
		BattleshipGame game = new BattleshipGame (player);
		game.play();

		ComputerBattleshipPlayer player2 = new ComputerBattleshipPlayer ();
		PlayerEvaluator report = new PlayerEvaluator (player2, 50);
		System.out.println(report.minTurns());
		System.out.println(report.maxTurns());
		System.out.println(report.averageTurns());

	}
}


