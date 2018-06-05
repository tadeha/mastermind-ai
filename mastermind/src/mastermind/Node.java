package mastermind;

public class Node {

    // value of each field (_,_,_,_) -> (c1,c2,c3,c4)
    public int c1;
    public int c2;
    public int c3;
    public int c4;
    // a 4 digit number containing above digits (c1c2c3c4)
    public int number;
    // value of error
    public int error;
    // doubly linked list prev/next
    public Node prelink;  //Reference to prelink
    public Node link;      //Reference to next

    // constructor of the node class
    public Node(int c1, int c2, int c3, int c4, int number, int error) {

        super();
        this.link = null;
        this.prelink = null;
        this.c1 = c1;
        this.c2 = c2;
        this.c3 = c3;
        this.c4 = c4;
        this.number = number;
        this.error = error;

    }
}
