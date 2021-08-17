package com.example.learntocode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuotesCardAdapter extends RecyclerView.Adapter<QuotesCardAdapter.ViewHolder> {

    private Context context;
    private ArrayList<QuotesModel> quotesModelArrayList;


    public QuotesCardAdapter(Context context, ArrayList<QuotesModel> quotesModelArrayList) {
        this.context = context;
        this.quotesModelArrayList = quotesModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.quotes_card_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        QuotesModel quotesModel=quotesModelArrayList.get(position);

        String author=quotesModel.getAuthor();
        String quotes=quotesModel.getQuote();

        if(author==null){
            author="Unknown";
        }

        holder.quoteText.setText(quotes);
        holder.authorText.setText(author);

    }

    @Override
    public int getItemCount() {
        return quotesModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView quoteText,authorText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quoteText=itemView.findViewById(R.id.quotes_text);
            authorText=itemView.findViewById(R.id.quotes_author);
        }
    }
}
