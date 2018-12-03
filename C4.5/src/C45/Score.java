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
		
	//	LocalThresholdsValues[0] = n.getNums().get(Thresholds[0]);
		
		for(int i = 1; i < Thresholds.length-2; i++){
			
			LocalThresholdsValues[i] = n.getNums().get(Thresholds[i]);// + (((n.getNums().get(Thresholds[i] - 1))) / 2)); // this gets midpoint between two
			
		}
		
		//LocalThresholdsValues[Thresholds.length-1] = n.getNums().get(Thresholds[Thresholds.length-1]);
		
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
		return "\nBest split is on attribute " + attributeIndex + ": [Threshold Values=" + Arrays.toString(ThresholdsValues) + ", informationGain=" + informationGain + "]";
	}



	public int getAttributeIndex() {
		return attributeIndex;
	}



	public void setAttributeIndex(int attributeIndex) {
		this.attributeIndex = attributeIndex;
	}


}
