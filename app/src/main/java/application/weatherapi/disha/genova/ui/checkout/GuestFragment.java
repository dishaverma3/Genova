package application.weatherapi.disha.genova.ui.checkout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import application.weatherapi.disha.genova.R;


public class GuestFragment extends Fragment {

    private Button guestLoginButton;
    private TextInputEditText emailText;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public GuestFragment() {
        // Required empty public constructor
    }
    public static GuestFragment newInstance() {
        GuestFragment fragment = new GuestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_guest,container,false);
        init(v);

        guestLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(emailText.getText()) || !(emailText.getText().toString().trim().matches(emailPattern)))
                {
                    Toast.makeText(getContext(),"Email id is not correct",Toast.LENGTH_LONG).show();
                    emailText.setError("Wrong Email id");
                }
                else {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,ShippingFragment.newInstance(emailText.getText().toString())).commit();
                }
            }
        });
        return v;
    }


    private void init(View v) {
        guestLoginButton = v.findViewById(R.id.guestLoginButton);
        emailText = v.findViewById(R.id.emailGuestEdit);
    }

}
