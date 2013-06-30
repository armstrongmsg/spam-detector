package spamdetection.util;

import java.io.File;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import spamdetection.detection.ProgramArguments;
import weka.core.Instances;

/**
 * Note: this is not a normal test. We're not checking anything.
 * Its only purpose is to watch the behavior of the code which relies on 
 * a weka library we still don't know how it works. We know it gets string data
 * and converts it on a format some classifiers can handle.
 */
public class DataLoaderTest {

	private static final String CLASSIFICATION_TECHNIQUE = "J48";
	private static final String TRAINING_SET_DIRECTORY = "training_set";
	private static final String TEST_SET_DIRECTORY = "test_set";
	private static final String TRAINING_FILE_1 = TRAINING_SET_DIRECTORY + File.separator + "file_1";
	private static final String TRAINING_FILE_2 = TRAINING_SET_DIRECTORY + File.separator + "file_2";
	private static final String TEST_FILE_1 = TEST_SET_DIRECTORY + File.separator + "file_1";
	private static final String TEST_FILE_2 = TEST_SET_DIRECTORY + File.separator + "file_2";
	private DataLoader loader;
	private File trainingSet;
	private File testSet;
	
	@Before
	public void setUp() throws Exception {
		loader = new DataLoader();
		
		trainingSet = new File(TRAINING_SET_DIRECTORY);
		testSet = new File(TEST_SET_DIRECTORY);

		trainingSet.mkdir();
		testSet.mkdir();
		
		File trainingFile1 = new File(TRAINING_FILE_1);
		File trainingFile2 = new File(TRAINING_FILE_2);
		File testFile1 = new File(TEST_FILE_1);
		File testFile2 = new File(TEST_FILE_2);
		
		trainingFile1.createNewFile();
		trainingFile2.createNewFile();
		testFile1.createNewFile();
		testFile2.createNewFile();
	
		PrintStream printStreamTrainingFile1 = new PrintStream(trainingFile1);
		PrintStream printStreamTrainingFile2 = new PrintStream(trainingFile2);
		PrintStream printStreamTestFile1 = new PrintStream(testFile1);
		PrintStream printStreamTestFile2 = new PrintStream(testFile2);
		
		printStreamTrainingFile1.printf("word1 word1 word1 word1\n");
		printStreamTrainingFile2.printf("word1 word1 word1 word1\n");
		printStreamTestFile1.printf("word2 word2 word3 word4\n");
		printStreamTestFile2.printf("word2 word2 word3 word4\n");
		
		printStreamTrainingFile1.close();
		printStreamTrainingFile2.close();
		printStreamTestFile1.close();
		printStreamTestFile2.close();
	}

	@After
	public void tearDown() throws Exception {
		new File(TRAINING_FILE_1).delete();
		new File(TRAINING_FILE_2).delete();
		
		new File(TEST_FILE_1).delete();
		new File(TEST_FILE_2).delete();
		
		new File(TRAINING_SET_DIRECTORY).delete();
		new File(TEST_SET_DIRECTORY).delete();
	}
	
	@Test
	public void testGetTrainingData() throws Exception {
		ProgramArguments arguments = new ProgramArguments(CLASSIFICATION_TECHNIQUE, trainingSet, testSet);
		Instances instances = loader.getTrainingData(arguments);
		System.out.println(instances);
	}
	
	@Test
	public void testGetTestData() throws Exception {
		ProgramArguments arguments = new ProgramArguments(CLASSIFICATION_TECHNIQUE, trainingSet, testSet);
		Instances instances = loader.getTestData(arguments);
		System.out.println(instances);
	}
}
