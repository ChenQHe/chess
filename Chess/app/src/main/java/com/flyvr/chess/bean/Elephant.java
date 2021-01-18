package com.flyvr.chess.bean;

import com.flyvr.chess.ChessBitmapUtil;
import com.flyvr.chess.ChessBoard;

import java.util.List;

/**
 * Created by android studio
 * author:chen
 */
public class Elephant extends Chess {

    public Elephant(ChessBoard context, boolean campBlack, int x, int y) {
        super(context, campBlack, x, y);
        if (campBlack) {
            bg = ChessBitmapUtil.getBitmap("chess2.png");
        } else {
            bg = ChessBitmapUtil.getBitmap("chess9.png");
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

        if (isCampBlack()) {
            if (newY > 4) {
                return false;
            }
        } else {
            if (newY < 5) {
                return false;
            }
        }
        //下棋距离超过一格
        if (Math.abs(newX - x) == 2 && Math.abs(newY - y) == 2) {
            return mContext.mChessCoordinates[(newX + x) / 2][(newY + y) / 2] == -1;
        } else {
            return false;
        }
    }
}
