package application.weatherapi.disha.genova.ui.checkout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import application.weatherapi.disha.genova.R;

public class ShippingFragment extends Fragment {

    Spinner title, city, country, countryCode;
    TextInputEditText fullName, poBox, address;
    EditText number, otpEdit;
    TextView otpHead;
    Button verify, addAddress;

    public ShippingFragment() {
        // Required empty public constructor
    }

    public static ShippingFragment newInstance(String email) {
        ShippingFragment fragment = new ShippingFragment();
        Bundle args = new Bundle();
        args.putString("email",email);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_shipping, container, false);

        init(v);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidations())
                {
                    otpEdit.setVisibility(View.VISIBLE);
                    otpHead.setVisibility(View.VISIBLE);
                }
            }
        });

        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkValidations())
                {
                    if(!TextUtils.isEmpty(otpEdit.getText().toString()))
                    {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,PaymentFragment.newInstance()).commit();
                    }
                    else {
                        Toast.makeText(getActivity(),"Enter code",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return v;
    }

    private boolean checkValidations() {
        if(TextUtils.isEmpty(fullName.getText()))
        {
            fullName.setError("Name is empty");return false;
        }
        else if(TextUtils.isEmpty(poBox.getText()))
        {
            poBox.setError("P.O. Box is empty");return false;
        }
        else if(TextUtils.isEmpty(address.getText()))
        {
            address.setError("Address can't be empty");return false;
        }
        else if(title.getSelectedItem().toString().equals("Title"))
        {
            Toast.makeText(getActivity(),"Select Title",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(city.getSelectedItem().toString().equals("Select City"))
        {
            Toast.makeText(getActivity(),"Select City",Toast.LENGTH_LONG).show();return false;
        }
        else if(country.getSelectedItem().toString().equals("Select Country"))
        {
            Toast.makeText(getActivity(),"Select Country",Toast.LENGTH_LONG).show();return false;
        }
        else if(countryCode.getSelectedItem().toString().equals("Select Code"))
        {
            Toast.makeText(getActivity(),"Select Code",Toast.LENGTH_LONG).show();return false;
        }
        else if(number.getText().toString().length() < 10)
        {
            Toast.makeText(getActivity(),"Number is invalid",Toast.LENGTH_LONG).show();return false;
        }
        else return true;
    }

    private void init(View v) {
        fullName = v.findViewById(R.id.fullNameEdit);
        verify = v.findViewById(R.id.verifyButton);
        poBox = v.findViewById(R.id.PoBoxEdit);
        address = v.findViewById(R.id.addressEdit);
        number = v.findViewById(R.id.phoneNumberEdit);
        addAddress = v.findViewById(R.id.add_address_button);

        title = v.findViewById(R.id.titleSpinner);
        city = v.findViewById(R.id.citySpinner);
        country = v.findViewById(R.id.countrySpinner);
        countryCode = v.findViewById(R.id.codeSpinner);

        otpEdit = v.findViewById(R.id.verifyCodeEdit);
        otpHead = v.findViewById(R.id.verifyCodeHead);

        otpHead.setVisibility(View.GONE);
        otpEdit.setVisibility(View.VISIBLE);
    }
}
