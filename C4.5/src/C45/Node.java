package C45;

import java.util.ArrayList;

public class Node {

	private ArrayList<String> cata;
	private ArrayList<Integer> nums;

	public Node(ArrayList<String> cata, ArrayList<Integer> nums) {

		this.cata = cata;
		this.nums = nums;

	}

	public static void main(String[] args) {

	}

	public ArrayList<String> getCata() {
		return cata;
	}

	public void setCata(ArrayList<String> cata) {
		this.cata = cata;
	}

	public ArrayList<Integer> getNums() {
		return nums;
	}

	public void setNums(ArrayList<Integer> nums) {
		this.nums = nums;
	}

	public int getNumberOfCata() {	
		
		int numberOfCata = this.getUniqueCata().size();
		return numberOfCata;

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
	
	public ArrayList<Integer> gatInitialPartitions() {

		int numberOfPartitions = this.getNumberOfCata() - 1;

		// System.out.println(numberOfPartitions);
		ArrayList<Integer> partitions = new ArrayList<>();

		int i = this.nums.size();

		while (i >= 0) {

			partitions.add( this.nums.size() - i);

			i = i - ( this.nums.size() / numberOfPartitions);

		}

		System.out.println("Fetching Inital Partitions...");
		for (int j = 0; j < partitions.size(); j++) {

			System.out.println( partitions.get(j));

		}
		System.out.println("Done");
		
		return partitions;

	}

}
