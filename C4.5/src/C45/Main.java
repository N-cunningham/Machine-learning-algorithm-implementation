package C45;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {

		ArrayList<String> cata = new ArrayList<String>();
		cata.add("red");
		cata.add("green");
		cata.add("red");
		cata.add("blue");
		cata.add("red");
		cata.add("blue");
		cata.add("blue");
		cata.add("red");
		cata.add("green");
		cata.add("green");

		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(1);
		nums.add(5);
		nums.add(2);
		nums.add(10);
		nums.add(0);
		nums.add(8);
		nums.add(9);
		nums.add(1);
		nums.add(5);
		nums.add(6);

		Node n = new Node(cata, nums);

		test(n);

	}

	public static void test(Node n) {

		Collections.sort(n.getNums());

		int numberOfCata = n.getNumberOfCata();
		ArrayList<String> cats = n.getUniqueCata();

		for (int j = 0; j < cats.size(); j++) {//get best in cat
			
			int min = 0;
			int max = n.getNums().size();
			
			while (max > min) {
				int correct = 0;
				int inncorrect = 0;

				for (int i = min; i < max; i++) {// ToDo iterate through range and choose left or right

					if (n.getCata().get(i) == cats.get(j)) {

						correct++;

					}

				}

				max--;
				System.out.println("Catagory " + cats.get(j));//store
				System.out.println("Correct: " + ((float) correct / (float) n.getNums().size() * 100) + "%\n");//store

			}

		}

	}

}
