package com.yalar.skicomp.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class FileManager {
	ArrayList<String> readFile(String path) {
		ArrayList<String> file = new ArrayList<String>();

		BufferedReader myReader = null;
		try {
			String strCurrentLine;

			myReader = new BufferedReader(new FileReader(path));

			while ((strCurrentLine = myReader.readLine()) != null) {

				file.add(strCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				if (myReader != null)
					myReader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return file;
	}

	public boolean doesExist(String path) {
		File f = new File(path);
		if (f.exists()) {
			return true;
		}
		return false;
	}

	public boolean deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	public boolean saveFile(ArrayList<String> file, String path) {
		try {
			// Creates a FileWriter
			FileWriter fp = new FileWriter(path);


			// Creates a BufferedWriter
			BufferedWriter output = new BufferedWriter(fp);

			// Writes the string to the file

			for (Iterator<String> iterator = file.iterator(); iterator.hasNext();) {
				String line = (String) iterator.next();
				output.write(line);
			}

			// Closes the writer
			output.close();
		}
		catch (Exception e) {
			e.getStackTrace();
			return true;
		}
		return false;
	}
}
