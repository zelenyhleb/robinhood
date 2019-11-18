package ru.krivocraft.robinhood.network;

public class VkApiService extends NetworkService {

    private final VkApi vkApi;

    public VkApiService() {
        super("https://api.vk.com");
        vkApi = getRetrofit().create(VkApi.class);
    }

    public VkApi getVkApi() {
        return vkApi;
    }
}
