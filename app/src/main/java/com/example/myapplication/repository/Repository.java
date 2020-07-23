package com.example.myapplication.repository;

import com.example.myapplication.model.FourInRowColor;
import com.example.myapplication.model.Player;
import com.example.myapplication.model.Setting;
import com.example.myapplication.model.TicTacToeChar;

public class Repository {
    private static Repository sRepository = new Repository();
    private Player mPlayer1;
    private Player mPlayer2;
    private Setting mSetting;

    private Repository() {}

    public static Repository getRepository() {
        return sRepository;
    }

    public Setting getSetting() {
        if(mSetting == null){
            mSetting = new Setting();
        }
        return mSetting;
    }

    public Player getPlayer1() {
        if(mPlayer1 == null){
            mPlayer1 = new Player("Player1",true, TicTacToeChar.X, FourInRowColor.R);
        }
        return mPlayer1;
    }


    public Player getPlayer2() {
        if(mPlayer2 == null){
            mPlayer2 = new Player("Player2",false,TicTacToeChar.O,FourInRowColor.B);
        }
        return mPlayer2;
    }

}
