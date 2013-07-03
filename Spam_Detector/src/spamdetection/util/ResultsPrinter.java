package spamdetection.util;

import java.io.PrintStream;

import spamdetection.detection.Results;

public class ResultsPrinter {

	private PrintStream printStream;
	
	public ResultsPrinter(PrintStream printStream) {
		this.printStream = printStream;
	}

	public void print(Results results) {
		printStream.printf("precision: %f\n", results.getPrecision());
		printStream.printf("recall: %f\n", results.getRecall());
		printStream.printf("f-measure: %f\n", results.getfMeasure());
	}
	
	public void printVerbose(Results results) {
		// TODO to be implemented
	}
}
