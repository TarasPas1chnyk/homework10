package org.example.jsonReadAndWrite;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JsonReader {
    public static void main(String[] args) throws IOException {
        read();
    }

    public static void read() throws IOException {
        FileReader fileReader = new FileReader("files/file-all.txt");
        Scanner scanner = new Scanner(fileReader);
        boolean isFirst = true;
        User user = null;
        List<User> users = new ArrayList<>();
        int age = 0;
        while (scanner.hasNextLine()) {
            if (isFirst) {
                scanner.nextLine();
                isFirst = false;
                continue;
            } else {
                String all = scanner.nextLine();
                String[] s1 = all.split(" ");
                String name = s1[0];
                age = Integer.parseInt(s1[1]);
                user = new User(name, age);
                users.add(user);
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s1 = gson.toJson(users);


        try (FileWriter fileWriter = new FileWriter("files/user.json");) {
            fileWriter.write(s1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
