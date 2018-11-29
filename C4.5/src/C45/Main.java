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

	private static final int thresholdAccuracy = 90000;//sorry
	static ArrayList<int[]> divisionSplits = new ArrayList<int[]>();
	static ArrayList<Score> treeScores = new ArrayList<Score>();
	static ArrayList<String> UniqueCata = new ArrayList<String>();

	public static /*Score*/ void loadData(String filename) throws IOException {

		String line2;
		String test;
		String csvFile = filename;
		Scanner reader = new Scanner(System.in);

		System.out.println("What is your target column, starting at 0?: ");
		int targetCol = reader.nextInt();
		BufferedReader br2 = new BufferedReader(new FileReader(csvFile));
		BufferedReader br3 = new BufferedReader(new FileReader(csvFile));
		int numberOfCols = 0;
		
		/*System.out.println("What % of data do you want to use for training? (type in the form xx.x)");
		double testTrainSpilt = reader.nextDouble();*/
		
		line2 = br2.readLine();
		String cvsSplitBy1 = ",";
		String[] cols1 = line2.split(cvsSplitBy1);
		numberOfCols = cols1.length;
		//System.out.println(cols1.length);

		ArrayList<Score> scores = new ArrayList<Score>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		ArrayList<JoinedColumTuple> jctList = new ArrayList<JoinedColumTuple>();
		
		int dataPoints = 0;
		
		while ((test = br3.readLine()) != null) {
			dataPoints++;
		}
		
		for (int p = 0; p < numberOfCols - 1; p++) {

			jctList.removeAll(jctList);
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			String line = br.readLine();
			
			while ((line = br.readLine()) != null) {

				String cvsSplitBy = ",";
				String[] cols = line.split(cvsSplitBy);
				JoinedColumTuple jct = new JoinedColumTuple((String) cols[targetCol], (double) Double.valueOf(cols[p]) , p);
				jctList.add(jct);

			}
			
			Node n = new Node(jctList, p);
			nodes.add(n);
			UniqueCata = n.getUniqueCata();
			Score s = runTestOnAttribute(n, p);
			scores.add(s);

		}

		double max = 0;
		int maxindex = 0;

		for (int i = 0; i < scores.size(); i++) {

			if (max < scores.get(i).getInformationGain()) {

				max = scores.get(i).getInformationGain();
				maxindex = i;

			}

		}

		treeScores.add(scores.get(maxindex));
		
		Score winner = scores.get(maxindex);
		//System.out.println("Attribute " + maxindex + "wins" + winner.toString());
		ArrayList<Node> nextNodes = splitDataOnThresholds(winner.getThresholds(), nodes.get(maxindex), nodes);

		System.out.println(nextNodes.size());

		for(int p = 0; p < nextNodes.size(); p++){
			
			System.out.println(nextNodes.get(p).getJoinedColums().toString());
			System.out.println("");
			
		}
		
		//again(nextNodes);
		//return scores.get(maxindex);

	}
	
	
	public static void again (ArrayList<Node> nodes ){//TODO SHOULD BE COMPAIRING ATTRIBUTE NUMBER NOT TARGET	
		
		for (int p = 0; p < nodes.size(); p++) {
			
			//System.out.print("\n\n NEW PARENT\n \n");
		
			ArrayList<Score> localScores = new ArrayList<Score>();
			ArrayList<Node> localNodes = new ArrayList<Node>();
			
			Node nMixed = nodes.get(p);
			ArrayList<String> catagoriesInNode = nMixed.getUniqueCata();
			ArrayList<ArrayList<JoinedColumTuple>> jctMetaList = new ArrayList<ArrayList<JoinedColumTuple>>();
			
						
			for(int i = 0; i < catagoriesInNode.size(); i++){
			
				ArrayList<JoinedColumTuple> jctList = new ArrayList<JoinedColumTuple>();
				
				for(int j = 0; j < nMixed.getCata().size(); j ++){
					
					if(nMixed.getJoinedColums().get(j).equals(catagoriesInNode.get(i))){//##BUG HERER ### SHOULD BE COMPAIRING ATTRIBUTE NUMBER NOT TARGET
						
						jctList.add(nMixed.getJoinedColums().get(j));
						//System.out.print(nMixed.getJoinedColums().get(j).toString() + "\t");
						
					}
					
				}
				
				//System.out.print("\n.\n");
				
				jctMetaList.add(jctList);
				
			}
		
			
	
			double Localmax = 0;
			int Localmaxindex = 0;
			/*
			for (int i = 0; i < scores.size(); i++) {
	
				if (max < scores.get(i).getInformationGain()) {
	
					max = scores.get(i).getInformationGain();
					maxindex = i;
	
				}
	
			}
	
			treeScores.add(scores.get(maxindex));
			
			Score winner = scores.get(maxindex);
			//ArrayList<Node> nextNodes = splitDataOnThresholds(winner.getThresholds(), nodes.get(maxindex), nodes);*/

			
			for(int i = 0; i < jctMetaList.size(); i++){
				
				Node n1 = new Node(jctMetaList.get(i), i);
				localNodes.add(n1);
				Score s = runTestOnAttribute(n1, i);
				localScores.add(s);
				
			}
		
			
			for (int i = 0; i < localScores.size(); i++) {
				
				if (Localmax < localScores.get(i).getInformationGain()) {
	
					Localmax = localScores.get(i).getInformationGain();
					Localmaxindex = i;
	
				}
	
			}
			
			
			treeScores.add(localScores.get(Localmaxindex));
			
		}
		
	}
	
	
	
	
	
	public static ArrayList<Node> splitDataOnThresholds(int[] midpoints, Node nodeToSplit, ArrayList<Node> nodes){
		
		ArrayList<Node> Metajct = new ArrayList<Node>();
		
		for(int i = 0; i < midpoints.length-1; i++){
			
			ArrayList<JoinedColumTuple> jct = new ArrayList<JoinedColumTuple>();
			
			for(int k = 0; k < nodes.size(); k++){							
							
				for(int p = 0; p < nodeToSplit.getUnsortedJoinedColums().size(); p++){
				
					int index = nodeToSplit.getUnsortedJoinedColums().indexOf(nodeToSplit.getJoinedColums().get(p));
					
					if(midpoints[i] <= index && index <= midpoints[i + 1]){
						
						jct.add(nodes.get(k).getUnsortedJoinedColums().get(p));
						
					}
									
				}	
								
			}	
			
			Node n = new Node(jct, 999);//999 denotes mixed columns
			Metajct.add(n);
		
		}
		
		return Metajct;
		
	}

	public static Score runTestOnAttribute(Node n, int attributeIndex) {

		int numberOfUniqueCata = n.getNumberOfCata();
		int numberOfDatapionts = n.getNums().size();
		ArrayList<String> catagories = n.getUniqueCata();

		ArrayList<int[]> allThresholds = generateAllThresholds(n);
		ArrayList<Double> allThresholdsScores = new ArrayList<Double>();

		for (int i = 0; i < allThresholds.size(); i++) {

			int[] thresholds = allThresholds.get(i);
			ArrayList<Integer> thresholdsAsList = new ArrayList<Integer>();

			for (int p = 0; p < allThresholds.get(i).length; p++) {

				thresholdsAsList.add(thresholds[p]);

			}

			double AttributeInformationGain = getAttributeInformationGain(n, numberOfDatapionts);

			double InformationGain = getDivisionsInformationGain(thresholdsAsList, numberOfDatapionts, n,
					AttributeInformationGain);

			allThresholdsScores.add(InformationGain);

		}

		double best = Collections.max(allThresholdsScores);
		int indexOfBest = allThresholdsScores.indexOf(best);

		
		for (int k = 0; k < divisionSplits.get(0).length; k++) {

			double denominator = 0.0;
			int zeros = 0;

			for (int p = 0; p < divisionSplits.get(k).length; p++) {

				if (divisionSplits.get(((indexOfBest * numberOfUniqueCata) + k))[p] == 0) {

					zeros++;

				}

				denominator = denominator
						+ ((double) ((divisionSplits.get(((indexOfBest * numberOfUniqueCata) + k))[p])));

			}

			double percentage = 0.0;

			int max = 0;
			int maxIndex = 0;

			for (int i = 0; i < divisionSplits.get(k).length; i++) {

				if (max < divisionSplits.get(k)[i]) {

					max = divisionSplits.get(k)[i];
					maxIndex = i;

				}

			}
			
			

			if (zeros == divisionSplits.get(k).length - 1
					&& (divisionSplits.get((indexOfBest * numberOfUniqueCata) + k)[maxIndex]) == 0.0) {

				percentage = 0.0;

			} else {

				percentage = ((((double) (divisionSplits.get((indexOfBest * numberOfUniqueCata) + k)[maxIndex]))
						/ (denominator)));

			}

		}

		Score s = new Score(allThresholds.get(indexOfBest), best, attributeIndex, n);
		divisionSplits.removeAll(divisionSplits);		
		
		System.out.println("\nSplitting Attribute " + attributeIndex + " with these values");
		
		for(int i = 0; i < allThresholds.get(indexOfBest).length; i++){
			
			System.out.print(n.getNums().get(allThresholds.get(indexOfBest)[i]) + "\t");
			
		}
		
		System.out.println(" ");
		
		return s;

	}

	public static ArrayList<int[]> generateAllThresholds(Node n) {

		int numberOfDatapoints = n.getNums().size();
		int numberOfCatagories = n.getNumberOfCata();
		ArrayList<int[]> allThresholds = new ArrayList<int[]>();

		for (int j = 0; j < thresholdAccuracy; j++) {

			int[] threshold = new int[numberOfCatagories + 1];

			for (int i = 0; i < threshold.length - 1; i++) {

				threshold[0] = 0;
				threshold[i] = (int) (Math.random() * numberOfDatapoints);
				threshold[numberOfCatagories] = numberOfDatapoints - 1;

			}

			int inorder = 0;

			for (int p = 0; p < threshold.length - 1; p++) {

				if (threshold[p] < threshold[p + 1]) {

					inorder++;

				}

			}

			if (inorder == threshold.length - 1 && (allThresholds.indexOf(threshold) == -1)) {

				allThresholds.add(threshold);

			}
		}

		return allThresholds;

	}

	public static Double getDivisionsInformationGain(ArrayList<Integer> midpoints, int numberOfTotalDatapionts, Node n,
			double AttributeInformationGain) {

		Double InformationGain = 0.0;
		Double entropyOfS = 0.0; // TODO define this value

		ArrayList<Double> entropyScores = getEntropy(n, midpoints);// Where the magic happens

		for (int i = 0; i < entropyScores.size(); i++) {

			int numberOfElementsinDivision = (midpoints.get(i + 1) - midpoints.get(i));

			InformationGain = InformationGain
					- (((double) numberOfElementsinDivision / (double) numberOfTotalDatapionts) * entropyScores.get(i));

		}

		InformationGain = AttributeInformationGain + InformationGain;// negative created in for loop, so positive here
		return InformationGain;

	}

	public static Double getAttributeInformationGain(Node n, int numberOfTotalDatapionts) {

		double InformationGain = 0.0;

		ArrayList<String> UniqueCata = n.getUniqueCata();
		int numberOfCata = n.getNumberOfCata();
		int[] UniqueCataScores = new int[numberOfCata];

		for (int i = 0; i < numberOfTotalDatapionts; i++) {

			if (UniqueCata.contains(n.getCata().get(i))) {

				UniqueCataScores[UniqueCata.indexOf(n.getCata().get(i))]++;

			}

		}

		for (int j = 0; j < UniqueCataScores.length; j++) {

			InformationGain = InformationGain - (((double) UniqueCataScores[j] / (double) numberOfTotalDatapionts)
					* ((Math.log(((double) UniqueCataScores[j] / (double) numberOfTotalDatapionts))) / (Math.log(2))));

		}

		return InformationGain;

	}

	public static ArrayList<Double> getEntropy(Node n, ArrayList<Integer> midpoints) {

		ArrayList<String> catagories = n.getUniqueCata();
		ArrayList<Double> midpointsEntropy = new ArrayList<Double>();
		ArrayList<String> catagorisesDone = new ArrayList<String>();

		for (int j = 1; j < midpoints.size(); j++) {

			double correct = 0;
			int[] catagoriesScore = new int[n.getNumberOfCata()];
			double totalDataPoints = 0;
			double entropy = 0;
			String cata = n.getCata().get(midpoints.get(j - 1));

			if (catagorisesDone.contains(cata)) {

				cata = n.getCata().get((midpoints.get(j - 1)) + 1);

			}

			catagorisesDone.add(cata);
			int lowerbound = midpoints.get(j - 1);

			if (j == 1) {

				lowerbound--;

			}

			for (int i = midpoints.get(j); i > lowerbound; i--) {

				for (int p = 0; p < catagories.size(); p++) {

					if (n.getCata().get(i).equals(catagories.get(p))) {

						catagoriesScore[p]++;

					}

				}

				totalDataPoints++;
			}

			entropy = 0;

			for (int k = 0; k < catagoriesScore.length; k++) {

				if (catagoriesScore[k] != 0) {
					entropy = entropy + ((-(((double) catagoriesScore[k]) / totalDataPoints))
							* ((Math.log(((double) catagoriesScore[k]) / totalDataPoints))) / (Math.log(2)));
				}

			}
			
			divisionSplits.add(catagoriesScore);
			midpointsEntropy.add(entropy);
		}

		return midpointsEntropy;

	}

}
