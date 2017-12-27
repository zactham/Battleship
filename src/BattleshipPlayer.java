//Zac Thamer
import java.util.Scanner;
public class BattleshipPlayer 
{
	Scanner input = new Scanner (System.in);
	private String name;
	public BattleshipGrid grid = null;


	int[] numArray = new int[10];
	char[][] playerArray = new char[11][11];

	public BattleshipPlayer()
	{
		name = null;
	}

	//modifier method is called each time a new game is started. It creates the BattleshipGrid used to keep the user informed.
	//If name is null, it asks the user to enter a name and welcomes the user to the game
	public void startGame()
	{

		if (name == null)
		{
			System.out.println("BATTLESHIP");
			System.out.println();
			System.out.println("Enter your name: ");
			name = input.next();
		}
	}

	//returns the name of the human player
	public String playerName()
	{
		return name;
	}

	// gets a position to shoot at 
	//prompts the user to enter a row (A-J) and a column (1-10) 
	//returns that position. If the user enters an invalid row or column, it prints out an error message and prompts again
	public Position shoot()
	{
		Scanner input = new Scanner (System.in);	

		//Row Input
		System.out.println("Enter a row (A-J): ");
		String rowS = input.nextLine();
		char row = rowS.charAt(0);
		while (! (row == 'A' || row == 'B' ||  row == 'C' || row == 'D' ||  row == 'E' ||  row == 'F' ||  row =='G' ||  row == 'H' ||  row == 'I' ||  row == 'J') )
		{
			System.out.println("Incorrect Input, Enter a row (A-J):  ");
			row = input.nextLine().charAt(0);
		}

		//Column Input
		System.out.println("Enter a column (1-10):  ");
		int col = input.nextInt();
		while (col<1 || col>10)
		{
			System.out.println("Incorrect Input, Enter a column (1-10):  ");
			col = input.nextInt();
		}

		Position pos = new Position (row,col);

		return pos;

	}



	//updates the grid based on the results of a shot
	protected void updateGrid(Position pos, boolean hit, char initial)
	{
		grid.shotAt(pos,hit,initial);
	}

	public void setGrid(BattleshipGrid grid)
	{
		this.grid = grid;
	}

	/*
	This method informs the user of the result of his/her shot, including:

•	Whether it was a hit or miss.
•	Which ship was hit.
•	Whether the ship is sunk.
•	If the game is over.
•	If the game has gone on too long.
•	The number of turns the player has taken so far.

	It also calls the updateGrid method and then outputs the grid as a graphical representation of the state of the game

 This output should have the following characteristics:

•	The columns should be labeled with the numbers 1-10
•	The rows should be labeled with the letters A-J
•	Denote a square that has not been shot at with a period (.)
•	Denote a square that has been shot at and is a miss with an asterisk (*)
•	Denote a square that has been shot at and hit with the initial of the boat that has been hit.


	Grid:

   1 2 3 4 5 6 7 8 9 10
A  . . * . . . * . . .
B  . . C . . . . . . .
C  . . C * . * . * . .
D  . . C . . . . . . .
E  . . . * B B B B . .
F  . . . . * . . . . .
G  . * . . . . S . . *
H  . . . . . . * . . .
I  . * . . . * . . . . 
J  . . . . . . * . . .

Turn #21: Your shot at H-7 was a miss.

	 */

	public void updatePlayer(Position pos, boolean hit, char initial, String boatName, boolean sunk, boolean gameOver, boolean tooManyTurns, int turns)
	{
		int row = pos.getrowIndex();
		int col = pos.getcolumnIndex();

		if (turns == 1)
		{
			for (int r = 0; r < 10; r++)
			{
				for (int c = 0; c < 11; c ++)
				{
					playerArray[r+1][c] = '.';
				}

			}
		}



		//Denote a square that has been shot at and hit with the initial of the boat that has been hit.
		if (hit)
			playerArray[row+1][col+1] = initial;
		else
			playerArray[row+1][col+1] = '*';
		/*
		//Denote a square that has been shot at and is a miss with an asterisk (*)
		if (grid.miss(pos))
			playerArray[row+1][col+1] = '*';
		 */

		//Prints the grid numbers at the top
		System.out.print("  ");
		for (int i = 0; i <= 9; i ++)
		{
			numArray[i]= i+1;
			System.out.print(numArray[i] + " ");
		}

		//First Column
		playerArray[1][0] = 'A';
		playerArray[2][0] = 'B';
		playerArray[3][0] = 'C';
		playerArray[4][0] = 'D';
		playerArray[5][0] = 'E';
		playerArray[6][0] = 'F';
		playerArray[7][0] = 'G';
		playerArray[8][0] = 'H';
		playerArray[9][0] = 'I';
		playerArray[10][0] = 'J';

		for (int r = 0; r < 11; r++)
		{
			for (int c = 0; c < 11; c ++)
			{
				System.out.print(playerArray[r][c] + " ");
			}
			System.out.println();
		}

		//Turn #21: Your shot at H-7 was a miss.
		System.out.println();
		if (gameOver)
			System.out.println("Turn #" + turns + ": Game Over ");
		else
			if (tooManyTurns)
				System.out.println("Turn #" + turns + ": There have been too many turns");
			else
				if (sunk)
					System.out.println("Turn #" + turns + ": " + boatName +" was sunk");
				else	
					if (hit)
						System.out.println("Turn #" + turns + ": Your shot at " + pos +" was a hit.");
					else
						System.out.println("Turn #" + turns + ": Your shot at " + pos +" was a miss.");

	}
}
