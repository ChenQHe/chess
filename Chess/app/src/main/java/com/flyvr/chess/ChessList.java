package com.flyvr.chess;

import android.util.SparseArray;

import com.flyvr.chess.bean.Chess;

/**
 * Created by android studio
 * author:chen
 */
public class ChessList {
    private final SparseArray<Chess> mSparseArray = new SparseArray<>();

    public Chess getValue(int key) {
        return mSparseArray.get(key);
    }

    public void add(Chess pChess) {
        mSparseArray.put(pChess.id, pChess);
    }

    public int size() {
        return mSparseArray.size();
    }

    public Chess get(int index) {
        return mSparseArray.valueAt(index);
    }
}
