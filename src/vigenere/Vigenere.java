package vigenere;

public class Vigenere {
    private final StringBuilder alphabet = new StringBuilder();

    private int alphabetSize;

    private String message;
    private final StringBuilder encryptedMessage = new StringBuilder();
    private final StringBuilder decryptedMessage = new StringBuilder();

    private String key;
    private final StringBuilder fullKey = new StringBuilder();

    private char[][] table;

    private void setAlphabetSize() {
        alphabetSize = alphabet.length();
    }

    private int getAlphabetSize() {
        return alphabetSize;
    }

    void setMessage(String message) {
        this.message = message;
    }

    void setKey(String key) {
        this.key = key;
    }

    private void deleteKey() {
        key = "";
    }

    public void addAlphabet(String alphabetToAdd) {
        alphabet.append(alphabetToAdd);
        setAlphabetSize();
    }

    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage.append(encryptedMessage);
    }

    private void createTable() {
        table = new char[getAlphabetSize()][getAlphabetSize()];
        for (int i = 0; i < getAlphabetSize(); i++) {
            for (int j = 0; j < getAlphabetSize(); j++) {
                table[i][j] = alphabet.charAt((j + i) % getAlphabetSize());
            }
        }
    }

    public String encrypt() {
        createTable();

        setFullKey(message);

        encryptMessage();

        deleteKey();

        return encryptedMessage.toString();
    }

    public String decrypt() {
        createTable();

        setFullKey(encryptedMessage.toString());

        decryptMessage();

        deleteKey();

        return decryptedMessage.toString();
    }

    private void setFullKey(String mess) {
        if (mess.length() >= key.length()) {
            fullKey.append(key.repeat((mess.length() / key.length())));

            for (int j = 0; j < (mess.length() % key.length()); j++) {
                fullKey.append(key.charAt(j));
            }
        } else {
            for (int i = 0; i < mess.length(); i++) {
                fullKey.append(key.charAt(i));
            }
        }
    }

    private char encrypting(char originalLetter, char keyLetter) {
        int stringIndex = 0;
        int columnIndex = 0;
        int j = 0;
        for (int i = 0; i < getAlphabetSize(); i++) {
            if (table[i][j] == originalLetter) {
                stringIndex = i;
            }
        }

        int i = 0;
        for (j = 0; j < getAlphabetSize(); j++) {
            if (table[i][j] == keyLetter) {
                columnIndex = j;
            }
        }
        return table[stringIndex][columnIndex];
    }

    private char decrypting(char encryptedLetter, char keyLetter) {
        int stringIndex = 0;
        int columnIndex = 0;
        int i = 0;
        for (int j = 0; j < getAlphabetSize(); j++) {
            if (table[i][j] == keyLetter) {
                columnIndex = j;
            }
        }

        int j = columnIndex;
        for (i = 0; i < getAlphabetSize(); i++) {
            if (table[i][j] == encryptedLetter) {
                stringIndex = i;
            }
        }
        return table[stringIndex][0];
    }

    private void encryptMessage() {
        for (int i = 0; i < message.length(); i++) {
            encryptedMessage.append(encrypting(message.charAt(i), fullKey.charAt(i)));
        }
    }

    private void decryptMessage() {
        for (int i = 0; i < encryptedMessage.length(); i++) {
            decryptedMessage.append(decrypting(encryptedMessage.charAt(i), fullKey.charAt(i)));
        }
    }
}
