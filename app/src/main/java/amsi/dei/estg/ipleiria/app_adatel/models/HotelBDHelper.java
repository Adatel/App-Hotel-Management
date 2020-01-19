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

    ///Campos de Tabela Linha_Produtos
    private static final String ID_LINHAPRODUTO = "id";
    private static final String QUANTIDADE = "quantidade";
    private static final String ID_PRODUTOO = "id_produto";
    private static final String ID_PEDIDOO = "id_pedido";



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

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
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
        values.put(ID, user.getId());
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

        long id_reserva = this.database.insert(TABLE_RESERVA, null , values);
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

    public ArrayList<Pedido> getAllPedidosBD(){
        ArrayList<Pedido> tempPedido = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_PEDIDO, new String[]{
                ID_PEDIDO,
                CUSTO,
                ID_RESERVAQUARTO,
                DATA_HORA},null,null,null,null,null);

        if(cursor.moveToNext()){
            do{
                Pedido auxPedido = new Pedido(cursor.getInt(0),cursor.getInt(1), cursor.getInt(2),
                        cursor.getInt(3),cursor.getString(4));
            }while (cursor.moveToNext());
        }
        return tempPedido;
    }

}
