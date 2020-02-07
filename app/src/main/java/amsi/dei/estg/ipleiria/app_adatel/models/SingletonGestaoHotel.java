package amsi.dei.estg.ipleiria.app_adatel.models;

import android.content.Context;
import android.util.Base64;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import amsi.dei.estg.ipleiria.app_adatel.listeners.ClassificacoesListener;
import amsi.dei.estg.ipleiria.app_adatel.listeners.PedidosListener;
import amsi.dei.estg.ipleiria.app_adatel.listeners.ProfilesListener;
import amsi.dei.estg.ipleiria.app_adatel.listeners.ReservasListener;
import amsi.dei.estg.ipleiria.app_adatel.listeners.ProdutosListener;
import amsi.dei.estg.ipleiria.app_adatel.listeners.QuartosListener;
import amsi.dei.estg.ipleiria.app_adatel.listeners.TipoQuartosListener;
import amsi.dei.estg.ipleiria.app_adatel.listeners.LinhaProdutosListener;
import amsi.dei.estg.ipleiria.app_adatel.listeners.TipoProdutosListener;
import amsi.dei.estg.ipleiria.app_adatel.listeners.UsersListener;
import amsi.dei.estg.ipleiria.app_adatel.utils.ClassificacaoJsonParser;
import amsi.dei.estg.ipleiria.app_adatel.utils.LinhaprodutoJsonParser;
import amsi.dei.estg.ipleiria.app_adatel.utils.PedidoJsonParser;
import amsi.dei.estg.ipleiria.app_adatel.utils.ProdutoJsonParser;
import amsi.dei.estg.ipleiria.app_adatel.utils.QuartoJsonParser;
import amsi.dei.estg.ipleiria.app_adatel.utils.ReservaJsonParser;
import amsi.dei.estg.ipleiria.app_adatel.utils.TipoprodutoJsonParser;
import amsi.dei.estg.ipleiria.app_adatel.utils.TipoquartoJsonParser;
import amsi.dei.estg.ipleiria.app_adatel.utils.UserJsonParser;

public class SingletonGestaoHotel implements ReservasListener, UsersListener, ProfilesListener, PedidosListener, ProdutosListener, QuartosListener, TipoProdutosListener, LinhaProdutosListener, TipoQuartosListener, ClassificacoesListener {

    private static RequestQueue volleyQueue = null;

    private String idCliente = null;
    private String mUrlAPIUSERS = " http://192.168.1.67:8081/api/users";
    private String mUrlAPIPROFILES = "http://192.168.1.67:8081/api/profiles";
    private String mUrlAPIPEDIDOS = "https://192.168.1.67:8081/api/pedidos";
    private String mUrlAPIRESERVAS = "http://192.168.1.67:8081/api/reservas";
    private String mUrlAPIPRODUTOS = "http://192.168.1.67:8081/api/produtos";
    private String mUrlAPITIPOPRODUTO = "http://192.168.1.67:8081/api/tipoprodutos";
    private String mUrlAPIQUARTOS = "http://192.168.1.67:8081/api/quartos";
    private String mUrlAPITIPOQUARTO = "http://192.168.1.67:8081/api/tipoquartos";
    private String mUrlAPIRESERVAQUARTO = "http://192.168.1.67:8081/api/reservaquartos";
    private String mUrlAPILINHAPRODUTO = "http://192.168.1.67:8081/api/linhaprodutos";
    private String mUrlAPICLASSIFICACAO = "http://192.168.1.67:8081/api/classificacoes";



    ///Adicionei
    private ArrayList<User> users;
    private ArrayList<Reserva> reservas;
    private ArrayList<Profile> profiles;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Produto> produtos;
    private ArrayList<Quarto> quartos;
    private ArrayList<Tipoquarto> tipoQuartos;
    private ArrayList<TipoProduto> tipoProdutos;
    private ArrayList<Linhaproduto> linhaprodutos;
    private ArrayList<Reservaquarto> reservaquartos;
    private ArrayList<Classificacao> classificacaos;

    private static SingletonGestaoHotel INSTANCE = null;
    private HotelBDHelper hotelBDHelper = null;

    private UsersListener userListener;
    private ReservasListener reservasListener;
    private PedidosListener pedidosListener;
    private ProdutosListener produtosListener;
    private QuartosListener quartosListener;
    private TipoQuartosListener tipoQuartosListener;
    private TipoProdutosListener tipoProdutosListener;
    private LinhaProdutosListener linhaProdutosListener;
    private ClassificacoesListener classificacoesListener;


    //Verificacao
    private String user;
    private String pass;


    public static synchronized SingletonGestaoHotel getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SingletonGestaoHotel(context);
            volleyQueue = Volley.newRequestQueue(context);
        }
        return INSTANCE;
    }

    private SingletonGestaoHotel(Context context) {
        ///Adicionei
        users = new ArrayList<>();
        profiles = new ArrayList<>();
        reservas = new ArrayList<>();
        pedidos = new ArrayList<>();
        produtos = new ArrayList<>();
        quartos = new ArrayList<>();
        tipoQuartos = new ArrayList<>();
        tipoProdutos = new ArrayList<>();
        linhaprodutos = new ArrayList<>();
        reservaquartos = new ArrayList<>();
        classificacaos = new ArrayList<>();

        hotelBDHelper = new HotelBDHelper(context);
    }


    // <--------------  Métodos para garantir que os dados da BD estão atualizados com os dados vindos da API -------------->


    // <----------------------------------- USERS ----------------------------------->

    public User getUserBD(int id) {
        for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }

    ///Adicionei
    public ArrayList<User> getUsersBD() {
        return users = hotelBDHelper.getAllUsersBD();
    }

    public void adicionarUserBD(User user) {
        hotelBDHelper.adicionarUserBD(user);
    }

    public void adicionarUsersBD(ArrayList<User> users) {
        hotelBDHelper.removerAllUsers();
    }

    public void removerUserBD(int id) {
        User auxUser = getUserBD(id);

        if (auxUser != null) {
            if (hotelBDHelper.removerUserBD(auxUser.getId())) {
                users.remove(auxUser);
                System.out.println("--> User removido");
            }
        }
    }

    public void guardarUserBD(User user) {
        if (!users.contains(user)) {
            return;
        }

        User auxUser = getUserBD(user.getId());
        auxUser.setUsername(user.getUsername());
        auxUser.setEmail(user.getEmail());
        auxUser.setPassword(user.getPassword());

        if (hotelBDHelper.guardarUserBD(auxUser)) {
            System.out.println("--> USer Guardado na BD");
        }
    }

    public void idClienteNull() {
        idCliente = null;
    }

    // <----------------------------------- PROFILE ----------------------------------->

    public Profile getProfileBD(int id_user) {
        for (Profile p : profiles) {
            if (p.getId_user() == id_user) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Profile> getProfilesBD() {
        profiles = hotelBDHelper.getAllProfilesBD();
        return profiles;
    }

    public void adicionarProfileBD(Profile profile) {
        hotelBDHelper.adicionarProfileBD(profile);
    }

    public void adicionarProfilesBD(ArrayList<Profile> profiles) {
        hotelBDHelper.removerALLProfilesDB();
    }


    public void removerProfileBD(int id_user) {
        Profile auxProfile = getProfileBD(id_user);

        if (auxProfile != null) {
            if (hotelBDHelper.removerProfileBD(auxProfile.getId_user())) {
                profiles.remove(auxProfile);
                System.out.println("--> Profile removido");
            }
        }
    }

    public void guardarProfileBD(Profile profile) {
        if (!profiles.contains(profile)) {
            return;
        }

        Profile auxProfile = getProfileBD(profile.getId_user());
        auxProfile.setNome(profile.getNome());
        auxProfile.setNif(profile.getNif());
        auxProfile.setTelefone(profile.getTelefone());
        auxProfile.setIs_admin(profile.getIs_admin());
        auxProfile.setIs_cliente(profile.getIs_cliente());
        auxProfile.setIs_funcionario(profile.getIs_funcionario());

        if (hotelBDHelper.guardarProfileBD(auxProfile)) {
            System.out.println("--> Profile Guardado");
        }
    }


    // <----------------------------------- RESERVAS ----------------------------------->

    public ArrayList<Reserva> getReservasBD() {
        return reservas;
    }

    public Reserva getReservaBD(long idReserva) {
        for (Reserva r : reservas) {
            if (r.getId() == idReserva) {
                return r;
            }
        }
        return null;
    }

    public void adicionarReservaBD(Reserva reserva) {
        reservas.add(reserva);
    }

    public void adicionarReservasBD(ArrayList<Reserva> reservas) {
        hotelBDHelper.removerALLReservasDB();
    }

    public void removerReservaBD(int idReserva) {
        Reserva auxReserva = getReservaBD(idReserva);
        reservas.remove(auxReserva);
    }

    public void guardarReservaBD(Reserva reserva) {
        if (!reservas.contains(reserva)) {
            return;
        }
        Reserva auxReserva = getReservaBD(reserva.getId());
        auxReserva.setDtEntrada(reserva.getDtEntrada());
        auxReserva.setDtSaida(reserva.getDtSaida());
        auxReserva.setNumPessoas(reserva.getNumPessoas());
        //auxReserva.setNumQuartos(reserva.getNumQuartos());
    }


    // <----------------------------------- PEDIDO ----------------------------------->

    public ArrayList<Pedido> getPedidosBD() {
        return pedidos;
    }

    public Pedido getPedidoBD(long idPedido) {
        for (Pedido p : pedidos) {
            if (p.getId() == idPedido) {
                return p;
            }
        }
        return null;
    }

    public void adicionarPedidoBD(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void adicionarPedidosBD(ArrayList<Pedido> pedidos) {
        hotelBDHelper.removerALLRPedidosDB();
    }

    public void removerPedidoBD(int idPedido) {
        Pedido auxPedido = getPedidoBD(idPedido);
        pedidos.remove(auxPedido);
    }

    public void guardarPedidoBD(Pedido pedido) {
        if (!pedidos.contains(pedido)) {
            return;
        }
        Pedido auxPedido = getPedidoBD(pedido.getId());
        auxPedido.setCusto(pedido.getCusto());
        auxPedido.setId_reservaquarto(pedido.getId_reservaquarto());
        /*auxPedido.getDt_hora(pedido.setDt_hora());*/
    }

    // <----------------------------------- PRODUTO ----------------------------------->

    public ArrayList<Produto> getProdutosBD() {
        return produtos;
    }

    public Produto getProdutoBD(long idProduto) {
        for (Produto pr : produtos) {
            if (pr.getId() == idProduto) {
                return pr;
            }
        }
        return null;
    }

    public void adicionarProdutoBD(Produto produto) {
        produtos.add(produto);
    }

    public void adicionarProdutosBD(ArrayList<Produto> produtos) {
        hotelBDHelper.removerALLProdutosDB();
    }

    public void removerProdutoBD(int idProduto) {
        Produto auxProduto = getProdutoBD(idProduto);
        produtos.remove(auxProduto);
    }

    public void guardarProdutoBD(Produto produto) {
        if (!produtos.contains(produto)) {
            return;
        }
        Produto auxProduto = getProdutoBD(produto.getId());
        auxProduto.setDesignacao(produto.getDesignacao());
        auxProduto.setId_tipoproduto(produto.getId_tipoproduto());
        /*auxProduto.setPreco_unitario(produto.setPreco_unitario());*/
    }

    // <----------------------------------- QUARTO ----------------------------------->

    public ArrayList<Quarto> getQuartosBD() {
        return quartos;
    }

    public Quarto getQuartoBD(long idQuarto) {
        for (Quarto q : quartos) {
            if (q.getNum_quarto() == idQuarto) {
                return q;
            }
        }
        return null;
    }

    public void adicionarQuartoBD(Quarto quarto) {
        quartos.add(quarto);
    }

    public void adicionarQuartosBD(ArrayList<Quarto> quartos) {
        hotelBDHelper.removerALLQuartosDB();
    }

    public void removerQuartoBD(int idQuarto) {
        Quarto auxQuarto = getQuartoBD(idQuarto);
        quartos.remove(auxQuarto);
    }

    public void guardarQuartoBD(Quarto quarto) {
        if (!quartos.contains(quarto)) {
            return;
        }
        Quarto auxQuarto = getQuartoBD(quarto.getNum_quarto());
        auxQuarto.setEstado(quarto.getEstado());
        auxQuarto.setId_tipo(quarto.getId_tipo());
    }

    // <----------------------------------- TIPO QUARTO ----------------------------------->

    public ArrayList<Tipoquarto> getTipoQuartosBD() {
        return tipoQuartos;
    }

    public Tipoquarto getTipoQuartoBD(long idTipoQuarto) {
        for (Tipoquarto tq : tipoQuartos) {
            if (tq.getId() == idTipoQuarto) {
                return tq;
            }
        }
        return null;
    }

    public void adicionarTipoQuartoBD(Tipoquarto tipoquarto) {
        tipoQuartos.add(tipoquarto);
    }

    public void adicionarTipoQuartosBD(ArrayList<Tipoquarto> tipoQuartos) {
        hotelBDHelper.removerALLTipoquartosDB();
    }

    public void removerTipoQuartoBD(int idTipoQuarto) {
        Tipoquarto auxTipoQuarto = getTipoQuartoBD(idTipoQuarto);
        tipoQuartos.remove(auxTipoQuarto);
    }

    public void guardarTipoQuartoBD(Tipoquarto tipoquarto) {
        if (!tipoQuartos.contains(tipoquarto)) {
            return;
        }
        Tipoquarto auxTipoQuarto = getTipoQuartoBD(tipoquarto.getId());
        auxTipoQuarto.setDesignacao(tipoquarto.getDesignacao());
        auxTipoQuarto.setPreco_noite(tipoquarto.getPreco_noite());
    }

    // <----------------------------------- TIPO PRODUTO ----------------------------------->

    public ArrayList<TipoProduto> getTipoProdutosBD() {
        return tipoProdutos;
    }

    public TipoProduto getTipoProdutoBD(long idTipoProduto) {
        for (TipoProduto tp : tipoProdutos) {
            if (tp.getId() == idTipoProduto) {
                return tp;
            }
        }
        return null;
    }

    public void adicionarTipoProdutoBD(TipoProduto tipoProduto) {
        tipoProdutos.add(tipoProduto);
    }

    public void adicionarTipoProdutosBD(ArrayList<TipoProduto> tipoProdutos) {
        hotelBDHelper.removerALLTipoprodutoDB();
    }

    public void removerTipoProdutoBD(int idTipoProduto) {
        TipoProduto auxTipoProduto = getTipoProdutoBD(idTipoProduto);
        tipoProdutos.remove(auxTipoProduto);
    }

    public void guardarTipoQuartoBD(TipoProduto tipoProduto) {
        if (!tipoProdutos.contains(tipoProduto)) {
            return;
        }
        TipoProduto auxTipoProduto = getTipoProdutoBD(tipoProduto.getId());
        auxTipoProduto.setDescricao(tipoProduto.getDescricao());
    }

    // <----------------------------------- LINHA PRODUTO ----------------------------------->

    public ArrayList<Linhaproduto> getLinhaprodutosBD() {
        return linhaprodutos;
    }

    public Linhaproduto getLinhaprodutoBD(long idLinhaProduto) {
        for (Linhaproduto lp : linhaprodutos) {
            if (lp.getId() == idLinhaProduto) {
                return lp;
            }
        }
        return null;
    }

    public void adicionarLinhaprodutoBD(Linhaproduto linhaproduto) {
        linhaprodutos.add(linhaproduto);
    }

    public void adicionarLinhaprodutosBD(ArrayList<Linhaproduto> linhaprodutos) {
        hotelBDHelper.removerALLLinhaprodutosDB();
    }

    public void removerLinhaprodutoBD(int idLinhaProduto) {
        Linhaproduto auxLinhaProduto = getLinhaprodutoBD(idLinhaProduto);
        linhaprodutos.remove(auxLinhaProduto);
    }

    public void guardarLinhaprodutoBD(Linhaproduto linhaproduto) {
        if (!linhaprodutos.contains(linhaproduto)) {
            return;
        }
        Linhaproduto auxLinhaProduto = getLinhaprodutoBD(linhaproduto.getId());
        auxLinhaProduto.setId_pedido(linhaproduto.getId_pedido());
        auxLinhaProduto.setId_produto(linhaproduto.getId_produto());
        auxLinhaProduto.setQuantidade(linhaproduto.getQuantidade());
    }


    // <----------------------------------- RESERVA QUARTO ----------------------------------->

    public ArrayList<Reservaquarto> getReservaquartosBD() {
        return reservaquartos;
    }

    public Reservaquarto getReservaquartoBD(long idReservaQuarto) {
        for (Reservaquarto rq : reservaquartos) {
            if (rq.getId() == idReservaQuarto) {
                return rq;
            }
        }
        return null;
    }

    public void adicionarReservaquartoBD(Reservaquarto reservaquarto) {
        reservaquartos.add(reservaquarto);
    }

    public void adicionarReservaquartosBD(ArrayList<Reservaquarto> reservaquartos) {
        hotelBDHelper.removerALLReservaquartosDB();
    }

    public void removerReservaquartoBD(int idReservaQuarto) {
        Reservaquarto auxReservaquarto = getReservaquartoBD(idReservaQuarto);
        reservaquartos.remove(auxReservaquarto);
    }

    public void guardarReservaquartoBD(Reservaquarto reservaquarto) {
        if (!reservaquartos.contains(reservaquarto)) {
            return;
        }
        Reservaquarto auxReservaquarto = getReservaquartoBD(reservaquarto.getId());
        auxReservaquarto.setIdQuarto(reservaquarto.getIdQuarto());
        auxReservaquarto.setIdReserva(reservaquarto.getIdReserva());
    }


    // <----------------------------------- CLASSIFICAÇÕES ----------------------------------->

    public ArrayList<Classificacao> getClassificacoesBD() {
        return classificacaos;
    }

    public Classificacao getClassificacaoBD(long idClassificacao) {
        for (Classificacao c : classificacaos) {
            if (c.getId() == idClassificacao) {
                return c;
            }
        }
        return null;
    }

    public void adicionarClassificacaoBD(Classificacao classificacao) {
        classificacaos.add(classificacao);
    }

    public void adicionarClassificacoesBD(ArrayList<Classificacao> classificacaos) {
        hotelBDHelper.removerALLClassificacaoDB();
    }

    public void removerClassificacaoBD(int idClassificacao) {
        Classificacao auxClassificacao = getClassificacaoBD(idClassificacao);
        classificacaos.remove(auxClassificacao);
    }

    public void guardarClassificacaoBD(Classificacao classificacao) {
        if (!classificacaos.contains(classificacao)) {
            return;
        }
        Classificacao auxClassificacao = getClassificacaoBD(classificacao.getId());
        auxClassificacao.setQuarto(auxClassificacao.getQuarto());
        auxClassificacao.setComida(auxClassificacao.getComida());
        auxClassificacao.setStaff(auxClassificacao.getStaff());
        auxClassificacao.setServicos(auxClassificacao.getServicos());
        auxClassificacao.setGeral(auxClassificacao.getGeral());
        auxClassificacao.setId_cliente(auxClassificacao.getId_cliente());
    }



    // <----------------------------- Métodos para atualizarem a API ----------------------------->

    // <----------------------------------- USERS ----------------------------------->
    public String getUsersAPI(final Context context, boolean isConected, final String username, final String password){
        Toast.makeText(context, "Is Connected", Toast.LENGTH_SHORT).show();

        if(!isConected){
            //Toast.makeText(context, "NotConnected", Toast.LENGTH_SHORT).show();
            users = hotelBDHelper.getAllUsersBD();

            if(userListener != null){
                userListener.onRefreshListaUser(users);
            }
        }else {
            StringRequest stringRequest = new StringRequest(Request.Method.GET, mUrlAPIUSERS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            System.out.println("-->" + response);
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                idCliente = jsonObject.getString("id");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            System.out.println("--> id: " + idCliente);

                            System.out.println("--> OK");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--> Error: Invalido - " + error.getMessage());
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    user = username;
                    pass = password;

                    String loginString = user + ":" + pass;

                    byte[] loginStringBytes = null;

                    try {
                        loginStringBytes = loginString.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    String loginStringb64 = Base64.encodeToString(loginStringBytes, Base64.NO_WRAP);

                    //  Authorization: Basic $auth
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Basic " + loginStringb64);
                    return headers;
                }
            };
            volleyQueue.add(stringRequest);
        }
        return idCliente;
    }
    // <----------------------------------- PROFILES ----------------------------------->



    // <----------------------------------- RESERVAS ----------------------------------->

    // Vai buscar as reservas todas à API
    public void getAllReservasAPI(final Context context, boolean isConnected){

        Toast.makeText(context, "ISCONNECTED: " + isConnected, Toast.LENGTH_SHORT).show();
        if(!isConnected){
            //Toast.makeText(context, "NotConnected", Toast.LENGTH_SHORT).show();
            reservas = hotelBDHelper.getAllReservasBD();

            if(reservasListener != null){
                reservasListener.onRefreshListaReservas(reservas);
            }
        } else {
            //Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
           // System.out.println("--> Reserva id Cliente: " + idCliente);
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIUSERS + "/" + idCliente + "/reservas", null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    reservas = ReservaJsonParser.parserJsonReservas(response, context);
                    //System.out.println("--> RESPOSTA: " + response);
                    adicionarReservasBD(reservas);

                    if(reservasListener != null){
                        reservasListener.onRefreshListaReservas(reservas);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--> ERRO: getAllReservasAPI: " + error.getMessage());
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    String loginString = user + ":" + pass;

                    byte[] loginStringBytes = null;

                    try {
                        loginStringBytes = loginString.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    String loginStringb64 = Base64.encodeToString(loginStringBytes, Base64.NO_WRAP);

                    //  Authorization: Basic $auth
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Basic " + loginStringb64);
                    return headers;
                }

            };
            volleyQueue.add(req);
        }

    }



    // Adicionar 1 só livro à API
    public void adicionarReservaAPI(final Reserva reserva, final Context context, final String username, final String password){

        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPIRESERVAS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("--> RESPOSTA ADD POST: " + response);

                if(reservasListener != null){
                    reservasListener.onUpdateListaReservasBD(ReservaJsonParser.parserJsonReservas(response, context), 1);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO: adicionarReservasAPI: " + error.getMessage());
            }
        })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                user = username;
                pass = password;

                String loginString = user + ":" + pass;

                byte[] loginStringBytes = null;

                try {
                    loginStringBytes = loginString.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String loginStringb64 = Base64.encodeToString(loginStringBytes, Base64.NO_WRAP);

                //  Authorization: Basic $auth
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Basic " + loginStringb64);
                return headers;
            }
            protected Map<String, String> getParams(){

                Map<String, String> params = new HashMap<>();
                params.put("num_pessoas", reserva.getNumPessoas() + "");
                params.put("num_quartos", reserva.getNumQuartos() + "");        // Os Nomes
                params.put("quarto_solteiro", reserva.getQuartoSol() + "");     // têm de
                params.put("quarto_duplo", reserva.getQuartoD() + "");          // corresponder
                params.put("quarto_familia", reserva.getQuartoF() + "");        // aos da
                params.put("quarto_casal", reserva.getQuartoC() + "");          // API
                params.put("data_entrada", reserva.getDtEntrada());
                params.put("data_saida", reserva.getDtSaida());
                params.put("id_cliente", idCliente + "");

                return params;
            }
        };
        volleyQueue.add(req);
    }

    // Remove a reserva da API
    public void removerReservaAPI(final Reserva reserva, final String username, final String password){

        final StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPIRESERVAS + '/' + reserva.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("--> RESPOSTA REMOVER: " + response);

                if(reservasListener != null){
                    reservasListener.onUpdateListaReservasBD(reserva,3);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("--> ERRO: removerReservaAPI: " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                user = username;
                pass = password;

                String loginString = user + ":" + pass;

                byte[] loginStringBytes = null;

                try {
                    loginStringBytes = loginString.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String loginStringb64 = Base64.encodeToString(loginStringBytes, Base64.NO_WRAP);

                //  Authorization: Basic $auth
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Basic " + loginStringb64);
                return headers;
            }
        };
        volleyQueue.add(req);
    }

    // Atualiza a reserva na API
    public void editarReservaAPI(final Reserva reserva, final Context context, final String username, final String password){

        StringRequest req = new StringRequest(Request.Method.PUT, mUrlAPIRESERVAS + '/' + reserva.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("--> editarReservaAPI: " + response);

                if(reservasListener != null){
                    reservasListener.onUpdateListaReservasBD(ReservaJsonParser.parserJsonReservas(response, context), 2);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("--> ERRO: editarReservaAPI: " + error.getMessage());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                user = username;
                pass = password;

                String loginString = user + ":" + pass;

                byte[] loginStringBytes = null;

                try {
                    loginStringBytes = loginString.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String loginStringb64 = Base64.encodeToString(loginStringBytes, Base64.NO_WRAP);

                //  Authorization: Basic $auth
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "Basic " + loginStringb64);
                return headers;
            }

            protected Map<String, String> getParams(){

                Map<String, String> params = new HashMap<>();
                params.put("num_pessoas", reserva.getNumPessoas() + "");
                params.put("num_quartos", reserva.getNumQuartos() + "");         // Os Nomes
                params.put("quarto_solteiro", reserva.getQuartoSol() + "");      // têm de
                params.put("quarto_duplo", reserva.getQuartoD() + "");           // corresponder
                params.put("quarto_familia", reserva.getQuartoF() + "");         // aos da
                params.put("quarto_casal", reserva.getQuartoC() + "");           // API
                params.put("data_entrada", reserva.getDtEntrada());
                params.put("data_saida", reserva.getDtSaida());
                params.put("id_cliente", idCliente + "");

                return params;
            }
        };
        volleyQueue.add(req);
    }


    public void setReservasListener(ReservasListener reservasListener){

        this.reservasListener = reservasListener;
    }


    // <--------------------------------------- PEDIDOS --------------------------------------->

    // Vai buscar todos os Pedidos à API
    public void getAllPedidosAPI(final Context context, boolean isConnected){

        Toast.makeText(context, "ISCONNECTED: " + isConnected, Toast.LENGTH_SHORT).show();
        if(!isConnected){
            //Toast.makeText(context, "NotConnected", Toast.LENGTH_SHORT).show();
            pedidos = hotelBDHelper.getAllPedidosBD();

            if(pedidosListener != null){
                pedidosListener.onRefreshListaPedidos(pedidos);
            }
        } else {
            //Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIRESERVAS, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    pedidos = PedidoJsonParser.parserJsonPedidos(response, context);
                    //adicionarPedidosBD(pedidos);

                    if(pedidosListener != null){
                        pedidosListener.onRefreshListaPedidos(pedidos);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //System.out.println("--> ERRO: getAllReservasAPI: " + error.getMessage());
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    String loginString = user + ":" + pass;

                    byte[] loginStringBytes = null;

                    try {
                        loginStringBytes = loginString.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    String loginStringb64 = Base64.encodeToString(loginStringBytes, Base64.NO_WRAP);

                    //  Authorization: Basic $auth
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Basic " + loginStringb64);
                    return headers;
                }

            };


            volleyQueue.add(req);
        }
    }

    public void adicionarPedidoAPI(final Pedido pedido, final Context context){

        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPIPEDIDOS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("--> RESPOSTA ADD POST: " + response);

                if(pedidosListener != null){
                    pedidosListener.onUpdateListaPedidosBD(PedidoJsonParser.parserJsonPedidos(response, context), 1);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO: adicionarPedidosAPI: " + error.getMessage());
            }
        }){
            protected Map<String, String> getParams(){

                Map<String, String> params = new HashMap<>();
                params.put("custo", pedido.getCusto() + "");
                params.put("data_hora", pedido.getDt_hora());
                params.put("id_reservaquarto", pedido.getId_reservaquarto() + "");

                return params;
            }
        };
        volleyQueue.add(req);
    }

    // Remove a reserva da API
    public void removerPedidoAPI(final Pedido pedido){

        final StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPIPEDIDOS + '/' + pedido.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("--> RESPOSTA REMOVER: " + response);

                if(pedidosListener != null){
                    pedidosListener.onUpdateListaPedidosBD(pedido,3);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("--> ERRO: removerPedidoAPI: " + error.getMessage());
            }
        });
        volleyQueue.add(req);
    }

    // Atualiza a reserva na API
    public void editarPedidoAPI(final Pedido pedido, final Context context){

        StringRequest req = new StringRequest(Request.Method.PUT, mUrlAPIPEDIDOS + '/' + pedido.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("--> editarPedidoAPI: " + response);

                if(pedidosListener != null){
                    pedidosListener.onUpdateListaPedidosBD(PedidoJsonParser.parserJsonPedidos(response, context), 2);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("--> ERRO: editarPedidoAPI: " + error.getMessage());
            }
        }){
            protected Map<String, String> getParams(){

                Map<String, String> params = new HashMap<>();
                params.put("custo", pedido.getCusto() + "");
                params.put("data_hora", pedido.getDt_hora());
                params.put("id_reservaquarto", pedido.getId_reservaquarto() + "");

                return params;
            }
        };
        volleyQueue.add(req);
    }



    public void setPedidosListener(PedidosListener pedidosListener){

        this.pedidosListener = pedidosListener;
    }


    // <--------------------------------------- PRODUTOS --------------------------------------->

    public void getAllProdutosAPI(final Context context, boolean isConnected){

        Toast.makeText(context, "ISCONNECTED: " + isConnected, Toast.LENGTH_SHORT).show();
        if(!isConnected){
            //Toast.makeText(context, "NotConnected", Toast.LENGTH_SHORT).show();
            produtos = hotelBDHelper.getAllProdutosBD();

            if(produtosListener != null){
                produtosListener.onRefreshListaProdutos(produtos);
            }
        } else {
            //Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            // System.out.println("--> Reserva id Cliente: " + idCliente);
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIPRODUTOS, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    produtos = ProdutoJsonParser.parserJsonProdutos(response, context);
                    //System.out.println("--> RESPOSTA: " + reservas);
                    adicionarProdutosBD(produtos);

                    if(produtosListener != null){
                        produtosListener.onRefreshListaProdutos(produtos);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--> ERRO: getAllProdutosAPI: " + error.getMessage());
                }
            });
            volleyQueue.add(req);
        }

    }

    public void setProdutosListener(ProdutosListener produtosListener){

        this.produtosListener = produtosListener;
    }

    // <--------------------------------------- QUARTOS --------------------------------------->

    public void getAllQuartosAPI(final Context context, boolean isConnected){

        Toast.makeText(context, "ISCONNECTED: " + isConnected, Toast.LENGTH_SHORT).show();
        if(!isConnected){
            //Toast.makeText(context, "NotConnected", Toast.LENGTH_SHORT).show();
            quartos = hotelBDHelper.getAllQuartoBD();

            if(quartosListener != null){
                quartosListener.onRefreshListaQuartos(quartos);
            }
        } else {
            //Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            // System.out.println("--> Reserva id Cliente: " + idCliente);
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPIQUARTOS, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                   quartos = QuartoJsonParser.parserJsonQuartos(response, context);
                    //System.out.println("--> RESPOSTA: " + reservas);
                    adicionarQuartosBD(quartos);

                    if(quartosListener != null){
                        quartosListener.onRefreshListaQuartos(quartos);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--> ERRO: getAllQuartosAPI: " + error.getMessage());
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    String loginString = user + ":" + pass;

                    byte[] loginStringBytes = null;

                    try {
                        loginStringBytes = loginString.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    String loginStringb64 = Base64.encodeToString(loginStringBytes, Base64.NO_WRAP);

                    //  Authorization: Basic $auth
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Basic " + loginStringb64);
                    return headers;
                }

            };
            volleyQueue.add(req);
        }

    }

    public void setQuartosListener(QuartosListener quartosListener){

        this.quartosListener = quartosListener;
    }

    // <--------------------------------------- TIPO QUARTOS --------------------------------------->

    public void getAllTipoQuartosAPI(final Context context, boolean isConnected){

        Toast.makeText(context, "ISCONNECTED: " + isConnected, Toast.LENGTH_SHORT).show();
        if(!isConnected){
            //Toast.makeText(context, "NotConnected", Toast.LENGTH_SHORT).show();
            tipoQuartos = hotelBDHelper.getAllTipoquartoBD();

            if(tipoQuartosListener != null){
                tipoQuartosListener.onRefreshListaTipoquartos(tipoQuartos);
            }
        } else {
            //Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            // System.out.println("--> Reserva id Cliente: " + idCliente);
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPITIPOQUARTO, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    tipoQuartos = TipoquartoJsonParser.parserJsonTipoquartos(response, context);
                    //System.out.println("--> RESPOSTA: " + reservas);
                    adicionarTipoQuartosBD(tipoQuartos);

                    if(tipoQuartosListener != null){
                        tipoQuartosListener.onRefreshListaTipoquartos(tipoQuartos);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--> ERRO: getAllTipoQuartosAPI: " + error.getMessage());
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    String loginString = user + ":" + pass;

                    byte[] loginStringBytes = null;

                    try {
                        loginStringBytes = loginString.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    String loginStringb64 = Base64.encodeToString(loginStringBytes, Base64.NO_WRAP);

                    //  Authorization: Basic $auth
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Basic " + loginStringb64);
                    return headers;
                }

            };
            volleyQueue.add(req);
        }

    }

    public void setTipoQuartosListener(TipoQuartosListener tipoQuartosListener){

        this.tipoQuartosListener = tipoQuartosListener;
    }

    // <--------------------------------------- TIPO PRODUTOS --------------------------------------->

    public void getAllTipoProdutosAPI(final Context context, boolean isConnected){

        Toast.makeText(context, "ISCONNECTED: " + isConnected, Toast.LENGTH_SHORT).show();
        if(!isConnected){
            //Toast.makeText(context, "NotConnected", Toast.LENGTH_SHORT).show();
            tipoProdutos = hotelBDHelper.getAllTipoprodutoBD();

            if(tipoProdutosListener != null){
                tipoProdutosListener.onRefreshListaTipoProdutos(tipoProdutos);
            }
        } else {
            //Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            // System.out.println("--> Reserva id Cliente: " + idCliente);
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPITIPOPRODUTO, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    tipoProdutos = TipoprodutoJsonParser.parserJsonTipoprodutos(response, context);
                    //System.out.println("--> RESPOSTA: " + reservas);
                    adicionarTipoProdutosBD(tipoProdutos);

                    if(tipoProdutosListener != null){
                        tipoProdutosListener.onRefreshListaTipoProdutos(tipoProdutos);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--> ERRO: getAllTipoProdutosAPI: " + error.getMessage());
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    String loginString = user + ":" + pass;

                    byte[] loginStringBytes = null;

                    try {
                        loginStringBytes = loginString.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    String loginStringb64 = Base64.encodeToString(loginStringBytes, Base64.NO_WRAP);

                    //  Authorization: Basic $auth
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Basic " + loginStringb64);
                    return headers;
                }

            };
            volleyQueue.add(req);
        }

    }

    public void setTipoProdutosListener(TipoProdutosListener tipoProdutosListener){

        this.tipoProdutosListener = tipoProdutosListener;
    }

    // <--------------------------------------- LINHA PRODUTOS --------------------------------------->

    public void getAllLinhaProdutosAPI(final Context context, boolean isConnected){

        Toast.makeText(context, "ISCONNECTED: " + isConnected, Toast.LENGTH_SHORT).show();
        if(!isConnected){
            //Toast.makeText(context, "NotConnected", Toast.LENGTH_SHORT).show();
            linhaprodutos = hotelBDHelper.getAllLinhaprodutosBD();

            if(linhaProdutosListener != null){
                linhaProdutosListener.onRefreshListaLinhaProdutos(linhaprodutos);
            }
        } else {
            //Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            // System.out.println("--> Reserva id Cliente: " + idCliente);
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPILINHAPRODUTO, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    linhaprodutos = LinhaprodutoJsonParser.parserJsonLinhaproduto(response, context);
                    //System.out.println("--> RESPOSTA: " + reservas);
                    adicionarLinhaprodutosBD(linhaprodutos);

                    if(linhaProdutosListener != null){
                        linhaProdutosListener.onRefreshListaLinhaProdutos(linhaprodutos);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--> ERRO: getAllLinhaProdutosAPI: " + error.getMessage());
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {

                    String loginString = user + ":" + pass;

                    byte[] loginStringBytes = null;

                    try {
                        loginStringBytes = loginString.getBytes("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    String loginStringb64 = Base64.encodeToString(loginStringBytes, Base64.NO_WRAP);

                    //  Authorization: Basic $auth
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Authorization", "Basic " + loginStringb64);
                    return headers;
                }

            };
            volleyQueue.add(req);
        }

    }

    public void setLinhaProdutosListener(LinhaProdutosListener linhaProdutosListener){

        this.linhaProdutosListener = linhaProdutosListener;
    }

    // <--------------------------------------- CLASSIFICAÇÕES --------------------------------------->

    public void getAllClassificacoesAPI(final Context context, boolean isConnected){

        Toast.makeText(context, "ISCONNECTED: " + isConnected, Toast.LENGTH_SHORT).show();
        if(!isConnected){
            //Toast.makeText(context, "NotConnected", Toast.LENGTH_SHORT).show();
            classificacaos = hotelBDHelper.getAllClassificacoesBD();

            if(classificacoesListener != null){
                classificacoesListener.onRefreshListaClassificacoes(classificacaos);
            }
        } else {
            JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, mUrlAPICLASSIFICACAO, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    classificacaos = ClassificacaoJsonParser.parserJsonClassificacoes(response, context);
                    //System.out.println("--> RESPOSTA: " + reservas);
                    adicionarClassificacoesBD(classificacaos);

                    if(classificacoesListener != null){
                        classificacoesListener.onRefreshListaClassificacoes(classificacaos);
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("--> ERRO: getAllClassificacoesAPI: " + error.getMessage());
                }
            });
            volleyQueue.add(req);
        }
    }
    // Adicionar 1 Classificação à API
    public void adicionarClassificacaoAPI(final Classificacao classificacao, final Context context){

        StringRequest req = new StringRequest(Request.Method.POST, mUrlAPICLASSIFICACAO, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("--> RESPOSTA ADD POST: " + response);

                if(classificacoesListener != null){
                    classificacoesListener.onUpdateListaClassificacoesBD(ClassificacaoJsonParser.parserJsonClassificacoes(response, context), 1);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("--> ERRO: adicionarClassificacoesAPI: " + error.getMessage());
            }
        }){
            protected Map<String, String> getParams(){

                Map<String, String> params = new HashMap<>();
                params.put("quarto", classificacao.getQuarto() + "");
                params.put("comida", classificacao.getComida() + "");              // Os Nomes
                params.put("staff", classificacao.getStaff() + "");                // têm de
                params.put("servicos", classificacao.getServicos() + "");          // corresponder
                params.put("geral", classificacao.getGeral() + "");                // aos da
                params.put("id_cliente", classificacao.getId_cliente() + "");

                return params;
            }
        };
        volleyQueue.add(req);
    }

    // Remove a Classificacao da API
    public void removerClassificacaoAPI(final Classificacao classificacao){

        final StringRequest req = new StringRequest(Request.Method.DELETE, mUrlAPICLASSIFICACAO + '/' + classificacao.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("--> RESPOSTA REMOVER: " + response);

                if(classificacoesListener != null){
                    classificacoesListener.onUpdateListaClassificacoesBD(classificacao,3);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("--> ERRO: removerClassificacaoAPI: " + error.getMessage());
            }
        });
        volleyQueue.add(req);
    }

    // Atualiza a Classificação na API
    public void editarClassificacaoAPI(final Classificacao classificacao, final Context context){

        StringRequest req = new StringRequest(Request.Method.PUT, mUrlAPICLASSIFICACAO + '/' + classificacao.getId(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("--> editarClassificacaoAPI: " + response);

                if(classificacoesListener != null){
                    classificacoesListener.onUpdateListaClassificacoesBD(ClassificacaoJsonParser.parserJsonClassificacoes(response, context), 2);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                System.out.println("--> ERRO: editarClassificacaoAPI: " + error.getMessage());
            }
        }){
            protected Map<String, String> getParams(){

                Map<String, String> params = new HashMap<>();
                params.put("quarto", classificacao.getQuarto() + "");
                params.put("comida", classificacao.getComida() + "");               // Os Nomes
                params.put("staff", classificacao.getStaff() + "");                 // têm de
                params.put("servicos", classificacao.getServicos() + "");           // corresponder
                params.put("geral", classificacao.getGeral() + "");                 // aos da
                params.put("id_cliente", classificacao.getId_cliente() + "");

                return params;
            }
        };
        volleyQueue.add(req);
    }

    public void setClassificacoesListener(ClassificacoesListener classificacoesListener){

        this.classificacoesListener = classificacoesListener;
    }


    // <------------------------------------------- Métodos OnRefresh e OnUpdate ------------------------------------------->

    // <--------------------------------------- USERS --------------------------------------->

    @Override
    public void onRefreshListaUser(ArrayList<User> listaLivros) {

    }

    @Override
    public void onUpdateListaUserBD(User user, int operacao) {

    }


    // <--------------------------------------- PROFILES --------------------------------------->

    @Override
    public void onRefreshListaProfiles(ArrayList<Profile> listaProfiles) {

    }

    @Override
    public void onUpdateListaProfilesBD(Profile livro, int operacao) {

    }


    // <--------------------------------------- RESERVAS --------------------------------------->

    @Override
    public void onRefreshListaReservas(ArrayList<Reserva> listaReservas) {

    }

    @Override
    public void onUpdateListaReservasBD(Reserva reserva, int operacao) {

        System.out.println("--> Entrou update lista reservasBD");

        switch (operacao){
            case 1: adicionarReservaBD(reserva);
                break;
            case 2: guardarReservaBD(reserva);
                break;
            case 3: removerReservaBD(reserva.getId());
                break;

        }
    }


    // <--------------------------------------- PEDIDOS --------------------------------------->

    @Override
    public void onRefreshListaPedidos(ArrayList<Pedido> listaPedidos) {

    }

    @Override
    public void onUpdateListaPedidosBD(Pedido pedido, int operacao) {

    }

    // <--------------------------------------- PRODUTOS --------------------------------------->

    @Override
    public void onRefreshListaProdutos(ArrayList<Produto> listaProdutos) {

    }

    @Override
    public void onUpdateListaProdutosBD(Produto produto, int operacao) {

    }


    // <----------------------------------------------------------------------------------------------------->

    private void gerarFakeData(){
        /*
            reservas.add(new Reserva(1, 2, 2, 0, 1,0, 1, "9/12/2019", "15/12/2019"));
            reservas.add(new Reserva(2, 1, 1, 1, 0,0, 0, "23/12/2019", "26/12/2019"));
            reservas.add(new Reserva(3, 6, 3, 0, 0,2, 1, "8/01/2020", "14/01/2020"));
            reservas.add(new Reserva(4, 3, 2, 1, 1,0, 0, "20/01/2020", "24/01/2020"));
         */
    }


    @Override
    public void onRefreshListaQuartos(ArrayList<Quarto> listaQuartos) {

    }

    @Override
    public void onUpdateListaQuartosBD(Quarto quarto, int operacao) {

    }

    @Override
    public void onRefreshListaLinhaProdutos(ArrayList<Linhaproduto> listaLinhaproduto) {

    }

    @Override
    public void onUpdateListaLinhaProdutosBD(Linhaproduto linhaproduto, int operacao) {

    }

    @Override
    public void onRefreshListaTipoProdutos(ArrayList<TipoProduto> listaTipoProduto) {

    }

    @Override
    public void onUpdateListaTipoProdutosBD(TipoProduto tipoProduto, int operacao) {

    }

    @Override
    public void onRefreshListaTipoquartos(ArrayList<Tipoquarto> lista) {

    }

    @Override
    public void onUpdateListaTipoquartosBD(Tipoquarto tipoqTipoQuartouarto, int operacao) {

    }

    @Override
    public void onRefreshListaClassificacoes(ArrayList<Classificacao> listaClassificacoes) {

    }

    @Override
    public void onUpdateListaClassificacoesBD(Classificacao classificacao, int operacao) {

    }
}
