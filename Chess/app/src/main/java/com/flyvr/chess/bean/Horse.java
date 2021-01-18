package com.flyvr.chess.bean;

import com.flyvr.chess.ChessBitmapUtil;
import com.flyvr.chess.ChessBoard;

import java.util.List;

/**
 * Created by android studio
 * author:chen
 */
public class Horse extends Chess {

    public Horse(ChessBoard context, boolean campBlack, int x, int y) {
        super(context, campBlack, x, y);
        if (campBlack) {
            bg = ChessBitmapUtil.getBitmap("chess3.png");
        } else {
            bg = ChessBitmapUtil.getBitmap("chess10.png");
        }
    }

    @Override
    protected List<Node> getCanModeNodes() {
        return null;
    }

    @Override
    protected boolean canMove(int newX, int newY) {
        //如果马不走"日"字，即横向位移量乘纵向位移量不等于2
        if (Math.abs(newX - x) * Math.abs(newY - y) != 2) {
            return false;
        }

        if (x != 0 && mContext.mChessCoordinates[x - 1][y] != -1) { //左边 绊左腿 即 不能往左 跳 “倒日“
            if (newX - x == -2) {
                return false;
            }
        }
        if (x != 8 && mContext.mChessCoordinates[x + 1][y] != -1) { //右边 绊右腿 即 不能往右 跳 “倒日“
            if (newX - x == 2) {
                return false;
            }
        }
        if (y != 0 && mContext.mChessCoordinates[x][y - 1] != -1) { //上边 绊上腿 即 不能往上 跳 “日“
            if (newY - y == -2) {
                return false;
            }
        }
        if (y != 9 && mContext.mChessCoordinates[x][y + 1] != -1) { //下边 绊下腿 即 不能往下 跳 “日“
            if (newY - y == 2) {
                return false;
            }
        }
        return true;
    }
}
