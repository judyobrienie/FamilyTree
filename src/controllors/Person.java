package controllors;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Judy
 * @param A Person Class 
 */
public class Person implements Comparable<Person> {

   
	public String name;
	public String gender;
	public int dob;
	public Person mother = null;
	public Person father= null;
	public HashSet<Person>children = new HashSet<Person>();
	public HashSet<Person>siblings = new HashSet<Person>();

	/** Constructor for object of class Item. 
	 * @param Person Class
	 
	 * @return 
	 * @return English translation
	 */

	public Person(String name, String gender, int dob)
	{
		this.name = name;
		this.gender = gender;
		this.dob = dob;

	}

	public Person(String name)
	{
		this.name = name;
		

	}

	public Person(String name, String gender, int dob, Person mother, Person father)
	{
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.mother = mother;
		this.father = father;
	}


	@Override
	public String toString() {
		return name + ", " +  gender + ", " + dob ;
		
	}

	

	/**
	 * Getters and Setters
	 */


	/**
	 * @param Comparason of two items
	  * @returns the item in decending order
	  */ 
	public HashSet<Person> getChildren() {
		return children;
	}

	public void setChildren(HashSet<Person> children) {
		this.children = children;
	}

	public HashSet<Person> getSiblings() {
		return siblings;
	}

	public void setSiblings(HashSet<Person> siblings) {
		this.siblings = siblings;
	}

	
	
	public int compareTo(Person person) {
		return Integer.compare(this.dob, dob);
		
	}

	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getDob() {
		return dob;
	}

	public void setDob(int dob) {
		this.dob = dob;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}



	

}
	