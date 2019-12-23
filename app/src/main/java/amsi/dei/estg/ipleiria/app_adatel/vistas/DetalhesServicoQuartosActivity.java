package amsi.dei.estg.ipleiria.app_adatel.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import amsi.dei.estg.ipleiria.app_adatel.R;

public class DetalhesServicoQuartosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_servico_quartos);

        Spinner spinner;
        spinner = (Spinner) findViewById(R.id.spCategoria);
        java.util.ArrayList<String> strings = new java.util.ArrayList<>();
        strings.add("Pequeno almoço");
        strings.add("Almoço");
        strings.add("Jantar");
        strings.add("Bebidas");
    }
}

