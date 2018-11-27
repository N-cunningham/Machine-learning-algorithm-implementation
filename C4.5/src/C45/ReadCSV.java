package C45;

import java.io.*;
import java.util.*;

public class ReadCSV {

	public static void main(String[] args) throws IOException {
		String line;
		String csvFile = "C:\\Users\\15910831\\owls.csv";
		Scanner reader = new Scanner(System.in);

		System.out.println("What is your target column?: ");
		int targetCol = reader.nextInt();

		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		int c = 0;
		int colsLength = 0;

		while ((line = br.readLine()) != null) {

			String cvsSplitBy = ",";
			String[] cols = line.split(cvsSplitBy);
			colsLength = cols.length;
			c++;

		}

		BufferedReader br2 = new BufferedReader(new FileReader(csvFile));

		double[][] data = new double[colsLength - 1][c];
		String[] targetData = new String[c];

		int c2 = 0;
		while ((line = br2.readLine()) != null) {

			String cvsSplitBy = ",";
			String[] cols = line.split(cvsSplitBy);

			for (int i = 0; i < cols.length; i++) {
				if (i == targetCol) {
					targetData[c2] = (cols[i]);
				} else {

					data[i][c2] = Double.parseDouble(cols[i]);

				}
			}

			c2++;

		}

		reader.close();
		br.close();

		/*for (int p = 0; p < data.length; p++) {

			for (int x = 0; x < data[p].length; x++) {

				System.out.println(data[p][x]);

			}

			System.out.println("FFFFFFFFFFS");

		}

		for (int p = 0; p < targetData.length; p++) {

			System.out.println(targetData[p]);

		}*/


		ArrayList<ArrayList<JoinedColumTuple>> metaList = new ArrayList<ArrayList<JoinedColumTuple>>();
		ArrayList<String> targetList = new ArrayList<String>();


	
				
		for(int j=0; j<colsLength - 1;j++) {
			ArrayList<JoinedColumTuple> jctArrayList = new ArrayList<JoinedColumTuple>();
				
			for(int k=0; k<data[j].length; k++) {
				JoinedColumTuple jct = new JoinedColumTuple(targetData[k], data[j][k]);
				jctArrayList.add(jct);

			}
			metaList.add(jctArrayList);
			
		}
	
	
		/*
		for(int i=0; i<metaList.size();i++) {
			for (int j =0; j<metaList.get(i).size();i++) {
			System.out.println(metaList.get(i).get(j).toString());
			}
		}
		*/
		System.out.println(metaList.size());
		ArrayList<Score> scores = new ArrayList<Score>();
		
			for(int i = 0 ; i < metaList.size(); i ++) {
				
				Node n = new Node(metaList.get(i));
				scores.add(Main.runTestOnAttribute(n));
				
			}
			
			for(int i = 0 ; i < scores.size(); i ++) {
				
				
				System.out.println(scores.get(i));
				
			}
			
		
	/*
			for(int i=0; i<n.getCata().size(); i++) {
			System.out.print(n.getCata().get(i));
			System.out.print("\t" + n.getNums().get(i) + "\n");
			System.out.println(metaList.get(1).get(i).toString());
			System.out.print("\n");
		}		
		*/
	}
}