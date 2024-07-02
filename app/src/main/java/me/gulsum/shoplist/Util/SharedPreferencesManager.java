package me.gulsum.shoplist.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    //SP için yani shared preferences için hem okuma hem de yazma fonksiyonları yazmamız gerekiyor
    //Bu fonksiyonları generic kullanarak yazıcaz

    public static <T> void writeData(String variableName, T data, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(variableName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(data instanceof Boolean) {
            editor.putBoolean(variableName, (Boolean) data);
        } else if(data instanceof Integer) {
            editor.putInt(variableName, (Integer) data);
        } else if(data instanceof Float) {
            editor.putFloat(variableName, (Float) data);
        } else if(data instanceof Long) {
            editor.putLong(variableName, (Long) data);
        } else if(data instanceof String) {
            editor.putString(variableName, (String) data);
        } else {
            throw new IllegalArgumentException("Desteklenmeyen veri tipi...");
        }

        editor.apply();
    }

}

