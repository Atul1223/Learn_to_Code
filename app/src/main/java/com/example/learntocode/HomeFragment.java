package com.example.learntocode;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class HomeFragment extends Fragment implements CaptionedImageAdapter.ClickInterface{

    ArrayList<HomeDetails> homeDetailsArrayList;
    ArrayList<QuotesModel> quotesModelArrayList;
    int a;
    QuotesCardAdapter quotesCardAdapter;
    ProgressBar progressBar;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=(RelativeLayout)inflater.inflate(R.layout.home_fragment,container,false);
        homeDetailsArrayList=new ArrayList<>();
        quotesModelArrayList=new ArrayList<>();

        progressBar=(ProgressBar)v.findViewById(R.id.progBar);
        progressBar.setVisibility(View.VISIBLE);

        RecyclerView recyclerViewQuotes=(RecyclerView)v.findViewById(R.id.quotesCard) ;
        quotesCardAdapter=new QuotesCardAdapter(getActivity(),quotesModelArrayList);
        RecyclerView.LayoutManager layoutManagerQuotes=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerViewQuotes.setLayoutManager(layoutManagerQuotes);
        recyclerViewQuotes.setAdapter(quotesCardAdapter);

        RecyclerView recyclerViewCourse=(RecyclerView)v.findViewById(R.id.courseCard);
        CaptionedImageAdapter captionedImageAdapter=new CaptionedImageAdapter(homeDetailsArrayList,getActivity(),this::onClick);
        recyclerViewCourse.setAdapter(captionedImageAdapter);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getActivity(),2);
        recyclerViewCourse.setLayoutManager(layoutManager);
        for (int i=0;i<HomeDetails.homeDetails.length;i++){
            homeDetailsArrayList.add(i,HomeDetails.homeDetails[i]);
        }
        captionedImageAdapter.notifyDataSetChanged();
        getQuoteFromApi();
        return v;
    }

    @Override
    public void onClick(int position) {
        String course=homeDetailsArrayList.get(position).courseName;
        openCourse(course);
    }
    public void openCourse(String course){
        Fragment fragment=null;
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (course){
            case "Android": fragment=new AndroidFragment();
                break;
            case "Cpp":fragment=new CppFragment();
                break;
            case "Java":fragment=new JavaFragment();
                break;
            case "DSA":fragment=new DSAfragment();
                break;
        }
        fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame,fragment).addToBackStack("my_fragment");
        fragmentTransaction.commit();
    }

    public void getQuoteFromApi(){
        quotesModelArrayList.clear();
        String url="https://type.fit/api/quotes";
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                try {
                    Random random=new Random();
                    a=random.nextInt(response.length());
                    JSONObject jsonObject=response.getJSONObject(a);
                    String quote=jsonObject.getString("text");
                    String author=jsonObject.getString("author");
                    quotesModelArrayList.add(new QuotesModel(quote,author));
                    quotesCardAdapter.notifyDataSetChanged();

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Fail to load",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}
