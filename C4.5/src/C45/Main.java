package C45;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {

		ArrayList<String> cata = new ArrayList<String>();
		cata.add("red");
		cata.add("red");
		cata.add("red");
		cata.add("red");
		cata.add("blue");
		cata.add("blue");
		cata.add("blue");
		cata.add("green");
		cata.add("green");
		cata.add("green");

		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(0);
		nums.add(1);
		nums.add(2);
		nums.add(2);
		nums.add(5);
		nums.add(5);
		nums.add(6);
		nums.add(8);
		nums.add(9);
		nums.add(10);

		Node n = new Node(cata, nums);

		test(n);

	}

	public static void test(Node n) {

		Collections.sort(n.getNums());
		// Collections.sort(n.getCata());

		int numberOfCata = n.getNumberOfCata();
		ArrayList<String> cats = n.getUniqueCata();
		int lowerThreshod = 0;
		int higherThreshold = 0;

		for (int j = 0; j < cats.size(); j++) {// get best in cat

			int max = n.getNums().size();
			int min = higherThreshold;
			int diection[] = getDirection(n, cats.get(j), min, max);			
			float purity = 0;
			

			int accuracy = 0;

			while (min < max || accuracy < 100) {

				
				diection = getDirection(n, cats.get(j), min, max);
				min = (min + diection[0]);
				max = (max - diection[1]);

				

				int correct = 0;
				int i = min;

				while (i < max) {

					if (n.getCata().get(i) == cats.get(j)) {

						correct++;

					}

					i++;

				}

				float localPurity = correct / (float) (max - min);

				if (localPurity > purity) {

					purity = localPurity;
					lowerThreshod = min;
					higherThreshold = max;

				}

				// System.out.println("Catagory " + cats.get(j));
				// System.out.println("Correct: " + ((float) localPurity * 100)
				// + "%\n");// store
				accuracy++;

			}

			System.out.println(cats.get(j) + ": " + purity * 100 + " correct\n" + n.getNums().get(lowerThreshod)
					+ " lowerThreshod\n" + n.getNums().get(higherThreshold) + " lowerThreshod\n");

		}

	}

	private static int[] getDirection(Node n, String cata, int min, int max) {// decides to reduce the thresholds min or max

		ArrayList<String> catas = n.getCata();
		ArrayList<Integer> nums = n.getNums();

		int leftCorrect = 0;
		int i = min;

		while (i < max - 1) {

			if (n.getCata().get(i) == cata) {

				leftCorrect++;

			}
			
			i++;

		}

		int rightCorrect = 0;
		int j = min;
		
		while( j + 1 < max) {

			if (n.getCata().get(j) == cata) {

				rightCorrect++;

			}

			j++;
			
		}

		if (rightCorrect >= leftCorrect) {

			int[] direction = new int[] { 0, 1 };
			return direction;

		} else {

			int[] direction = new int[] { 1, 0 };
			return direction;

		}

	}

}

