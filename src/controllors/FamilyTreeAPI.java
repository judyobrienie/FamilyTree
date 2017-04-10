package controllors;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.Scanner;
import java.util.TreeMap;
import utils.Serializer;



public class FamilyTreeAPI  {

	private Serializer serializer;

    public Map<String, Person> familytree = new TreeMap<>();
    List<Person> copy = new ArrayList<Person>();
    
   

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
		String delims = "[ ]" ;//each field in the file is separated(delimited) by a space.
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
			 //System.out.println(familytree);
		
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
			String spouseTemp = userToken[5];
			
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
			
			Person sp = null;
			if (familytree.get(spouseTemp) != null){
				Person spouse = familytree.get(spouseTemp);
				familytree.get(userToken[0]).setSpouse(spouse);
			}
			else  {sp = new Person(spouseTemp);
			familytree.get(userToken[0]).setSpouse(sp);
			familytree.put(spouseTemp, sp);
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
		
		if(familytree.containsKey(firstName)){
			System.err.println("Person with That Name Already Exists \nAdding a _1 to name \nPlease ensure you use _1 to reference correct family members");
			firstName = firstName + "_1";
			
			Person person1 = new Person (firstName, gender, age);
			familytree.put(firstName,  person1);
		}
		else
		{
		
		Person person = new Person (firstName, gender, age);
		familytree.put(firstName,  person);
		
		}
		
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
		
		System.out.println("Added to Family Tree\n\n");
		// add children and siblings and persons
		allPersons();
		addChildren();
		addSiblings();
	}
	
	
	/**
	 *@param addPerson Create a new instance of Person
	 *@return an updated Hash Map of type Person
	 */
	
public void updatePerson(String firstName,String gender, int age, String mother, String father) {
		
		familytree.get(firstName).setGender(gender);
		familytree.get(firstName).setDob(age);  
		
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
		
		System.out.println(firstName + " has been UPDATED in the Family Tree\n\n");
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

			System.out.println("Family Member  :  " + value);
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

 
 /**
	 *@param Print array of Persons in decending order 
	 *@return A list of All Persons 
	 */
	 	
 
 public void  min() {
	 
	 
	 Iterator<String> name= familytree.keySet().iterator();
		while (name.hasNext()) {
			String key = name.next();
			Person value = familytree.get(key);
	//if arraylist already contains object do not add
	 if(!copy.contains(value)){
			copy.add(value);
	 }
		}
	 Collections.sort(copy);
	 System.out.println("Family Members in Decencing Order ");
	 System.out.println(copy.toString());
	
	 for(int i=0; i< copy.size(); i++){
		 if(copy.get(i).dob != 0){
	 System.out.println(copy.get(i));
	 }
		 
	 }
 	}
	
 
 
 /**
	 *@param Print Tree
	 *@return A list of All Persons showing relationships
	 */
	 	
 
 public void printTree()

 {
	 
     for(int j = 0; j < copy.size(); j++){
     System.out.println(copy.get(j));
     System.out.println("  Children of  " + copy.get(j).name + " = " + copy.get(j).getChildren().toString());
     List<Person> temp = new ArrayList<Person>(copy.get(j).getChildren());
     
     for(int i= 0; i < temp.size() ; i++){
    	 //if(temp.get(i).getChildren() != null)
     System.out.println("           Children of  " + temp.get(i).name + " = " + temp.get(i).getChildren().toString());
    	 
     List<Person> temp2 = new ArrayList<Person>(temp.get(i).getChildren());
     
     for(int m= 0; m < temp2.size(); m++){
    	// if(temp2.get(m).getChildren() != null)
         System.out.println("                          Children of  " + temp2.get(m).name + " = " + temp2.get(m).getChildren().toString());
    	 
     }
 }}
 }
 
 
 
 
 
 
 public void printTree(Person person, String indent)

 {    
     if(person == null)

         return;

	
    // System.out.println(" "+ person.toString());

     System.out.println("  " +indent + "     Children of  " + person + " = " + person.getChildren().toString());
     List<Person> temp = new ArrayList<Person>(person.getChildren());
     
     for(int i= 0; i < temp.size() ; i++){
    	 if(temp.get(i).getChildren() != null){
    printTree( temp.get(i),indent+"        " );
    	 }
     }
 
 }
 
 /**
  * Printing Family tree, calling recursive call on printTree() and also
  * printing their spouses Tree
  * @param person
  */
 
 
 public void printTree2(Person person)
 {
	 System.err.println("Family Tree of :" + person + " Spouse : " + person.getSpouse());
    
	System.out.println("===============================================");
	System.out.println("===============================================");
	
	printTree(person,  "indent ");
	
	Person spouse = person.getSpouse();
	
	System.out.println("Family Tree of  Spouse : " + person.getSpouse());
	printTree(spouse, "indent ");
	
	
	System.err.println("\n\nMother :" + person.getMother() + " Father : " + person.getFather());
	 
	 //printTree(person.getMother(), "indent ");
	 
 }
 
 
 
 
 
 
} //end of FamilyTreeApi


