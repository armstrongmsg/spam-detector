package spamdetection.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {

	public static void checkFileExist(File file) throws IOException {
		if (!file.exists()) {
			throw new IOException("File does not exist:" + file.getAbsolutePath());
		}
	}

	public static void checkIsDirectory(File file) throws IOException {
		if (!file.isDirectory()) {
			throw new IOException("File is not directory:" + file.getAbsolutePath());
		}
	}
}
