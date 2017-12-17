//Zac Thamer
public class PositionTester
{
	public static void main(String[]args)
	{
		//Test 1
		Position blank = new Position ('A',2);
		System.out.println("Row: " + blank.getRow());
		System.out.println("Column: " + blank.getCol());
		System.out.println("Position: " + blank.toString());
		System.out.println("Row Index: " + blank.getrowIndex());
		System.out.println("Column Index: " + blank.getcolumnIndex());

		System.out.println();
		
		//Test 2
		Position blank2 = new Position (1,1);
		System.out.println("Row: " + blank2.getRow());
		System.out.println("Column: " + blank2.getCol());
		System.out.println("Position: " + blank2.toString());
		System.out.println("Row Index: " + blank2.getrowIndex());
		System.out.println("Column Index: " + blank2.getcolumnIndex());

	}
}
