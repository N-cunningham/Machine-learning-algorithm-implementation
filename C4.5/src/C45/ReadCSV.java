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

		for (int p = 0; p < data.length; p++) {

			for (int x = 0; x < data[p].length; x++) {

				System.out.println(data[p][x]);

			}

			System.out.println("FFFFFFFFFFS");

		}

		for (int p = 0; p < targetData.length; p++) {

			System.out.println(targetData[p]);

		}


		ArrayList<ArrayList<JoinedColumTuple>> metaList = new ArrayList<ArrayList<JoinedColumTuple>>();
		ArrayList<String> targetList = new ArrayList<String>();


		for(int i=0; i<targetData.length; i++) {
			targetList.add(targetData[i]);
				
			for(int j=0; j<data.length;j++) {
				ArrayList<JoinedColumTuple> jctArrayList = new ArrayList<JoinedColumTuple>();
				
				for(int k=0; k<data[j].length; k++) {
					JoinedColumTuple jct = new JoinedColumTuple(targetData[i], data[j][k]);
					jctArrayList.add(jct);

				}
				metaList.add(jctArrayList);
			}
		}
		
		
		
			
			Node n = new Node(metaList.get(0));
			Main.runTestOnAttribute(n);
		
		
		
	}
}