package amsi.dei.estg.ipleiria.app_adatel.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.app_adatel.models.Classificacao;
import amsi.dei.estg.ipleiria.app_adatel.models.Reserva;

public class ClassificacaoJsonParser {

    // Devolve um Array de Classificacaoes vindo da API
    public static ArrayList<Classificacao> parserJsonClassificacoes(JSONArray response, Context context){

        System.out.println("--> Classificação: " + response);
        ArrayList<Classificacao> tempListaClassificacoes = new ArrayList<Classificacao>();

        try {
            for (int i = 0; i < response.length(); i++){

                JSONObject classificacao = (JSONObject)response.get(i);

                int id = classificacao.getInt("id");
                double quarto = classificacao.getDouble("quarto");                       // Nomes
                double comida = classificacao.getDouble("comida");                       // iguais aos
                double staff = classificacao.getDouble("staff");                           // que estão
                double servicos = classificacao.getDouble("servicos");                      // na API
                double geral = classificacao.getDouble("geral");
                int id_cliente = classificacao.getInt("id_cliente");


                Classificacao auxClassificacao = new Classificacao(id, quarto, comida, staff, servicos, geral, id_cliente);

                tempListaClassificacoes.add(auxClassificacao);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return tempListaClassificacoes;
    }


    // Devolve uma Classificacao vinda da API
    public static Classificacao parserJsonClassificacoes(String response, Context context){

        System.out.println("--> PARSER ADICIONAR: " + response);
        Classificacao auxClassificacao = null;

        try {
            JSONObject classificacao = new JSONObject(response);

            int id = classificacao.getInt("id");
            double quarto = classificacao.getDouble("quarto");                       // Nomes
            double comida = classificacao.getDouble("comida");                       // iguais aos
            double staff = classificacao.getDouble("staff");                        // que estão
            double servicos = classificacao.getDouble("servicos");                      // na API
            double geral = classificacao.getDouble("geral");
            int id_cliente = classificacao.getInt("id_cliente");

            auxClassificacao = new Classificacao(id, quarto, comida, staff, servicos, geral, id_cliente);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return auxClassificacao;
    }


    // Método que verifica se existe acesso à internet
    public static boolean isConnectionInternet(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }
}
