package org.vivekthazhathattil.smaranka;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        EditText numDigitsText = getView().findViewById(R.id.digits_per_turn_text);
        EditText maxNumText = getView().findViewById(R.id.maximum_number_edit_text);
        EditText timeLimitText = getView().findViewById(R.id.time_interval_edit_text);

        TextView highScoreText = getView().findViewById(R.id.high_score_view);
        int highScore = PrefConfig.getHighScore(getContext());
        String highScoreStr = "High Score:  " + highScore;
        highScoreText.setText(highScoreStr);

        ImageButton randNumButton = getView().findViewById(R.id.rand_num_start_button);
        randNumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numDigits = 3, maxNum = 200;
                double timeLimit = 1.0;
                String numDigs = numDigitsText.getText().toString();
                if(!numDigs.equals("") && numDigs.matches("\\d+(?:\\.\\d+)?")){
                    numDigits = Integer.parseInt(numDigs);
                    if(numDigits > 20 || numDigits <=0){
                        numDigits = 3;
                    }
                }
                String maxNumStr = maxNumText.getText().toString();
                if(!maxNumStr.equals("") && maxNumStr.matches("\\d+(?:\\.\\d+)?")){
                    maxNum = Integer.valueOf(maxNumStr);
                    if(maxNum > Integer.MAX_VALUE || maxNum < 2){
                        maxNum = 200;
                    }
                }

                String timeStr = timeLimitText.getText().toString();
                if(!timeStr.equals("") && timeStr.matches("\\d+(?:\\.\\d+)?")){
                    timeLimit = Double.valueOf(timeStr);
                    if(timeLimit > Double.MAX_VALUE || maxNum < 0.2){
                        timeLimit = 1.0;
                    }
                }
                ((MainActivity)getActivity()).switchToInGameFragment(numDigits, maxNum, timeLimit);
            }
        });
    }
}