package com.company;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Menu  extends  JFrame{
    Menu(){
       setTitle("Главное меню");
       setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// закрытие окна
       setSize(400, 400); // размер окна
       getContentPane().setLayout(null);

       JButton button = new JButton ("Начать игру"); // открыть игровое окно
       button.setBounds(90, 90, 200, 50);
       getContentPane().add(button);
       setVisible(true);
       ActionListener actionListener1 = new Button1ActionListener();
       button.addActionListener(actionListener1);

       JButton button2 = new JButton ("Выход"); // выход из игры
       button2.setBounds(90, 150, 200, 50);
       getContentPane().add(button2);
       setVisible(true);
       ActionListener actionListener2 = new Button2ActionListener();
       button2.addActionListener(actionListener2);
   }
}


