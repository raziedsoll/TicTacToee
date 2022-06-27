package com.company;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Menu {
      private JFrame menuWindow;
      Menu(){
         JFrame menuWindow = new JFrame("TicTacToe");
         menuWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         menuWindow.setSize(400, 400);
         menuWindow.getContentPane().setLayout(null);

         JButton button = new JButton ("Начать игру"); // открыть игровое окно
         button.setBounds(90, 90, 200, 50);
         menuWindow.getContentPane().add(button);
         menuWindow.setVisible(true);
         ActionListener actionListener1 = new Button1ActionListener(menuWindow);
         button.addActionListener(actionListener1);

         JButton button2 = new JButton ("Выход"); // выход из игры
         button2.setBounds(90, 150, 200, 50);
         menuWindow.getContentPane().add(button2);
         menuWindow.setVisible(true);
         ActionListener actionListener2 = new Button2ActionListener();
         button2.addActionListener(actionListener2);
      }


}

class Button1ActionListener implements ActionListener{
   private JFrame menuWindow;
   Button1ActionListener(JFrame menuWindow) {
      this.menuWindow = menuWindow;
   }
   public void actionPerformed(ActionEvent e) {
      menuWindow.setVisible(false);
      JFrame gameWindow = new JFrame("TicTacToe"); // главное меню
      gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// закрытие окна
      gameWindow.setSize(400, 400); // размер окна
      gameWindow.setLayout(new BorderLayout()); //менеджер компановки
      gameWindow.setLocationRelativeTo(null); // разместить окно в центре
      gameWindow.setVisible(true); //видимость окна
      TicTacToe game = new TicTacToe(menuWindow, gameWindow);
      gameWindow.add(game);
   }
}

class Button2ActionListener implements ActionListener {
   public void actionPerformed(ActionEvent e) {
      System.exit(1); // Завершение выполнения
   }
}

