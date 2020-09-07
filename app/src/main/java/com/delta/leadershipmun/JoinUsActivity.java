package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class JoinUsActivity extends AppCompatActivity {

    private EditText fullName, email, motivation, munOtherSpecification;
    private CheckBox munImpact, munBilbao, royalRussel, other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_us);

        fullName = (EditText)findViewById(R.id.fullName);
        email = (EditText)findViewById(R.id.email);
        motivation = (EditText)findViewById(R.id.fldSuggestion);
        munOtherSpecification = (EditText)findViewById(R.id.munOtherSpecification);
        munImpact = (CheckBox)findViewById(R.id.munImpact);
        munBilbao = (CheckBox)findViewById(R.id.munBilbao);
        royalRussel = (CheckBox)findViewById(R.id.royalRussel);
        other = (CheckBox)findViewById(R.id.munOther);

        Intent intent = getIntent();
        String intentString = intent.getStringExtra("example");
        fullName.setText(intentString);

    }

    public void toggleOtherEditText(View v){
        if(munOtherSpecification.getVisibility() == View.INVISIBLE){
            munOtherSpecification.setVisibility(View.VISIBLE);
        } else{
            munOtherSpecification.setVisibility(View.INVISIBLE);
        }
    }

    public void SubmitJobApplication(View v){

        boolean formIsNotComplete = false;

        String [] fields = new String[]{fullName.getText().toString(),
        email.getText().toString(),
        motivation.getText().toString(),
        munOtherSpecification.getText().toString()};

        CheckBox [] munConferences = new CheckBox[]{munImpact, munBilbao, royalRussel, other};

        for(int i = 0; i < fields.length - 1; i++){
            if(fields[i].equals("")){
                formIsNotComplete = true;
                break;
            }
        }

        for(int i = 0; i < munConferences.length - 1; i++){
            if (munConferences[i].isChecked()) {
                formIsNotComplete = false;
                break;
            } else{
                formIsNotComplete = true;
            }

        }


        if(formIsNotComplete) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_LONG).show();
        } else{
            sendMail();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }

    private void sendMail(){

        String otherMUNspecification = munOtherSpecification.getText().toString();

        String conferencesAttended = "";
        if(munImpact.isChecked()) conferencesAttended += "\n- MUN Impact";
        if(munBilbao.isChecked()) conferencesAttended += "\n- MUN Bilbao";
        if(royalRussel.isChecked()) conferencesAttended += "\n- Royal Russel";
        if(!otherMUNspecification.equals("")){
            if(conferencesAttended.equals("")){
                conferencesAttended += otherMUNspecification;
            } else conferencesAttended += "\n- " + otherMUNspecification;
        }

        String mail = SensitiveInfo.EMAIL;
        String subject = "Job Application";
        String message = "Full name: " + fullName.getText().toString()
                + "\n\nEmail: " + email.getText().toString()
                + "\n\nLetter: " + motivation.getText().toString()
                + "\n\nConferences attended: " + conferencesAttended;


        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, subject, message);

        javaMailAPI.execute();
    }
}
