package controllors;

import java.util.List;

public class Node {
   
	List<Node>children;
	
}



class PersonNode extends Node {

	Node right, left;
	Person person;
    
public PersonNode(Person person)
{

    left = null;
    right = null;
    
    this.person = person;
    
}
} // end of person note


class RelationNode extends Node {
	Node right, left;
    List<Node> children;
   
  public RelationNode(List<Node> children){
	  left = null;
	  right = null;
	    
	  this.children = children;
	    
  }
} // end of relation node



