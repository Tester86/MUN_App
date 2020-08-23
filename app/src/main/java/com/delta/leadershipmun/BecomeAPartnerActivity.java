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

    private RadioButton ngoOption, businessOption, governmentalAgencyOption,
                    workshopOption, podcastOption, presentationOption,
                    oneTimePartnershipOption, longLastingConferenceOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_a_partner);

        // Form's EditText elements
        organizationName = (EditText)findViewById(R.id.organizationNamePartnership);
        directorName = (EditText)findViewById(R.id.directorNamePartnership);
        directorEmail = (EditText)findViewById(R.id.directorEmailPartnership);
        interestFromPartnership = (EditText)findViewById(R.id.interestFromPartnership);
        ourProfitFromPartnership = (EditText)findViewById(R.id.ourProfitFromPartnership);
        ideasForPartnership = (EditText)findViewById(R.id.ideasForPartnership);

        // Form's RadioButton elements
        ngoOption = (RadioButton) findViewById(R.id.ngoOption);
        businessOption = (RadioButton) findViewById(R.id.businessOption);
        governmentalAgencyOption = (RadioButton) findViewById(R.id.governmentalAgencyOption);
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

        RadioButton[] organizationType = {ngoOption, businessOption, governmentalAgencyOption};
        RadioButton[] partnershipType = {workshopOption, podcastOption, presentationOption};
        RadioButton[] partnershipDuration = {oneTimePartnershipOption, longLastingConferenceOption};

        for(int i = 0; i < 3; i++){
            if(organizationData[i].equals("") || profits[i].equals("")){
                formIsNotComplete = true;
                break;
            }
        }

        for(RadioButton rBtn : organizationType){
            if(rBtn.isChecked() && !formIsNotComplete){
                formIsNotComplete = false;
                break;
            } else formIsNotComplete = true;
        }

        for(RadioButton rBtn : partnershipType){
            if(rBtn.isChecked() && !formIsNotComplete){
                formIsNotComplete = false;
                break;
            } else formIsNotComplete = true;
        }

        for(RadioButton rBtn : partnershipDuration){
            if(rBtn.isChecked() && !formIsNotComplete){
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

        String message = "====== Miscellaneous Data ======"
                + "\n\nOrganization Name: " + organizationName.getText().toString()
                + "\nDirector's Name: " + directorName.getText().toString()
                + "\nDirector's Email: " + directorEmail.getText().toString()

                + "\n\n====== Info about the Entity ======"

                + "\n\nEntity Type: " + entityType
                + "\nPartnership type: " + partnershipType
                + "\nPartnership Duration: " + partnershipDuration

                + "\n\n====== Detailed Info ======"

                + "\n\n        1. Why they want to partner with us:\n\n" + interestFromPartnership.getText().toString()
                + "\n\n        2. Our possible benefit from this partnership:\n\n" + ourProfitFromPartnership.getText().toString()
                + "\n\n        3. Ideas for the partnership:\n\n" + ideasForPartnership.getText().toString();

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, subject, message);
        javaMailAPI.execute();

    }
}
