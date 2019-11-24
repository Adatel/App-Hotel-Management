package amsi.dei.estg.ipleiria.app_adatel.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import amsi.dei.estg.ipleiria.app_adatel.R;
import amsi.dei.estg.ipleiria.app_adatel.vistas.LoginActivity;

public class RegistarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);

        //retira a status bar
        getSupportActionBar().hide();

    }

    public void onClickVoltar(View v) {
        Intent intent = new Intent(this, LoginActivity.class);
        // Arranca para a LoginActivity
        startActivity(intent);
        finish();
    }
}
