package com.example.trabalho_mobile.external.gateway;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.example.trabalho_mobile.entity.Ad;
import com.example.trabalho_mobile.entity.User;
import com.example.trabalho_mobile.external.adapter.SQLiteAdapter;
import com.example.trabalho_mobile.external.database.model.AdData;
import com.example.trabalho_mobile.use_case.gateway.AdGateway;

import java.util.ArrayList;
import java.util.List;

public class SQLiteAdGateway implements AdGateway {

    SQLiteAdapter adapterDB;

    public SQLiteAdGateway(Context context) {
        adapterDB = new SQLiteAdapter(context);
    }

    @Override
    public AdData add(Ad ad, User user) {
        adapterDB.open();

        String statement = createSqlInsert();
        Cursor cursor = adapterDB.query(statement, new String[]{
                        ad.getId().toString(),
                        ""+ad.getPrice(),
                        ad.getImmobile().getId().toString(),
                        user.getId().toString()
                }
        );
        cursor.moveToFirst();

        AdData adData = null;
        while(!cursor.isAfterLast()) {
            adData = transformCursorToAdData(cursor);
        }

        adapterDB.close();
        return adData;
    }

    @Override
    public List<AdData> findAll(User user) {
        adapterDB.open();

        String statement = createSqlFindAll();
        Cursor cursor = adapterDB.query(statement, new String[]{user.getId().toString()});
        cursor.moveToFirst();

        ArrayList<AdData> adDataArrayList = new ArrayList<>();
        while(!cursor.isAfterLast()) {
            adDataArrayList.add(transformCursorToAdData(cursor));
        }

        adapterDB.close();
        return adDataArrayList;
    }

    @Override
    public AdData findById(String id, User user) {
        adapterDB.open();

        String statement = createSqlFindById();
        Cursor cursor = adapterDB.query(statement, new String[]{user.getId().toString(), id});
        cursor.moveToFirst();

        AdData adData = null;
        while(!cursor.isAfterLast()) {
            adData = transformCursorToAdData(cursor);
        }

        adapterDB.close();
        return adData;
    }

    private String createSqlInsert() {
        return "INSERT INTO "
                + SQLiteAdapter.Table.ADS + "("
                + SQLiteAdapter.TABLE_ADS_COLUMN_ID
                + SQLiteAdapter.TABLE_ADS_COLUMN_PRICE
                + SQLiteAdapter.TABLE_ADS_COLUMN_PROPERTIES_ID
                + SQLiteAdapter.TABLE_ADS_COLUMN_USER_ID
                + ") VALUES (?, ?, ?, ?)";
    }

    private String createSqlFindAll() {
        return "SELECT * FROM "
                + SQLiteAdapter.Table.ADS
                + "WHERE " + SQLiteAdapter.TABLE_ADS_COLUMN_USER_ID
                + " = ?";
    }

    private String createSqlFindById() {
        return "SELECT * FROM "
                + SQLiteAdapter.Table.CATEGORIES
                + "WHERE " + SQLiteAdapter.TABLE_CATEGORIES_COLUMN_USER_ID
                + " = ?"
                + "AND " + SQLiteAdapter.TABLE_CATEGORIES_COLUMN_ID
                + " = ?";
    }

    @SuppressLint("Range")
    private AdData transformCursorToAdData(Cursor cursor) {
        AdData adData = new AdData();

        adData.id = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.TABLE_ADS_COLUMN_ID));
        adData.price = cursor.getDouble(cursor.getColumnIndex(SQLiteAdapter.TABLE_ADS_COLUMN_PRICE));
        adData.propertyId = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.TABLE_ADS_COLUMN_PROPERTIES_ID));
        adData.userId = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.TABLE_ADS_COLUMN_USER_ID));

        return adData;
    }
}
