package com.errorstation.sportstv;

/**
 * Created by Rubayet on 19-Dec-16.
 */
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rubayet on 08-Dec-16.
 */

public interface API {

  String baseURL = "http://errorstation.com/sportsapp/api/";
  @GET("getnewsdetails.php")
  Call<NewsModel> getNews();

  class Factory {
    public static API api;

    public static API getInstance() {
      if (api == null) {
        Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseURL)
            .build();
        api = retrofit.create(API.class);
        return api;
      } else {
        return api;
      }
    }
  }
}
