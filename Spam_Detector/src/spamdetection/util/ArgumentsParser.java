package spamdetection.util;

import java.io.File;
import java.io.IOException;

import spamdetection.detection.ProgramArguments;

public class ArgumentsParser {

	private static final int TRAINING_SET_DIRECTORY_INDEX = 1;
	private static final int CLASSIFICATION_TECHNIQUE_INDEX = 0;
	private static final int TEST_SET_DIRECTORY_INDEX = 2;
	private static final int ARGS_EXPECTED_LENGTH = 3;

	public ProgramArguments getArguments(String[] args) throws IOException {
		if (args.length != ARGS_EXPECTED_LENGTH) {
			throw new IOException("Incorrect number of arguments");
		}
		
		String classificationTechnique = getClassificationTechnique(args);
		File trainingSet = getTrainingSet(args);
		File testSet = getTestSet(args);
		
		return new ProgramArguments(classificationTechnique, trainingSet, testSet);
	}

	private File getTestSet(String[] args) throws IOException {
		if (args[TEST_SET_DIRECTORY_INDEX] != null) {
			if (!args[TEST_SET_DIRECTORY_INDEX].isEmpty()) {
				File testSet = new File(args[TEST_SET_DIRECTORY_INDEX]);
				FileUtil.checkFileExist(testSet);
				FileUtil.checkIsDirectory(testSet);
				return testSet;
			}
		}
		throw new IOException("Invalid test set directory.");
	}

	private String getClassificationTechnique(String[] args) throws IOException {
		if (args[CLASSIFICATION_TECHNIQUE_INDEX] != null) {
			if (!args[CLASSIFICATION_TECHNIQUE_INDEX].isEmpty()) {
				return args[CLASSIFICATION_TECHNIQUE_INDEX];
			}
		}
		throw new IOException("Invalid classification technique");
	}

	private File getTrainingSet(String[] args) throws IOException {
		if (args[TRAINING_SET_DIRECTORY_INDEX] != null) {
			if (!args[TRAINING_SET_DIRECTORY_INDEX].isEmpty()) {
				File trainingSet = new File(args[TRAINING_SET_DIRECTORY_INDEX]);
				FileUtil.checkFileExist(trainingSet);
				FileUtil.checkIsDirectory(trainingSet);
				return trainingSet;
			}
		}
		throw new IOException("Invalid training set directory.");
	}
}
