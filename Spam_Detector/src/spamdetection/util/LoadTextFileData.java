package spamdetection.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class LoadTextFileData {

	private static final int INDEX_IS_SPAM = 2;
	private static final int INDEX_CONTENTS = 1;
	private static final int INDEX_FILENAME = 0;
	private static final String IS_NOT_SPAM = "ham";
	private static final String IS_SPAM = "spam";

	private static final String IS_NOT_SPAM_DIRECTORY = "ham";
	private static final String IS_SPAM_DIRECTORY = "spam";
	
	/**
	 * It loads all the data from files within the given directory and 
	 * returns a created set of instances from it.
	 * 
	 * Modified from the code found on:
	 * http://weka.wikispaces.com/ARFF+files+from+Text+Collections
	 * 
	 * @param directoryPath
	 * @return
	 * @throws Exception
	 */
	public Instances loadDataset(String directoryPath) throws Exception {
		FastVector atts = getInstancesAttributes();
		Instances instances = new Instances("text_files_in_" + directoryPath, atts, 0);
		File spamDirectory = new File(directoryPath + File.separator + IS_SPAM_DIRECTORY);
		File hamDirectory = new File(directoryPath + File.separator + IS_NOT_SPAM_DIRECTORY);
		getSpamData(atts, instances, spamDirectory);
		getHamData(atts, instances, hamDirectory);
		return instances;
	}

	private void getHamData(FastVector atts, Instances data, File hamDirectory)
			throws IOException {
		String[] files = hamDirectory.list();
		for (String filename : files) {
			double filenameNumber = (double)data.attribute(0).addStringValue(filename);
			StringBuffer txtStr = getFileText(hamDirectory.getAbsolutePath(), filename);
			double contents = (double)data.attribute(1).addStringValue(txtStr.toString());
			Instance instance = createInstance(atts, filenameNumber, contents, IS_NOT_SPAM);
			data.add(instance);
		}
	}

	private void getSpamData(FastVector atts, Instances data, File spamDirectory)
			throws IOException {
		String[] files = spamDirectory.list();
		for (String filename : files) {
			double filenameNumber = (double)data.attribute(0).addStringValue(filename);
			StringBuffer fileText = getFileText(spamDirectory.getAbsolutePath(), filename);
			double contents = (double)data.attribute(1).addStringValue(fileText.toString());
			Instance instance = createInstance(atts, filenameNumber, contents, IS_SPAM);
			data.add(instance);
		}
	}

	private StringBuffer getFileText(String directoryPath, String filename) throws IOException {
		File dataFile = new File(directoryPath + File.separator + filename);  
		StringBuffer fileContent = getFileContent(dataFile);
		return fileContent;
	}

	private StringBuffer getFileContent(File dataFile)
			throws FileNotFoundException, IOException {
		InputStreamReader is = new InputStreamReader(new FileInputStream(dataFile));
		StringBuffer fileContent = new StringBuffer();
		int c;
		while ((c = is.read()) != -1) {
			fileContent.append((char)c);
		}
		is.close();
		return fileContent;
	}
	
	private Instance createInstance(FastVector atts, double filename,
			double contents, String spam) {
		Instance instance = new Instance(3);
		instance.setValue((Attribute)atts.elementAt(INDEX_FILENAME), filename);
		instance.setValue((Attribute)atts.elementAt(INDEX_CONTENTS), contents);
		instance.setValue((Attribute)atts.elementAt(INDEX_IS_SPAM), spam);
		return instance;
	}

	private FastVector getInstancesAttributes() {
		FastVector atts = new FastVector(3);
		Attribute spam = getSpamAttribute();
		atts.addElement(new Attribute("filename", (FastVector) null));
		atts.addElement(new Attribute("contents", (FastVector) null));
		atts.addElement(spam);
		return atts;
	}
	
	private Attribute getSpamAttribute() {
		FastVector spamValues = new FastVector(2);
		spamValues.addElement(IS_SPAM);
		spamValues.addElement(IS_NOT_SPAM);
		Attribute spam = new Attribute(IS_SPAM, spamValues);
		return spam;
	}
}

