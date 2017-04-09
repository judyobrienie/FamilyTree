package controllors;



import java.io.File;
import java.util.Iterator;
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
	private static String String;

	
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
					f.min();
					//f.allPersons();
					String root = "Johanna";
					Person person = f.familytree.get(root);
					
					f.printTree(person, "indent " );
					
					break;
				case 2:
					System.out.println("Enter the Name: ");
					input.nextLine(); //swallow bug
					String temp = input.next();
					System.out.println("Answer From TreeMap =     " +  f.familytree.get(temp));
					
					break;
					
				case 3:
					System.out.println("Please enter First Name: ");
					input.nextLine(); //swallow bug
					String firstName = input.nextLine();
					boolean goodInput1 = false;
					int age = 0;
					do {
						try {
							System.out.println("Please enter Year of Birth: ");
							age = Integer.parseInt(input.nextLine());
							goodInput1 = true;
						}
						catch (Exception e) {
							System.err.println("Num Expected - you entered text");
						}
					} while (!goodInput1);	
					System.out.println("Please enter Gender  M or F: ");
					String gender = input.nextLine();
					System.out.println("Please enter the Mother: ");
					String mother = input.nextLine();
					System.out.println("Please enter the Father: ");
					String father = input.nextLine();
					

					f.addPerson(firstName, gender, age, mother, father);

					break;
				   
				case 4:
					
					
					f.printChildren();
					System.out.println("\n\n\n\"=======================\n\n\n");
					f.printSiblings();
					
					
				  break;
				  
				case 5:
					System.out.println("\n" + "Current List Of Persons");
					System.out.println("=======================");
					f.allPersons();
					System.out.println("\n");
					System.out.println("Choose a Person to Update" + "\n");
					System.out.println("==>>");
					input.nextLine(); //swallow bug
					String firstName1 = input.nextLine();
					

					boolean goodInput3 = false;
					int age1 = 0;
					do {
						try {
							System.out.println("Please enter Year of Birth: ");
							age1 = Integer.parseInt(input.nextLine());
							goodInput3 = true;
						}
						catch (Exception e) {
							System.err.println("Num Expected - you entered text");
						}
					} while (!goodInput3);	
					
					System.out.println("Please enter Gender  M or F: ");
					String gender1 = input.nextLine();
					
					System.out.println("Please enter the Mother: ");
					String mother1 = input.nextLine();
					
					System.out.println("Please enter the Father: ");
					String father1 = input.nextLine();
					
					

					f.updatePerson(firstName1, gender1, age1, mother1, father1);
				
				break;
				
				case 6:
					
					System.out.println("\n" + "Current List Of Persons");
					System.out.println("=======================");
					f.allPersons();
					System.out.println("\n");
					System.out.println("Choose a Person to Remove" + "\n");
					System.out.println("==>>");
					input.nextLine(); //swallow bug
					String removeName = input.nextLine();
					f.familytree.remove(removeName);
					f.allPersons();
					f.addChildren();
					f.addSiblings();
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
    System.out.println("4)Print Children & Siblings");
    System.out.println("5)Update a Person");
    System.out.println("6)Remove a Person");
    
	


	System.out.println("0) Exit");
	System.out.print("==>>");

int option = input.nextInt();
	return option;
}








}//end of main


