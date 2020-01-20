package amsi.dei.estg.ipleiria.app_adatel.vistas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import amsi.dei.estg.ipleiria.app_adatel.MainActivity;
import amsi.dei.estg.ipleiria.app_adatel.R;
import amsi.dei.estg.ipleiria.app_adatel.models.HotelBDHelper;
import amsi.dei.estg.ipleiria.app_adatel.models.SingletonGestaoHotel;
import amsi.dei.estg.ipleiria.app_adatel.utils.ReservaJsonParser;

public class LoginActivity extends AppCompatActivity {


    private EditText editTextUser;
    private EditText editTextPassword;
    private SharedPreferences sharedPreferences;
    Context context = this;


    TextView registar;

    HotelBDHelper hotelBDHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        //retira a status bar
        getSupportActionBar().hide();

        registar = findViewById(R.id.tvLinktoRegistar);
        editTextUser = findViewById(R.id.editTextUser);
        editTextPassword = findViewById(R.id.editTextPassword);
        String user = editTextUser.getText().toString();



        sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);
        String login = sharedPreferences.getString("LOGIN", null);
       // System.out.println("--> LoginPreferences " + login);
    }

    public void onClick(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        startActivity(browserIntent);
    }

    public void onClickLogin(View view) {

        String user = String.valueOf(editTextUser.getText());
        String password = String.valueOf(editTextPassword.getText());

        // Se a password nao for válida
        if (!isPasswordValida(password)) {
            editTextPassword.setError(getString(R.string.password_invalida));
            return;
        }

        String recebido = SingletonGestaoHotel.getInstance(context).getUsersAPI(context,
                ReservaJsonParser.isConnectionInternet(context), user, password);


        System.out.println("--> Recebido: " + recebido);

        if(!(recebido == null)){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.CHAVE_EMAIL, user);
            startActivity(intent);
        }
    }


    private boolean isPasswordValida(String password) {
        if(password == null){
            return false;
        }
        return password.length() > 4;
    }

}
