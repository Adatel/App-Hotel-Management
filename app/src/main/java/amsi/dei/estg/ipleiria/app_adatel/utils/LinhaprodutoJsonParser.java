package amsi.dei.estg.ipleiria.app_adatel.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Linhaproduto;
import amsi.dei.estg.ipleiria.app_adatel.models.Pedido;

public class LinhaprodutoJsonParser {

    // Devolve um Array de Linha Produto vindo da API
    public static ArrayList<Linhaproduto> parserJsonLinhaproduto(JSONArray response, Context context){

        System.out.println("--> Linha produto: " + response);
        ArrayList<Linhaproduto> tempListaLinhaprodutos = new ArrayList<Linhaproduto>();

        try {
            for (int i = 0; i < response.length(); i++){

                JSONObject linhaproduto = (JSONObject)response.get(i);

                int id = linhaproduto.getInt("id");
                int quantidade = linhaproduto.getInt("quantidade");
                int id_produto = linhaproduto.getInt("id_produto");
                int id_pedido = linhaproduto.getInt("id_pedido");

                Linhaproduto auxLinhaproduto = new Linhaproduto(id, quantidade, id_produto, id_pedido);

                tempListaLinhaprodutos.add(auxLinhaproduto);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return tempListaLinhaprodutos;
    }


    // Devolve uma Linha de produto vinda da API
    public static Linhaproduto parserJsonLinhaproduto(String response, Context context){

        System.out.println("--> PARSER ADICIONAR: " + response);
        Linhaproduto auxLinhaproduto = null;

        try {
            JSONObject linhaproduto = new JSONObject(response);

            int id = linhaproduto.getInt("id");
            int quantidade = linhaproduto.getInt("quantidade");
            int id_produto = linhaproduto.getInt("id_produto");
            int id_pedido = linhaproduto.getInt("id_pedido");

            auxLinhaproduto = new Linhaproduto(id, quantidade, id_produto, id_pedido);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return auxLinhaproduto;
    }


    // Método que verifica se existe acesso à internet
    public static boolean isConnectionInternet(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
