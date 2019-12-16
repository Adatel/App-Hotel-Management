package amsi.dei.estg.ipleiria.app_adatel.vistas;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    private EditText  dataEntrada, dataSaida, numeroPessoas, numQuartos, quartosSolteiro, quartosDuplo, quartosCasal, quartosFamilia;
    private Reserva reserva;
    private FloatingActionButton btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhes_reserva_cliente);

        idReserva = getIntent().getIntExtra(CHAVE_ID, -1);

        dataEntrada = findViewById(R.id.etDataEntrada);
        dataSaida = findViewById(R.id.etDataSaida);
        numeroPessoas = findViewById(R.id.etNumPessoas);
        quartosSolteiro = findViewById(R.id.etSolteiro);
        quartosCasal = findViewById(R.id.etCasal);
        quartosDuplo = findViewById(R.id.etDuplo);
        quartosFamilia = findViewById(R.id.etFamilia);
        btnSave = findViewById(R.id.btnSave);

        if(idReserva == -1){
            setTitle("Adicionar Reserva");
        }else{
            reserva = SingletonGestaoHotel.getInstance().getReserva(idReserva);
            setTitle("Detalhes: " + reserva.getId());
            mostrarReserva();
        }

    }

    private void  mostrarReserva(){
        ArrayList<Reserva> reservas = SingletonGestaoHotel.getInstance().getReservas();
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

    private void dialogRemover(){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Remover")
                .setMessage("Pretende mesmo remover esta reserva?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SingletonGestaoHotel.getInstance().removerReserva(idReserva);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_delete)
                .show();
    }
}
