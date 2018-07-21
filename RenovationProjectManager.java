import javax.swing.JOptionPane;

// PMD: No package.  All classes and interfaces must belong to a package.
public class RenovationProjectManager { /*
										 * PMD: Comment is required. It is better to insert a comment at the beginning
										 * of your code, to give a brief description/overview of the program
										 */
	
	public int menuOptions() { // Method for initial menu selection.
		Object[] menuOptions = { "Calculate paint required for a wall", "Calculate paint required for a project",
				"Calculate flooring required for a room", "Calculate flooring required for a project" };
		int result = JOptionPane.showOptionDialog(null, "What would you like to do?", "Your choice!",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, menuOptions, menuOptions[0]);
		return result;
	}
	
	public double getInput(String prompt) { // Method for obtaining wall height.
		double result = Integer.parseInt(JOptionPane.showInputDialog(prompt));
		while (result < 0) {
			result = Integer.parseInt(JOptionPane.showInputDialog("Sorry! Try a positive value.\n" + prompt));
		}
		return result;
	}
	
	public double getArea(double heightOrWidth, double length) {
		return heightOrWidth * length;
	}
	
	public double getCost(double area, double costPer) {
		return area * costPer;
	}
	
	public int addWallOrRoom() { // Method for asking if user would like to add walls.
		Object[] options = { "Yes please!", "No thanks" };
		int result = JOptionPane.showOptionDialog(null, "Would you like to add more?", "Your choice!",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return result;
	}
	
	public void calculatePaintRequiredForAWall() { // If selection == 0...
		double height = getInput("Enter wall height (m)");
		double length = getInput("Enter wall length (m)");
		double wallArea = getArea(height, length);
		double costPerLitre = getInput("Enter cost per litre ($)");
		double cost = getCost(wallArea, costPerLitre);
		JOptionPane.showMessageDialog(null, "The cost to paint a wall of " + wallArea + "sqm is $" + cost);
	}

	public void calculatePaintRequiredForAProject() { // If selection == 1...
		int wallCount = 0;
		int moreWallsLeft = 0;
		double wallArea = 0;
		while (moreWallsLeft == 0) {
			wallCount++;
			double height = getInput("Enter wall " + wallCount + " height (m)");
			double length = getInput("Enter wall " + wallCount + " length (m)");
			wallArea += getArea(height, length);
			moreWallsLeft = addWallOrRoom();
			while (moreWallsLeft < 0 || moreWallsLeft > 1) {
				moreWallsLeft = addWallOrRoom();
			}
		}
		double costPerLitre = getInput("Enter cost per litre ($)");
		double cost = getCost(wallArea, costPerLitre);
		JOptionPane.showMessageDialog(null,
				"The cost to paint " + wallCount + " wall(s) of total area " + wallArea + "sqm is $" + cost);
	}

	public void calculateFlooringRequiredForARoom() { // Made to work like Bunnings flooring calculator
		double width = getInput("Enter room width (m)");
		double length = getInput("Enter room length (m)");
		double floorArea = getArea(width, length);
		double costPerSquareMetre = getInput("Enter cost per square metre ($)");
		double cost = getCost(floorArea, costPerSquareMetre);
		JOptionPane.showMessageDialog(null,
				"The cost of flooring for a room of total area " + floorArea + "sqm is $" + cost);
	}

	public void calculateFlooringRequiredForAProject() {
		double floorArea = 0;
		int roomCount = 0;
		int moreRoomsLeft = 0;
		while (moreRoomsLeft == 0) {
			roomCount++;
			double width = getInput("Enter room " + roomCount + " width (m)");
			double length = getInput("Enter room " + roomCount + " length (m)");
			floorArea += getArea(width, length);
			moreRoomsLeft = addWallOrRoom();
			while (moreRoomsLeft < 0 || moreRoomsLeft > 1) {
				moreRoomsLeft = addWallOrRoom();
			}
		}
		double costPerSquareMetre = getInput ("Enter cost per square metre ($)");
		double cost = getCost(floorArea, costPerSquareMetre);
		JOptionPane.showMessageDialog(null,
				"The cost of flooring " + roomCount + " rooms at " + floorArea + "sqm is $" + cost);
	}

	public void showMenu() {
		// PMD: 'menu' could be final, as it does not change. This won't change the
		// functionality of the code however since it never changes it could be final.
		int inputInt = menuOptions();
		String tempInput = Integer.toString(inputInt);
		int selection = Integer.parseInt(tempInput);
		// PMD: 'selection' could be final, as it does not change.
		if (selection == 0) { // PMD: Avoid using literals in condition statements. We can replace the numbers
		  					  // with variables.
			calculatePaintRequiredForAWall();
			// PMD: Avoid using if/else statements without curly brackets. This is for
			// readability and any future editing. This is because the brackets clearly
			// define the
			// function of the if statement.
		} else if (selection == 1) { // PMD: Avoid using literals in condition statements
			calculatePaintRequiredForAProject();
		} else if (selection == 2) {
			calculateFlooringRequiredForARoom();
		} else {
			calculateFlooringRequiredForAProject();
		}
		inputInt = menuOptions();
	}

	public static void main(String[] args) { // Main method...
		RenovationProjectManager obj = new RenovationProjectManager();
		obj.showMenu();
	}
}
