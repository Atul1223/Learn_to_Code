package com.example.learntocode;

public class HomeDetails {

    String courseName;
    int imgRes;


    HomeDetails(String name,int res) {
        this.courseName=name;
        this.imgRes=res;
    }

    static final HomeDetails[] homeDetails={
            new HomeDetails("Android", R.drawable.androidcard),
            new HomeDetails("Cpp", R.drawable.cppcard),
            new HomeDetails("Java", R.drawable.javacard),
            new HomeDetails("DSA", R.drawable.dsacard)
    };

    public String getCourseName(){
        return courseName;
    }

    public int getImgRes(){
        return imgRes;
    }
}
