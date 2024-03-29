package com.app.youthlive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.app.youthlive.Activitys.RattingActivity;
import com.app.youthlive.GetRankingPOJO.Datum;
import com.app.youthlive.GetRankingPOJO.RankingBean;
import com.app.youthlive.followPOJO.followBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by USER on 12/18/2017.
 */

public class Rating3 extends Fragment {


    RecyclerView grid;
    LinearLayoutManager manager;
    RankingAdapter2 adapter1;
    List<Datum> list;
    ProgressBar bar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.rating1, container, false);


        grid = view.findViewById(R.id.grid);
        manager = new LinearLayoutManager(getContext());

        bar = view.findViewById(R.id.progress);

        list = new ArrayList<>();
        adapter1 = new RankingAdapter2(getContext(), list);

        grid.setAdapter(adapter1);
        grid.setLayoutManager(manager);

        bar.setVisibility(View.VISIBLE);

        bean b = (bean) getContext().getApplicationContext();

        Call<RankingBean> call = b.getRetrofit().ranking(SharePreferenceUtils.getInstance().getString("userId"), "weekly");

        call.enqueue(new Callback<RankingBean>() {
            @Override
            public void onResponse(Call<RankingBean> call, Response<RankingBean> response) {


                adapter1.setgrid(response.body().getData());

                bar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<RankingBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });
        return view;
    }


    public class RankingAdapter2 extends RecyclerView.Adapter<RankingAdapter2.MyViewHolder> {

        Context context;

        List<Datum> list = new ArrayList<>();

        public RankingAdapter2(Context context, List<Datum> list) {

            this.context = context;
            this.list = list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(context).inflate(R.layout.tabratting_adapterlayout, parent, false);

            return new MyViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.setIsRecyclable(false);

            final Datum item = list.get(position);


            holder.name.setText(item.getUserName());
            holder.beans.setText("Coins - " + item.getBeans());
            holder.change.setText(String.valueOf(position + 1));
            holder.rankno.setText(String.valueOf(position + 1));
            if (position == 0)
            {
                holder.medalimg.setImageDrawable(context.getDrawable(R.drawable.ic_gold));
            }
            else if (position == 1)
            {
                holder.medalimg.setImageDrawable(context.getDrawable(R.drawable.ic_silver));
            }
            else if (position == 2)
            {
                holder.medalimg.setImageDrawable(context.getDrawable(R.drawable.ic_bronze));
            }


            if (position < 3) {
                holder.rankno.setVisibility(View.GONE);
                holder.medalimg.setVisibility(View.VISIBLE);
                holder.change.setVisibility(View.GONE);
            } else {
                holder.rankno.setVisibility(View.VISIBLE);
                holder.medalimg.setVisibility(View.GONE);
                holder.change.setVisibility(View.GONE);

            }

            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .cacheOnDisc(true).resetViewBeforeLoading(false).build();
            if (!item.getUserImage().isEmpty()) {
                ImageLoader loader = ImageLoader.getInstance();
                loader.displayImage(item.getUserImage(), holder.image, options);
            }


            //////////////////check follow status/////////////////////////


            final bean b = (bean) getApplicationContext();

            Call<followBean> call = b.getRetrofit().followcheck(SharePreferenceUtils.getInstance().getString("userId"), item.getUserId());

            call.enqueue(new Callback<followBean>() {
                @Override
                public void onResponse(Call<followBean> call, Response<followBean> response) {

                    try {
                        if (!item.getUserId().toString().equals(SharePreferenceUtils.getInstance().getString("userId"))) {
                            if (response.body().getMessage().equals("Following")) {
                                holder.follow.setBackgroundResource(R.drawable.ic_checked);
                            }
                            if (response.body().getMessage().equals("Not Following")) {
                                holder.follow.setBackgroundResource(R.drawable.ic_plus);
                            }

                        } else {
                            holder.follow.setVisibility(View.GONE);
                            holder.ratingcard.setCardBackgroundColor(Color.parseColor("#ffef99"));
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        // Toast.makeText(PersonalInfo.this, "Some Error Occurred, Please try again follow", Toast.LENGTH_SHORT).show();
                    }


                    bar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<followBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });


            ////////////////////////////////////////////////////////////////

            holder.follow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    bar.setVisibility(View.VISIBLE);

                    final bean b = (bean) context.getApplicationContext();


                    Call<followBean> call = b.getRetrofit().follow(SharePreferenceUtils.getInstance().getString("userId"), item.getUserId());

                    call.enqueue(new Callback<followBean>() {
                        @Override
                        public void onResponse(Call<followBean> call, Response<followBean> response) {

                            if (!item.getUserId().equals(SharePreferenceUtils.getInstance().getString("userId"))) {
                                if (response.body().getMessage().equals("Follow Success")) {
                                    holder.follow.setBackgroundResource(R.drawable.ic_checked);
                                }
                                if (response.body().getMessage().equals("Unfollow Success")) {
                                    holder.follow.setBackgroundResource(R.drawable.ic_plus);
                                }
                                ((RattingActivity) Objects.requireNonNull(getActivity())).methodd();
                                bar.setVisibility(View.GONE);

                            }
                        }

                        @Override
                        public void onFailure(Call<followBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);

                        }
                    });


                }
            });

            holder.profilelayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), TimelineProfile.class);
                    intent.putExtra("userId", item.getUserId());
                    startActivity(intent);
                }
            });


        }


        public void setgrid(List<Datum> list) {

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView beans, name, change, rankno;

            ImageView image, follow, medalimg;
            RelativeLayout profilelayout;
            CardView ratingcard;


            public MyViewHolder(View itemView) {
                super(itemView);


                beans = itemView.findViewById(R.id.beans);
                name = itemView.findViewById(R.id.name);
                change = itemView.findViewById(R.id.change);
                image = itemView.findViewById(R.id.image);
                follow = itemView.findViewById(R.id.follow);
                profilelayout = itemView.findViewById(R.id.profilelayout_click);
                medalimg = itemView.findViewById(R.id.medal_img);
                rankno = itemView.findViewById(R.id.rankno);
                ratingcard = itemView.findViewById(R.id.ratingcard);

            }
        }
    }
}
