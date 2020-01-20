package android.unitedremote.com.unitedremotemobilechallenge.services;

import android.unitedremote.com.unitedremotemobilechallenge.models.Response;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import io.reactivex.Single;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    @GET("search/repositories")
    Single<Response> getResponse(@QueryMap Map<String, String> options);
}