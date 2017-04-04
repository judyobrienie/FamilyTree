package controllors;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
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
			 System.out.println(familytree);
		
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
			String fatherTemp = userToken[4];
			
			//Check to see if mum or dad are already an object of type person if not create one
			Person mum = null;
			if (familytree.get(motherTemp) != null){
				Person mother = familytree.get(motherTemp);
				familytree.get(userToken[0]).setMother(mother);
			}
			else  {mum = new Person(motherTemp);
			familytree.get(userToken[0]).setMother(mum);
			familytree.put(motherTemp, mum);
			}
			
			Person dad = null;
			if (familytree.get(fatherTemp) != null){
				Person father = familytree.get(fatherTemp);
				familytree.get(userToken[0]).setFather(father);
			}
			else  {dad = new Person(fatherTemp);
			familytree.get(userToken[0]).setFather(dad);
			familytree.put(fatherTemp, dad);
			}
			
			
		}
		inUser.close();
       // add children and siblings
	   addChildren();
	   addSiblings();
	} //end of loadDefaultFile
	
	
	
	/**
	 *@param addPerson Create a new instance of Person
	 *@return an updated Hash Map of type Person
	 */

	public void addPerson(String firstName,String gender, int age, String mother, String father) {
		
		Person person = new Person (firstName, gender, age);
		familytree.put(firstName,  person);
		
		
		Person mum = null;
		if (familytree.get(mother) != null){
			Person mothers = familytree.get(mother);
			familytree.get(firstName).setMother(mothers);
		}
		else  {mum = new Person(mother);
		familytree.get(firstName).setMother(mum);
		familytree.put(mother,  mum);
		}
		
		Person dad = null;
		if (familytree.get(father) != null){
			Person fathers = familytree.get(father);
			familytree.get(father).setFather(fathers);
		}
		else  {dad = new Person(father);
		familytree.get(firstName).setFather(dad);
		familytree.put(father, dad);
		}
		
		System.out.println(person + " has been added to Family Tree\n\n");
		// add children and siblings and persons
		allPersons();
		addChildren();
		addSiblings();
	}
	
	
	
	/**
	 *@param allPersons Total Users on File
	 *@return A list of All Users on File.
	 */

	public void allPersons(){
		Iterator<String> iterator = familytree.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Person value = familytree.get(key);

			System.out.println("Person  :  " + value);
		}
	}//end of allPersons
	
	
	/**
	 *@param addChildren add children to user
	 *@return A list of All Persons children .
	 */

	public void addChildren(){
		
		Iterator<String> iterator = familytree.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Person value = familytree.get(key);
			 Person mother = value.getMother();
			 if (mother != null){
				 mother.children.add(value);
			 }
			Person father = value.getFather();
			if (father != null){
			father.children.add(value);
			}
			//System.out.println("Arraylist of Children for  " + value.name + " = " + value.getChildren().toString());
		}
	}// end of add Children
	
		/**
		 *@param Print Children 
		 *@return A list of All Persons children 
		 */
		 	
	
    public void printChildren(){
    	
		Iterator<String> children = familytree.keySet().iterator();
		while (children.hasNext()) {
			String key = children.next();
			Person value = familytree.get(key);
			System.out.println("Children of  " + value.name + " = " + value.getChildren().toString());
		}
	}//end of printChildren
	
	
	

    public void addSiblings(){

    	Iterator<String> iterator = familytree.keySet().iterator();
    	while (iterator.hasNext()) {
    		String key = iterator.next();
    		Person value = familytree.get(key);
    		Person mother = value.getMother();
    		Person father = value.getFather();
    		if(mother != null){

    		Iterator<String> sibs= familytree.keySet().iterator();
    			while (sibs.hasNext()) {
    				String name = sibs.next();
    				Person names = familytree.get(name);
    				if(!(names.equals(value))){
    				Person sibling = familytree.get(name);
    				Person mother2 = sibling.getMother();
    				if(mother2 != null){
    					if(mother2.equals(mother)){
    						value.siblings.add(sibling);
    					}
    				}// end of mother2!=null
    			}// end of !name.equal(value)
    			}// end of while
    		} // of if(mother != null){
    		
    		
    		if(father != null){

        		Iterator<String> sibs2= familytree.keySet().iterator();
        			while (sibs2.hasNext()) {
        				String name2 = sibs2.next();
        				Person names2 = familytree.get(name2);
        				if(!(names2.equals(value))){
        				Person sibling2 = familytree.get(name2);
        				Person father2 = sibling2.getFather();
        				if(father2 != null){
        					if(father2.equals(father)){
        						value.siblings.add(sibling2);
        					}
        				}// end of father2!=null
        			}// end of !name.equal(value)
        			}// end of while
        		} // of if(father != null){
    		
    		
    		
    	}

    }// end of add Sibling
		
    
    /**
	 *@param Print Siblings 
	 *@return A list of All Persons siblings
	 */
	 	
 public void printSiblings(){
    	
		Iterator<String> siblings = familytree.keySet().iterator();
		while (siblings.hasNext()) {
			String key = siblings.next();
			Person value = familytree.get(key);
			System.out.println("Siblings of  " + value.name + " = " + value.getSiblings().toString());
		}
	}//end of printChildren

	
	
} //end of FamilyTreeApi


