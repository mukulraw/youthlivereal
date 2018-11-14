package com.yl.youthlive;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yl.youthlive.Activitys.Diamond_purchase_history_Activity;
import com.yl.youthlive.Activitys.HistoryActivity;
import com.yl.youthlive.walletPOJO.walletBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WalletHistory extends Fragment {

    ScrollView scrollView;
    ProgressBar progress;
    TextView coins, purchase_history, gift_history, coin_history, exchange_history;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wallethistory_layout, container, false);
        progress = view.findViewById(R.id.progressBar6b);
        coins = view.findViewById(R.id.textView12);
        scrollView = view.findViewById(R.id.scroll);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);
        purchase_history = view.findViewById(R.id.textView68);
        gift_history = view.findViewById(R.id.textView69);
        coin_history = view.findViewById(R.id.textView65);
        exchange_history = view.findViewById(R.id.textView70);
        purchase_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getContext(), Diamond_purchase_history_Activity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);


            }
        });
        gift_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);


            }
        });
        coin_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);


            }
        });
        exchange_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getContext(), HistoryActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getCoinsData();
    }

    public void getCoinsData() {
        progress.setVisibility(View.VISIBLE);


        final bean b = (bean) getContext().getApplicationContext();
        Call<walletBean> call = b.getRetrofit().getWalletData(SharePreferenceUtils.getInstance().getString("userId"));
        Log.d("userId", SharePreferenceUtils.getInstance().getString("userId"));

        call.enqueue(new Callback<walletBean>() {
            @Override
            public void onResponse(Call<walletBean> call, Response<walletBean> response) {

                try {
                    if (!response.body().getData().getDiamond().isEmpty()) {
                        //  Toast.makeText(BuyDiamonds.this, "Purchase done", Toast.LENGTH_SHORT).show();
                        coins.setText(response.body().getData().getBeans());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                progress.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<walletBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }

        });
    }

}

