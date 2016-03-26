package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown";
    public static final String CHEAT_STATE = "cheat_state";

    private boolean mAnswerIsTrue;
    private boolean mIsCheated;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue, boolean isCheated) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        i.putExtra(CHEAT_STATE, isCheated);
        return  i;
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        mIsCheated = isAnswerShown;
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    public static boolean wasAnswerShown(Intent result) {
        Log.i(CHEAT_STATE, "wasAnswerShown() called");
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        if (savedInstanceState != null)
            setAnswerShownResult(savedInstanceState.getBoolean(CHEAT_STATE, false));

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mIsCheated = getIntent().getBooleanExtra(CHEAT_STATE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mShowAnswer = (Button) findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mAnswerIsTrue) {
//                    mAnswerTextView.setText(R.string.true_button);
//                } else {
//                    mAnswerTextView.setText(R.string.false_button);
//                }
                mAnswerTextView.setText(mAnswerIsTrue ? R.string.true_button : R.string.false_button);
                setAnswerShownResult(true);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putBoolean(CHEAT_STATE, mIsCheated);
    }

}
