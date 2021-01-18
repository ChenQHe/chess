package com.flyvr.chess;

import com.flyvr.chess.bean.Chess;

/**
 * Created by android studio
 * author:chen
 */
public class Player {

    public int touchX;
    public int touchY;

    public Chess currentTouchChess;

    private Chess lastTouchChess;

    private Chess lastEatonChess;

    private final ChessBoard mChessBoard;

    public Player(ChessBoard pChessBoard) {
        mChessBoard = pChessBoard;
    }

    public void touchChessBoard(int pTouchX, int pTouchY) {
        touchX = pTouchX;
        touchY = pTouchY;
        Chess lChess = touchChessFromChessPool();
        if (lChess == null) {
            if (currentTouchChess != null) {
                if (currentTouchChess.moveByPlayer(this)) {
                    currentTouchChess.setTouched(false);
                    lastTouchChess = currentTouchChess;
                    currentTouchChess = null;
                    SoundMgr.playClick();
                }
            }
        } else {
            if (currentTouchChess == null) {
                currentTouchChess = lChess;
                currentTouchChess.setTouched(true);
                SoundMgr.playClick();
            } else {
                if (lChess == currentTouchChess) {
                    currentTouchChess.setTouched(false);
                    currentTouchChess = null;
                    SoundMgr.playClick();
                } else {
                    if (lChess.isCampBlack() == currentTouchChess.isCampBlack()) {
                        currentTouchChess.setTouched(false);
                        currentTouchChess = lChess;
                        currentTouchChess.setTouched(true);
                        SoundMgr.playClick();
                    } else {
                        if (currentTouchChess.moveByPlayer(this)) {
                            currentTouchChess.setTouched(false);
                            lastTouchChess = currentTouchChess;
                            currentTouchChess = null;
                            lChess.setBeEaton(true);
                            lastEatonChess = lChess;
                            SoundMgr.playHit();
                        }
                    }
                }
            }
        }
    }

    public void regretChess() {
        if (lastTouchChess != null) {
            lastTouchChess.back();
            lastTouchChess = null;
            if (lastEatonChess != null) {
                lastEatonChess.setBeEaton(false);
                lastEatonChess = null;
            }
        }
    }

    private Chess touchChessFromChessPool() {
        for (int i = 0; i < mChessBoard.mActiveChess.size(); i++) {
            Chess chess = mChessBoard.mActiveChess.get(i);
            if (chess.isClickByPlayer(this)) {
                return chess;
            }
        }
        return null;
    }
}
