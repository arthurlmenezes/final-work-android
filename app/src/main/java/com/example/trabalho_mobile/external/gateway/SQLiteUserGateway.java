package com.example.trabalho_mobile.external.gateway;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.example.trabalho_mobile.entity.User;
import com.example.trabalho_mobile.external.adapter.SQLiteAdapter;
import com.example.trabalho_mobile.external.database.model.UserData;
import com.example.trabalho_mobile.use_case.gateway.UserGateway;

import java.util.List;

public class SQLiteUserGateway implements UserGateway {

    SQLiteAdapter adapterDB;

    public SQLiteUserGateway(Context context) {
        adapterDB = new SQLiteAdapter(context);
    }

    @Override
    public UserData add(User user) {
        adapterDB.open();

        String statement = createSqlInsert();
        Cursor cursor = adapterDB.query(statement, new String[]{
                user.getId().toString(),
                user.getName(),
                user.getEmail(),
                user.getPhoneNumber()
            }
        );
        cursor.moveToFirst();

        UserData userData = null;
        while(!cursor.isAfterLast()) {
            userData = transformCursorToCategoryData(cursor);
        }

        adapterDB.close();
        return userData;
    }

    @Override
    public List<UserData> findAll() {
        return null;
    }

    @Override
    public UserData findById(String id) {
        return null;
    }

    private String createSqlInsert() {
        return "INSERT INTO "
            + SQLiteAdapter.Table.USERS + "("
            + SQLiteAdapter.TABLE_USERS_COLUMN_ID
            + SQLiteAdapter.TABLE_USERS_COLUMN_NAME
            + SQLiteAdapter.TABLE_USERS_COLUMN_EMAIL
            + SQLiteAdapter.TABLE_USERS_COLUMN_PHONE_NUMBER
            + ") VALUES (?, ?, ?, ?)";
    }

    @SuppressLint("Range")
    private UserData transformCursorToCategoryData(Cursor cursor) {
        UserData userData = new UserData();

        userData.id = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.TABLE_USERS_COLUMN_ID));
        userData.name = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.TABLE_USERS_COLUMN_NAME));
        userData.email = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.TABLE_USERS_COLUMN_EMAIL));
        userData.phoneNumber = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.TABLE_USERS_COLUMN_PHONE_NUMBER));

        return userData;
    }
}
