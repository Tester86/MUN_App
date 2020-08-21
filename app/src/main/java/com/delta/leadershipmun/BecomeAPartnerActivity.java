package com.delta.leadershipmun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BecomeAPartnerActivity extends AppCompatActivity {

    private EditText organizationName, directorName, directorEmail, interestFromPartnership,
                    ourProfitFromPartnership, ideasForPartnership;

    private RadioButton ngoOption, businessOption, governamentalAgencyOption,
                    workshopOption, podcastOption, presentationOption,
                    oneTimePartnershipOption, longLastingConferenceOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_a_partner);

        // Form's EditText elements
        organizationName = (EditText)findViewById(R.id.organizationName);
        directorName = (EditText)findViewById(R.id.directorName);
        directorEmail = (EditText)findViewById(R.id.directorEmail);
        interestFromPartnership = (EditText)findViewById(R.id.interestFromPartnership);
        ourProfitFromPartnership = (EditText)findViewById(R.id.ourProfitFromPartnership);
        ideasForPartnership = (EditText)findViewById(R.id.ideasForPartnership);

        // Form's RadioButton elements
        ngoOption = (RadioButton) findViewById(R.id.ngoOption);
        businessOption = (RadioButton) findViewById(R.id.businessOption);
        governamentalAgencyOption = (RadioButton) findViewById(R.id.governamentalAgencyOption);
        workshopOption = (RadioButton) findViewById(R.id.workshopOption);
        podcastOption = (RadioButton) findViewById(R.id.podcastOption);
        presentationOption = (RadioButton) findViewById(R.id.presentationOption);
        oneTimePartnershipOption = (RadioButton) findViewById(R.id.oneTimePartnershipOption);
        longLastingConferenceOption = (RadioButton) findViewById(R.id.longLastingConferenceOption);


    }

   public void SubmitPartnershipRequest(View v){

        boolean formIsNotComplete = false;

        String[] organizationData = {organizationName.getText().toString(),
                                    directorName.getText().toString(),
                                    directorEmail.getText().toString()};

        String[] profits = {interestFromPartnership.getText().toString(),
                            ourProfitFromPartnership.getText().toString(),
                            ideasForPartnership.getText().toString()};

        RadioButton[] organizationType = {ngoOption, businessOption, governamentalAgencyOption};
        RadioButton[] partnershipType = {workshopOption, podcastOption, presentationOption};
        RadioButton[] partnershipDuration = {oneTimePartnershipOption, longLastingConferenceOption};

        for(int i = 0; i < 3; i++){
            if(organizationData[i] == "" || profits[i] == ""){
                formIsNotComplete = true;
                break;
            }
        }

        for(RadioButton rBtn : organizationType){
            if(rBtn.isChecked()){
                formIsNotComplete = false;
                break;
            } else formIsNotComplete = true;
        }

        for(RadioButton rBtn : partnershipType){
            if(rBtn.isChecked()){
                formIsNotComplete = false;
                break;
            } else formIsNotComplete = true;
        }

        for(RadioButton rBtn : partnershipDuration){
            if(rBtn.isChecked()){
                formIsNotComplete = false;
                break;
            } else formIsNotComplete = true;
        }

        if(formIsNotComplete){
            Toast.makeText(this, "All fields must be filled", Toast.LENGTH_LONG).show();
        } else {
            sendMail();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    private void sendMail(){

        String entityType, partnershipType, partnershipDuration;

        if(ngoOption.isChecked()) entityType = "NGO";
        else if(businessOption.isChecked()) entityType = "Business";
        else entityType = "Governmental Agency";

        if(workshopOption.isChecked()) partnershipType = "Workshop";
        else if(podcastOption.isChecked()) partnershipType = "Podcast";
        else partnershipType = "Presentation";

        if(oneTimePartnershipOption.isChecked()) partnershipDuration = "One time partnership";
        else partnershipDuration = "Long lasting partnership";

        String subject = "Partnership Request";
        String mail = SensitiveInfo.EMAIL;

        String message = "Organization Name: " + organizationName.getText().toString()
                + "\nDirector's Name: " + directorName.getText().toString()
                + "\nDirector's Email: " + directorEmail.getText().toString()

                + "\n\nEntity Type: " + entityType
                + "\nPartnership type: " + partnershipType
                + "\nPartnership Duration: " + partnershipDuration

                + "\n\nWhy do you want to partner with us?: " + interestFromPartnership.getText().toString()
                + "\nWhat can we benefit from this partnership? " + ourProfitFromPartnership.getText().toString()
                + "\nIdeas for the partnership: " + ideasForPartnership.getText().toString();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, subject, message);
        javaMailAPI.execute();

        Toast.makeText(this, "Submitted", Toast.LENGTH_LONG).show();

    }
}
