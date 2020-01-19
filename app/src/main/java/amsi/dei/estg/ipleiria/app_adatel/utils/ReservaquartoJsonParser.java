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
import amsi.dei.estg.ipleiria.app_adatel.models.Reservaquarto;

public class ReservaquartoJsonParser {

    // Devolve um Array de Reserva_quartos vindo da API
    public static ArrayList<Reservaquarto> parserJsonLReservaquartos(JSONArray response, Context context){

        System.out.println("--> Reserva quarto: " + response);
        ArrayList<Reservaquarto> tempListaReservaquartos = new ArrayList<Reservaquarto>();

        try {
            for (int i = 0; i < response.length(); i++){

                JSONObject reservaquarto = (JSONObject)response.get(i);

                int id = reservaquarto.getInt("id");
                int idReserva = reservaquarto.getInt("id_reserva");                       // Nomes iguais aos da api
                int idQuarto = reservaquarto.getInt("id_quarto");

                Reservaquarto auxReservaquarto = new Reservaquarto(id, idReserva, idQuarto);

                tempListaReservaquartos.add(auxReservaquarto);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return tempListaReservaquartos;
    }


    // Devolve uma Reserva_quarto  vinda da API
    public static Reservaquarto parserJsonReservaquartos(String response, Context context){

        System.out.println("--> PARSER ADICIONAR: " + response);
        Reservaquarto auxReservaquarto = null;

        try {
            JSONObject reservaquarto = new JSONObject(response);

            int id = reservaquarto.getInt("id");
            int idReserva = reservaquarto.getInt("id_reserva");                       // Nomes iguais aos da api
            int idQuarto = reservaquarto.getInt("id_quarto");

            auxReservaquarto = new Reservaquarto(id, idReserva, idQuarto);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return auxReservaquarto;
    }


    // Método que verifica se existe acesso à internet
    public static boolean isConnectionInternet(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
