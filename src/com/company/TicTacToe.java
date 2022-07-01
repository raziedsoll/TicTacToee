package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;


public class TicTacToe extends JComponent {
    public static final int FIELD_EMPTY = 0; //пустое поле
    public static final int FIELD_X = 10; // поле с крестиком
    public static final int FIELD_O = 200; // поле с ноликом
    private int[][] field; // массив игрового поля
    private int N;
    private boolean isXturn; // true - ход X , false - ход O
    private JFrame menuWindow;
    private JFrame gameWindow;

    public TicTacToe(JFrame menuWindow, JFrame gameWindow, int N) {
        this.menuWindow = menuWindow;
        this.gameWindow = gameWindow;
        this.N = N;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field = new int[N][N]; // выделяем память
        initGame();
    }

    public void initGame() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
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
            int i = (int) ((float) x / getWidth() * N);
            int j = (int) ((float) y / getHeight() * N);
            // проверяем, что выбранная ячейка пуста и туда можно сходить
            if (field[i][j] == FIELD_EMPTY) {
                // чей ход
                field[i][j] = isXturn ? FIELD_X : FIELD_O;
                isXturn = !isXturn; // меняем флаг хода
                repaint(); // перерисовка компонента
            }
            int res = checkState();
            if (res != 0) {
                if (res == FIELD_O * N) {
                    //победил O
                    JOptionPane.showMessageDialog(this, "Нолики выйграли",
                            "Победа!", JOptionPane.INFORMATION_MESSAGE);
                    gameWindow.setVisible(false);
                    menuWindow.setVisible(true);
                } else if (res == FIELD_X * N) {
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
    protected void paintComponent(Graphics graphics) {
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

    void drawGrid(Graphics graphics) {
        int w = getWidth(); //ширина игрового поля
        int h = getHeight(); // высота игрового поля
        int dw = w / N; //делим ширину на 3
        int dh = h / N; //делим высоту на 3
        graphics.setColor(Color.BLUE); //цвет линий
        for (int i = 1; i < N; i++) {
            graphics.drawLine(0, dh * i, w, dh * i);
            graphics.drawLine(dw * i, 0, dw * i, h);
        }
    }

    void drawRedLine(Graphics graphics) {
        graphics.setColor(Color.RED);
        int w = getWidth(); //ширина игрового поля
        int h = getHeight(); // высота игрового поля
        int dw = w / N; //делим ширину на 3
        int dh = h / N; //делим высоту на 3

        // проверка диагоналей
        int diag = 0;
        int diag2 = 0;
        for (int i = 0; i < N; i++) {
            //сумма значений по диагонали от левого угла
            diag += field[i][i];
            // сумма значений по диагонали от правого угла
            diag2 += field[i][N - 1 - i];
        }
        if (diag == FIELD_O * N || diag == FIELD_X * N)
            graphics.drawLine(0, 0, w, h);
        if (diag2 == FIELD_O * N || diag2 == FIELD_X * N)
            graphics.drawLine(w, 0, 0, h);

        // проверка строк и столбов

        for (int i = 0; i < N; i++) {
            int check_i = 0, check_j = 0;
            for (int j = 0; j < N; j++) {
                check_i += field[j][i];
                check_j += field[i][j];

                if (check_i == FIELD_O * N || check_i == FIELD_X * N) {
                    // рисование красной линии
                    graphics.drawLine(0, dh * (i + 1) - (61 * 3 / N), w, dh * (i + 1) - (61 * 3 / N));
                    break;
                }
                if (check_j == FIELD_O * N || check_j == FIELD_X * N) {
                    // рисование красной линии
                    graphics.drawLine(dw * (i + 1) - (61 * 3 / N), 0, dw * (i + 1) - (61 * 3 / N), h);
                    break;
                }
            }


        }
    }


    void drawX(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / N;
        int dh = getHeight() / N;
        int x = i * dw;
        int y = j * dh;
        //линия от верхнего левого угла в правый нижний
        graphics.drawLine(x, y, x + dw, y + dh);
        //линия от левого нижнего угла до правого верхнего
        graphics.drawLine(x, y + dh, x + dw, y);
    }

    void draw0(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int dw = getWidth() / N;
        int dh = getHeight() / N;
        int x = i * dw;
        int y = j * dh;
        // вытягиваем окружность
        graphics.drawOval(x, y, dw, dh);
    }

    void drawXO(Graphics graphics) {
        //вложенные циклы
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                //если в данной ячейке крестик - рисуем его
                if (field[i][j] == FIELD_X) {
                    drawX(i, j, graphics);
                } else if (field[i][j] == FIELD_O) {
                    draw0(i, j, graphics);
                }
            }
        }
    }

    int checkState() {
        // проверка диагоналей
        int diag = 0;
        int diag2 = 0;
        for (int i = 0; i < N; i++) {
            //сумма значений по диагонали от левого угла
            diag += field[i][i];
            // сумма значений по диагонали от правого угла
            diag2 += field[i][N - 1 - i];

        }

        if (diag == FIELD_O * N || diag == FIELD_X * N)
            return diag;
        if (diag2 == FIELD_O * N || diag2 == FIELD_X * N)
            return diag2;

        int check_i, check_j;
        boolean hasEmpty = false;
        //будем бегать по всем рядам
        for (int i = 0; i < N; i++) {
            check_i = 0;
            check_j = 0;
            for (int j = 0; j < N; j++) {
                //суммируем знаки в текущем ряду
                if (field[i][j] == 0) {
                    hasEmpty = true;
                }
                check_i += field[i][j];
                check_j += field[j][i];
            }
            // если выигрыш крестика или нолика, то выхоим
            if (check_i == FIELD_O * N || check_i == FIELD_X * N) {
                return check_i;
            }
            if (check_j == FIELD_O * N || check_j == FIELD_X * N) {
                return check_j;
            }
        }

        if (hasEmpty) return 0;
        else return -1;
    }
}

