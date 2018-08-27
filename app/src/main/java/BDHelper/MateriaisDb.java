package BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import model.Materiais;

public class MateriaisDb extends SQLiteOpenHelper {

    private static final String DATABASE="bdmateriais";
    private static final int VERSION = 1;

    public MateriaisDb (Context context){
        super(context, DATABASE,null,VERSION);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String material = "CREATE TABLE materiais(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomematerial TEXT NOT NULL, descmaterial TEXT NOT NULL, quantidade INTEGER);";
    db.execSQL(material);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String material = "DROP TABLE IF EXISTS materiais";
        db.execSQL(material);
    }

    //salvar os items
    public void salvarMaterial (Materiais material){
            ContentValues values = new ContentValues();
            values.put("nomematerial", material.getNomeMaterial());
            values.put("descmaterial", material.getDescMaterial());
            values.put("quantidade", material.getQuantidade());
            getWritableDatabase().insert("materiais",null,values);




    }
    //alteracoes nos itens
    public void alterarMaterial(Materiais material) {
        ContentValues values = new ContentValues();
        values.put("nomematerial", material.getNomeMaterial());
        values.put("descmaterial", material.getDescMaterial());
        values.put("quantidade", material.getQuantidade());

        String[] args ={material.getId().toString()};
        getWritableDatabase().update("materiais",values,"id=?",args);


    }


    public void deletarMaterial(Materiais material){

        String[] args ={material.getId().toString()};
        getWritableDatabase().delete("materiais","id=?",args);


    }



    ///lista material - mostrar


 public ArrayList<Materiais> getLista(){
        String []columns ={"id","nomematerial","descmaterial", "quantidade"};
        Cursor cursor = getWritableDatabase().query("materiais", columns,null,null,null,null,null,null);
        ArrayList<Materiais> materiais = new ArrayList<Materiais>();

        while (cursor.moveToNext()){

            Materiais material = new Materiais();
            material.setId(cursor.getLong(0));
            material.setNomeMaterial(cursor.getString(1));
            material.setDescMaterial(cursor.getString(2));
            material.setQuantidade(cursor.getInt(3));

            materiais.add(material);


        }

        return materiais;









}}
