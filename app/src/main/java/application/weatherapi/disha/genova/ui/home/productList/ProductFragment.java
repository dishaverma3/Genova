package application.weatherapi.disha.genova.ui.home.productList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import application.weatherapi.disha.genova.R;
import application.weatherapi.disha.genova.model.Item;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ProductFragment extends Fragment {

    private static String TAG = "ProductFragment" ;
    String title;
    private RecyclerView recyclerView;
    private ArrayList<Item> productList;
    private ProgressBar progressBar;
    private ProductAdapter adapter;
    private productViewmodel viewModel;

    public static ProductFragment newInstance(String title) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        Log.d(TAG, "newInstance: NEW INSTANCE");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_product, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(productViewmodel.class);

        init(view);

        viewModel.loadJSONFromAsset();

        viewModel.jsonFromFile.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(!s.equals(""))
                    getProductsData(viewModel.jsonFromFile.getValue());
            }
        });

        viewModel.setList.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    setRecyclerView(viewModel.productList);
            }
        });

        return view;
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        title = getArguments().getString("title");

    }

    private void getProductsData(String jsonFromFile) {
        try {
            JSONObject productJson = new JSONObject(jsonFromFile);
            JSONArray itemsJson = productJson.getJSONArray("items");
            ArrayList<String> listOfItems = new ArrayList<>();
            productList = new ArrayList<>();

            for (int i = 0; i < itemsJson.length(); i++) {
                JSONObject item = itemsJson.getJSONObject(i);

                viewModel.getAllSizes(listOfItems,item);

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

        progressBar.setVisibility(View.GONE);
    }

//    private void getAllSizes(ArrayList<String> listOfItems, JSONObject item) {
//        JSONArray temp = null;
//        try {
//            temp = item.getJSONArray("sizeAvailable");
//            for (int j = 0; j < item.length(); j++) {
//                listOfItems.add(temp.getString(j));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }




}
