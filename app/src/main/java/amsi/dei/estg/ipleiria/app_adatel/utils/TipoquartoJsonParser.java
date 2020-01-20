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
import amsi.dei.estg.ipleiria.app_adatel.models.Tipoquarto;

public class TipoquartoJsonParser {

    // Devolve um Array de Tipos de quarto vindo da API
    public static ArrayList<Tipoquarto> parserJsonTipoquartos(JSONArray response, Context context){

        System.out.println("--> Tipo quarto: " + response);
        ArrayList<Tipoquarto> tempListaTipoquartos = new ArrayList<Tipoquarto>();

        try {
            for (int i = 0; i < response.length(); i++){

                JSONObject tipoquarto = (JSONObject)response.get(i);

                int id = tipoquarto.getInt("id");
                int preco_noite = tipoquarto.getInt("preco_noite");
                String designacao = tipoquarto.getString("designacao");

                Tipoquarto auxTipoquarto = new Tipoquarto(id, preco_noite, designacao);

                tempListaTipoquartos.add(auxTipoquarto);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return tempListaTipoquartos;
    }


    // Devolve um Tipo de quarto vindo da API
    public static Tipoquarto parserJsonTipoquartos(String response, Context context){

        System.out.println("--> PARSER ADICIONAR: " + response);
        Tipoquarto auxTipoquarto = null;

        try {
            JSONObject tipoquarto = new JSONObject(response);

            int id = tipoquarto.getInt("id");
            int preco_noite = tipoquarto.getInt("preco_noite");
            String designacao = tipoquarto.getString("designacao");

            auxTipoquarto = new Tipoquarto(id, preco_noite, designacao);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return auxTipoquarto;
    }


    // Método que verifica se existe acesso à internet
    public static boolean isConnectionInternet(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
