package com.persLayer;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBase {
    protected DatabaseReference mDatabase;

    public FireBase() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void writeNewRepository(User re) {
        mDatabase.child("Repo").setValue(re);
    }
}
