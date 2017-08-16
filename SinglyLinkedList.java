/**
 *Dewey L. Sia 
 *L.05 Singly Linked List Implementation
 *7/18/2016
 */


public class SinglyLinkedList {
	public Node head,tail;
	public int count = 0;
    public SinglyLinkedList() {
    	head = tail = null;
    }
    public boolean isEmpty(){
    	return head == null;
    }
    public void addToHead(int n){
    	if(!isEmpty()){
    		head = new Node(n, head);	
    	}
    	else head = tail = new Node(n);
    }
    
    public void addToTail(int n){
    	if(!isEmpty()){
    		tail = tail.next = new Node(n);
    	}
    	else head = tail = new Node(n);
    }
    
    public int deleteHead(){
    	if(!isEmpty()){ //not empty 
    		int val = head.data;
    		if( head != tail) //more than one node
    			head = head.next;
    		else head = tail = null; //only one node
    		return val;
    	}
    	else return -1; //no nodes exist
    	
    }
    public int deleteTail(){
    	if(!isEmpty()){ //not empty 
    		int val = tail.data;
    		if(head != tail){ //more than one node
    			Node temp;
	    		for(temp = head; temp.next != tail; temp = temp.next);
	    		tail = temp;
	    		tail.next = null;
    		} //one node only
    		else head = tail = null;
    		return val;
    	}
    	else return -1;
    }
    
    public void print(){
    	for(Node temp = head; temp != null; temp = temp.next)
    		System.out.print(temp.data + " ");
    		System.out.println();
    }
}