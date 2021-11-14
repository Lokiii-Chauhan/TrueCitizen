package com.lokiiichauhan.truecitizen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import com.lokiiichauhan.truecitizen.databinding.ActivityMainBinding;
import com.lokiiichauhan.truecitizen.model.Questions;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQueIndex = 0;
    private final Questions[] questionBank = new Questions[] {
            new Questions(R.string.question_amendments, false),
            new Questions(R.string.question_constitution, true),
            new Questions(R.string.question_declaration, true),
            new Questions(R.string.question_independence_rights, true),
            new Questions(R.string.question_religion, true),
            new Questions(R.string.question_government, false),
            new Questions(R.string.question_government_feds, false),
            new Questions(R.string.question_government_senators, true),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.questioinTextView.setText(questionBank[currentQueIndex].getAnswerResId());

        binding.nextButton.setOnClickListener(v -> {

            currentQueIndex = (currentQueIndex + 1) % questionBank.length;
            updateQuestion();

        });

        binding.prevButton.setOnClickListener(view -> {
            if (currentQueIndex > 0){
                currentQueIndex = (currentQueIndex - 1) % questionBank.length;
                updateQuestion();
            }
        });

        binding.trueButton.setOnClickListener(view -> checkAnswer(true));

        binding.falseButton.setOnClickListener(view -> checkAnswer(false));
    }

    private void checkAnswer(boolean userChoseCorrect){

        boolean answerIsCorrect = questionBank[currentQueIndex].isAnswerTrue();
        int messageid;

        if (answerIsCorrect == userChoseCorrect){
            messageid = R.string.correct_answer;
        }else {
            messageid = R.string.wrong_answer;
        }

        Snackbar.make(binding.imageView,messageid,Snackbar.LENGTH_SHORT).show();

    }

    private void updateQuestion() {
        binding.questioinTextView.setText(questionBank[currentQueIndex].getAnswerResId());
    }


}