package C45;

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

	public void setInformationGain(double informationGain) {
		this.informationGain = informationGain;
	}

	


}
