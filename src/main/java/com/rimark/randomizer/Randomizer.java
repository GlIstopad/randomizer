package com.rimark.randomizer;

import java.io.*;
import java.util.*;
// TODO: подбор слов с заранее разными буквами по ключу
public class Randomizer{
    public Random random = new Random();
    public int _key;
    public String[] _baseWords;
    public int[] _indexesOfRandomWords;
    public int _numberOfRandomWords;
    public String _fileName;
    public String[] _randomWords;
    public char[] _randomChars;
    public Randomizer(String fileName,int numberOfWords){
        _numberOfRandomWords = numberOfWords;
        _indexesOfRandomWords = new int[numberOfWords]; // Номера слов в списке
        _fileName = fileName + ".txt";
        try(FileReader reader = new FileReader(_fileName)){ // перевод всех слов из файла в
            StringBuilder stringBuilder = new StringBuilder(reader.readAllLines().toString());
            _baseWords = stringBuilder.toString().split(",\\s{2}");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void generateRandomWords(){
        String[] randomWords = new String[_numberOfRandomWords];
        String letters = "";
        for(int i = 0; i<_numberOfRandomWords; i++){
            char currLetter;
            _indexesOfRandomWords[i] = random.nextInt(0,_baseWords.length - 1);
            currLetter = _baseWords[_indexesOfRandomWords[i]].charAt(_key);

            while(letters.contains(new StringBuffer().append(currLetter))){
                _indexesOfRandomWords[i] = random.nextInt(0,_baseWords.length - 1);
                currLetter = _baseWords[_indexesOfRandomWords[i]].charAt(_key);
                System.out.println(currLetter);
            }
            letters = letters + currLetter;
            System.out.println(letters);

        }
        for(int i = 0; i<_numberOfRandomWords; i++){
            randomWords[i] = _baseWords[_indexesOfRandomWords[i]]; 
        }
        _randomWords = randomWords;
        System.out.println("Generated Random Words: " + Arrays.toString(_randomWords));
    }
    public void keyGenerator(){
        _key = random.nextInt(0, minLength(_baseWords)); // минимальное кол-во букв во всех словах это 3 буквы
    }
    public int minLength(String[] words){
        int minLength = 99999999;
        for(int i = 0; i<words.length; i++){
            if(_baseWords[i].length() < minLength && !_baseWords[i].isEmpty()){
                minLength = _baseWords[i].length();
            }
        }
        return minLength;
    }
    public void encryptLetters(){
        _randomChars = new char[_numberOfRandomWords];
        for(int i = 0; i<_numberOfRandomWords; i++){
            _randomChars[i] = _randomWords[i].charAt(_key);
        }
    }
    public char[] getRandomChars(){return _randomChars;}
    public String[] getWords(){return _randomWords;}
    public String[] getBaseWords(){return _baseWords;}
    public int getNumberOfRandomWords(){return _numberOfRandomWords;}
    public int getKey(){return _key;}


}