package com.rimark.randomizer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base {
    public String _name;
    public String[] _base;
    public Base(String name,String base){
        _base = new String[999999];
        Pattern pattern = Pattern.compile("[^\\d*.]");
        Matcher matcher = pattern.matcher(base);
        String actualBase = "";
        _name = name;
        while (matcher.find()) {
            actualBase = actualBase + matcher.group();
        }
        _base = actualBase.split(" ,");
        baseToText();
    }
    public void baseToText(){
        try (FileWriter writer = new FileWriter(nameTxt(_name), false)) {
            for(String base : _base){
                writer.write(base);
            }
            writer.flush();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void addWord(String word){
        boolean isFound = false;
        int finalIndex = 0;
        int currentIndex = 0;
        while(!isFound){
            if(_base[currentIndex] == null){
                finalIndex = currentIndex;
                isFound = true;
            }
            else{
                currentIndex++;
            }
        }
        _base[finalIndex] = word;
        baseToText();
    }
    public String nameTxt(String name){
        name = name + ".txt";
        return name;
    }
}
