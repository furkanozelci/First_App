package com.ahmet1.myapplication;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewTitle, textViewWord, puanTextView;
    private Button buttonOption1, buttonOption2, buttonOption3, buttonNext;
    private ProgressBar progressBar;

    private LinkedList<QuestionNode> questionList;
    private QuestionNode currentQuestionNode;
    private int puan = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML öğelerini bağlama
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewWord = findViewById(R.id.textViewWord);
        buttonOption1 = findViewById(R.id.buttonOption1);
        buttonOption2 = findViewById(R.id.buttonOption2);
        buttonOption3 = findViewById(R.id.buttonOption3);
        buttonNext = findViewById(R.id.buttonNext);
        puanTextView = findViewById(R.id.puanTextView);
        progressBar = findViewById(R.id.progressBar);

        // Soru listesini hazırla
        prepareQuestions();

        // Başlangıçta ilk soruyu göster
        showQuestion();


        buttonOption1.setOnClickListener(this);
        buttonOption2.setOnClickListener(this);
        buttonOption3.setOnClickListener(this);
        buttonNext.setOnClickListener(this);

        // Puanı gösteren TextView'i güncelle
        updatePuanTextView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonNext) {
            showNextQuestion();
        } else if (view.getId() == R.id.buttonOption1 ||
                view.getId() == R.id.buttonOption2 ||
                view.getId() == R.id.buttonOption3) {
            checkAnswer(((Button) view).getText().toString());
        }
    }

    private void showQuestion() {
        // Belirtilen soruyu göster
        textViewWord.setText(currentQuestionNode.getQuestion().getEnglishWord());

        List<String> options = currentQuestionNode.getQuestion().getOptions();

        buttonOption1.setText(options.get(0));
        buttonOption2.setText(options.get(1));
        buttonOption3.setText(options.get(2));
    }

    private void prepareQuestions() {
        questionList = new LinkedList<>();

        questionList.add(new QuestionNode(new Question("Apple", "Elma", "Armut", "Çilek")));
        questionList.add(new QuestionNode(new Question("Banana", "Muz", "Kivi", "Portakal")));
        questionList.add(new QuestionNode(new Question("Grapes", "Üzüm", "Kiraz", "Erik")));
        questionList.add(new QuestionNode(new Question("Orange", "Portakal", "Mango", "Çilek")));
        questionList.add(new QuestionNode(new Question("Pear", "Armut", "Elma", "Üzüm")));
        questionList.add(new QuestionNode(new Question("Pineapple", "Ananas", "Mango", "Kivi")));
        questionList.add(new QuestionNode(new Question("Cherry", "Kiraz", "Üzüm", "Çilek")));
        questionList.add(new QuestionNode(new Question("Watermelon", "Karpuz", "Kavun", "Çilek")));
        questionList.add(new QuestionNode(new Question("Strawberry", "Çilek", "Muz", "Ahududu")));
        questionList.add(new QuestionNode(new Question("Kiwi", "Kivi", "Mango", "Elma")));
        questionList.add(new QuestionNode(new Question("Mango", "Mango", "Elma", "Üzüm")));
        questionList.add(new QuestionNode(new Question("Blueberry", "Yaban Mersini", "Böğürtlen", "Ahududu")));
        questionList.add(new QuestionNode(new Question("Apricot", "Kayısı", "Şeftali", "Erik")));
        questionList.add(new QuestionNode(new Question("Peach", "Şeftali", "Kayısı", "Üzüm")));
        questionList.add(new QuestionNode(new Question("Plum", "Erik", "Şeftali", "Kayısı")));
        questionList.add(new QuestionNode(new Question("Raspberry", "Ahududu", "Böğürtlen", "Yaban Mersini")));
        questionList.add(new QuestionNode(new Question("Cranberry", "Yaban Mersini", "Ahududu", "Böğürtlen")));
        questionList.add(new QuestionNode(new Question("Melon", "Kavun", "Karpuz", "Çilek")));
        questionList.add(new QuestionNode(new Question("Blackberry", "Böğürtlen", "Yaban Mersini", "Ahududu")));
        questionList.add(new QuestionNode(new Question("Avocado", "Avokado", "Mango", "Üzüm")));
        questionList.add(new QuestionNode(new Question("Orange", "Portakal", "Mango", "Çilek")));
        questionList.add(new QuestionNode(new Question("Mango", "Mango", "Elma", "Üzüm")));
        questionList.add(new QuestionNode(new Question("Lion", "Aslan", "Kaplan", "Leopard")));
        questionList.add(new QuestionNode(new Question("Elephant", "Fil", "Zürafa", "Hippopotamus")));
        questionList.add(new QuestionNode(new Question("Giraffe", "Zürafa", "Fil", "Kaplumbağa")));
        questionList.add(new QuestionNode(new Question("Monkey", "Maymun", "Goril", "Zebra")));
        questionList.add(new QuestionNode(new Question("Tiger", "Kaplan", "Aslan", "Jaguar")));
        questionList.add(new QuestionNode(new Question("Horse", "At", "Eşek", "Zebra")));
        questionList.add(new QuestionNode(new Question("Dolphin", "Yunus", "Balina", "Köpekbalığı")));
        questionList.add(new QuestionNode(new Question("Kangaroo", "Kanguru", "Koala", "Tasmanya Canavarı")));
        questionList.add(new QuestionNode(new Question("Penguin", "Penguen", "Kuş", "Deniz Ayısı")));
        questionList.add(new QuestionNode(new Question("Crocodile", "Timsah", "Yılan", "Kertenkele")));
        questionList.add(new QuestionNode(new Question("Rabbit", "Tavşan", "Kaplumbağa", "Kanguru")));
        questionList.add(new QuestionNode(new Question("Gorilla", "Goril", "Maymun", "Şempanze")));
        questionList.add(new QuestionNode(new Question("Ostrich", "Deve Kuşu", "Kuş", "Baykuş")));
        questionList.add(new QuestionNode(new Question("Koala", "Koala", "Kanguru", "Fil")));
        questionList.add(new QuestionNode(new Question("Panda", "Panda", "Ayı", "Koala")));
        questionList.add(new QuestionNode(new Question("Wolf", "Kurt", "Ayı", "Tilki")));
        questionList.add(new QuestionNode(new Question("Parrot", "Papağan", "Kuş", "Karga")));
        questionList.add(new QuestionNode(new Question("Seagull", "Martı", "Leylek", "Turna")));
        questionList.add(new QuestionNode(new Question("Squirrel", "Sincap", "Tavşan", "Kaplumbağa")));
        questionList.add(new QuestionNode(new Question("Rhino", "Gergedan", "Fil", "Hippopotamus")));


        Collections.shuffle(questionList);
        currentQuestionNode = questionList.getFirst();

        for (int i = 0; i < questionList.size() - 1; i++) {
            questionList.get(i).setNextQuestionNode(questionList.get(i + 1));
        }
    }

    private void showNextQuestion() {
        currentQuestionNode = currentQuestionNode.getNextQuestionNode();

        if (currentQuestionNode == null) {
            // Tüm soruları tamamladıysa
            if (puan == 100) {
                // If the user reaches 100 points, show a custom dialog message
                showCustomDialog("Congratulations!", "You've reached 100 points! Well done!");
                // Wait for 6 seconds before navigating to the main menu
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navigateToMainMenu();
                    }
                }, 6000); // 6000 milliseconds = 6 seconds
            } else {

                Toast.makeText(this, "Tüm soruları tamamladınız! Puan: " + puan, Toast.LENGTH_SHORT).show();
                currentQuestionNode = questionList.getFirst();
                progressBar.setProgress(0);
                puan = 0;
                updatePuanTextView();

                // ProgressBar 100 olduğunda ana menüye dön
                if (progressBar.getProgress() == 100) {
                    navigateToMainMenu();
                }
            }
        } else {
            int progress = puan;
            progressBar.setProgress(progress);

            // Eğer progress 100 olduysa, istediğiniz işlemi gerçekleştirin
            if (progress == 100) {
                // Show a custom dialog message for reaching 100 points
                showCustomDialog("Congratulations!", "You've reached 100 points! Well done!");
                // Wait for 6 seconds before navigating to the main menu
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        navigateToMainMenu();
                    }
                }, 6000); // 6000 milliseconds = 6 seconds
            } else {
                showQuestion();
            }
        }
    }

    // Custom dialog message method
    private void showCustomDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }






    private void checkAnswer(String selectedOption) {
        if (selectedOption.equals(currentQuestionNode.getQuestion().getCorrectAnswer())) {
            puan += 10;
            Toast.makeText(this, "Doğru! Puan: " + puan, Toast.LENGTH_SHORT).show();
        } else {
            puan = Math.max(0, puan - 10); // Puan 0'ın altına düşmemesi için kontrol eklendi
            Toast.makeText(this, "Yanlış! Doğru cevap: " + currentQuestionNode.getQuestion().getCorrectAnswer() + ". 10 puan eksildi.", Toast.LENGTH_SHORT).show();
        }

        updatePuanTextView();
        showNextQuestion();
    }



    private void updatePuanTextView() {
        puanTextView.setText("Puan: " + puan);
    }

    private void navigateToMainMenu() {


        Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }
}

class QuestionNode {
    private Question question;
    private QuestionNode nextQuestionNode;

    public QuestionNode(Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }

    public QuestionNode getNextQuestionNode() {
        return nextQuestionNode;
    }

    public void setNextQuestionNode(QuestionNode nextQuestionNode) {
        this.nextQuestionNode = nextQuestionNode;
    }


}
