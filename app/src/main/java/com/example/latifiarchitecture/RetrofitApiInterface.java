package com.example.latifiarchitecture;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface RetrofitApiInterface {

    String Version = "/api/v1";



    @FormUrlEncoded
    @POST(Version + "/application/hi")
    Call<MR_Hi> getHi
            (
                    @Field("client_id") String client_id,
                    @Field("client_secret") String client_secret,
                    @Field("name") String name
            );

}
