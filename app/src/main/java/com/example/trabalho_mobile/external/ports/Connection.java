package com.example.trabalho_mobile.external.ports;

import android.database.Cursor;

public interface Connection {
    Cursor query(String statement, String[] params);
    void close();
}
