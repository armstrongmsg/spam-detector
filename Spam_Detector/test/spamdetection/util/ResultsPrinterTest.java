package spamdetection.util;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import spamdetection.detection.Results;

public class ResultsPrinterTest {

	private static final String testFileName = "test.txt";
	private static final double PRECISION_1 = 100.2343;
	private static final double RECALL_1 = 123.34;
	private static final double FMEASURE_1 = 111111.0;
	private ResultsPrinter printer;
	
	@Before
	public void setUp() throws Exception {
		File testFile = new File(testFileName);
		testFile.createNewFile();
		
		printer = new ResultsPrinter(new PrintStream(testFileName));
	}

	@After
	public void tearDown() throws Exception {
		new File(testFileName).delete();
	}
	
	@Test
	public void testPrint() throws IOException {
		Results testResults = new Results(PRECISION_1, RECALL_1, FMEASURE_1);
		
		printer.print(testResults);
		
		RandomAccessFile f = new RandomAccessFile(new File(testFileName), "r");
		
		String line1 = f.readLine();
		assertEquals("precision", line1.split(":")[0]);
		assertEquals(PRECISION_1, Double.parseDouble(line1.split(":")[1].trim()), 0.005);
		
		String line2 = f.readLine();
		assertEquals("recall", line2.split(":")[0]);
		assertEquals(RECALL_1, Double.parseDouble(line2.split(":")[1].trim()), 0.005);
		
		String line3 = f.readLine();
		assertEquals("f-measure", line3.split(":")[0]);
		assertEquals(FMEASURE_1, Double.parseDouble(line3.split(":")[1].trim()), 0.005);

		f.close();
	}
}
