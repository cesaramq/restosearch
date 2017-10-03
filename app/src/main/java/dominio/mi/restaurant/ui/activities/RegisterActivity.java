package dominio.mi.restaurant.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import dominio.mi.restaurant.R;
import dominio.mi.restaurant.Utils;

public class RegisterActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView dateTextView;
    private EditText userEmail;
    private EditText password;
    private EditText fullname;
    private EditText confirmPassword;
    private CheckBox terms;
    private static final int MIN_PASSWORD_LENGTH = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView textLogo = (TextView) findViewById(R.id.logo_text_resto);
        textLogo.setTypeface(Utils.getFontsAcme(getAssets()));

        TextView textLogoSearch = (TextView) findViewById(R.id.logo_text_search);
        textLogoSearch.setTypeface(Utils.getFontShadow(getAssets()));

        fullname = (EditText) findViewById(R.id.fullname);
        userEmail = (EditText) findViewById(R.id.email);

        password = (EditText) findViewById(R.id.password);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());

        confirmPassword = (EditText) findViewById(R.id.confirm_password);
        confirmPassword.setTypeface(Typeface.DEFAULT);
        confirmPassword.setTransformationMethod(new PasswordTransformationMethod());
        terms = (CheckBox) findViewById(R.id.terms);

        TextView registerText = (TextView) findViewById(R.id.signin_button);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formRegister();
            }
        });

        TextView haveAccount = (TextView) findViewById(R.id.have_account);
        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        dateTextView = (TextView) findViewById(R.id.bithdate);

        Calendar now = Calendar.getInstance();
        final DatePickerDialog dpd = DatePickerDialog.newInstance(
                RegisterActivity.this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpd.show(getFragmentManager(), "Datepickerdialog");
                dpd.setVersion(DatePickerDialog.Version.VERSION_2);
            }
        });


    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = "" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
        dateTextView.setText(date);
    }

    private void formRegister() {
        String userFullNameForm = fullname.getText().toString();
        String userEmailForm = userEmail.getText().toString();
        String userPassword = password.getText().toString();
        String userPasswordConfirm = confirmPassword.getText().toString();

        if (TextUtils.isEmpty(userFullNameForm)) {
            Toast.makeText(this, "The item fullname cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userEmailForm)) {
            Toast.makeText(this, "The item email cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmailForm).matches()) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "The item password cannot be empty", Toast.LENGTH_SHORT).show();
        } else if (userPassword.length() < MIN_PASSWORD_LENGTH) {
            Toast.makeText(this, "The password should have 4 characters at least", Toast.LENGTH_SHORT).show();
        } else if (!userPasswordConfirm.equals(userPassword)) {
            Toast.makeText(this, "The passwords are not the same", Toast.LENGTH_SHORT).show();
        } else if (!terms.isChecked()) {
            Toast.makeText(this, "You have to accept the Terms and Conditions", Toast.LENGTH_SHORT).show();
        } else {
            startActivity(Utils.intentUserSharedPreferences(this, CategoriesActivity.class, true));
        }
    }
}

