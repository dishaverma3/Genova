package application.weatherapi.disha.genova.ui.cart;

import android.app.Application;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import application.weatherapi.disha.genova.model.Item;

public class CartViewModel extends AndroidViewModel {

    private Context context;
    MutableLiveData<String> jsonFromFile = new MutableLiveData<>();
    ArrayList<Item> productList = new ArrayList<>();
    ArrayList<Integer> itemInCart = new ArrayList<>();
    MutableLiveData<Boolean> setList = new MutableLiveData<>();
    Integer price = 0;


    public CartViewModel(@NonNull Application application) {
        super(application);

        context = application.getApplicationContext();
    }


    public void loadJSONFromAsset() {
        try {
            InputStream is = context.getAssets().open("products.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonFromFile.setValue(new String(buffer, "UTF-8"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void getProductsData() {
        try {
            JSONObject productJson = new JSONObject(jsonFromFile.getValue());
            JSONArray itemsJson = productJson.getJSONArray("items");
            ArrayList<String> listOfItems = new ArrayList<>();
            productList = new ArrayList<>();

            for (int i = 0; i < itemsJson.length(); i++) {
                JSONObject item = itemsJson.getJSONObject(i);

                getAllSizes(listOfItems,item);

                Item product = new Item(
                        item.getInt("id"),
                        item.getString("name"),
                        item.getInt("price"),
                        item.getString("type"),
                        item.getString("discount"),
                        item.getString("gender"),
                        listOfItems,
                        item.getString("discount"));

                if(itemInCart.contains(item.getInt("id")))
                {
                    productList.add(product);
                    price = price + product.getPrice();
                }
            }

            setList.setValue(true);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void getAllSizes(ArrayList<String> listOfItems, JSONObject item) {
        JSONArray temp = null;
        try {
            temp = item.getJSONArray("sizeAvailable");
            for (int j = 0; j < item.length(); j++) {
                listOfItems.add(temp.getString(j));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
