package ru.ex;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class WordProv {

    public String getWord() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get("dictionary.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> words = new ArrayList<>();
        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine());
        }

        int randomIndex = ThreadLocalRandom.current().nextInt(0, words.size());
        scanner.close();

        return words.get(randomIndex);
    }

}
