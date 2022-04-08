package rs.raf.projekat1.view.fragments;

import static rs.raf.projekat1.view.activities.MainActivity.EMAIL_KEY;
import static rs.raf.projekat1.view.activities.MainActivity.IS_LOGGED_IN;
import static rs.raf.projekat1.view.activities.MainActivity.USERNAME_KEY;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import rs.raf.projekat1.R;
import rs.raf.projekat1.view.activities.LoginActivity;

public class ProfileFragment extends Fragment {

    private String username;
    private String email;
    private TextView usernameEditText;
    private TextView emailEditText;
    private Button button;

    public ProfileFragment() {
        super(R.layout.fragment_profle);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        //sharedPreferences.edit().clear().apply();
        username = sharedPreferences.getString(USERNAME_KEY, "");
        email = sharedPreferences.getString(EMAIL_KEY, "");
        initView(view);
        initListeners();
    }

    private void initListeners() {
        button.setOnClickListener( v-> {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
    }

    private void initView(View view) {
        usernameEditText = view.findViewById(R.id.usernameTextViewProfilePage);
        emailEditText = view.findViewById(R.id.emailTextViewProfilePage);
        button = view.findViewById(R.id.logOutButton);
        usernameEditText.setText(username);
        emailEditText.setText(email);
    }
}
