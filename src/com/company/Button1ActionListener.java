package com.company;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Button1ActionListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
        JFrame window = new JFrame("TicTacToe"); // главное меню
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// закрытие окна
        window.setSize(400, 400); // размер окна
        window.setLayout(new BorderLayout()); //менеджер компановки
        window.setLocationRelativeTo(null); // разместить окно в центре
        window.setVisible(true); //видимость окна
        TicTacToe game = new TicTacToe();
        window.add(game);
    }
}
