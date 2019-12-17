package amsi.dei.estg.ipleiria.app_adatel.vistas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.R;
import amsi.dei.estg.ipleiria.app_adatel.models.SingletonGestaoHotel;
import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;

public class DetalhesReservaClienteActivity extends AppCompatActivity {

    public static final String CHAVE_ID = "idReserva";

    private int idReserva;
    private EditText  dataEntrada, dataSaida, numeroPessoas, quartosSolteiro, quartosDuplo, quartosCasal, quartosFamilia;
    private Reserva reserva;
    private FloatingActionButton fab;

    private Reserva reservaSelecionada;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhes_reserva_cliente);

        dataEntrada = findViewById(R.id.etDataEntrada);
        dataSaida = findViewById(R.id.etDataSaida);
        numeroPessoas = findViewById(R.id.etNumPessoas);
        quartosSolteiro = findViewById(R.id.etSolteiro);
        quartosCasal = findViewById(R.id.etCasal);
        quartosDuplo = findViewById(R.id.etDuplo);
        quartosFamilia = findViewById(R.id.etFamilia);
        fab = findViewById(R.id.fab);

        // Recebe o id do livro como parâmentro e vai buscar o livro ao SingletonGestorLivros pelo id
        idReserva = getIntent().getIntExtra(CHAVE_ID,-1);

        if(idReserva == -1){
            setTitle("Criar Reserva");
            fab.setImageResource(R.drawable.ic_adicionar);
        } else {
            mostrarReserva(idReserva);
            fab.setImageResource(R.drawable.ic_alterar);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idReserva == -1){
                    SingletonGestaoHotel.getInstance(getApplicationContext()).adicionarReservaBD(adicionarReserva());
                    finish();
                } else {
                    SingletonGestaoHotel.getInstance(getApplicationContext()).editarReservaBD(editarReserva());
                    finish();
                }
            }
        });

    }

    private Reserva adicionarReserva(){

        Reserva auxiliar = new Reserva(0, Integer.parseInt(numeroPessoas.getText().toString()), Integer.parseInt(quartosSolteiro.getText().toString()), Integer.parseInt(quartosDuplo.getText().toString()), Integer.parseInt(quartosFamilia.getText().toString()), Integer.parseInt(quartosCasal.getText().toString()),  dataEntrada.getText().toString(), dataSaida.getText().toString(), 1);
        return auxiliar;
    }

    private Reserva editarReserva(){

        reservaSelecionada.setNumPessoas(Integer.parseInt(numeroPessoas.getText().toString()));
        reservaSelecionada.setQuartoSol(Integer.parseInt(quartosSolteiro.getText().toString()));
        reservaSelecionada.setQuartoD(Integer.parseInt(quartosDuplo.getText().toString()));
        reservaSelecionada.setQuartoF(Integer.parseInt(quartosFamilia.getText().toString()));
        reservaSelecionada.setQuartoF(Integer.parseInt(quartosCasal.getText().toString()));
        reservaSelecionada.setDtEntrada(dataEntrada.getText().toString());
        reservaSelecionada.setDtSaida(dataSaida.getText().toString());

        return reservaSelecionada;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(idReserva != -1) {
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

    private void  mostrarReserva(int idReserva){

        ArrayList<Reserva> reservas = SingletonGestaoHotel.getInstance(getApplicationContext()).getReservasBD();
        reserva = reservas.get(idReserva -1);

        //setTitle("Reserva");
        dataEntrada.setText(reserva.getDtEntrada());
        dataSaida.setText(reserva.getDtSaida());
        numeroPessoas.setText(reserva.getNumPessoas() + "");
        quartosSolteiro.setText(reserva.getQuartoSol() + "");
        quartosDuplo.setText(reserva.getQuartoD() + "");
        quartosCasal.setText(reserva.getQuartoC() + "");
        quartosFamilia.setText(reserva.getQuartoF() + "");
    }


    private void dialogRemover() {

        AlertDialog.Builder builder;

        builder = new AlertDialog.Builder(this);
        // Contruindo Alert Dialog
        // Título
        builder.setTitle("Cancelar Reserva")
                // Messagem
                .setMessage("Pretende mesmo cancelar a reserva?")
                // 2 Botões
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SingletonGestaoHotel.getInstance(getApplicationContext()).removerReservaBD(reservaSelecionada.getId());
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
