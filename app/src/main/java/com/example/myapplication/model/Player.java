package com.example.myapplication.model;


import java.io.Serializable;

public class Player {
    private String mName ;
    private int mScore =0;
    private boolean mTurn;
    private TicTacToeChar mTicTacToeChar;
    private FourInRowColor mFourInRowColor;

    public Player(String name, boolean turn, TicTacToeChar ticTacToeChar, FourInRowColor fourInRowColor) {
        mName = name;
        mTurn = turn;
        mTicTacToeChar = ticTacToeChar;
        mFourInRowColor = fourInRowColor;
    }


    public boolean isTurn() {
        return mTurn;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getScore() {
        return mScore;
    }

    public TicTacToeChar getTicTacToeChar() {
        return mTicTacToeChar;
    }

    public FourInRowColor getFourInRowColor() {
        return mFourInRowColor;
    }

    public void changeTurn() {
        if(this.mTurn){
            this.mTurn = false;
        }else
            this.mTurn = true;
    }

    public void playerWins(){
        mScore++;
    }

    public void resetScore(){
        mScore = 0;
    }

}
