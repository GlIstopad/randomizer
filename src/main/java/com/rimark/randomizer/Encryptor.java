package com.rimark.randomizer;
// TODO: соотнести слова, ключ к паролю и зашифровать
public class Encryptor {
    public Randomizer _randomizer;
    public String[] _wordBase;
    public Base _base;
    public int _key;
    public char[] _encryptedLetters;
    public StringBuffer _encryptedPassword;
    public char[] _randomLetters;
    public Encryptor(Randomizer randomizer, Base base) {
        _randomizer = randomizer;
        _key = randomizer.getKey();
        _wordBase = randomizer.getWords();
        _base = base;
        _encryptedPassword = new StringBuffer(_wordBase.length);
    }
    public int[] lettersToIndexes(String password){
        int[] indexesOfPasswords = new int[password.length()];
        for(int i = 0; i < password.length(); i++){
            if(password.charAt(i) >= 97 && password.charAt(i) <= 122){
                indexesOfPasswords[i] = (int) password.charAt(i) - 96;
            }
            else if(password.charAt(i) >= 65 && password.charAt(i) <= 90){
                indexesOfPasswords[i] = (int) password.charAt(i) - 64;
            }
        }
        return indexesOfPasswords; // возвращает номер каждой буквы пароля по алфавиту
    }
    public String encrypt(String password){
        int[] bukvy = lettersToIndexes(password); // номер каждой буквы пароля по алфавиту
        _encryptedLetters = _randomizer.getRandomChars(); // буквы заменяющий каждую букву по алафавиту
        for(int i = 0; i < password.length()-1; i++){
            _encryptedPassword.append(_encryptedLetters[bukvy[i]]);
        }
        return _encryptedPassword.toString();
    }

    public static String decrypt(String password){ return "";}
}
