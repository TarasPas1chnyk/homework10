package org.example.fileReader;

import java.io.*;

public class ReadTheFile {
    public void readFile(File file){
        int c;
        String pN = "";
        try (FileReader fr = new FileReader(file)) {
            while ((c = fr.read()) != -1) {
                pN += (char) c;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] pNs = pN.split("\n");
        StringBuilder newString = new StringBuilder();
        String pattern = "^\\d{3}-\\d{3}-\\d{4}$";
        String pattern1 = "\\(\\d{3}\\) \\d{3}-\\d{4}";
        for (String p : pNs) {
            if (p.matches(pattern) || p.matches(pattern1)) {
                newString.append(p);
                newString.append("\n");
            }
        }
        System.out.println(newString);
    }

    public static void main(String[] args) {
        File file = new File("files/test.txt");
        try (FileWriter fw = new FileWriter(file)) {
            fw.write("987-123-4567\n" +
                    "123 456 7890\n" +
                    "(123) 456-7890");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ReadTheFile readTheFile = new ReadTheFile();
        readTheFile.readFile(file);
    }
}