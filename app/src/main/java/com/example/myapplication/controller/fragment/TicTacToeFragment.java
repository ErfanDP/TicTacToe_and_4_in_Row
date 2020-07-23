package com.example.myapplication.controller.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.model.GameSituation;
import com.example.myapplication.model.TicTacToeChar;

public class TicTacToeFragment extends GameFragment {
    private static final String EXTRA_BUTTONS = "com.example.myapplication.extra_buttons";

    private String[][] mButtons;

    private Button mButton_0_0;
    private Button mButton_0_1;
    private Button mButton_0_2;
    private Button mButton_1_0;
    private Button mButton_1_1;
    private Button mButton_1_2;
    private Button mButton_2_0;
    private Button mButton_2_1;
    private Button mButton_2_2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonsInit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tic_tac_toe, container, false);
        findViews(view);
        if (savedInstanceState != null) {
            String[] allButtons = savedInstanceState.getStringArray(EXTRA_BUTTONS);
            allViewButtonsInit(allButtons);
        }else{
            gameStarter();
        }
        updateInformation();
        gameBarListeners();
        listeners();
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String[] allButtons = new String[9];
        for (int i = 0, k = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++, k++) {
                allButtons[k] = mButtons[i][j];
            }
        }
        outState.putStringArray(EXTRA_BUTTONS, allButtons);
    }

    private void allViewButtonsInit(String[] allButtons) {
        buttonsInit();
        for (int i = 0, k = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++, k++) {
                mButtons[i][j] = allButtons[k];
            }
        }
        viewButtonInit(mButton_0_0, 0, 0);
        viewButtonInit(mButton_0_1, 0, 1);
        viewButtonInit(mButton_0_2, 0, 2);
        viewButtonInit(mButton_1_0, 1, 0);
        viewButtonInit(mButton_1_1, 1, 1);
        viewButtonInit(mButton_1_2, 1, 2);
        viewButtonInit(mButton_2_0, 2, 0);
        viewButtonInit(mButton_2_1, 2, 1);
        viewButtonInit(mButton_2_2, 2, 2);
    }

    private void viewButtonInit(Button button, int i, int j) {
        switch (mButtons[i][j]) {
            case "X":
                button.setText("X");
                button.setEnabled(false);
                break;
            case "O":
                button.setText("O");
                button.setEnabled(false);
                break;
        }
    }

    private void buttonsInit() {
        mButtons = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mButtons[i][j] = "";
            }
        }
    }

    private void listeners() {
        mButton_0_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGameButtonClick(mButton_0_0, 0, 0);
            }
        });

        mButton_0_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGameButtonClick(mButton_0_1, 0, 1);
            }
        });

        mButton_0_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGameButtonClick(mButton_0_2, 0, 2);
            }
        });

        mButton_1_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGameButtonClick(mButton_1_0, 1, 0);
            }
        });

        mButton_1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGameButtonClick(mButton_1_1, 1, 1);
            }
        });

        mButton_1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGameButtonClick(mButton_1_2, 1, 2);
            }
        });

        mButton_2_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGameButtonClick(mButton_2_0, 2, 0);
            }
        });

        mButton_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGameButtonClick(mButton_2_1, 2, 1);
            }
        });

        mButton_2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGameButtonClick(mButton_2_2, 2, 2);
            }
        });


    }

    private void onGameButtonClick(Button button, int i, int j) {
        button.setText(getTurn().getTicTacToeChar().toString());
        mButtons[i][j] = getTurn().getTicTacToeChar().toString();
        button.setEnabled(false);
        changeTurn();
    }


    @Override
    protected void findViews(View view) {
        super.findViews(view);
        mButton_0_0 = view.findViewById(R.id.button_tic_tac_toe_0_0);
        mButton_0_1 = view.findViewById(R.id.button_tic_tac_toe_0_1);
        mButton_0_2 = view.findViewById(R.id.button_tic_tac_toe_0_2);
        mButton_1_0 = view.findViewById(R.id.button_tic_tac_toe_1_0);
        mButton_1_1 = view.findViewById(R.id.button_tic_tac_toe_1_1);
        mButton_1_2 = view.findViewById(R.id.button_tic_tac_toe_1_2);
        mButton_2_0 = view.findViewById(R.id.button_tic_tac_toe_2_0);
        mButton_2_1 = view.findViewById(R.id.button_tic_tac_toe_2_1);
        mButton_2_2 = view.findViewById(R.id.button_tic_tac_toe_2_2);
    }

    @Override
    protected GameSituation checkGameSituation() {
        for (int a = 0; a < 8; a++) {
            String line = null;
            switch (a) {
                case 0:
                    line = mButtons[0][0] + mButtons[0][1] + mButtons[0][2];
                    break;
                case 1:
                    line = mButtons[1][0] + mButtons[1][1] + mButtons[1][2];
                    break;
                case 2:
                    line = mButtons[2][0] + mButtons[2][1] + mButtons[2][2];
                    break;
                case 3:
                    line = mButtons[0][0] + mButtons[1][0] + mButtons[2][0];
                    break;
                case 4:
                    line = mButtons[0][1] + mButtons[1][1] + mButtons[2][1];
                    break;
                case 5:
                    line = mButtons[0][2] + mButtons[1][2] + mButtons[2][2];
                    break;
                case 6:
                    line = mButtons[0][0] + mButtons[1][1] + mButtons[2][2];
                    break;
                case 7:
                    line = mButtons[0][2] + mButtons[1][1] + mButtons[2][0];
                    break;
            }
            if (line.equals("XXX")) {
                if (findPlayer(TicTacToeChar.X) == 1) {
                    return GameSituation.PLAYER_1_WINNER;
                } else
                    return GameSituation.PLAYER_2_WINNER;
            } else if (line.equals("OOO")) {
                if (findPlayer(TicTacToeChar.O) == 1) {
                    return GameSituation.PLAYER_1_WINNER;
                } else
                    return GameSituation.PLAYER_2_WINNER;
            }
        }
        return gameNotDoneOrDraw();
    }

    @Override
    protected void resetGame() {
        buttonsInit();
        gameButtonReset(mButton_0_0);
        gameButtonReset(mButton_0_1);
        gameButtonReset(mButton_0_2);
        gameButtonReset(mButton_1_0);
        gameButtonReset(mButton_1_1);
        gameButtonReset(mButton_1_2);
        gameButtonReset(mButton_2_0);
        gameButtonReset(mButton_2_1);
        gameButtonReset(mButton_2_2);
        changeTurn();
    }


    private GameSituation gameNotDoneOrDraw() {
        String allHomes = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                allHomes += mButtons[i][j];
            }
        }
        if (allHomes.length() == 9) {
            return GameSituation.DRAW;
        } else
            return GameSituation.NOT_DONE;
    }

    private int findPlayer(TicTacToeChar ticTacToeChar) {
        if (mPlayer1.getTicTacToeChar() == ticTacToeChar) {
            return 1;
        } else
            return 2;
    }
}