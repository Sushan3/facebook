package com.example.fb;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fb.Adapater.FacebookRecyclerAdapater;
import com.example.fb.Api.Facebook;
import com.example.fb.Bll.LoginCheck;
import com.example.fb.Model.ApiUser;
import com.example.fb.Model.LoadData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DashboardActivity extends AppCompatActivity {

    ImageView circleImageView;
    private Retrofit retrofit;
    private Facebook facebook;
    public static String imagePath = "uploads/" ;
    RecyclerView recyclerView;
    List<LoadData> timeline = new ArrayList<>();
    TextView dashboardtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        circleImageView = findViewById(R.id.imgProgileImg);
        dashboardtop = findViewById(R.id.dashboardtopname);

        loadCurrentUser();

        recyclerView=findViewById(R.id.rvfbtimeline);

        timelineList();



    }

    private void loadCurrentUser() {

        retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        facebook = retrofit.create(Facebook.class);

        Call<ApiUser> userCall = facebook.getUserDetails(LoginCheck.token);

        userCall.enqueue(new Callback<ApiUser>() {
            @Override
            public void onResponse(Call<ApiUser> call, Response<ApiUser> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DashboardActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                String imgPath = imagePath +  response.body().getProfileimage();

                dashboardtop.setText("Welcome "+response.body().getFirstname()+" ");

                Picasso.with(DashboardActivity.this)
                        .load("http://10.0.2.2:5000/"+imgPath)
                        .into(circleImageView);

            }

            @Override
            public void onFailure(Call<ApiUser> call, Throwable t) {

                Toast.makeText(DashboardActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private  void timelineList(){
        Call<List<LoadData>> timelineList = facebook.getTimelines(LoginCheck.token);
        timelineList.enqueue(new Callback<List<LoadData>>() {
            @Override
            public void onResponse(Call<List<LoadData>> call, Response<List<LoadData>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(DashboardActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<LoadData> timList = response.body();

                for (LoadData tim: timList) {

                    timeline.add(new LoadData(tim.getFullname(),tim.getStatus(),tim.getTime(),tim.getLoadimage()));

                }

                FacebookRecyclerAdapater adapter = new FacebookRecyclerAdapater(timeline,DashboardActivity.this);
                RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(DashboardActivity.this);


                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<LoadData>> call, Throwable t) {
                Log.d("ApiEx:",t.getMessage());

            }
        });

    }
}
