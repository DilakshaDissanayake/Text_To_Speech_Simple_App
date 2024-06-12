// Programmer:  Dilaksha Dissanayake
// Final  Edit Date :  6/13/2023
// application: Text To Speech App

package com.example.texttospeechapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private EditText typeText;
    private TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        typeText = findViewById(R.id.TypeText);
        Button btnClickHere = findViewById(R.id.btnClickHere);


        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(new Locale("en", "US"));
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(MainActivity.this, "Language Not Supported", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });

        btnClickHere.setOnClickListener(v -> {
            String text = typeText.getText().toString();
            Toast.makeText(MainActivity.this, "Please wait..", Toast.LENGTH_SHORT).show();
            speak(text);
        });
    }

    private void speak(String text) {
        if (textToSpeech != null && !text.isEmpty()) {
            Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            typeText.setText("");
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

}
