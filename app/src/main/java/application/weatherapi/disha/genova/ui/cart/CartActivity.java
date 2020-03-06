package application.weatherapi.disha.genova.ui.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import application.weatherapi.disha.genova.R;
import application.weatherapi.disha.genova.model.Item;
import application.weatherapi.disha.genova.ui.checkout.CheckoutActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    static ArrayList<Integer> cartItems = new ArrayList<>();
    private static String TAG = "knkdvkjfnvd";
    RecyclerView recyclerView;
    CartViewModel viewModel;
    private CartAdapter adapter;
    private static TextView priceTotal;
    static Integer totalPrice = 0;
    private Button checkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        init();

        viewModel.loadJSONFromAsset();
        viewModel.itemInCart = cartItems;
        viewModel.jsonFromFile.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null)
                    viewModel.getProductsData();
            }
        });

        viewModel.setList.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                {
                    setRecycler(viewModel.productList);
                    priceTotal.setText("AED "+viewModel.price);
                    totalPrice = viewModel.price;
                }
            }
        });

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CartActivity.this, CheckoutActivity.class);
                Bundle b = new Bundle();
                b.putIntegerArrayList("cartItems",cartItems);
                i.putExtras(b);
                startActivity(i);
            }
        });


    }

    private void setRecycler(ArrayList<Item> productList) {
        adapter = new CartAdapter(productList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        if(totalPrice == 0)
            checkoutButton.setEnabled(false);
        else checkoutButton.setEnabled(true);
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        priceTotal = findViewById(R.id.price_total);
        checkoutButton = findViewById(R.id.checkout_button);

        Log.d(TAG, "init: ITEMS -- "+ cartItems.get(0));
    }

    public static void addItemToCart(int id){
        cartItems.add(id);
        Log.d(TAG, "addItemToCart: CALLED");
    }

    public static void priceChange(int price){
        priceTotal.setText((totalPrice - price)+" AED");
    }
}
