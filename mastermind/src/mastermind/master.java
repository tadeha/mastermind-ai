package mastermind;

import java.util.Random;

public class master {
	static int[][] minerror;
	static int[][] save;
	private Node first;

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
		int answer[] = m.random();

		System.out.println(answer[0] + "," + answer[1] + "," + answer[2] + "," + answer[3]);
		int guess1[] = m.random();
		System.out.print(guess1[0] + "," + guess1[1] + "," + guess1[2] + "," + guess1[3] + ":  ");
		int pin1[] = m.Comparison(answer, guess1);
		int guess2[] = m.random();
		System.out.print(guess2[0] + "," + guess2[1] + "," + guess2[2] + "," + guess2[3] + ":  ");
		int pin2[] = m.Comparison(answer, guess2);
		int guess3[] = m.random();
		System.out.print(guess3[0] + "," + guess3[1] + "," + guess3[2] + "," + guess3[3] + ":  ");
		int pin3[] = m.Comparison(answer, guess3);
		int guess4[] = m.random();
		System.out.print(guess4[0] + "," + guess4[1] + "," + guess4[2] + "," + guess4[3] + ":  ");
		int pin4[] = m.Comparison(answer, guess4);
		int guess5[] = m.random();
		System.out.print(guess5[0] + "," + guess5[1] + "," + guess5[2] + "," + guess5[3] + ":  ");
		m.saveguess(guess1, guess2, guess3, guess4, pin1, pin2, pin3, pin4);

		int child1[] = new int[4];
		child1 = m.child1(guess5);
		minerror = m.errorComparison(
				m.error(save[0], save[1], save[2], save[3], guess5, save[4], save[5], save[6], save[7]),
				(m.error(guess1, guess2, guess3, guess4, child1, pin1, pin2, pin3, pin4)), guess5, child1);
		System.out.println("ghild:");

		m.child(minerror[0]);

		if (minerror[1][0] == 0) {
			System.out
					.print(minerror[0][0] + "," + minerror[0][1] + "," + minerror[0][2] + "," + minerror[0][3] + ":  ");
		}

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
		System.out.println("black is:" + black + "  " + "white is :" + white);
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
		errorarray[1] = m.sumerror(pin1, pin6);
		int pin7[] = m.Comparison(newguess, guess3);
		errorarray[2] = m.sumerror(pin1, pin7);
		int pin8[] = m.Comparison(newguess, guess4);
		errorarray[3] = m.sumerror(pin1, pin8);
		error = errorarray[0] + errorarray[1] + errorarray[2] + errorarray[3];

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

	public int[] child1(int parent[]) {
		int child1[] = new int[4];
		int l = 0;

		for (int j = 1; j < 10 && l < 4; j++) {

			if (parent[0] != j && parent[1] != j && parent[2] != j && parent[3] != j) {
				child1[l] = j;
				l++;
			}

		}
		return child1;

	}

	// insert between the linked list
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

	// search Node
	public boolean Searchnumber(int number) {
		Node cur = first;

		while (cur.number != number && cur != null) {
			cur = cur.link;

		}
		if (cur.number == number) {
			return true;
		}
		return false;
	}

	// search2 Node
	public boolean Search(int c, Node node) {
		Node cur = node;
		if (cur.c1 == c || cur.c2 == c || cur.c3 == c || cur.c4 == c) {
			return false;
		}
		return true;

	}

	public Node min_error_node() {
		Node min_error = first;
		

		return first;

	}

	public void child(int parent[]) {
		master m = new master();
		Random rand = new Random();
		int error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
		Node p = m.insertb(parent, error);
		int j = rand.nextInt(3);
		for (int i = 1; i < 10; i++) {
			if (m.Search(i, p) == false) {
				parent[j] = i;
				error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
				m.insertb(parent, error);

			}
		}
		int j1 = 0;
		while (j == j1) {
			j1 = rand.nextInt(3);
		}
		for (int i = 1; i < 10; i++) {
			if (m.Search(i, p) == false) {
				parent[j1] = i;
				error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
				m.insertb(parent, error);

			}
		}
		int j2 = 0;
		while (j == j2 || j2 == j1) {
			j2 = rand.nextInt(3);
		}

		for (int i = 1; i < 10; i++) {
			if (m.Search(i, p) == false) {
				parent[j2] = i;
				error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
				m.insertb(parent, error);

			}
		}
		int j3= 0;
		while (j == j3 || j3 == j1 || j3==j2) {
			j3 = rand.nextInt(3);
		}

		for (int i = 1; i < 10; i++) {
			if (m.Search(i, p) == false) {
				parent[j3] = i;
				error = m.error(save[0], save[1], save[2], save[3], parent, save[4], save[5], save[6], save[7]);
				m.insertb(parent, error);

			}
		}
		m.min_error_node();

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
