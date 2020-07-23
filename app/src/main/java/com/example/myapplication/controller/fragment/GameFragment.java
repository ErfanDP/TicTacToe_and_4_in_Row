package com.example.myapplication.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.GameSituation;
import com.example.myapplication.model.Player;
import com.example.myapplication.repository.Repository;
import com.google.android.material.snackbar.Snackbar;

public abstract class GameFragment extends Fragment {

    protected Player mPlayer1 = Repository.getRepository().getPlayer1();
    protected Player mPlayer2 = Repository.getRepository().getPlayer2();
    private Button mButtonTicTacToe;
    private Button mButtonFourInRow;
    private Button mButtonSetting;
    private TextView mTextPlayer1Name;
    private TextView mTextPlayer2Name;
    private TextView mTextPlayer1Score;
    private TextView mTextPlayer2Score;
    private View mTurnPlayer1;
    private View mTurnPlayer2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void gameBarListeners() {
        mButtonTicTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.
                        beginTransaction()
                        .replace(R.id.fragment_container, new TicTacToeFragment())
                        .commit();

            }
        });
        mButtonFourInRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.
                        beginTransaction()
                        .replace(R.id.fragment_container, new FourInRowFragment())
                        .commit();

            }
        });
        mButtonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.
                        beginTransaction()
                        .replace(R.id.fragment_container, new SettingFragment())
                        .commit();

            }
        });
    }

    protected void findViews(View view) {
        mButtonFourInRow = view.findViewById(R.id.four_in_row_button);
        mButtonTicTacToe = view.findViewById(R.id.tic_tac_toe_button);
        mButtonSetting = view.findViewById(R.id.button_settting);
        mTextPlayer2Name = view.findViewById(R.id.text_player_two_name);
        mTextPlayer2Score = view.findViewById(R.id.text_player_two_score);
        mTextPlayer1Name = view.findViewById(R.id.text_player_one_name);
        mTextPlayer1Score = view.findViewById(R.id.text_player_one_score);
        mTurnPlayer1 = view.findViewById(R.id.player1_turn);
        mTurnPlayer2 = view.findViewById(R.id.player2_turn);
    }

    public void updateInformation() {
        mTextPlayer1Name.setText(mPlayer1.getName());
        mTextPlayer1Score.setText(String.valueOf(mPlayer1.getScore()));
        mTextPlayer2Name.setText(mPlayer2.getName());
        mTextPlayer2Score.setText(String.valueOf(mPlayer2.getScore()));
        checkTurn();
    }


    public void checkTurn() {
        if (mPlayer1.isTurn()) {
            mTurnPlayer2.setVisibility(View.INVISIBLE);
            mTurnPlayer1.setVisibility(View.VISIBLE);
        } else {
            mTurnPlayer1.setVisibility(View.INVISIBLE);
            mTurnPlayer2.setVisibility(View.VISIBLE);
        }
    }

    protected abstract GameSituation checkGameSituation();

    protected abstract void resetGame();

    public void winnerFunction(Player player) {
        player.playerWins();
        Snackbar.make(getActivity().findViewById(R.id.fragment_container),
                player.getName() + " Wins!!!", Snackbar.LENGTH_SHORT).show();
        updateInformation();
        resetGame();
    }

    public Player getTurn() {
        if (mPlayer1.isTurn()) {
            return mPlayer1;
        } else
            return mPlayer2;
    }

    public void gameStarter() {
        if (mPlayer1.getScore() > mPlayer2.getScore()) {
            makePlayer2Turn();
        } else {
            makePlayer1Turn();
        }
    }


    public void changeTurn() {
        mPlayer1.changeTurn();
        mPlayer2.changeTurn();
        if (mTurnPlayer1.getVisibility() == View.VISIBLE) {
            mTurnPlayer1.setVisibility(View.INVISIBLE);
            mTurnPlayer2.setVisibility(View.VISIBLE);
        } else {
            mTurnPlayer2.setVisibility(View.INVISIBLE);
            mTurnPlayer1.setVisibility(View.VISIBLE);
        }
        switch (checkGameSituation()) {
            case PLAYER_1_WINNER:
                winnerFunction(mPlayer1);
                break;
            case PLAYER_2_WINNER:
                winnerFunction(mPlayer2);
                break;
            case DRAW:
                resetGame();
        }

    }

    private void makePlayer1Turn() {
        if (!mPlayer1.isTurn()) {
            changeTurn();
        }

    }

    private void makePlayer2Turn() {
        if (!mPlayer2.isTurn()) {
            changeTurn();
        }
    }

    protected void gameButtonReset(Button button) {
        button.setText("");
        button.setEnabled(true);
        button.setBackgroundColor(getResources().getColor(R.color.baseBackgroundButton));
    }

}