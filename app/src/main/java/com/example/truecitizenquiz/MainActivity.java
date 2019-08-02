package com.example.truecitizenquiz;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button falseButton;
    private Button trueButton;
    private TextView questionTextView;
    private ImageButton nextButton;
    private ImageButton prevButton;
    private int currentQuestionIndex=0;
    private Question[] questionbank= new Question[]
            {

      new Question(R.string.question_amendments,false),
      new Question(R.string.question_constitution,true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Question question=new Question(R.string.question_declaration,true);


    falseButton=findViewById(R.id.false_button);
    trueButton=findViewById(R.id.true_button);
    questionTextView=findViewById(R.id.answer_text_view);
    nextButton=findViewById(R.id.next_button);
    prevButton=findViewById(R.id.prev_button);


   falseButton.setOnClickListener(this);//register our buttons to listen
   trueButton.setOnClickListener(this);
    nextButton.setOnClickListener(this);
    prevButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.false_button:
                checkAnswer(false);
                //Toast.makeText(MainActivity.this,"False",Toast.LENGTH_SHORT).show();
                break;
            case R.id.true_button:
                checkAnswer(true);
                //Toast.makeText(MainActivity.this,"true",Toast.LENGTH_SHORT).show();

                break;
            case R.id.next_button:
            currentQuestionIndex=(currentQuestionIndex+1)%questionbank.length;

            updateQuestion();
            break;

            case R.id.prev_button:
               if(currentQuestionIndex>0){
                   currentQuestionIndex=(currentQuestionIndex-1)%questionbank.length;
                   updateQuestion();
               }

        }


    }
    private void updateQuestion(){
        Log.d("Current","onClick: "+ currentQuestionIndex);
        questionTextView.setText(questionbank[currentQuestionIndex].getAnswerResId());

    }

    private void checkAnswer(boolean userChooseCorrect) {
        boolean answerIsTrue = questionbank[currentQuestionIndex].isAnswerTrue();
        int toastMessageId;

        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
        } else {
            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(MainActivity.this, toastMessageId,
                Toast.LENGTH_SHORT)
                .show();

    }
}
