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
		String prev = " ";
		int numberOfCata = 0;

		for (int i = 0; i < this.getCata().size(); i++) {

			if (this.getCata().get(i) != prev) {

				numberOfCata++;

			}

		}

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

}