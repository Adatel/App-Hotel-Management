package amsi.dei.estg.ipleiria.app_adatel.vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import amsi.dei.estg.ipleiria.app_adatel.R;
import amsi.dei.estg.ipleiria.app_adatel.models.Pedido;
import amsi.dei.estg.ipleiria.app_adatel.models.SingletonGestaoHotel;

public class DetalhesServicoQuartosActivity extends AppCompatActivity {


    public static final String CHAVE_IDPEDIDO = "idPedido";

    private int idPedido;
    private Pedido pedidoSelecionado;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_servico_quartos);

        fab = findViewById(R.id.fab);

        // Recebe o id do pedido como parâmentro e vai buscar o pedido ao SingletonGestaoHotel pelo id
        idPedido = getIntent().getIntExtra(CHAVE_IDPEDIDO,-1);


        //System.out.println("--> " + dataSaida);

        if(idPedido == -1){
            setTitle("Fazer Pedido");
            System.out.println("--> adicionar");
            fab.setImageResource(R.drawable.ic_adicionar);
        } else {
            mostrarPedido(idPedido);
            fab.setImageResource(R.drawable.ic_alterar);
        }

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

    private void mostrarPedido(int idPedido) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(idPedido != -1) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_detalhes_reserva, menu);
            return super.onCreateOptionsMenu(menu);
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.itemRemover){
            //Toast.makeText(this, "Remover", Toast.LENGTH_SHORT).show();
            dialogRemover();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void dialogRemover() {

        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(this);
        // Contruindo Alert Dialog
        // Título
        builder.setTitle("Cancelar Pedido")
                // Messagem
                .setMessage("Pretende mesmo cancelar o pedido?")
                // 2 Botões
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SingletonGestaoHotel.getInstance(getApplicationContext()).removerPedidoAPI(pedidoSelecionado);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Não faz nada
                    }
                })
                // Icon
                .setIcon(android.R.drawable.ic_delete)
                .show();
    }

}

