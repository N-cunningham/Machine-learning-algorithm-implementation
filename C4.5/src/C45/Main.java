package C45;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Collection;
import java.util.Collections;

public class Main {

	public static void main(String[] args) {

		ArrayList<String> cata = new ArrayList<String>();// correspondingness of the values are  implicit in order written CAREFUL!!!
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

		// test(n);
		/*
		 * ArrayList<String> cats = n.getUniqueCata();
		 * 
		 * 
		 * for (int j = 0; j < cats.size(); j++) {
		 * 
		 * int lastMax = 0;
		 * 
		 * ArrayList<Integer> bounds = iterate(n, cats.get(j), lastMax,
		 * n.getNums().size()); lastMax = bounds.get(1);
		 * 
		 * System.out.println(cats.get(j) + "\nLower Bound: " + bounds.get(0) +
		 * "\nUpper Bound: " + bounds.get(1) + "\nPurity: " + bounds.get(2) +
		 * "%\n");
		 * 
		 * }
		 */

		int numberOfUniqueCata = n.getNumberOfCata();
		int	numberOfDatapionts = n.getNums().size();
		
		System.out.println(numberOfUniqueCata + " Catagories detected");
		System.out.println(numberOfDatapionts + " Data records detected");
		
		
		ArrayList<Integer> midpoints = new ArrayList<Integer>();
		
		int newMidpoint = 0;
		int increase = (numberOfDatapionts / numberOfUniqueCata);
		
		for(int c = 0; c < numberOfUniqueCata + 1; c++){	//TODO do this for for all possible permutations and return best information gain		
			
			newMidpoint = increase * c;
			midpoints.add(newMidpoint);
			
			
		}
	
		System.out.println("\nMidpoints");
		for(int i = 0; i < midpoints.size(); i++){
			
			System.out.println(midpoints.get(i));
			
			
		}
		
		System.out.println("");
		
		ArrayList<Double> entropyScores = getEntropy(n, midpoints);//Where the magic happens
		
		System.out.println("\n--------\nReturns\n--------");
		for(int i = 0; i < entropyScores.size(); i++){
			
			System.out.println("Entropy "+ i + ": " + entropyScores.get(i));
			
			
		}
		
		System.out.println("\n");
		
		Double InformationGain = getInformationGain(entropyScores, midpoints, numberOfDatapionts);
		System.out.println("\nInformationGain: " + InformationGain);

	}
	
	public static Double getInformationGain(ArrayList<Double> entropyScores, ArrayList<Integer> midpoints, int numberOfTotalDatapionts){		
		
		Double InformationGain = 0.0;
		Double entropyOfS = 0.0; //TODO define this value
		
		for(int i = 0; i < entropyScores.size(); i++){
			
			int numberOfElements = (midpoints.get(i + 1) - midpoints.get(i)) ;
						
			InformationGain = InformationGain - (( (double) numberOfElements / (double) numberOfTotalDatapionts) * entropyScores.get(i));
			
			System.out.println(InformationGain);
			
		}
		
		InformationGain = entropyOfS + InformationGain;//negative created in for loop, so positive here  
		
		return InformationGain;
		
	}
	
	
	
	
	public static ArrayList<Double> getEntropy(Node n, ArrayList<Integer> midpoints){
		
		//int totalDataPoints = n.getNums().size();
		ArrayList<Double> midpointsEntropy = new ArrayList<Double>();
		
		ArrayList<String> catagorisesDone = new ArrayList<String>();
		
		for(int j = 1; j < midpoints.size(); j++){
			
			double correct = 0;
			double totalDataPoints = 0;
			double entropy = 0;			
			
			String cata = n.getCata().get(midpoints.get(j - 1));
			
			if(catagorisesDone.contains(cata)){//TODO THis only works once, must account for case where next element is also already catagorised
												
				cata = n.getCata().get((midpoints.get(j - 1)) + 1);
								
			}
						
			catagorisesDone.add(cata);
			
			System.out.println("Catagorising "+ cata + "\n------------------");
			 
			for(int i = midpoints.get(j); i > midpoints.get(j - 1); i--){
				
				
				if(n.getCata().get(i) == cata){
					
					correct++;
					
					
				}
				
				System.out.println(n.getCata().get(i) + " at index " + i);
				
				//prev = n.getCata().get(i);
				totalDataPoints++;
			}
			
			
			double inncorrect = totalDataPoints - correct;
			
			if(inncorrect == (double) 0){
				
				entropy = 0;
				
			}else{
				
			entropy = - ((correct/totalDataPoints)* Math.log(correct/totalDataPoints)) - ((inncorrect/totalDataPoints)*Math.log(inncorrect/totalDataPoints));
			
			}
			
			System.out.println("Number correct: " + correct);
			System.out.println("Number inncorrect: " + inncorrect);
			
			 
			
			System.out.println("Entropy: " + entropy  + "\n");
			
			midpointsEntropy.add(entropy);
		}
		
		return midpointsEntropy;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static void partition(Node n) {

		ArrayList<Integer> partitions = n.gatInitialPartitions();

		// TODO loop with new partitions and compare for the best combinedpurity

		double entorpy = 0;
		double datapoints = 0;
		int partition = 0;
		int x = 0;

		for (int i = partitions.get(0); i < partitions.get(1); x++) {// TODO loop this for all initial partitions and get combined purity

			i++;

			double localEntorpy = 0;
			double localCorrect = 0;
			double localDatapoints = 0;
			String prev = n.getCata().get(0);

			for (int j = partitions.get(0); j < i; j++) {

				if (n.getCata().get(j) == prev) {

					localCorrect++;

				}

				prev = n.getCata().get(j);

				localDatapoints++;

			}

			//localEntorpy = ((localCorrect / localDatapoints) * log(localCorrect / localDatapoints)) - ;

			if (localEntorpy > entorpy) {

				entorpy = localEntorpy;
				partition = i;
				datapoints = localDatapoints;

			} else if (localEntorpy == entorpy) {

				entorpy = localEntorpy;
				partition = i;
				datapoints = localDatapoints;

			}

		}

		System.out.println("\nSTATS\npurity: " + entorpy * 100 + " \ndatapoints: " + datapoints + "\npartition: "
				+ n.getNums().get(partition));

	}

	/*
	 * public static void test(Node n) {
	 * 
	 * Collections.sort(n.getNums()); // Collections.sort(n.getCata());
	 * 
	 * int numberOfCata = n.getNumberOfCata(); ArrayList<String> cats =
	 * n.getUniqueCata(); int lowerThreshod = 0; int higherThreshold = 0;
	 * 
	 * for (int j = 0; j < cats.size(); j++) {// get best in cat
	 * 
	 * int max = n.getNums().size(); int min = higherThreshold; int diection[] =
	 * getDirection(n, cats.get(j), min, max); float purity = 0;
	 * 
	 * int accuracy = 0;
	 * 
	 * while (min < max || accuracy < 100) {
	 * 
	 * diection = getDirection(n, cats.get(j), min, max); min = (min +
	 * diection[0]); max = (max - diection[1]);
	 * 
	 * int correct = 0; int i = min;
	 * 
	 * while (i < max) {
	 * 
	 * if (n.getCata().get(i) == cats.get(j)) {
	 * 
	 * correct++;
	 * 
	 * }
	 * 
	 * i++;
	 * 
	 * }
	 * 
	 * float localPurity = correct / (float) (max - min);
	 * 
	 * if (localPurity > purity) {
	 * 
	 * purity = localPurity; lowerThreshod = min; higherThreshold = max;
	 * 
	 * }
	 * 
	 * // System.out.println("Catagory " + cats.get(j)); //
	 * System.out.println("Correct: " + ((float) localPurity * 100) // +
	 * "%\n");// store accuracy++;
	 * 
	 * }
	 * 
	 * System.out.println(cats.get(j) + ": " + purity * 100 + "% correct\n" +
	 * n.getNums().get(lowerThreshod) + " lowerThreshod\n" +
	 * n.getNums().get(higherThreshold) + " UpperThreshod\n");
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public static ArrayList<Integer> iterate(Node n, String cata, int min,
	 * int max) {
	 * 
	 * Collections.sort(n.getNums()); ArrayList<String> catas = n.getCata();
	 * ArrayList<Integer> nums = n.getNums(); ArrayList<Integer> results = new
	 * ArrayList<Integer>();
	 * 
	 * int bestLow = 0; int bestHigh = 0; float bestSize = 0; float bestPurity =
	 * 0; int max2 = max; int min2 = min;
	 * 
	 * // int low = 0; // int high = 0;
	 * 
	 * while (max > min) {
	 * 
	 * int size = 0; float purity = 0; float correct = 0; float incorrect = 0;
	 * int i = min;
	 * 
	 * while (i < max) {
	 * 
	 * if (catas.get(i) == cata) {
	 * 
	 * correct++;
	 * 
	 * } else {
	 * 
	 * incorrect++;
	 * 
	 * }
	 * 
	 * i++;
	 * 
	 * }
	 * 
	 * purity = correct / (correct + incorrect);
	 * 
	 * if (purity > bestPurity) {
	 * 
	 * bestPurity = purity; bestLow = min; bestHigh = max; bestSize = max - min;
	 * 
	 * }
	 * 
	 * while (max > min) {
	 * 
	 * float Innerpurity = 0; float Innercorrect = 0; float Innerincorrect = 0;
	 * int Inneri = min;
	 * 
	 * while (Inneri < max) {
	 * 
	 * if (catas.get(Inneri) == cata) {
	 * 
	 * Innercorrect++;
	 * 
	 * } else {
	 * 
	 * Innerincorrect++;
	 * 
	 * }
	 * 
	 * Inneri++;
	 * 
	 * }
	 * 
	 * Innerpurity = Innercorrect / (Innercorrect + Innerincorrect);
	 * 
	 * if (Innerpurity > bestPurity) {
	 * 
	 * bestPurity = Innerpurity; bestLow = min; bestHigh = max; bestSize = max -
	 * min;
	 * 
	 * }
	 * 
	 * min++;
	 * 
	 * }
	 * 
	 * min = min2; max--;
	 * 
	 * }
	 * 
	 * results.add(nums.get(bestLow)); results.add(nums.get(bestHigh - 1));
	 * results.add((int) (bestPurity * 100));
	 * 
	 * return results;
	 * 
	 * }
	 * 
	 * private static int[] getDirection(Node n, String cata, int min, int max)
	 * {// decides // to // reduce // the // thresholds // min // or // max
	 * 
	 * ArrayList<String> catas = n.getCata(); ArrayList<Integer> nums =
	 * n.getNums();
	 * 
	 * int leftCorrect = 0; int i = min;
	 * 
	 * while (i < max - 1) {
	 * 
	 * if (n.getCata().get(i) == cata) {
	 * 
	 * leftCorrect++;
	 * 
	 * }
	 * 
	 * i++;
	 * 
	 * }
	 * 
	 * int rightCorrect = 0; int j = min;
	 * 
	 * while (j + 1 < max) {
	 * 
	 * if (n.getCata().get(j) == cata) {
	 * 
	 * rightCorrect++;
	 * 
	 * }
	 * 
	 * j++;
	 * 
	 * }
	 * 
	 * if (rightCorrect >= leftCorrect) {
	 * 
	 * int[] direction = new int[] { 0, 1 }; return direction;
	 * 
	 * } else {
	 * 
	 * int[] direction = new int[] { 1, 0 }; return direction;
	 * 
	 * }
	 * 
	 * }
	 */

}
