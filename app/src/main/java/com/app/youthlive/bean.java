package com.app.youthlive;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.app.youthlive.INTERFACE.AllAPIs;
import com.app.youthlive.internetConnectivity.ConnectivityReceiver;
import com.app.youthlive.vlogListPOJO.Datum;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class bean extends Application {

    private static AllAPIs cr;
    public static ArrayList<String> mylist;
    private static Context context;
    private static bean mInstance;
    public String BASE_URL = "http://ec2-13-126-246-74.ap-south-1.compute.amazonaws.com/softcode/";
    //public String BASE_URL = "http://youthlive.in/softcode/";
    public String userId = "";
    String userName = "";
    String userImage = "";
    String liveId = "";

    List<Datum> vlist = new ArrayList<>();
    List<com.app.youthlive.vlogListPopularPOJO.Datum> plist = new ArrayList<>();
    public bean() {
        mylist = new ArrayList<>();
    }
    public static Context getContext() {
        return context;
    }

    public static synchronized bean getInstance() {
        return mInstance;
    }


    public AllAPIs getRetrofit()
    {
        return cr;
    }



    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        mInstance = this;
        context = getApplicationContext();
        String TAG = "myApp";
        Log.e(TAG, "  myapp stater");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cr = retrofit.create(AllAPIs.class);

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));

        FontsOverride.setDefaultFont(this, "MONOSPACE", "calibri.ttf");

    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }


    public String names[] = {
            "star",
            "teddy",
            "heart",
            "gun",
            "wings",
            "paisa",
            //"coins",
            "teddy",
            "chocolates",
            "money",
            //"clap",
            "poison",
            "bike",
            "rose",
            "car",
            "rabbit",
            "bird",
            "rose",
            "genie",
            "dancing girl",
            "diamond",
            "superbee",
            "hug",
            "heart beat",
            "diamond ring",
            "crown",
            "golden egg",
            "love",
            "drinks",
            "cupid love",
            "rabbits",
            "magic mirror",
            "treasure",
            "loving heart",
            "ring",
            "kiss",
            "fire",
            "head phone",
            "weapon",
            "money bag"
    };


    public Integer gifts[] = new Integer[]
            {
                    R.drawable.g4,
                    R.drawable.g8,
                    R.drawable.g52,
                    R.drawable.g20,
                    R.drawable.g33,
                    R.drawable.g44,
                    //R.drawable.g52,
                    R.drawable.g72,
                    R.drawable.g112,
                    R.drawable.g153,
                    //R.drawable.g172,
                    R.drawable.g180,
                    R.drawable.g192,
                    R.drawable.g200,
                    R.drawable.g212,
                    R.drawable.g228,
                    R.drawable.g240,
                    R.drawable.g252,
                    R.drawable.g264,
                    R.drawable.g280,
                    R.drawable.g300,
                    R.drawable.g312,
                    R.drawable.g352,
                    R.drawable.g380,
                    R.drawable.g400,
                    R.drawable.g430,
                    R.drawable.g452,
                    R.drawable.g500,
                    R.drawable.g550,
                    R.drawable.g580,
                    R.drawable.g612,
                    R.drawable.g650,
                    R.drawable.g680,
                    R.drawable.g700,
                    R.drawable.g800,
                    R.drawable.g900,
                    R.drawable.g1000,
                    R.drawable.g1100,
                    R.drawable.g1200,
                    R.drawable.g1501
            };


    public String diamonds[] = {
            "4",
            "8",
            "12",
            "20",
            "32",
            "44",
            //"52",
            "72",
            "112",
            "152",
            //"172",
            "180",
            "192",
            "200",
            "212",
            "228",
            "240",
            "252",
            "264",
            "280",
            "300",
            "312",
            "352",
            "380",
            "400",
            "432",
            "452",
            "500",
            "552",
            "580",
            "612",
            "652",
            "680",
            "700",
            "800",
            "900",
            "1000",
            "1100",
            "1200",
            "1500"
    };

}
