package spamdetection.detection;

import weka.classifiers.Evaluation;

public class ResultsFactory {
	
	private static final int SPAM_CLASS_INDEX = 2;

	public Results getResults(Evaluation test) {
		return new Results(test.precision(SPAM_CLASS_INDEX), 
						test.recall(SPAM_CLASS_INDEX), test.fMeasure(SPAM_CLASS_INDEX));
	}
}
