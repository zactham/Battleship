//Zac Thamer
public class BattleshipPlayerTester 
{
	public static void main (String[]args)
	{
		//Position pos;
		//A,B,C,D or S
		////“Aircraft Carrier”, “Battleship”, “Cruiser”, “Destroyer” or “Submarine”
		char initialA = 'A';
		char initialB = 'B';
		char initialC = 'C';
		char initialD = 'D';
		char initialS = 'S';

		Boolean hitT = true;
		Boolean hitF = false;
		
		Boolean sunkT = true;
		Boolean sunkF = false;
		
		Boolean gameOverT = true;
		Boolean gameOverF = false;
		
		Boolean tooManyTurnsT = true;
		Boolean tooManyTurnsF = false;
		
		int turns = 2;
		

		String boatNameA = "Aircraft Carrier";
		String boatNameB = "Battleship"; 
		String boatNameC = "Cruiser"; 
		String boatNameD = "Destroyer"; 
		String boatNameS = "Submarine"; 

		BattleshipPlayer player1 = new BattleshipPlayer();
		//player1.startGame();
		//player1.playerName();
		Position posShoot = player1.shoot();

		player1.updateGrid(posShoot, hitF, initialB);

		/*
		player1.updateGrid(pos, hitT, initialB);
		player1.updateGrid(pos, hitT, initialC);
		player1.updateGrid(pos, hitT, initialD);
		player1.updateGrid(pos, hitT, initialS);

		player1.updateGrid(pos, hitF, initialA);
		player1.updateGrid(pos, hitF, initialB);
		player1.updateGrid(pos, hitF, initialC);
		player1.updateGrid(pos, hitF, initialD);
		player1.updateGrid(pos, hitF, initialS);
		 */



		player1.updatePlayer(posShoot, hitF, initialB, boatNameB, sunkF, gameOverF, tooManyTurnsF, turns);

	}

}
