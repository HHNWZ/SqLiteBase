package kelvinng2017.membermanage.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import kelvinng2017.membermanage.helper.InputValidation;
import kelvinng2017.membermanage.model.User;
import kelvinng2017.membermanage.sql.DatabaseHelper;
import kelvinng2017.membermanage.R;

/**
 * Created by shuhui on 2017/6/3.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity=RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews(){
        nestedScrollView=(NestedScrollView)findViewById(R.id.nestedScrollView);

        textInputLayoutName=(TextInputLayout)findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail=(TextInputLayout)findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword=(TextInputLayout)findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword=(TextInputLayout)findViewById(R.id.textInputLayoutConfrimPassword);

        textInputEditTextName=(TextInputEditText)findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail=(TextInputEditText)findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword=(TextInputEditText)findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword=(TextInputEditText)findViewById(R.id.textInputEditTextConfrimPassword);

        appCompatButtonRegister=(AppCompatButton)findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink=(AppCompatTextView)findViewById(R.id.appCompatTextViewLoginLink);
    }

    private void initListeners(){
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }

    private void initObjects(){
        inputValidation =new InputValidation(activity);
        databaseHelper=new DatabaseHelper(activity);
        user=new User();
    }

    @Override
    public  void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    private void postDataToSQLite(){
        if(!inputValidation.isInputEditTextFiled(textInputEditTextName,textInputLayoutName,getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFiled(textInputEditTextEmail,textInputLayoutEmail,getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail,textInputLayoutEmail,getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextFiled(textInputEditTextPassword,textInputLayoutPassword,getString(R.string.error_message_password))){
            return;
        }
        if(!inputValidation.isInputEditTextMatches(textInputEditTextPassword,textInputEditTextConfirmPassword,textInputLayoutConfirmPassword,getString(R.string.error_password_match))){
            return;
        }
        if(!databaseHelper.checkUsers(textInputEditTextEmail.getText().toString().trim())){

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);
            Snackbar.make(nestedScrollView,getString(R.string.success_message),Snackbar.LENGTH_LONG).show();
            emptyInputEditText();
        }else{
            Snackbar.make(nestedScrollView,getString(R.string.error_email_exists),Snackbar.LENGTH_LONG).show();
        }
    }
    private void emptyInputEditText(){
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);

    }

}
