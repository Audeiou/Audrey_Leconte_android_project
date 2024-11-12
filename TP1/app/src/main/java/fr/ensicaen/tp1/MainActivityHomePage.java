package fr.ensicaen.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityHomePage extends AppCompatActivity {

    private Button debut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home_page);
        debut = findViewById(R.id.buttonDebut);
        debut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityHomePage.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}