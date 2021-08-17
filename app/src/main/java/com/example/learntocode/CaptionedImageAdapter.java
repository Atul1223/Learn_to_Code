package com.example.learntocode;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CaptionedImageAdapter extends RecyclerView.Adapter<CaptionedImageAdapter.ViewHolder> {

    private ArrayList<HomeDetails> homeDetailsArrayList;
    private Context context;
    private ClickInterface clickInterface;


    public CaptionedImageAdapter(ArrayList<HomeDetails> homeDetailsArrayList, Context context, ClickInterface clickInterface) {
        this.homeDetailsArrayList = homeDetailsArrayList;
        this.context = context;
        this.clickInterface = clickInterface;
    }

    public interface ClickInterface{
        public void onClick(int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.card_captioned_layout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        HomeDetails homeDetails=homeDetailsArrayList.get(position);
        String caption=homeDetails.getCourseName();
        int imgRes=homeDetails.getImgRes();

        holder.imageView.setImageResource(imgRes);
        holder.textView.setText(caption);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return homeDetailsArrayList.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView_card);
            textView=itemView.findViewById(R.id.textViewCard);
        }
    }
}
