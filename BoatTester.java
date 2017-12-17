//Zac Thamer
public class BoatTester 
{
	public static void main (String[]args)
	{
		//“Aircraft Carrier”, “Battleship”, “Cruiser”, “Destroyer” or “Submarine”


		//Test 1 - Aircraft Carrier
		Position pos1 = new Position ('A',1);
		Position pos12 = new Position ('A', 2);
		Position pos13 = new Position ('A', 3);
		Position pos14 = new Position ('A', 4);
		Position pos15 = new Position ('A', 5);
		Position posonBoat = new Position ('A',3);
		Boat ship = new Boat ("Aircraft Carrier", pos1, "horizontal");
		System.out.println("Name: " + ship.getName());
		System.out.println("Abbreviation: " + ship.getAbbreviation());
		System.out.println("Size: " + ship.getSize());
		System.out.println("Direction: " + ship.getDirection());
		System.out.println("Is this on the boat " + posonBoat + " :" + ship.onBoat(posonBoat));//true 
		ship.hit(pos1);
		ship.hit(pos12);
		ship.hit(pos13);
		ship.hit(pos14);
		ship.hit(pos15);
		System.out.println("Is the boat hit in "+ pos1 +" :" + ship.isHit(pos1));//true
		System.out.println("Boat Sunk: " + ship.sunk());//true
		System.out.println("Boat Starting Position: " + ship.getPosition());


		System.out.println();

		//Test 2 - Battleship
		Position pos2 = new Position ('B',3);
		Position posonBoat2 = new Position ('C',3);
		Boat ship2 = new Boat ("Battleship", pos2, "vertical");
		System.out.println("Name: " + ship2.getName());
		System.out.println("Abbreviation: " + ship2.getAbbreviation());
		System.out.println("Size: " + ship2.getSize());
		System.out.println("Direction: " + ship2.getDirection());
		System.out.println("Is this on the boat: " + posonBoat2 + " :" +  ship2.onBoat(posonBoat2));//true
		ship2.hit(pos2);
		System.out.println("Is the boat hit in "+ posonBoat2 +" :" + ship2.isHit(posonBoat2));//false
		System.out.println("Boat Sunk: " + ship2.sunk());//false
		System.out.println("Boat Starting Position: " + ship2.getPosition());

		System.out.println();

		//Test 3 - Cruiser
		Position pos3 = new Position ('C',4);
		Position pos32 = new Position ('C',5);
		Position pos33 = new Position ('C',6);
		Position posonBoat3 = new Position ('C',4);
		Boat ship3 = new Boat ("Cruiser", pos3, "horizontal");
		System.out.println("Name: " + ship3.getName());
		System.out.println("Abbreviation: " + ship3.getAbbreviation());
		System.out.println("Size: " + ship3.getSize());
		System.out.println("Direction: " + ship3.getDirection());
		System.out.println("Is this on the boat: " + posonBoat3 + " :" +  ship3.onBoat(posonBoat3));//true
		ship3.hit(pos3);
		ship3.hit(pos32);
		ship3.hit(pos33);
		System.out.println("Is the boat hit in "+ pos3 +" :" + ship3.isHit(pos3));//true
		System.out.println("Boat Sunk: " + ship3.sunk());//true
		System.out.println("Boat Starting Position: " + ship3.getPosition());

		System.out.println();

		//Test 4 - Destroyer
		Position pos4 = new Position ('D',5);
		Position posonBoat4 = new Position ('E',6);
		Boat ship4 = new Boat ("Destroyer", pos4, "vertical");
		System.out.println("Name: " + ship4.getName());
		System.out.println("Abbreviation: " + ship4.getAbbreviation());
		System.out.println("Size: " + ship4.getSize());
		System.out.println("Direction: " + ship4.getDirection());
		System.out.println("Is this on the boat: " + posonBoat4 + " :" +  ship4.onBoat(posonBoat4));//false
		ship4.hit(pos4);
		System.out.println("Is the boat hit in "+ pos4 +" :" + ship4.isHit(pos4));// true
		System.out.println("Boat Sunk: " + ship4.sunk());//false
		System.out.println("Boat Starting Position: " + ship4.getPosition());

		System.out.println();

		//Test 5 Submarine
		Position pos5 = new Position ('F',1);
		Position pos52 = new Position ('F',2);
		Position pos53 = new Position ('F',3);
		Position pos5False = new Position ('F',7);
		Position posonBoat5 = new Position ('F',3);
		Boat ship5 = new Boat ("Submarine", pos5, "horizontal");
		System.out.println("Name: " + ship5.getName());
		System.out.println("Abbreviation: " + ship5.getAbbreviation());
		System.out.println("Size: " + ship5.getSize());
		System.out.println("Direction: " + ship5.getDirection());
		System.out.println("Is this on the boat: " + posonBoat5 + " :" + ship5.onBoat(posonBoat5));//true
		ship5.hit(pos5);
		ship5.hit(pos52);
		ship5.hit(pos53);
		System.out.println("Is the boat hit in "+ pos5False +" :" + ship5.isHit(pos5False));//false
		System.out.println("Boat Sunk: " + ship5.sunk());//true
		System.out.println("Boat Starting Position: " + ship5.getPosition());



	}
}
