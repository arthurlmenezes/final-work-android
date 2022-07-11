package com.example.trabalho_mobile.external.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.trabalho_mobile.external.ports.Connection;

public class SQLiteAdapter implements Connection {
    private final int DATABASE_VERSION = 1;
    private final String DATABASE_NAME = "property.db";
    public enum Table {
        CATEGORIES,
        PROPERTIES,
        ADDRESS,
        ADS,
        FAVORITES,
        USERS
    }

    public static final String TABLE_CATEGORIES_COLUMN_ID = "id",
            TABLE_CATEGORIES_COLUMN_NAME = "name",
            TABLE_CATEGORIES_COLUMN_USER_ID = "user_id";

    public static final String TABLE_ADDRESS_COLUMN_ID = "id",
            TABLE_ADDRESS_COLUMN_LAT = "lat",
            TABLE_ADDRESS_COLUMN_LON = "lon",
            TABLE_ADDRESS_COLUMN_VALUE = "value",
            TABLE_ADDRESS_COLUMN_USER_ID = "user_id";

    public static final String
            TABLE_PROPERTIES_COLUMN_ID = "id",
            TABLE_PROPERTIES_COLUMN_TITLE = "title",
            TABLE_PROPERTIES_COLUMN_DESCRIPTION = "description",
            TABLE_PROPERTIES_COLUMN_PHOTO = "photo",
            TABLE_PROPERTIES_COLUMN_ADDRESS_ID = "address_id",
            TABLE_PROPERTIES_COLUMN_CATEGORY_ID = "category_id",
            TABLE_PROPERTIES_COLUMN_USER_ID = "user_id";

    public static final String TABLE_ADS_COLUMN_ID = "id",
        TABLE_ADS_COLUMN_PRICE = "price",
        TABLE_ADS_COLUMN_PROPERTIES_ID = "property_id",
        TABLE_ADS_COLUMN_USER_ID = "user_id";

    public static final String TABLE_FAVORITES_COLUMN_ID = "id",
        TABLE_FAVORITES_COLUMN_AD_ID = "ad_id",
        TABLE_FAVORITES_COLUMN_USER_ID = "user_id";

    public static final String TABLE_USERS_COLUMN_ID = "id",
            TABLE_USERS_COLUMN_NAME = "name",
            TABLE_USERS_COLUMN_EMAIL = "email",
            TABLE_USERS_COLUMN_PHONE_NUMBER = "phone_number";

    private final String SQL_CREATE_TABLE_CATEGORIES = "CREATE TABLE " + Table.CATEGORIES.name() + " ("
            + TABLE_CATEGORIES_COLUMN_ID + " TEXT PRIMARY KEY, "
            + TABLE_CATEGORIES_COLUMN_NAME + " TEXT"
            + TABLE_CATEGORIES_COLUMN_USER_ID + " TEXT"
            + ")";

    private final String SQL_CREATE_TABLE_ADDRESS = "CREATE TABLE " + Table.ADDRESS.name() + " ("
            + TABLE_ADDRESS_COLUMN_ID + " TEXT PRIMARY KEY, "
            + TABLE_ADDRESS_COLUMN_LAT + " TEXT, "
            + TABLE_ADDRESS_COLUMN_LON + " TEXT, "
            + TABLE_ADDRESS_COLUMN_VALUE + " TEXT, "
            + TABLE_ADDRESS_COLUMN_USER_ID + " TEXT"
            + ")";

    private final String SQL_CREATE_TABLE_USERS = "CREATE TABLE " + Table.USERS.name() + " ("
            + TABLE_USERS_COLUMN_ID + " TEXT PRIMARY KEY, "
            + TABLE_USERS_COLUMN_NAME + " TEXT, "
            + TABLE_USERS_COLUMN_EMAIL + " TEXT, "
            + TABLE_USERS_COLUMN_PHONE_NUMBER + " TEXT"
            + ")";

    private final String SQL_CREATE_TABLE_ADS = "CREATE TABLE " + Table.ADS.name() + " ("
            + TABLE_ADS_COLUMN_ID + " TEXT PRIMARY KEY, "
            + TABLE_ADS_COLUMN_PRICE + " TEXT, "
            + TABLE_ADS_COLUMN_PROPERTIES_ID + " TEXT, "
            + TABLE_ADS_COLUMN_USER_ID + " TEXT, "
            + "FOREIGN KEY(" + TABLE_ADS_COLUMN_PROPERTIES_ID + ") REFERENCES " + Table.PROPERTIES.name() + "(id), "
            + "FOREIGN KEY(" + TABLE_ADS_COLUMN_USER_ID + ") REFERENCES " + Table.USERS.name() + "(id)"
            + ")";

    private final String SQL_CREATE_TABLE_FAVORITES = "CREATE TABLE " + Table.FAVORITES.name() + " ("
            + TABLE_FAVORITES_COLUMN_ID + " TEXT PRIMARY KEY, "
            + TABLE_FAVORITES_COLUMN_AD_ID + " TEXT, "
            + TABLE_FAVORITES_COLUMN_USER_ID + " TEXT, "
            + "FOREIGN KEY(" + TABLE_FAVORITES_COLUMN_AD_ID + ") REFERENCES " + Table.ADS.name() + "(id), "
            + "FOREIGN KEY(" + TABLE_FAVORITES_COLUMN_USER_ID + ") REFERENCES " + Table.USERS.name() + "(id)"
            + ")";

    private final String SQL_CREATE_TABLE_PROPERTIES = "CREATE TABLE " + Table.PROPERTIES.name() + " ("
            + TABLE_PROPERTIES_COLUMN_ID + " TEXT PRIMARY KEY, "
            + TABLE_PROPERTIES_COLUMN_TITLE + " TEXT, "
            + TABLE_PROPERTIES_COLUMN_DESCRIPTION + " TEXT, "
            + TABLE_PROPERTIES_COLUMN_PHOTO + " TEXT, "
            + TABLE_PROPERTIES_COLUMN_ADDRESS_ID + " TEXT, "
            + TABLE_PROPERTIES_COLUMN_CATEGORY_ID + " TEXT, "
            + TABLE_PROPERTIES_COLUMN_USER_ID + " TEXT, "
            + "FOREIGN KEY(" + TABLE_PROPERTIES_COLUMN_ADDRESS_ID + ") REFERENCES " + Table.ADDRESS.name() + "(id), "
            + "FOREIGN KEY(" + TABLE_PROPERTIES_COLUMN_CATEGORY_ID + ") REFERENCES " + Table.CATEGORIES.name() + "(id), "
            + "FOREIGN KEY(" + TABLE_PROPERTIES_COLUMN_USER_ID + ") REFERENCES " + Table.USERS.name() + "(id)"
            + ")";



    private SQLiteOpenHelper helper;
    private SQLiteDatabase database;

    public SQLiteAdapter(Context context) {
        helper = new SQLiteHelper(context, this.DATABASE_NAME, null, this.DATABASE_VERSION);
    }

    public SQLiteOpenHelper getInstanceHelper() {
        return this.helper;
    }

    public String getDatabaseName() {
        return this.DATABASE_NAME;
    }

    public void open() {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
    }

    public Cursor query(String statement, String[] params) {
        return database.rawQuery(statement, params);
    }

    private class SQLiteHelper extends SQLiteOpenHelper {

        public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(SQL_CREATE_TABLE_CATEGORIES);
            database.execSQL(SQL_CREATE_TABLE_ADDRESS);
            database.execSQL(SQL_CREATE_TABLE_USERS);
            database.execSQL(SQL_CREATE_TABLE_ADS);
            database.execSQL(SQL_CREATE_TABLE_FAVORITES);
            database.execSQL(SQL_CREATE_TABLE_PROPERTIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            database.execSQL("DROP TABLE IF EXISTS " + Table.CATEGORIES.name());
            database.execSQL("DROP TABLE IF EXISTS " + Table.ADDRESS.name());
            database.execSQL("DROP TABLE IF EXISTS " + Table.USERS.name());
            database.execSQL("DROP TABLE IF EXISTS " + Table.ADS.name());
            database.execSQL("DROP TABLE IF EXISTS " + Table.FAVORITES.name());
            database.execSQL("DROP TABLE IF EXISTS " + Table.PROPERTIES.name());

            onCreate(database);
        }

        @Override
        public void onConfigure(SQLiteDatabase db) {
            db.setForeignKeyConstraintsEnabled(true);
        }

        @Override
        public void onDowngrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            onUpgrade(database, oldVersion, newVersion);
        }
    }
}