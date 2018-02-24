package com.example.android.courtcounter;

import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.EmptyStackException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    Button BtnTeamAOnePoint, BtnTeamATwoPoint, BtnTeamAThreePoint, BtnTeamBOnePoint, BtnTeamBTwoPoint, BtnTeamBThreePoint, BtnReset;

    Presenter presenter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter();

        BtnTeamAOnePoint = findViewById(R.id.points1A);
        BtnTeamATwoPoint = findViewById(R.id.points2A);
        BtnTeamAThreePoint = findViewById(R.id.points3A);
        BtnTeamBOnePoint = findViewById(R.id.points1B);
        BtnTeamBTwoPoint = findViewById(R.id.points2B);
        BtnTeamBThreePoint = findViewById(R.id.points3B);

        BtnTeamAOnePoint.setOnClickListener(this);
        BtnTeamATwoPoint.setOnClickListener(this);
        BtnTeamAThreePoint.setOnClickListener(this);
        BtnTeamBOnePoint.setOnClickListener(this);
        BtnTeamBTwoPoint.setOnClickListener(this);
        BtnTeamBThreePoint.setOnClickListener(this);

        BtnTeamAOnePoint.setOnLongClickListener(this);
        BtnTeamATwoPoint.setOnLongClickListener(this);
        BtnTeamAThreePoint.setOnLongClickListener(this);
        BtnTeamBOnePoint.setOnLongClickListener(this);
        BtnTeamBTwoPoint.setOnLongClickListener(this);
        BtnTeamBThreePoint.setOnLongClickListener(this);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            BtnReset = findViewById(R.id.reset);
            BtnReset.setOnClickListener(this);
        }


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
            case R.id.reset:
                presenter.setScoreTeamA(0);
                presenter.setScoreTeamB(0);
                presenter.clearStacks();
                displayForTeams();
                break;
        }
        setBtnColor();
        displayForTeams();
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
                presenter.onLongClickButtonTeamA(1);
                break;
            case R.id.points2B:
                presenter.onLongClickButtonTeamA(2);
                break;
            case R.id.points3B:
                presenter.onLongClickButtonTeamA(3);
                break;
        }
        setBtnColor();
        displayForTeams();
        return false;
    }

    @Override
    protected void onDestroy() {
        presenter = null;
        super.onDestroy();
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        presenter = (Presenter) savedInstanceState.get("presenter");
        super.onRestoreInstanceState(savedInstanceState);
    }

    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("presenter", presenter);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        displayForTeams();
        setBtnColor();
        super.onResume();
    }

    public void displayForTeams() {
        TextView TVScoreTeamA = findViewById(R.id.scoreTeamA);
        TVScoreTeamA.setText(String.valueOf(presenter.getScoreTeamA()));
        TextView TVScoreTeamB = findViewById(R.id.scoreTeamB);
        TVScoreTeamB.setText(String.valueOf(presenter.getScoreTeamB()));
    }

    public void setBtnColor() {
        try {
            switch (presenter.getLastStackA()) {
                case 1:
                    BtnTeamAOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    BtnTeamATwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BtnTeamAThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 2:
                    BtnTeamAOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BtnTeamATwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    BtnTeamAThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 3:
                    BtnTeamAOnePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BtnTeamATwoPoint.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BtnTeamAThreePoint.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
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

}

