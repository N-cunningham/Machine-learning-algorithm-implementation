package C45;

import java.util.ArrayList;
import java.util.Collections;

public class Attributes {
	static ArrayList<Score> scoreList = new ArrayList<Score>();
	ArrayList<Double> scoreInfoGains = new ArrayList<Double>();
	
	static ArrayList<ArrayList> metaList = new ArrayList<ArrayList>();	

	ArrayList<String> target = new ArrayList<String>();
	
	static ArrayList<ArrayList<JoinedColumTuple>> JoinedColumTuplesList = new ArrayList<ArrayList<JoinedColumTuple>>();

	
	public static void main(String[] args) {
		
		ArrayList<String> data = new ArrayList<String>();
		
		
		int attribute = 0;
		
		int columns = 4;
		int targetColumn = 2;
		
		
		attribute(data, metaList, data, columns, targetColumn);
	}
	
	
	private static void attribute(ArrayList<String> data, ArrayList<ArrayList> metaList, ArrayList<String> target, int columns, int targetColumn) {
	
	for(int i=0;i<columns;i++) {
		if(i==targetColumn){
			target = metaList.get(i);
			metaList.remove(i);
		}
	
	}
	
	for(int j=0; j<metaList.size();j++) {
		
		ArrayList<JoinedColumTuple> JoinedColumTuples = new ArrayList<JoinedColumTuple>();
		
		for(int k = 0; k < metaList.get(j).size(); k++) {			
		
			JoinedColumTuple jct = new JoinedColumTuple(target.get(k), metaList.get(j).get(k));
			JoinedColumTuples.add(jct);
			
		}
		
		JoinedColumTuplesList.add(JoinedColumTuples);
		
		Node n = new Node(JoinedColumTuples);
		Score s = Main.runTestOnAttribute(n);
		scoreList.add(s);
	}
	

	
	}
	
	public int getScoreMax() {
		for(int i=0; i<scoreList.size();i++) {
			scoreInfoGains.add(scoreList.get(i).getInformationGain());
		}
		return scoreList.indexOf(Collections.max(scoreInfoGains));		
	}
	
	public void setTarget(ArrayList<String> target) {
		this.target = target;
	}


	public static void setMetaList(ArrayList<ArrayList> metaList) {
		Attributes.metaList = metaList;
	}
}
