package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BecomeAnAssociateActivity extends AppCompatActivity {

    private EditText organizationName, directorName, directorEmail, goalOfOrganization,
                    targetAudienceOther, platformOther, detailedPastEvents,
                    interestFromAssociation;

    private RadioButton exampleTargetAudience1Option, exampleTargetAudience2Option,
                        exampleTargetAudience3Option, exampleTargetAudienceOtherOption,
                        examplePlatform1Option, examplePlatform2Option, examplePlatform3Option,
                        examplePlatformOtherOption;

    private RadioGroup targetAudienceRadioGroup, platformRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_an_associate);

        // Form's EditText elements
        organizationName = (EditText)findViewById(R.id.organizationNameAssociation);
        directorName = (EditText)findViewById(R.id.directorNameAssociation);
        directorEmail = (EditText)findViewById(R.id.directorEmailAssociation);
        goalOfOrganization = (EditText)findViewById(R.id.goalOfOrganization);
        targetAudienceOther = (EditText)findViewById(R.id.targetAudienceOther);
        platformOther = (EditText)findViewById(R.id.platformOther);
        detailedPastEvents = (EditText)findViewById(R.id.detailedPastEvents);
        interestFromAssociation = (EditText)findViewById(R.id.interestFromAssociation);

        // Form's RadioButton elements
        exampleTargetAudience1Option = (RadioButton)findViewById(R.id.exampleTargetAudience1Option);
        exampleTargetAudience2Option = (RadioButton)findViewById(R.id.exampleTargetAudience2Option);
        exampleTargetAudience3Option = (RadioButton)findViewById(R.id.exampleTargetAudience3Option);
        exampleTargetAudienceOtherOption = (RadioButton)findViewById(R.id.exampleTargetAudienceOtherOption);
        examplePlatform1Option = (RadioButton)findViewById(R.id.examplePlatform1Option);
        examplePlatform2Option = (RadioButton)findViewById(R.id.examplePlatform2Option);
        examplePlatform3Option = (RadioButton)findViewById(R.id.examplePlatform3Option);
        examplePlatformOtherOption = (RadioButton)findViewById(R.id.examplePlatformOtherOption);

        targetAudienceRadioGroup = (RadioGroup)findViewById(R.id.targetAudience);
        platformRadioGroup = (RadioGroup)findViewById(R.id.platform);

        targetAudienceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == exampleTargetAudienceOtherOption.getId()){
                    if(targetAudienceOther.getVisibility() == View.INVISIBLE) targetAudienceOther.setVisibility(View.VISIBLE);
                    else targetAudienceOther.setVisibility(View.VISIBLE);
                } else targetAudienceOther.setVisibility(View.INVISIBLE);
            }
        });

        platformRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == examplePlatformOtherOption.getId()){
                    if(platformOther.getVisibility() == View.INVISIBLE) platformOther.setVisibility(View.VISIBLE);
                    else platformOther.setVisibility(View.VISIBLE);
                } else platformOther.setVisibility(View.INVISIBLE);
            }
        });



    }

    public void SubmitAssociationRequest(View v){

        boolean formIsComplete = true;

        String _organizationName = organizationName.getText().toString();
        String _directorName = directorName.getText().toString();
        String _directorEmail = directorEmail.getText().toString();

        String _goalOfOrganisation = goalOfOrganization.getText().toString();

        String _targetAudienceOther = targetAudienceOther.getText().toString();
        String _platformOther = platformOther.getText().toString();

        String _detailedPastEvents = detailedPastEvents.getText().toString();
        String _interestFromAssociation = interestFromAssociation.getText().toString();

        RadioButton[] targetAudience = {exampleTargetAudience1Option, exampleTargetAudience2Option,
                                        exampleTargetAudience3Option, exampleTargetAudienceOtherOption};

        RadioButton[] organizationPLatform = {examplePlatform1Option, examplePlatform2Option,
                                            examplePlatform3Option, examplePlatformOtherOption};

        if(_organizationName.equals("") || _directorName.equals("") || _directorEmail.equals("") || _goalOfOrganisation.equals("") || _detailedPastEvents.equals("") || _interestFromAssociation.equals("")) {
            Log.e("ERROR: ", "Edit Text left blank");
            formIsComplete = false;
        }

        if(!(targetAudience[0].isChecked() || targetAudience[1].isChecked() || targetAudience[2].isChecked() || targetAudience[3].isChecked())){
            formIsComplete = false; Log.e("ERROR: ", "Target Audience left blank");
        }

        if(!(organizationPLatform[0].isChecked() || organizationPLatform[1].isChecked() || organizationPLatform[2].isChecked() || organizationPLatform[3].isChecked())){
            formIsComplete = false;
        }

        if(exampleTargetAudienceOtherOption.isChecked()){
            if(_targetAudienceOther.equals("")){
                formIsComplete = false; Log.e("ERROR: ", "targetAudienceOther blank");
            }

        }

        if(examplePlatformOtherOption.isChecked()){
            if(_platformOther.equals("")){
                formIsComplete = false; Log.e("ERROR: ", "platformOtherOption blank");
            }

        }

        if(formIsComplete){
            sendMail();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else Toast.makeText(this, "All fields must be filled", Toast.LENGTH_LONG).show();

    }

    private void sendMail(){

        String mail = SensitiveInfo.EMAIL;
        String subject = "Association Request";

        String finalTargetAudience;
        if(exampleTargetAudience1Option.isChecked()) finalTargetAudience = "Example 1 Target Audience";
        else if(exampleTargetAudience2Option.isChecked()) finalTargetAudience = "Example 2 Target Audience";
        else if(exampleTargetAudience3Option.isChecked()) finalTargetAudience = "Example 3 Target Audience";
        else finalTargetAudience = targetAudienceOther.getText().toString();

        String finalPlatform;
        if(examplePlatform1Option.isChecked()) finalPlatform = "Example 1 Platform";
        else if(examplePlatform2Option.isChecked()) finalPlatform = "Example 2 Platform";
        else if(examplePlatform3Option.isChecked()) finalPlatform = "Example 3 Platform";
        else finalPlatform = platformOther.getText().toString();

        String message = "====== Miscellaneous Data ======"
                + "\n\nOrganization Name: " + organizationName.getText().toString()
                + "\nDirector's Name: " + directorName.getText().toString()
                + "\nDirector's Email: " + directorEmail.getText().toString()

                + "\n\n====== Info about the Entity ======"
                + "\n\nTarget Audience: " + finalTargetAudience
                + "\nPresent in the following platforms: " + finalPlatform
                + "\n\nGoal of organization:\n\n- " + goalOfOrganization.getText().toString()
                + "\n\nPrevious events run by the organization (" + organizationName.getText().toString() + "): " + detailedPastEvents.getText().toString()
                + "\n\nWhy they want to partner with us:\n\n- " + interestFromAssociation.getText().toString();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, subject, message);
        javaMailAPI.execute();

    }
}
