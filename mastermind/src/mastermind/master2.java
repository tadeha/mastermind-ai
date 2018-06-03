package mastermind;
import java.util.Random;

public class master2 extends master {
	

	
		static int[][] minerror;
		static int[][] save;
		
		public int error(int[] guess1, int[] guess2, int[] guess3, int[] newguess, int[] pin1, int[] pin2,
				int[] pin3) {

			int error = 0;
			int errorarray[] = new int[3];
			master m = new master();
			int pin5[] = m.Comparison(newguess, guess1);
			errorarray[0] = m.sumerror(pin1, pin5);
			int pin6[] = m.Comparison(newguess, guess2);
			errorarray[1] = m.sumerror(pin2, pin6);
			int pin7[] = m.Comparison(newguess, guess3);
			errorarray[2] = m.sumerror(pin3, pin7);
			
			error = errorarray[0] + errorarray[1] + errorarray[2] ;
			System.out.println("error :" + error);
			System.out.println("------------------------------");
			return error;
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

		public Node[] child(int parent[], int[] answer) {
			master2 m = new master2();
			Random rand = new Random();
			Node[] A = null;
			Node minerror = first;
			int error = m.error(save[0], save[1], save[2], parent, save[4], save[5], save[6]);

			Node p = m.insertb(parent, error);

			minerror = m.min_error_node();
			int j = 2;// rand.nextInt(4);

			for (int i = 1; i < 10; i++) {
				if (m.Search(i, p) == true) {
					parent[j] = i;
					System.out.println(parent[0] + "," + parent[1] + "," + parent[2] + "," + parent[3] + ":  ");
					error = m.error(save[0], save[1], save[2], parent, save[4], save[5], save[6]);
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
					error = m.error(save[0], save[1], save[2],  parent, save[4], save[5], save[6]);
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
					error = m.error(save[0], save[1], save[2], parent, save[4], save[5], save[6]);
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
					error = m.error(save[0], save[1], save[2], parent, save[4], save[5], save[6]);
					m.insertb(parent, error);
					minerror = m.min_error_node();

				}
			}
			System.out.println("minerror:" + minerror.error);
			if (minerror.number == p.number) {
			
				return A;
			}
			A = m.numbers_of_minerror(minerror);
			if (A[0].error != 0)
				m.S(A, answer);

			if (A[0].error == 0) {
				D(A, answer);
			}

			return A;

		}

		public void saveguess(int[] guess1, int[] guess2,  int[] guess4, int[] pin1, int[] pin2, int[] pin3) {
			
			save = new int[8][];
			save[0] = new int[4];
			save[1] = new int[4];
			
			save[3] = new int[4];
			save[4] = new int[2];
			save[5] = new int[2];
			save[6] = new int[2];
		
			for (int j = 0; j < 4; j++) {
				save[0][j] = guess1[j];
			}
			for (int j = 0; j < 4; j++) {
				save[1][j] = guess2[j];
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
			
		

		
	}

}
