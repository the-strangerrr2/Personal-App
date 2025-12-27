package com.example.personalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etName, etAge, etPhone, etEmail;
    Button btnSave;
    TextView tvResult;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        btnSave = findViewById(R.id.btnSave);
        tvResult = findViewById(R.id.tvResult);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Load saved info on start
        loadData();

        btnSave.setOnClickListener(v -> saveData());
    }

    private void saveData() {

        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String phone = etPhone.getText().toString();
        String email = etEmail.getText().toString();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("age", age);
        editor.putString("phone", phone);
        editor.putString("email", email);
        editor.apply();

        tvResult.setText("✅ اطلاعات ذخیره شد:\n\n" +
                "نام: " + name + "\n" +
                "سن: " + age + "\n" +
                "شماره: " + phone + "\n" +
                "ایمیل: " + email);
    }

    private void loadData() {

        String name = sharedPreferences.getString("name", "");
        String age = sharedPreferences.getString("age", "");
        String phone = sharedPreferences.getString("phone", "");
        String email = sharedPreferences.getString("email", "");

        if (!name.isEmpty()) {
            etName.setText(name);
            etAge.setText(age);
            etPhone.setText(phone);
            etEmail.setText(email);

            tvResult.setText("📌 اطلاعات ذخیره شده:\n\n" +
                    "نام: " + name + "\n" +
                    "سن: " + age + "\n" +
                    "شماره: " + phone + "\n" +
                    "ایمیل: " + email);
        }
    }
}
