package com.app.youthlive.pl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.youthlive.R;

public class MainVideoActivity extends AppCompatActivity {

    /*public static final String TAG = "MainActivity";
    private static final int MESSAGE_ID_RECONNECTING = 0x01;
    public ProgressBar progressBar;
    //private static final String DEFAULT_TEST_URL = "http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8";
    private static final String DEFAULT_TEST_URL = "rtmp://ec2-13-127-59-58.ap-south-1.compute.amazonaws.com:1935/vod/maharani.mp4";
    private static final String DEFAULT_TEST_URL2 = "rtmp://ec2-13-127-59-58.ap-south-1.compute.amazonaws.com:1935/vod/ilovu.mp4";
    private static final String DEFAULT_TEST_URL3 = "rtmp://ec2-13-127-59-58.ap-south-1.compute.amazonaws.com:1935/vod/VID_20180806_133717.3gp";
    String[] stringlist = new String[10];
    //private static final String DEFAULT_TEST_URL = "http://mobile.xinhuashixun.com/Live/cncHD.m3u8";
    private boolean mIsActivityPaused = true;
    private MediaController mMediaController;
    private PLVideoTextureView mVideoView;
    private Toast mToast = null;
    private String mVideoPath = null;
    protected Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what != MESSAGE_ID_RECONNECTING) {
                return;
            }
            if (mIsActivityPaused || !Utils.isLiveStreamingAvailable()) {
                finish();
                return;
            }
            if (!Utils.isNetworkAvailable(MainVideoActivity.this)) {
                sendReconnectMessage();
                return;
            }
            mVideoView.setVideoPath(mVideoPath);
            mVideoView.start();
        }
    };
    private int mDisplayAspectRatio = PLVideoTextureView.ASPECT_RATIO_PAVED_PARENT;
    private VerticalViewPager mViewPager;
    private RelativeLayout mRoomContainer;
    private PagerAdapter mPagerAdapter;
    private int mCurrentItem;
    private int isLiveStreaming = 1;
    private AVOptions options;
    private FrameLayout mFragmentContainer;
    private ArrayList<String> mVideoUrls = new ArrayList<>();
    private Subscription mSubscription = Subscriptions.empty();
    private FragmentManager mFragmentManager;
    private int mRoomId = -1;
    private RoomFragment mRoomFragment = RoomFragment.newInstance();
    private boolean mInit = false;
    private PLMediaPlayer.OnErrorListener mOnErrorListener = new PLMediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(PLMediaPlayer mp, int errorCode) {
            boolean isNeedReconnect = false;
            switch (errorCode) {
                case PLMediaPlayer.ERROR_CODE_INVALID_URI:
                    showToastTips("Invalid URL !");
                    break;
                case PLMediaPlayer.ERROR_CODE_404_NOT_FOUND:
                    showToastTips("404 resource not found !");
                    break;
                case PLMediaPlayer.ERROR_CODE_CONNECTION_REFUSED:
                    showToastTips("Connection refused !");
                    break;
                case PLMediaPlayer.ERROR_CODE_CONNECTION_TIMEOUT:
                    showToastTips("Connection timeout !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_EMPTY_PLAYLIST:
                    showToastTips("Empty playlist !");
                    break;
                case PLMediaPlayer.ERROR_CODE_STREAM_DISCONNECTED:
                    showToastTips("Stream disconnected !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_IO_ERROR:
                    showToastTips("Network IO Error !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_UNAUTHORIZED:
                    showToastTips("Unauthorized Error !");
                    break;
                case PLMediaPlayer.ERROR_CODE_PREPARE_TIMEOUT:
                    showToastTips("Prepare timeout !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.ERROR_CODE_READ_FRAME_TIMEOUT:
                    showToastTips("Read frame timeout !");
                    isNeedReconnect = true;
                    break;
                case PLMediaPlayer.MEDIA_ERROR_UNKNOWN:
                    break;
                default:
                    showToastTips("unknown error !");
                    break;
            }
            // Todo pls handle the error status here, reconnect or call finish()
            if (isNeedReconnect) {
                sendReconnectMessage();
            } else {
                finish();
            }
            // Return true means the error has been handled
            // If return false, then `onCompletion` will be called
            return true;
        }
    };
    private PLMediaPlayer.OnCompletionListener mOnCompletionListener = new PLMediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(PLMediaPlayer plMediaPlayer) {
            showToastTips("Play Completed !");
            finish();
        }
    };
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        /*mViewPager = (VerticalViewPager) findViewById(R.id.view_pager);
        mRoomContainer = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.view_room_container, null);
        mFragmentContainer = (FrameLayout) mRoomContainer.findViewById(R.id.fragment_container);
        mVideoView = (PLVideoTextureView) mRoomContainer.findViewById(R.id.texture_view);
        mVideoView.setDisplayAspectRatio(mDisplayAspectRatio);


        mVideoPath = DEFAULT_TEST_URL;
        mFragmentManager = getSupportFragmentManager();
        initAVOptions();
        mVideoView.setAVOptions(options);
        mMediaController = new MediaController(this, false, true);
        generateUrls();
        mPagerAdapter = new PagerAdapter();
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e(TAG, "mCurrentId == " + position + ", positionOffset == " + positionOffset +
                        ", positionOffsetPixels == " + positionOffsetPixels);
                mCurrentItem = position;
                mVideoView.setVideoPath(mVideoUrls.get(mCurrentItem));
            }
        });

        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                ViewGroup viewGroup = (ViewGroup) page;
                Log.e(TAG, "page.id == " + page.getId() + ", position == " + position);

                if ((position < 0 && viewGroup.getId() != mCurrentItem)) {
                    View roomContainer = viewGroup.findViewById(R.id.room_container);
                    if (roomContainer != null && roomContainer.getParent() != null && roomContainer.getParent() instanceof ViewGroup) {
                        ((ViewGroup) (roomContainer.getParent())).removeView(roomContainer);
                    }
                }
                // Satisfy this condition, indicating that live video needs to be loaded, as well as the chat room.
                if (viewGroup.getId() == mCurrentItem && position == 0 && mCurrentItem != mRoomId) {
                    if (mRoomContainer.getParent() != null && mRoomContainer.getParent() instanceof ViewGroup) {
                        ((ViewGroup) (mRoomContainer.getParent())).removeView(mRoomContainer);
                    }
                    loadVideoAndChatRoom(viewGroup, mCurrentItem);
                }
            }
        });
        mViewPager.setAdapter(mPagerAdapter);*/
    }
/*
    private void generateUrls() {
        for (int i = 0; i < 10; i++) {
            mVideoUrls.add(DEFAULT_TEST_URL);
            mVideoUrls.add(DEFAULT_TEST_URL2);
            mVideoUrls.add(DEFAULT_TEST_URL3);

        }
    }

    private void initAVOptions() {
        options = new AVOptions();
        // the unit of timeout is ms
        options.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        options.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000);
        // Some optimization with buffering mechanism when be set to 1
        options.setInteger(AVOptions.KEY_LIVE_STREAMING, isLiveStreaming);
        options.setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 1);
        // 1 -> hw codec enable, 0 -> disable [recommended]
        int codec = 0;
        options.setInteger(AVOptions.KEY_MEDIACODEC, codec);
        // whether start play automatically after prepared, default value is 1
        options.setInteger(AVOptions.KEY_START_ON_PREPARED, 0);
    }

    *//**
     * @param viewGroup
     * @param currentItem
     *//*
    private void loadVideoAndChatRoom(ViewGroup viewGroup, int currentItem) {
//        mSubscription = AppObservable.bindActivity(this, ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).
        //The chat room's fragment is only loaded once and reused later.
        if (!mInit) {
            mFragmentManager.beginTransaction().add(mFragmentContainer.getId(), mRoomFragment).commitAllowingStateLoss();
            mInit = true;
        }
        loadVideo(currentItem);
        viewGroup.addView(mRoomContainer);
        mRoomId = currentItem;
    }

    private void loadVideo(int position) {
        mVideoView.setMediaController(mMediaController);
        mVideoView.setOnCompletionListener(mOnCompletionListener);
        mVideoView.setOnErrorListener(mOnErrorListener);
        mVideoView.setVideoPath(mVideoUrls.get(position));
        mVideoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mToast = null;
        mVideoView.pause();
        mIsActivityPaused = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActivityPaused = false;
        mVideoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVideoView.stopPlayback();
        mSubscription.unsubscribe();
    }

    private void showToastTips(final String tips) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(MainVideoActivity.this, tips, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }

    private void sendReconnectMessage() {
        showToastTips("Reconnecting...");
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_ID_RECONNECTING), 500);
    }

    class PagerAdapter extends android.support.v4.view.PagerAdapter {

        @Override
        public int getCount() {
            return mVideoUrls.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = LayoutInflater.from(container.getContext()).inflate(R.layout.view_room_item, null);
            view.setId(position);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(container.findViewById(position));
        }
    }*/
}
