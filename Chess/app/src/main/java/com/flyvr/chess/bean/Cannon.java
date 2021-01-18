package com.flyvr.chess.bean;

import com.flyvr.chess.ChessBitmapUtil;
import com.flyvr.chess.ChessBoard;

import java.util.List;

/**
 * Created by android studio
 * author:chen
 */
public class Cannon extends Chess {

    public Cannon(ChessBoard context, boolean campBlack, int x, int y) {
        super(context, campBlack, x, y);
        if (campBlack) {
            bg = ChessBitmapUtil.getBitmap("chess5.png");
        } else {
            bg = ChessBitmapUtil.getBitmap("chess12.png");
        }
    }

    @Override
    protected List<Node> getCanModeNodes() {
        return null;
    }

    @Override
    protected boolean canMove(int newX, int newY) {

        //斜着走
        if ((newX - x) * (newY - y) != 0) {
            return false;
        }

        int count = 0;//统计 两点之间的棋子个数

        if (newX - x != 0) {//水平走
            int s = Math.min(newX, x);
            int l = Math.max(newX, x);
            for (int i = s + 1; i < l; i++) {
                if (mContext.mChessCoordinates[i][y] != -1) {
                    count = count + 1;
                    if (count > 1) {
                        return false;
                    }
                }
            }
        } else if (newY - y != 0) {//垂直走
            int s = Math.min(newY, y);
            int l = Math.max(newY, y);

            for (int i = s + 1; i < l; i++) {
                if (mContext.mChessCoordinates[x][i] != -1) {
                    count = count + 1;
                    if (count > 1) {
                        return false;
                    }
                }
            }
        }

        switch (count) {
            case 0: //新位置与起点位置之间没有棋子  但是 新位置处有棋子 炮如果没有炮台 无法吃棋 所以不能走
                return mContext.mChessCoordinates[newX][newY] == -1;
            case 1: //新位置与起点位置之间有一个棋子  但是 新位置处没有棋子 炮 只有吃棋子时才能过炮台
                return mContext.mChessCoordinates[newX][newY] != -1;
            default:
                return false;
        }
    }
}
