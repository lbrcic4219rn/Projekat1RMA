package rs.raf.projekat1.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.projekat1.R;
import rs.raf.projekat1.view.fragments.MainFragment;
import rs.raf.projekat1.view.viewpager.PageAdapterMenu;
import rs.raf.projekat1.viewmodels.MainViewModel;

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
    public static final String TICKET_EDIT_TAG = "ticketEdit";

    private MainViewModel viewModel;


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
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initFragment();
    }

    private void initFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.mainFragmentContainer, new MainFragment());
        transaction.commit();
    }


}