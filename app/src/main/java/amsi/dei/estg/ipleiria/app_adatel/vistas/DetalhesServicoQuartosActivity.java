package amsi.dei.estg.ipleiria.app_adatel.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import amsi.dei.estg.ipleiria.app_adatel.R;

public class DetalhesServicoQuartosActivity extends AppCompatActivity {

    private int idServico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_servico_quartos);

        Spinner spinner;
        spinner = findViewById(R.id.spCategoria);
        java.util.ArrayList<String> strings = new java.util.ArrayList<>();
        strings.add("Pequeno almoço");
        strings.add("Almoço");
        strings.add("Jantar");
        strings.add("Bebidas");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, strings);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        Spinner spProduto;
        spProduto = findViewById(R.id.spProduto);
        java.util.ArrayList<String> produtos = new java.util.ArrayList<>();
        produtos.add("Bitoque");
        produtos.add("Água");
        produtos.add("Coca-Cola");
        produtos.add("Panquecas");
        produtos.add("Bacalhau com natas");


        ArrayAdapter<String> arrayAdapterProdutos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, produtos);
        arrayAdapterProdutos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spProduto.setAdapter(arrayAdapterProdutos);
        spProduto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

    }

}

