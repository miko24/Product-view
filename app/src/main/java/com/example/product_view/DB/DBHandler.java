package com.example.product_view.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.product_view.Models.Product;

import java.sql.SQLInput;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "devoirdb";

    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "products";

    private static final String ID_COL = "id";

    private static final String LABEL_COL = "label";

    private static final String STOCK_COL = "stock";

    private static final String PRICE_COL = "price";

    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        String query = "CREATE TABLE " + TABLE_NAME + "("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LABEL_COL + " TEXT,"
                + STOCK_COL + " INTERGER,"
                + PRICE_COL + " FLOAT)";

        db.execSQL(query);
    }

    public void addNewProduct(String Product_label,Integer Product_stock,Float Product_price){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(LABEL_COL, Product_label);
        values.put(STOCK_COL, Product_stock);
        values.put(PRICE_COL, Product_price);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }
    public ArrayList<Product> readProducts(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor ProductCursor = db.rawQuery("Select * from " + TABLE_NAME, null);

        ArrayList<Product> productList = new ArrayList<>();
        if (ProductCursor.moveToFirst()){
         do {
             productList.add(new Product(ProductCursor.getString(1),ProductCursor.getInt(2),ProductCursor.getFloat(3)));
         } while (ProductCursor.moveToNext());
        }
        ProductCursor.close();
        return productList;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
