package com.example.hph1995.multipurposebooking;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class HomePage extends AppCompatActivity {

    private DrawerLayout myDrawer;
    private ActionBarDrawerToggle myToggle;
    private NavigationView nvDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        myDrawer = (DrawerLayout) findViewById(R.id.myDrawer);
        myToggle = new ActionBarDrawerToggle(this, myDrawer, R.string.open, R.string.close);

        myDrawer.addDrawerListener(myToggle);
        myToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nvDrawer = (NavigationView)findViewById(R.id.nv);
        setupDrawerContent(nvDrawer);
    }

    public void selectItemDrawer(MenuItem item){
        Fragment myFragment = null;
        Class fragmentClass = null;

        switch (item.getItemId()){
            case R.id.home:
                break;
            case R.id.event:
                fragmentClass = Event.class;
                break;
            case R.id.account:
                fragmentClass = AccountManagement.class;
                break;
            case R.id.booking:
                fragmentClass = OnlineBooking.class;
                break;
            case R.id.report:
                fragmentClass = Report.class;
                break;
            case R.id.timetable:
                break;
            default:
                fragmentClass = Event.class;
        }

        try{
            myFragment = (Fragment) fragmentClass.newInstance();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flcontent, myFragment).commit();
        item.setChecked(true);
        setTitle(item.getTitle());
        myDrawer.closeDrawers();
    }

    private void setupDrawerContent(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(myToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
