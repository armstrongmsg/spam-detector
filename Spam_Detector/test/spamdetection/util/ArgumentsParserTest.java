package spamdetection.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import spamdetection.detection.ProgramArguments;

public class ArgumentsParserTest {

	private static final String CLASSIFICATION_TECHNIQUE = "technique";
	private static final String TRAINING_SET = "something";
	private static final String TEST_SET = "anything";
	private static final String NOT_EXISTENT_TRAINING_SET = "does_not_exist_1";
	private static final String NOT_EXISTENT_TEST_SET = "does_not_exist_2";
	private static final String A_FILE = "a_file";
	
	private ArgumentsParser parser;
	
	@Before
	public void setUp() throws Exception {
		parser = new ArgumentsParser();
		
		File trainingSet = new File(TRAINING_SET);
		trainingSet.mkdir();
		
		File testSet = new File(TEST_SET);
		testSet.mkdir();
		
		new File(A_FILE).createNewFile();
	}

	@After
	public void tearDown() throws Exception {
		new File(TRAINING_SET).delete();
		new File(TEST_SET).delete();
		new File(A_FILE).delete();
	}

	@Test
	public void testGetArguments() throws IOException {
		String[] args = new String[] {CLASSIFICATION_TECHNIQUE, 
				TRAINING_SET, TEST_SET};
		
		ProgramArguments result = parser.getArguments(args);
		
		assertEquals(CLASSIFICATION_TECHNIQUE, result.getClassificationTechnique());
		assertTrue(result.getTestSet().isDirectory());
		assertEquals(TEST_SET, result.getTestSet().getName());
		assertTrue(result.getTrainingSet().isDirectory());
		assertEquals(TRAINING_SET, result.getTrainingSet().getName());
	}
	
	@Test(expected = IOException.class)
	public void testGetArgumentsNotExistentTrainingSetDirectory() throws IOException {
		String[] args = new String[] {CLASSIFICATION_TECHNIQUE, 
				NOT_EXISTENT_TRAINING_SET, TEST_SET};
		parser.getArguments(args);
	}
	
	@Test(expected = IOException.class)
	public void testGetArgumentsNotExistentTestSetDirectory() throws IOException {
		String[] args = new String[] {CLASSIFICATION_TECHNIQUE, 
				TRAINING_SET, NOT_EXISTENT_TEST_SET};
		parser.getArguments(args);
	}
	
	@Test(expected = IOException.class)
	public void testGetArgumentsEmptyClassificationTechnique() throws IOException {
		String[] args = new String[] {"", TRAINING_SET, TEST_SET};
		parser.getArguments(args);
	}
	
	@Test(expected = IOException.class)
	public void testGetArgumentsEmptyTestSetDirectory() throws IOException {
		String[] args = new String[] {CLASSIFICATION_TECHNIQUE, 
				TRAINING_SET, ""};
		parser.getArguments(args);
	}
	
	@Test(expected = IOException.class)
	public void testGetArgumentsEmptyTrainingSetDirectory() throws IOException {
		String[] args = new String[] {CLASSIFICATION_TECHNIQUE, 
				"", TEST_SET};
		parser.getArguments(args);
	}
	
	@Test(expected = IOException.class)
	public void testGetArgumentsTestSetIsNotDirectory() throws IOException {
		String[] args = new String[] {CLASSIFICATION_TECHNIQUE, 
				TRAINING_SET, A_FILE};
		parser.getArguments(args);
	}
	
	@Test(expected = IOException.class)
	public void testGetArgumentsTrainingSetIsNotDirectory() throws IOException {
		String[] args = new String[] {CLASSIFICATION_TECHNIQUE, 
				A_FILE, TEST_SET};
		parser.getArguments(args);
	}
}
