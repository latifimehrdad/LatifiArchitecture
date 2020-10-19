package ir.mlcode.latifiarchitecturelibrary.daggers.retrofit;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import ir.mlcode.latifiarchitecturelibrary.daggers.DaggerScope;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {


    private Context context;
    private String Host;
    private Gson gson;

    public RetrofitModule(Context context, String Host, Gson gson) {
        this.context = context;
        this.Host = Host;
        this.gson = gson;
    }


    @Provides
    @DaggerScope
    public retrofit2.Retrofit getRetrofit(OkHttpClient okHttpClient) {

/*        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
//                .setDateFormat("E, dd MMM yyyy HH:mm:ss")
                .create();*/

        return new retrofit2.Retrofit.Builder()
                .baseUrl(Host)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Provides
    @DaggerScope
    public OkHttpClient getOkHttpClient(Cache cache, HttpLoggingInterceptor interceptor) {



        return new OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)

                .build();
    }

    @Provides
    @DaggerScope
    public HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @DaggerScope
    public Cache getCache(File file) {
        return new Cache(file, 5 * 1000 * 1000);
    }

    @Provides
    @DaggerScope
    public File getFile() {
        return new File(context.getCacheDir(), "Okhttp_cache");
    }
}
