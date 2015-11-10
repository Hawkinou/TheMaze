package com.example.clement.themaze;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.main_activity)
public class MainActivity extends Activity {
    public String EXTRA_INTERACTION_DEPLACEMENT = "interactionDep";
    public String EXTRA_INTERACTION_SWITCH_VIEW = "interactionDep2";
    @ViewById
    RadioGroup deplacement;
    @ViewById(R.id.accelerometre)
    RadioButton deplacementResult;

    @ViewById
    RadioGroup changeView;
    @ViewById(R.id.button)
    RadioButton changeViewResult;

    @Click
    public void launchGame(){
        int selectedId = deplacement.getCheckedRadioButtonId();
        deplacementResult = (RadioButton) findViewById(selectedId);
         selectedId = changeView.getCheckedRadioButtonId();
        changeViewResult = (RadioButton) findViewById(selectedId);
        Intent intent = new Intent(MainActivity.this, TheMaze_.class);
        intent.putExtra(EXTRA_INTERACTION_DEPLACEMENT, deplacementResult.getText());
        intent.putExtra(EXTRA_INTERACTION_SWITCH_VIEW, changeViewResult.getText());
        startActivity(intent);
    }

}
