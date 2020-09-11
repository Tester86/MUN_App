package com.delta.leadershipmun;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class BecomeAnAssociateActivity extends AppCompatActivity {

    private EditText organizationsName, directorsName, directorsEmail, goalOfOrganization,
                    targetAudienceOther, interestFromAssociation,
                    ourBenefitFromAssociation, ideasForAssociation, platformOther,
                    reasonsForAssociation;

    private String _organizationsName, _directorsName, _directorsEmail, _goalOfOrganization,
            _targetAudienceOther, _interestFromAssociation,
            _ourBenefitFromAssociation, _ideasForAssociation, _platformOther,
            _reasonsForAssociation;

    private RadioGroup targetAudienceRadioGroup;

    private RadioButton exampleTargetAudience1Option, exampleTargetAudience2Option,
                        exampleTargetAudience3Option, exampleTargetAudienceOtherOption,
                        ngoOption, businessOption, governmentalAgencyOption,
                        partnerForWorkshopOption, partnerForPodcastOption, partnerForPresentationOption,
                        longLastingPartnershipOption, oneTimePartnershipOption;

    private CheckBox examplePlatform1Option, examplePlatform2Option, examplePlatform3Option,
                    examplePlatformOtherOption;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_become_an_associate);

        // Edit Text
        organizationsName = (EditText)findViewById(R.id.organizationNameAssociation);
        directorsName = (EditText)findViewById(R.id.directorNameAssociation);
        directorsEmail = (EditText)findViewById(R.id.directorEmailAssociation);

        goalOfOrganization = (EditText)findViewById(R.id.goalOfOrganization);

        targetAudienceOther = (EditText)findViewById(R.id.targetAudienceOther);

        interestFromAssociation = (EditText)findViewById(R.id.interestFromAssociation);
        ourBenefitFromAssociation = (EditText)findViewById(R.id.ourBenefitFromAssociation);
        ideasForAssociation = (EditText)findViewById(R.id.ideasForAssociation);

        platformOther = (EditText)findViewById(R.id.platformOther);

        reasonsForAssociation = (EditText)findViewById(R.id.reasonsForAssociation);

        // RadioGroup
        targetAudienceRadioGroup = (RadioGroup)findViewById(R.id.targetAudienceRadioGroup);

        // RadioButton
        exampleTargetAudience1Option = (RadioButton)findViewById(R.id.exampleTargetAudience1Option);
        exampleTargetAudience2Option = (RadioButton)findViewById(R.id.exampleTargetAudience2Option);
        exampleTargetAudience3Option = (RadioButton)findViewById(R.id.exampleTargetAudience3Option);
        exampleTargetAudienceOtherOption = (RadioButton)findViewById(R.id.exampleTargetAudienceOtherOption);

        ngoOption = (RadioButton)findViewById(R.id.ngoOption);
        businessOption = (RadioButton)findViewById(R.id.businessOption);
        governmentalAgencyOption = (RadioButton)findViewById(R.id.governmentalAgencyOption);

        partnerForWorkshopOption = (RadioButton)findViewById(R.id.partnerForWorkshopOption);
        partnerForPodcastOption = (RadioButton)findViewById(R.id.partnerForPodcastOption);
        partnerForPresentationOption = (RadioButton)findViewById(R.id.partnerForPresentationOption);

        longLastingPartnershipOption = (RadioButton)findViewById(R.id.longLastingPartnershipOption);
        oneTimePartnershipOption = (RadioButton)findViewById(R.id.oneTimePartnershipOption);

        examplePlatform1Option = (CheckBox)findViewById(R.id.examplePlatform1Option);
        examplePlatform2Option = (CheckBox)findViewById(R.id.examplePlatform2Option);
        examplePlatform3Option = (CheckBox)findViewById(R.id.examplePlatform3Option);
        examplePlatformOtherOption = (CheckBox)findViewById(R.id.examplePlatformOtherOption);


        examplePlatformOtherOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleOtherEditText();
            }
        });

        targetAudienceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.exampleTargetAudienceOtherOption) targetAudienceOther.setVisibility(View.VISIBLE);
                else targetAudienceOther.setVisibility(View.INVISIBLE);
            }
        });
    }

    private boolean formIsComplete(){

        _organizationsName = organizationsName.getText().toString();
        _directorsName = directorsName.getText().toString();
        _directorsEmail = directorsEmail.getText().toString();

        _goalOfOrganization = goalOfOrganization.getText().toString();

        _targetAudienceOther = targetAudienceOther.getText().toString();

        _interestFromAssociation = interestFromAssociation.getText().toString();
        _ourBenefitFromAssociation = ourBenefitFromAssociation.getText().toString();
        _ideasForAssociation = ideasForAssociation.getText().toString();

        _platformOther = platformOther.getText().toString();

        _reasonsForAssociation = reasonsForAssociation.getText().toString();

        if(_organizationsName.equals("") || _directorsName.equals("") || _directorsEmail.equals("")) return false;

        if(_goalOfOrganization.equals("")) return false;

        if(_interestFromAssociation.equals("") || _ourBenefitFromAssociation.equals("") || _ideasForAssociation.equals("")) return false;

        if(_reasonsForAssociation.equals("")) return false;

        if(!(exampleTargetAudience1Option.isChecked() || exampleTargetAudience2Option.isChecked()
            || exampleTargetAudience3Option.isChecked() || (exampleTargetAudienceOtherOption.isChecked() && !targetAudienceOther.getText().toString().equals("")))) return false;

        if(!(ngoOption.isChecked() || businessOption.isChecked() || governmentalAgencyOption.isChecked())) return false;

        if(!(partnerForWorkshopOption.isChecked() || partnerForPodcastOption.isChecked() || partnerForPresentationOption.isChecked())) return false;

        if(!(longLastingPartnershipOption.isChecked() || oneTimePartnershipOption.isChecked())) return false;

        if(!(examplePlatform1Option.isChecked() || examplePlatform2Option.isChecked()
            || examplePlatform3Option.isChecked() || (examplePlatformOtherOption.isChecked() && !platformOther.getText().toString().equals("")))) return false;

        return true;

    }

    public void submitAssociationRequest(View v){

        if(formIsComplete()) sendMail();
        else Toast.makeText(getApplicationContext(), "All fields must be filled", Toast.LENGTH_LONG).show();

    }

    private void sendMail(){

        String finalTargetAudience, finalEntityType, finalAspectToPartnerIn, finalAssociationDuration;
        String finalPlatforms = "";

        if(exampleTargetAudience1Option.isChecked()) finalTargetAudience = "Example 1";
        else if(exampleTargetAudience2Option.isChecked()) finalTargetAudience = "Example 2";
        else if(exampleTargetAudience3Option.isChecked()) finalTargetAudience = "Example 3";
        else finalTargetAudience = _targetAudienceOther;

        if(ngoOption.isChecked()) finalEntityType = "NGO";
        else if(businessOption.isChecked()) finalEntityType = "Business";
        else finalEntityType = "Governmental Agency";

        if(partnerForWorkshopOption.isChecked()) finalAspectToPartnerIn = "Workshop";
        else if(partnerForPodcastOption.isChecked()) finalAspectToPartnerIn = "Podcast";
        else finalAspectToPartnerIn = "Presentation";

        if(examplePlatform1Option.isChecked()) finalPlatforms = "   \n- Example 1";
        if(examplePlatform2Option.isChecked()) finalPlatforms += "   \n- Example 2";
        if(examplePlatform3Option.isChecked()) finalPlatforms += "   \n- Example 3";
        if (examplePlatformOtherOption.isChecked()) finalPlatforms += "   \n- " + _platformOther;

        if(longLastingPartnershipOption.isChecked()) finalAssociationDuration = "Long lasting Association";
        else finalAssociationDuration = "One time Association";

        String mail = SensitiveInfo.EMAIL;
        String subject = "Association Request";

        String message = "====== Miscellaneous Data ======"
                + "\n\nOrganization Name: " + _organizationsName
                + "\nDirector's Name: " + _directorsName
                + "\nDirector's Email: " + _directorsEmail

                + "\n\n====== Info about the Entity ======"
                + "\n\nEntity Type: " + finalEntityType
                + "\nTarget Audience: " + finalTargetAudience
                + "\nPresent in the platforms: " + finalPlatforms
                + "\n\nGoal of organization:\n\n- " + _goalOfOrganization

                + "\n\n====== Interests ======"
                + "\n\nDuration of Association: " + finalAssociationDuration
                + "\n\nAspect to associate in: " + finalAspectToPartnerIn
                + "\n\nWhat they want from us:\n\n- " + _interestFromAssociation
                + "\n\nWhat we can get from this association:\n\n- " + _ourBenefitFromAssociation
                + "\n\nThe ideas they have for this association:\n\n- " + _ideasForAssociation
                + "\n\nReason why they want to associate with us:\n\n- " + _reasonsForAssociation;

        JavaMailAPI javaMailAPI = new JavaMailAPI(this, mail, subject, message);

        javaMailAPI.execute();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void toggleOtherEditText(){
        if(platformOther.getVisibility() == View.INVISIBLE){
            platformOther.setVisibility(View.VISIBLE);
        } else platformOther.setVisibility(View.INVISIBLE);
    }
}
