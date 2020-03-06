package application.weatherapi.disha.genova.ui.checkout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import application.weatherapi.disha.genova.R;
import application.weatherapi.disha.genova.ui.basic.BasicActivity;

public class PaymentFragment extends Fragment {
    private Button payNowButton;

    public PaymentFragment() {
        // Required empty public constructor
    }

    public static PaymentFragment newInstance() {
        PaymentFragment fragment = new PaymentFragment();
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
        View v = inflater.inflate(R.layout.fragment_payment, container, false);
        init(v);

        payNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Payment done. Order has been placed",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getActivity(), BasicActivity.class));
            }
        });

        return v;
    }

    private void init(View v) {
        payNowButton = v.findViewById(R.id.payNowButton);
    }

}
