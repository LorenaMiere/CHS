package com.example.app;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceFood;
    private List<Food> food = new ArrayList<>();

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceFood = mDatabase.getReference("Food");
    }

    public interface DataStatus{
        void DataIsLoaded(List<Food> food,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public void selectFood(final DataStatus dataStatus) {
        mReferenceFood.addValueEventListener(new ValueEventListener() {

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                food.clear();
                List<String> keys=new ArrayList<>();
                for(DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                   Food foods= keyNode.getValue(Food.class);
                   food.add(foods);
                }
                dataStatus.DataIsLoaded(food,keys);
            }

            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    public void addFood(Food food,final DataStatus dataStatus){
        String key= mReferenceFood.push().getKey();
        mReferenceFood.child(key).setValue(food)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }
}
