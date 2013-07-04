package spamdetection.util;

import spamdetection.detection.ProgramArguments;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class DataLoader {

	private static final int IS_SPAM_INDEX = 0;
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
	
	public Instances getTrainingData(ProgramArguments arguments) throws Exception {
		Instances instances = textFileDataLoader.loadDataset(arguments.getTrainingSet().getAbsolutePath());
		// FIXME create a constant for the value
		// FIXME maybe this should be put in LoadTextFileData
		instances.setClassIndex(IS_SPAM_INDEX);
		return filter(instances);
	}

	public Instances getTestData(ProgramArguments arguments) throws Exception {
		Instances instances = textFileDataLoader.loadDataset(arguments.getTestSet().getAbsolutePath());
		instances.setClassIndex(IS_SPAM_INDEX);
		return filter(instances);
	}
	
	private Instances filter(Instances instances) throws Exception {
		filter.setInputFormat(instances);
		return Filter.useFilter(instances, filter);
	}
}
