package com.yalar.skicomp.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

	/**
	 * Reads a file into an ArrayList where each line in the file becomes an entry
	 * in the ArrayList.
	 * 
	 * @param path
	 * @return
	 */
	public List<String> readFile(String path) {
		List<String> file = new ArrayList<String>();

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

	/**
	 * Checks if a file exists.
	 * 
	 * @param path
	 * @return
	 */
	public boolean doesExist(String path) {
		File f = new File(path);
		return f.exists();
	}

	/**
	 * Deletes a file.
	 * 
	 * @param path
	 * @return
	 */
	public boolean deleteFile(String path) {
		File file = new File(path);
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * Lists all files in a directory.
	 * 
	 * @param dir
	 * @return
	 */
	public List<String> listDirectory(String path) {
		List<String> files = new ArrayList<String>();

		List<File> myList;

		try {
			myList = Files.list(Paths.get(path)).map(Path::toFile).collect(Collectors.toList());
		} catch (IOException e) {
			System.out.println("Error while reading directory!");
			return null;
		}

		for (var file : myList) {
			// to not include the mysterious logdir.txt file
			if (!file.getName().equals("logdir.txt")) {
				// removing file extension for improved user experience
				files.add(file.getName().replace(".log", ""));
			}
		}

		return files;
	}

	/**
	 * Saves an ArrayList to a file.
	 * 
	 * @param file
	 * @param path
	 * @return
	 */
	public boolean saveFile(List<String> file, String path) {
		try {
			// Creates a FileWriter
			FileWriter fp = new FileWriter(path);

			// Creates a BufferedWriter
			BufferedWriter output = new BufferedWriter(fp);

			// Writes the string to the file

			for (var line : file) {
				output.write(line);
				output.newLine();
			}

			// Closes the writer
			output.close();
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			System.err.println(e.getMessage());
			return false;
		}
	}
}
