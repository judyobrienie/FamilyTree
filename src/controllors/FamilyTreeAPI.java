package controllors;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import utils.Serializer;



public class FamilyTreeAPI  {

	private Serializer serializer;

    public Map<String, Person> familytree = new TreeMap<>();
    
   //creating an instance of node
    Node node  = new Node();

    public FamilyTreeAPI(Serializer serializer)throws Exception {

		this.serializer = serializer; 

	}

    /**
	 * @param load from xml file 
	 */
	@SuppressWarnings("unchecked")
	public void load() throws Exception
	{
		serializer.read();
		familytree = (Map<String, Person>) serializer.pop();
		

	}

	/**
	 * @param save to xml file

	 */


	public void store() throws Exception
	{

		serializer.push(familytree);
		serializer.write(); 


	}

	
	
	/**
	 *@param loadDefaultfiles external files with familytree data
	 *@return populated   Person
	 */

	public void loadDefaultFiles() throws FileNotFoundException{

		File usersFile = new File("../FamilyTree/familytree.txt");
		Scanner inUsers = new Scanner(usersFile);
		String delims = " ";//each field in the file is separated(delimited) by a space.
		while (inUsers.hasNextLine()) {
			// get name and meaning from data source
			String userDetails = inUsers.nextLine();
			userDetails=userDetails.trim();

			// parse user details string
			String[] userTokens = userDetails.split(delims);
			

			Person person = new Person(userTokens[0],userTokens[1], Integer.parseInt(userTokens[2]));
			familytree.put((userTokens[0]),person);
			
		}
			 inUsers.close();
		
		
		//traversing the file a second time to add parents
		Scanner inUser = new Scanner(usersFile);
		String delim = " ";//each field in the file is separated(delimited) by a space.
		while (inUser.hasNextLine()) {
			// get name and meaning from data source
			String userDetail = inUser.nextLine();
			userDetail=userDetail.trim();

			// parse user details string
			String[] userToken = userDetail.split(delims);
			String motherTemp = userToken[3];
			Person mother = familytree.get(motherTemp);
			String fatherTemp = userToken[4];
			Person father = familytree.get(fatherTemp);
			//adding to map
					familytree.get(userToken[0]).setMother(mother);
					familytree.get(userToken[0]).setFather(father);
			
		}
		inUser.close();
		
		 
          
	} //end of loadDefaultFile
} //end of FamilyTreeApi


