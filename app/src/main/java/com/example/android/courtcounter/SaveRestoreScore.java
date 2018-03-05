package com.example.android.courtcounter;

import android.content.Context;
import android.content.SharedPreferences;



class SaveRestoreScore {

    private static final String APP_PREFERENCES = "mysettings";
    private static final String APP_PREFERENCES_SCOREA1 = "ScoreAQuarter1";
    private static final String APP_PREFERENCES_SCOREB1 = "ScoreBQuarter1";
    private static final String APP_PREFERENCES_SCOREA2 = "ScoreAQuarter2";
    private static final String APP_PREFERENCES_SCOREB2 = "ScoreBQuarter2";
    private static final String APP_PREFERENCES_SCOREA3 = "ScoreAQuarter3";
    private static final String APP_PREFERENCES_SCOREB3 = "ScoreBQuarter3";
    private static final String APP_PREFERENCES_SCOREA4 = "ScoreAQuarter4";
    private static final String APP_PREFERENCES_SCOREB4 = "ScoreBQuarter4";
    private final int firstQuarter = 0, secondQuarter = 1, thirdQuarter = 2, fourthQuarter = 3;

    private SharedPreferences TeamsScore;

    void save(Context context, int quarter, int scoreA, int scoreB) {
        TeamsScore = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = TeamsScore.edit();
        switch (quarter) {
            case firstQuarter:
                editor.putInt(APP_PREFERENCES_SCOREA1, scoreA);
                editor.putInt(APP_PREFERENCES_SCOREB1, scoreB);
                editor.apply();
                break;
            case secondQuarter:
                editor.putInt(APP_PREFERENCES_SCOREA2, scoreA);
                editor.putInt(APP_PREFERENCES_SCOREB2, scoreB);
                editor.apply();
                break;
            case thirdQuarter:
                editor.putInt(APP_PREFERENCES_SCOREA3, scoreA);
                editor.putInt(APP_PREFERENCES_SCOREB3, scoreB);
                editor.apply();
                break;

            case fourthQuarter:
                editor.putInt(APP_PREFERENCES_SCOREA4, scoreA);
                editor.putInt(APP_PREFERENCES_SCOREB4, scoreB);
                editor.apply();
                break;
        }
    }

    int restoreTeamA(Context context, int quarter) {
        TeamsScore = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        switch (quarter) {
            case firstQuarter:
                if(TeamsScore.contains(APP_PREFERENCES_SCOREA1)) {
                    return TeamsScore.getInt(APP_PREFERENCES_SCOREA1, 0);
                }
                break;
            case secondQuarter:
                if(TeamsScore.contains(APP_PREFERENCES_SCOREA2)) {
                    return TeamsScore.getInt(APP_PREFERENCES_SCOREA2, 0);
                }
                break;
            case thirdQuarter:
                if(TeamsScore.contains(APP_PREFERENCES_SCOREA3)) {
                    return TeamsScore.getInt(APP_PREFERENCES_SCOREA3, 0);
                }
                break;

            case fourthQuarter:
                if(TeamsScore.contains(APP_PREFERENCES_SCOREA4)) {
                    return TeamsScore.getInt(APP_PREFERENCES_SCOREA4, 0);
                }
                break;
        }
        return 0;
    }

    int restoreTeamB(Context context, int quarter) {
        TeamsScore = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        switch (quarter) {
            case firstQuarter:
                if(TeamsScore.contains(APP_PREFERENCES_SCOREB1)) {
                    return TeamsScore.getInt(APP_PREFERENCES_SCOREB1, 0);
                }
                break;
            case secondQuarter:
                if(TeamsScore.contains(APP_PREFERENCES_SCOREB2)) {
                    return TeamsScore.getInt(APP_PREFERENCES_SCOREB2, 0);
                }
                break;
            case thirdQuarter:
                if(TeamsScore.contains(APP_PREFERENCES_SCOREB3)) {
                    return TeamsScore.getInt(APP_PREFERENCES_SCOREB3, 0);
                }
                break;

            case fourthQuarter:
                if(TeamsScore.contains(APP_PREFERENCES_SCOREB4)) {
                    return TeamsScore.getInt(APP_PREFERENCES_SCOREB4, 0);
                }
                break;
        }
        return 0;
    }


}
