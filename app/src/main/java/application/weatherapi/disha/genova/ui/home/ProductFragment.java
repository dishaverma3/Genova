package application.weatherapi.disha.genova.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import application.weatherapi.disha.genova.R;
import application.weatherapi.disha.genova.model.Item;
import application.weatherapi.disha.genova.model.Product;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductFragment extends Fragment {

    String title;
    private RecyclerView recyclerView;
    private ArrayList<Item> productList;
    private ProductAdapter adapter;

    public static ProductFragment newInstance(String title) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        
        init(view);
        getProductsData(loadJSONFromAsset());
        
        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView); 
    }

    private void getProductsData(String jsonFromFile) {
        try {
            JSONObject productJson = new JSONObject(jsonFromFile);
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

            setRecyclerView(productList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setRecyclerView(ArrayList<Item> productList) {
        adapter = new ProductAdapter(productList,getActivity());
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void getAllSizes(ArrayList<String> listOfItems, JSONObject item) {
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

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("products.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}
