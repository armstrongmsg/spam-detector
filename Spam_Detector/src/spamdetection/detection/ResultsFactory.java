package spamdetection.detection;

import spamdetection.util.LoadTextFileData;
import weka.classifiers.Evaluation;
import weka.core.Attribute;

public class ResultsFactory {
	/**
	 * Precision related to spam
	 * 
	 * This number is the index of the "spam" value in the IS_SPAM attribute
	 */
	private static final int SPAM_CLASS_INDEX = 1;

	public Results getResults(Evaluation test) {
		Attribute spam = new LoadTextFileData().getSpamAttribute();
		return new Results(test.precision(spam.indexOfValue("spam")), 
						test.recall(spam.indexOfValue("spam")), test.fMeasure(spam.indexOfValue("spam")));
	}
}
