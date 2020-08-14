package com.theonetech.android.domain.utils;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;

public class FileUtils {

    /**
     * this function is used for create directory in app storage.
     *
     * @param context - to prevent unconditionally errors use application context.
     * @param path - provide path eg. "main/sub/folder"
     */
    public static void createDirectory(Context context, String path) {
        String tempPath = "";
        File dirPath;
        for (String dir : path.split("/")) {
            dirPath = new File(context.getFilesDir(), tempPath + dir);
            if (!dirPath.exists()) {
                dirPath.mkdir();
                tempPath = tempPath + dir + "/";
            } else {
                tempPath = tempPath + dir + "/";
            }
        }
    }


    /**
     * List the files in the parameter directory.
     * @param directoryName
     * @param files
     */
    public static void listFiles(String directoryName, ArrayList<File> files) {
        File directory = new File(directoryName);
        listFiles(directory, files);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listFiles(file.getAbsolutePath(), files);
            }
        }
    }

    /**
     * List the files in the parameter directory.
     * @param files
     */
    public static void listFiles(File directory, ArrayList<File> files) {

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                listFiles(file.getAbsolutePath(), files);
            }
        }
    }


    /**
     * this function is used to delete directory with contents in app storage.
     *
     * @param context - application context
     * @param directory - directory
     */
    public static void deleteDirectory(Context context, String directory) {
        File dir = new File(context.getFilesDir() + directory);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                new File(dir, children[i]).delete();
            }
        }
    }
}
