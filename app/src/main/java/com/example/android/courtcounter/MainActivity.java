package com.example.android.courtcounter;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.EmptyStackException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private Button btnTeamAOnePoint, btnTeamATwoPoint, btnTeamAThreePoint, btnTeamBOnePoint,
            btnTeamBTwoPoint, btnTeamBThreePoint, btnReset, btnNext, btnPrevious, btnFinish;

    private Presenter presenter;

    static final int FIRST_QUARTER = 0, SECOND_QUARTER = 1, THIRD_QUARTER = 2, FOURTH_QUARTER = 3;
    private int currentState = FIRST_QUARTER, globalState = FIRST_QUARTER;
    static final int MAX_STATE = FOURTH_QUARTER;

    private SaveRestoreScore saveRestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveRestore = new SaveRestoreScore();
        saveRestore.clearSP(this);

        presenter = new Presenter();

        btnTeamAOnePoint = findViewById(R.id.points1A);
        btnTeamATwoPoint = findViewById(R.id.points2A);
        btnTeamAThreePoint = findViewById(R.id.points3A);
        btnTeamBOnePoint = findViewById(R.id.points1B);
        btnTeamBTwoPoint = findViewById(R.id.points2B);
        btnTeamBThreePoint = findViewById(R.id.points3B);
        btnNext = findViewById(R.id.next);
        btnPrevious = findViewById(R.id.previous);
        btnFinish = findViewById(R.id.finish);
        btnReset = findViewById(R.id.reset);

        btnTeamAOnePoint.setOnClickListener(this);
        btnTeamATwoPoint.setOnClickListener(this);
        btnTeamAThreePoint.setOnClickListener(this);
        btnTeamBOnePoint.setOnClickListener(this);
        btnTeamBTwoPoint.setOnClickListener(this);
        btnTeamBThreePoint.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        btnTeamAOnePoint.setOnLongClickListener(this);
        btnTeamATwoPoint.setOnLongClickListener(this);
        btnTeamAThreePoint.setOnLongClickListener(this);
        btnTeamBOnePoint.setOnLongClickListener(this);
        btnTeamBTwoPoint.setOnLongClickListener(this);
        btnTeamBThreePoint.setOnLongClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.points1A) {
            presenter.onClickButtonTeamA(1);
        } else if (id == R.id.points2A) {
            presenter.onClickButtonTeamA(2);
        } else if (id == R.id.points3A) {
            presenter.onClickButtonTeamA(3);
        } else if (id == R.id.points1B) {
            presenter.onClickButtonTeamB(1);
        } else if (id == R.id.points2B) {
            presenter.onClickButtonTeamB(2);
        } else if (id == R.id.points3B) {
            presenter.onClickButtonTeamB(3);
        } else if (id == R.id.next) {
            nextBtn();
        } else if (id == R.id.previous) {
            previousBtn();
        } else if (id == R.id.finish) {
            finishBtn();
        } else if (id == R.id.reset) {
            resetBtn();
        }
        saveScoreOnActivity();
        rebuildActivity(currentState);
    }

    @Override
    public boolean onLongClick(View v) {
        int id = v.getId();
        if (id == R.id.points1A) {
            presenter.onLongClickButtonTeamA(1);
        } else if (id == R.id.points2A) {
            presenter.onLongClickButtonTeamA(2);
        } else if (id == R.id.points3A) {
            presenter.onLongClickButtonTeamA(3);
        } else if (id == R.id.points1B) {
            presenter.onLongClickButtonTeamB(1);
        } else if (id == R.id.points2B) {
            presenter.onLongClickButtonTeamB(2);
        } else if (id == R.id.points3B) {
            presenter.onLongClickButtonTeamB(3);
        }
        setBtnColor();
        return false;
    }


    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        presenter = (Presenter) savedInstanceState.get("presenter");
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("presenter", presenter);
        super.onSaveInstanceState(outState);
    }

    public void displayForTeams(int globalScoreA, int globalScoreB) {
        TextView tvScoreTeamA = findViewById(R.id.scoreTeamA);
        tvScoreTeamA.setText(String.valueOf(presenter.getScoreTeamA()));

        TextView tvScoreTeamB = findViewById(R.id.scoreTeamB);
        tvScoreTeamB.setText(String.valueOf(presenter.getScoreTeamB()));

        TextView tvGlobalScoreA = findViewById(R.id.globalScoreTeamA);
        TextView tvGlobalScoreB = findViewById(R.id.globalScoreTeamB);

        if ((globalScoreA != 0) || (globalScoreB != 0)) {
            tvGlobalScoreA.setVisibility(View.VISIBLE);
            tvGlobalScoreA.setText(String.valueOf(globalScoreA));

            tvGlobalScoreB.setVisibility(View.VISIBLE);
            tvGlobalScoreB.setText(String.valueOf(globalScoreB));
        } else {
            tvGlobalScoreA.setVisibility(View.INVISIBLE);
            tvGlobalScoreB.setVisibility(View.INVISIBLE);
        }
    }

    public void setBtnStandardColor() {
        btnTeamAOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
        btnTeamATwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
        btnTeamAThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
        btnTeamBOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
        btnTeamBTwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
        btnTeamBThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
    }

    public void setBtnColor() {
        try {
            switch (presenter.getLastStackA()) {
                case 1:
                    btnTeamAOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    btnTeamATwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    btnTeamAThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 2:
                    btnTeamAOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    btnTeamATwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    btnTeamAThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 3:
                    btnTeamAOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    btnTeamATwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    btnTeamAThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    break;
            }
        } catch (EmptyStackException ex) {
            btnTeamAOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
            btnTeamATwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
            btnTeamAThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
        }

        try {
            switch (presenter.getLastStackB()) {
                case 1:
                    btnTeamBOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    btnTeamBTwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    btnTeamBThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 2:
                    btnTeamBOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    btnTeamBTwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    btnTeamBThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 3:
                    btnTeamBOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    btnTeamBTwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    btnTeamBThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    break;
            }

        } catch (EmptyStackException ex) {
            btnTeamBOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
            btnTeamBTwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
            btnTeamBThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);

        }

    }

    void rebuildActivity(int currentState) {
        switch (currentState) {
            case FIRST_QUARTER:
                btnPrevious.setVisibility(View.INVISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.INVISIBLE);

                presenter.setScoreTeamA(saveRestore.restoreTeamA(this, FIRST_QUARTER));
                presenter.setScoreTeamB(saveRestore.restoreTeamB(this, FIRST_QUARTER));

                displayForTeams(0, 0);
                break;

            case SECOND_QUARTER:
                btnPrevious.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.INVISIBLE);

                presenter.setScoreTeamA(saveRestore.restoreTeamA(this, SECOND_QUARTER));
                presenter.setScoreTeamB(saveRestore.restoreTeamB(this, SECOND_QUARTER));

                displayForTeams(saveRestore.restoreTeamA(this, FIRST_QUARTER), saveRestore.restoreTeamB(this, FIRST_QUARTER));
                break;

            case THIRD_QUARTER:
                btnPrevious.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                btnFinish.setVisibility(View.INVISIBLE);

                presenter.setScoreTeamA(saveRestore.restoreTeamA(this, THIRD_QUARTER));
                presenter.setScoreTeamB(saveRestore.restoreTeamB(this, THIRD_QUARTER));

                int globalScoreA = saveRestore.restoreTeamA(this, FIRST_QUARTER) + saveRestore.restoreTeamA(this, SECOND_QUARTER);
                int globalScoreB = saveRestore.restoreTeamB(this, FIRST_QUARTER) + saveRestore.restoreTeamB(this, SECOND_QUARTER);

                displayForTeams(globalScoreA, globalScoreB);
                break;
            case FOURTH_QUARTER:
                btnPrevious.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.INVISIBLE);

                presenter.setScoreTeamA(saveRestore.restoreTeamA(this, FOURTH_QUARTER));
                presenter.setScoreTeamB(saveRestore.restoreTeamB(this, FOURTH_QUARTER));

                int globalScoreA2 = saveRestore.restoreTeamA(this, FIRST_QUARTER) + saveRestore.restoreTeamA(this, SECOND_QUARTER) + saveRestore.restoreTeamA(this, THIRD_QUARTER);
                int globalScoreB2 = saveRestore.restoreTeamB(this, FIRST_QUARTER) + saveRestore.restoreTeamB(this, SECOND_QUARTER) + saveRestore.restoreTeamB(this, THIRD_QUARTER);

                displayForTeams(globalScoreA2, globalScoreB2);
                if (globalState == FOURTH_QUARTER + 1) {
                    btnFinish.setVisibility(View.INVISIBLE);
                    btnReset.setVisibility(View.VISIBLE);
                } else {
                    btnFinish.setVisibility(View.VISIBLE);
                    btnReset.setVisibility(View.INVISIBLE);
                }
                break;
        }

        if (currentState < globalState) {
            btnTeamAOnePoint.setEnabled(false);
            btnTeamATwoPoint.setEnabled(false);
            btnTeamAThreePoint.setEnabled(false);
            btnTeamBOnePoint.setEnabled(false);
            btnTeamBTwoPoint.setEnabled(false);
            btnTeamBThreePoint.setEnabled(false);
            setBtnStandardColor();

        } else {
            btnTeamAOnePoint.setEnabled(true);
            btnTeamATwoPoint.setEnabled(true);
            btnTeamAThreePoint.setEnabled(true);
            btnTeamBOnePoint.setEnabled(true);
            btnTeamBTwoPoint.setEnabled(true);
            btnTeamBThreePoint.setEnabled(true);
            setBtnColor();
        }

        if (presenter.getScoreTeamA() == 0) {
            presenter.clearStackA();
        }
        if (presenter.getScoreTeamB() == 0) {
            presenter.clearStackB();
        }
    }

    void setTVQuarter(int quarter) {
        TextView TVQuarter = findViewById(R.id.whatQuarter);
        switch (quarter) {
            case FIRST_QUARTER:
                TVQuarter.setText(getResources().getString(R.string.firstQuarter));
                break;
            case SECOND_QUARTER:
                TVQuarter.setText(getResources().getString(R.string.secondQuarter));
                break;
            case THIRD_QUARTER:
                TVQuarter.setText(getResources().getString(R.string.thirdQuarter));
                break;
            case FOURTH_QUARTER:
                TVQuarter.setText(getResources().getString(R.string.fourthQuarter));
                break;
        }
    }

    void nextBtn() {
        if (currentState < MAX_STATE) {
            presenter.setScoreTeamA(0);
            presenter.setScoreTeamB(0);
            currentState++;
            if ((globalState < FOURTH_QUARTER) && (currentState > globalState)) globalState++;
            setTVQuarter(currentState);
            rebuildActivity(currentState);
        }
    }

    void previousBtn() {
        if (currentState > MAX_STATE) {
            currentState--;
        }
        currentState--;
        setTVQuarter(currentState);
        rebuildActivity(currentState);
    }

    void finishBtn() {
        saveRestore.save(this, globalState, presenter.getScoreTeamA(), presenter.getScoreTeamB());
        globalState++;
        presenter.clearStackA();
        presenter.clearStackB();
        rebuildActivity(currentState);
    }

    void resetBtn() {
        presenter.setScoreTeamA(0);
        presenter.setScoreTeamB(0);
        presenter.clearStackA();
        presenter.clearStackB();
        saveRestore.clearSP(this);
        globalState = FIRST_QUARTER;
        currentState = globalState;
        setTVQuarter(currentState);
    }

    void saveScoreOnActivity() {
        saveRestore.save(this, currentState, presenter.getScoreTeamA(), presenter.getScoreTeamB());
    }
}
