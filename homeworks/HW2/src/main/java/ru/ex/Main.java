package ru.ex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public static void main(String[] args) throws IOException {

        WordProv wordProv = new WordProv();
        String hiddenWord = wordProv.getWord();
        System.out.println(hiddenWord);

        System.out.println("Привет, угадай слово, которое я загадал. Твой вариант:");

        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringBuffer  usersWord = new StringBuffer(br.readLine());


        System.out.println("Ты ввел: " + usersWord);

        while(!usersWord.toString().equals(hiddenWord))
        {
            int cowsCount = 0;
            int bullsCount = 0;
            StringBuffer  bufferWord = new StringBuffer(hiddenWord);
            ArrayList<Integer> indexForRemove = new ArrayList <Integer>();

            for(int index = 0; index < usersWord.length(); index ++)
            {
                if(index < hiddenWord.length() && usersWord.charAt(index) == hiddenWord.charAt(index)){
                    bullsCount ++;
                    indexForRemove.add(index);
                   // usersWord = removeCharAt(usersWord,index);
                    //bufferWord = removeCharAt(hiddenWord,index);
                }
            }
            int h=indexForRemove.size();
            for (int i=indexForRemove.size();i>0;i--)
            {
                usersWord.deleteCharAt(indexForRemove.get(i-1));
                bufferWord.deleteCharAt(indexForRemove.get(i-1));
               // usersWord = removeCharAt(usersWord,);
               // bufferWord = removeCharAt(bufferWord,indexForRemove.remove(i));
            }
            for(int index = 0; index < usersWord.length(); index ++)
            {
                String a = Character.toString(usersWord.charAt(index));
                if(bufferWord.toString().contains(a))
                {
                    cowsCount++;
                }
            }

            System.out.println("Cows: " + cowsCount + ". Bulls: " + bullsCount);
            System.out.println("Users word: " + usersWord + ". buff: " + bufferWord);
            System.out.println("Please type another word");
            usersWord = new StringBuffer(br.readLine());
           // usersWord = br.readLine();
        }
        System.out.println("Congratulations! You win!");


    }

}
