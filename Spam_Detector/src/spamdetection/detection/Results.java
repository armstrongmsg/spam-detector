package spamdetection.detection;

public class Results {

	private double precision;
	private double recall;
	private double fMeasure;
	
	public Results(double precision, double recall, double fMeasure) {
		this.precision = precision;
		this.recall = recall;
		this.fMeasure = fMeasure;
	}
	
	public double getPrecision() {
		return precision;
	}

	public double getRecall() {
		return recall;
	}

	public double getfMeasure() {
		return fMeasure;
	}
}
