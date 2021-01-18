package com.flyvr.chess;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.flyvr.chess.bean.Bachelor;
import com.flyvr.chess.bean.Cannon;
import com.flyvr.chess.bean.Car;
import com.flyvr.chess.bean.Chess;
import com.flyvr.chess.bean.Elephant;
import com.flyvr.chess.bean.Horse;
import com.flyvr.chess.bean.King;
import com.flyvr.chess.bean.Node;
import com.flyvr.chess.bean.Soldier;

/**
 * Created by android studio
 * author:chen
 */
public class ChessBoard implements Drawer {

    public static final int MAX_X = 8;
    public static final int MAX_Y = 9;

    private static final int OFFSET_X = 70;
    private static final int OFFSET_Y = 390;
    private static final int PADDING = 80;

    private final Bitmap bg;
    private final Rect mRect;
    /**
     * x 方向 每个坐标歌的单位像素
     */
    private final int mBaseX;
    /**
     * y 方向 每个坐标歌的单位像素
     */
    private final int mBaseY;
    public final ChessList mActiveChess;
    /**
     * 棋子在棋盘的坐标
     */
    public final int[][] mChessCoordinates;

    public ChessBoard() {
        bg = ChessBitmapUtil.getBitmap("chessBoard.png");
        mRect = new Rect(OFFSET_X, OFFSET_Y, 692 * 4 / 3 + 70, 764 * 2 - 45);

        mActiveChess = new ChessList();
        mChessCoordinates = new int[MAX_X + 1][MAX_Y + 1];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 9; j++) {
                mChessCoordinates[i][j] = -1;
            }
        }

        mBaseX = (getWidth() - 2 * PADDING) / MAX_X;
        mBaseY = (getHeight() - 2 * PADDING) / MAX_Y;

        //帅
        King lKing = new King(this, true, 4, 0);
        mActiveChess.add(lKing);
        lKing = new King(this, false, 4, 9);
        mActiveChess.add(lKing);

        //仕
        Bachelor lBachelor = new Bachelor(this, true, 3, 0);
        mActiveChess.add(lBachelor);
        lBachelor = new Bachelor(this, true, 5, 0);
        mActiveChess.add(lBachelor);
        lBachelor = new Bachelor(this, false, 3, 9);
        mActiveChess.add(lBachelor);
        lBachelor = new Bachelor(this, false, 5, 9);
        mActiveChess.add(lBachelor);

        //相
        Elephant lElephant = new Elephant(this, true, 2, 0);
        mActiveChess.add(lElephant);
        lElephant = new Elephant(this, true, 6, 0);
        mActiveChess.add(lElephant);
        lElephant = new Elephant(this, false, 2, 9);
        mActiveChess.add(lElephant);
        lElephant = new Elephant(this, false, 6, 9);
        mActiveChess.add(lElephant);

        //马
        Horse lHorse = new Horse(this, true, 1, 0);
        mActiveChess.add(lHorse);
        lHorse = new Horse(this, true, 7, 0);
        mActiveChess.add(lHorse);
        lHorse = new Horse(this, false, 1, 9);
        mActiveChess.add(lHorse);
        lHorse = new Horse(this, false, 7, 9);
        mActiveChess.add(lHorse);

        //车
        Car lCar = new Car(this, true, 0, 0);
        mActiveChess.add(lCar);
        lCar = new Car(this, true, 8, 0);
        mActiveChess.add(lCar);
        lCar = new Car(this, false, 0, 9);
        mActiveChess.add(lCar);
        lCar = new Car(this, false, 8, 9);
        mActiveChess.add(lCar);

        //炮
        Cannon lCannon = new Cannon(this, true, 1, 2);
        mActiveChess.add(lCannon);
        lCannon = new Cannon(this, true, 7, 2);
        mActiveChess.add(lCannon);
        lCannon = new Cannon(this, false, 1, 7);
        mActiveChess.add(lCannon);
        lCannon = new Cannon(this, false, 7, 7);
        mActiveChess.add(lCannon);

        //兵
        Soldier lSoldier = new Soldier(this, true, 0, 3);
        mActiveChess.add(lSoldier);
        lSoldier = new Soldier(this, true, 2, 3);
        mActiveChess.add(lSoldier);
        lSoldier = new Soldier(this, true, 4, 3);
        mActiveChess.add(lSoldier);
        lSoldier = new Soldier(this, true, 6, 3);
        mActiveChess.add(lSoldier);
        lSoldier = new Soldier(this, true, 8, 3);
        mActiveChess.add(lSoldier);
        lSoldier = new Soldier(this, false, 0, 6);
        mActiveChess.add(lSoldier);
        lSoldier = new Soldier(this, false, 2, 6);
        mActiveChess.add(lSoldier);
        lSoldier = new Soldier(this, false, 4, 6);
        mActiveChess.add(lSoldier);
        lSoldier = new Soldier(this, false, 6, 6);
        mActiveChess.add(lSoldier);
        lSoldier = new Soldier(this, false, 8, 6);
        mActiveChess.add(lSoldier);
    }

    public int getWidth() {
        return mRect.width();
    }

    public int getHeight() {
        return mRect.height();
    }

    @Override
    public void draw(Canvas pCanvas, Paint pPaint) {
        pCanvas.drawBitmap(bg, null, mRect, pPaint);
    }

    public void resetAllChess() {
        for (int i = 0; i < mActiveChess.size(); i++) {
            mActiveChess.get(i).reset();
        }
    }

    public void setChessCoordinates(int x, int y, int id) {
        mChessCoordinates[x][y] = id;
    }

    public int getPixelX(int x) {
        return x * mBaseX + OFFSET_X + PADDING;
    }

    public int getPixelY(int y) {
        return y * mBaseY + OFFSET_Y + PADDING;
    }

    public Node getNode(int pixelX, int pixelY) {
        Rect lRect = new Rect();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                lRect.set(OFFSET_X + PADDING + i * mBaseX - Chess.CHESS_SIZE_HALF
                        , OFFSET_Y + PADDING + j * mBaseY - Chess.CHESS_SIZE_HALF
                        , OFFSET_X + PADDING + i * mBaseX + Chess.CHESS_SIZE_HALF
                        , OFFSET_Y + PADDING + j * mBaseY + Chess.CHESS_SIZE_HALF);
                if (lRect.contains(pixelX, pixelY)) {
                    return new Node(i, j);
                }
            }
        }
        return null;
    }
}
