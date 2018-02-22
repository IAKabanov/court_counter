package com.example.android.courtcounter;

import java.io.Serializable;
import java.util.Stack;

/**
 * Created by Лунтя on 22.02.2018.
 */

class Presenter implements Serializable{
    private int scoreA, scoreB;
    private Stack<Integer> pushedA, pushedB;
    private boolean longClick;

    Presenter(){
        scoreA = 0;
        scoreB = 0;
        pushedA = new Stack<>();
        pushedB = new Stack<>();
        longClick = false;
    }


    void setScoreA(int _scoreA){
        scoreA = _scoreA;
    }

    void setScoreB(int _scoreB){
        scoreB = _scoreB;
    }
    int getScoreA(){
        return scoreA;
    }
    int getScoreB(){
        return scoreB;
    }
    int getLastStackA(){
        return pushedA.peek();
    }
    int getLastStackB(){
        return pushedB.peek();
    }
    void clearStacks(){
        pushedA.removeAllElements();
        pushedB.removeAllElements();
    }


    private void addToStack(Stack<Integer> teamBtn, int lastPush) {
        teamBtn.push(lastPush);
    }

    private void removeFromStack(Stack<Integer> teamBtn) {
        teamBtn.pop();
    }

    void buttonLogicA(int whatPush) {
        if (!longClick) {
            scoreA += whatPush;
            addToStack(pushedA, whatPush);
        } else {
            longClick = false;
        }
    }

    void buttonLogicB(int whatPush) {
        if (!longClick) {
            scoreB += whatPush;
            addToStack(pushedB, whatPush);
        } else {
            longClick = false;
        }
    }

    void buttonLongLogicA(int whatPush) {
        longClick = true;
        try {
            if (pushedA.get(pushedA.size() - 1) == whatPush) {
                removeFromStack(pushedA);
                scoreA -= whatPush;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    void buttonLongLogicB(int whatPush) {
        longClick = true;
        try {
            if (pushedB.get(pushedB.size() - 1) == whatPush) {
                removeFromStack(pushedB);
                scoreB -= whatPush;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }
}
