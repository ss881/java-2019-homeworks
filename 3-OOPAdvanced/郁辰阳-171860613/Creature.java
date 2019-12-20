
public class Creature {
	private int row;
	private int col;
	private String name;
	
	//construction methods
	public Creature() {
		row=0; col=0; name="null";
	}
	public Creature(String input) {
		row=0; col=0; name=input;
	}
	public Creature(int inputRow, int inputCol, String inputName) {
		row=inputRow; col=inputCol;
		name=inputName;
	}
	
	//output method
	int outputRow() {return row;}
	int outputCol() {return col;}
	String outputName() {return name;}
	
}
