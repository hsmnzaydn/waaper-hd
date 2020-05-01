package com.basefy.core_network;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Response;
import retrofit2.HttpException;

import static java.net.HttpURLConnection.HTTP_UNAUTHORIZED;


public class CoreNetworkError extends Throwable {

    public static final String DEFAULT_ERROR_MESSAGE = "Bir sorun oluştu lütfen tekrar deneyiniz";
    public static final String NETWORK_ERROR_MESSAGE = "İnternet bağlantınız yok. Lütfen bağlantıyı kontrol edeniz";
    private static final String ERROR_MESSAGE_HEADER = "Error-Message";
    public Throwable error;
    public int code;

    public CoreNetworkError(int code, Throwable e) {
        super(e);
        this.error = e;
        this.code = code;
    }

    public CoreNetworkError(Throwable e) {
        try {
            new CoreNetworkError(((HttpException) e).code(), e);
            this.error = e;
            this.code = ((HttpException) e).code();
        } catch (Exception a) {
            new CoreNetworkError(-1, e);
            this.error = e;
            this.code = -1;
        }
    }

    public String getMessage() {
        return error.getMessage();
    }

    public int getErrorCode() {
        return code;
    }

    public boolean isAuthFailure() {
        return error instanceof HttpException &&
                ((HttpException) error).code() == HTTP_UNAUTHORIZED;
    }

    public boolean isResponseNull() {
        return error instanceof HttpException && ((HttpException) error).response() == null;
    }

    public String getAppErrorMessage() {
        if (this.error instanceof IOException) return NETWORK_ERROR_MESSAGE;
        if (!(this.error instanceof HttpException)) return DEFAULT_ERROR_MESSAGE;
        retrofit2.Response<?> response = ((HttpException) this.error).response();
        if (response != null) {
            String status = getJsonStringFromResponse(response);
            if (!TextUtils.isEmpty(status)) return status;

            Map<String, List<String>> headers = response.headers().toMultimap();
            if (headers.containsKey(ERROR_MESSAGE_HEADER))
                return headers.get(ERROR_MESSAGE_HEADER).get(0);
        }

        return DEFAULT_ERROR_MESSAGE;
    }

    protected String getJsonStringFromResponse(final retrofit2.Response<?> response) {
        try {
            String jsonString = response.errorBody().string();
            Response errorResponse = new Gson().fromJson(jsonString, Response.class);
            return errorResponse.message();
        } catch (Exception e) {
            return null;
        }
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoreNetworkError that = (CoreNetworkError) o;

        return error != null ? error.equals(that.error) : that.error == null;

    }

    @Override
    public int hashCode() {
        return error != null ? error.hashCode() : 0;
    }
}
