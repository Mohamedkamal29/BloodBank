package app.p.bloodbank.Fragment.Fragment.BottomNavigation.HomeFragment;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import app.p.bloodbank.Adapter.PostRecyclerAdapter;
import app.p.bloodbank.R;
import app.p.bloodbank.SharedPrefManger;
import app.p.bloodbank.data.api.ApiService;
import app.p.bloodbank.data.api.RetrofitClient;
import app.p.bloodbank.data.model.Post.Data;
import app.p.bloodbank.data.model.Post.Post;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostFragment extends Fragment {

    private SharedPrefManger sharedPrefManger;
    private PostRecyclerAdapter adapter;
    Context context;
    int page = 1;
    @BindView(R.id.postRecycler)
    RecyclerView postRecycler;
    @BindView(R.id.ArticlesAndRequests_FloatingButton)
    FloatingActionButton ArticlesAndRequestsFloatingButton;

    public PostFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        //initialize(view);

        initialize(view);
        return view;
    }

    private void initialize(View view) {
        ButterKnife.bind(this, view);
        context = getContext();
        sharedPrefManger = new SharedPrefManger(context);
        postRecycler.setLayoutManager(new LinearLayoutManager(context));
        adapter = new PostRecyclerAdapter();
        postRecycler.setAdapter(adapter);
        getPosts();
    }

    private void getPosts (){

        RetrofitClient.getClient().create(ApiService.class).getPosts(sharedPrefManger.getUserData().getApiToken(), page)
                .enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {


                try {


                    if (response.isSuccessful()) {
                        if (response.body().getStatus() == 1) {
                            adapter.addPosts(response.body().getData().getData());
                            adapter.notifyDataSetChanged();
                            if (response.body().getData().getLastPage() > page) {
                                page++;
                            }
                        }
                    }
                }catch (Exception e)
                {

                }
                }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }


}
