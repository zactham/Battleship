//Zac Thamer
import java.util.*;

public class Boat {
	private String ship;// “Aircraft Carrier”, “Battleship”, “Cruiser”,
	// “Destroyer” or “Submarine”
	private Position pos;// instance of the Position class where the boat will
	// start
	private String orientation;// “horizontal” or “vertical” indicating the
	// orientation of the boat
	private ArrayList<Position> hitList = new ArrayList<Position>();

	public Boat(String v, Position location, String orientate) {
		ship = v;
		pos = location;
		orientation = orientate;
	}

	// returns the name of the type of ship
	public String getName() {
		return ship;
	}

	// returns a character, a one letter abbreviation (A,B,C,D or S) for the
	// name of the type of ship
	public char getAbbreviation() {
		return ship.charAt(0);
	}

	// returns the number of squares the ship takes up (5 for Aircraft Carrier,
	// 4 for Battleship, 3 for Cruiser and Submarine, 2 for Destroyer)
	public int getSize() {
		if (ship.equalsIgnoreCase("Aircraft Carrier")) {
			return 5;
		} else if (ship.equalsIgnoreCase("Battleship")) {
			return 4;
		} else if (ship.equalsIgnoreCase("Cruiser")) {
			return 3;
		} else if (ship.equalsIgnoreCase("Submarine")) {
			return 3;
		} else if (ship.equalsIgnoreCase("Destroyer")) {
			return 2;
		}

		return -9;// should never happen
	}

	// returns “horizontal” or “vertical”, the direction of the ship.
	public String getDirection() {
		return orientation;
	}

	// takes an instance of Position as an argument, and returns true if the
	// Position is on the boat and false otherwise
	// p = posOnBoat
	// pos = pos
	public boolean onBoat(Position p) {
		if (getDirection().equals("horizontal")) {
			for (int i = 0; i < getSize(); i++) {
				if (p.getrowIndex() == pos.getrowIndex()
						&& p.getcolumnIndex() == pos.getcolumnIndex() + i) {
					return true;
				}

			}
			return false;
		} else {
			for (int i = 0; i < getSize(); i++) {
				if (p.getcolumnIndex() == pos.getcolumnIndex()
						&& p.getrowIndex() == pos.getrowIndex() + i) {
					return true;
				}
			}
			return false;
		}
	}

	// takes a Position as an argument and returns a boolean, true if the ship
	// has been hit in that place, false otherwise
	public boolean isHit(Position p) {
		if (hitList.contains(p)) {
			return true;
		} else
			return false;
	}

	// takes a Position as an argument. If that argument overlaps the ship, it
	// records the fact that the ship has been hit in that position
	public void hit(Position p) {
		if (onBoat(p) == true) {
			hitList.add(p);
		}
	}

	// returns a Boolean, true if the ship has been hit on all its squares or
	// false otherwise

	public boolean sunk() 
	{
		if (hitList.size() == getSize())
			return true;
		else
			return false;
	}

	// returns an instance of Position, where your boat will start
	public Position getPosition() {
		return pos;
	}

}