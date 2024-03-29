package com.app.youthlive.INTERFACE;


import com.app.youthlive.ExchangeDiamondPOJO.ExchangeBean;
import com.app.youthlive.GetRankingPOJO.RankingBean;
import com.app.youthlive.PhoneupdateminiPOJO;
import com.app.youthlive.TotalbroadcastPOJO;
import com.app.youthlive.acceptRejectPOJO.acceptRejectBean;
import com.app.youthlive.addCareerPOJO.addCareerBean;
import com.app.youthlive.addEducationPOJO.addEducationBean;
import com.app.youthlive.addVideoPOJO.addVideoBean;
import com.app.youthlive.addWalletPOJO.addWalletBean;
import com.app.youthlive.allMessagePOJO.allMessageBean;
import com.app.youthlive.buydiamondPOJO.Data;
import com.app.youthlive.checkStatusPOJO.checkStatusBean;
import com.app.youthlive.checkinPOJO.CheckinPOJO;
import com.app.youthlive.checkinPostPOJO.CheckinPostPOJO;
import com.app.youthlive.commentPOJO.commentBean;
import com.app.youthlive.deleteCareerPOJO.deleteCareerBean;
import com.app.youthlive.deleteVLOGPOJO.deleteVLOGBean;
import com.app.youthlive.diamondpurchasehistoryPOJO.DiamondpurchaselistPOJO;
import com.app.youthlive.dummyPOJO.dummyBean;
import com.app.youthlive.editCareerPOJO.editCareerBean;
import com.app.youthlive.editEducationPOJO.editEducationBean;
import com.app.youthlive.endLivePOJO.endLiveBean;
import com.app.youthlive.fan_listPOJO.FanListPOJO;
import com.app.youthlive.feedBackPOJO.feedBackBean;
import com.app.youthlive.followListPOJO.followListBean;
import com.app.youthlive.followPOJO.followBean;
import com.app.youthlive.forgotpasswordPOJO.ForgotPassword;
import com.app.youthlive.friendListPOJO.FriendListPOJO;
import com.app.youthlive.getConnectionPOJO.getConnectionBean;
import com.app.youthlive.getIpdatedPOJO.getUpdatedBean;
import com.app.youthlive.getLivePOJO.getLiveBean;
import com.app.youthlive.giftBeanss;
import com.app.youthlive.giftHistoryPOJO.giftHistoryBean;
import com.app.youthlive.giftPOJO.giftBean;
import com.app.youthlive.goLivePOJO.goLiveBean;
import com.app.youthlive.liveBean;
import com.app.youthlive.liveCommentPOJO.liveCommentBean;
import com.app.youthlive.liveLikePOJO.liveLikeBean;
import com.app.youthlive.login2POJO.login2Bean;
import com.app.youthlive.loginResponsePOJO.loginResponseBean;
import com.app.youthlive.newpasswordPOJO.NewPassword;
import com.app.youthlive.otpPOJO.otpBean;
import com.app.youthlive.redeemHistoryPOJO.redeemHistoryBean;
import com.app.youthlive.reportPOJO.reportBean;
import com.app.youthlive.requestConnectionPOJO.requestConnectionBean;
import com.app.youthlive.searchlistPOJO.SearchListPOJO;
import com.app.youthlive.sendGiftPOJO.sendGiftBean;
import com.app.youthlive.sendMessagePOJO.sendMessageBean;
import com.app.youthlive.sharePOJO.shareBean;
import com.app.youthlive.singleMessagePOJO.singleMessageBean;
import com.app.youthlive.singleStreamBean;
import com.app.youthlive.singleVideoPOJO.singleVideoBean;
import com.app.youthlive.socialPOJO.socialBean;
import com.app.youthlive.startStreamPOJO.startStreamBean;
import com.app.youthlive.streamPOJO.streamBean;
import com.app.youthlive.streamResponsePOJO.streamResponseBean;
import com.app.youthlive.timelinePOJO.timelineBean;
import com.app.youthlive.timelineProfilePOJO.timelineProfileBean;
import com.app.youthlive.updatePOJO.updateBean;
import com.app.youthlive.updateProfilePOJO.updateProfileBean;
import com.app.youthlive.updatephonePOJO.UpdatephonePOJO;
import com.app.youthlive.vlogListPOJO.vlogListBean;
import com.app.youthlive.vlogListPopularPOJO.vlogListPopularBean;
import com.app.youthlive.vlogsearchListPOJO.vlogsearchListBean;
import com.app.youthlive.walletPOJO.walletBean;
import com.app.youthlive.wowzaAPIPOJO.wowzaAPIBean;
import com.app.youthlive.wowzaLiveStreamsPOJO.getWowzaStreamBean;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface AllAPIs {
    @Multipart
    @POST("api/sign_up.php")
    Call<loginResponseBean> signUp(
            @Part("phone") String phone
    );

    // get checkin data
    @Multipart
    @POST("api/checkin/getcheckin.php")
    Call<CheckinPOJO> getcheckin(
            @Part("userId") String userId,
            @Part("day") String day,
            @Part("month") String month
    );

    // post checkin data
    @Multipart
    @POST("api/checkin/postcheckin.php")
    Call<CheckinPostPOJO> postcheckin(
            @Part("userId") Integer userId,
            @Part("day") Integer day,
            @Part("month") Integer month,
            @Part("broadcast_duration") Long broadcast_duration
    );

    // get checkin data
    @Multipart
    @POST("api/checkin/monthlycheckin.php")
    Call<TotalbroadcastPOJO> totalbroadcast(
            @Part("userId") String userId,
            @Part("month") String month
    );

    // get diamond purchase history
    @Multipart
    @POST("api/getdiamondpurchase.php")
    Call<DiamondpurchaselistPOJO> getdiamondpurchasehistory(
            @Part("userId") Integer userId

    );

    @Multipart
    @POST("api/getGiftsHistory.php")
    Call<giftHistoryBean> getgiftshistory(
            @Part("userId") Integer userId

    );

    @Multipart
    @POST("api/getRedeemHistory.php")
    Call<redeemHistoryBean> getRedeemHistory(
            @Part("userId") Integer userId

    );

    // post diamond purchase data
    @Multipart
    @POST("api/user_add_diamonds.php")
    Call<Data> postdiamondpurchase(
            @Part("userId") Integer userId,
            @Part("productId") String productId,
            @Part("orderId") String orderId,
            @Part("fullorderinfo") String fullorderinfo

    );

    @Multipart
    @POST("api/get_wallet.php")
    Call<walletBean> getWalletData(
            @Part("userId") String userId
    );

    @Multipart
    @POST("api/socialsign_up.php")
    Call<socialBean> socialSignIn(
            @Part("pid") String pid,
            @Part("email") String email,
            @Part("keey") String keey
    );

    @Multipart
    @POST("api/resend_code.php")
    Call<loginResponseBean> resend(
            @Part("phone") String phone
    );

    @Multipart
    @POST("api/exchange_beans_diamond.php")
    Call<loginResponseBean> exchangeBeansToDiamond(
            @Part("userId") String userId,
            @Part("beans") String beans,
            @Part("diamond") String diamond
    );

    @Multipart
    @POST("api/redeemCoins.php")
    Call<loginResponseBean> redeemCoins(
            @Part("userId") String userId,
            @Part("beans") String beans,
            @Part("diamond") String diamond
    );

    @Multipart
    @POST("api/follow_unfollow.php")
    Call<followBean> follow(
            @Part("userId") String userId,
            @Part("friendId") String friendId
    );

    @Multipart
    @POST("api/user_add_beans.php")
    Call<addWalletBean> addBeans(
            @Part("userId") String userId,
            @Part("amount") String amount
    );

    @Multipart
    @POST("api/user_feedback.php")
    Call<feedBackBean> feedback(
            @Part("userId") String userId,
            @Part("message") String message
    );

    @Multipart
    @POST("api/user_report.php")
    Call<feedBackBean> report(
            @Part("userId") String userId,
            @Part("message") String message,
            @Part("viewId") String viewId
    );

    @Multipart
    @POST("api/follow_list.php")
    Call<followListBean> followList(
            @Part("userId") String userId
    );

    @Multipart
    @POST("api/all_message.php")
    Call<allMessageBean> allMessageList(
            @Part("userId") String userId
    );


    @Multipart
    @POST("api/update_user_info.php")
    Call<loginResponseBean> addUserData(
            @Part MultipartBody.Part file,
            @Part("userName") String userName,
            @Part("gender") String gender,
            @Part("birthday") String birthday,
            @Part("bio") String bio,
            @Part("userId") String userId
    );

    @Multipart
    @POST("api/update_user_info.php")
    Call<updateBean> updateUserData(
            @Part("userName") String userName,
            @Part("gender") String gender,
            @Part("birthday") String birthday,
            @Part("bio") String bio,
            @Part("userId") String userId
    );

    @Multipart
    @POST("api/add_career.php")
    Call<addCareerBean> addCareer(
            @Part("position") String position,
            @Part("company") String company,
            @Part("from") String from,
            @Part("to") String to,
            @Part("userId") String userId
    );

    @Multipart
    @POST("api/finishConnection.php")
    Call<String> endConnection(
            @Part("requestId") String position
    );

    @Multipart
    @POST("api/edit_career.php")
    Call<editCareerBean> editCareer(
            @Part("userId") String userId,
            @Part("position") String position,
            @Part("company") String company,
            @Part("from") String from,
            @Part("to") String to,
            @Part("careerId") String careerId
    );

    @Multipart
    @POST("api/varify_code.php")
    Call<otpBean> verify(
            @Part("phone") String phone,
            @Part("code") String code
    );

    @Multipart
    @POST("api/delete_career.php")
    Call<deleteCareerBean> deleteCareer(
            @Part("userId") String userId,
            @Part("careerId") String code
    );

    @Multipart
    @POST("api/video_share.php")
    Call<shareBean> share(
            @Part("userId") String userId,
            @Part("videoId") String videoId
    );

    @Multipart
    @POST("api/add_user_image.php")
    Call<updateProfileBean> updateProfile(
            @Part("userId") String userId,
            @Part MultipartBody.Part file
    );

    @Multipart
    @POST("api/create_password.php")
    Call<otpBean> createPassword(
            @Part("userId") String userId,
            @Part("password") String password
    );

    @Multipart
    @POST("api/update_phone.php")
    Call<loginResponseBean> updatePhone(
            @Part("userId") String userId,
            @Part("phone") String phone
    );

    @Multipart
    @POST("api/update_phonemini.php")
    Call<PhoneupdateminiPOJO> updatePhonemini(
            @Part("userId") String userId,
            @Part("phone") String phone
    );

    @Multipart
    @POST("api/update_phoneno.php")
    Call<UpdatephonePOJO> updatePhoneno(
            @Part("phone") String phone
    );

    @Multipart
    @POST("api/single_user_message.php")
    Call<singleMessageBean> singleChatList(
            @Part("userId") String userId,
            @Part("friendId") String friendId,
            @Part("chatId") String chatId
    );

    @Multipart
    @POST("api/send_message.php")
    Call<sendMessageBean> sendMessage(
            @Part("userId") String userId,
            @Part("friendId") String friendId,
            @Part("message") String message
    );

    @Multipart
    @POST("api/go_live.php")
    Call<goLiveBean> goLive(
            @Part("userId") String userId,
            @Part("thirdPartyKey") String key,
            @Part("liveTag") String tag
    );

    @GET("api/getDummyUsers.php")
    Call<dummyBean> getDummy();

    @Multipart
    @POST("api/go_live_end.php")
    Call<endLiveBean> endLive(
            @Part("userId") String userId,
            @Part("liveId") String liveId
    );

    @Multipart
    @POST("api/syncLive.php")
    Call<endLiveBean> syncLive(
            @Part("duration") String duration,
            @Part("liveId") String liveId
    );

    @Multipart
    @POST("api/add_education.php")
    Call<addEducationBean> addEducation(
            @Part("userId") String userId,
            @Part("educationTitle") String title,
            @Part("educationTime") String year
    );

    @Multipart
    @POST("api/edit_education.php")
    Call<editEducationBean> editEducation(
            @Part("userId") String userId,
            @Part("educationId") String educationId,
            @Part("educationTitle") String title,
            @Part("educationTime") String year
    );

    @Multipart
    @POST("api/delete_education.php")
    Call<editEducationBean> deleteEducation(
            @Part("userId") String userId,
            @Part("educationId") String educationId
    );

    @Multipart
    @POST("api/get_profile.php")
    Call<loginResponseBean> getProfile(
            @Part("userId") String userId
    );


    @Multipart
    @POST("api/get_profile_new.php")
    Call<timelineProfileBean> getProfile2(
            @Part("userId") String userId,
            @Part("friendId") String friendId
    );


    @Multipart
    @POST("api/add_cover_image.php")
    Call<loginResponseBean> addCover(
            @Part("userId") String userId,
            @Part("title") String title,
            @Part MultipartBody.Part file
    );

    @Multipart
    @POST("api/mobile_signin.php")
    Call<login2Bean> signIn(
            @Part("phone") String phone,
            @Part("password") String password,
            @Part("keey") String keey
    );

    @Multipart
    @POST("api/user_report.php")
    Call<reportBean> reportUser(
            @Part("userId") String userId,
            @Part("viewId") String viewId,
            @Part("message") String message
    );

    @Multipart
    @POST("api/all_videolist_popular.php")
    Call<vlogListPopularBean> getVlogListpopular(
            @Part("userId") String userId
    );
    @Multipart
    @POST("api/all_live_user.php")
    Call<List<liveBean>> getLives(
            @Part("userId") String userId
    );

    @Multipart
    @POST("api/go_live_users_get.php")
    Call<List<liveBean>> getLives2(
            @Part("userId") String userId
    );

    @GET("api/test_live_users.php")
    Call<List<liveBean>> getLives3();

    @GET("api/get_gifts.php")
    Call<List<giftBeanss>> getGifts();

    @Multipart
    @POST("api/get_timelinelist.php")
    Call<timelineBean> getTimeline(
            @Part("userId") String userId
    );

    @Multipart
    @POST("api/go_live_update.php")
    Call<String> updateLive(
            @Part("liveId") String userId
    );

    @Headers({
            "Accept: application/vnd.bambuser.v1+json",
            "Content-Type: application/json",
            "Authorization: Bearer 3m5chxsssdd7f6d3s7l2pkklx"
    })
    @GET("broadcasts")
    Call<getLiveBean> getLiveList();


    @Headers({
            "Accept: application/json",
    })
    @GET("v2/servers/_defaultServer_/vhosts/_defaultVHost_/applications/instances/_definst_")
    Call<String> getEngineLiveList();


    @Headers({
            "Accept: application/vnd.bambuser.v1+json",
            "Content-Type: application/json",
            "Authorization: Bearer 3m5chxsssdd7f6d3s7l2pkklx"
    })
    @GET("broadcasts/{id}")
    Call<singleStreamBean> getSingleLive(@Path("id") String id);


    @Multipart
    @POST("api/all_videolist.php")
    Call<vlogListBean> getVlogList(
            @Part("userId") String userId
    );

    @Multipart
    @GET("api//vlog_video_search.php?")
    Call<vlogsearchListBean> getVlogSearchList(
            @Part("userId") String userId,
            @Part("search") String search
    );


    @Multipart
    @POST("api/all_gift.php")
    Call<giftBean> getGiftData(
            @Part("userId") String userId
    );


    @Multipart
    @POST("api/get_video.php")
    Call<vlogListBean> getVlog(
            @Part("userId") String userId
    );

    @Multipart
    @POST("api/get_single_video.php")
    Call<singleVideoBean> getsingleVideo(
            @Part("userId") String userId,
            @Part("videoId") String videoId
    );

    @Multipart
    @POST("api/delete_video.php")
    Call<deleteVLOGBean> removeVideo(
            @Part("userId") String userId,
            @Part("videoId") String videoId
    );

    @Multipart
    @POST("api/getUpdatedData.php")
    Call<getUpdatedBean> getUpdatedData(
            @Part("userId") String userId,
            @Part("liveId") String liveId,
            @Part("keey") String key
    );

    @Multipart
    @POST("api/player_updated_data.php")
    Call<getUpdatedBean> getPlayerUpdatedData(
            @Part("userId") String userId,
            @Part("liveId") String liveId,
            @Part("keey") String key
    );

    @Multipart
    @POST("api/dummy_updated_data.php")
    Call<getUpdatedBean> getDummyUpdatedData(
            @Part("userId") String userId,
            @Part("liveId") String liveId,
            @Part("keey") String key
    );

    @Multipart
    @POST("api/exitPlayer.php")
    Call<String> exitPlayer(
            @Part("userId") String userId,
            @Part("liveId") String liveId
    );

    @Multipart
    @POST("api/go_live_like.php")
    Call<liveLikeBean> likeLive(
            @Part("userId") String userId,
            @Part("liveId") String liveId
    );


    @Multipart
    @POST("api/video_likes.php")
    Call<singleVideoBean> likeVideo(
            @Part("userId") String userId,
            @Part("videoId") String videoId
    );

    @Multipart
    @POST("api/video_comment.php")
    Call<commentBean> comment(
            @Part("userId") String userId,
            @Part("videoId") String videoId,
            @Part("comment") String comment
    );

    @Multipart
    @POST("api/add_live_comment.php")
    Call<liveCommentBean> commentLive(
            @Part("userId") String userId,
            @Part("videoId") String videoId,
            @Part("comment") String comment,
            @Part("type") String type
    );

    @Multipart
    @POST("api/send_live_gift.php")
    Call<sendGiftBean> sendGift(
            @Part("userId") String userId,
            @Part("videoId") String videoId,
            @Part("liveUserId") String liveId,
            @Part("giftId") String giftId,
            @Part("qty") String qty,
            @Part("diamond") String comment
    );

    @Multipart
    @POST("api/add_video.php")
    Call<addVideoBean> addVideo(
            @Part("userId") String userId,
            @Part("caption") String caption,
            @Part("tag") String tag,
            @Part MultipartBody.Part file,
            @Part MultipartBody.Part image
    );

    @Multipart
    @POST("api/add_screenshot.php")
    Call<String> addScreenshot(
            @Part("userId") String userId,
            @Part("liveId") String liveId,
            @Part MultipartBody.Part image
    );

    @Multipart
    @POST("api/get_ranking.php")
    Call<RankingBean> ranking(
            @Part("userId") String userId,
            @Part("type") String caption
    );

    @Multipart
    @POST("api/get_connection.php")
    Call<getConnectionBean> getConnection(
            @Part("userId") String userId,
            @Part("liveId") String liveId
    );


    @Multipart
    @POST("api/checkRequest.php")
    Call<checkStatusBean> checkStatus(
            @Part("userId") String userId,
            @Part("liveId") String liveId
    );


    @Multipart
    @POST("api/accept_reject_connection.php")
    Call<acceptRejectBean> acceptReject(
            @Part("requestId") String requestId,
            @Part("url") String url,
            @Part("status") String status,
            @Part("userId") String uid
    );

    @Multipart
    @POST("api/accept_from_broadcaster.php")
    Call<acceptRejectBean> acceptRejectBroadcaster(
            @Part("requestId") String requestId,
            @Part("url") String url,
            @Part("status") String status,
            @Part("userId") String uid
    );

    @Multipart
    @POST("api/accept_reject_connection3.php")
    Call<acceptRejectBean> acceptReject3(
            @Part("requestId") String requestId,
            @Part("url") String url,
            @Part("status") String status,
            @Part("userId") String uid
    );

    @Multipart
    @POST("api/accept_reject_connection.php")
    Call<String> acceptReject2(
            @Part("requestId") String requestId,
            @Part("url") String url,
            @Part("status") String status,
            @Part("userId") String uid
    );

    @Multipart
    @POST("api/request_connection.php")
    Call<requestConnectionBean> requestConnection(
            @Part("liveId") String requestId,
            @Part("liveUserId") String url,
            @Part("receiverId") String status
    );

    @Multipart
    @POST("api/request_from_player.php")
    Call<requestConnectionBean> requestConnectionFromPlayer(
            @Part("liveId") String requestId,
            @Part("liveUserId") String url,
            @Part("receiverId") String status
    );

    @Multipart
    @POST("api/all_wowza_user.php")
    Call<List<wowzaAPIBean>> getAllStreams2(
            @Part("userId") String userId
    );

    @Multipart
    @POST("api/exchange_beans_diamond.php")
    Call<ExchangeBean> exchange(
            @Part("userId") String userId,
            @Part("beans") String code,
            @Part("diamond") String phone
    );

    @Multipart
    @POST("api/follow_unfollow_check.php")
    Call<followBean> followcheck(
            @Part("userId") String userId,
            @Part("friendId") String friendId
    );


    @Multipart
    @POST("api/follow_live_user.php")
    Call<followBean> followLiveUser(
            @Part("userId") String userId,
            @Part("friendId") String friendId,
            @Part("liveId") String liveId
    );


    @Multipart
    @POST("api/fan_list.php")
    Call<FanListPOJO> fanList(
            @Part("userId") String userId
    );

    @Multipart
    @POST("api/friend_list.php")
    Call<FriendListPOJO> friendList(
            @Part("userId") String userId
    );

    @Multipart
    @POST("api/search.php")
    Call<SearchListPOJO> getSearchList(
            @Part("search") String search
    );

    @Multipart
    @POST("api/umaylike.php")
    Call<SearchListPOJO> getumaylikeList(
            @Part("search") String search
    );

    @Multipart
    @POST("api/add_live_comment.php")
    Call<liveCommentBean> commentLive(
            @Part("userId") String userId,
            @Part("videoId") String videoId,
            @Part("comment") String comment
    );

    ///  user forgot password request
    @Multipart
    @POST("api/password_reset/user_forgot_password.php")
    Call<ForgotPassword> UserForgotPassword(
            @Part("phone") String phone
    );

    ///  create new password request
    @Multipart
    @POST("api/password_reset/new_password.php")
    Call<NewPassword> UserNewPassword(
            @Part("userid") String userid,
            @Part("password") String password
    );

    @Multipart
    @POST("api/mute_user.php")
    Call<String> mute(
            @Part("name") String name,
            @Part("videoId") String liveId,
            @Part("userId") String userId
    );

    //@Multipart
    @Headers({"Content-Type: application/json", "wsc-api-key: dicLNKTWDjmx14cgVpw1sLVuQxBQVbOVE0tpkf3y6VijiUln4sn3QJ2W5zKr3524", "wsc-access-key: tsaCoQS07GnFTTcel0L3gY59ELa7ouYykKFXLQLBApzjPV7a3IWfKXkYt0e3323e"})
    @POST("api/v1/live_streams")
    Call<streamResponseBean> createStream(
            @Body streamBean body
    );

    //@Multipart
    @Headers({"Content-Type: application/json", "wsc-api-key: dicLNKTWDjmx14cgVpw1sLVuQxBQVbOVE0tpkf3y6VijiUln4sn3QJ2W5zKr3524", "wsc-access-key: tsaCoQS07GnFTTcel0L3gY59ELa7ouYykKFXLQLBApzjPV7a3IWfKXkYt0e3323e"})
    @PUT("api/v1/live_streams/{id}/start")
    Call<startStreamBean> startStream(
            @Path("id") String id
    );

    @Headers({"Content-Type: application/json", "wsc-api-key: dicLNKTWDjmx14cgVpw1sLVuQxBQVbOVE0tpkf3y6VijiUln4sn3QJ2W5zKr3524", "wsc-access-key: tsaCoQS07GnFTTcel0L3gY59ELa7ouYykKFXLQLBApzjPV7a3IWfKXkYt0e3323e"})
    @PUT("api/v1/live_streams/{id}/stop")
    Call<startStreamBean> stopStream(
            @Path("id") String id
    );

    @Headers({"Content-Type: application/json", "wsc-api-key: dicLNKTWDjmx14cgVpw1sLVuQxBQVbOVE0tpkf3y6VijiUln4sn3QJ2W5zKr3524", "wsc-access-key: tsaCoQS07GnFTTcel0L3gY59ELa7ouYykKFXLQLBApzjPV7a3IWfKXkYt0e3323e"})
    @GET("api/v1/live_streams/{id}/state")
    Call<startStreamBean> getState(
            @Path("id") String id
    );

    @Headers({"Content-Type: application/json", "wsc-api-key: dicLNKTWDjmx14cgVpw1sLVuQxBQVbOVE0tpkf3y6VijiUln4sn3QJ2W5zKr3524", "wsc-access-key: tsaCoQS07GnFTTcel0L3gY59ELa7ouYykKFXLQLBApzjPV7a3IWfKXkYt0e3323e"})
    @GET("api/v1/live_streams")
    Call<getWowzaStreamBean> getAllStreams();


}
