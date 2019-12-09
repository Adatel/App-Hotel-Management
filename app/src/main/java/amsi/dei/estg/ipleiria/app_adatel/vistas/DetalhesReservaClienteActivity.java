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

    private TextView idQuarto, dataEntrada, dataSaida, numeroPessoas;
    private Reserva reserva;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_listas);

        idQuarto = findViewById(R.id.tv_dtSaida);
        dataEntrada = findViewById(R.id.tv_dataEntrada);
        dataSaida = findViewById(R.id.tv_dataSaida);
        numeroPessoas = findViewById(R.id.tv_numeroPessoas);

        mostrarReserva();
    }

    private void  mostrarReserva(){
        ArrayList<Reserva> reservas = SingletonGestaoHotel.getInstance().getReservas();
        reserva = reservas.get(0);

        setTitle("Reserva");

        idQuarto.setText(reserva.getId());
        dataEntrada.setText(reserva.getDt_entrada());
        dataSaida.setText(reserva.getDt_saida());
        numeroPessoas.setText(reserva.getN_pessoas());

    }
}
