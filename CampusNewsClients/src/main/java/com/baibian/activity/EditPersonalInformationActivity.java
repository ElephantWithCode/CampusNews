package com.baibian.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputBinding;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.baibian.R;
import com.baibian.tool.BaseTools;
import com.baibian.tool.DataTools;
import com.baibian.tool.LinearLayout_Inflaterable;
import com.baibian.tool.RecyclerViewCommonTool.CommonAdapter;
import com.baibian.tool.RecyclerViewCommonTool.ViewHolder;
import com.baibian.tool.ToastTools;
import com.baibian.tool.UI_Tools;
import com.baibian.view.CompletableEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditPersonalInformationActivity extends AppCompatActivity implements View.OnClickListener{

    private String[] mGenders;
    private Toolbar mToolbar;
    private ImageView mArrowPortrait;
    private ImageView mArrowBirthDate;
    private TextView mBirthDateContent;
    private int mYear, mMonth, mDay;
    private LinearLayout mResidenceLayout;
    private ImageView mAddResidence;
    private PopupWindow window;

    private CompletableEditText mEditPhoneNumber;
    private CompletableEditText mEditEmailNumber;
    private CompletableEditText mEditSignature;
    private CompletableEditText mEditName;
    private CompletableEditText.OnCancelFocusListener mCancelListener;
    private List<String> mData;
    private TextView mGender;
    private ImageView mArrowGender;

    //    private ArrayAdapter<String> mArrayAdapter;
    //    private Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_information);

        initViews();

        initCalender();
        mCancelListener = new CompletableEditText.OnCancelFocusListener() {
            @Override
            public void cancelFocus(EditText editText) {
                InputMethodManager imm  = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null){
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                }
                editText.clearFocus();
            }
        };
        mData = new ArrayList<>();
        mArrowPortrait.setOnClickListener(this);
        mArrowBirthDate.setOnClickListener(this);
        mArrowGender.setOnClickListener(this);
        mEditName.setOnCancelFocusListener(mCancelListener);
        mEditSignature.setOnCancelFocusListener(mCancelListener);
        mEditEmailNumber.setOnCancelFocusListener(mCancelListener);
        mEditPhoneNumber.setOnCancelFocusListener(mCancelListener);
//        mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mGenders);
//        mArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//        mSpinner.setAdapter(mArrayAdapter);
//        mSpinner.setDropDownVerticalOffset(DataTools.dip2px(this, 50));
//        mSpinner.setDropDownHorizontalOffset(DataTools.dip2px(this, -150));
//        mSpinner.setDropDownWidth(DataTools.dip2px(this, 70));
        initToolbar();

        UI_Tools ui_tools = new UI_Tools();
        ui_tools.CancelFocusOne(EditPersonalInformationActivity.this, (LinearLayout)findViewById(R.id.portrait_and_signature), mEditSignature);
        ui_tools.CancelFocusOne(EditPersonalInformationActivity.this, (LinearLayout)findViewById(R.id.nick_name_genders_and_birth_date),mEditName);

    }

    private void initCalender(){
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
    }
    private void initViews() {

        mBirthDateContent = (TextView) findViewById(R.id.birth_date_content);
        mArrowBirthDate = (ImageView) findViewById(R.id.birth_date_arrow);
        mEditEmailNumber = (CompletableEditText) findViewById(R.id.email_number_content);
        mEditPhoneNumber = (CompletableEditText) findViewById(R.id.phone_number_content);
        mArrowPortrait = (ImageView) findViewById(R.id.portrait_arrow);
        mGenders = getResources().getStringArray(R.array.spinner_genders);
        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mEditName = (CompletableEditText) findViewById(R.id.nick_name_content);
        mEditSignature = (CompletableEditText) findViewById(R.id.signature_content);

        mGender = (TextView) findViewById(R.id.gender_select);
        mArrowGender = (ImageView) findViewById(R.id.gender_drop_down_arrow);

//        mSpinner = (Spinner) findViewById(R.id.gender_spinner);

    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Edit Personal Information");
        }
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mBirthDateContent.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.portrait_arrow:
                Intent intent = new Intent(EditPersonalInformationActivity.this, EditPortraitActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case R.id.birth_date_arrow:
                DatePickerDialog dialog = new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
                dialog.show();
                break;
            case R.id.gender_drop_down_arrow:

                ToastTools.ToastShow("TestPopup");
                View popupView = LayoutInflater.from(this).inflate(R.layout.personal_informartion_gender_popup_window, null);
                RecyclerView genderList = (RecyclerView) popupView.findViewById(R.id.gender_list);
                genderList.setLayoutManager(new LinearLayoutManager(this));
                genderList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
                genderList.setAdapter(new CommonAdapter<GenderContent.Gender>(this, R.layout.gender_item, GenderContent.GENDERS){
                    @Override
                    public void convert(ViewHolder holder, GenderContent.Gender gender) {
                        final TextView genderText = (TextView) holder.getItemView().findViewById(R.id.gender_text);

                        holder.getItemView().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                window.dismiss();
                                mGender.setText(genderText.getText());
                            }
                        });
                    }
                });
                window = new PopupWindow(popupView, 300, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
                window.setFocusable(true);
                window.setOutsideTouchable(true);
                window.update();
                window.showAsDropDown(mArrowGender, -200, 0);
        }
    }
    public static class GenderContent{

        public static final List<Gender> GENDERS = new ArrayList<>();
        private static int COUNT = 2;
        public static class Gender{
            private int genderSourceId;
            private String genderText;
            public Gender(int genderSourceId, String genderText){
                this.genderSourceId = genderSourceId;
                this.genderText = genderText;
            }
            public Gender(){}
        }
        public static void addGender(Gender gender){
            GENDERS.add(gender);
        }
        static {
            for (int i = 0; i < COUNT; i++){
                addGender(new Gender());
            }
        }
    }
}
