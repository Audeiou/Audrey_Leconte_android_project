package fr.ensicaen.tp1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivityQuiz extends Activity {

    private final String url = "https://quizzapi.jomoreschi.fr/api/v1/quiz?limit=5&category=culture_generale&difficulty=facile";
    private TextView question;
    private ListView answers;
    private String correctAnswer;
    private Button exitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);

        question = findViewById(R.id.question);
        answers = findViewById(R.id.answers);
        exitButton = findViewById(R.id.exitButton);

        exitButton.setOnClickListener(view -> finish());


        RequestQueue requestQueue = Volley.newRequestQueue(MainActivityQuiz.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {
                JSONArray quizzes = response.getJSONArray("quizzes");

                JSONObject quizObj = quizzes.getJSONObject(0);
                String questionText = quizObj.getString("question");
                correctAnswer = quizObj.getString("answer");

                JSONArray badAnswersArray = quizObj.getJSONArray("badAnswers");
                ArrayList<String> answersList = new ArrayList<>();
                answersList.add(correctAnswer);

                for (int i = 0; i < badAnswersArray.length(); i++) {
                    answersList.add(badAnswersArray.getString(i));
                }

                Collections.shuffle(answersList);

                question.setText(questionText);

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivityQuiz.this, android.R.layout.simple_list_item_1, answersList);
                answers.setAdapter(adapter);

                answers.setOnItemClickListener((parent, view, position, id) -> {
                    String selectedAnswer = answersList.get(position);
                    if (selectedAnswer.equals(correctAnswer)) {
                        Toast.makeText(MainActivityQuiz.this, "Bonne réponse !", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivityQuiz.this, "Mauvaise réponse. La bonne réponse était : " + correctAnswer, Toast.LENGTH_LONG).show();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MainActivityQuiz.this, "Erreur dans le traitement des données", Toast.LENGTH_SHORT).show();
            }
        }, error -> Toast.makeText(MainActivityQuiz.this, error.getMessage(), Toast.LENGTH_SHORT).show());

        requestQueue.add(jsonObjectRequest);
    }
}