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
	public Person spouse = null;
	

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dob;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (dob != other.dob)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
		return name + ", " + dob ;
		
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

	@Override
	 public int compareTo(Person o) {
		    return Integer.compare(getDob(), o.getDob());
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

	public Person getSpouse() {
		return spouse;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}

	

}
	