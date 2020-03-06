package application.weatherapi.disha.genova.ui.cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import application.weatherapi.disha.genova.R;
import application.weatherapi.disha.genova.model.Item;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    List<Item> list;
    Context context;

    public CartAdapter(List<Item> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_listitem,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView productImage,closeImage;
        TextView prodType, prodName, prodPrice, prodQuantity, prodQuantityByUser;
        Button increment, decrement;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.product_image);
            closeImage = itemView.findViewById(R.id.close);
            prodName = itemView.findViewById(R.id.product_name);
            prodPrice = itemView.findViewById(R.id.product_price);
            prodQuantity = itemView.findViewById(R.id.product_quantity);
            increment = itemView.findViewById(R.id.increment_button);
            decrement = itemView.findViewById(R.id.decrement_button);
            prodType = itemView.findViewById(R.id.product_type);
            prodQuantityByUser = itemView.findViewById(R.id.quantity_value);
        }

        public void bind(final int position) {
            final Item listItem = list.get(position);
            prodName.setText(listItem.getName());
            prodPrice.setText(listItem.getPrice()+" AED");
            prodType.setText(listItem.getType());
            prodQuantity.setText("120 ml");
            prodQuantityByUser.setText(listItem.getQuantity()+"");
            productImage.setImageResource(context.getResources().getIdentifier("spray_image_"+listItem.getId(),"drawable",context.getPackageName()));

            closeImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(position);
                    CartActivity.priceChange(listItem.getPrice());
                    notifyItemRemoved(position);
                }
            });

            increment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!prodQuantityByUser.getText().equals("100"))
                    {
                        int i = Integer.parseInt(prodQuantityByUser.getText().toString());
                        prodQuantityByUser.setText((i+1)+"");
                        listItem.setQuantity(i+1);
                    }
                }
            });

            decrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!prodQuantityByUser.getText().equals("1"))
                    {
                        int i = Integer.parseInt(prodQuantityByUser.getText().toString());
                        prodQuantityByUser.setText((i-1)+"");
                        listItem.setQuantity(i-1);
                    }

                }
            });

        }
    }
}
