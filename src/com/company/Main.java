package com.company;
import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
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
