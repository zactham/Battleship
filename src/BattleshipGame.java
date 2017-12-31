//Zac Thamer
public class BattleshipGame 
{
	private int turns = 0;
	private Ocean oc = new Ocean();
	private BattleshipPlayer p;
	private BattleshipGrid g = new BattleshipGrid();
	//creates an Ocean and initializes the number of turns to zero. It also calls the startGame method for the player
	public BattleshipGame(BattleshipPlayer player) throws Exception
	{
		p = player;
		p.setGrid(g);
		oc.placeAllBoats();
		//oc.dumpGrid();		// FOR TESTING ONLY
		player.startGame();
	}
	//method allows the user to play Battleship. It contains a loop that gets a shot from the player, gets the result back from the ocean and updates the player. 
	//It does this until either the game is over (all ships are sunk), or the user has taken 100 turns. 
	//It does not need to do any checking on whether the position has already been shot at. 
	//It is up to the player to make sure that the same position is not shot at twice
	//returns the number of turns the player took to finish
	public int play()
	{
		boolean tooManyTurns = false;
		boolean gameOver = false;

		while (!gameOver)
		{
			// get position of shot
			System.out.println("\n");
			Position pos = p.shoot();
			if (pos == null)
			{
				System.out.println("Can't find valid pos!");
				break;
			}

			System.out.println("*** Shooting at " + pos);
			// shoot at that position in the ocean
			oc.shootAt(pos);

			// record the shot in the BattleShip grid
			boolean hit = oc.hit(pos);
			char initial = oc.boatInitial(pos);
			g.shotAt(pos, hit, initial);

			String boatName = oc.boatName(pos);
			boolean sunk = oc.sunk(pos);
			turns++;

			// check for end conditions
			if (turns>=100)
				tooManyTurns = true;
			if (oc.allSunk())
			{
				System.out.println("ALL SHIPS SUNK");
				gameOver = true;
			}
			if (tooManyTurns)
			{
				System.out.println("Too many turns");
				gameOver = true;
			}

			p.updatePlayer(pos,  hit,  initial,  boatName,  sunk,  gameOver,  tooManyTurns, turns);
		}
		
		
		return turns;
	}
}
