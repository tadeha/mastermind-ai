package mastermind;



public class Node {

	//fields
	public int c1;
	public int c2;
	public int c3;
	public int c4;
	public int number;
	public int error;
	public  Node prelink;  //Reference to prelink
	public Node link;       //Reference to next
	
	//constructor 
	public Node(int c1,int c2,int c3,int c4,int number,int error) {
		
		super();
		this.link=null;
		this.prelink=null;
		this.c1=c1;
		this.c2=c2;
		this.c3=c3;
		this.c4=c4;
		this.number=number;
		this.error=error;

		
	}
	//print link
	//void print() {
		
	//	System.out.println(this.id+"-->"+this.Name);
		
//	}
	
}
