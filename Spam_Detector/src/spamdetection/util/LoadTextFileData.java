package spamdetection.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class LoadTextFileData {

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
		FastVector atts = new FastVector(2);
		atts.addElement(new Attribute("filename", (FastVector) null));
		atts.addElement(new Attribute("contents", (FastVector) null));
		Instances data = new Instances("text_files_in_" + directoryPath, atts, 0);

		File dir = new File(directoryPath);
		String[] files = dir.list();
		for (int i = 0; i < files.length; i++) {
			double[] newInst = new double[2];
			newInst[0] = (double)data.attribute(0).addStringValue(files[i]);
			File dataFile = new File(directoryPath + File.separator + files[i]);  
			InputStreamReader is = new InputStreamReader(new FileInputStream(dataFile));
			StringBuffer txtStr = new StringBuffer();
			int c;
			while ((c = is.read()) != -1) {
				txtStr.append((char)c);
			}
			newInst[1] = (double)data.attribute(1).addStringValue(txtStr.toString());
			data.add(new Instance(1.0, newInst));
			is.close();
		}
		return data;
	}
}

