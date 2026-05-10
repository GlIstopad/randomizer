package com.rimark.randomizer;
import org.jspecify.annotations.NonNull;

import java.io.FileReader;
import java.util.*;

public class Randomizer{
    public Random random = new Random();
    public int _key;
    public String[] baseWords;
    public int[] indexesOfRandomWords;
    public int _numberOfRandomWords;
    public String _fileName;
    public String[] _randomWords;
    public char[] _randomChars;
    public Randomizer(String fileName,int numberOfWords){
        _numberOfRandomWords = numberOfWords;
        indexesOfRandomWords = new int[numberOfWords]; // Номера слов в списке
        _fileName = fileName + ".txt";
        randomWordsGenerator();
    }
    public String[] randomWords(int[] indexes, int numberOfWords){
        String[] randomWords = new String[numberOfWords];
        for(int i = 0; i<numberOfWords; i++){
            randomWords[i] = baseWords[indexes[i]];
        }
        return randomWords;
    }
    public void randomWordsGenerator(){
        try(FileReader reader = new FileReader(_fileName)){
            StringBuilder stringBuilder = new StringBuilder(reader.readAllLines().toString());
            baseWords = stringBuilder.toString().split(",\\s{2}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i<_numberOfRandomWords; i++){
            indexesOfRandomWords[i] = random.nextInt(0,baseWords.length - 1);
        }
        _randomWords = randomWords(indexesOfRandomWords, _numberOfRandomWords);
    }
    public void keyGenerator(){
        int maxR = maxLength(_randomWords);
        _key = random.nextInt(0, maxR);
    }
    public int maxLength(String[] words){
        int maxLength = 99999999;
        for(int i = 0; i<words.length; i++){
            if(words[i].length() < maxLength && !words[i].isEmpty()){
                maxLength = words[i].length();
            }
        }
        return maxLength;
    }
    public void encryptLetters(){
        _randomChars = new char[_numberOfRandomWords];
        for(int i = 0; i<_numberOfRandomWords; i++){
            _randomChars[i] = _randomWords[i].charAt(_key);
        }
    }
    public static boolean validator(String[] words, int key){
        boolean isValid = true;
        int[] listOfNumbers = new int[words.length]; // список букв по ключу
        for(int i = 0; i<words.length; i++){
            listOfNumbers[i] = (int) words[i].charAt(key);
        }
        int temp;
        boolean swapped;
        for(int i = 0;i<listOfNumbers.length - 1;i++){
            swapped = false;
            for(int j = 0; j<listOfNumbers.length - 1 - i; j++){
                if(listOfNumbers[j] > listOfNumbers[j+1]){
                    temp = listOfNumbers[j];
                    listOfNumbers[j] = listOfNumbers[j+1];
                    listOfNumbers[j+1] = temp;
                    swapped = true;
                }
            }
            if(!swapped){
                break;
            }
        }
        for(int i = 0; i<listOfNumbers.length - 1; i++){
            for(int j = 0; j<listOfNumbers.length - 1 - i; j++){
                if(listOfNumbers[j] == listOfNumbers[j+1]){
                    isValid = false;
                }
            }
        }
        return isValid;
    }
    public char[] getRandomChars(){return _randomChars;}
    public String[] getWords(){
        return _randomWords;
    }
    public String[] getBaseWords(){
        return baseWords;
    }
    public int getNumberOfRandomWords(){return _numberOfRandomWords;}
    public int getKey(){
        return _key;
    }


}