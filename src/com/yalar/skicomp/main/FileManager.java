package com.yalar.skicomp.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
