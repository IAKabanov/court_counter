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
    Button AOne, ATwo, AThree, BOne, BTwo, BThree, Reset;

    Presenter presenter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getMenuInflater().inflate(R.menu.menu, menu);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.setScoreA(0);
        presenter.setScoreB(0);
        presenter.clearStacks();
        displayForTeams();
        setBgColor();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter();

        AOne = findViewById(R.id.points1A);
        ATwo = findViewById(R.id.points2A);
        AThree = findViewById(R.id.points3A);
        BOne = findViewById(R.id.points1B);
        BTwo = findViewById(R.id.points2B);
        BThree = findViewById(R.id.points3B);

        AOne.setOnClickListener(this);
        ATwo.setOnClickListener(this);
        AThree.setOnClickListener(this);
        BOne.setOnClickListener(this);
        BTwo.setOnClickListener(this);
        BThree.setOnClickListener(this);

        AOne.setOnLongClickListener(this);
        ATwo.setOnLongClickListener(this);
        AThree.setOnLongClickListener(this);
        BOne.setOnLongClickListener(this);
        BTwo.setOnLongClickListener(this);
        BThree.setOnLongClickListener(this);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Reset = findViewById(R.id.reset);
            Reset.setOnClickListener(this);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.points1A:
                presenter.buttonLogicA(1);
                break;
            case R.id.points2A:
                presenter.buttonLogicA(2);
                break;
            case R.id.points3A:
                presenter.buttonLogicA(3);
                break;
            case R.id.points1B:
                presenter.buttonLogicB(1);
                break;
            case R.id.points2B:
                presenter.buttonLogicB(2);
                break;
            case R.id.points3B:
                presenter.buttonLogicB(3);
                break;
            case R.id.reset:
                presenter.setScoreA(0);
                presenter.setScoreB(0);
                presenter.clearStacks();
                displayForTeams();
                break;
        }
        setBgColor();
        displayForTeams();
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.points1A:
                presenter.buttonLongLogicA(1);
                break;
            case R.id.points2A:
                presenter.buttonLongLogicA(2);
                break;
            case R.id.points3A:
                presenter.buttonLongLogicA(3);
                break;
            case R.id.points1B:
                presenter.buttonLongLogicB(1);
                break;
            case R.id.points2B:
                presenter.buttonLongLogicB(2);
                break;
            case R.id.points3B:
                presenter.buttonLongLogicB(3);
                break;
        }
        setBgColor();
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
        setBgColor();
        super.onResume();
    }

    public void displayForTeams() {
        TextView tvscoreA = findViewById(R.id.scoreTeamA);
        tvscoreA.setText(String.valueOf(presenter.getScoreA()));
        TextView tvscoreB = findViewById(R.id.scoreTeamB);
        tvscoreB.setText(String.valueOf(presenter.getScoreB()));
    }

    public void setBgColor() {
        try {
            switch (presenter.getLastStackA()) {
                case 1:
                    AOne.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    ATwo.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    AThree.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 2:
                    AOne.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    ATwo.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    AThree.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 3:
                    AOne.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    ATwo.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    AThree.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    break;
            }
        } catch (EmptyStackException ex) {
            AOne.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
            ATwo.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
            AThree.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
        }

        try {
            switch (presenter.getLastStackB()) {
                case 1:
                    BOne.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    BTwo.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BThree.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 2:
                    BOne.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BTwo.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    BThree.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    break;
                case 3:
                    BOne.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BTwo.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
                    BThree.getBackground().setColorFilter(getResources().getColor(R.color.colorPressedButton), PorterDuff.Mode.SRC);
                    break;
            }

        } catch (EmptyStackException ex) {
            BOne.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
            BTwo.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);
            BThree.getBackground().setColorFilter(getResources().getColor(R.color.colorButtonNormal), PorterDuff.Mode.SRC);

        }

    }

}

