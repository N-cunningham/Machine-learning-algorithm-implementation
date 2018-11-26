package C45;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.util.Collection;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {

		ArrayList<JoinedColumTuple> JoinedColums = new ArrayList<JoinedColumTuple>();
		
		JoinedColumTuple jct0 = new JoinedColumTuple("red", 12.0);
		JoinedColums.add(jct0);
		JoinedColumTuple jct1 = new JoinedColumTuple("red", 15.0);
		JoinedColums.add(jct1);
		JoinedColumTuple jct2 = new JoinedColumTuple("red", 18.0);
		JoinedColums.add(jct2);
		JoinedColumTuple jct3 = new JoinedColumTuple("blue", 14.0);
		JoinedColums.add(jct3);
		JoinedColumTuple jct4 = new JoinedColumTuple("blue", 6.0);
		JoinedColums.add(jct4);
		JoinedColumTuple jct5 = new JoinedColumTuple("blue", 9.0);
		JoinedColums.add(jct5);
		JoinedColumTuple jct6 = new JoinedColumTuple("green", 1.0);
		JoinedColums.add(jct6);
		JoinedColumTuple jct7 = new JoinedColumTuple("green", 3.0);
		JoinedColums.add(jct7);
		JoinedColumTuple jct8 = new JoinedColumTuple("green", 2.0);
		JoinedColums.add(jct8);

		Node n = new Node(JoinedColums);

		runTestOnAttribute(n);
		n.printData();

	}

	public static Score runTestOnAttribute(Node n) {

		int numberOfUniqueCata = n.getNumberOfCata();
		int numberOfDatapionts = n.getNums().size();

		System.out.println(numberOfUniqueCata + " Catagories detected");
		System.out.println(numberOfDatapionts + " Data records detected");

		// ArrayList<Integer> midpoints = new ArrayList<Integer>();

		// int newMidpoint = 0;
		// int increase = (numberOfDatapionts / numberOfUniqueCata);

		/*
		 * for (int c = 0; c < numberOfUniqueCata + 1; c++) { // TODO do this
		 * for for all possible permutations and return best information gain
		 * 
		 * newMidpoint = increase * c; midpoints.add(newMidpoint);
		 * 
		 * }
		 * 
		 * System.out.println("\nMidpoints"); for (int i = 0; i <
		 * midpoints.size(); i++) {
		 * 
		 * System.out.println(midpoints.get(i));
		 * 
		 * }
		 */

		System.out.println("");

		System.out.println("\n");

		ArrayList<int[]> allThresholds = getThresholds(n);
		ArrayList<Double> allThresholdsScores = new ArrayList<Double>();

		
		 for (int i = 0; i < allThresholds.size(); i++) {
		 
			 int[] thresholds = allThresholds.get(i); ArrayList<Integer>
			 thresholdsAsList = new ArrayList<Integer>();
			 
			 for (int p = 0; p < allThresholds.get(i).length; p++) {
			 
				 thresholdsAsList.add(thresholds[p]);
			 
			 }
			 
			 double AttributeInformationGain = getAttributeInformationGain(n,
			 numberOfDatapionts);
			 
			 double InformationGain =
			 getDivisionsInformationGain(thresholdsAsList, numberOfDatapionts, n,
			 AttributeInformationGain);
			 
			 allThresholdsScores.add(InformationGain);
			 
			 System.out.println("\nInformationGain: " + InformationGain);
		 
		 }
		 
		double best = Collections.max(allThresholdsScores); 
		int indexOfBest = allThresholdsScores.indexOf(best);
		 
		System.out.println("\n*************************************************************\nTHE WINNER  with a score of " + best + " is ");
		
		for (int k = 0; k <= n.getNumberOfCata(); k++) {
		  
			int[] Thresholds = allThresholds.get(indexOfBest);
			  
			 System.out.print(Thresholds[k] + "\t");
		 
		}
		
		Score s = new Score(allThresholds.get(indexOfBest), best);
		
		return s;

	}

	public static ArrayList<int[]> getThresholds(Node n) {

		int numberOfDatapoints = n.getNums().size();
		int numberOfCatagories = n.getNumberOfCata();
		ArrayList<int[]> allThresholds = new ArrayList<int[]>();

		for (int j = 0; j < numberOfDatapoints; j++) {

			int[] threshold = new int[numberOfCatagories + 1];

			int res = j;

			for (int i = 1; i < numberOfCatagories + 1; i++) {

				if (res >= (numberOfDatapoints - numberOfCatagories - 2)) {

					threshold[i] = numberOfDatapoints - 3;

				} else {

					res = res + i;
					threshold[i] = res;

				}

			}

			threshold[0] = 0;
			threshold[numberOfCatagories] = numberOfDatapoints - 1;
			allThresholds.add(threshold);

		}

		for (int i = 0; i < allThresholds.size(); i++) {

			int[] threshold = allThresholds.get(i);

			for (int p = 0; p < numberOfCatagories + 1; p++) {

				System.out.print(threshold[p] + " ");

			}

			System.out.println(" ");

		}

		return allThresholds;

	}

	public static Double getDivisionsInformationGain(ArrayList<Integer> midpoints, int numberOfTotalDatapionts, Node n,
			double AttributeInformationGain) {

		Double InformationGain = 0.0;
		Double entropyOfS = 0.0; // TODO define this value

		ArrayList<Double> entropyScores = getEntropy(n, midpoints);// Where the
		// magic
		// happens

		System.out.println("--------\nReturns\n--------");

		for (int i = 0; i < entropyScores.size(); i++) {

			System.out.println("Entropy " + i + ": " + entropyScores.get(i));

		}

		for (int i = 0; i < entropyScores.size(); i++) {

			int numberOfElementsinDivision = (midpoints.get(i + 1) - midpoints.get(i));

			InformationGain = InformationGain - (((double) numberOfElementsinDivision / (double) numberOfTotalDatapionts) * entropyScores.get(i));

			System.out.println(InformationGain);

		}

		InformationGain = AttributeInformationGain + InformationGain;// negative
																		// created
																		// in
																		// for
																		// loop,
																		// so
																		// positive
																		// here

		return InformationGain;

	}

	public static Double getAttributeInformationGain(Node n, int numberOfTotalDatapionts) {

		double InformationGain = 0.0;

		ArrayList<String> UniqueCata = n.getUniqueCata();
		int numberOfCata = n.getNumberOfCata();
		int[] UniqueCataScores = new int[numberOfCata];

		for (int i = 0; i < numberOfTotalDatapionts - 1; i++) {

			if (UniqueCata.contains(n.getCata().get(i))) {

				UniqueCataScores[UniqueCata.indexOf(n.getCata().get(i))]++;

			}

		}

		for (int j = 0; j < UniqueCataScores.length; j++) {

			InformationGain = InformationGain - (((double) UniqueCataScores[j] / (double) numberOfTotalDatapionts)
					* Math.log(((double) UniqueCataScores[j] / (double) numberOfTotalDatapionts)));

		}

		return InformationGain;

	}

	public static ArrayList<Double> getEntropy(Node n, ArrayList<Integer> midpoints) {

		// int totalDataPoints = n.getNums().size();
		ArrayList<Double> midpointsEntropy = new ArrayList<Double>();

		ArrayList<String> catagorisesDone = new ArrayList<String>();

		System.out.print(
				"\n#####################################################################################\nNEW RUN\n");
		System.out.print(
				"#####################################################################################\nThresholds: ");
		for (int k = 0; k < midpoints.size(); k++) {

			System.out.print(midpoints.get(k) + " ");

		}
		System.out.println(" ");

		for (int j = 1; j < midpoints.size(); j++) {

			double correct = 0;
			double totalDataPoints = 0;
			double entropy = 0;

			String cata = n.getCata().get(midpoints.get(j - 1));

			if (catagorisesDone.contains(cata)) {// TODO THis only works once,
													// must account for case
													// where next element is
													// also already catagorised

				cata = n.getCata().get((midpoints.get(j - 1)) + 1);

			}

			catagorisesDone.add(cata);

			System.out.println("Catagorising " + cata + "\n------------------");

			int lowerbound = midpoints.get(j - 1);

			if (j == 1) {

				lowerbound--;

			}

			for (int i = midpoints.get(j); i > lowerbound; i--) {

				if (n.getCata().get(i) == cata) {

					correct++;

				}

				System.out.println(n.getCata().get(i) + " at index " + i);

				// prev = n.getCata().get(i);
				totalDataPoints++;
			}

			double inncorrect = totalDataPoints - correct;

			if (inncorrect == (double) 0) {

				entropy = 0;

			} else if (correct == (double) 0) {

				entropy = (double) 0 + ((-(inncorrect / totalDataPoints)) * Math.log(inncorrect / totalDataPoints));

			} else {

				entropy = ((-(correct / totalDataPoints)) * Math.log(correct / totalDataPoints))
						+ ((-(inncorrect / totalDataPoints)) * Math.log(inncorrect / totalDataPoints));

			}

			System.out.println("Number correct: " + correct);
			System.out.println("Number inncorrect: " + inncorrect);

			System.out.println("Entropy: " + entropy + "\n");

			midpointsEntropy.add(entropy);
		}

		return midpointsEntropy;

	}

}
