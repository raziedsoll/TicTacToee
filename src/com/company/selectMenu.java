package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class selectMenu {
    private JFrame menuWindow;

    selectMenu(JFrame menuWindow) {
        this.menuWindow = menuWindow;

        JFrame choseWindow = new JFrame("Размер поля");
        choseWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        choseWindow.setSize(400, 400);
        choseWindow.getContentPane().setLayout(null);

        JButton button1 = new JButton("3 на 3");
        button1.setBounds(90, 80, 200, 50);
        choseWindow.getContentPane().add(button1);
        choseWindow.setVisible(true);
        ActionListener actionListener1 = new Button10ActionListener(menuWindow, choseWindow);
        button1.addActionListener(actionListener1);

        JButton button2 = new JButton("4 на 4");
        button2.setBounds(90, 140, 200, 50);
        choseWindow.getContentPane().add(button2);
        choseWindow.setVisible(true);
        ActionListener actionListener2 = new Button20ActionListener(menuWindow, choseWindow);
        button2.addActionListener(actionListener2);

        JButton button3 = new JButton("5 на 5");
        button3.setBounds(90, 200, 200, 50);
        choseWindow.getContentPane().add(button3);
        choseWindow.setVisible(true);
        ActionListener actionListener3 = new Button30ActionListener(menuWindow, choseWindow);
        button3.addActionListener(actionListener3);

        JButton button4 = new JButton("Назад");
        button4.setBounds(90, 300, 200, 50);
        choseWindow.getContentPane().add(button4);
        choseWindow.setVisible(true);
        ActionListener actionListener4 = new Button40ActionListener(menuWindow, choseWindow);
        button4.addActionListener(actionListener4);
    }
}

class Button10ActionListener implements ActionListener {
    private JFrame menuWindow;
    private JFrame choseWindow;

    Button10ActionListener(JFrame menuWindow, JFrame choseWindow) {
        this.menuWindow = menuWindow;
        this.choseWindow = choseWindow;
    }

    public void actionPerformed(ActionEvent e) {
        JFrame gameWindow = new JFrame("TicTacToe - 3x3"); // главное меню
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// закрытие окна
        gameWindow.setSize(400, 400); // размер окна
        gameWindow.setLayout(new BorderLayout()); //менеджер компановки
        gameWindow.setLocationRelativeTo(null); // разместить окно в центре
        gameWindow.setVisible(true); //видимость окна
        choseWindow.setVisible(false);
        TicTacToe game = new TicTacToe(menuWindow, gameWindow, 3);
        gameWindow.add(game);
    }
}


class Button20ActionListener implements ActionListener {
    private JFrame menuWindow;
    private JFrame choseWindow;

    Button20ActionListener(JFrame menuWindow, JFrame choseWindow) {
        this.menuWindow = menuWindow;
        this.choseWindow = choseWindow;
    }

    public void actionPerformed(ActionEvent e) {
        JFrame gameWindow = new JFrame("TicTacToe - 4x4"); // главное меню
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// закрытие окна
        gameWindow.setSize(400, 400); // размер окна
        gameWindow.setLayout(new BorderLayout()); //менеджер компановки
        gameWindow.setLocationRelativeTo(null); // разместить окно в центре
        gameWindow.setVisible(true); //видимость окна
        choseWindow.setVisible(false);
        TicTacToe game = new TicTacToe(menuWindow, gameWindow, 4);
        gameWindow.add(game);
    }
}

class Button30ActionListener implements ActionListener {
    private JFrame menuWindow;
    private JFrame choseWindow;

    Button30ActionListener(JFrame menuWindow, JFrame choseWindow) {
        this.menuWindow = menuWindow;
        this.choseWindow = choseWindow;
    }

    public void actionPerformed(ActionEvent e) {
        JFrame gameWindow = new JFrame("TicTacToe - 5x5"); // главное меню
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// закрытие окна
        gameWindow.setSize(400, 400); // размер окна
        gameWindow.setLayout(new BorderLayout()); //менеджер компановки
        gameWindow.setLocationRelativeTo(null); // разместить окно в центре
        gameWindow.setVisible(true); //видимость окна
        choseWindow.setVisible(false);
        TicTacToe game = new TicTacToe(menuWindow, gameWindow, 5);
        gameWindow.add(game);
    }
}

class Button40ActionListener implements ActionListener {
    private JFrame menuWindow;
    private JFrame choseWindow;

    Button40ActionListener(JFrame menuWindow, JFrame choseWindow) {
        this.menuWindow = menuWindow;
        this.choseWindow = choseWindow;
    }

    public void actionPerformed(ActionEvent e) {
        choseWindow.setVisible(false);
        menuWindow.setVisible(true);
    }
}