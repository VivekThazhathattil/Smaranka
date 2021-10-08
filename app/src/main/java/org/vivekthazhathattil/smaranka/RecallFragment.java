package org.vivekthazhathattil.smaranka;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RecallFragment extends Fragment {

    private String solutionText = "";
    private int maxIdx = 0;

    public RecallFragment(String soln, int maxIdx) {
        this.solutionText = soln;
        this.maxIdx = maxIdx;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recall, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        Button checkButton = getView().findViewById(R.id.check_button);
        EditText yourAnswerText = getView().findViewById(R.id.solution_type_edit_text);
        TextView statsView = getView().findViewById(R.id.stats_view);
        statsView.setText("");

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(statsView.getText() != ""){
                    statsView.setText("");
                    yourAnswerText.setText("");
                    ((MainActivity)getActivity()).switchToMainFragment();
                }
                checkCorrectness(yourAnswerText, statsView);
                yourAnswerText.setFocusable(false);
                yourAnswerText.setFocusableInTouchMode(false);
                yourAnswerText.setClickable(false);
                checkButton.setText("Restart");
            }
        });
    }
    private void checkCorrectness(EditText ansText, TextView stats){
        String ans = ansText.getText().toString();
        String solutionSubStr = solutionText.substring(0, maxIdx);
        SpannableString ss = new SpannableString(solutionSubStr);
        ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.rgb(255, 127, 138));
        ss.setSpan(fcsRed, 0, solutionSubStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        int correct = 0;
        int total = maxIdx;
        try{
            for(int i = 0; i <= maxIdx && i < ans.length(); ++i){
                if(ans.charAt(i) == solutionText.charAt(i)){
                    correct++;
                    ForegroundColorSpan fcsGreen = new ForegroundColorSpan(Color.rgb(101, 195, 104));
                    ss.setSpan(fcsGreen, i, i+1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            ansText.setText(ss);
        }
        catch(Exception e){
        }
        String statText = "Correct: \n" + correct + "/" + total;
        stats.setText(statText);
    }
}