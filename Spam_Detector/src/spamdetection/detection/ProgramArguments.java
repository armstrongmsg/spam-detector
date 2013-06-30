package spamdetection.detection;

import java.io.File;

public class ProgramArguments {
	
	private String classificationTechnique;
	private File trainingSet;
	private File testSet;
	
	public ProgramArguments(String classificationTechnique, File trainingSet,
			File testSet) {
		this.classificationTechnique = classificationTechnique;
		this.trainingSet = trainingSet;
		this.testSet = testSet;
	}

	public String getClassificationTechnique() {
		return classificationTechnique;
	}

	public File getTrainingSet() {
		return trainingSet;
	}

	public File getTestSet() {
		return testSet;
	}
}
