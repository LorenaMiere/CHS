package com.example.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerVire_config {
    private Context mContext;
    private FoodAdapter mFoodAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Food> foods, List<String> keys) {
        mContext = context;
        mFoodAdapter = new FoodAdapter(foods, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mFoodAdapter);

    }

    class FoodItem extends RecyclerView.ViewHolder {
        private TextView foodN;
        private TextView cal;


        private String key;

        public FoodItem(ViewGroup parent) {
            super(LayoutInflater.from(mContext).inflate(R.layout.food_list, parent, false));
            foodN = (TextView) itemView.findViewById(R.id.nameOfFood);
            cal = (TextView) itemView.findViewById(R.id.calories);


        }

        public void bind(Food food, String key) {
            foodN.setText(food.getName());
            cal.setText(Long.toString(food.getCalories()));
            System.out.println(cal);
            this.key = key;
        }
    }
       class  FoodAdapter extends RecyclerView.Adapter<FoodItem> {
            private List<Food> foodL;
           private List<String> fkeys;

           public FoodAdapter(List<Food> foodL, List<String> fkeys) {
               this.foodL = foodL;
               this.fkeys = fkeys;
           }




           @NonNull
           @Override
           public FoodItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               return new FoodItem(parent) ;
           }

           @Override
           public void onBindViewHolder(@NonNull FoodItem holder, int position) {
                holder.bind(foodL.get(position),fkeys.get(position));
           }

           @Override
           public int getItemCount() {
               return foodL.size();
           }
       }
}
