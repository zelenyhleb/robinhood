package ru.krivocraft.robinhood.network;

import com.google.gson.annotations.SerializedName;
import ru.krivocraft.robinhood.model.TokenData;

public class UserInfoRequestBody {
    @SerializedName("v")
    private final String version;

    @SerializedName("https")
    private final String https;

    @SerializedName("androidVersion")
    private final String androidVersion;

    @SerializedName("androidModel")
    private final String androidModel;

    @SerializedName("info_fields")
    private final String infoFields;

    @SerializedName("lang")
    private final String language;

    @SerializedName("func_v")
    private final String funcV;

    @SerializedName("androidManufacturer")
    private final String androidManufacturer;

    @SerializedName("fields")
    private final String fields;

    @SerializedName("access_token")
    private final String accessToken;

    @SerializedName("device_id")
    private final String deviceId;


    public UserInfoRequestBody(TokenData token) {
        this.version = "5.93";
        this.https = "1";
        this.androidVersion = "19";
        this.androidModel = "Android SDK built for x86";
        this.infoFields = "audio_ads,audio_background_limit,country,discover_design_version,discover_preload,discover_preload_not_seen,gif_autoplay,https_required,inline_comments,intro,lang,menu_intro,money_clubs_p2p,money_p2p,money_p2p_params,music_intro,audio_restrictions,profiler_settings,raise_to_record_enabled,stories,masks,subscriptions,support_url,video_autoplay,video_player,vklive_app,community_comments,webview_authorization,story_replies,animated_stickers,community_stories,live_section,playlists_download,calls,security_issue,eu_user,wallet,vkui_community_create,vkui_profile_edit,vkui_community_manage,vk_apps,stories_photo_duration,stories_reposts,live_streaming,live_masks,camera_pingpong,role,video_discover";
        this.language = "en";
        this.funcV = "11";
        this.androidManufacturer = "unknown";
        this.fields = "photo_100,photo_50,exports,country,sex,status,bdate,first_name_gen,last_name_gen,verified,trending";
        this.deviceId = token.getDeviceId();
        this.accessToken = token.getAccessToken();
    }

    public String getVersion() {
        return version;
    }

    public String getHttps() {
        return https;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public String getAndroidModel() {
        return androidModel;
    }

    public String getInfoFields() {
        return infoFields;
    }

    public String getLanguage() {
        return language;
    }

    public String getFuncV() {
        return funcV;
    }

    public String getAndroidManufacturer() {
        return androidManufacturer;
    }

    public String getFields() {
        return fields;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
