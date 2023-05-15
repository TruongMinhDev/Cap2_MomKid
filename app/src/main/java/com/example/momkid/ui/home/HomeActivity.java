package com.example.momkid.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.legacy.app.ActionBarDrawerToggle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.momkid.R;
import com.example.momkid.chatgpt.ChatGPTActivity;
import com.example.momkid.ui.baby.BabyFragment;
import com.example.momkid.ui.blog.BlogFragment;
import com.example.momkid.ui.book_doctor.BookDoctorFragment;
import com.example.momkid.ui.bmi.BmiFragment;
import com.example.momkid.ui.schedule.ScheduleFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_SCHEDULE = 1;
    private static final int FRAGMENT_INCREASE = 2;
    private static final int FRAGMENT_BLOG = 3;
    private static final int FRAGMENT_BOOK_DOCTOR = 4;

    private static final int FRAGMENT_LIST_KID = 5;

    private int currentFragment = FRAGMENT_HOME;

    DrawerLayout drawer;
    private FloatingActionButton fab_chatGPT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        checkBabyList();

        fab_chatGPT=findViewById(R.id.fab_ChatGPT);
        fab_chatGPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,ChatGPTActivity.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout) ;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            if(currentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                currentFragment = FRAGMENT_HOME;
            }
        }else if(id == R.id.nav_schedule){
            if(currentFragment != FRAGMENT_SCHEDULE){
                replaceFragment(new ScheduleFragment());
                currentFragment = FRAGMENT_SCHEDULE;
            }
        }else if(id == R.id.nav_increase){
            if(currentFragment != FRAGMENT_INCREASE){
                replaceFragment(new BmiFragment());
                currentFragment = FRAGMENT_INCREASE;
            }
        }else if(id == R.id.nav_blog){
            if(currentFragment != FRAGMENT_BLOG){
                replaceFragment(new BlogFragment());
                currentFragment = FRAGMENT_BLOG;
            }
        }else if(id == R.id.nav_book_doctor){
            if(currentFragment != FRAGMENT_BOOK_DOCTOR){
                replaceFragment(new BookDoctorFragment());
                currentFragment = FRAGMENT_BOOK_DOCTOR;
            }
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    //sử lý click toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this,"Trang cài đặt",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_notification:
                Toast.makeText(this,"Trang thông báo",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //check baby list
    public void checkBabyList(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Vui lòng thêm thông tin về bé nhé :3");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // code khi người dùng nhấn nút OK
                replaceFragment(new BabyFragment());
            }
        });
        AlertDialog alert = builder.create();
        alert.show();


    }

}