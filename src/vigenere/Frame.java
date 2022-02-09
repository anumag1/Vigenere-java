package vigenere;

import javax.swing.*;
import java.awt.*;

public class Frame {
    private final JFrame mainFrame;
    private final JCheckBox uaUA;
    private final JCheckBox ruRU;
    private final JCheckBox enEN;

    public Frame() {
        mainFrame = new JFrame();
        mainFrame.setTitle("Vigenere");
        mainFrame.setSize(500, 360);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);

        JLabel head = new JLabel("Шифр Віженера");
        head.setFont(new Font("Serif", Font.BOLD, 20));

        JButton encryptButton = new JButton("Зашифрувати");

        JTextArea output = new JTextArea(5, 21);
        output.setEditable(false);
        JScrollPane paneOut = new JScrollPane(output);

        JTextArea inputMessage = new JTextArea("Введіть повідомлення", 5, 21);
        JScrollPane paneInMessage = new JScrollPane(inputMessage);

        JTextArea inputKey = new JTextArea("Введіть ключ", 3, 21);
        JScrollPane paneInKey = new JScrollPane(inputKey);

        JButton decryptButton = new JButton("Розшифрувати");

        uaUA = new JCheckBox("Український алфавіт");
        ruRU = new JCheckBox("Російський алфавіт");
        enEN = new JCheckBox("Англійський алфавіт");

        JPanel header = new JPanel();
        header.setBorder(BorderFactory.createLineBorder(Color.black));
        header.add(head);

        JPanel alphabets = new JPanel();
        alphabets.setLayout(new GridBagLayout());
        GridBagConstraints alpha = new GridBagConstraints();
        alpha.fill = GridBagConstraints.HORIZONTAL;
        alpha.gridy = 0;
        alpha.gridx = 0;
        alphabets.add(uaUA, alpha);
        alpha.gridy = 1;
        alphabets.add(ruRU, alpha);
        alpha.gridy = 2;
        alphabets.add(enEN, alpha);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel cipher = new JPanel();

        cipher.add(paneInMessage);
        cipher.add(paneInKey);
        cipher.add(encryptButton);
        cipher.add(decryptButton);
        cipher.add(paneOut);

        mainPanel.add(alphabets, BorderLayout.WEST);
        mainPanel.add(cipher, BorderLayout.CENTER);

        mainFrame.add(header, BorderLayout.PAGE_START);
        mainFrame.add(mainPanel, BorderLayout.CENTER);

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

                    String out = vig.encrypt();
                    out = out.replaceAll("(.{20})", "$1\n");
                    output.setText(out);
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
                String message = inputMessage.getText();
                message = message.replaceAll("\\s+", "");
                String key = inputKey.getText();
                key = key.replaceAll("\\s+", "");
                if (key.length() == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "Поле з ключем не має бути пустим!");
                } else if (message.length() == 0) {
                    JOptionPane.showMessageDialog(mainFrame, "Поле з повідомленням не має бути пустим!");
                } else {
                    vig.setEncryptedMessage(message);
                    vig.setKey(key);

                    String out = vig.decrypt();
                    out = out.replaceAll("(.{20})", "$1\n");
                    output.setText(out);
                    mainFrame.revalidate();
                }
            }
        });
    }
}
