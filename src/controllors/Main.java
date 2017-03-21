package controllors;



import java.io.File;
import java.util.Map;
import java.util.Scanner;


import utils.Serializer;
import utils.XMLSerializer;



/**
 * Creates a new scanner for i/o
 * @param a new File called dictionary.xml
 * @returns an array and hashtree of data
 * @
 */
public class Main {


	private static Scanner input = new Scanner(System.in);

	
	public static void main(String[] args) throws Exception {

		File  familytree = new File("familytree.xml");
		Serializer serializer = new XMLSerializer(familytree);
		FamilyTreeAPI f = new FamilyTreeAPI(serializer); 
		if (familytree.isFile())
		{
			f.load();
		}
		else f.loadDefaultFiles();
	

	/**
	 * Runs the program using i/o from user
	 * 
	 */

	System.out.println("Family Tree");
	System.out.println("-----------------");

	boolean goodInput = false;
	do{
		try{
			int option = mainMenu();
			while (option != 0)
			{
				goodInput = true;

				switch (option)
				{
				case 1:
					
					for(Map.Entry<String,Person> entry : f.familytree.entrySet()) {
						  String key = entry.getKey();
						  Person value = entry.getValue();

						  System.out.println(key + " => " + value);
						}
				
					
					break;
				case 2:
					System.out.println("Enter the Name: ");
					input.nextLine(); //swallow bug
					String temp = input.next();
					System.out.println("Answer From TreeMap =     " +  f.familytree.get(temp));
					
					break;
					
				case 3:
					
				   
				case 4:
					
					
				  break;
				    
				default:    System.out.println("Invalid option entered: " + option);
				mainMenu();
				break;
				}

				//pause the program so that the user can read what we just printed to the terminal window
				System.out.println("\n\nPress any key to continue...");
				input.nextLine();

				//display the main menu again
				option = mainMenu();
				//the user chose option 0, so exit the program
			}
			System.out.println("Exiting... bye");
			f.store();
			System.exit(0);

		}

		catch (Exception e) {
			input.nextLine(); //swallows bug
			System.err.println("Num expected - You Entered Text. Try Again...\n");
		}
	} while (!goodInput);  	




}


/**
 * mainMenu() - This method displays the menu for the application, 
 * reads the menu option that the user entered and returns it.
 * @return     the users menu choice
 */

private static  int mainMenu() {
	System.out.println("\n1)Print out Tree");
	System.out.println("2)Search for a Person");
	System.out.println("3)Add a Person");
    System.out.println("4)Delete a Person");
	


	System.out.println("0) Exit");
	System.out.print("==>>");

int option = input.nextInt();
	return option;
}








}//end of main


