package C45;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {

		ArrayList<String> cata = new ArrayList<String>();//correspondingness of the values are implicit in order written, BE CAREFUL!!!
		cata.add("red");
		cata.add("red");
		cata.add("red");	
		cata.add("red");
		cata.add("blue");
		cata.add("blue");
		cata.add("blue");
		cata.add("blue");
		cata.add("blue");
		cata.add("green");
		cata.add("green");
		cata.add("green");
		cata.add("green");
		cata.add("green");
		cata.add("gray");
		cata.add("gray");
		cata.add("gray");

		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(0);
		nums.add(10);
		nums.add(15);		
		nums.add(22);
		nums.add(30);
		nums.add(35);
		nums.add(50);
		nums.add(55);
		nums.add(55);
		nums.add(60);
		nums.add(76);
		nums.add(86);
		nums.add(90);
		nums.add(100);			
		nums.add(150);		
		nums.add(155);
		nums.add(160);

		Node n = new Node(cata, nums);

		// test(n);
		ArrayList<String> cats = n.getUniqueCata();
		

		for (int j = 0; j < cats.size(); j++) {

			int lastMax = 0;
			
			ArrayList<Integer> bounds = iterate(n, cats.get(j), lastMax, n.getNums().size());
			lastMax = bounds.get(1);

			System.out.println(cats.get(j) + "\nLower Bound: " + bounds.get(0) + "\nUpper Bound: " + bounds.get(1)
					+ "\nPurity: " + bounds.get(2) + "%\n");

		}

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

			System.out.println(cats.get(j) + ": " + purity * 100 + "% correct\n" + n.getNums().get(lowerThreshod)
					+ " lowerThreshod\n" + n.getNums().get(higherThreshold) + " UpperThreshod\n");

		}

	}

	public static ArrayList<Integer> iterate(Node n, String cata, int min, int max) {

		Collections.sort(n.getNums());
		ArrayList<String> catas = n.getCata();
		ArrayList<Integer> nums = n.getNums();
		ArrayList<Integer> results = new ArrayList<Integer>();

		int bestLow = 0;
		int bestHigh = 0;
		float bestSize = 0;
		float bestPurity = 0;
		int max2 = max;
		int min2 = min;

		// int low = 0;
		// int high = 0;

		while (max > min) {

			int size = 0;
			float purity = 0;
			float correct = 0;
			float incorrect = 0;
			int i = min;

			while (i < max) {

				if (catas.get(i) == cata) {

					correct++;

				} else {

					incorrect++;

				}

				i++;

			}

			purity = correct / (correct + incorrect);

			if (purity > bestPurity) {

				bestPurity = purity;
				bestLow = min;
				bestHigh = max;
				bestSize = max - min;

			}
			
			while (max > min) {

			
				float Innerpurity = 0;
				float Innercorrect = 0;
				float Innerincorrect = 0;
				int Inneri = min;

				while (Inneri < max) {

					if (catas.get(Inneri) == cata) {

						Innercorrect++;

					} else {

						Innerincorrect++;

					}

					Inneri++;

				}

				Innerpurity = Innercorrect / (Innercorrect + Innerincorrect);

				if (Innerpurity > bestPurity) {

					bestPurity = Innerpurity;
					bestLow = min;
					bestHigh = max;
					bestSize = max - min;

				}

				min++;

			}
			
			min = min2; 
			max--;

		}
					
		
		results.add(nums.get(bestLow));
		results.add(nums.get(bestHigh-1));
		results.add((int) (bestPurity * 100));

		return results;

	}

	private static int[] getDirection(Node n, String cata, int min, int max) {// decides
																				// to
																				// reduce
																				// the
																				// thresholds
																				// min
																				// or
																				// max

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

		while (j + 1 < max) {

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
