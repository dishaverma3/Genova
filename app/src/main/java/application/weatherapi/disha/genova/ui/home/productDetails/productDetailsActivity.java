package application.weatherapi.disha.genova.ui.home.productDetails;

import androidx.appcompat.app.AppCompatActivity;
import application.weatherapi.disha.genova.R;
import application.weatherapi.disha.genova.model.Item;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class productDetailsActivity extends AppCompatActivity {

    Item item;
    TextView discount,productType,productName,productPrice,productSize1,productSize2,productSize3,productSize4, productDetails, quantityValue;
    Button decrementBtn, incrementBtn, addToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Product Details");

        init();
        setValues();

        incrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!quantityValue.getText().equals("100"))
                {
                    int i = Integer.parseInt(quantityValue.getText().toString());
                    quantityValue.setText((i+1)+"");
                    item.setQuantity(i+1);
                }
            }
        });

        decrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!quantityValue.getText().equals("1"))
                {
                    quantityValue.setText((Integer.parseInt(quantityValue.getText().toString())-1)+"");
                }

            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addToCartButton.getText().toString().equalsIgnoreCase("add to cart"))
                    addToCartButton.setText("Go to Cart");
                else {
                    //TODO: pass intent to cart
                }
            }
        });


    }

    private void setValues() {
        discount.setText(item.getDiscount()+"% Off");
        productName.setText(item.getName());
        productType.setText(item.getType());
        productPrice.setText(item.getPrice()+" AED");

        List<String> sizes = new ArrayList<>();

        for(int i = 0; i < item.getSizeAvailable().size(); i++)
        {
            sizes.add(item.getSizeAvailable().get(i));
        }

        productSize1.setText(sizes.get(0));
        productSize2.setText(sizes.get(1));
        productSize3.setText(sizes.get(2));
        productSize4.setText(sizes.get(3));

        productDetails.setText(item.getDetails());

    }

    private void init() {
        item = (Item) getIntent().getSerializableExtra("value");
        discount = findViewById(R.id.discount);
        productPrice = findViewById(R.id.product_price);
        productType = findViewById(R.id.product_type);
        productName = findViewById(R.id.product_name);
        productSize1 = findViewById(R.id.size1Text);
        productSize2 = findViewById(R.id.size2Text);
        productSize3 = findViewById(R.id.size3Text);
        productSize4 = findViewById(R.id.size4Text);
        productDetails = findViewById(R.id.product_details);
        quantityValue = findViewById(R.id.quantity_value);
        addToCartButton = findViewById(R.id.add_cart_button);

        decrementBtn = findViewById(R.id.decrement_button);
        incrementBtn = findViewById(R.id.increment_button);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
