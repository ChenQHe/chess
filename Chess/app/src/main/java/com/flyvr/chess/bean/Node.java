package com.flyvr.chess.bean;

import androidx.annotation.NonNull;
/**
 * Created by android studio
 * author:chen
 *
 * 这是棋子在棋盘的坐标 棋盘的 左上角为原点（0，0），右下角为底顶点（8，9）
 */
public class Node {
    private int x;
    private int y;

    public Node(int pX, int pY) {
        setNode(pX, pY);
    }

    public void setNode(int pX, int pY) {
        x = pX;
        y = pY;
    }

    public int getX() {
        return x;
    }

    public void setX(int pX) {
        x = pX;
    }

    public int getY() {
        return y;
    }

    public void setY(int pY) {
        y = pY;
    }

    @NonNull
    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
