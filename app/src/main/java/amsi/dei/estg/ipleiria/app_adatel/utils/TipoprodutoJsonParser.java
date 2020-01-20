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
import amsi.dei.estg.ipleiria.app_adatel.models.TipoProduto;

public class TipoprodutoJsonParser {

    // Devolve um Array de Tipo de produtos vindo da API
    public static ArrayList<TipoProduto> parserJsonTipoprodutos(JSONArray response, Context context){

        System.out.println("--> Tipo de produtos: " + response);
        ArrayList<TipoProduto> tempListaTipoprodutos = new ArrayList<TipoProduto>();

        try {
            for (int i = 0; i < response.length(); i++){

                JSONObject tipoproduto = (JSONObject)response.get(i);

                int id = tipoproduto.getInt("id");
                String descricao_tipo = tipoproduto.getString("descricao_tipo");

                TipoProduto auxTipoproduto = new TipoProduto(id, descricao_tipo);

                tempListaTipoprodutos.add(auxTipoproduto);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return tempListaTipoprodutos;
    }


    // Devolve um Tipo de produto vindo da API
    public static TipoProduto parserJsonTipoprodutos(String response, Context context){

        System.out.println("--> PARSER ADICIONAR: " + response);
        TipoProduto auxTipoproduto = null;

        try {
            JSONObject tipoproduto = new JSONObject(response);

            int id = tipoproduto.getInt("id");
            String descricao_tipo = tipoproduto.getString("descricao_tipo");

            auxTipoproduto = new TipoProduto(id, descricao_tipo);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return auxTipoproduto;
    }


    // Método que verifica se existe acesso à internet
    public static boolean isConnectionInternet(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
