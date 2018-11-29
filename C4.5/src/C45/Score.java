package C45;

import java.util.Arrays;

public class Score {
	
	
	private int[] Thresholds;
	private double[] ThresholdsValues;
	private double informationGain;
	private int attributeIndex;
	
	Score(int[] Thresholds, double informationGain, int attributeIndex, Node n){
		
		
		this.Thresholds = Thresholds;
		this.informationGain = informationGain;
		this.attributeIndex = attributeIndex;
		double[] LocalThresholdsValues = new double[Thresholds.length];
		
		for(int i = 0; i < Thresholds.length; i++){
			
			LocalThresholdsValues[i] = n.getNums().get(Thresholds[i]);
			
		}
		
		setThresholdsValues(LocalThresholdsValues);
		
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

	public double[] getThresholdsValues() {
		return ThresholdsValues;
	}



	public void setThresholdsValues(double[] thresholdsValues) {
		ThresholdsValues = thresholdsValues;
	}


	@Override
	public String toString() {
		return "\nSplit on attribute " + attributeIndex + ": Score [Threshold Values=" + Arrays.toString(ThresholdsValues) + ", informationGain=" + informationGain + "]";
	}


}
