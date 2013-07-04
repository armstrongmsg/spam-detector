package spamdetection.detection;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public class EvaluationManager {

	public Evaluation getEvaluation(Classifier classifier,
			Instances trainingData, Instances realData) throws Exception {
		Evaluation test = new Evaluation(trainingData);
		classifier.buildClassifier(trainingData);
		test.evaluateModel(classifier, realData);
		return test;
	}
}
