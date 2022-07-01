package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class Menu {
    Menu() {
        JFrame menuWindow = new JFrame("TicTacToe");
        menuWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuWindow.setSize(400, 400);
        menuWindow.getContentPane().setLayout(null);

        JButton button1 = new JButton("Начать игру"); // Начать игру, выбрав размер сетки
        button1.setBounds(90, 90, 200, 50);
        menuWindow.getContentPane().add(button1);
        menuWindow.setVisible(true);
        ActionListener actionListener1 = new Button1ActionListener(menuWindow);
        button1.addActionListener(actionListener1);

        JButton button2 = new JButton("Авторизация"); // Авторизироваться
        button2.setBounds(90, 150, 200, 50);
        menuWindow.getContentPane().add(button2);
        menuWindow.setVisible(true);
        //ActionListener actionListener2 = new Button2ActionListener(menuWindow);
        //button2.addActionListener(actionListener2);

        JButton button3 = new JButton("Выход"); // выход из игры
        button3.setBounds(90, 300, 200, 50);
        menuWindow.getContentPane().add(button3);
        menuWindow.setVisible(true);
        ActionListener actionListener3 = new Button3ActionListener();
        button3.addActionListener(actionListener3);
    }


}


class Button1ActionListener implements ActionListener {
    private JFrame menuWindow;

    Button1ActionListener(JFrame menuWindow) {
        this.menuWindow = menuWindow;
    }

    public void actionPerformed(ActionEvent e) {
        menuWindow.setVisible(false);
        selectMenu select_menu = new selectMenu(menuWindow); // Вызов меню выбора размера сетки
    }
}


class Button3ActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.exit(1); // Завершение выполнения
    }
}

