package com.example.data;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Results {
    //只读取一个迭代的时间统计信息
    private static List<String> readFromFile(File file, int interations) throws IOException {
        BufferedReader br = null;
        List<String> lines = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            int i = 0;
            while (i <interations  && (line = br.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public static String get() throws IOException {
        File resultFileDir = new File("D:/tmpdownloads/Results/RenderOnUI");
        if (!resultFileDir.exists() || !resultFileDir.isDirectory()) {
            throw new IOException(resultFileDir.getAbsolutePath() + " should exist and also be a directory");
        }
        String[] files = resultFileDir.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                File foundFile = new File(dir, name);
                return foundFile.exists() && foundFile.isFile() && foundFile.getName().startsWith("part-");
            }
        });
        Arrays.sort(files);
        List<String> results = new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            List<String> lines = readFromFile(new File(resultFileDir.getAbsolutePath(), files[i]), 1);
            results.addAll(lines);
        }
        return new Gson().toJson(results);

    }
}
