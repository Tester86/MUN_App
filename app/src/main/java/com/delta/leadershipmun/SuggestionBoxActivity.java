package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SuggestionBoxActivity extends AppCompatActivity {

    private EditText fldSuggestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_box);

        fldSuggestion = (EditText)findViewById(R.id.fldSuggestion);


    }

    public void SubmitSuggestion(View v){
        String suggestion = fldSuggestion.getText().toString();
        String mail = SensitiveInfo.EMAIL;
        String subject = "Suggestion";

        if(!suggestion.equals("")){
            JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, subject, suggestion);
            javaMailAPI.execute();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else Toast.makeText(this, "Suggestion field cannot be empty", Toast.LENGTH_LONG).show();
    }
}
