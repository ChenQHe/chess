package com.flyvr.chess.bean;

import com.flyvr.chess.ChessBitmapUtil;
import com.flyvr.chess.ChessBoard;

import java.util.List;

/**
 * Created by android studio
 * author:chen
 */
public class King extends Chess {

    public King(ChessBoard context, boolean campBlack, int x, int y) {
        super(context, campBlack, x, y);
        if (campBlack) {
            bg = ChessBitmapUtil.getBitmap("chess0.png");
        } else {
            bg = ChessBitmapUtil.getBitmap("chess7.png");
        }
    }


    @Override
    protected List<Node> getCanModeNodes() {
        return null;
    }

    @Override
    protected boolean canMove(int newX, int newY) {

        //"将"吃"帅"，判断二者是否同列，且保证二者之间不存在任何棋子
        if (x == newX && (mContext.mChessCoordinates[newX][newY] == 40 || mContext.mChessCoordinates[newX][newY] == 49)) {
            Chess blackKing = mContext.mActiveChess.getValue(40);
            Chess redKing = mContext.mActiveChess.getValue(49);
            if (blackKing.x != redKing.x) {
                return false;
            }
            for (int i = blackKing.y + 1; i < redKing.y; i++) {
                if (mContext.mChessCoordinates[x][i] != -1) {
                    return false;
                }
            }
            return true;
        }

        //斜着走
        if ((newX - x) * (newY - y) != 0) {
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
