package android.unitedremote.com.unitedremotemobilechallenge.services;

import android.unitedremote.com.unitedremotemobilechallenge.models.Repo;

import io.reactivex.Single;

import retrofit2.http.GET;

public interface ApiService {
    @GET("repos/tipsy/profile-summary-for-github")
    Single<Repo> getListRepos();
}