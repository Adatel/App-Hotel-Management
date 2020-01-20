package amsi.dei.estg.ipleiria.app_adatel.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Quarto;
import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;

public class QuartoJsonParser {

    // Devolve um Array de Quartos vindo da API
    public static ArrayList<Quarto> parserJsonQuartos(JSONArray response, Context context){

        System.out.println("--> Quarto: " + response);
        ArrayList<Quarto> tempListaQuartos = new ArrayList<Quarto>();

        try {
            for (int i = 0; i < response.length(); i++){

                JSONObject quarto = (JSONObject)response.get(i);

                int num_quarto = quarto.getInt("num_quarto");
                int estado = quarto.getInt("estado");
                int id_tipo = quarto.getInt("id_tipo");

                Quarto auxQuarto = new Quarto(num_quarto, estado, id_tipo);

                tempListaQuartos.add(auxQuarto);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return tempListaQuartos;
    }


    // Devolve um Quarto vinda da API
    public static Quarto parserJsonQuartos(String response, Context context){

        System.out.println("--> PARSER ADICIONAR: " + response);
        Quarto auxQuarto = null;

        try {
            JSONObject quarto = new JSONObject(response);

            int num_quarto = quarto.getInt("num_quarto");
            int estado = quarto.getInt("estado");
            int id_tipo = quarto.getInt("id_tipo");

            auxQuarto = new Quarto(num_quarto, estado, id_tipo);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return auxQuarto;
    }


    // Método que verifica se existe acesso à internet
    public static boolean isConnectionInternet(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
