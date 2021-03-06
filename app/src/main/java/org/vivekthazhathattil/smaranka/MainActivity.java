package org.vivekthazhathattil.smaranka;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private IngameFragment ingameFragment;
    private RecallFragment recallFragment;
    private MainFragment mainFragment;
    public static final double DEFAULT_TIME_LIMIT = 1;
    public static final int DEFAULT_RIGHT_BOUND = 200;
    public static final int DEFAULT_MAX_NUM = 3;
    private static final int CONTENT_VIEW_ID = 10101010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout frame = new FrameLayout(this);
        frame.setId(CONTENT_VIEW_ID);

        // setContentView(R.layout.activity_main);
        setContentView(frame, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));

        mainFragment = new MainFragment();
        mainFragment.setArguments(getIntent().getExtras());
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(CONTENT_VIEW_ID, mainFragment);
        fragmentTransaction.commit();

        try {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }
        catch (NullPointerException ignored){
        }
    }

    public void switchToInGameFragment(int numDigits, int maxNum, double timeLimit){
        ingameFragment = new IngameFragment(numDigits, maxNum, timeLimit);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(mainFragment.getId(), ingameFragment);
        fragmentTransaction.commit();
    }

    public void switchToRecallFragment(String solutionText, int maxIdx, boolean highScoreWorthy){
        recallFragment = new RecallFragment(solutionText, maxIdx, highScoreWorthy);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(ingameFragment.getId(), recallFragment);
        fragmentTransaction.commit();
    }

    public void switchToMainFragment(){
        mainFragment = new MainFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(recallFragment.getId(), mainFragment);
        fragmentTransaction.commit();
    }
}