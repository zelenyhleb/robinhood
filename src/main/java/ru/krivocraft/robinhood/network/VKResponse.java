package ru.krivocraft.robinhood.network;

import com.google.gson.annotations.SerializedName;

public class VKResponse<T> {

    @SerializedName("response")
    private final T response;

    public VKResponse(T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }

}
