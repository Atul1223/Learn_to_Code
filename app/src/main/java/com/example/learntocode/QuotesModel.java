package com.example.learntocode;

public class QuotesModel {
    String quote;
    String author;
    QuotesModel (String quote,String author){
        this.author=author;
        this.quote=quote;
    }

    public String getAuthor() {
        return author;
    }

    public String getQuote() {
        return quote;
    }
}
