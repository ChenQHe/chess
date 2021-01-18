package com.flyvr.chess.bean;

import com.flyvr.chess.ChessBitmapUtil;
import com.flyvr.chess.ChessBoard;

import java.util.List;

/**
 * Created by android studio
 * author:chen
 */
public class Bachelor extends Chess {

    public Bachelor(ChessBoard context, boolean campBlack, int x, int y) {
        super(context,campBlack, x, y);
        if (campBlack) {
            bg = ChessBitmapUtil.getBitmap("chess1.png");
        } else {
            bg = ChessBitmapUtil.getBitmap("chess8.png");
        }
    }

    @Override
    protected List<Node> getCanModeNodes() {
        return null;
    }

    @Override
    protected boolean canMove(int newX, int newY) {

        //直着走
        if ((newX - x) * (newY - y) == 0) {
            return false;
        }

        //下棋距离超过一格
        if (Math.abs(newX - x) > 1 || Math.abs(newY - y) > 1) {
            return false;
        }

        if (isCampBlack()) {
            //超出九宫格区域
            return newX >= 3 && newX <= 5 && newY <= 2;
        } else {
            //超出九宫格区域
            return newX >= 3 && newX <= 5 && newY >= 7;
        }
    }
}
