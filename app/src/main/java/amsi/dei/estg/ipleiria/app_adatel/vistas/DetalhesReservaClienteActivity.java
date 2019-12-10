package amsi.dei.estg.ipleiria.app_adatel.vistas;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.R;
import amsi.dei.estg.ipleiria.app_adatel.models.SingletonGestaoHotel;
import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;

public class DetalhesReservaClienteActivity extends AppCompatActivity {

    public static final String CHAVE_ID = "idReserva";

    private int idReserva;
    private EditText  dataEntrada, dataSaida, numeroPessoas, numQuartos, quartosSolteiro, quartosDuplo, quartosCasal, quartosFamilia;
    private Reserva reserva;

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

        mostrarReserva();
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
}
