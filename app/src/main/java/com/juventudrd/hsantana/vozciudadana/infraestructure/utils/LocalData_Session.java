package com.juventudrd.hsantana.vozciudadana.infraestructure.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.juventudrd.hsantana.vozciudadana.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hsantana on 12/13/2016.
 */

public class LocalData_Session {
    private SharedPreferences pref;
    private Editor editor;
    private Context _context;
    private int PRIVATE_MODE = 0;

    private static String PREF_NAME = "LocalDataPreferences";
    private static String IS_LOGIN = "IsLoggedIn";
    private static String IS_PARTIAL = "IsPartialIn";
    public static String KEY_NAME = "UserName";
    public static String KEY_EMAIL = "Email";
    public static String KEY_TOKEN = "Token";
    public static String KEY_USERID = "UserId";
    public static String KEY_ROLE = "Role";
    public static String KEY_PERMISSIONS = "Permissions";

    public LocalData_Session(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String name, String email){
        if (!email.equals(pref.getString(KEY_EMAIL, null))){
            LocalData_Manager data = new LocalData_Manager(_context);
            data.clearAllData();
        }
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
        //Agregar el clear de datos si se ingresa otro usuario diferente al actual
    }

    public void saveLoginData(String Authtoken, int userid, String role, List<String> permissionList){
        editor.putString(KEY_TOKEN, Authtoken);
        editor.putInt(KEY_USERID, userid);
        editor.putString(KEY_ROLE, role);

        editor.putString(KEY_PERMISSIONS, TextUtils.join(",", permissionList));

        editor.commit();
    }

    public void createPartialLoginSession(String name, String email){
        createLoginSession(name, email);
        editor.putBoolean(IS_PARTIAL, true);
        editor.commit();
        //Agregar el clear de datos si se ingresa otro usuario diferente al actual
    }

    public boolean checkLogin(){
        if (!this.isLoggedIn()){
            logoutUser(true);
            return false;
        }
        return true;
    }

    public void checkPartialLogin(){
        if (this.isPartialLoggedIn()){
            logoutUser(false);
        }
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        user.put(KEY_NAME, pref.getString(KEY_NAME,null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL,null));
        return user;
    }

    private int getUserId(){
        return pref.getInt(KEY_USERID, 0);
    }

    private String getUserName(){
        return pref.getString(KEY_NAME, "");
    }

    private String getRole(){
        return pref.getString(KEY_ROLE, "");
    }

    private String getAuthorization(){
        return pref.getString(KEY_TOKEN, "");
    }

    private List<String> getPermissions() {
        String permissions = pref.getString(KEY_PERMISSIONS, null);
        if (permissions!=null) return new ArrayList<>(Arrays.asList(TextUtils.split(permissions, ",")));
        return new ArrayList<>(Collections.singletonList(""));
    }

    public static int getUserId(Context context){
        LocalData_Session session = new LocalData_Session(context);
        return session.getUserId();
    }

    public static String getUserName(Context context){
        LocalData_Session session = new LocalData_Session(context);
        return session.getUserName();
    }

    public static String getRole(Context context){
        LocalData_Session session = new LocalData_Session(context);
        return session.getRole();
    }

    public static String getAuthorization(Context context){
        LocalData_Session session = new LocalData_Session(context);
        return session.getAuthorization();
    }

    public static List<String> getPermissionList(Context context){
        LocalData_Session session = new LocalData_Session(context);
        return session.getPermissions();
    }

    public static String getStringDataOfSession(Context _context, String TAG){
        LocalData_Session session = new LocalData_Session(_context);
        return session.pref.getString(TAG, "");
    }

    public static boolean getBooleanDataOfSession(Context _context, String TAG){
        LocalData_Session session = new LocalData_Session(_context);
        return session.pref.getBoolean(TAG, false);
    }

    public static boolean saveBooleanArray(Context _context, boolean[] array, String TAG){
        return putBooleanArray(new LocalData_Session(_context), array, TAG);
    }

    private static boolean putBooleanArray(LocalData_Session session, boolean[] array, String TAG){
        if (session!=null){
            session.editor.putInt(TAG+"_size", array.length);
            for (int i=0; i<array.length; i++){
                session.editor.putBoolean(TAG+"_"+i, array[i]);
            }
            return session.editor.commit();
        }
        return false;
    }

    public static Boolean[] getBooleanArray(Context _context, String TAG){
        LocalData_Session session = new LocalData_Session(_context);
        int size = session.pref.getInt(TAG+"_size", 0);
        Boolean[] array = new Boolean[size];
        for (int i=0; i<size; i++){
            array[i] = session.pref.getBoolean(TAG+"_"+i, false);
        }
        return array;
    }

    public void logoutUser(Boolean intent){
        editor.clear();
        editor.commit();

        if (intent){
//            Intent i = new Intent(_context, LoginActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            _context.startActivity(i);
            AppController.getInstance().cancelPendingRequests(_context);
        }
    }

    void NVSession(View view, String name, String email){
        if(name == null){
//            Toast.makeText(_context, "Tu sesión ha expirado!", Toast.LENGTH_SHORT).show();
            logoutUser(true);
        }
        TextView NVname = (TextView) view.findViewById(R.id.NVname);
        TextView NVemail = (TextView) view.findViewById(R.id.NVemail);
        NVname.setText(name);
        NVemail.setText(email);
    }

    private boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
    private boolean isPartialLoggedIn(){
        return pref.getBoolean(IS_PARTIAL, false);
    }
}
