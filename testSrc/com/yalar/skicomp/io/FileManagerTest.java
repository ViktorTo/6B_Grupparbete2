package com.yalar.skicomp.io;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    private FileManager fm;
    private ArrayList<String> testList;
    private ArrayList<String> listDirectory;
    private ArrayList<String>saveFile;
    @BeforeEach
    void setUp() {
        fm = new FileManager();
        testList = new ArrayList<>();
        saveFile = new ArrayList<>();
        listDirectory = new ArrayList<>();
        testList.add("line1");
        testList.add("line2");
        testList.add("line3");
        listDirectory.add("test.txt");
        saveFile.add("Name");
        saveFile.add("Line");
    }

    @Test
    void readFile() {
        assertLinesMatch(testList, fm.readFile("./ioTestDir/test.txt"));
    }

    @Test
    void doesExist() {
        assertTrue(fm.doesExist("./ioTestDir/test.txt"));
        assertFalse(fm.doesExist("./ioTestDir/thisfiledoesnotexist.txt"));
    }

    @Test
    void deleteFile() {
        File delete = new File("./ioTestDir/deleteMe.txt");
        try {
            delete.createNewFile();
        }catch (IOException e) {
            System.err.println("Test file creation failed, deleteFile test will fail");
        }
        fm.deleteFile("./ioTestDir/deleteMe.txt");
        assertFalse(delete.exists());
    }

    @Test
    void listDirectory() {
        assertLinesMatch(listDirectory,fm.listDirectory("./ioTestDir"));
    }

    @Test
    void saveFile() {
        String path = "./ioTestDir/new.txt";
        fm.saveFile(saveFile,path);
        assertTrue(fm.doesExist(path));
        assertLinesMatch(saveFile,fm.readFile(path));
        fm.deleteFile(path);
    }
}