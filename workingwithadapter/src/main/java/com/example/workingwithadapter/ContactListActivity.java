package com.example.workingwithadapter;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

@SuppressLint("Registered")

public class ContactListActivity  extends AppCompatActivity {

    RecyclerView recyclerView;
    private ArrayList<Contact> mContact = new ArrayList<>();

    private final String mFROM_COLUMNS[] = {
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.Data.DATA1,
            ContactsContract.Contacts.Photo.PHOTO,
            ContactsContract.Contacts._ID
};


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_activity);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true); // it means that size of rv won't change

        /*
        *  LayoutManager отвечает за позиционирование view-компонентов в RecyclerView
        */
//        Contact.initializeData();
        getContData();
        setRecyclerViewAdapter();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    void getContData(){
        ContentResolver contentResolver = this.getContentResolver();
        @SuppressLint("Recycle") Cursor phones =
                contentResolver.query(ContactsContract.Data.CONTENT_URI,mFROM_COLUMNS,
                        null,null,null);
        assert phones != null;
        while (phones.moveToNext()){
            String id = phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID));

            //int img = phones.getInt(phones.getColumnIndex(ContactsContract.Photo.PHOTO));

          /* // if(phones.getInt(phones.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))>0) {
            *//*    @SuppressLint("Recycle")
                Cursor mCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{id},
                        null);
                assert mCursor != null;*//*

          */      // mPhone_number = mCursor.getString(mCursor.getColumnIndex(ContactsContract.PhoneLookup.NUMBER));
                String mPhone_number = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String contact_name = phones.getString(phones.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
                //Log.d("...","name: "+contact_name+"\n"+"number: "+mPhone_number);

                mContact.add(new Contact(contact_name, mPhone_number, 1, this));


                Log.d("...", "name: " + contact_name + "\n" + "number: " + mPhone_number);
           // }
            }
//            String contact_phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            int img = phones.getInt(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
//            mContact.add(new Contact(contact_name,contact_phone,img,this));
        phones.close();
    }

    void setRecyclerViewAdapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter mRecyclerAdapter = new RecyclerAdapter(mContact, this);
        recyclerView.setAdapter(mRecyclerAdapter);
    }



}

