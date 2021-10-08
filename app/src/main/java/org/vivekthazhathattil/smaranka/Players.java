package org.vivekthazhathattil.smaranka;

import java.util.Random;

public class Players {
    public String[] mNamesA;
    public String[] mNamesB;
    public double[] mRatingsA;
    public double[] mRatingsB;
    private final double MIN_RATING = 600.0;
    private final double MAX_RATING = 2800.0;
    private final double THRES_RATING = 1500.0;

    public Players(){
        initPlayers();
        initRatings();
    }

    private void initPlayers(){
        mNamesA = new String[]{
                "Phil McCracken",
                "Burlington Coatfactory",
                "Jessica Munkititz",
                "Zats Goodenoff",
                "Stone Wang",
                "Justin Case",
                "Hardik Patel",
                "Mustafa Mustafa",
                "Dr Cox Hyman",
                "Obu Obu Obu",
                "Sheila Dikshit",
                "Yu Arafuka",
                "Marybeth Beth Beth",
                "Count Ravioli",
                "Minty Hummer",
                "Ita Dapeeza",
                "Obese Jackson",
                "Anyuk Nyuk",
                "Disfatt Bidge",
                "Dr. Shrimp Puerto Rico"
        };

        mNamesB = new String[]{
                "Brad Poole",
                "Allan Guzman",
                "Emily Singleton",
                "Rhonda Foster",
                "Jacquelyn Parker",
                "Craig Ballard",
                "Monica Stanley",
                "Meghan Bell",
                "Horace Burns",
                "Sonia Watson",
                "Emmett Moore",
                "Faith Shaw",
                "Kelvin Flores",
                "Patty Newman",
                "Theresa Perkins",
                "Jared Carlson",
                "Lydia Cruz",
                "Patricia Mullins",
                "Pam Roy",
                "Felicia Day"
        };
    }

    private void initRatings(){
        Random randNum = new Random();
        mRatingsA = new double[mNamesA.length];
        mRatingsB = new double[mNamesB.length];
        for(int i = 0; i < mNamesB.length; ++i){
            mRatingsB[i] = MIN_RATING + randNum.nextInt((int)(THRES_RATING - MIN_RATING + 1));
        }
        for(int i = 0; i < mNamesA.length; ++i){
            mRatingsA[i] = THRES_RATING + randNum.nextInt((int)(MAX_RATING - THRES_RATING + 1));
        }
    }

    public double getRating(String strType){
        if(strType.equals("thres")){
            return THRES_RATING;
        }
        else if(strType.equals("min")){
            return MIN_RATING;
        }
        else if(strType.equals("max")){
            return MAX_RATING;
        }
        return -1;
    }
}
