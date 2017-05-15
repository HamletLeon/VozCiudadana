package com.juventudrd.hsantana.vozciudadana.infraestructure.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by hsantana on 12/13/2016.
 */

public class LocalData_Manager {
    SharedPreferences pref;
    private static Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static String PREF_NAME = "LocalDataManagement";
    private static String mResponse = "AllData";
    private static String mNoData = "{ \"NO DATA\" : [ { \"Descripcion\" : \"No hay data disponible\" } ] }";

    public LocalData_Manager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void SetLocalData(JSONObject response){
        editor.putString(mResponse, response.toString());
        editor.commit();
    }

    public JSONObject GetLocalData(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(pref.getString(mResponse, mNoData));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void clearAllData(){
        editor.clear();
        editor.commit();
    }

    public static void set(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public String getString(String key){
        return pref.getString(key, "");
    }
}
