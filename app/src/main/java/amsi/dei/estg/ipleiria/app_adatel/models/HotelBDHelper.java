package amsi.dei.estg.ipleiria.app_adatel.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HotelBDHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 8;
    private static final String DB_NAME = "adatel";
    private static final String TABLE_USER = "User";
    private static final String TABLE_PROFILE = "Profile";
    private static final String TABLE_RESERVA = "Reserva";

    ///Campos da table User
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
    private static final String QUARTO_SOLTEIRO = "quarto_solteiro";
    private static final String QUARTO_DUPLO = "quarto_duplo";
    private static final String QUARTO_FAMILIA = "quarto_familia";
    private static final String QUARTO_CASAL = "quarto_casal";
    private static final String DATA_ENTRADA = "data_entrada";
    private static final String DATA_SAIDA = "data_saida";
    private static final String ID_CLIENTE = "ic_cliente";


    private SQLiteDatabase database;

    public HotelBDHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_USER
                + "(" + ID + " INTEGER PRIMARY KEY ,AUTOINCREMENTE, "
                + USERNAME + " TEXT NOT NULL, "
                + PASSWORD + " TEXT NOT NULL, "
                + EMAIL + " TEXT NOT NULL);";
        db.execSQL(createUserTable);

        String createProfileTable = "CREATE TABLE " + TABLE_PROFILE
                + "(" + NOME + " TEXT NOT NULL, "
                + NIF + " INTEGER PRIMARY KEY ,AUTOINCREMENTE, "
                + TELEMOVEL + " INTEGER, "
                + MORADA + " TEXT NOT NULL, "
                + IS_ADMIN + " INTEGER, "
                + IS_CLIENTE + " INTEGER, "
                + IS_FUNCIONARIO + " INTEGER, "
                + ID_USER + " INTEGER);";
        db.execSQL(createProfileTable);

        String createReservaTable = "CREATE TABLE " + TABLE_RESERVA
                + "(" + ID_RESERVA + "INTEGER PRIMARY KEY ,AUTOINCREMENTE, "
                + NUM_PESSOAS + " INTEGER NOT NULL, "
                + QUARTO_SOLTEIRO + " INTEGER NOT NULL, "
                + QUARTO_DUPLO + " INTEGER NOT NULL, "
                + QUARTO_FAMILIA + " INTEGER NOT NULL, "
                + QUARTO_CASAL + " INTEGER NOT NULL, "
                + DATA_ENTRADA + " INTEGER NOT NULL, "
                + DATA_SAIDA + " INTEGER NOT NULL, "
                + ID_CLIENTE + " INTEGER);";
        db.execSQL(createReservaTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        this.onCreate(db);
    }


    //***************************** METODOS CRUD**********************************//

    public ArrayList<User> getAllUsers(){
        ArrayList<User> TempUsers = new ArrayList<>();

        Cursor cursor = this.database.query(TABLE_USER, new String[]{
                ID,
                USERNAME,
                PASSWORD,
                EMAIL},
                null,null,null, null, null);

        if(cursor.moveToNext()){
            do{
                User auxUser = new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
                TempUsers.add(auxUser);
            }while (cursor.moveToNext());
        }
        return TempUsers;
    }

    public  ArrayList<Profile> getAllProfiles(){
        ArrayList<Profile> TempProfile = new ArrayList<>();

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
        return TempProfile;
    }



    public void adicionarUserBD(User user){
        ContentValues values = new ContentValues();
        values.put(ID, user.getId());
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());
        values.put(EMAIL, user.getEmail());

        this.database.insert(TABLE_USER, null, values);
    }

    public Profile adicionarProfileBD(Profile profile){
        ///Maneira Antiga corrigir depois
        ContentValues values = new ContentValues();
        values.put(NOME, profile.getNome());
        values.put(NIF, profile.getNif());
        values.put(TELEMOVEL, profile.getTelefone());
        values.put(IS_ADMIN, profile.getId_user());
        values.put(IS_CLIENTE, profile.getIs_cliente());
        values.put(IS_FUNCIONARIO, profile.getIs_funcionario());
        values.put(ID_USER, profile.getId_user());

        long id_user = this.database.insert(TABLE_PROFILE, null , values);

        if(id_user > -1){
            profile.setId_user((int)id_user);
            return profile;
        }
        return null;
    }

    public boolean guardarUserBD(User user){
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());
        values.put(EMAIL, user.getEmail());

        return this.database.update(TABLE_USER,values,"id = ?", new String[]{"" + user.getId()}) > 0;
    }

    public boolean guardarProfileBD(Profile profile){
        ContentValues values = new ContentValues();
        values.put(NOME, profile.getNome());
        values.put(NIF, profile.getNif());
        values.put(TELEMOVEL, profile.getTelefone());
        values.put(IS_ADMIN, profile.getIs_admin());
        values.put(IS_CLIENTE, profile.getIs_cliente());
        values.put(IS_FUNCIONARIO, profile.getIs_funcionario());

        return this.database.update(TABLE_PROFILE, values, "id_user = ?", new String[]{"" + profile.getId_user()}) > 0;
    }

    public boolean removerUserBD(int id){
        return (this.database.delete(TABLE_USER, "id = ?", new String[]{"" + id}) == 1);
    }

    public boolean removerProfileBD(int id_user){
        return  (this.database.delete(TABLE_PROFILE, "id_user = ?", new String[]{"" + id_user}) == 1);
    }

    public void removerAllUsers(){
        this.database.delete(TABLE_USER, null,null);
    }

    public void removerALLProfiles(){
        this.database.delete(TABLE_PROFILE, null, null);
    }


}
