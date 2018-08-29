package com.example.workingwithadapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

@SuppressLint("Registered")
class Contact extends AppCompatActivity{
    String  contact_name;
    String contact_phone_number;
    int cont_img;
    Context mContext;
    public static List<Contact> mContact; // list of Contact objects

    private static String[] names = {
            "Асылбек",
            "Марсел",
            "Арзымат",
            "Кубаныч",
            "Бакыт",
            "Айдарбек",
            "Токтосун",
            "Жыргалбек",
            "Жылдыз"};

    private static String[] phone_numbers = {
            "0779331166",
            "0773441545",
            "0775457854",
            "0778532078",
            "0776451545",
            "0777152132",
            "0556489862",
            "0706441415",
            "0700123396"};

    public static int[] imgs = {
            R.drawable.im1,
            R.drawable.im2,
            R.drawable.im3,
            R.drawable.im4,
            R.drawable.im1,
            R.drawable.ic_launcher_background,
            R.drawable.im4,
            R.drawable.im2,
            R.drawable.ic_launcher_foreground };


    Contact(String contact_name, String contact_phone_number, int cont_img, Context context) {
        this.contact_name = contact_name;
        this.contact_phone_number = contact_phone_number;
        this.cont_img = cont_img;
        this.mContext = context;
    }



   /* @RequiresApi(api = Build.VERSION_CODES.O)
   public void initializeData(){
        mContact = new ArrayList<>();
        for(int i=0;i<names.length;i++){
            mContact.add(new Contact(names[i],phone_numbers[i],imgs[i],this));
        }
    }*/

}