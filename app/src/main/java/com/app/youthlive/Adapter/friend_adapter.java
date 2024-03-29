package com.app.youthlive.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.app.youthlive.Activitys.FriendActivity;
import com.app.youthlive.Activitys.MessaageActivity;
import com.app.youthlive.R;
import com.app.youthlive.SharePreferenceUtils;
import com.app.youthlive.TimelineProfile;
import com.app.youthlive.bean;
import com.app.youthlive.followPOJO.followBean;
import com.app.youthlive.friendListPOJO.Datum;
import com.app.youthlive.sendMessagePOJO.sendMessageBean;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class friend_adapter extends RecyclerView.Adapter<friend_adapter.friendadapter> {

    Context context;
    List<Datum> list = new ArrayList<>();
    FriendActivity con;


    public friend_adapter(Context context, List<Datum> list) {
        this.list = list;
        this.context = context;
    }


    @Override
    public friendadapter onCreateViewHolder(ViewGroup parent, int viewType) {
        con = (FriendActivity) context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.friend_recycler, parent, false);
        return new friendadapter(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final friendadapter holder, int position) {
        holder.setIsRecyclable(false);
        final Datum item = list.get(position);

        if (!item.getUserImage().isEmpty()) {
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
            ImageLoader loader = ImageLoader.getInstance();
            loader.displayImage(item.getUserImage(), holder.image , options);
        }

        holder.name.setText(item.getUserName());
        holder.youthId.setText("Youthlive Id: " + item.getYouthLiveId());

        holder.relative_following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = item.getUserId();
                Intent intent = new Intent(context, TimelineProfile.class);
                intent.putExtra("userId", id);
                context.startActivity(intent);

            }
        });
        // loading follow unfollow data check
        final bean b = (bean) context.getApplicationContext();

        Call<followBean> call = b.getRetrofit().followcheck(SharePreferenceUtils.getInstance().getString("userId"), item.getUserId());

        call.enqueue(new Callback<followBean>() {
            @Override
            public void onResponse(Call<followBean> call, Response<followBean> response) {

                try {
                    if (!item.getUserId().equals(SharePreferenceUtils.getInstance().getString("userId"))) {
                        // Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if (response.body().getMessage().equals("Following")) {
                            holder.unfollow.setVisibility(View.VISIBLE);
                            holder.unfollow.setText("UNFOLLOW");

                        }
                        if (response.body().getMessage().equals("Not Following")) {
                            holder.unfollow.setVisibility(View.VISIBLE);
                            holder.unfollow.setText("FOLLOW");

                        }
                    } else {
                        holder.followmsgcontainer.setVisibility(View.GONE);
                        holder.friendcard.setCardBackgroundColor(Color.parseColor("#ffef99"));
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    // Toast.makeText(TimelineProfile.this , "Some Error Occurred, Please try again follow" , Toast.LENGTH_SHORT).show();
                }


                con.progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<followBean> call, Throwable t) {

                con.progress.setVisibility(View.GONE);

            }
        });


        //


        holder.message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.send_message_dialog);
                dialog.setCancelable(true);
                dialog.show();

                final EditText comment = (EditText) dialog.findViewById(R.id.message);
                final ProgressBar bar = (ProgressBar) dialog.findViewById(R.id.progress);
                Button submit = (Button) dialog.findViewById(R.id.send);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        bar.setVisibility(View.VISIBLE);

                        String comm = comment.getText().toString();

                        if (comm.length() > 0) {

                            //    progress.setVisibility(View.VISIBLE);

                            bean b = (bean) context.getApplicationContext();



                            Call<sendMessageBean> call = b.getRetrofit().sendMessage(SharePreferenceUtils.getInstance().getString("userId"), item.getUserId(), comm);

                            call.enqueue(new Callback<sendMessageBean>() {
                                @Override
                                public void onResponse(Call<sendMessageBean> call, Response<sendMessageBean> response) {


                                    dialog.dismiss();


                                    Intent intent = new Intent(context, MessaageActivity.class);
                                    context.startActivity(intent);


                                    bar.setVisibility(View.GONE);

                                }

                                @Override
                                public void onFailure(Call<sendMessageBean> call, Throwable t) {

                                    bar.setVisibility(View.GONE);

                                }
                            });

                        }

                    }
                });


            }
        });


        holder.unfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                con.progress.setVisibility(View.VISIBLE);

                final bean b = (bean) context.getApplicationContext();


                Call<followBean> call = b.getRetrofit().follow(SharePreferenceUtils.getInstance().getString("userId"), item.getUserId());

                call.enqueue(new Callback<followBean>() {
                    @Override
                    public void onResponse(Call<followBean> call, Response<followBean> response) {

                        // Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        con.progress.setVisibility(View.GONE);

                        con.loadData();

                    }

                    @Override
                    public void onFailure(Call<followBean> call, Throwable t) {

                        con.progress.setVisibility(View.GONE);
                        Log.d("asdad", t.toString());

                    }
                });


            }
        });

    }


    public void setGridData(List<Datum> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class friendadapter extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView name, youthId, beans;
        Button unfollow;
        Button message;
        RelativeLayout relative_following;
        LinearLayout followmsgcontainer;
        CardView friendcard;


        public friendadapter(View itemView) {
            super(itemView);

            image = (CircleImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            youthId = (TextView) itemView.findViewById(R.id.yid);
            beans = (TextView) itemView.findViewById(R.id.beans);
            message = (Button) itemView.findViewById(R.id.message);

            unfollow = (Button) itemView.findViewById(R.id.unfollow);
            relative_following = itemView.findViewById(R.id.relative_follow);
            followmsgcontainer = itemView.findViewById(R.id.followmsgcontainer);
            friendcard = itemView.findViewById(R.id.friendscard);

        }
    }
}
