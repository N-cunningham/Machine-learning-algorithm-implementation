package C45;

import java.util.Arrays;

public class Score {
	
	
	private int[] Thresholds;
	private double informationGain;
	private int attributeIndex;
	
	Score(int[] Thresholds, double informationGain, int attributeIndex, Node n){
		
		this.Thresholds = Thresholds;
		this.informationGain = informationGain;
		this.attributeIndex = attributeIndex;
		
	}
	
	
	
	public int[] getThresholds() {
		return Thresholds;
	}

	public void setThresholds(int[] thresholds) {
		Thresholds = thresholds;
	}

	public double getInformationGain() {
		return informationGain;
	}

	public void setInformationGain(double informationGain) {
		this.informationGain = informationGain;
	}



	@Override
	public String toString() {
		return "\nSplit on attribute " + attributeIndex + ": Score [Thresholds=" + Arrays.toString(Thresholds) + ", informationGain=" + informationGain + "]";
	}


}
