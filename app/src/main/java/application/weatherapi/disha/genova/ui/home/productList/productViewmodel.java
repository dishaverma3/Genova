package application.weatherapi.disha.genova.ui.home.productList;

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

public class productViewmodel extends AndroidViewModel {

    private Context context;
    ArrayList<Item> productList = new ArrayList<>();
    MutableLiveData<String> jsonFromFile = new MutableLiveData<>();
    MutableLiveData<Boolean> setList = new MutableLiveData<>();

    public productViewmodel(@NonNull Application application) {
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

    void getProductsData(String title) {
        try {
            JSONObject productJson = new JSONObject(jsonFromFile.getValue());
            JSONArray itemsJson = productJson.getJSONArray("items");
            ArrayList<String> listOfItems = new ArrayList<>();
            productList = new ArrayList<>();

            for (int i = 0; i < itemsJson.length(); i++) {
                JSONObject item = itemsJson.getJSONObject(i);

                getAllSizes(listOfItems,item);

                Item product = new Item(
                        item.getString("name"),
                        item.getInt("price"),
                        item.getString("type"),
                        item.getString("discount"),
                        item.getString("gender"),
                        listOfItems,
                        item.getString("discount"));

                if((title.equalsIgnoreCase(item.getString("gender")) || (item.getString("gender").equalsIgnoreCase("uni"))))
                    productList.add(product);
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

