package com.yl.youthlive;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.yl.youthlive.INTERFACE.AllAPIs;
import com.yl.youthlive.walletPOJO.walletBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Beans extends Fragment {
    static String rate;
    ScrollView scrollView;
    ProgressBar progress;
    TextView coins, conversion_rate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.beans_layout, container, false);
        progress = view.findViewById(R.id.progressBar6b);
        coins = view.findViewById(R.id.textView12);
        conversion_rate = view.findViewById(R.id.textView47);
        scrollView = view.findViewById(R.id.scroll);
        scrollView.setVerticalScrollBarEnabled(false);
        scrollView.setHorizontalScrollBarEnabled(false);

        conversion_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();

                TextView mTitle = new TextView(getContext());
                mTitle.setText("Tips" + "\n");
                mTitle.setTextSize(20);
                mTitle.setTextColor(getResources().getColor(R.color.colorAccent));
                mTitle.setGravity(Gravity.CENTER);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCustomTitle(mTitle);
                builder.setMessage("Latest rate for Coins to Rewards" + "\n" + "\n" + rate + " Coins = 1 $" + "\n");
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.show();

// Must call show() prior to fetching text view
                TextView messageView = (TextView) dialog.findViewById(android.R.id.message);
                messageView.setGravity(Gravity.CENTER);
                messageView.setTextSize(16);


                messageView.setTextColor(getResources().getColor(R.color.grey));

           /*     try {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Light_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(getContext());
                    }
                    builder.setTitle("Tips")
                            .setMessage("Latest rate for Coins to Rewards"+"\n"+ rate +" Coins = 1 $"+"\n")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                } catch (Exception e) {
                    Log.d("TAG", "Show Dialog: " + e.getMessage());
                }
*/


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
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final AllAPIs cr = retrofit.create(AllAPIs.class);
        Call<walletBean> call = cr.getWalletData(SharePreferenceUtils.getInstance().getString("userId"));

        Log.d("userId", SharePreferenceUtils.getInstance().getString("userId"));

        call.enqueue(new Callback<walletBean>() {
            @Override
            public void onResponse(Call<walletBean> call, Response<walletBean> response) {

                try {
                    if (!response.body().getData().getDiamond().isEmpty()) {
                        //  Toast.makeText(BuyDiamonds.this, "Purchase done", Toast.LENGTH_SHORT).show();
                        coins.setText(response.body().getData().getBeans());
                        rate = response.body().getData().getConversion_rate();
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

