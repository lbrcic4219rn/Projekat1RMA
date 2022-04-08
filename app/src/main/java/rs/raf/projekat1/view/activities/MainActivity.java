package rs.raf.projekat1.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.projekat1.R;
import rs.raf.projekat1.view.viewpager.PageAdapterMenu;

public class MainActivity extends AppCompatActivity {

    //CONSTS
    public static final String IS_LOGGED_IN  = "userLoggedIn";
    public static final String ADMIN_LOGGED = "A";
    public static final String USER_LOGGED = "U";
    public static final String ADMIN_USERNAME = "admin123";
    public static final String ADMIN_PASSWORD = "password";
    public static final String ADMIN_EMAIL = "admin@admin.com";
    public static final String USER_USERNAME = "user123";
    public static final String USER_PASSWORD = "password";
    public static final String USER_EMAIL = "user@user.com";
    public static final String USERNAME_KEY = "username_key";
    public static final String EMAIL_KEY = "email";
    public static final String TICKET_DETAIL_TAG = "ticketDetails";


    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ON CREATE");
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> {
            System.out.println("SPLASH");

            SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
            //sharedPreferences.edit().clear().apply();
            String message = sharedPreferences.getString(IS_LOGGED_IN, "");
            if(message == null || message == ""){
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
            return  false;
        });

        Intent intent = getIntent();
        if(intent != null) {
            String userType = intent.getStringExtra(IS_LOGGED_IN);
            if(userType != null && !userType.equals("") ){
                SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
                String message = sharedPreferences.getString(IS_LOGGED_IN, "");
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
        setContentView(R.layout.activity_main);
        initViewPager();
        initNavigation();


    }

    private void initNavigation() {
        ((BottomNavigationView) findViewById(R.id.bottomNavigation)).setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.statistics: viewPager.setCurrentItem(PageAdapterMenu.FRAGMENT_STATISTICS, false); break;
                case R.id.createTicket: viewPager.setCurrentItem(PageAdapterMenu.FRAGMENT_NEW_TICKET, false); break;
                case R.id.tickets: viewPager.setCurrentItem(PageAdapterMenu.FRAGMENT_TICKET_LIST, false); break;
                default: viewPager.setCurrentItem(PageAdapterMenu.FRAGMENT_PROFILE, false); break;
            }
            return true;
        });
    }

    private void initViewPager() {
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PageAdapterMenu(getSupportFragmentManager()));
    }
}