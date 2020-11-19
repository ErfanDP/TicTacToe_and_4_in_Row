package com.example.myapplication.controller.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.myapplication.R;
import com.example.myapplication.model.FourInRowColor;
import com.example.myapplication.model.GameSituation;
import com.example.myapplication.model.Player;
import com.example.myapplication.repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class FourInRowFragment extends GameFragment {
    private final static String EXTRA_BOARD = "com.example.myapplication.extra_board";

    private String[][] mBoard;
    private int[] mLastMove = new int[2];
    private List<Button> mListVertical0 = new ArrayList<>();
    private List<Button> mListVertical1 = new ArrayList<>();
    private List<Button> mListVertical2 = new ArrayList<>();
    private List<Button> mListVertical3 = new ArrayList<>();
    private List<Button> mListVertical4 = new ArrayList<>();
    private List<Button> mListVertical5;
    private List<Button> mListVertical6;

    private View mSixXSixHorizontal;
    private View mSixXSixVertical;
    private View mSevenXSevenHorizontal;
    private View mSevenXSevenVertical;

    private Button mButton00;
    private Button mButton01;
    private Button mButton02;
    private Button mButton03;
    private Button mButton04;
    private Button mButton05;
    private Button mButton06;
    private Button mButton10;
    private Button mButton11;
    private Button mButton12;
    private Button mButton13;
    private Button mButton14;
    private Button mButton15;
    private Button mButton16;
    private Button mButton20;
    private Button mButton21;
    private Button mButton22;
    private Button mButton23;
    private Button mButton24;
    private Button mButton25;
    private Button mButton26;
    private Button mButton30;
    private Button mButton31;
    private Button mButton32;
    private Button mButton33;
    private Button mButton34;
    private Button mButton35;
    private Button mButton36;
    private Button mButton40;
    private Button mButton41;
    private Button mButton42;
    private Button mButton43;
    private Button mButton44;
    private Button mButton45;
    private Button mButton46;
    private Button mButton50;
    private Button mButton51;
    private Button mButton52;
    private Button mButton53;
    private Button mButton54;
    private Button mButton55;
    private Button mButton56;
    private Button mButton60;
    private Button mButton61;
    private Button mButton62;
    private Button mButton63;
    private Button mButton64;
    private Button mButton65;
    private Button mButton66;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            boardInit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_four_in_row, container, false);
        findViews(view);
        viewBoardInit();
        listsViewBinding();
        gameBarListeners();
        updateInformation();
        if (savedInstanceState != null) {
            String[] allButtons = savedInstanceState.getStringArray(EXTRA_BOARD);
            buttonsAndBoardInit(allButtons);
        }else{
            gameStarter();
        }
        buttonListeners();

        return view;
    }

    private void buttonsAndBoardInit(String[] allButtons) {
        for (int i = 0, k = 0; i < mBoard.length; i++) {
            for (int j = 0; j < mBoard.length; j++, k++) {
                mBoard[i][j] = allButtons[k];
            }
            switch (Repository.getRepository().getSetting().getFourInRowSituation()) {
                case SEVEN_X_SEVEN:
                    viewButtonInit(mButton06, 0, 6);
                    viewButtonInit(mButton16, 1, 6);
                    viewButtonInit(mButton26, 2, 6);
                    viewButtonInit(mButton36, 3, 6);
                    viewButtonInit(mButton46, 4, 6);
                    viewButtonInit(mButton56, 5, 6);
                    viewButtonInit(mButton60, 6, 0);
                    viewButtonInit(mButton61, 6, 1);
                    viewButtonInit(mButton62, 6, 2);
                    viewButtonInit(mButton63, 6, 3);
                    viewButtonInit(mButton64, 6, 4);
                    viewButtonInit(mButton65, 6, 5);
                    viewButtonInit(mButton66, 6, 6);
                case SIX_X_SIX:
                    viewButtonInit(mButton05,0,5);
                    viewButtonInit(mButton15,1,5);
                    viewButtonInit(mButton25,2,5);
                    viewButtonInit(mButton35,3,5);
                    viewButtonInit(mButton45,4,5);
                    viewButtonInit(mButton50,5,0);
                    viewButtonInit(mButton51,5,1);
                    viewButtonInit(mButton52,5,2);
                    viewButtonInit(mButton53,5,3);
                    viewButtonInit(mButton54,5,4);
                    viewButtonInit(mButton55,5,5);
                case NORMAL:
                    viewButtonInit(mButton00,0,0);
                    viewButtonInit(mButton01,0,1);
                    viewButtonInit(mButton02,0,2);
                    viewButtonInit(mButton03,0,3);
                    viewButtonInit(mButton04,0,4);
                    viewButtonInit(mButton10,1,0);
                    viewButtonInit(mButton11,1,1);
                    viewButtonInit(mButton12,1,2);
                    viewButtonInit(mButton13,1,3);
                    viewButtonInit(mButton14,1,4);
                    viewButtonInit(mButton20,2,0);
                    viewButtonInit(mButton21,2,1);
                    viewButtonInit(mButton22,2,2);
                    viewButtonInit(mButton23,2,3);
                    viewButtonInit(mButton24,2,4);
                    viewButtonInit(mButton30,3,0);
                    viewButtonInit(mButton31,3,1);
                    viewButtonInit(mButton32,3,2);
                    viewButtonInit(mButton33,3,3);
                    viewButtonInit(mButton34,3,4);
                    viewButtonInit(mButton40,4,0);
                    viewButtonInit(mButton41,4,1);
                    viewButtonInit(mButton42,4,2);
                    viewButtonInit(mButton43,4,3);
                    viewButtonInit(mButton44,4,4);

            }
        }
    }

    private void viewButtonInit(Button button, int i, int j) {
        switch (mBoard[i][j]) {
            case "R":
                button.setBackgroundColor(getResources().getColor(R.color.player1));
                button.setEnabled(false);
                break;
            case "B":
                button.setBackgroundColor(getResources().getColor(R.color.player2));
                button.setEnabled(false);
                break;
            case "N":
                button.setBackgroundColor(getResources().getColor(R.color.baseBackgroundButton));
                button.setEnabled(true);
                break;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String[] allButtons = new String[mBoard.length*mBoard.length];
        for (int i = 0, k = 0; i < mBoard.length; i++) {
            for (int j = 0; j < mBoard.length; j++, k++) {
                allButtons[k] = mBoard[i][j];
            }
        }
        outState.putStringArray(EXTRA_BOARD, allButtons);
    }

    private void buttonListeners() {
        mButton00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical0, mButton00, 0, 0);
            }
        });
        mButton10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical0, mButton10, 1, 0);
            }
        });
        mButton20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical0, mButton20, 2, 0);
            }
        });
        mButton30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical0, mButton30, 3, 0);
            }
        });
        mButton40.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical0, mButton40, 4, 0);
            }
        });
        mButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical1, mButton01, 0, 1);
            }
        });
        mButton11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical1, mButton11, 1, 1);
            }
        });
        mButton21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical1, mButton21, 2, 1);
            }
        });
        mButton31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical1, mButton31, 3, 1);
            }
        });
        mButton41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical1, mButton41, 4, 1);
            }
        });
        mButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical2, mButton02, 0, 2);
            }
        });
        mButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical2, mButton12, 1, 2);
            }
        });
        mButton22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical2, mButton22, 2, 2);
            }
        });
        mButton32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical2, mButton32, 3, 2);
            }
        });
        mButton42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical2, mButton42, 4, 2);
            }
        });
        mButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical3, mButton03, 0, 3);
            }
        });
        mButton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical3, mButton13, 1, 3);
            }
        });
        mButton23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical3, mButton23, 2, 3);
            }
        });
        mButton33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical3, mButton33, 3, 3);
            }
        });
        mButton43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical3, mButton43, 4, 3);
            }
        });
        mButton04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical4, mButton04, 0, 4);
            }
        });
        mButton14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical4, mButton14, 1, 4);
            }
        });
        mButton24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical4, mButton24, 2, 4);
            }
        });
        mButton34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical4, mButton34, 3, 4);
            }
        });
        mButton44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick(mListVertical4, mButton44, 4, 4);
            }
        });
        switch (Repository.getRepository().getSetting().getFourInRowSituation()) {
            case SEVEN_X_SEVEN:
                mButton60.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical0, mButton60, 6, 0);
                    }
                });
                mButton61.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical1, mButton61, 6, 1);
                    }
                });
                mButton62.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical2, mButton62, 6, 2);
                    }
                });
                mButton63.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical3, mButton63, 6, 3);
                    }
                });
                mButton64.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical4, mButton64, 6, 4);
                    }
                });
                mButton65.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical5, mButton65, 6, 5);
                    }
                });
                mButton66.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical6, mButton66, 6, 6);
                    }
                });
                mButton06.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical6, mButton06, 0, 6);
                    }
                });
                mButton16.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical6, mButton16, 1, 6);
                    }
                });
                mButton26.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical6, mButton26, 2, 6);
                    }
                });
                mButton36.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical6, mButton36, 3, 6);
                    }
                });
                mButton46.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical6, mButton46, 4, 6);
                    }
                });
                mButton56.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical6, mButton56, 5, 6);
                    }
                });
                mButton66.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical6, mButton66, 6, 6);
                    }
                });

            case SIX_X_SIX:
                mButton50.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical0, mButton50, 5, 0);
                    }
                });
                mButton51.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical1, mButton51, 5, 1);
                    }
                });
                mButton52.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical2, mButton52, 5, 2);
                    }
                });
                mButton53.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical3, mButton53, 5, 3);
                    }
                });
                mButton54.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical4, mButton54, 5, 4);
                    }
                });
                mButton05.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical5, mButton05, 0, 5);
                    }
                });
                mButton15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical5, mButton15, 1, 5);
                    }
                });
                mButton25.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical5, mButton25, 2, 5);
                    }
                });
                mButton35.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical5, mButton35, 3, 5);
                    }
                });
                mButton45.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical5, mButton45, 4, 5);
                    }
                });
                mButton55.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onButtonClick(mListVertical5, mButton55, 5, 5);
                    }
                });

        }
    }

    private void onButtonClick(List<Button> mListVertical, Button button, int i, int j) {
        try {
            Button underButton = mListVertical.get(mListVertical.indexOf(button) + 1);
            if (backgroundChecker(underButton) == FourInRowColor.W) {
                onButtonClick(mListVertical, underButton, i + 1, j);
            } else {
                onConfirmedClickButton(button, i, j);
            }
        } catch (IndexOutOfBoundsException exc) {
            onConfirmedClickButton(button, i, j);
        }
    }

    private void onConfirmedClickButton(Button button, int i, int j) {
        switch (getTurn().getFourInRowColor()) {
            case B:
                button.setBackgroundColor(getResources().getColor(R.color.player2));
                mBoard[i][j] = "B";
                break;
            case R:
                button.setBackgroundColor(getResources().getColor(R.color.player1));
                mBoard[i][j] = "R";
                break;
        }
        mLastMove[0] = i;
        mLastMove[1] = j;
        button.setEnabled(false);
        changeTurn();
    }

    private FourInRowColor backgroundChecker(Button button) {
        int color = ((ColorDrawable) button.getBackground()).getColor();
        if (color == getResources().getColor(R.color.baseBackgroundButton)) {
            return FourInRowColor.W;
        } else if (color == getResources().getColor(R.color.player1)) {
            return FourInRowColor.R;
        } else if (color == getResources().getColor(R.color.player2)) {
            return FourInRowColor.B;
        }
        return null;
    }

    private void listsViewBinding() {
        mListVertical0.add(mButton00);
        mListVertical0.add(mButton10);
        mListVertical0.add(mButton20);
        mListVertical0.add(mButton30);
        mListVertical0.add(mButton40);
        mListVertical1.add(mButton01);
        mListVertical1.add(mButton11);
        mListVertical1.add(mButton21);
        mListVertical1.add(mButton31);
        mListVertical1.add(mButton41);
        mListVertical2.add(mButton02);
        mListVertical2.add(mButton12);
        mListVertical2.add(mButton22);
        mListVertical2.add(mButton32);
        mListVertical2.add(mButton42);
        mListVertical3.add(mButton03);
        mListVertical3.add(mButton13);
        mListVertical3.add(mButton23);
        mListVertical3.add(mButton33);
        mListVertical3.add(mButton43);
        mListVertical4.add(mButton04);
        mListVertical4.add(mButton14);
        mListVertical4.add(mButton24);
        mListVertical4.add(mButton34);
        mListVertical4.add(mButton44);
        switch (Repository.getRepository().getSetting().getFourInRowSituation()) {
            case SIX_X_SIX:
                mListVertical0.add(mButton50);
                mListVertical1.add(mButton51);
                mListVertical2.add(mButton52);
                mListVertical3.add(mButton53);
                mListVertical4.add(mButton54);
                mListVertical5.add(mButton05);
                mListVertical5.add(mButton15);
                mListVertical5.add(mButton25);
                mListVertical5.add(mButton35);
                mListVertical5.add(mButton45);
                mListVertical5.add(mButton55);
                break;
            case SEVEN_X_SEVEN:
                mListVertical0.add(mButton50);
                mListVertical0.add(mButton60);
                mListVertical1.add(mButton51);
                mListVertical1.add(mButton61);
                mListVertical2.add(mButton52);
                mListVertical2.add(mButton62);
                mListVertical3.add(mButton53);
                mListVertical3.add(mButton63);
                mListVertical4.add(mButton54);
                mListVertical4.add(mButton64);
                mListVertical5.add(mButton05);
                mListVertical5.add(mButton15);
                mListVertical5.add(mButton25);
                mListVertical5.add(mButton35);
                mListVertical5.add(mButton45);
                mListVertical5.add(mButton55);
                mListVertical5.add(mButton65);
                mListVertical6.add(mButton06);
                mListVertical6.add(mButton16);
                mListVertical6.add(mButton26);
                mListVertical6.add(mButton36);
                mListVertical6.add(mButton46);
                mListVertical6.add(mButton56);
                mListVertical6.add(mButton66);
                break;
        }
    }

    private void boardInit() {
        switch (Repository.getRepository().getSetting().getFourInRowSituation()) {
            case NORMAL:
                mBoard = new String[5][5];
                break;
            case SIX_X_SIX:
                mBoard = new String[6][6];
                mListVertical5 = new ArrayList<>();
                break;
            case SEVEN_X_SEVEN:
                mBoard = new String[7][7];
                mListVertical5 = new ArrayList<>();
                mListVertical6 = new ArrayList<>();
                break;
        }

        for (int i = 0; i < mBoard.length; i++) {
            for (int j = 0; j < mBoard.length; j++) {
                mBoard[i][j] = "N";
            }
        }
    }

    private void viewBoardInit() {
        switch (Repository.getRepository().getSetting().getFourInRowSituation()) {
            case NORMAL:
                mSixXSixVertical.setVisibility(View.GONE);
                mSixXSixHorizontal.setVisibility(View.GONE);
            case SIX_X_SIX:
                mSevenXSevenVertical.setVisibility(View.GONE);
                mSevenXSevenHorizontal.setVisibility(View.GONE);

        }
    }

    protected void findViews(View view) {
        super.findViews(view);
        mSixXSixHorizontal = view.findViewById(R.id.horizontal_6X6_layout);
        mSixXSixVertical = view.findViewById(R.id.vertical_6X6_layout);
        mSevenXSevenHorizontal = view.findViewById(R.id.horizontal_7X7_layout);
        mSevenXSevenVertical = view.findViewById(R.id.vertical_7X7_layout);
        switch (Repository.getRepository().getSetting().getFourInRowSituation()) {
            case SEVEN_X_SEVEN:
                mButton06 = view.findViewById(R.id.button_0_6);
                mButton16 = view.findViewById(R.id.button_1_6);
                mButton26 = view.findViewById(R.id.button_2_6);
                mButton36 = view.findViewById(R.id.button_3_6);
                mButton46 = view.findViewById(R.id.button_4_6);
                mButton56 = view.findViewById(R.id.button_5_6);
                mButton60 = view.findViewById(R.id.button_6_0);
                mButton61 = view.findViewById(R.id.button_6_1);
                mButton62 = view.findViewById(R.id.button_6_2);
                mButton63 = view.findViewById(R.id.button_6_3);
                mButton64 = view.findViewById(R.id.button_6_4);
                mButton65 = view.findViewById(R.id.button_6_5);
                mButton66 = view.findViewById(R.id.button_6_6);
            case SIX_X_SIX:
                mButton05 = view.findViewById(R.id.button_0_5);
                mButton15 = view.findViewById(R.id.button_1_5);
                mButton25 = view.findViewById(R.id.button_2_5);
                mButton35 = view.findViewById(R.id.button_3_5);
                mButton45 = view.findViewById(R.id.button_4_5);
                mButton50 = view.findViewById(R.id.button_5_0);
                mButton51 = view.findViewById(R.id.button_5_1);
                mButton52 = view.findViewById(R.id.button_5_2);
                mButton53 = view.findViewById(R.id.button_5_3);
                mButton54 = view.findViewById(R.id.button_5_4);
                mButton55 = view.findViewById(R.id.button_5_5);
            case NORMAL:
                mButton00 = view.findViewById(R.id.button_0_0);
                mButton01 = view.findViewById(R.id.button_0_1);
                mButton02 = view.findViewById(R.id.button_0_2);
                mButton03 = view.findViewById(R.id.button_0_3);
                mButton04 = view.findViewById(R.id.button_0_4);
                mButton10 = view.findViewById(R.id.button_1_0);
                mButton11 = view.findViewById(R.id.button_1_1);
                mButton12 = view.findViewById(R.id.button_1_2);
                mButton13 = view.findViewById(R.id.button_1_3);
                mButton14 = view.findViewById(R.id.button_1_4);
                mButton20 = view.findViewById(R.id.button_2_0);
                mButton21 = view.findViewById(R.id.button_2_1);
                mButton22 = view.findViewById(R.id.button_2_2);
                mButton23 = view.findViewById(R.id.button_2_3);
                mButton24 = view.findViewById(R.id.button_2_4);
                mButton30 = view.findViewById(R.id.button_3_0);
                mButton31 = view.findViewById(R.id.button_3_1);
                mButton32 = view.findViewById(R.id.button_3_2);
                mButton33 = view.findViewById(R.id.button_3_3);
                mButton34 = view.findViewById(R.id.button_3_4);
                mButton40 = view.findViewById(R.id.button_4_0);
                mButton41 = view.findViewById(R.id.button_4_1);
                mButton42 = view.findViewById(R.id.button_4_2);
                mButton43 = view.findViewById(R.id.button_4_3);
                mButton44 = view.findViewById(R.id.button_4_4);
        }
    }

    @Override
    protected GameSituation checkGameSituation() {
        for (int i = 0; i < 4; i++) {
            String str = "";
            switch (i) {
                case 0://horizontal check
                    for (int j = 0; j < mBoard.length; j++) {
                        str += mBoard[mLastMove[0]][j];
                    }
                    break;
                case 1://vertical check
                    for (int j = 0; j < mBoard.length; j++) {
                        str += mBoard[j][mLastMove[1]];
                    }
                    break;
                case 2://diagonal  check
                {
                    for (int k = mLastMove[0], j = mLastMove[1];
                         j >=0  && k < mBoard.length ; k++,j--) {
                        str =mBoard[k][j]+str;
                    }
                    for (int k = mLastMove[0]-1 , j = mLastMove[1]+1 ;
                         j < mBoard.length && k >=0 ; k--,j++) {
                        str +=mBoard[k][j];
                    }
                    break;
                }
                case 3:// rev diagonal check
                {
                    for (int k = mLastMove[0], j = mLastMove[1];
                         j >=0  && k >=0 ; k--,j--) {
                        str +=mBoard[k][j];
                    }
                    for (int k = mLastMove[0]+1 , j = mLastMove[1]+1 ;
                         j < mBoard.length && k <mBoard.length ; k++,j++) {
                        str =mBoard[k][j]+str;
                    }
                    break;
                }
            }
            if (str.contains("RRRR")) {
                return GameSituation.PLAYER_1_WINNER;
            } else if (str.contains("BBBB")) {
                return GameSituation.PLAYER_2_WINNER;
            } else if(boardFull()){
                return GameSituation.DRAW;
            }
        }
        return GameSituation.NOT_DONE;
    }
    public boolean boardFull(){
        for (int i = 0; i < mBoard.length; i++) {
            for (int j = 0; j < mBoard.length; j++) {
                if(mBoard[i][j].equals("N")){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void resetGame() {
        switch (Repository.getRepository().getSetting().getFourInRowSituation()) {
            case SEVEN_X_SEVEN:
                gameButtonReset(mButton06);
                gameButtonReset(mButton16);
                gameButtonReset(mButton26);
                gameButtonReset(mButton36);
                gameButtonReset(mButton46);
                gameButtonReset(mButton56);
                gameButtonReset(mButton60);
                gameButtonReset(mButton61);
                gameButtonReset(mButton62);
                gameButtonReset(mButton63);
                gameButtonReset(mButton64);
                gameButtonReset(mButton65);
                gameButtonReset(mButton66);
            case SIX_X_SIX:
                gameButtonReset(mButton05);
                gameButtonReset(mButton15);
                gameButtonReset(mButton25);
                gameButtonReset(mButton35);
                gameButtonReset(mButton45);
                gameButtonReset(mButton50);
                gameButtonReset(mButton51);
                gameButtonReset(mButton52);
                gameButtonReset(mButton53);
                gameButtonReset(mButton54);
                gameButtonReset(mButton55);
            case NORMAL:
                gameButtonReset(mButton00);
                gameButtonReset(mButton01);
                gameButtonReset(mButton02);
                gameButtonReset(mButton03);
                gameButtonReset(mButton04);
                gameButtonReset(mButton10);
                gameButtonReset(mButton11);
                gameButtonReset(mButton12);
                gameButtonReset(mButton13);
                gameButtonReset(mButton14);
                gameButtonReset(mButton20);
                gameButtonReset(mButton21);
                gameButtonReset(mButton22);
                gameButtonReset(mButton23);
                gameButtonReset(mButton24);
                gameButtonReset(mButton30);
                gameButtonReset(mButton31);
                gameButtonReset(mButton32);
                gameButtonReset(mButton33);
                gameButtonReset(mButton34);
                gameButtonReset(mButton40);
                gameButtonReset(mButton41);
                gameButtonReset(mButton42);
                gameButtonReset(mButton43);
                gameButtonReset(mButton44);
                boardInit();
        }
    }

}