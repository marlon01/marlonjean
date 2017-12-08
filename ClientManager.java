package marlon.com.libiki;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by marlon on 04/12/17.
 */

public class ClientManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "Libiki";
    private static final String TABLE_NAME = "clients";
    private static final String ID = " _id";
    private static final String NAME = "name";
    private static final String NUM = "num";
    private static final String DATE = "date_ins";


    private static final String TABLE_COMMANDE = "commande";
    private static final String ARTICLES = "article";
    private static final String ID_ARTICLES ="_id";
    private static final String ID_CLIENT = "id_client";
    private static final String DATE_COMMANDE ="date_comnd";
    private static final String PRIX = "prix";
    private static final int VERSION = 1;

    private SQLiteDatabase db = getWritableDatabase();
    public ClientManager(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_COMMANDE+"( _id integer primary key autoincrement,"+
                        ARTICLES+" text not null,"+PRIX+" integer not null,"+ ID_CLIENT+
                " long not null,"+DATE_COMMANDE+" date not null)");

        db.execSQL("create table "+TABLE_NAME+"( _id integer primary key autoincrement," +
                " name text not null, num text not null, date_ins date not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertClient(Client client){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(new java.util.Date());
        ContentValues values = new ContentValues();
        values.put(NAME,client.getName());
        values.put(NUM,client.getNum());
        values.put(DATE,date);
        long result = db.insert(TABLE_NAME,null,values);
        boolean etat ;
        if(result==-1){
            etat = false;
        }else{
            etat = true;
        }
        return etat;
    }

    public Cursor viewData(){
        return db.rawQuery("select * from "+TABLE_NAME+" order by "+DATE+" desc",null);
    }
    public Cursor viewNameNum(long id){
        return db.rawQuery("select "+NAME+","+NUM+" from "+TABLE_NAME+" where "+ID+" = ?",new String[]{String.valueOf(id)});
    }

    public boolean inserCommande(Commande commande){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy");
        String d = simpleDateFormat.format(new java.util.Date());
        ContentValues values = new ContentValues();
        values.put(ARTICLES,commande.getArticle());
        values.put(ID_CLIENT,commande.getId_client());
        values.put(PRIX,commande.getPrix());
        values.put(DATE_COMMANDE,d);
        long result = db.insert(TABLE_COMMANDE,null,values);
        boolean etat = false;
        if(result == -1){
          etat = false;
        }else{
            etat = true;
        }
        return etat;
    }

    public Cursor viewCommande(long id ){
        return db.rawQuery("select "+ARTICLES+","+PRIX+","+DATE_COMMANDE+" from "+TABLE_COMMANDE+" where "+ID_CLIENT+"=? order by "+DATE_COMMANDE+" desc",new String[]{String.valueOf(id)});
    }

    public Cursor getTotal(long id){
        return db.rawQuery("select sum("+PRIX+") as total from "+TABLE_COMMANDE+" where "+ID_CLIENT+"=?",new String[]{String.valueOf(id)});
    }

    public void deleteClients(long id){
        db.delete(TABLE_NAME,ID+" = ?",new String[]{String.valueOf(id)});
        db.delete(TABLE_COMMANDE,ID_CLIENT+" = ?",new String[]{String.valueOf(id)});
    }
}
