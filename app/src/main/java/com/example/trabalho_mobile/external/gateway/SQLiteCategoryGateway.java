package com.example.trabalho_mobile.external.gateway;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import com.example.trabalho_mobile.entity.Category;
import com.example.trabalho_mobile.entity.User;
import com.example.trabalho_mobile.external.adapter.SQLiteAdapter;
import com.example.trabalho_mobile.external.database.model.CategoryData;
import com.example.trabalho_mobile.use_case.gateway.CategoryGateway;

import java.util.ArrayList;
import java.util.List;

public class SQLiteCategoryGateway implements CategoryGateway {

    SQLiteAdapter adapterDB;

    public SQLiteCategoryGateway(Context context) {
        adapterDB = new SQLiteAdapter(context);
    }

    @Override
    public CategoryData add(Category category, User user) {
        adapterDB.open();

        String statement = createSqlInsert();
        Cursor cursor = adapterDB.query(statement, new String[]{
                        category.getId().toString(),
                        category.getName(),
                        user.getId().toString()
                }
        );
        cursor.moveToFirst();

        CategoryData categoryData = null;
        while(!cursor.isAfterLast()) {
            categoryData = transformCursorToCategoryData(cursor);
        }

        adapterDB.close();
        return categoryData;
    }

    @Override
    public List<CategoryData> findAll(User user) {
        adapterDB.open();

        String statement = createSqlFindAll();
        Cursor cursor = adapterDB.query(statement, new String[]{user.getId().toString()});
        cursor.moveToFirst();

        ArrayList<CategoryData> categoryDataArrayList = new ArrayList<>();
        while(!cursor.isAfterLast()) {
            categoryDataArrayList.add(transformCursorToCategoryData(cursor));
        }

        adapterDB.close();
        return categoryDataArrayList;
    }

    @Override
    public CategoryData findById(String id, User user) {
        adapterDB.open();

        String statement = createSqlFindById();
        Cursor cursor = adapterDB.query(statement, new String[]{user.getId().toString(), id});
        cursor.moveToFirst();

        CategoryData categoryData = null;
        while(!cursor.isAfterLast()) {
            categoryData = transformCursorToCategoryData(cursor);
        }

        adapterDB.close();
        return categoryData;
    }

    private String createSqlInsert() {
        return "INSERT INTO "
                + SQLiteAdapter.Table.CATEGORIES + "("
                + SQLiteAdapter.TABLE_CATEGORIES_COLUMN_ID
                + SQLiteAdapter.TABLE_CATEGORIES_COLUMN_NAME
                + SQLiteAdapter.TABLE_CATEGORIES_COLUMN_USER_ID
                + ") VALUES (?, ?, ?)";
    }

    private String createSqlFindAll() {
        return "SELECT * FROM "
                + SQLiteAdapter.Table.CATEGORIES
                + "WHERE " + SQLiteAdapter.TABLE_CATEGORIES_COLUMN_USER_ID
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
    private CategoryData transformCursorToCategoryData(Cursor cursor) {
        CategoryData categoryData = new CategoryData();

        categoryData.id = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.TABLE_CATEGORIES_COLUMN_ID));
        categoryData.name = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.TABLE_CATEGORIES_COLUMN_NAME));
        categoryData.userId = cursor.getString(cursor.getColumnIndex(SQLiteAdapter.TABLE_CATEGORIES_COLUMN_USER_ID));

        return categoryData;
    }
}
