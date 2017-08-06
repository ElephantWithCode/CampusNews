package com.baibian.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.baibian.R;
import com.baibian.tool.BaseTools;
import com.baibian.tool.DataTools;

public class EditPersonalInformationActivity extends AppCompatActivity {

    private String[] mGenders;
    private ArrayAdapter<String> mArrayAdapter;
    private Spinner mSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_information);

        mGenders = getResources().getStringArray(R.array.spinner_genders);
        mSpinner = (Spinner) findViewById(R.id.gender_spinner);
        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mGenders);
        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mSpinner.setAdapter(mArrayAdapter);
        mSpinner.setDropDownVerticalOffset(DataTools.dip2px(this, 50));
        mSpinner.setDropDownHorizontalOffset(DataTools.dip2px(this, -150));
        mSpinner.setDropDownWidth(DataTools.dip2px(this, 70));
    }
}
