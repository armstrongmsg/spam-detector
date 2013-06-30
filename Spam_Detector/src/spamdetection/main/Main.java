package spamdetection.main;

import spamdetection.detection.ClassifierGetter;
import spamdetection.detection.EvaluationManager;
import spamdetection.detection.ProgramArguments;
import spamdetection.detection.Results;
import spamdetection.detection.ResultsGetter;
import spamdetection.util.ArgumentsParser;
import spamdetection.util.DataLoader;
import spamdetection.util.ResultsPrinter;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public class Main {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// gets the command line arguments
		ArgumentsParser argumentsParser = new ArgumentsParser();
		ProgramArguments arguments = argumentsParser.getArguments(args);
		// loads the data
		DataLoader dataLoader = new DataLoader();
		Instances trainingData = dataLoader.getTrainingData(arguments);
		Instances realData = dataLoader.getRealData(arguments);
		// gets the correct classifier
		ClassifierGetter classifierGetter = new ClassifierGetter();
		Classifier classifier = classifierGetter.getClassifier(arguments);
		// tests
		EvaluationManager evaluationManager = new EvaluationManager();
		Evaluation test = evaluationManager.getEvaluation(classifier, trainingData, realData);
		// print the results
		ResultsGetter resultsGetter = new ResultsGetter();
		Results results = resultsGetter.getResults(test);
		ResultsPrinter resultsPrinter = new ResultsPrinter();
		resultsPrinter.print(results);
	}
}
