import java.util.Scanner;

//Zac Thamer
public class BattleshipGame 
{
	private int turns = 0;
	private Ocean oc = new Ocean();
	private BattleshipPlayer p;
	private BattleshipGrid g = new BattleshipGrid();
	private int testCase = 0;
	//creates an Ocean and initializes the number of turns to zero. It also calls the startGame method for the player
	/*
	 * "Aircraft Carrier"; Length: 5
		"Battleship"; Length: 4
		"Cruiser"; Length: 3 light blue
		"Submarine"; Length: 3
		"Destroyer"; Length: 2
	 */
	public BattleshipGame(BattleshipPlayer player, boolean runTestCases) throws Exception
	{
		Scanner input = new Scanner (System.in);
		if(runTestCases)
		{
			System.out.println("Enter a test case");
			testCase = input.nextInt();
		}
		else
		{
			oc.placeAllBoats();
		}
		p = player;
		p.setGrid(g);
		if (testCase == 0)
			oc.placeAllBoats();
		else if (testCase == 1)
		{
			///////////////////////////////////////// TEST CASE 1/////////////////////////////////////////////////
			Position ac1 = new Position (0,2);
			oc.placeBoat("Aircraft Carrier", "horizontal", ac1);
			Position bs1 = new Position (6,9);
			oc.placeBoat("Battleship", "vertical", bs1);
			Position cs1 = new Position (3,0);
			oc.placeBoat("Cruiser", "vertical", cs1);
			Position sub1 = new Position (9,1);
			oc.placeBoat("Submarine", "horizontal", sub1);
			Position des1 = new Position (3,9);
			oc.placeBoat("Destroyer", "vertical", des1);

		}
		else if (testCase == 2)
		{
			///////////////////////////////////////// TEST CASE 2/////////////////////////////////////////////////
			Position ac2 = new Position (0,0);
			oc.placeBoat("Aircraft Carrier", "vertical", ac2);
			Position bs2 = new Position (6,9);
			oc.placeBoat("Battleship", "vertical", bs2);
			Position cs2 = new Position (4,4);
			oc.placeBoat("Cruiser", "horizontal", cs2);
			Position sub2 = new Position (9,0);
			oc.placeBoat("Submarine", "horizontal", sub2);
			Position des2 = new Position (0,9);
			oc.placeBoat("Destroyer", "vertical", des2);
		}
		else if (testCase == 3)
		{
			///////////////////////////////////////// TEST CASE 3/////////////////////////////////////////////////
			Position ac3 = new Position (2,2);
			oc.placeBoat("Aircraft Carrier", "vertical", ac3);
			Position bs3 = new Position (2,3);
			oc.placeBoat("Battleship", "vertical", bs3);
			Position cs3 = new Position (2,4);
			oc.placeBoat("Cruiser", "vertical", cs3);
			Position sub3 = new Position (2,5);
			oc.placeBoat("Submarine", "vertical", sub3);
			Position des3 = new Position (2,6);
			oc.placeBoat("Destroyer", "vertical", des3);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////
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
