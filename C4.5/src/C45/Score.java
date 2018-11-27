package C45;

import java.util.Arrays;

public class Score {
	
	
	private int[] Thresholds;
	private double informationGain;
	

	Score(int[] Thresholds, double informationGain){
		
		this.Thresholds = Thresholds;
		this.informationGain = informationGain;
		
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

	@Override
	public String toString() {
	
		return "Score [Thresholds=" + Arrays.toString(Thresholds) + ", informationGain=" + informationGain + "]";
		
	}



	public void setInformationGain(double informationGain) {
		this.informationGain = informationGain;
	}

	


}
