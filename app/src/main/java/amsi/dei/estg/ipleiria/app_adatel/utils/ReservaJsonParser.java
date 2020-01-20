package amsi.dei.estg.ipleiria.app_adatel.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;

public class ReservaJsonParser {

    // Devolve um Array de Reservas vindo da API
    public static ArrayList<Reserva> parserJsonReservas(JSONArray response, Context context){

        System.out.println("--> Reserva: " + response);
        ArrayList<Reserva> tempListaReservas = new ArrayList<Reserva>();

        try {
            for (int i = 0; i < response.length(); i++){

                JSONObject reserva = (JSONObject)response.get(i);

                int idReserva = reserva.getInt("id");
                int num_pessoas = reserva.getInt("num_pessoas");                       // Nomes
                int num_quartos = reserva.getInt("num_quartos");                       // iguais aos
                int quartoSolteiro = reserva.getInt("quarto_solteiro");                // que estão
                int quartoDuplo = reserva.getInt("quarto_duplo");                      // na API
                int quartoFamilia = reserva.getInt("quarto_familia");
                int quartoCasal = reserva.getInt("quarto_casal");
                String dataEntrada = reserva.getString("data_entrada");
                String dataSaida = reserva.getString("data_saida");
                int idCliente = reserva.getInt("id_cliente");

                Reserva auxReserva = new Reserva(idReserva, num_pessoas, num_quartos, quartoSolteiro, quartoDuplo, quartoFamilia, quartoCasal, dataEntrada, dataSaida, idCliente);

                tempListaReservas.add(auxReserva);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return tempListaReservas;
    }


    // Devolve uma Reserva vinda da API
    public static Reserva parserJsonReservas(String response, Context context){

        System.out.println("--> PARSER ADICIONAR: " + response);
        Reserva auxReserva = null;

        try {
            JSONObject reserva = new JSONObject(response);

            int idReserva = reserva.getInt("id");
            int num_pessoas = reserva.getInt("num_pessoas");                       // Nomes
            int num_quartos = reserva.getInt("num_quartos");                       // iguais aos
            int quartoSolteiro = reserva.getInt("quarto_solteiro");                // que estão
            int quartoDuplo = reserva.getInt("quarto_duplo");                      // na API
            int quartoFamilia = reserva.getInt("quarto_familia");
            int quartoCasal = reserva.getInt("quarto_casal");
            String dataEntrada = reserva.getString("data_entrada");
            String dataSaida = reserva.getString("data_saida");
            int idCliente = reserva.getInt("id_cliente");

            auxReserva = new Reserva(idReserva, num_pessoas, num_quartos, quartoSolteiro, quartoDuplo, quartoFamilia, quartoCasal, dataEntrada, dataSaida, idCliente);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return auxReserva;
    }


    // Método que verifica se existe acesso à internet
    public static boolean isConnectionInternet(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
