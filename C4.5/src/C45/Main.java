package C45;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Collection;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {

		ArrayList<String> cata = new ArrayList<String>();// correspondingness of
															// the values are
															// implicit in order
															// written
															// CAREFUL!!!
		cata.add("red");
		cata.add("red");
		cata.add("red");
		cata.add("blue");
		cata.add("red");
		cata.add("blue");
		cata.add("blue");
		cata.add("blue");
		cata.add("green");
		cata.add("green");
		cata.add("green");
		cata.add("green");
		cata.add("green");

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

		Node n = new Node(cata, nums);

		runTestOnAttribute(n);

	}
	
	

	public static void runTestOnAttribute(Node n) {

		int numberOfUniqueCata = n.getNumberOfCata();
		int numberOfDatapionts = n.getNums().size();

		System.out.println(numberOfUniqueCata + " Catagories detected");
		System.out.println(numberOfDatapionts + " Data records detected");

		ArrayList<Integer> midpoints = new ArrayList<Integer>();

		int newMidpoint = 0;
		int increase = (numberOfDatapionts / numberOfUniqueCata);

		for (int c = 0; c < numberOfUniqueCata + 1; c++) { // TODO do this for
															// for all possible
															// permutations and
															// return best
															// iformation gain

			newMidpoint = increase * c;
			midpoints.add(newMidpoint);

		}

		System.out.println("\nMidpoints");
		for (int i = 0; i < midpoints.size(); i++) {

			System.out.println(midpoints.get(i));

		}

		System.out.println("");

		ArrayList<Double> entropyScores = getEntropy(n, midpoints);// Where the
																	// magic
																	// happens

		System.out.println("\n--------\nReturns\n--------");
		for (int i = 0; i < entropyScores.size(); i++) {

			System.out.println("Entropy " + i + ": " + entropyScores.get(i));

		}

		System.out.println("\n");

		double AttributeInformationGain = getAttributeInformationGain(n, numberOfDatapionts);
		double InformationGain = getDivisionsInformationGain(entropyScores, midpoints, numberOfDatapionts, n,
				AttributeInformationGain);
		System.out.println("\nInformationGain: " + InformationGain);

	}
	
	

	public static Double getDivisionsInformationGain(ArrayList<Double> entropyScores, ArrayList<Integer> midpoints,
			int numberOfTotalDatapionts, Node n, double AttributeInformationGain) {

		Double InformationGain = 0.0;
		Double entropyOfS = 0.0; // TODO define this value

		for (int i = 0; i < entropyScores.size(); i++) {

			int numberOfElementsinDivision = (midpoints.get(i + 1) - midpoints.get(i));

			InformationGain = InformationGain
					- (((double) numberOfElementsinDivision / (double) numberOfTotalDatapionts) * entropyScores.get(i));

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

			for (int i = midpoints.get(j); i > midpoints.get(j - 1); i--) {

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
