package amsi.dei.estg.ipleiria.app_adatel.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import amsi.dei.estg.ipleiria.app_adatel.R;

public class LoginActivity extends AppCompatActivity {

    TextView registar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //retira a status bar
        getSupportActionBar().hide();

        registar = findViewById(R.id.tvLinktoRegistar);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, RegistarActivity.class);
        startActivity(intent);
    }
}
