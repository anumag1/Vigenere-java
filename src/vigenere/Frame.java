package vigenere;

import javax.swing.*;

public class Frame {
    private final JFrame mainFrame;
    private final JTextField inputMessage;
    private final JTextField inputEncryptedMessage;
    private final JTextField inputKey;
    private final JTextField inputKey2;
    private final JTextField encryptionOutput;
    private final JTextField decryptionOutput;
    private final JCheckBox uaUA;
    private final JCheckBox ruRU;
    private final JCheckBox enEN;

    public Frame() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Vigenere");
        mainFrame.setSize(700, 700);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        JPanel alphabetPanel = new JPanel();
        JPanel encryptPanel = new JPanel();
        JPanel decryptPanel = new JPanel();
        JPanel encryptOutputPanel = new JPanel();
        JPanel decryptOutputPanel = new JPanel();
        JPanel headerPanel = new JPanel();

        JLabel label = new JLabel("Шифр Віженера");
        headerPanel.add(label);

        uaUA = new JCheckBox("Український алфавіт");
        ruRU = new JCheckBox("Російський алфавіт");
        enEN = new JCheckBox("Англійський алфавіт");
        alphabetPanel.add(uaUA);
        alphabetPanel.add(ruRU);
        alphabetPanel.add(enEN);

        inputMessage = new JTextField("Введіть повідомлення");
        inputKey = new JTextField("Введіть ключ");
        JButton encryptButton = new JButton("Зашифрувати");
        encryptPanel.add(inputMessage);
        encryptPanel.add(inputKey);
        encryptPanel.add(encryptButton);

        encryptionOutput = new JTextField("Зашифроване повідомлення", 16);
        encryptOutputPanel.add(encryptionOutput);
        decryptionOutput = new JTextField("Розшифроване повідомлення", 16);
        decryptOutputPanel.add(decryptionOutput);

        inputEncryptedMessage = new JTextField("Введіть зашифроване повідомлення");
        inputKey2 = new JTextField("Введіть ключ");
        JButton decryptButton = new JButton("Розшифрувати");
        decryptPanel.add(inputEncryptedMessage);
        decryptPanel.add(inputKey2);
        decryptPanel.add(decryptButton);

        mainPanel.add(headerPanel);
        mainPanel.add(alphabetPanel);
        mainPanel.add(encryptPanel);
        mainPanel.add(encryptOutputPanel);
        mainPanel.add(decryptPanel);
        mainPanel.add(decryptOutputPanel);

        mainFrame.add(mainPanel);

        mainFrame.setVisible(true);

        encryptButton.addActionListener(le -> {
            vigenere.Vigenere vig = new vigenere.Vigenere();

            int countAlphabets = 0;
            if (uaUA.isSelected()) {
                vig.addAlphabet("абвгґдеєжзиіїйклмнопрстуфхцчшщьюяАБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ");
                countAlphabets++;
            }
            if (ruRU.isSelected()) {
                vig.addAlphabet("абвгдеёжзийклмнопрстуфхцчшщъыьюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЮЯ");
                countAlphabets++;
            }
            if (enEN.isSelected()) {
                vig.addAlphabet("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
                countAlphabets++;
            }

            if (countAlphabets == 0) {
                JOptionPane.showMessageDialog(mainFrame, "Ви маєте вибрати хоча б один алфавіт!");
            } else {
                String message = inputMessage.getText();
                message = message.replaceAll("\\s+", "");

                String key = inputKey.getText();
                key = key.replaceAll("\\s+", "");

                if (key.length() == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "Поле з ключем не має бути пустим!");
                } else if (message.length() == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "Поле з повідомленням не має бути пустим!");
                } else {
                    vig.setMessage(message);
                    vig.setKey(key);

                    encryptionOutput.setText(vig.encrypt());
                    mainFrame.revalidate();
                }
            }
        });

        decryptButton.addActionListener(le -> {
            Vigenere vig = new Vigenere();

            int countAlphabets = 0;
            if (uaUA.isSelected()) {
                vig.addAlphabet("абвгґдеєжзиіїйклмнопрстуфхцчшщьюяАБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ");
                countAlphabets++;
            }
            if (ruRU.isSelected()) {
                vig.addAlphabet("абвгдеёжзийклмнопрстуфхцчшщъыьюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЮЯ");
                countAlphabets++;
            }
            if (enEN.isSelected()) {
                vig.addAlphabet("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
                countAlphabets++;
            }

            if (countAlphabets == 0) {
                JOptionPane.showMessageDialog(mainFrame, "Ви маєте вибрати хоча б один алфавіт!");
            } else {
                String message = inputEncryptedMessage.getText();
                message = message.replaceAll("\\s+", "");
                String key = inputKey2.getText();
                key = key.replaceAll("\\s+", "");
                if (key.length() == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "Поле з ключем не має бути пустим!");
                } else if (message.length() == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "Поле з повідомленням не має бути пустим!");
                } else {
                    vig.setEncryptedMessage(message);
                    vig.setKey(key);

                    decryptionOutput.setText(vig.decrypt());
                    mainFrame.revalidate();
                }
            }
        });
    }
}