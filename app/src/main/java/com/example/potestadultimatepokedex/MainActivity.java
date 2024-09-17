package com.example.potestadultimatepokedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.potestadultimatepokedex.api.API;
import com.example.potestadultimatepokedex.model.dto.request.LoginDto;
import com.example.potestadultimatepokedex.model.dto.response.ErrorDto;
import com.example.potestadultimatepokedex.model.dto.response.RefreshTokenDto;
import com.example.potestadultimatepokedex.model.dto.response.UserDto;
import com.example.potestadultimatepokedex.prefs.AppPreferences;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    TextView textUsername, textPassword;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppPreferences.initialize(this);

        // Check if already logged in
//        if (AppPreferences.getInstance().getAccessToken() != null) {
//            // User is already logged in, go to HomeActivity directly
//            proceedToHome();
//        } else {
//            // Initialize the login process
//            initializeLogin();
//        }

        textUsername = findViewById(R.id.username);
        textPassword = findViewById(R.id.password);
        btnSubmit = findViewById(R.id.submit);

        // Handle login button click
        btnSubmit.setOnClickListener(view -> {
            String username = textUsername.getText().toString();
            String password = textPassword.getText().toString();

            // Check if both username and password are entered
            if (!username.isEmpty() && !password.isEmpty()) {
                // Proceed with the API login
                login(username, password);
            } else {
                Toast.makeText(MainActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void initializeLogin() {
//        // Find the username, password, and button fields
//
//    }

    private void login(String username, String password) {
        API.userApi()
                .login(new LoginDto(username, password))
                .enqueue(new Callback<RefreshTokenDto>() {
                    @Override
                    public void onResponse(@NonNull Call<RefreshTokenDto> call, @NonNull Response<RefreshTokenDto> response) {
                        if (response.isSuccessful()) {
                            RefreshTokenDto refreshTokenDto = response.body();
                            if (refreshTokenDto != null) {
                                // Store the access token
                                AppPreferences.getInstance().setAccessToken(refreshTokenDto.getAccessToken());
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                // Proceed to HomeActivity
                                proceedToHome();
                            }
                        } else {
                            handleLoginError(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<RefreshTokenDto> call, Throwable t) {
                        Log.e("JIANDDEBUG", "Failed to login", t);
                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void handleLoginError(@NonNull Response<RefreshTokenDto> response) {
        ResponseBody errorBody = null;
        try {
            errorBody = response.errorBody();
            if (errorBody != null) {
                String json = errorBody.string();
                ErrorDto errorDto = API.gson.fromJson(json, ErrorDto.class);
                Toast.makeText(MainActivity.this, errorDto.getDetail(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (errorBody != null) {
                errorBody.close();
            }
        }
    }

    private void proceedToHome() {
        // Navigate to HomeActivity
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish(); // Finish MainActivity so it's removed from the back stack
    }
}
