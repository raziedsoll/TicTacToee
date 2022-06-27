package com.company;

import javax.management.remote.JMXConnectorFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {
    public static final int FIELD_EMPTY = 0; //пустое поле
    public static final int FIELD_X = 10; // поле с крестиком
    public static final int FIELD_O = 200; // поле с ноликом
    private int[][] field; // массив игрового поля
    private boolean isXturn; // true - ход X , false - ход O
    private JFrame menuWindow;
    private JFrame gameWindow;

    public TicTacToe(JFrame menuWindow, JFrame gameWindow){
        this.menuWindow = menuWindow;
        this.gameWindow = gameWindow;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field = new int[3][3]; // выделяем память
        initGame();
    }

    public void initGame() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                field[i][j] = FIELD_EMPTY;
            }
        }
        isXturn = true;
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getButton() == MouseEvent.BUTTON1) {
            int x = mouseEvent.getX(); // координата x клика
            int y = mouseEvent.getY(); // координата y клика
            // переводим координаты в индексы ячейки в массиве field
            int i = (int) ((float) x / getWidth() * 3);
            int j = (int) ((float) y / getHeight() * 3);
            // проверяем, что выбранная ячейка пуста и туда можно сходить
            if (field[i][j] == FIELD_EMPTY) {
                // чей ход
                field[i][j] = isXturn ? FIELD_X : FIELD_O;
                isXturn = !isXturn; // меняем флаг хода
                repaint(); // перерисовка компонента
            }
            int res = checkState();
            if(res != 0){
                if (res == FIELD_O * 3){
                    //победил O
                    JOptionPane.showMessageDialog(this, "Нолики выйграли",
                            "Победа!", JOptionPane.INFORMATION_MESSAGE);
                             gameWindow.setVisible(false);
                             menuWindow.setVisible(true);
                } else if (res == FIELD_X * 3){
                    // победилп x
                    JOptionPane.showMessageDialog(this, "Крестики выйграли",
                            "Победа!", JOptionPane.INFORMATION_MESSAGE);
                            gameWindow.setVisible(false);
                            menuWindow.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Никто не выйграл!",
                            "Ничья!", JOptionPane.INFORMATION_MESSAGE);
                            gameWindow.setVisible(false);
                            menuWindow.setVisible(true);
                }
                initGame();
                repaint();
            }

        }

    }



    @Override
    protected void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        //очищаем область
        graphics.clearRect(0, 0, getWidth(), getHeight());
        // рисуем сетку
        drawGrid(graphics);
        // рисуем текущие крестики и нолики
        drawXO(graphics);
        // рисуем красную линию в победной зоне
        drawRedLine(graphics);
    }

    void drawGrid(Graphics graphics){
        int w = getWidth(); //ширина игрового поля
        int h = getHeight(); // высота игрового поля
        int dw = w / 3; //делим ширину на 3
        int dh = h / 3; //делим высоту на 3
        graphics.setColor(Color.BLUE); //цвет линий
        for (int i = 1; i < 3; i++) {
            graphics.drawLine(0, dh * i, w, dh * i);
            graphics.drawLine(dw * i, 0, dw * i , h);
        }
    }

    void drawRedLine(Graphics graphics){
        graphics.setColor(Color.RED);
        int w = getWidth(); //ширина игрового поля
        int h = getHeight(); // высота игрового поля
        int dw = w / 3; //делим ширину на 3
        int dh = h / 3; //делим высоту на 3

        // проверка диагоналей
        int diag = 0;
        int diag2 = 0;
        for (int i = 0; i < 3; i++) {
            //сумма значений по диагонали от левого угла
            diag += field[i][i];
            // сумма значений по диагонали от правого угла
            diag2 += field[i][2 - i];
        }
        if(diag == FIELD_O * 3 || diag == FIELD_X * 3)
            graphics.drawLine(0,0,w,h);
        if(diag2 == FIELD_O * 3 || diag2 == FIELD_X * 3)
            graphics.drawLine(w,0,0,h);

        // проверка строк и столбов
        int check_i, check_j;
        for(int i = 0 ; i < 3; i++){
            check_i = field[0][i] + field[1][i] + field[2][i];
            check_j = field[i][0] + field[i][1] + field[i][2];

            if(check_i == FIELD_O * 3 || check_i == FIELD_X * 3){
                // рисование красной линии
                graphics.drawLine(0, dh * (i+1) - 61, w,dh * (i+1) - 61);
                break;
            }
            if(check_j == FIELD_O * 3 || check_j == FIELD_X * 3){
                // рисование красной линии
                graphics.drawLine(dw * (i + 1) - 61, 0, dw * (i + 1) - 61,h);
                break;
            }
        }
    }


    void drawX(int i, int j, Graphics graphics){
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        //линия от верхнего левого угла в правый нижний
        graphics.drawLine(x, y, x + dw, y + dh);
        //линия от левого нижнего угла до правого верхнего
        graphics.drawLine(x, y + dh, x + dw, y);
    }

    void draw0(int i, int j, Graphics graphics){
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        // вытягиваем окружность
        graphics.drawOval(x, y, dw, dh);
    }

    void drawXO(Graphics graphics){
        //вложенные циклы
        for (int i = 0; i < 3; ++i){
            for (int j = 0; j < 3; ++j){
                //если в данной ячейке крестик - рисуем его
                if (field[i][j] == FIELD_X){
                    drawX(i, j, graphics);
                } else if (field[i][j] == FIELD_O){
                    draw0(i, j, graphics);
                }
            }
        }
    }

    int checkState() {
        // проверка диагоналей
        int diag = 0;
        int diag2 = 0;
        for (int i = 0; i < 3; i++) {
            //сумма значений по диагонали от левого угла
            diag += field[i][i];
            // сумма значений по диагонали от правого угла
            diag2 += field[i][2 - i];

        }

        if(diag == FIELD_O * 3 || diag == FIELD_X * 3)
            return diag;
        if(diag2 == FIELD_O * 3 || diag2 == FIELD_X * 3)
            return diag2;

        int check_i, check_j;
        boolean hasEmpty = false;
        //будем бегать по всем рядам
        for(int i = 0 ; i < 3; i++){
            check_i = 0;
            check_j = 0;
            for(int j=0; j < 3; j++){
                //суммируем знаки в текущем ряду
                if(field[i][j] == 0){
                    hasEmpty = true;
                }
                check_i += field[i][j];
                check_j += field[j][i];
            }
            // если выигрыш крестика или нолика, то выхоим
            if(check_i == FIELD_O*3 || check_i == FIELD_X * 3){
                return check_i;
            }
            if(check_j == FIELD_O * 3 || check_j == FIELD_X * 3){
                return check_j;
            }
        }

        if(hasEmpty) return 0;
        else return -1;
    }
}

