package amsi.dei.estg.ipleiria.app_adatel.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class HotelBDHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 8;
    private static final String DB_NAME = "adatel";
    private static final String TABLE_USER = "User";
    private static final String TABLE_PROFILE = "Profile";
    private static final String TABLE_RESERVA = "Reserva";
    private static final String TABLE_RESERVAQUARTO = "Reservaquarto";
    private static final String TABLE_QUARTO = "Quarto";
    private static final String TABLE_TIPOQUARTO = "Tipoquarto";
    private static final String TABLE_PRODUTO = "Produto";
    private static final String TABLE_TIPOPRODUTO = "Tipoproduto";
    private static final String TABLE_PEDIDO = "Pedido";
    private static final String TABLE_LINHAPRODUTO = "Linhaproduto";
    private static final String TABLE_CLASSIFICACAO = "Classificacao";

    ///Campos da Tabela User
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";

    ///Campos da Tabela Profile
    private static final String NOME = "nome";
    private static final String NIF = "nif";
    private static final String TELEMOVEL = "telemovel";
    private static final String MORADA = "morada";
    private static final String IS_ADMIN = "is_admin";
    private static final String IS_CLIENTE = "is_cliente";
    private static final String IS_FUNCIONARIO = "is_funcionario";
    private static final String ID_USER = "id_cliente";

    ///Campos da Tabela Reserva
    private static final String ID_RESERVA = "id";
    private static final String NUM_PESSOAS = "num_pessoas";
    private static final String NUM_QUARTOS = "num_quartos";
    private static final String QUARTO_SOLTEIRO = "quarto_solteiro";
    private static final String QUARTO_DUPLO = "quarto_duplo";
    private static final String QUARTO_FAMILIA = "quarto_familia";
    private static final String QUARTO_CASAL = "quarto_casal";
    private static final String DATA_ENTRADA = "data_entrada";
    private static final String DATA_SAIDA = "data_saida";
    private static final String ID_CLIENTE = "id_cliente";

    ///Campos da Tabela Reservaquarto
    private static final String ID_RESERVAQUARTO = "id";
    private static final String ID_RESERVAA = "id_reserva";
    private static final String ID_QUARTO = "id_quarto";

    ///Campos da tabela Tipo Quarto
    private static final String ID_TIPOQUARTO = "id";
    private static final String DESIGNACAO = "designacao";
    private static final String PRECO_NOITE = "preco_noite";

    ///Campos da Tabela Quarto
    private static final String NUM_QUARTO = "num_quarto";
    private static final String ID_TIPO = "id_tipo";
    private static final String ESTADO = "estado";

    ///Campos da Tabela Pedidos
    private static final String ID_PEDIDO = "id";
    private static final String CUSTO = "custo";
    private static final String ID_RESERVAQUARTOO = "id_reservaquarto";
    private static final String DATA_HORA = "data_hora";

    ///Campos da Tabela Tipo Produto
    private static final String ID_TIPOPRODUTO = "id";
    private static final String DESCRICAO_TIPO = "descricao_tipo";

    ///Campos da Tabela Produtos
    private static final String ID_PRODUTO = "id";
    private static final String DESIGNACAO_PRODUTO = "designacao";
    private static final String PRECO_UNITARIO = "preco_unitario";
    private static final String ID_TIPOPRODUTOO = "id_tipo";

    ///Campos da Tabela Linha_Produtos
    private static final String ID_LINHAPRODUTO = "id";
    private static final String QUANTIDADE = "quantidade";
    private static final String ID_PRODUTOO = "id_produto";
    private static final String ID_PEDIDOO = "id_pedido";

    ///Campos da Tabela Classificação
    private static final String ID_CLASSIFICACAO = "id";
    private static final String QUARTO = "quarto";
    private static final String COMIDA = "comida";
    private static final String STAFF = "staff";
    private static final String SERVICOS = "servicos";
    private static final String GERAL = "geral";
    private static final String CLIENTE = "id_cliente";



    private SQLiteDatabase database;

    public HotelBDHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_USER
                + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME + " TEXT NOT NULL, "
                + PASSWORD + " TEXT NOT NULL, "
                + EMAIL + " TEXT NOT NULL);";
        db.execSQL(createUserTable);

        String createProfileTable = "CREATE TABLE " + TABLE_PROFILE
                + "(" + NOME + " TEXT NOT NULL, "
                + NIF + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TELEMOVEL + " INTEGER, "
                + MORADA + " TEXT NOT NULL, "
                + IS_ADMIN + " INTEGER, "
                + IS_CLIENTE + " INTEGER, "
                + IS_FUNCIONARIO + " INTEGER, "
                + ID_USER + " INTEGER);";
        db.execSQL(createProfileTable);

        String createReservaTable = "CREATE TABLE " + TABLE_RESERVA
                + "(" + ID_RESERVA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NUM_PESSOAS + " INTEGER NOT NULL, "
                + NUM_QUARTOS + " INTEGER NOT NULL, "
                + QUARTO_SOLTEIRO + " INTEGER NOT NULL, "
                + QUARTO_DUPLO + " INTEGER NOT NULL, "
                + QUARTO_FAMILIA + " INTEGER NOT NULL, "
                + QUARTO_CASAL + " INTEGER NOT NULL, "
                + DATA_ENTRADA + " INTEGER NOT NULL, "
                + DATA_SAIDA + " INTEGER NOT NULL, "
                + ID_CLIENTE + " INTEGER);";
        db.execSQL(createReservaTable);

        String createTipoquartoTable = "CREATE TABLE " + TABLE_TIPOQUARTO
                + "(" + ID_TIPOQUARTO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DESIGNACAO + " TEXT NOT NULL, "
                + PRECO_NOITE + " INTEGER NOT NULL);";
        db.execSQL(createTipoquartoTable);

        String createQuartoTable = "CREATE TABLE " + TABLE_QUARTO
                + "(" + NUM_QUARTO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ID_TIPO + " INTEGER NOT NULL, "
                + ESTADO + " INTEGER);";
        db.execSQL(createQuartoTable);

        String createReservaquartoTable = "CREATE TABLE " + TABLE_RESERVAQUARTO
                + "(" + ID_RESERVAQUARTO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ID_RESERVAA + " INTEGER NOT NULL, "
                + ID_QUARTO + " INTEGER NOT NULL);";
        db.execSQL(createReservaquartoTable);

        String createPedidoTable = "CREATE TABLE " + TABLE_PEDIDO
                + "(" + ID_PEDIDO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CUSTO + " INTEGER NOT NULL, "
                + DATA_HORA + " TEXT NOT NULL, "
                + ID_RESERVAQUARTOO + " INTEGER NOT NULL);";
        db.execSQL(createPedidoTable);

        String createTipoprodutoTable = "CREATE TABLE " + TABLE_TIPOPRODUTO
                + "(" + ID_TIPOPRODUTO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DESCRICAO_TIPO + " TEXT NOT NULL);";
        db.execSQL(createTipoprodutoTable);

        String createProdutoTable = "CREATE TABLE " + TABLE_PRODUTO
                + "(" + ID_PRODUTO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DESIGNACAO_PRODUTO + " TEXT NOT NULL, "
                + PRECO_UNITARIO + " INTEGER NOT NULL, "
                + ID_TIPOPRODUTOO + " INTEGER NOT NULL);";
        db.execSQL(createProdutoTable);

        String createLinhaprodutoTable = "CREATE TABLE " + TABLE_LINHAPRODUTO
                + "(" + ID_LINHAPRODUTO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QUANTIDADE + " INTEGER NOT NULL, "
                + ID_PRODUTOO + " INTEGER NOT NULL, "
                + ID_PEDIDOO + " INTEGER NOT NULL);";
        db.execSQL(createLinhaprodutoTable);

        String createClassificacaoTable = "CREATE TABLE " + TABLE_CLASSIFICACAO
                + "(" + ID_CLASSIFICACAO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + QUARTO + " REAL NOT NULL, "
                + COMIDA + " REAL NOT NULL, "
                + STAFF + " REAL NOT NULL, "
                + SERVICOS + " REAL NOT NULL, "
                + GERAL + " REAL NOT NULL, "
                + CLIENTE + " INTEGER NOT NULL);";
        db.execSQL(createClassificacaoTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSIFICACAO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LINHAPRODUTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPOPRODUTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEDIDO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVAQUARTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUARTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPOQUARTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        this.onCreate(db);
    }


    //***************************** METODOS CRUD**********************************//

    // <------------------- USER ------------------->

    public ArrayList<User> getAllUsersBD(){
        ArrayList<User> tempUsers = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_USER, new String[]{
                ID,
                USERNAME,
                PASSWORD,
                EMAIL},
                null,null,null, null, null);

        if(cursor.moveToNext()){
            do{
                User auxUser = new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                tempUsers.add(auxUser);
            }while (cursor.moveToNext());
        }
        return tempUsers;
    }

    public void adicionarUserBD(User user){

        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());
        values.put(EMAIL, user.getEmail());

        this.database.insert(TABLE_USER, null, values);
    }

    public boolean guardarUserBD(User user){
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());
        values.put(EMAIL, user.getEmail());

        return this.database.update(TABLE_USER,values,"id = ?", new String[]{"" + user.getId()}) > 0;
    }

    public boolean removerUserBD(int id){
        return (this.database.delete(TABLE_USER, "id = ?", new String[]{"" + id}) == 1);
    }

    public void removerAllUsers(){
        this.database.delete(TABLE_USER, null,null);
    }


    // <------------------- PROFILE ------------------->


    public ArrayList<Profile> getAllProfilesBD(){
        ArrayList<Profile> tempProfile = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_PROFILE, new String[]{
                NOME,
                NIF,
                TELEMOVEL,
                MORADA,
                IS_ADMIN,
                IS_CLIENTE,
                IS_FUNCIONARIO,
                ID_USER},null,null,null,null,null);

        if(cursor.moveToNext()){
            do{
                Profile auxProfile = new Profile(cursor.getString(0),cursor.getInt(1), cursor.getInt(2),
                        cursor.getString(3),cursor.getInt(4),cursor.getInt(5),
                        cursor.getInt(6), cursor.getInt(7));
            }while (cursor.moveToNext());
        }
        return tempProfile;
    }

    public void adicionarProfileBD(Profile profile){

        ContentValues values = new ContentValues();

        values.put(NOME, profile.getNome());
        values.put(NIF, profile.getNif());
        values.put(TELEMOVEL, profile.getTelefone());
        values.put(IS_ADMIN, profile.getId_user());
        values.put(IS_CLIENTE, profile.getIs_cliente());
        values.put(IS_FUNCIONARIO, profile.getIs_funcionario());
        values.put(ID_USER, profile.getId_user());

        this.database.insert(TABLE_PROFILE, null , values);
    }

    public boolean guardarProfileBD(Profile profile){

        ContentValues values = new ContentValues();
        values.put(NOME, profile.getNome());
        values.put(NIF, profile.getNif());
        values.put(TELEMOVEL, profile.getTelefone());
        values.put(IS_ADMIN, profile.getIs_admin());
        values.put(IS_CLIENTE, profile.getIs_cliente());
        values.put(IS_FUNCIONARIO, profile.getIs_funcionario());

        return this.database.update(TABLE_PROFILE, values, "id = ?", new String[]{"" + profile.getId_user()}) > 0;
    }

    public boolean removerProfileBD(int id_user){
        return  (this.database.delete(TABLE_PROFILE, "id = ?", new String[]{"" + id_user}) == 1);
    }

    public void removerALLProfilesDB(){
        this.database.delete(TABLE_PROFILE, null, null);
    }


    // <------------------- RESERVA ------------------->

    public ArrayList<Reserva> getAllReservasBD(){
        ArrayList<Reserva> tempReserva = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_RESERVA, new String[]{
                ID_RESERVA,
                NUM_PESSOAS,
                NUM_QUARTOS,
                QUARTO_SOLTEIRO,
                QUARTO_DUPLO,
                QUARTO_FAMILIA,
                QUARTO_CASAL,
                DATA_ENTRADA,
                DATA_SAIDA,
                ID_CLIENTE},null,null,null,null,null);

        if(cursor.moveToNext()){
            do{
                Reserva auxReserva = new Reserva(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                        cursor.getInt(3),cursor.getInt(4),cursor.getInt(5), cursor.getInt(6),
                        cursor.getString(7), cursor.getString(8), cursor.getInt(9));
            }while (cursor.moveToNext());
        }
        return tempReserva;
    }

    public void adicionarReservaBD(Reserva reserva){

        ContentValues values = new ContentValues();
        values.put(NUM_PESSOAS, reserva.getNumPessoas());
        values.put(NUM_QUARTOS, reserva.getNumQuartos());
        values.put(QUARTO_SOLTEIRO, reserva.getQuartoSol());
        values.put(QUARTO_DUPLO, reserva.getQuartoD());
        values.put(QUARTO_FAMILIA, reserva.getQuartoF());
        values.put(QUARTO_CASAL, reserva.getQuartoC());
        values.put(DATA_ENTRADA, reserva.getDtEntrada());
        values.put(DATA_SAIDA, reserva.getDtSaida());
        values.put(ID_CLIENTE, reserva.getIdCliente());

        this.database.insert(TABLE_RESERVA, null , values);
    }

    public boolean guardarReservaBD(Reserva reserva){

        ContentValues values = new ContentValues();
        values.put(NUM_PESSOAS, reserva.getNumPessoas());
        values.put(NUM_QUARTOS, reserva.getNumQuartos());
        values.put(QUARTO_SOLTEIRO, reserva.getQuartoSol());
        values.put(QUARTO_DUPLO, reserva.getQuartoD());
        values.put(QUARTO_FAMILIA, reserva.getQuartoF());
        values.put(QUARTO_CASAL, reserva.getQuartoC());
        values.put(DATA_ENTRADA, reserva.getDtEntrada());
        values.put(DATA_SAIDA, reserva.getDtSaida());
        values.put(ID_CLIENTE, reserva.getIdCliente());

        return this.database.update(TABLE_RESERVA, values, "id = ?", new String[]{"" + reserva.getId()}) > 0;
    }

    public boolean removerReservaBD(int id_reserva){
        return  (this.database.delete(TABLE_RESERVA, "id = ?", new String[]{"" + id_reserva}) == 1);
    }

    public void removerALLReservasDB(){
        this.database.delete(TABLE_RESERVA, null, null);
    }


    // <------------------- TIPO QUARTO ------------------->

    public ArrayList<Tipoquarto> getAllTipoquartoBD(){
        ArrayList<Tipoquarto> tempTipoquarto = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_TIPOQUARTO, new String[]{
                ID_TIPOQUARTO,
                PRECO_NOITE,
                DESIGNACAO,},null,null,null,null,null);

        if(cursor.moveToNext()){
            do{
                Tipoquarto auxTipoquarto = new Tipoquarto(cursor.getInt(0),cursor.getInt(1), cursor.getString(2));
            }while (cursor.moveToNext());
        }
        return tempTipoquarto;
    }

    public void adicionarTipoquartoBD(Tipoquarto tipoquarto){

        ContentValues values = new ContentValues();
        values.put(PRECO_NOITE, tipoquarto.getPreco_noite());
        values.put(DESIGNACAO, tipoquarto.getDesignacao());

        this.database.insert(TABLE_TIPOQUARTO, null , values);
    }

    public boolean guardarTipoquartoBD(Tipoquarto tipoquarto){

        ContentValues values = new ContentValues();
        values.put(PRECO_NOITE, tipoquarto.getPreco_noite());
        values.put(DESIGNACAO, tipoquarto.getDesignacao());

        return this.database.update(TABLE_TIPOQUARTO, values, "id = ?", new String[]{"" + tipoquarto.getId()}) > 0;
    }

    public boolean removerTipoquartoBD(int id_tipoquarto){
        return  (this.database.delete(TABLE_TIPOQUARTO, "id = ?", new String[]{"" + id_tipoquarto}) == 1);
    }

    public void removerALLTipoquartosDB(){
        this.database.delete(TABLE_TIPOQUARTO, null, null);
    }


    // <------------------- QUARTO ------------------->

    public ArrayList<Quarto> getAllQuartoBD(){
        ArrayList<Quarto> tempQuarto = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_QUARTO, new String[]{
                NUM_QUARTO,
                ID_TIPOQUARTO,
                ESTADO,},null,null,null,null,null);

        if(cursor.moveToNext()){
            do{
                Quarto auxQuarto = new Quarto(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2));
            }while (cursor.moveToNext());
        }
        return tempQuarto;
    }

    public void adicionarQuartoBD(Quarto quarto){

        ContentValues values = new ContentValues();
        values.put(ID_TIPO, quarto.getId_tipo());
        values.put(ESTADO, quarto.getEstado());

        this.database.insert(TABLE_QUARTO, null , values);
    }

    public boolean guardarquartoBD(Quarto quarto){

        ContentValues values = new ContentValues();
        values.put(ID_TIPO, quarto.getId_tipo());
        values.put(ESTADO, quarto.getEstado());

        return this.database.update(TABLE_QUARTO, values, "id = ?", new String[]{"" + quarto.getNum_quarto()}) > 0;
    }

    public boolean removerQuartoBD(int id_quarto){
        return  (this.database.delete(TABLE_QUARTO, "id = ?", new String[]{"" + id_quarto}) == 1);
    }

    public void removerALLQuartosDB(){
        this.database.delete(TABLE_QUARTO, null, null);
    }


    // <------------------- RESERVA QUARTO ------------------->

    public ArrayList<Reservaquarto> getAllReservaquartoBD(){
        ArrayList<Reservaquarto> tempReservaquarto = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_RESERVAQUARTO, new String[]{
                ID_RESERVAQUARTO,
                ID_RESERVAA,
                ID_QUARTO,},null,null,null,null,null);

        if(cursor.moveToNext()){
            do{
                Reservaquarto auxReservaquarto = new Reservaquarto(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2));
            }while (cursor.moveToNext());
        }
        return tempReservaquarto;
    }

    public void adicionarReservaquartoBD(Reservaquarto reservaquarto){

        ContentValues values = new ContentValues();
        values.put(ID_RESERVAA, reservaquarto.getIdReserva());
        values.put(ID_QUARTO, reservaquarto.getIdQuarto());

        this.database.insert(TABLE_RESERVAQUARTO, null , values);
    }

    public boolean guardarReservaQuartoBD(Reservaquarto reservaquarto){

        ContentValues values = new ContentValues();
        values.put(ID_RESERVAA, reservaquarto.getIdReserva());
        values.put(ID_QUARTO, reservaquarto.getIdQuarto());

        return this.database.update(TABLE_RESERVAQUARTO, values, "id = ?", new String[]{"" + reservaquarto.getId()}) > 0;
    }

    public boolean removerReservaquartoBD(int id_reservaquarto){
        return  (this.database.delete(TABLE_RESERVAQUARTO, "id = ?", new String[]{"" + id_reservaquarto}) == 1);
    }

    public void removerALLReservaquartosDB(){
        this.database.delete(TABLE_RESERVAQUARTO, null, null);
    }

    // <------------------------------------ PEDIDOS ------------------------------------>
    public ArrayList<Pedido> getAllPedidosBD(){
        ArrayList<Pedido> tempPedido = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_PEDIDO, new String[]{
                ID_PEDIDO,
                CUSTO,
                ID_RESERVAQUARTOO,
                DATA_HORA},null,null,null,null,null);

        if(cursor.moveToNext()){
            do{
                Pedido auxPedido = new Pedido(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                        cursor.getString(3));
            }while (cursor.moveToNext());
        }
        return tempPedido;
    }

    public void adicionarPedidoBD(Pedido pedido){

        ContentValues values = new ContentValues();
        values.put(CUSTO, pedido.getCusto());
        values.put(ID_RESERVAQUARTOO, pedido.getId_reservaquarto());
        values.put(DATA_HORA, pedido.getDt_hora());

        this.database.insert(TABLE_PEDIDO, null , values);
    }

    public boolean guardarPedidoBD(Pedido pedido){

        ContentValues values = new ContentValues();
        values.put(CUSTO, pedido.getCusto());
        values.put(ID_RESERVAQUARTOO, pedido.getId_reservaquarto());
        values.put(DATA_HORA, pedido.getDt_hora());

        return this.database.update(TABLE_PEDIDO, values, "id = ?", new String[]{"" + pedido.getId()}) > 0;
    }

    public boolean removerPedidoBD(int id_pedido){
        return  (this.database.delete(TABLE_PEDIDO, "id = ?", new String[]{"" + id_pedido}) == 1);
    }

    public void removerALLRPedidosDB(){
        this.database.delete(TABLE_PEDIDO, null, null);
    }

    // <------------------------------------ TIPO PRODUTO ------------------------------------>
    public ArrayList<TipoProduto> getAllTipoprodutoBD(){
        ArrayList<TipoProduto> tempTipoproduto = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_TIPOPRODUTO, new String[]{
                ID_TIPOPRODUTO,
                DESCRICAO_TIPO,},null,null,null,null,null);

        if(cursor.moveToNext()){
            do{
                TipoProduto auxTipoproduto = new TipoProduto(cursor.getInt(0),cursor.getString(1));
            }while (cursor.moveToNext());
        }
        return tempTipoproduto;
    }

    public void adicionarTipoprodutoBD(TipoProduto tipoProduto){

        ContentValues values = new ContentValues();
        values.put(DESCRICAO_TIPO, tipoProduto.getDescricao());

        this.database.insert(TABLE_TIPOPRODUTO, null , values);
    }

    public boolean guardarTipoProdutoBD(TipoProduto tipoProduto){

        ContentValues values = new ContentValues();
        values.put(DESCRICAO_TIPO, tipoProduto.getDescricao());

        return this.database.update(TABLE_TIPOPRODUTO, values, "id = ?", new String[]{"" + tipoProduto.getId()}) > 0;
    }

    public boolean removerTipoprodutoBD(int id_tipoproduto){
        return  (this.database.delete(TABLE_TIPOPRODUTO, "id = ?", new String[]{"" + id_tipoproduto}) == 1);
    }

    public void removerALLTipoprodutoDB(){
        this.database.delete(TABLE_TIPOPRODUTO, null, null);
    }


    // <------------------- PRODUTO ------------------->

    public ArrayList<Produto> getAllProdutosBD(){
        ArrayList<Produto> tempProduto = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_PRODUTO, new String[]{
                ID_PRODUTO,
                PRECO_UNITARIO,
                ID_TIPOPRODUTOO,
                DESIGNACAO_PRODUTO,},null,null,null,null,null);

        if(cursor.moveToNext()){
            do{
                Produto auxRProduto = new Produto(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                        cursor.getString(3));
            }while (cursor.moveToNext());
        }
        return tempProduto;
    }

    public void adicionarProdutoBD(Produto produto){

        ContentValues values = new ContentValues();
        values.put(PRECO_UNITARIO, produto.getPreco_unitario());
        values.put(ID_TIPOPRODUTOO, produto.getId_tipoproduto());
        values.put(DESIGNACAO_PRODUTO, produto.getDesignacao());

        this.database.insert(TABLE_PRODUTO, null , values);
    }

    public boolean guardarProdutoBD(Produto produto){

        ContentValues values = new ContentValues();
        values.put(PRECO_UNITARIO, produto.getPreco_unitario());
        values.put(ID_TIPOPRODUTOO, produto.getId_tipoproduto());
        values.put(DESIGNACAO_PRODUTO, produto.getDesignacao());

        return this.database.update(TABLE_PRODUTO, values, "id = ?", new String[]{"" + produto.getId()}) > 0;
    }

    public boolean removerProdutoBD(int id_produto){
        return  (this.database.delete(TABLE_PRODUTO, "id = ?", new String[]{"" + id_produto}) == 1);
    }

    public void removerALLProdutosDB(){
        this.database.delete(TABLE_PRODUTO, null, null);
    }


    // <------------------- LINHA PRODUTO ------------------->

    public ArrayList<Linhaproduto> getAllLinhaprodutosBD(){
        ArrayList<Linhaproduto> tempLinhaproduto = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_LINHAPRODUTO, new String[]{
                ID_LINHAPRODUTO,
                QUANTIDADE,
                ID_PRODUTOO,
                ID_PEDIDOO,},null,null,null,null,null);

        if(cursor.moveToNext()){
            do{
                Linhaproduto auxLinhaproduto = new Linhaproduto(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                        cursor.getInt(3));
            }while (cursor.moveToNext());
        }
        return tempLinhaproduto;
    }

    public void adicionarLinhaprodutoBD(Linhaproduto linhaproduto){

        ContentValues values = new ContentValues();
        values.put(QUANTIDADE, linhaproduto.getQuantidade());
        values.put(ID_PRODUTOO, linhaproduto.getId_produto());
        values.put(ID_PEDIDOO, linhaproduto.getId_pedido());

        this.database.insert(TABLE_LINHAPRODUTO, null , values);
    }

    public boolean guardarLinhaprodutoBD(Linhaproduto linhaproduto){

        ContentValues values = new ContentValues();
        values.put(QUANTIDADE, linhaproduto.getQuantidade());
        values.put(ID_PRODUTOO, linhaproduto.getId_produto());
        values.put(ID_PEDIDOO, linhaproduto.getId_pedido());

        return this.database.update(TABLE_LINHAPRODUTO, values, "id = ?", new String[]{"" + linhaproduto.getId()}) > 0;
    }

    public boolean removerLinhaprodutoBD(int id_linhaproduto){
        return  (this.database.delete(TABLE_LINHAPRODUTO, "id = ?", new String[]{"" + id_linhaproduto}) == 1);
    }

    public void removerALLLinhaprodutosDB(){
        this.database.delete(TABLE_LINHAPRODUTO, null, null);
    }


    // <------------------- CLASSIFICAÇÃO ------------------->

    public ArrayList<Classificacao> getAllClassificacoesBD(){
        ArrayList<Classificacao> tempClassificacao = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_CLASSIFICACAO, new String[]{
                ID_CLASSIFICACAO,
                QUARTO,
                COMIDA,
                STAFF,
                SERVICOS,
                GERAL,
                CLIENTE},null,null,null,null,null);

        if(cursor.moveToNext()){
            do{
                Classificacao auxClassificacao = new Classificacao(cursor.getInt(0),cursor.getFloat(1), cursor.getFloat(2),
                        cursor.getFloat(3),cursor.getFloat(4),cursor.getFloat(5), cursor.getInt(6));
            }while (cursor.moveToNext());
        }
        return tempClassificacao;
    }

    public void adicionarClassificacaoBD(Classificacao classificacao){

        ContentValues values = new ContentValues();
        values.put(QUARTO, classificacao.getQuarto());
        values.put(COMIDA, classificacao.getComida());
        values.put(STAFF, classificacao.getStaff());
        values.put(SERVICOS, classificacao.getServicos());
        values.put(GERAL, classificacao.getGeral());
        values.put(CLIENTE, classificacao.getId_cliente());

        this.database.insert(TABLE_CLASSIFICACAO, null , values);
    }

    public boolean guardarClassificacaoBD(Classificacao classificacao){

        ContentValues values = new ContentValues();
        values.put(QUARTO, classificacao.getQuarto());
        values.put(COMIDA, classificacao.getComida());
        values.put(STAFF, classificacao.getStaff());
        values.put(SERVICOS, classificacao.getServicos());
        values.put(GERAL, classificacao.getGeral());
        values.put(CLIENTE, classificacao.getId_cliente());

        return this.database.update(TABLE_CLASSIFICACAO, values, "id = ?", new String[]{"" + classificacao.getId()}) > 0;
    }

    public boolean removerClassificacaoBD(int id_classificacao){
        return  (this.database.delete(TABLE_CLASSIFICACAO, "id = ?", new String[]{"" + id_classificacao}) == 1);
    }

    public void removerALLClassificacaoDB(){
        this.database.delete(TABLE_CLASSIFICACAO, null, null);
    }


}
