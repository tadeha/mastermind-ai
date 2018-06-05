package mastermind;

import java.util.*;

public class Mastermind extends file {

    static int[][] save;
    public Node first;
    static String path = "/Users/tadeh/Documents/mastermind-modifications/mastermind-ai/path1.txt";

    // constructor of the main class
    public Mastermind() {
        first = null;
    }

    public static void main(String[] args) {
        Mastermind m = new Mastermind();

        int[] answer = new int[4];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter 4 numbers to be guessed:");
        for (int i = 0; i < answer.length; i++) {
            answer[i] = scanner.nextInt();
        }

        clear_file(path);

        //System.out.println(answer[0] + "," + answer[1] + "," + answer[2] + "," + answer[3]);
        write_file(path, "Decided Answer:");
        write_file(path, answer[0] + "," + answer[1] + "," + answer[2] + "," + answer[3]);
        write_file(path, "------------------------------");

        int guess1[] = random();
        //System.out.print(guess1[0] + "," + guess1[1] + "," + guess1[2] + "," + guess1[3] + ":  ");
        write_file(path, guess1[0] + "," + guess1[1] + "," + guess1[2] + "," + guess1[3] + ":  ");
        int pin1[] = compare(answer, guess1);

        int guess2[] = random();
        //System.out.print(guess2[0] + "," + guess2[1] + "," + guess2[2] + "," + guess2[3] + ":  ");
        write_file(path, guess2[0] + "," + guess2[1] + "," + guess2[2] + "," + guess2[3] + ":  ");
        int pin2[] = compare(answer, guess2);

        int guess3[] = random();
        //System.out.print(guess3[0] + "," + guess3[1] + "," + guess3[2] + "," + guess3[3] + ":  ");
        write_file(path, guess3[0] + "," + guess3[1] + "," + guess3[2] + "," + guess3[3] + ":  ");
        int pin3[] = compare(answer, guess3);

        int guess4[] = random();
        //System.out.print(guess4[0] + "," + guess4[1] + "," + guess4[2] + "," + guess4[3] + ":  ");
        write_file(path, guess4[0] + "," + guess4[1] + "," + guess4[2] + "," + guess4[3] + ":  ");
        int pin4[] = compare(answer, guess4);

        int guess5[] = random();
        //System.out.println(guess5[0] + "," + guess5[1] + "," + guess5[2] + "," + guess5[3] + ":  ");
        write_file(path, guess5[0] + "," + guess5[1] + "," + guess5[2] + "," + guess5[3] + ":  ");

        saveGuesses(guess1, guess2, guess3, guess4, pin1, pin2, pin3, pin4);
        m.child(guess5, answer);

        int guess6[] = random();
        m.child(guess6, answer);

        int guess7[] = random();
        m.child(guess7, answer);

        int guess8[] = random();
        m.child(guess8, answer);

        int guess9[] = random();
        m.child(guess9, answer);

        int guess10[] = random();
        m.child(guess10, answer);

    }

    // this function returns a 4 random numbers array (no repeating numbers)
    private static int[] random() {
        Random rand = new Random();

        int a = rand.nextInt(9) + 1;
        int b = rand.nextInt(9) + 1;
        while (a == b) {
            b = rand.nextInt(9) + 1;
        }
        int c = rand.nextInt(9) + 1;
        while (c == a || c == b) {
            c = rand.nextInt(9) + 1;
        }
        int d = rand.nextInt(9) + 1;
        while (d == a || d == b || d == c) {
            d = rand.nextInt(9) + 1;
        }

        int answer[] = new int[4];
        answer[0] = a;
        answer[1] = b;
        answer[2] = c;
        answer[3] = d;

        return answer;
    }

    // this function compares the guess with our actual answer and returns the pins array
    private static int[] compare(int[] answer, int[] guess) {
        int black = 0;
        int white = 0;
        int pin[] = new int[2];

        for (int i = 0; i < answer.length; i++) {
            if (answer[i] == guess[i]) {
                black++;
            }
            for (int j = 0; j < guess.length; j++) {
                if ((answer[i] == guess[j]) && (i != j)) {
                    white++;
                }
            }
        }

        write_file(path, "number of black pegs/peg are/is:" + black + "  " + "number of white pegs/peg are/is :" + white);

        pin[0] = black;
        pin[1] = white;

        return pin;

    }

    // this function calculates and returns number of errors for each guess
    public int error(int[] guess1, int[] guess2, int[] guess3, int[] guess4, int[] newguess, int[] pin1, int[] pin2,
            int[] pin3, int[] pin4) {

        int error = 0;
        int errorarray[] = new int[4];

        int pin5[] = compare(newguess, guess1);
        errorarray[0] = sumerror(pin1, pin5);

        int pin6[] = compare(newguess, guess2);
        errorarray[1] = sumerror(pin2, pin6);

        int pin7[] = compare(newguess, guess3);
        errorarray[2] = sumerror(pin3, pin7);

        int pin8[] = compare(newguess, guess4);
        errorarray[3] = sumerror(pin4, pin8);

        error = errorarray[0] + errorarray[1] + errorarray[2] + errorarray[3];
        System.out.println("error :" + error);
        write_file(path, "error :" + error);

        System.out.println("------------------------------");
        write_file(path, "------------------------------");
        return error;
    }

    //this function calculates the sum of two error
    private static int sumerror(int[] right, int[] guess) {
        int a = right[0] - guess[0];

        int b = right[1] - guess[1];
        if (a < 0 || b < 0) {
            if (a < 0) {
                a = a * -1;
            }
            if (b < 0) {
                b = b * -1;
            }
        }
        return (a + b);
    }

    // returns a child node
    public Node insertNode(int parent[], int error) {
        int c1, c2, c3, c4, number;
        c1 = parent[0];
        c2 = parent[1];
        c3 = parent[2];
        c4 = parent[3];

        // creating a 4 digit number
        number = (c1 * 1000) + (c2 * 100) + (c3 * 10) + (c4);
        // creating a node from given info
        Node p = new Node(c1, c2, c3, c4, number, error);

        if (first == null) {
            // parent always will be at first
            first = p;
        } else {
            // connect children to the parent
            Node cur = first;
            while (cur.link != null) {
                cur = cur.link;
            }
            if (cur.link == null) {
                cur.link = p;
                p.prelink = cur;
            }
        }
        return p;
    }

    // this function searches for the minimum error node and returns a boolean
    public boolean searchMinError(int c, Node node) {
        Node cur = node;
        if (cur.c1 == c || cur.c2 == c || cur.c3 == c || cur.c4 == c) {
            return false;
        }
        return true;
    }

    // this function searches for the minumim error node and returns a node
    public Node min_error_node() {
        Node min_error = first;
        int minerror = first.error;
        Node cur = first;
        while (cur.link != null) {
            if (cur.link.error < minerror) {
                minerror = cur.link.error;
                min_error = cur.link;
            }
            cur = cur.link;
        }

        return min_error;
    }

    // this function returns the number of minimum errors in our list
    public Node[] numbers_of_minerror(Node minerror) {
        Node cur = first;
        int j = 10;
        int i = 0;
        Node[] A = new Node[j];

        while (cur != null) {
            if (cur.error == minerror.error) {
                A[i] = cur;
                i++;
            }

            cur = cur.link;
        }
        return A;
    }

    // a function to create children from a parent (it returns a child of a parent) (the answer does not used for camparing)
    private Node[] child(int parent[], int[] answer) {
        Mastermind m = new Mastermind();
        Random rand = new Random();

        // this array contains the children nodes which has the same error number
        Node[] A = null;

        Node minerror = first;

        // calculate the error number
        int error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);

        // create parent/children nodes
        Node p = m.insertNode(parent, error);

        minerror = m.min_error_node();
        int j = rand.nextInt(4);

        // creating the children -> field 1
        for (int i = 1; i < 10; i++) {
            // check for repeating numbers
            if (m.searchMinError(i, p) == true) {
                parent[j] = i;
                //System.out.println(parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
                write_file(path, parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
                error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
                m.insertNode(parent, error);
                // find the most minimum error number
                minerror = m.min_error_node();
            }
        }

        parent[0] = p.c1;
        parent[1] = p.c2;
        parent[2] = p.c3;
        parent[3] = p.c4;

        int j1 = rand.nextInt(4);

        while (j == j1) {
            j1 = rand.nextInt(4);
        }

        for (int i = 1; i < 10; i++) {
            if (m.searchMinError(i, p) == true) {
                parent[j1] = i;
                //System.out.println(parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
                write_file(path, parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
                error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
                m.insertNode(parent, error);
                minerror = m.min_error_node();

            }
        }

        parent[0] = p.c1;
        parent[1] = p.c2;
        parent[2] = p.c3;
        parent[3] = p.c4;

        int j2 = 0;
        while (j == j2 || j2 == j1) {
            j2 = rand.nextInt(4);
        }

        for (int i = 1; i < 10; i++) {
            if (m.searchMinError(i, p) == true) {
                parent[j2] = i;
                System.out.println(parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
                write_file(path, parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
                error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
                m.insertNode(parent, error);
                minerror = m.min_error_node();

            }
        }
        parent[0] = p.c1;
        parent[1] = p.c2;
        parent[2] = p.c3;
        parent[3] = p.c4;

        int j3 = 0;
        while (j == j3 || j3 == j1 || j3 == j2) {
            j3 = rand.nextInt(4);
        }

        for (int i = 1; i < 10; i++) {
            if (m.searchMinError(i, p) == true) {
                parent[j3] = i;
                System.out.println(parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
                write_file(path, parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
                error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
                m.insertNode(parent, error);
                minerror = m.min_error_node();

            }
        }
        // print the minerror node number
        System.out.println("minerror:" + minerror.error);
        write_file(path, "minerror:" + minerror.error);

        // avoiding loop in creating children and stack overflow
        if (minerror.number == p.number) {
            // finish creating children
            return A;
        }

        // fill the array with nodes which has the same error number
        A = m.numbers_of_minerror(minerror);

        if (A[0].error != 0) {
            m.S(A, answer);
        }

        if (A[0].error == 0) {
            D(A, answer);
        }

        return A;

    }

    private static void D(Node[] A, int[] answer) {
        int B[] = new int[4];
        Mastermind m = new Mastermind();
        int pin[];
        int n = 0;

        for (Node A1 : A) {
            if (A1 != null) {
                n++;
            }
        }
        if (n == 1) {
            B[0] = A[0].c1;
            B[1] = A[0].c2;
            B[2] = A[0].c3;
            B[3] = A[0].c4;
            pin = compare(answer, B);
            if (pin[0] == 4) {
                System.out.print("answer is " + B[0] + "," + B[1] + "," + B[2] + "," + B[3]);
                write_file(path, "answer is " + B[0] + "," + B[1] + "," + B[2] + "," + B[3]);
                System.exit(0);
            }

        } else if (n != 1) {
            for (int j = 0; j != n; j++) {
                B[0] = A[j].c1;
                B[1] = A[j].c2;
                B[2] = A[j].c3;
                B[3] = A[j].c4;
                pin = compare(answer, B);
                if (pin[0] == 4) {
                    System.out.print("answer is " + B[0] + "," + B[1] + "," + B[2] + "," + B[3]);
                    write_file(path, "answer is " + B[0] + "," + B[1] + "," + B[2] + "," + B[3]);
                    System.exit(0);

                }

            }
        }

    }

    public void S(Node[] A, int[] answer) {
        int B[] = new int[4];

        Mastermind m = new Mastermind();
        int n = 0;
        for (Node A1 : A) {
            if (A1 != null) {
                n++;
            }
        }
        if (n == 1) {
            B[0] = A[0].c1;
            B[1] = A[0].c2;
            B[2] = A[0].c3;
            B[3] = A[0].c4;
            m.child(B, answer);
        } else if (n != 1) {
            for (int j = 0; j != n; j++) {
                B[0] = A[j].c1;
                B[1] = A[j].c2;
                B[2] = A[j].c3;
                B[3] = A[j].c4;
                m.child(B, answer);
            }
        }

    }

    // this function save guesses with their pin values in an array called "save"
    private static void saveGuesses(int[] guess1, int[] guess2, int[] guess3, int[] guess4, int[] pin1, int[] pin2, int[] pin3,
            int[] pin4) {

        save = new int[8][];
        save[0] = new int[4];
        save[1] = new int[4];
        save[2] = new int[4];
        save[3] = new int[4];
        save[4] = new int[2];
        save[5] = new int[2];
        save[6] = new int[2];
        save[7] = new int[2];

        save[0] = guess1;
        save[1] = guess2;
        save[2] = guess3;
        save[3] = guess4;

        save[4] = pin1;
        save[5] = pin2;
        save[6] = pin3;
        save[7] = pin4;
    }

    // this function compares the minimum number of errors of parent and its children
    // and returns whichever is less as result
    public int[][] errorComparison(int parentError, int childError, int[] parent, int[] child) {
        int result[][] = new int[2][];// Most Minimum Errors array
        result[0] = new int[4]; // Array of errors
        result[1] = new int[1]; // Sum of errors

        if (parentError < childError) {
            result[0] = parent;
            result[1][0] = parentError;
        } else if (childError < parentError) {
            result[0] = child;
            result[1][0] = childError;
        }

        return result;
    }
}
