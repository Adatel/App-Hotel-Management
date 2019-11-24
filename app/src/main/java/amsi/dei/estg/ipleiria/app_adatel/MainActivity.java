package amsi.dei.estg.ipleiria.app_adatel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String CHAVE_EMAIL = "EMAIL";
    private String email;
    private NavigationView navigationView;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.str_navigation_drawer_open, R.string.str_navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        carregarCabecalho();
    }

    private void carregarCabecalho() {

        // Obtem o email por parametro
        email = getIntent().getStringExtra(CHAVE_EMAIL).toString();

        // Atraves do navigation view vai buscar a posição no header
        View view = navigationView.getHeaderView(0);
        // Procura pelo ID do email
        TextView textViewUser = view.findViewById(R.id.tvEmail);
        // Escreve o email na TextView
        textViewUser.setText(email);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_criarReserva:
                System.out.println("-->Nav Criar Reserva");
                break;
            case R.id.nav_estadoReservas:
                System.out.println("-->Nav Estado de Reservas");
                break;
            case R.id.nav_servicoQuartos:
                System.out.println("-->Nav Serviço de Quartos");
                break;
            case R.id.nav_classificacao:
                System.out.println("-->Nav Classificação");
                break;
            case R.id.nav_email:
                System.out.println("-->Nav Email");
                break;
            case R.id.nav_telemovel:
                System.out.println("-->Nav Telemovel");
                break;
            default:
                System.out.println("-->Nav Default");
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
