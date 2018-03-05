package com.example.android.courtcounter;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.EmptyStackException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    Button BtnTeamAOnePoint, BtnTeamATwoPoint, BtnTeamAThreePoint, BtnTeamBOnePoint,
            BtnTeamBTwoPoint, BtnTeamBThreePoint, /*BtnReset*/ BtnNext, BtnPrevious, BtnFinish;

    Presenter presenter;

    final int firstQuarter = 0, secondQuarter = 1, thirdQuarter = 2, fourthQuarter = 3;
    int currentState = firstQuarter, globalState = firstQuarter;
    final int maxState = fourthQuarter;

    SaveRestoreScore saveRestore;

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getMenuInflater().inflate(R.menu.menu, menu);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item != null) {
            presenter.setScoreTeamA(0);
            presenter.setScoreTeamB(0);
            presenter.clearStacks();
            displayForTeams();
            setBtnColor();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saveRestore = new SaveRestoreScore();

        presenter = new Presenter();

        BtnTeamAOnePoint = findViewById(R.id.points1A);
        BtnTeamATwoPoint = findViewById(R.id.points2A);
        BtnTeamAThreePoint = findViewById(R.id.points3A);
        BtnTeamBOnePoint = findViewById(R.id.points1B);
        BtnTeamBTwoPoint = findViewById(R.id.points2B);
        BtnTeamBThreePoint = findViewById(R.id.points3B);
        BtnNext = findViewById(R.id.next);
        BtnPrevious = findViewById(R.id.previous);
        BtnFinish = findViewById(R.id.finish);

        BtnTeamAOnePoint.setOnClickListener(this);
        BtnTeamATwoPoint.setOnClickListener(this);
        BtnTeamAThreePoint.setOnClickListener(this);
        BtnTeamBOnePoint.setOnClickListener(this);
        BtnTeamBTwoPoint.setOnClickListener(this);
        BtnTeamBThreePoint.setOnClickListener(this);
        BtnNext.setOnClickListener(this);
        BtnPrevious.setOnClickListener(this);
        BtnFinish.setOnClickListener(this);

        BtnTeamAOnePoint.setOnLongClickListener(this);
        BtnTeamATwoPoint.setOnLongClickListener(this);
        BtnTeamAThreePoint.setOnLongClickListener(this);
        BtnTeamBOnePoint.setOnLongClickListener(this);
        BtnTeamBTwoPoint.setOnLongClickListener(this);
        BtnTeamBThreePoint.setOnLongClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.points1A:
                presenter.onClickButtonTeamA(1);
                break;
            case R.id.points2A:
                presenter.onClickButtonTeamA(2);
                break;
            case R.id.points3A:
                presenter.onClickButtonTeamA(3);
                break;
            case R.id.points1B:
                presenter.onClickButtonTeamB(1);
                break;
            case R.id.points2B:
                presenter.onClickButtonTeamB(2);
                break;
            case R.id.points3B:
                presenter.onClickButtonTeamB(3);
                break;
//            case R.id.reset:
//                presenter.setScoreTeamA(0);
//                presenter.setScoreTeamB(0);
//                presenter.clearStacks();
//                break;
            case R.id.next:
                if (globalState <= maxState){
                    saveRestore.save(this, globalState,
                            presenter.getScoreTeamA(), presenter.getScoreTeamB());
                }
                if (currentState < maxState) {
                    presenter.clearStacks();
                    presenter.setScoreTeamA(0);
                    presenter.setScoreTeamB(0);
                    currentState++;
                    if ((globalState < fourthQuarter) && (currentState > globalState))
                        globalState++;

                    rebuildActivity(currentState);
                }
                break;
            case R.id.previous:
                if (currentState > maxState) {
                    currentState--;
                }
                currentState--;
                rebuildActivity(currentState);
                break;
            case R.id.finish:
                saveRestore.save(this, globalState,
                        presenter.getScoreTeamA(), presenter.getScoreTeamB());
                globalState++;
                presenter.clearStacks();
                rebuildActivity(currentState);
                break;

        }
        setBtnColor();
        rebuildActivity(currentState);
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.points1A:
                presenter.onLongClickButtonTeamA(1);
                break;
            case R.id.points2A:
                presenter.onLongClickButtonTeamA(2);
                break;
            case R.id.points3A:
                presenter.onLongClickButtonTeamA(3);
                break;
            case R.id.points1B:
                presenter.onLongClickButtonTeamB(1);
                break;
            case R.id.points2B:
                presenter.onLongClickButtonTeamB(2);
                break;
            case R.id.points3B:
                presenter.onLongClickButtonTeamB(3);
                break;
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

//    @Override
//    protected void onResume() {
//        displayForTeams();
//        setBtnColor();
//        super.onResume();
//    }

    public void displayForTeams(int globalScoreA, int globalScoreB) {
        TextView TVScoreTeamA = findViewById(R.id.scoreTeamA);
        TVScoreTeamA.setText(String.valueOf(presenter.getScoreTeamA()));

        TextView TVScoreTeamB = findViewById(R.id.scoreTeamB);
        TVScoreTeamB.setText(String.valueOf(presenter.getScoreTeamB()));

        if ((globalScoreA!=0) || (globalScoreB!=0)) {
            TextView TVGlobalScoreA = findViewById(R.id.globalScoreTeamA);
            TVGlobalScoreA.setVisibility(View.VISIBLE);
            TVGlobalScoreA.setText(String.valueOf(globalScoreA));

            TextView TVGlobalScoreB = findViewById(R.id.globalScoreTeamB);
            TVGlobalScoreB.setVisibility(View.VISIBLE);
            TVGlobalScoreB.setText(String.valueOf(globalScoreB));
        }
        else {
            TextView TVGlobalScoreA = findViewById(R.id.globalScoreTeamA);
            TVGlobalScoreA.setVisibility(View.INVISIBLE);
            TextView TVGlobalScoreB = findViewById(R.id.globalScoreTeamB);
            TVGlobalScoreB.setVisibility(View.INVISIBLE);
        }
    }

    public void setBtnColor() {
        try {
            switch (presenter.getLastStackA()) {
                case 1:
                    BtnTeamAOnePoint.getBackground().setColorFilter(getResources()
                            .getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    BtnTeamATwoPoint.getBackground().setColorFilter(getResources()
                            .getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BtnTeamAThreePoint.getBackground().setColorFilter(getResources()
                            .getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 2:
                    BtnTeamAOnePoint.getBackground().setColorFilter(getResources()
                            .getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BtnTeamATwoPoint.getBackground().setColorFilter(getResources()
                            .getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    BtnTeamAThreePoint.getBackground().setColorFilter(getResources()
                            .getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 3:
                    BtnTeamAOnePoint.getBackground().setColorFilter(getResources()
                            .getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BtnTeamATwoPoint.getBackground().setColorFilter(getResources()
                            .getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BtnTeamAThreePoint.getBackground().setColorFilter(getResources()
                            .getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    break;
            }
        } catch (EmptyStackException ex) {
            BtnTeamAOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
            BtnTeamATwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
            BtnTeamAThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
        }

        try {
            switch (presenter.getLastStackB()) {
                case 1:
                    BtnTeamBOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    BtnTeamBTwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BtnTeamBThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 2:
                    BtnTeamBOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BtnTeamBTwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    BtnTeamBThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 3:
                    BtnTeamBOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BtnTeamBTwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BtnTeamBThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    break;
            }

        } catch (EmptyStackException ex) {
            BtnTeamBOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
            BtnTeamBTwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
            BtnTeamBThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);

        }

    }

    void rebuildActivity(int currentState) {
        switch (currentState) {
            case firstQuarter:
                BtnPrevious.setVisibility(View.INVISIBLE);
                BtnNext.setVisibility(View.VISIBLE);
                BtnFinish.setVisibility(View.INVISIBLE);

                if (globalState != currentState) {
                    presenter.setScoreTeamA(saveRestore.restoreTeamA(this, firstQuarter));
                    presenter.setScoreTeamB(saveRestore.restoreTeamB(this, firstQuarter));
                }
                displayForTeams(0,0);

                break;

            case secondQuarter:
                BtnPrevious.setVisibility(View.VISIBLE);
                BtnNext.setVisibility(View.VISIBLE);
                BtnFinish.setVisibility(View.INVISIBLE);

                if (globalState != currentState) {
                    presenter.setScoreTeamA(saveRestore.restoreTeamA(this, secondQuarter));
                    presenter.setScoreTeamB(saveRestore.restoreTeamB(this, secondQuarter));
                }
                displayForTeams(saveRestore.restoreTeamA(this, firstQuarter),
                        saveRestore.restoreTeamB(this, firstQuarter));

                break;

            case thirdQuarter:
                BtnPrevious.setVisibility(View.VISIBLE);
                BtnNext.setVisibility(View.VISIBLE);
                BtnFinish.setVisibility(View.INVISIBLE);

                if (globalState != currentState) {
                    presenter.setScoreTeamA(saveRestore.restoreTeamA(this, thirdQuarter));
                    presenter.setScoreTeamB(saveRestore.restoreTeamB(this, thirdQuarter));
                }
                int globalScoreA = saveRestore.restoreTeamA(this, firstQuarter)+
                        saveRestore.restoreTeamA(this, secondQuarter);

                int globalScoreB = saveRestore.restoreTeamB(this, firstQuarter)+
                        saveRestore.restoreTeamB(this, secondQuarter);

                displayForTeams(globalScoreA,globalScoreB);

                break;
            case fourthQuarter:
                BtnPrevious.setVisibility(View.VISIBLE);
                BtnNext.setVisibility(View.INVISIBLE);

                if (currentState < globalState) {
                    presenter.setScoreTeamA(saveRestore.restoreTeamA(this, fourthQuarter));
                    presenter.setScoreTeamB(saveRestore.restoreTeamB(this, fourthQuarter));
                }
                int globalScoreA2 = saveRestore.restoreTeamA(this, firstQuarter)+
                        saveRestore.restoreTeamA(this, secondQuarter)+
                        saveRestore.restoreTeamA(this, thirdQuarter);

                int globalScoreB2 = saveRestore.restoreTeamB(this, firstQuarter)+
                        saveRestore.restoreTeamB(this, secondQuarter)+
                        saveRestore.restoreTeamB(this, thirdQuarter);

                displayForTeams(globalScoreA2,globalScoreB2);

                if (globalState == fourthQuarter + 1)
                    BtnFinish.setVisibility(View.INVISIBLE);
                else BtnFinish.setVisibility(View.VISIBLE);
                break;
        }

        if (currentState < globalState) {
            BtnTeamAOnePoint.setEnabled(false);
            BtnTeamATwoPoint.setEnabled(false);
            BtnTeamAThreePoint.setEnabled(false);
            BtnTeamBOnePoint.setEnabled(false);
            BtnTeamBTwoPoint.setEnabled(false);
            BtnTeamBThreePoint.setEnabled(false);
        } else {
            BtnTeamAOnePoint.setEnabled(true);
            BtnTeamATwoPoint.setEnabled(true);
            BtnTeamAThreePoint.setEnabled(true);
            BtnTeamBOnePoint.setEnabled(true);
            BtnTeamBTwoPoint.setEnabled(true);
            BtnTeamBThreePoint.setEnabled(true);
        }

    }
}
