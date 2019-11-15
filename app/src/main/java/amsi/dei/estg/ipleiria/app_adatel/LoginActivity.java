package amsi.dei.estg.ipleiria.app_adatel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //retira a status bar
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }
}
