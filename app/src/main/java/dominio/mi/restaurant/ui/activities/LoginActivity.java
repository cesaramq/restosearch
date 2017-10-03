package dominio.mi.restaurant.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.Utils;

public class LoginActivity extends AppCompatActivity {
    private EditText password;
    private EditText userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView textLogo = (TextView) findViewById(R.id.logo_text_resto);
        Typeface customAcme = Typeface.createFromAsset(getAssets(), "fonts/Acme-Regular.ttf");
        textLogo.setTypeface(customAcme);

        TextView textLogoSearch = (TextView) findViewById(R.id.logo_text_search);
        Typeface customShadow = Typeface.createFromAsset(getAssets(), "fonts/ShadowsIntoLight.ttf");
        textLogoSearch.setTypeface(customShadow);

        userEmail = (EditText) findViewById(R.id.user_email);
        password = (EditText) findViewById(R.id.password);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());

        TextView textLogin = (TextView) findViewById(R.id.login_button);
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formLogin();
            }
        });

        TextView dontAccount = (TextView) findViewById(R.id.dont_account);
        dontAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void formLogin() {
        String userEmailForm = userEmail.getText().toString();
        String userPassword = password.getText().toString();

        if (TextUtils.isEmpty(userEmailForm)) {
            Toast.makeText(this, "The item email cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmailForm).matches()) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "The item password cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (userPassword.length() < 4) {
            Toast.makeText(this, "The password should have 4 characters at least", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(Utils.intentUserSharedPreferences(this, CategoriesActivity.class, true));
        }
    }
}
