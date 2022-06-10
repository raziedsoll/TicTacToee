package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button2ActionListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.exit(1); // Завершение выполнения
    }
}
