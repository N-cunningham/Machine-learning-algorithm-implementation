package C45;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

	static ArrayList<int[]> divisionSplits = new ArrayList<int[]>();
	
	
	public static void main(String[] args) throws IOException {
		
		String line2;
		String csvFile = "F:/Downloads/owls (1).csv";
		Scanner reader = new Scanner(System.in);

		System.out.println("What is your target column, starting at 0?: ");
		int targetCol = reader.nextInt();
		BufferedReader br2 = new BufferedReader(new FileReader(csvFile));
		int numberOfCols = 0;
		
		line2 = br2.readLine();
		String cvsSplitBy1 = ",";
		String[] cols1 = line2.split(cvsSplitBy1);			
		numberOfCols = cols1.length;
		System.out.println(cols1.length);
		
		ArrayList<Score> scores = new ArrayList<Score>();
	
		
		for(int p = 0; p < numberOfCols-1; p++){

			ArrayList<JoinedColumTuple> jctList = new ArrayList<JoinedColumTuple>();	
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			String line = br.readLine();
			
			while ((line = br.readLine()) != null) {
	
				String cvsSplitBy = ",";
				String[] cols = line.split(cvsSplitBy);	
				JoinedColumTuple jct = new JoinedColumTuple((String)cols[targetCol], (double) Double.valueOf(cols[p]));				
				jctList.add(jct);
				//System.out.println(p);
				
			}
			
			Node n = new Node(jctList);
			Score s = runTestOnAttribute(n);
			scores.add(s);
		
		}
		
		/* int i = 0;
		for(i = 0 ; i < 15; i++){
			
			JoinedColumTuple jct3 = new JoinedColumTuple("LongEaredOwl", 1.60);
			JoinedColums.add(jct3);
			JoinedColumTuple jct8 = new JoinedColumTuple("LongEaredOwl", 1.60);
			JoinedColums.add(jct8);
			JoinedColumTuple jct1 = new JoinedColumTuple("LongEaredOwl", 1.40);
			JoinedColums.add(jct1);
			JoinedColumTuple jct2 = new JoinedColumTuple("LongEaredOwl", 1.40);
			JoinedColums.add(jct2);		
			JoinedColumTuple jct4 = new JoinedColumTuple("LongEaredOwl", 1.50);
			JoinedColums.add(jct4);	
			JoinedColumTuple jct10 = new JoinedColumTuple("SnowyOwl", 5.60);
			JoinedColums.add(jct10);
			JoinedColumTuple jct0 = new JoinedColumTuple("SnowyOwl", 5.30);
			JoinedColums.add(jct0);
			JoinedColumTuple jct11 = new JoinedColumTuple("SnowyOwl", 4.80);
			JoinedColums.add(jct11);
			JoinedColumTuple jct5 = new JoinedColumTuple("BarnOwl", 4.70);
			JoinedColums.add(jct5);
			JoinedColumTuple jct7 = new JoinedColumTuple("BarnOwl", 3.60);
			JoinedColums.add(jct7);
			JoinedColumTuple jct6 = new JoinedColumTuple("BarnOwl", 4.00);
			JoinedColums.add(jct6);		
			JoinedColumTuple jct9 = new JoinedColumTuple("BarnOwl", 4.70);
			JoinedColums.add(jct9);
			
		}*/
	
	

		/*ArrayList<JoinedColumTuple> JoinedColums = new ArrayList<JoinedColumTuple>();
		Node n = new Node(JoinedColums);

		runTestOnAttribute(n);
		n.printData();*/
		

	}

	public static Score runTestOnAttribute(Node n) {

		int numberOfUniqueCata = n.getNumberOfCata();
		int numberOfDatapionts = n.getNums().size();
		ArrayList<String> catagories = n.getUniqueCata();

		System.out.println("\n" + numberOfUniqueCata + " Catagories detected");
		System.out.println(numberOfDatapionts + " Data records detected");
		
		ArrayList<int[]> allThresholds = generateAllThresholds(n);
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
			 
		 
		 }
		 
		double best = Collections.max(allThresholdsScores); 
		int indexOfBest = allThresholdsScores.indexOf(best);
		 
		//System.out.println("\n*************************************************************\nTHE WINNER  with a score of " + best + " is ");
		
		/*for (int k = 0; k <= n.getNumberOfCata(); k++) {
		  
			int[] Thresholds = allThresholds.get(indexOfBest);
			  
			 System.out.print(Thresholds[k] + "\t");
		 
		}*/
		
		//System.out.println(divisionSplits.size());
		
		
		for(int k = 0; k < divisionSplits.get(0).length; k++){
			
			double denominator = 0.0;
			int zeros = 0;
			
			for(int p = 0; p <  divisionSplits.get(k).length ; p++ ){
				
				if(divisionSplits.get(((indexOfBest * numberOfUniqueCata) + k))[p] == 0){
					
					zeros++;
					
				}
				
				denominator = denominator + ((double)((divisionSplits.get(((indexOfBest * numberOfUniqueCata) + k))[p])));
				
			}
			
			double percentage = 0.0;
			
			int max = 0;			
			int maxIndex = 0;
			
			for(int i = 0; i < divisionSplits.get(k).length; i++){
				
				if (max < divisionSplits.get(k)[i]){
					
					max = divisionSplits.get(k)[i];
					maxIndex = i;
					
				}
				
			}
			
			if (zeros == divisionSplits.get(k).length - 1 && (divisionSplits.get((indexOfBest * numberOfUniqueCata) + k)[maxIndex]) == 0.0 ){
				
				percentage = 0.0;
				System.out.println(catagories.get(maxIndex) + " " + percentage*100 + "%");
				
			}else{
				
				percentage = ((((double)(divisionSplits.get((indexOfBest * numberOfUniqueCata) + k)[maxIndex])) / (denominator)));
				System.out.println(catagories.get(maxIndex) + " " + percentage*100 + "%");
			
			}
			
		}
		
		for(int p = 0; p < catagories.size(); p++){

			System.out.println(catagories.get(p) + " " + Arrays.toString(divisionSplits.get((indexOfBest * numberOfUniqueCata) + p)));
		
		}
		
		System.out.println("\n");
		
		Score s = new Score(allThresholds.get(indexOfBest), best);
		System.out.println(s.toString());
		//Score s = new Score(allThresholds.get(0), 0); ==
		
		divisionSplits.removeAll(divisionSplits);
		return s;

	}

	public static ArrayList<int[]> generateAllThresholds(Node n) {

		int numberOfDatapoints = n.getNums().size();
		int numberOfCatagories = n.getNumberOfCata();
		ArrayList<int[]> allThresholds = new ArrayList<int[]>();
		
		for(int j = 0; j < 100000; j++){
			
			int[] threshold = new int[numberOfCatagories + 1];
			
			for(int i = 0; i < threshold.length -1 ; i++){
				
				threshold[0] = 0;
				threshold[i] = (int) (Math.random() * numberOfDatapoints);	
				threshold[numberOfCatagories] = numberOfDatapoints - 1;
				
						
				
			}
			
			int inorder = 0;
			
			for(int p = 0; p < threshold.length-1; p++){
				
				if(threshold[p] < threshold[p + 1]){
					
					inorder++;
					
				}
			
			}
			
			if(inorder == threshold.length-1 && (allThresholds.indexOf(threshold) == - 1)){
				
				allThresholds.add(threshold);
				
			}
		}
		
		/*for (int i = 0; i < allThresholds.size(); i++) {

			int[] threshold = allThresholds.get(i);
			
					
			
			for (int p = 0; p < numberOfCatagories + 1; p++) {

				System.out.print(threshold[p] + " ");

			}

			System.out.println(" ");

		}*/

		
		//System.out.println(allThresholds.size());
		return allThresholds;
		

	}

	public static Double getDivisionsInformationGain(ArrayList<Integer> midpoints, int numberOfTotalDatapionts, Node n, double AttributeInformationGain) {

		Double InformationGain = 0.0;
		Double entropyOfS = 0.0; // TODO define this value

		ArrayList<Double> entropyScores = getEntropy(n, midpoints);// Where the
		// magic
		// happens

		/*System.out.println("--------\nReturns\n--------");

		for (int i = 0; i < entropyScores.size(); i++) {

			System.out.println("Entropy " + i + ": " + entropyScores.get(i));

		}*/

		for (int i = 0; i < entropyScores.size(); i++) {

			int numberOfElementsinDivision = (midpoints.get(i + 1) - midpoints.get(i));

			InformationGain = InformationGain - (((double) numberOfElementsinDivision / (double) numberOfTotalDatapionts) * entropyScores.get(i));

			//System.out.println(InformationGain);

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

		for (int i = 0; i < numberOfTotalDatapionts ; i++) {

			if (UniqueCata.contains(n.getCata().get(i))) {

				UniqueCataScores[UniqueCata.indexOf(n.getCata().get(i))]++;

			}

		}

		for (int j = 0; j < UniqueCataScores.length; j++) {

			InformationGain = InformationGain - (((double) UniqueCataScores[j] / (double) numberOfTotalDatapionts)
					* ((Math.log(((double) UniqueCataScores[j] / (double) numberOfTotalDatapionts)))/ (Math.log(2))	)	);

		}
		
		
		//System.out.println(InformationGain);
		return InformationGain;

	}

	public static ArrayList<Double> getEntropy(Node n, ArrayList<Integer> midpoints) {

		ArrayList<String> catagories = n.getUniqueCata();	
		ArrayList<Double> midpointsEntropy = new ArrayList<Double>();
		ArrayList<String> catagorisesDone = new ArrayList<String>();
		

		/*System.out.print(
				"\n#####################################################################################\nNEW RUN\n");
		System.out.print(
				"#####################################################################################\nThresholds: ");
		for (int k = 0; k < midpoints.size(); k++) {

			System.out.print(midpoints.get(k) + " ");

		}
		System.out.println(" ");*/

		for (int j = 1; j < midpoints.size(); j++) {

			double correct = 0;
			int[] catagoriesScore = new int[n.getNumberOfCata()];
			double totalDataPoints = 0;
			double entropy = 0;
			String cata = n.getCata().get(midpoints.get(j - 1));

			if (catagorisesDone.contains(cata)) {// TODO THis only works once, must account for case where next element isalso already catagorised

				cata = n.getCata().get((midpoints.get(j - 1)) + 1);

			}

			catagorisesDone.add(cata);
			//System.out.println("Catagorising " + cata + "\n------------------");
			int lowerbound = midpoints.get(j - 1);

			if (j == 1) {

				lowerbound--;

			}

			for (int i = midpoints.get(j); i > lowerbound; i--) {

				for(int p = 0; p < catagories.size(); p ++){
					
					if (n.getCata().get(i).equals(catagories.get(p))) {
	
						catagoriesScore[p]++;
	
					}
					
				}
				
				//System.out.println(n.getCata().get(i) + " at index " + i);				
				totalDataPoints++;
			}

			entropy = 0;
			
			for(int k = 0; k < catagoriesScore.length; k++){
				
				
				if(catagoriesScore[k] != 0){
					entropy = entropy + ((-(((double)catagoriesScore[k]) / totalDataPoints)) * ((Math.log(((double)catagoriesScore[k]) / totalDataPoints))) / ( Math.log(2)));
				}
				
			}
			
			//System.out.println("Entropy: " + entropy + "\n");
			
			divisionSplits.add(catagoriesScore);

			midpointsEntropy.add(entropy);
		}

		return midpointsEntropy;

	}

}
