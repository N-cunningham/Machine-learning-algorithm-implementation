package C45;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Node {

	private ArrayList<String> cata;
	private ArrayList<Double> nums;
	private ArrayList<JoinedColumTuple> JoinedColums;
	private ArrayList<JoinedColumTuple> unsortedJoinedColums;



	public Node(ArrayList<JoinedColumTuple> JoinedColums) {

		ArrayList<String> cataLocal = new ArrayList<String>();
		ArrayList<Double> numsLocal = new ArrayList<Double>();
		
		this.JoinedColums = JoinedColums;
		this.unsortedJoinedColums = JoinedColums;
		getLists(JoinedColums);
		
		for(int i = 0; i < JoinedColums.size(); i++){
						
			numsLocal.add(JoinedColums.get(i).getAttribute());
			cataLocal.add(JoinedColums.get(i).getTarget());
			
		}
		
		this.setCata(cataLocal);
		this.setNums(numsLocal);

	}


	public void getLists(ArrayList<JoinedColumTuple> JoinedColums){
		
		Collections.sort(JoinedColums, new Comparator<JoinedColumTuple>() {
	        @Override
	        public int compare(JoinedColumTuple Tuple1, JoinedColumTuple Tuple2) {
	            return Double.compare(Tuple1.getAttribute(), Tuple2.getAttribute());
	        }
	    });
		
	}
	

	public static void main(String[] args) {

	}

	public ArrayList<String> getCata() {
		return cata;
	}

	public void setCata(ArrayList<String> cata) {
		this.cata = cata;
	}

	public ArrayList<Double> getNums() {
		return nums;
	}

	public void setNums(ArrayList<Double> nums) {
		this.nums = nums;
	}

	public int getNumberOfCata() {

		int numberOfCata = this.getUniqueCata().size();
		return numberOfCata;

	}
	

	public ArrayList<JoinedColumTuple> getJoinedColums() {
		return JoinedColums;
	}


	public void setJoinedColums(ArrayList<JoinedColumTuple> joinedColums) {
		JoinedColums = joinedColums;
	}
	

	public ArrayList<JoinedColumTuple> getUnsortedJoinedColums() {
		return unsortedJoinedColums;
	}


	public void setUnsortedJoinedColums(ArrayList<JoinedColumTuple> unsortedJoinedColums) {
		this.unsortedJoinedColums = unsortedJoinedColums;
	}
	
	public void printData(){
		
		
		System.out.println("\n\nSTART###########################\n\n");
		
		System.out.println("NUMS\n");
		for(int i = 0; i < nums.size(); i++){
			
			System.out.print(nums.get(i) + " ");
			
			
		}
		
		System.out.println("\ncaTA\n");
		for(int j = 0; j < cata.size(); j++){
			
			System.out.print(cata.get(j) + " ");
			
			
		}
		
	}

	public ArrayList<String> getUniqueCata() {
		String prev = " ";
		int numberOfCata = 0;
		ArrayList<String> cats = new ArrayList<String>();

		for (int i = 0; i < this.getCata().size(); i++) {

			if (!cats.contains(this.getCata().get(i))) {

				cats.add(this.cata.get(i));

			}

		}

		return cats;

	}

}
