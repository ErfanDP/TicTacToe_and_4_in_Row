package com.example.myapplication.model;

public class Setting {
    private FourInRowSituation mFourInRowSituation = FourInRowSituation.NORMAL;

    public FourInRowSituation getFourInRowSituation() {
        return mFourInRowSituation;
    }

    public void setFourInRowSituation(FourInRowSituation fourInRowSituation) {
        mFourInRowSituation = fourInRowSituation;
    }
}
