package controllors;


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
	public String toString() {
		return name + ", " +  gender + ", " + dob + ", " + mother + ", " +father;
	}

	public Person(String name, String gender, int dob, Person mother, Person father)
	{
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.mother = mother;
		this.father = father;
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

	/**
	 * Getters and Setters
	 */


	/**
	 * @param Comparason of two items
	  * @returns the item in decending order
	  */ 


	

}
	