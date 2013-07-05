package spamdetection.detection;

import java.io.IOException;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.J48graft;
import weka.classifiers.trees.RandomTree;

public class ClassifierFactory {

	public Classifier getClassifier(ProgramArguments arguments) throws IOException {
		String classifierType = arguments.getClassificationTechnique();
		Classifier classifier = null;
		
		if (classifierType.equals("J48")) {
			classifier = new J48();
		} else if (classifierType.equals("J48graft")) {
			classifier = new J48graft();
		} else if (classifierType.equals("RandomTree")) {
			classifier = new RandomTree();
		} else {
			throw new IOException("Invalid classifier name");						
		}
		return classifier;
	}
}
