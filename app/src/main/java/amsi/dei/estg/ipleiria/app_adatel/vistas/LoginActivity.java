package amsi.dei.estg.ipleiria.app_adatel.vistas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import amsi.dei.estg.ipleiria.app_adatel.MainActivity;
import amsi.dei.estg.ipleiria.app_adatel.R;
import amsi.dei.estg.ipleiria.app_adatel.models.HotelBDHelper;
import amsi.dei.estg.ipleiria.app_adatel.models.SingletonGestaoHotel;
import amsi.dei.estg.ipleiria.app_adatel.utils.UserJsonParser;

public class LoginActivity extends AppCompatActivity {


    private EditText editTextEmail;
    private EditText editTextPassword;
    private SharedPreferences sharedPreferences;


    TextView registar;

    HotelBDHelper hotelBDHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        //retira a status bar
        getSupportActionBar().hide();

        registar = findViewById(R.id.tvLinktoRegistar);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        String email = editTextEmail.getText().toString();



        sharedPreferences = getApplicationContext().getSharedPreferences("Preferences", 0);
        String login = sharedPreferences.getString("LOGIN", null);
        System.out.println("--> " + login);

        if(login != null) {
            // Arranca para a MainActivity
            // Intent que permite a passagem de parametros (email)
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.CHAVE_EMAIL, login);
            startActivity(intent);
        }

    }

    public void onClick(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
        SingletonGestaoHotel.getInstance(getApplicationContext()).getAllUsersAPI(getApplicationContext(), UserJsonParser.isConnectionEthernet(getApplicationContext()));
        startActivity(browserIntent);
    }

    public void onClickLogin(View view) {

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();



        // Se o email não for válido
        if(!isEmailValido(email)){
            editTextEmail.setError(getString(R.string.email_invalido));
            return;
        }

        // Se a password nao for válida
        if(!isPasswordValida(password)){
            editTextPassword.setError(getString(R.string.password_invalida));
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LOGIN", email);
        editor.commit();

        // Fecha o LoginActivity
        // Intent que permite a passagem de parametros (email)
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.CHAVE_EMAIL, email);
        startActivity(intent);
    }


    private boolean isEmailValido(String email) {
        if(email == null){
            return false;
        }
        // Só devolve verdade se for um email válido
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    private boolean isPasswordValida(String password) {
        if(password == null){
            return false;
        }
        return password.length() > 4;
    }

}
