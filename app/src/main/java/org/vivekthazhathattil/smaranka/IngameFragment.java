package org.vivekthazhathattil.smaranka;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class IngameFragment extends Fragment {
    private String mTestNumString;
    private int nDigitSystem = 3;
    private int digitDisplayIndex = 0;
    private int maxDisplayedCount = 0;
    private int rightBound = 200;

    private double timeLimit = 1;
    private TextView numDisplayView;
    private long millisLeft;
    private float timeDelay = 1;
    private CountDownTimer timer;
    private TextView timerView;

    public IngameFragment(int numDigits, int maxNum, double timeLimit) {
       this.nDigitSystem = numDigits;
       this.rightBound = maxNum;
       this.timeLimit = timeLimit;
        createTestString();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ingame, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageButton nextButton = getView().findViewById(R.id.next_button);
        ImageButton prevButton = getView().findViewById(R.id.prev_button);
        Button recallButton = getView().findViewById(R.id.recall_button);
        numDisplayView = getView().findViewById(R.id.random_number_textview);

        showSubStringNumber(nDigitSystem);
        millisLeft = (long)(timeLimit * 60000);
        timerView = getView().findViewById(R.id.timeLeftView);
        timer = setupTimer(millisLeft, timeDelay);
        timer.start();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSubStringNumber(nDigitSystem);
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSubStringNumber(-1 * nDigitSystem);
            }
        });

        recallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).switchToRecallFragment(mTestNumString, maxDisplayedCount);
            }
        });
    }

    private void showSubStringNumber(int incDigits){
        String subString = "";
        if(digitDisplayIndex + incDigits > mTestNumString.length() || (digitDisplayIndex == 0 && incDigits < 0) || incDigits == 0) {
            return;
        }
            if(incDigits > 0){
                subString = mTestNumString.substring(digitDisplayIndex, digitDisplayIndex + incDigits);
            }
            else{
                if(-incDigits > digitDisplayIndex){
                    subString = mTestNumString.substring(0, -incDigits - digitDisplayIndex);
                }
                else{
                    subString = mTestNumString.substring(digitDisplayIndex + incDigits, digitDisplayIndex);
                }
            }
            digitDisplayIndex = Math.max(0, digitDisplayIndex + incDigits);
            maxDisplayedCount = Math.max(maxDisplayedCount, digitDisplayIndex);

            try{
                if(!subString.equals("")){
                    numDisplayView.setText(subString);
                }
            }
            catch(NullPointerException e){
            }
    }

    private void createTestString(){
        Random randNum = new Random();
        this.mTestNumString =  "";
        int leftBound = 0, numTotalDigits = 1000;
        for(int i = 0; i < numTotalDigits; i+=3){
           String numString = String.valueOf(leftBound + randNum.nextInt(rightBound - leftBound + 1));
           int numLength = numString.length();
           // TODO: replace this hacky solution
           if(numLength < nDigitSystem){
              for(int j = 0; j < nDigitSystem - numLength; ++j){
                  this.mTestNumString += "0";
              }
           }
           this.mTestNumString += numString;
        }
    }

    public CountDownTimer setupTimer(long timeLeft, float interval){
        CountDownTimer timer = new CountDownTimer(timeLeft,(int)(interval*1000) ) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisLeft = millisUntilFinished;
                if(timerView != null){
                    String timeText = String.valueOf(millisLeft/1000);
                    timerView.setText(timeText);
                }
            }

            @Override
            public void onFinish() {
                ((MainActivity)getActivity()).switchToRecallFragment(mTestNumString, maxDisplayedCount);
            }
        };
        return timer;
    }
}