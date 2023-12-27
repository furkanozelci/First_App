package com.ahmet1.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.LinkedList;

public class NewPageActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private EditText editTextAnswer;
    private Button buttonCheckAnswer;
    private Button buttonNextQuestion;
    private TextView textViewScore;
    private ProgressBar progressBar;

    private LinkedList<FillBlankQuestion> questionList;
    private FillBlankQuestion currentQuestion;

    private int userScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_page);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        buttonCheckAnswer = findViewById(R.id.buttonCheckAnswer);
        buttonNextQuestion = findViewById(R.id.buttonNextQuestion);
        textViewScore = findViewById(R.id.puanTextView);
        progressBar = findViewById(R.id.progressBar);

        prepareQuestions();
        showCurrentQuestion();

        buttonCheckAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });

        buttonNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNextQuestion();
            }
        });
    }

    private void showCurrentQuestion() {
        textViewQuestion.setText(currentQuestion.getQuestionText());
        editTextAnswer.setText("");
        updatePuanTextView();
    }

    private void checkAnswer() {
        String userAnswer = editTextAnswer.getText().toString().trim().toLowerCase();
        String correctAnswer = currentQuestion.getCorrectAnswer();

        if (userAnswer.equals(correctAnswer)) {
            userScore += 10;
            updateProgressBar();
            Toast.makeText(this, "Doğru!", Toast.LENGTH_SHORT).show();
        } else {
            userScore -= 10;
            updateProgressBar();
            Toast.makeText(this, "Yanlış! Doğru Cevap: " + correctAnswer, Toast.LENGTH_SHORT).show();
        }

        showNextQuestion();
    }

    private void updateProgressBar() {

        int progress = Math.min(userScore, 100);


        progressBar.setProgress(progress);

        if (progress >= 100) {
            // Kullanıcı 100 puana ulaştığında veya geçtiğinde
            showCustomDialog();

            // Delay for 6 seconds before navigating to the main menu
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    navigateToMainMenu();
                }
            }, 6000); // 6000 milliseconds = 6 seconds
        }
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Congratulations!")
                .setMessage("You've reached 100 points! Well done!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Delay for 6 seconds before navigating to the main menu
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                navigateToMainMenu();
                            }
                        }, 6000); // 6000 milliseconds = 6 seconds
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }

    private void showNextQuestion() {
        currentQuestion = questionList.poll();

        if (currentQuestion == null) {
            Toast.makeText(this, "Tüm soruları tamamladınız! Toplam Puanınız: " + userScore, Toast.LENGTH_SHORT).show();


            progressBar.setProgress(100);

            // Ana menüye dön
            navigateToMainMenu();
        } else {
            // Sadece soru değiştirilir, ProgressBar güncellenmez
            showCurrentQuestion();
        }
    }

    private void prepareQuestions() {
        questionList = new LinkedList<>();
        questionList.add(new FillBlankQuestion("She ____ to the park every day. (gitmek)", "go"));
        questionList.add(new FillBlankQuestion("We ____ two dogs. (sahip olmak)", "have"));
        questionList.add(new FillBlankQuestion("I love ____ TV shows. (izlemek)", "watch"));
        questionList.add(new FillBlankQuestion("He ____ playing football. (zevk almak)", "enjoys"));
        questionList.add(new FillBlankQuestion("She ____ her homework every evening. (yapmak)", "does"));
        questionList.add(new FillBlankQuestion("The cat ____ on the windowsill. (olmak)", "is"));
        questionList.add(new FillBlankQuestion("We ____ our friends at the cafe. (buluşmak)", "meet"));
        questionList.add(new FillBlankQuestion("My mother ____ delicious cakes. (yapmak)", "bakes"));
        questionList.add(new FillBlankQuestion("She ____ a beautiful song. (söylemek)", "sings"));
        questionList.add(new FillBlankQuestion("I ____ my keys at home. (unutmak)", "forget"));
        questionList.add(new FillBlankQuestion("He ____ the tallest person in the room. (olmak)", "is"));
        questionList.add(new FillBlankQuestion("They ____ at the same school. (okumak)", "study"));
        questionList.add(new FillBlankQuestion("We ____ a new language. (öğrenmek)", "learn"));
        questionList.add(new FillBlankQuestion("She ____ her lunch at 12 o'clock. (yemek yemek)", "eats"));
        questionList.add(new FillBlankQuestion("I ____ my computer every day. (kullanmak)", "use"));
        questionList.add(new FillBlankQuestion("He ____ his coffee black. (içmek)", "drinks"));
        questionList.add(new FillBlankQuestion("The sun ____ in the east. (doğmak)", "rises"));
        questionList.add(new FillBlankQuestion("We ____ a new project. (başlamak)", "start"));
        questionList.add(new FillBlankQuestion("She ____ her room every week. (temizlemek)", "cleans"));
        questionList.add(new FillBlankQuestion("They ____ a lot of time together. (geçirmek)", "spend"));
        questionList.add(new FillBlankQuestion("I ____ my family every weekend. (görmek)", "see"));

        Collections.shuffle(questionList);

        currentQuestion = questionList.poll();
    }

    private void updatePuanTextView() {
        textViewScore.setText("Puanınız: " + userScore);
    }

    private void navigateToMainMenu() {
        Intent intent = new Intent(NewPageActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }



}


