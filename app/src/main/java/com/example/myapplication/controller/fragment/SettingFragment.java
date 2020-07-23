package com.example.myapplication.controller.fragment;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.myapplication.R;
import com.example.myapplication.model.FourInRowSituation;
import com.example.myapplication.model.GameSituation;
import com.example.myapplication.repository.Repository;

public class SettingFragment extends GameFragment {
    private EditText mPlayer1Name;
    private EditText mPlayer2Name;
    private RadioGroup mRadioGroupFourInRowGameMode;
    private Button mButtonResetScore;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        findViews(view);
        settingInit();
        gameBarListeners();
        listeners();
        updateInformation();
        return view;
    }

    private void listeners() {
        mPlayer1Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayer1.setName(s.toString());
                updateInformation();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mPlayer2Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayer2.setName(s.toString());
                updateInformation();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mRadioGroupFourInRowGameMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Repository repository = Repository.getRepository();
                switch (checkedId){
                    case R.id.radioButton_5X5:
                        repository.getSetting().setFourInRowSituation(FourInRowSituation.NORMAL);
                        break;
                    case R.id.radioButton_6X6:
                        repository.getSetting().setFourInRowSituation(FourInRowSituation.SIX_X_SIX);
                        break;
                    case R.id.radioButton7X7:
                        repository.getSetting().setFourInRowSituation(FourInRowSituation.SEVEN_X_SEVEN);
                        break;
                }
            }
        });
        mButtonResetScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Repository.getRepository().getPlayer1().resetScore();
                Repository.getRepository().getPlayer2().resetScore();
                updateInformation();
            }
        });
    }

    private void settingInit() {
        Repository repository = Repository.getRepository();
        mPlayer1Name.setText(repository.getPlayer1().getName());
        mPlayer2Name.setText(repository.getPlayer2().getName());
        switch (repository.getSetting().getFourInRowSituation()){
            case NORMAL:
                mRadioGroupFourInRowGameMode.check(R.id.radioButton_5X5);
                break;
            case SIX_X_SIX:
                mRadioGroupFourInRowGameMode.check(R.id.radioButton_6X6);
                break;
            case SEVEN_X_SEVEN:
                mRadioGroupFourInRowGameMode.check(R.id.radioButton7X7);
        }
    }

    protected void findViews(View view) {
        super.findViews(view);
        mPlayer1Name = view.findViewById(R.id.setting_player1_name);
        mPlayer2Name = view.findViewById(R.id.setting_player2_name);
        mButtonResetScore = view.findViewById(R.id.button_reset_score);
        mRadioGroupFourInRowGameMode = view.findViewById(R.id.raidio_group_four_in_row_game_mod);
    }

    @Override
    protected GameSituation checkGameSituation() {
        return null;
    }

    @Override
    protected void resetGame() {
    }
}