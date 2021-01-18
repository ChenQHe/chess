package com.flyvr.chess.bean;

import com.flyvr.chess.ChessBitmapUtil;
import com.flyvr.chess.ChessBoard;

import java.util.List;

/**
 * Created by android studio
 * author:chen
 */
public class Soldier extends Chess {

    public Soldier(ChessBoard context, boolean campBlack, int x, int y) {
        super(context, campBlack, x, y);
        if (campBlack) {
            bg = ChessBitmapUtil.getBitmap("chess6.png");
        } else {
            bg = ChessBitmapUtil.getBitmap("chess13.png");
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

        //下棋距离超过一格
        if (Math.abs(newX - x) > 1 || Math.abs(newY - y) > 1) {
            return false;
        }

        if (isCampBlack()) { //如果是黑棋
            if (newY < y) return false; //黑卒只能往下走
            return newY >= 5 || (newX - x) == 0; //不过河 不能水平移动
        } else {
            if (newY > y) return false;//红兵只能往上走
            return newY <= 4 || (newX - x) == 0;//不过河 不能水平移动
        }
    }
}
