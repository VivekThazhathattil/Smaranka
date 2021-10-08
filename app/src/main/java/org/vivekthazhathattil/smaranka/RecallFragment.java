package org.vivekthazhathattil.smaranka;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
                    ((MainActivity)getActivity()).switchToMainFragment();
                }
                checkCorrectness(yourAnswerText, statsView);
                yourAnswerText.setEnabled(false);
                checkButton.setText("Restart");
            }
        });
    }
    private void checkCorrectness(EditText ansText, TextView stats){
        String ans = ansText.getText().toString();
        int correct = 0;
        int total = maxIdx;
        try{
            for(int i = 0; i <= maxIdx && i < ans.length(); ++i){
                if(ans.charAt(i) == solutionText.charAt(i)){
                    correct++;
                    //ansText.setTextColor(r);
                }
            }
        }
        catch(Exception e){
        }
        String statText = "Correct: " + correct + "/" + total;
        stats.setText(statText);
    }
}