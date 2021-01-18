package com.flyvr.chess.bean;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.flyvr.chess.ChessBoard;
import com.flyvr.chess.Drawer;
import com.flyvr.chess.Player;

import java.util.List;

/**
 * Created by android studio
 * author:chen
 */
public abstract class Chess implements Drawer {
    public static final int CHESS_SIZE_HALF = 45;

    public final int id;
    /**
     * 相对于棋盘坐标位置 x
     */
    protected int x;
    /**
     * 相对于棋盘坐标位置 y
     */
    protected int y;

    /**
     * 棋子的背景图
     */
    protected Bitmap bg;
    /**
     * 棋子是否被吃掉了
     * ps：棋子被吃掉，本来应该直接从集合中去掉的 ，但是我的绘制是在线程中循环遍历棋子的集合，
     * 在遍历的集合中去掉某个元素容易出现异常。可以copy这个集合，去掉这个元素，然后遍历新集合，
     * 这可能需要更多的内存与cpu。加上这个标记，就不需要从集合中去掉了，被吃掉的棋子可以直接不显示
     */
    private boolean isBeEaton;

    /**
     * 是否是黑棋
     */
    private final boolean campBlack;

    /**
     * 棋子显示的区域 为了做棋子与棋子 或者 棋子与手指是否碰撞
     */
    private final Rect mRect = new Rect();

    /**
     * 保存 原始棋子的位置像素位置x 为了方便重新开始，棋子复原
     */
    protected final int originX;
    /**
     * 保存 原始棋子的位置像素位置y 为了方便重新开始，棋子复原
     */
    protected final int originY;

    /**
     * 保持上一次的位置 以便实现 悔棋功能
     */
    protected int lastX = -1;
    protected int lastY = -1;

    protected ChessBoard mContext;

    public Chess(ChessBoard context, boolean campBlack, int x, int y) {
        mContext = context;
        this.id = x * 10 + y;
        this.campBlack = campBlack;
        originX = x;
        originY = y;
        setLocation(originX, originY);
    }

    public boolean isCampBlack() {
        return campBlack;
    }

    /**
     * 棋子是否被玩家点击
     *
     * @param pPlayer 玩家
     * @return true 被点击 false 没有被点击
     */
    public boolean isClickByPlayer(Player pPlayer) {
        return !isBeEaton && mRect.contains(pPlayer.touchX, pPlayer.touchY);
    }


    /**
     * @return 获取所有棋子能走动的点  由子类实现
     */
    protected abstract List<Node> getCanModeNodes();

    /**
     * 子类去实现
     * 判断是否 棋子是否可以移动到 坐标（x,y）处
     *
     * @param x 这里是 坐标 x
     * @param y 这里是 坐标 y
     * @return true 能 ，false 不能
     */
    protected abstract boolean canMove(int x, int y);

    public boolean moveByPlayer(Player pPlayer) {
        Node lNode = mContext.getNode(pPlayer.touchX, pPlayer.touchY);
        if (lNode == null) return false;
        if (!canMove(lNode.getX(), lNode.getY())) return false;
        setLocation(lNode.getX(), lNode.getY());
        return true;
    }

    /**
     * 设置棋子在棋盘的位置
     *
     * @param x 棋盘坐标 x
     * @param y 棋盘坐标 y
     */
    public void setLocation(int x, int y) {

        lastX = this.x;
        lastY = this.y;

        this.x = x;
        this.y = y;

        int pixelX = mContext.getPixelX(x);
        int pixelY = mContext.getPixelY(y);

        //棋子在棋盘的实现 像素区域
        mRect.set(pixelX - CHESS_SIZE_HALF
                , pixelY - CHESS_SIZE_HALF
                , pixelX + CHESS_SIZE_HALF
                , pixelY + CHESS_SIZE_HALF);

        mContext.setChessCoordinates(lastX, lastY, -1);
        mContext.setChessCoordinates(x, y, id);
    }

    public void back() {
        if (lastX == -1 && lastY == -1) {
            return;
        }
        setLocation(lastX, lastY);
    }

    private boolean isTouched;

    public void setTouched(boolean pTouched) {
        isTouched = pTouched;
    }

    public void setBeEaton(boolean pBeEaton) {
        isBeEaton = pBeEaton;
        if (!isBeEaton) {
            mContext.setChessCoordinates(x, y, id);
        }
    }

    public boolean isBeEaton() {
        return isBeEaton;
    }

    @Override
    public void draw(Canvas pCanvas, Paint pPaint) {
        if (isBeEaton) return;
        if (isTouched) {
            pPaint.setColor(Color.RED);
            pCanvas.drawCircle(mRect.centerX(), mRect.centerY(), CHESS_SIZE_HALF + 4, pPaint);
            if (getCanModeNodes() != null) {
                for (Node node : getCanModeNodes()) {
                    pCanvas.drawCircle(node.getX(), node.getY(), 4, pPaint);
                }
            }
        }
        pCanvas.drawBitmap(bg, null, mRect, pPaint);
    }

    public void reset() {
        isBeEaton = false;
        isTouched = false;
        lastX = -1;
        lastY = -1;
        setLocation(originX, originY);
    }

}
