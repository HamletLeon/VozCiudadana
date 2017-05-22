package com.juventudrd.hsantana.vozciudadana.infraestructure;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.Toast;

import com.juventudrd.hsantana.vozciudadana.R;
import com.juventudrd.hsantana.vozciudadana.infraestructure.utils.AppController;
import com.juventudrd.hsantana.vozciudadana.infraestructure.utils.LocalData_Session;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;
import net.hockeyapp.android.metrics.MetricsManager;

import java.util.HashMap;

/**
 * Created by hsantana on 2/13/2017.
 * Menu de Navegacion de la Aplicacion
 */

public class NavigationViewController extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected ViewStub mStubContainer;
    protected DrawerLayout mDrawerLayout;
    protected NavigationView mNavigationView;

    // Manejo de mSession
    protected String mName, mEmail;

    // Dialogos
    protected ProgressDialog mProgressDialog;

    protected Intent mIntent;
    private LocalData_Session mSession;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSession = new LocalData_Session(getApplicationContext());

        checkForUpdates();
//        if (checkSession()) {
        if (true) {
            // Configuracion Base de pantalla principal
            mStubContainer = (ViewStub) findViewById(R.id.layout_stub);

            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

            mNavigationView = (NavigationView) findViewById(R.id.nav_view);

            setDialogs();
        }else {
            finish();
        }
    }

    @Override
    protected void onStop() {
        AppController.getInstance().cancelPendingRequests(this);
        super.onStop();
    }

    private void setDialogs(){
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Cargando...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
        }
    }

    protected boolean checkSession() {
        // Chequeo de sesion
        if (mSession.checkLogin()){
            HashMap<String, String> user_data = mSession.getUserDetails();
            mName = user_data.get(LocalData_Session.KEY_NAME);
            mEmail = user_data.get(LocalData_Session.KEY_EMAIL);
            if (mName==null){
                mSession.logoutUser(true);
                return false;
            }
            return true;
        }else {
            return false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterManagers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForCrashes();
    }

    @Override
    protected void onDestroy() {
        if (mSession!=null)mSession.checkPartialLogin();
        if (mProgressDialog!=null && mProgressDialog.isShowing())mProgressDialog.dismiss();
        super.onDestroy();
        unregisterManagers();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent;

        if (!item.isChecked()){
            switch (id){
                default:
                    Toast.makeText(this, getString(R.string.inConstruction), Toast.LENGTH_LONG).show();
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    return false;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void checkForCrashes() {
        CrashManager.register(this);
    }

    private void checkForUpdates() {
        // Remove this for store builds!
        MetricsManager.register(getApplication());
        UpdateManager.register(this);
    }

    private void unregisterManagers() {
        UpdateManager.unregister();
    }
}
