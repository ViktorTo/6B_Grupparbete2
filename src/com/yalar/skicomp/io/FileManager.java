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
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public ArrayList<String> listDirectory(String dir){
		ArrayList<String> files = new ArrayList<String>();
		
		List<File> myList;
		
        try {
            myList = Files.list(Paths.get(dir))
                        .map(Path::toFile)
                        .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error while reading directory!");
            return null;
        }
        
        for (Iterator<File> iterator = myList.iterator(); iterator.hasNext();) {
			File file = (File) iterator.next();
			files.add(file.getName());
		}
		
		return files;
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
