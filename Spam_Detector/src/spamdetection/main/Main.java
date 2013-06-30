package spamdetection.main;

import spamdetection.detection.EvaluationManager;
import spamdetection.detection.ProgramArguments;
import spamdetection.detection.Results;
import spamdetection.detection.ResultsGetter;
import spamdetection.util.ArgumentsParser;
import spamdetection.util.DataLoader;
import spamdetection.util.ResultsPrinter;
import weka.classifiers.Evaluation;
import weka.core.Instances;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArgumentsParser argumentsParser = null;
		ProgramArguments arguments = argumentsParser.getArguments(args);
		DataLoader dataLoader = null;
		Instances trainingData = dataLoader.getTrainingData(arguments);
		Instances realData = dataLoader.getRealData(arguments);
		
		EvaluationManager evaluationManager = null;
		Evaluation test = evaluationManager.getEvaluation(trainingData, realData);
		ResultsGetter resultsGetter = null;
		Results results = resultsGetter.getResults(test);
		ResultsPrinter resultsPrinter = null;
		resultsPrinter.print(results);
	}
}
