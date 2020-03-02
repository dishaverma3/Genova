package application.weatherapi.disha.genova.ui.home.productList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import application.weatherapi.disha.genova.R;
import application.weatherapi.disha.genova.model.Item;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Item> list;
    Context context;

    public ProductAdapter(List<Item> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_listitem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        CardView card;
        TextView discount, productName, productType, productPrice;
        ImageView productImage, cartAdd, favouriteIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            card = itemView.findViewById(R.id.container);
            discount = itemView.findViewById(R.id.discount_percentage);
            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productType = itemView.findViewById(R.id.product_type);
            productPrice = itemView.findViewById(R.id.product_price);
            cartAdd = itemView.findViewById(R.id.add_cart_icon);
            favouriteIcon = itemView.findViewById(R.id.fav_icon);
        }

        public void bind(int position) {
            Item listItem = list.get(position);

            discount.setText(String.format("%s%% Off", listItem.getDiscount()));
            productName.setText(listItem.getName());
            productPrice.setText(String.format("%d AED", listItem.getPrice()));
            productType.setText(listItem.getType());
            productImage.setImageResource(R.drawable.spray_image_1);
        }
    }
}
