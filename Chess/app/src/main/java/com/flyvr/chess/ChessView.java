package com.flyvr.chess;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.flyvr.chess.bean.Chess;
import com.flyvr.chess.bean.King;

/**
 * Created by android studio
 * author:chen
 */
public class ChessView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private final SurfaceHolder mHolder;
    private volatile boolean mIsStarting;

    private Thread mThread;

    private final Paint mPaint;

    public ChessView(Context context) {
        this(context, null);
    }

    public ChessView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private final ChessBoard mChessBoard;

    private final Player mPlayer;

    public ChessView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG);
        mChessBoard = new ChessBoard();
        mPlayer = new Player(mChessBoard);

        mHolder = getHolder();
        mHolder.setFormat(PixelFormat.TRANSLUCENT);
        mHolder.setKeepScreenOn(true);

        mHolder.addCallback(this);

    }

    protected void myDraw(Canvas pCanvas, Paint pPaint) {
        mChessBoard.draw(pCanvas, pPaint);
        for (int i = 0; i < mChessBoard.mActiveChess.size(); i++) {
            Chess lChess = mChessBoard.mActiveChess.get(i);
            if (lChess instanceof King && lChess.isBeEaton()) {
                isGameOver = true;
                continue;
            }
            lChess.draw(pCanvas, pPaint);
        }
    }

    private boolean isGameOver;

    public void regretChess() {
        mPlayer.regretChess();
        if (isGameOver) {
            isGameOver = false;
        }
    }

    public void reset() {
        isGameOver = false;
        mChessBoard.resetAllChess();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isGameOver && event.getAction() == MotionEvent.ACTION_DOWN) {
            mPlayer.touchChessBoard((int) event.getX(), (int) event.getY());
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Log.e("Player", "surfaceCreated");
        mThread = new Thread(this);
        mThread.setDaemon(true);
        mIsStarting = true;
        mThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        Log.e("Player", "surfaceChanged");
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.e("Player", "surfaceDestroyed");
        mIsStarting = false;
        mThread = null;
    }

    @Override
    public void run() {
        while (mIsStarting) {
            Canvas lCanvas = null;
            try {
                synchronized (mHolder) {
                    lCanvas = mHolder.lockCanvas();
                    if (lCanvas == null) break;
                    lCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                    lCanvas.drawColor(Color.WHITE);
                    myDraw(lCanvas, mPaint);
                }
                Thread.sleep(100);
            } catch (InterruptedException pE) {
                pE.printStackTrace();
            } finally {
                if (lCanvas != null) {
                    mHolder.unlockCanvasAndPost(lCanvas);
                }
            }
        }
    }
}
