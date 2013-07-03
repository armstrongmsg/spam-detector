package spamdetection.detection;

import java.io.IOException;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;

public class ClassifierFactory {

	public Classifier getClassifier(ProgramArguments arguments) throws IOException {
		String classifierType = arguments.getClassificationTechnique();
		Classifier classifier = null;
		
		if (classifierType.equals("J48")) {
			classifier = new J48();
		} else {
			throw new IOException("Invalid classifier name");
		}
		return classifier;
	}
}
