package spamdetection.util;

import spamdetection.detection.ProgramArguments;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class DataLoader {

	private LoadTextFileData textFileDataLoader;
	/**
	 * This filter is used because some Classifiers do not handle
	 * String data. So it converts this type of data to a numeric 
	 * data.
	 */
	private StringToWordVector filter;
	
	public DataLoader() {
		textFileDataLoader = new LoadTextFileData();
		filter = new StringToWordVector();
	}
	
	// FIXME one thing is missing
	// we need to add information on if the text is spam or not.
	public Instances getTrainingData(ProgramArguments arguments) throws Exception {
		Instances instances = textFileDataLoader.loadDataset(arguments.getTrainingSet().getAbsolutePath());
		return filter(instances);
	}

	public Instances getTestData(ProgramArguments arguments) throws Exception {
		Instances instances = textFileDataLoader.loadDataset(arguments.getTestSet().getAbsolutePath());
		return filter(instances);
	}
	
	private Instances filter(Instances instances) throws Exception {
		filter.setInputFormat(instances);
		return Filter.useFilter(instances, filter);
	}
}
