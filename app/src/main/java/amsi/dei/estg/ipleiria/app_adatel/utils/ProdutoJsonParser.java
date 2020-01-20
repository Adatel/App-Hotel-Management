package amsi.dei.estg.ipleiria.app_adatel.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Produto;
import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;

public class ProdutoJsonParser {

    // Devolve um Array de Produtos vindo da API
    public static ArrayList<Produto> parserJsonProdutos(JSONArray response, Context context){

        System.out.println("--> Produto: " + response);
        ArrayList<Produto> tempListaProdutos = new ArrayList<Produto>();

        try {
            for (int i = 0; i < response.length(); i++){

                JSONObject produto = (JSONObject)response.get(i);

                int id = produto.getInt("id");
                int preco_unitario = produto.getInt("preco_unitario");
                int id_tipoproduto = produto.getInt("id_tipo");
                String designacao = produto.getString("designacao");

                Produto auxProduto = new Produto(id, preco_unitario, id_tipoproduto, designacao);

                tempListaProdutos.add(auxProduto);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return tempListaProdutos;
    }


    // Devolve um Produto vindo da API
    public static Produto parserJsonProdutos(String response, Context context){

        System.out.println("--> PARSER ADICIONAR: " + response);
        Produto auxProduto = null;

        try {
            JSONObject produto = new JSONObject(response);

            int id = produto.getInt("id");
            int preco_unitario = produto.getInt("preco_unitario");
            int id_tipoproduto = produto.getInt("id_tipo");
            String designacao = produto.getString("designacao");

            auxProduto = new Produto(id, preco_unitario, id_tipoproduto, designacao);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return auxProduto;
    }


    // Método que verifica se existe acesso à internet
    public static boolean isConnectionInternet(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
