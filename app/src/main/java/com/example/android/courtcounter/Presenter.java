package com.example.android.courtcounter;

import android.content.Context;

import java.io.Serializable;
import java.util.Stack;

/**
 * Created by Лунтя on 22.02.2018.
 */

class Presenter implements Serializable{
    private int scoreTeamA, scoreTeamB;
    private Stack<Integer> pushedBtnTeamA, pushedBtnTeamB;
    private boolean longClick;

    Presenter(){
        scoreTeamA = 0;
        scoreTeamB = 0;
        pushedBtnTeamA = new Stack<>();
        pushedBtnTeamB = new Stack<>();
        longClick = false;
    }


    void setScoreTeamA(int _scoreA){
        scoreTeamA = _scoreA;
    }

    void setScoreTeamB(int _scoreB){
        scoreTeamB = _scoreB;
    }
    int getScoreTeamA(){

        return scoreTeamA;
    }
    int getScoreTeamB(){
        return scoreTeamB;
    }
    int getLastStackA(){
        return pushedBtnTeamA.peek();
    }
    int getLastStackB(){
        return pushedBtnTeamB.peek();
    }
    int getStackALength(){
        return pushedBtnTeamA.size();
    }
    int getStackBLength(){
        return pushedBtnTeamA.size();
    }

    void clearStacks(){
        pushedBtnTeamA.removeAllElements();
        pushedBtnTeamB.removeAllElements();
    }
    private void addToStack(Stack<Integer> teamBtn, int lastPush) {
        teamBtn.push(lastPush);
    }
    void removeFromStackA() {
        pushedBtnTeamA.pop();
    }
    void removeFromStackB() {
        pushedBtnTeamB.pop();
    }

    void onClickButtonTeamA(int whatPush) {
        if (!longClick) {
            scoreTeamA += whatPush;
            addToStack(pushedBtnTeamA, whatPush);
        } else {
            longClick = false;
        }
    }
    void onClickButtonTeamB(int whatPush) {
        if (!longClick) {
            scoreTeamB += whatPush;
            addToStack(pushedBtnTeamB, whatPush);
        } else {
            longClick = false;
        }
    }

    void onLongClickButtonTeamA(int whatPush) {
        longClick = true;
        try {
            if (pushedBtnTeamA.get(pushedBtnTeamA.size() - 1) == whatPush) {
                removeFromStackA();
                scoreTeamA -= whatPush;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }
    void onLongClickButtonTeamB(int whatPush) {
        longClick = true;
        try {
            if (pushedBtnTeamB.get(pushedBtnTeamB.size() - 1) == whatPush) {
                removeFromStackB();
                scoreTeamB -= whatPush;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }
//    void save(Context context){
//        context.getSharedPreferences();
//    }
}
