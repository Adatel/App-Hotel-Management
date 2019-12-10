package amsi.dei.estg.ipleiria.app_adatel.vistas;

import android.os.Bundle;
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
    private TextView idQuarto, dataEntrada, dataSaida, numeroPessoas;
    private Reserva reserva;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhes_reserva_cliente);

        idReserva = getIntent().getIntExtra(CHAVE_ID, -1);

        idQuarto = findViewById(R.id.tv_idQuarto);
        dataEntrada = findViewById(R.id.tv_dataEntrada);
        dataSaida = findViewById(R.id.tv_dataSaida);
        numeroPessoas = findViewById(R.id.tv_numeroPessoas);

        mostrarReserva();
    }

    private void  mostrarReserva(){
        ArrayList<Reserva> reservas = SingletonGestaoHotel.getInstance().getReservas();
        reserva = reservas.get(idReserva -1);

        //setTitle("Reserva");

        idQuarto.setText(reserva.getId() + "");
        dataEntrada.setText(reserva.getDtEntrada());
        dataSaida.setText(reserva.getDtSaida());
        numeroPessoas.setText(reserva.getNumPessoas() + "");

    }
}
