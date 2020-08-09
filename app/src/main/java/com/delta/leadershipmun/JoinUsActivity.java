package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
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
        motivation = (EditText)findViewById(R.id.motivation);
        munOtherSpecification = (EditText)findViewById(R.id.munOtherSpecification);
        munImpact = (CheckBox)findViewById(R.id.munImpact);
        munBilbao = (CheckBox)findViewById(R.id.munBilbao);
        royalRussel = (CheckBox)findViewById(R.id.royalRussel);
        other = (CheckBox)findViewById(R.id.munOther);



    }

    public void Submit(View v){

        boolean formIsNotComplete = false;

        String [] fields = new String[]{fullName.getText().toString(),
        email.getText().toString(),
        motivation.getText().toString(),
        munOtherSpecification.getText().toString()};

        CheckBox [] munConferences = new CheckBox[]{munImpact, munBilbao, royalRussel, other};

        for(int i = 0; i < fields.length - 1; i++){
            if(fields[i].equals("") || !munConferences[i].isChecked()){
                formIsNotComplete = true;
                break;
            }
        }
        if(formIsNotComplete) {
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_LONG).show();
        } else{
            // send email with data
        }


    }
}
