package com.flyvr.chess.bean;

import com.flyvr.chess.ChessBitmapUtil;
import com.flyvr.chess.ChessBoard;

import java.util.List;

/**
 * Created by android studio
 * author:chen
 */
public class Car extends Chess {

    public Car(ChessBoard context, boolean campBlack, int x, int y) {
        super(context, campBlack, x, y);
        if (campBlack) {
            bg = ChessBitmapUtil.getBitmap("chess4.png");
        } else {
            bg = ChessBitmapUtil.getBitmap("chess11.png");
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

        if (newX - x != 0) {//水平走
            int s = Math.min(newX, x);
            int l = Math.max(newX, x);
            for (int i = s + 1; i < l; i++) {
                if (mContext.mChessCoordinates[i][y] != -1) {
                    return false;
                }
            }
        } else if (newY - y != 0) {//垂直走
            int s = Math.min(newY, y);
            int l = Math.max(newY, y);

            for (int i = s + 1; i < l; i++) {
                if (mContext.mChessCoordinates[x][i] != -1) {
                    return false;
                }
            }
        }

        return true;
    }
}
