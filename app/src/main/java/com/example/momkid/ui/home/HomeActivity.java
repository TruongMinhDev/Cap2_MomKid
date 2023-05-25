package com.example.momkid.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.legacy.app.ActionBarDrawerToggle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.momkid.MainActivity;
import com.example.momkid.R;
import com.example.momkid.chatgpt.ChatGPTActivity;
import com.example.momkid.helper.SharedPreferenceHelper;
import com.example.momkid.ui.baby.BabyDto;
import com.example.momkid.ui.baby.BabyFragment;
import com.example.momkid.ui.baby.INavigate;
import com.example.momkid.ui.blog.BlogFragment;
import com.example.momkid.ui.book_doctor.BookDoctorFragment;
import com.example.momkid.ui.bmi.BmiFragment;
import com.example.momkid.ui.book_doctor.DoctorDto;
import com.example.momkid.ui.book_doctor.DoctorFragment;
import com.example.momkid.ui.profile.ProfileBabyFragment;
import com.example.momkid.ui.profile.ProfileUserFragment;
import com.example.momkid.ui.schedule.ScheduleDetailFragment;
import com.example.momkid.ui.schedule.ScheduleDto;
import com.example.momkid.ui.schedule.ScheduleFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, INavigate {

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_SCHEDULE = 1;
    private static final int FRAGMENT_INCREASE = 2;
    private static final int FRAGMENT_BLOG = 3;
    private static final int FRAGMENT_BOOK_DOCTOR = 4;

    private static final int FRAGMENT_LIST_KID = 5;

    private static final int FRAGMENT_PROFILE_USER = 6;

    private static final int FRAGMENT_BABY = 7;

    private static final int LOGOUT=8;

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
//        replaceFragment(new BmiFragment());
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
        }else if(id == R.id.nav_profile){
            if(currentFragment != FRAGMENT_PROFILE_USER){
                replaceFragment(new ProfileUserFragment());
                currentFragment = FRAGMENT_PROFILE_USER;
            }
        }else if (id == R.id.nav_baby){
            if(currentFragment != FRAGMENT_BABY){
                replaceFragment(new ProfileBabyFragment());
                currentFragment = FRAGMENT_BABY;
            }
        }else if(id == R.id.nav_logout){
            if(currentFragment != LOGOUT){
                logout();
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
    public void checkBabyList(){
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setMessage("Vui lòng thêm thông tin về bé nhé :3");
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // code khi người dùng nhấn nút OK
                BabyFragment babyFragment = new BabyFragment(HomeActivity.this);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame,babyFragment);
                transaction.commit();
//                replaceFragment(new BabyFragment());
            }
        });
        AlertDialog alert = builder.create();
        alert.show();


    }

    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        // Add the buttons
        builder.setMessage("Bạn có thật sự muốn thoát !!!");
        builder.setIcon(ContextCompat.getDrawable(HomeActivity.this,R.drawable.baseline_warning_24));
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                SharedPreferenceHelper.setSharedPreferenceString(HomeActivity.this,"token","");
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        // Create the AlertDialog
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void navigate() {
        replaceFragment(new BmiFragment());
    }

    public void goToHomeFragment(BabyDto babyDto){
        HomeFragment homeFragment = new HomeFragment();


        Bundle bundle = new Bundle();
        bundle.putSerializable("object_baby",babyDto);

        homeFragment.setArguments(bundle);

        replaceFragment(homeFragment);


    }

    public void goToDetailSchedule(ScheduleDto scheduleDto){
        ScheduleDetailFragment scheduleDetailFragment = new ScheduleDetailFragment();


        Bundle bundle = new Bundle();
        bundle.putSerializable("object_schedule",scheduleDto);

        scheduleDetailFragment.setArguments(bundle);

        replaceFragment(scheduleDetailFragment);


    }

    public void goToDetailDoctor(DoctorDto doctorDto){
        DoctorFragment doctorFragment=new DoctorFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("object_doctor",doctorDto);

        doctorFragment.setArguments(bundle);

        replaceFragment(doctorFragment);


    }
    public void goToBabyFragment(){
        BabyFragment babyFragment =new BabyFragment();
        replaceFragment(babyFragment);


    }

//    public void refresh(Fragment fragment){
//        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
//        if (Build.VERSION.SDK_INT >= 26) {
//            ft.setReorderingAllowed(false);
//        }
//        BmiFragment bmiFragment = new BmiFragment();
//        ft.detach(fragment).attach(bmiFragment).commit();
//    }
}