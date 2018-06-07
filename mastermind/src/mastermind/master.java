package mastermind;

import java.io.File;
import java.util.Random;

public class master extends file {
	static int[][] minerror;
	static int[][] save;
	public Node first;
	String path = "F:\\hanieh\\gosaste\\java class slides\\a.txt";

	// constructor
	public master() {
		first = null;

	}

	// check link is empty or not
	int isempty() {
		return ((first == null) ? 1 : 0);
	}

	public static void main(String[] args) {
		master m = new master();
		master2 m2 = new master2();
		// int answer[] = m.random();
		int answer[] = { 7, 8, 3, 9 };
		String path = "F:\\hanieh\\gosaste\\java class slides\\a.txt";
		String path2 = "F:\\hanieh\\gosaste\\java class slides\\b.txt";
		write_file(path," ");

		System.out.println(answer[0] + "," + answer[1] + "," + answer[2] + "," + answer[3]);
		write_file(path, answer[0] + "," + answer[1] + "," + answer[2] + "," + answer[3]);
		// int guess1[] = m.random();
		int guess1[] = { 6, 1, 9, 4 };
		System.out.print(guess1[0] + "," + guess1[1] + "," + guess1[2] + "," + guess1[3] + ":  ");
		write_file(path, guess1[0] + "," + guess1[1] + "," + guess1[2] + "," + guess1[3] + ":  ");
		int pin1[] = m.Comparison(answer, guess1);
		// int guess2[] = m.random();
		int guess2[] = { 3, 4, 1, 9 };
		System.out.print(guess2[0] + "," + guess2[1] + "," + guess2[2] + "," + guess2[3] + ":  ");
		write_file(path, guess2[0] + "," + guess2[1] + "," + guess2[2] + "," + guess2[3] + ":  ");
		int pin2[] = m.Comparison(answer, guess2);
		// int guess3[] = m.random();
		int guess3[] = { 4, 5, 3, 7 };
		System.out.print(guess3[0] + "," + guess3[1] + "," + guess3[2] + "," + guess3[3] + ":  ");
		write_file(path, guess3[0] + "," + guess3[1] + "," + guess3[2] + "," + guess3[3] + ":  ");
		int pin3[] = m.Comparison(answer, guess3);
		// int guess4[] = m.random();
		int guess4[] = { 8, 6, 1, 2 };
		m2.saveguess(guess1, guess2, guess4, pin1, pin2, pin3);
		m2.child(guess4, answer);
		System.out.print(guess4[0] + "," + guess4[1] + "," + guess4[2] + "," + guess4[3] + ":  ");
		write_file(path, guess4[0] + "," + guess4[1] + "," + guess4[2] + "," + guess4[3] + ":  ");
		int pin4[] = m.Comparison(answer, guess4);
		// int guess5[] = m.random();
		int guess5[] = { 9, 7, 4, 1 };
		System.out.println(guess5[0] + "," + guess5[1] + "," + guess5[2] + "," + guess5[3] + ":  ");
		write_file(path, guess5[0] + "," + guess5[1] + "," + guess5[2] + "," + guess5[3] + ":  ");
		m.saveguess(guess1, guess2, guess3, guess4, pin1, pin2, pin3, pin4);
		m.child(guess5, answer);
		int guess6[] = m.random();
	    m.child(guess6, answer);
    	int guess7[] = m.random();
    	m.child(guess7, answer);
		int guess8[] = m.random();
		m.child(guess8, answer);
		int guess9[] = m.random();
		m.child(guess9, answer);
		int guess10[] = m.random();
		m.child(guess10, answer);

		System.out.println(";(");
		write_file(path, ":(");

	}

	public int[] random() {
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

	public int[] Comparison(int[] answer, int[] guess) {

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
	//	System.out.println("black is:" + black + "  " + "white is :" + white);
		write_file(path, "black is:" + black + "  " + "white is :" + white);
		pin[0] = black;
		pin[1] = white;
		return pin;

	}

	public int error(int[] guess1, int[] guess2, int[] guess3, int[] guess4, int[] newguess, int[] pin1, int[] pin2,
			int[] pin3, int[] pin4) {

		int error = 0;
		int errorarray[] = new int[4];
		master m = new master();
		int pin5[] = m.Comparison(newguess, guess1);
		errorarray[0] = m.sumerror(pin1, pin5);
		int pin6[] = m.Comparison(newguess, guess2);
		errorarray[1] = m.sumerror(pin2, pin6);
		int pin7[] = m.Comparison(newguess, guess3);
		errorarray[2] = m.sumerror(pin3, pin7);
		int pin8[] = m.Comparison(newguess, guess4);
		errorarray[3] = m.sumerror(pin4, pin8);
		error = errorarray[0] + errorarray[1] + errorarray[2] + errorarray[3];
		System.out.println("error :" + error);
		write_file(path, "error :" + error);

		System.out.println("------------------------------");
		write_file(path, "------------------------------");
		return error;
	}

	public int sumerror(int[] right, int[] guess) {

		int a = right[0] - guess[0];

		int b = right[1] - guess[1];
		if (a < 0 || b < 0) {
			if (a < 0)
				a = a * -1;
			if (b < 0)
				b = b * -1;
		}
		return (a + b);

	}

	public Node insertb(int parent[], int error) {
		int c1, c2, c3, c4, number;
		c1 = parent[0];
		c2 = parent[1];
		c3 = parent[2];
		c4 = parent[3];
		number = (c1 * 1000) + (c2 * 100) + (c3 * 10) + (c4);
		Node p = new Node(c1, c2, c3, c4, number, error);
		if (first == null)
			first = p;
		else {
			Node cur = first;
			while (cur.link != null)
				cur = cur.link;
			if (cur.link == null) {
				cur.link = p;
				p.prelink = cur;
			}

		}
		return p;
	}

	public int[] insertb(Node node) {
		int[] node_array = new int[4];
		node_array[0] = node.c1;
		node_array[1] = node.c2;
		node_array[2] = node.c3;
		node_array[3] = node.c4;
		System.out.println("------------------------------\nchild minerror:\n" + node_array[0] + "," + node_array[1]
				+ "," + node_array[2] + "," + node_array[3] + ":  ");
		write_file(path, "------------------------------\nchild minerror:\n" + node_array[0] + "," + node_array[1] + ","
				+ node_array[2] + "," + node_array[3] + ":  ");
		return node_array;

	}

	// search Node
	public boolean Search(int c, Node node) {
		Node cur = node;
		if (cur.c1 == c || cur.c2 == c || cur.c3 == c || cur.c4 == c) {
			return false;
		}
		return true;

	}

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

	public Node[] child(int parent[], int[] answer) {
		master m = new master();
		Random rand = new Random();
		Node[] A = null;
		Node minerror = first;
		int error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);

		Node p = m.insertb(parent, error);

		minerror = m.min_error_node();
		int j = 2;// rand.nextInt(4);

		for (int i = 1; i < 10; i++) {
			if (m.Search(i, p) == true) {
				parent[j] = i;
				System.out.println(parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
				write_file(path, parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
				error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
				m.insertb(parent, error);
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
			if (m.Search(i, p) == true) {
				parent[j1] = i;
				System.out.println(parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
				write_file(path, parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
				error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
				m.insertb(parent, error);
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
			if (m.Search(i, p) == true) {
				parent[j2] = i;
				System.out.println(parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
				write_file(path, parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
				error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
				m.insertb(parent, error);
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
			if (m.Search(i, p) == true) {
				parent[j3] = i;
				System.out.println(parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
				write_file(path, parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
				error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
				m.insertb(parent, error);
				minerror = m.min_error_node();

			}
		}
		System.out.println("minerror:" + minerror.error);
		write_file(path, "minerror:" + minerror.error);
		if (minerror.number == p.number) {

			return A;
		}
		A = m.numbers_of_minerror(minerror);
		if (A[0].error != 0)
			m.S(A, answer);

		if (A[0].error == 0) {
			m.D(A, answer);
		}

		return A;

	}

	public void D(Node[] A, int[] answer) {
		int B[] = new int[4];
		master m = new master();
		int pin[] = new int[2];
		int n = 0;

		for (int i = 0; i < A.length; i++) {
			if (A[i] != null)
				n++;
		}
		if (n == 1) {
			B[0] = A[0].c1;
			B[1] = A[0].c2;
			B[2] = A[0].c3;
			B[3] = A[0].c4;
			pin = m.Comparison(answer, B);
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
				pin = m.Comparison(answer, B);
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

		master m = new master();
		int n = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] != null) {
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

	public void saveguess(int[] guess1, int[] guess2, int[] guess3, int[] guess4, int[] pin1, int[] pin2, int[] pin3,
			int[] pin4) {
		// System.out.print(guess1[0] + "," + guess1[1] + "," + guess1[2] + "," +
		// guess1[3] + ": ");
		save = new int[8][];
		save[0] = new int[4];
		save[1] = new int[4];
		save[2] = new int[4];
		save[3] = new int[4];
		save[4] = new int[2];
		save[5] = new int[2];
		save[6] = new int[2];
		save[7] = new int[2];

		for (int j = 0; j < 4; j++) {
			save[0][j] = guess1[j];
		}
		for (int j = 0; j < 4; j++) {
			save[1][j] = guess2[j];
		}
		for (int j = 0; j < 4; j++) {
			save[2][j] = guess3[j];
		}
		for (int j = 0; j < 4; j++) {
			save[3][j] = guess4[j];
		}

		for (int j = 0; j < 2; j++) {
			save[4][j] = pin1[j];
		}
		for (int j = 0; j < 2; j++) {
			save[5][j] = pin2[j];
		}
		for (int j = 0; j < 2; j++) {
			save[6][j] = pin3[j];
		}
		for (int j = 0; j < 2; j++) {
			save[7][j] = pin4[j];
		}

	}

	public int[][] errorComparison(int parentError, int childError, int[] parent, int[] child) {
		int result[][] = new int[2][];// min error
		result[0] = new int[4];// Array error
		result[1] = new int[1];// sum error
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
