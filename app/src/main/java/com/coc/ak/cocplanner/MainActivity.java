package com.coc.ak.cocplanner;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.coc.ak.fragments.ClanFragment;
import com.coc.ak.fragments.LogAttacksFragment;
import com.coc.ak.fragments.NotifSettingsFragment;
import com.coc.ak.fragments.OutlawsFragment;
import com.coc.ak.fragments.PlanFragment;
import com.coc.ak.fragments.PushNowFragment;
import com.coc.ak.fragments.StartWarFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ClanFragment clanFragment;
    OutlawsFragment outlawsFragment;
    StartWarFragment startWarFragment;
    LogAttacksFragment logAttacksFragment;
    PushNowFragment pushNowFragment;
    NotifSettingsFragment notifSettingsFragment;
    PlanFragment planFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        initialize();
    }

    private void initialize() {
        this.clanFragment = new ClanFragment();
        this.outlawsFragment = new OutlawsFragment();
        this.startWarFragment = new StartWarFragment();
        this.logAttacksFragment = new LogAttacksFragment();
        this.pushNowFragment = new PushNowFragment();
        this.notifSettingsFragment = new NotifSettingsFragment();
        this.planFragment = new PlanFragment();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_clan) {
            //clanFragment = new ClanFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, this.clanFragment);
            ft.commit();
        } else if (id == R.id.nav_outlaws) {
            //outlawsFragment = new OutlawsFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, this.outlawsFragment);
            ft.commit();

        } else if (id == R.id.nav_startwar) {
            //startWarFragment = new StartWarFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, this.startWarFragment);
            ft.commit();

        } else if (id == R.id.nav_plan) {
            //planFragment = new PlanFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, this.planFragment);
            ft.commit();

        } else if (id == R.id.nav_reportAttack) {
            //logAttacksFragment = new LogAttacksFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, this.logAttacksFragment);
            ft.commit();
        } else if (id == R.id.nav_pushnow) {
            //pushNowFragment = new PushNowFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, this.pushNowFragment);
            ft.commit();
        } else if (id == R.id.nav_pushsettings) {
            //notifSettingsFragment = new NotifSettingsFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, this.notifSettingsFragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
