package application.weatherapi.disha.genova.ui.checkout;

import androidx.appcompat.app.AppCompatActivity;
import application.weatherapi.disha.genova.R;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CheckoutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame,GuestFragment.newInstance()).commit();
    }

}
