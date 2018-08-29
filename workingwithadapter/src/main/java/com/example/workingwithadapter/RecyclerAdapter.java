package com.example.workingwithadapter;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ContactViewHolder>  {
    private List<Contact> contacts;
    private Context mContext;
    private LayoutInflater inflater;
    RecyclerAdapter(List<Contact> contacts, Context context) {
        this.contacts = contacts;  // we create it to get access to Contact
        this.mContext = context;
    }



    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_contact_item,parent,false);
        return new ContactViewHolder(view);
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //  Бул жерде CardView' га маанилерди беребиз
        holder.cont_name.setText(contacts.get(position).contact_name);
        holder.cont_phone_number.setText(contacts.get(position).contact_phone_number);
        holder.cont_img.setImageResource(R.drawable.im1 );

        //  LISTENER BERSEK DA BOLOT
        holder.cont_img.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                inflater = LayoutInflater.from(mContext);
                @SuppressLint("InflateParams")
                View view1 = inflater.inflate(R.layout.alert_img,null);
                builder.setView(view1);
                builder.setNegativeButton("OK, Let's close it!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, holder.cont_name.getText().toString().trim()+"\n"
                        +holder.cont_phone_number.getText().toString().trim(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size(); //
    }

    // INITIALIZE  OUR VIEWGROUP

    class ContactViewHolder extends RecyclerView.ViewHolder {


        ImageView cont_img;
        TextView cont_name,cont_phone_number;
        CardView cardView;


        ContactViewHolder(View itemView) {
            super(itemView);

            cont_img = itemView.findViewById(R.id.contact_img);
            cont_name = itemView.findViewById(R.id.contact_name);
            cont_phone_number = itemView.findViewById(R.id.contact_phone_number);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

        }



}
}
