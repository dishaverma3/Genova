package application.weatherapi.disha.genova.ui.home.productDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import application.weatherapi.disha.genova.R;
import application.weatherapi.disha.genova.model.Item;
import application.weatherapi.disha.genova.ui.cart.CartActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;

public class productDetailsActivity extends AppCompatActivity {

    Item item;
    Toolbar toolbar;
    ImageView favourite,productImage;
    TextView discount,productType,productName,productPrice,productSize1,productSize2,productSize3,productSize4, productDetails, quantityValue;
    Button decrementBtn, incrementBtn, addToCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Product Details");

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
                    int i = Integer.parseInt(quantityValue.getText().toString());
                    quantityValue.setText((i-1)+"");
                    item.setQuantity(i-1);
                }

            }
        });

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addToCartButton.getText().toString().equalsIgnoreCase("add to cart"))
                {
                    addToCartButton.setText("Go to Cart");
                    Log.d("kjdfvkf", "onClick: ID SENT -- "+item.getId());
                    CartActivity.addItemToCart(item.getId());
                }
                else {
                    //TODO: pass intent to cart
//                    SharedPreferences.Editor editShared = getSharedPreferences("Cart Item",MODE_PRIVATE).edit();
//                    editShared.putInt("id",item.getId()).apply();
                    startActivity(new Intent(productDetailsActivity.this,CartActivity.class));
                }
            }
        });

        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!item.isFavourite())
                {
                    favourite.setImageResource(R.drawable.ic_favorite_black_24dp);
                    item.setFavourite(true);
                }else {
                    favourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    item.setFavourite(false);
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
        productImage = findViewById(R.id.product_image);
        productType = findViewById(R.id.product_type);
        productName = findViewById(R.id.product_name);
        productSize1 = findViewById(R.id.size1Text);
        productSize2 = findViewById(R.id.size2Text);
        productSize3 = findViewById(R.id.size3Text);
        productSize4 = findViewById(R.id.size4Text);
        productDetails = findViewById(R.id.product_details);
        quantityValue = findViewById(R.id.quantity_value);
        addToCartButton = findViewById(R.id.add_cart_button);
        favourite = findViewById(R.id.image_favourite);

        decrementBtn = findViewById(R.id.decrement_button);
        incrementBtn = findViewById(R.id.increment_button);

        productImage.setImageResource(getResources().getIdentifier("spray_image_"+item.getId(),"drawable",getPackageName()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
