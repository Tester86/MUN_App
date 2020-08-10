package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class JoinUsActivity extends AppCompatActivity {

    private EditText fullName, email, motivation, munOtherSpecification;
    private CheckBox munImpact, munBilbao, royalRussel, other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_us);

        fullName = (EditText)findViewById(R.id.fullName);
        email = (EditText)findViewById(R.id.email);
        motivation = (EditText)findViewById(R.id.motivation);
        munOtherSpecification = (EditText)findViewById(R.id.munOtherSpecification);
        munImpact = (CheckBox)findViewById(R.id.munImpact);
        munBilbao = (CheckBox)findViewById(R.id.munBilbao);
        royalRussel = (CheckBox)findViewById(R.id.royalRussel);
        other = (CheckBox)findViewById(R.id.munOther);



    }

    public void toggleOtherEditText(View v){
        if(munOtherSpecification.getVisibility() == View.INVISIBLE){
            munOtherSpecification.setVisibility(View.VISIBLE);
        } else{
            munOtherSpecification.setVisibility(View.INVISIBLE);
        }
    }

    public void Submit(View v){

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
        }


    }

    private boolean sendMail(){

        boolean emailSentSuccess = false;

        String otherMUNspecification = munOtherSpecification.getText().toString();

        String conferencesAttended = "";
        if(munImpact.isChecked()) conferencesAttended += "MUN Impact";
        if(munBilbao.isChecked()) conferencesAttended += ", MUN Bilbao";
        if(royalRussel.isChecked()) conferencesAttended += ", Royal Russel";
        if(!otherMUNspecification.equals("")){
            if(conferencesAttended.equals("")){
                conferencesAttended += otherMUNspecification;
            } else conferencesAttended += ", " + otherMUNspecification;
        }

        String mail = SensitiveInfo.EMAIL;
        String subject = "Job Application";
        String message = "Full name: " + fullName.getText().toString()
                + "\n\nEmail: " + email.getText().toString()
                + "\n\nLetter: " + motivation.getText().toString()
                + "\n\nConferences attended: " + conferencesAttended;


        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, subject, message);

        javaMailAPI.execute();

        emailSentSuccess = true;

        return emailSentSuccess;
    }
}
