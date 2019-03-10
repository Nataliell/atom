package ru.ex;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static int getCows(StringBuffer usWord, StringBuffer bufWord) {
        int cowsCount = 0;
        for (int index = 0; index < usWord.length(); index++) {
            String a = Character.toString(usWord.charAt(index));
            if (bufWord.toString().contains(a)) {
                cowsCount++;
            }
        }
        return cowsCount;
    }

    public static int getBulls(StringBuffer usWord, StringBuffer hiddenWord) {
        int bullsCount = 0;
        for (int index = 0; index < usWord.length(); index++) {
            if (index < hiddenWord.length() && usWord.charAt(index) == hiddenWord.charAt(index)) {
                bullsCount++;
                indexForRemove.add(index);
            }
        }
        return bullsCount;

    }

    public static ArrayList<Integer> indexForRemove = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {

        logger.debug("Logger started");

        WordProv wordProv = new WordProv();
        String hiddenWord = wordProv.getWord();
        //System.out.println(hiddenWord);

        logger.debug("Hidden word: " + hiddenWord);

        System.out.println("Hi, guess the word that I made. There are " + hiddenWord.length() + " letters in a word. Type the word:");

        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringBuffer usersWord = new StringBuffer(br.readLine());
        StringBuffer bufferWord;
        logger.debug("usersWord: " + usersWord);


        int bullsCount;
        int cowsCount;
        int attemptsCount = 1;

        while (!usersWord.toString().equals(hiddenWord) && attemptsCount != 10) {

            bufferWord = new StringBuffer(hiddenWord);

            bullsCount = getBulls(usersWord, bufferWord);

            for (int i = indexForRemove.size(); i > 0; i--) {
                usersWord.deleteCharAt(indexForRemove.get(i - 1));
                bufferWord.deleteCharAt(indexForRemove.get(i - 1));
            }

            cowsCount = getCows(usersWord, bufferWord);

            logger.debug("Attempts: " + attemptsCount + ". Cows: " + cowsCount + ". Bulls: " + bullsCount);

            System.out.println("Cows: " + cowsCount + ". Bulls: " + bullsCount);
            System.out.println("Please type another word");
            usersWord = new StringBuffer(br.readLine());
            attemptsCount++;
        }

        if (attemptsCount < 10)
            System.out.println("Congratulations! You win!");
        else
            System.out.println("Sorry, you lose.");


        System.out.println("Wanna play again? Y/N");
        String userChar = br.readLine();

        if (userChar.length() == 1 && userChar.contains("Y") || userChar.contains("y"))
            main(args);

    }

}
