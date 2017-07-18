package kelvinng2017.membermanage.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import kelvinng2017.membermanage.R;

/**
 * Created by shuhui on 2017/6/3.
 */

public class UsersActivity extends AppCompatActivity {

    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        textViewName=(TextView)findViewById(R.id.text1);
        String nameFromIntent=getIntent().getStringExtra("EMAIL");
        textViewName.setText("Welcome"+nameFromIntent);
    }
}
