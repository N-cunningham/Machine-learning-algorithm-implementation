package C45;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class RunClassification {
	
	public static void main(String[] args) throws IOException{
	
		ArrayList<Double> accuracyScores = new ArrayList<Double>();
		
		
		Scanner reader = new Scanner(System.in);
		
		System.out.println("What is your target column, starting at 0?: ");
		int targetCol = reader.nextInt();
		
		System.out.println("What percentage would you like to use for testing?: ");	
		double testPercentage = reader.nextDouble();
		
		System.out.println("How many times do you want to test?: ");	
		int numberOfTest = reader.nextInt();
		
			for(int z = 0; z < numberOfTest; z++){
			
			Main m  = new Main(); 
			Node[] testingData =  m.getTrunck("F:/Downloads/owls (1).csv", targetCol, testPercentage).clone();
			int numberOfTestingRecords = testingData[0].getCata().size();
			ArrayList<String> UniqueCata = testingData[0].getUniqueCata();
			
			System.out.println(" ");
			
			for(int i = 0; i < m.treeScores.size();i++){
				
				System.out.println(i + " " + m.treeScores.get(i).toString());
				
			}
			
			int correct = 0;
			
			for(int p = 0; p < UniqueCata.size(); p++){
						
				int winnerIndex = m.treeScores.get(0).getAttributeIndex();
				double[] thresholds = m.treeScores.get(0).getThresholdsValues();
				int[] thresholdscores = new int[thresholds.length - 1];		
				
				for(int i = 0; i < testingData[winnerIndex].getCata().size(); i++){
					
					for(int j = 0; j < thresholds.length - 2; j++){
						
						String catagory = testingData[winnerIndex].getCata().get(i);
						Double num = testingData[winnerIndex].getNums().get(i);
						
						if(thresholds[j] < num && thresholds[j + 1] < num && catagory.equals(UniqueCata.get(p))){
							
							thresholdscores[j]++;					
							
						}
						
						
					}
					
					
					
				}
				
				int max = 0;
				int maxIndex = 0;
				
				for (int y = 0; y < thresholdscores.length; y++){
					
					if(thresholdscores[y] > max){
						
						max = thresholdscores[y];
						maxIndex = y; 
						
					}
					
				}
				
				correct = correct + thresholdscores[maxIndex];
			
			}
					
			
			System.out.println(numberOfTestingRecords);
			
			double percentCorrect = ((((double)correct)/((double)numberOfTestingRecords))*100);
			
			System.out.println("Algorithm is " + percentCorrect + "% correct\n\n");
			accuracyScores.add(percentCorrect);
			
			}
			
		double total = 0;
		System.out.println("\nTotals\n---------------------");
		for(int k = 0; k < accuracyScores.size(); k++){
			
			System.out.println(accuracyScores.get(k).intValue());
			total = total + accuracyScores.get(k);
			
		}
		
		System.out.println("\nAverage\n---------------------");
		System.out.println(total/numberOfTest);
		
	}

}
