package ir.mlcode.latifiarchitecturelibrary.daggers.retrofit;

import android.content.Context;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import ir.mlcode.latifiarchitecturelibrary.daggers.DaggerScope;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
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

    public class EnumRetrofitConverterFactory extends Converter.Factory {
        @Override
        public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            Converter<?, String> converter = null;
            if (type instanceof Class && ((Class<?>)type).isEnum()) {
                converter = value -> EnumUtils.GetSerializedNameValue((Enum) value);
            }
            return converter;
        }
    }


    public static class EnumUtils {
        @Nullable
        static public <E extends Enum<E>> String GetSerializedNameValue(E e) {
            String value = null;
            try {
                value = e.getClass().getField(e.name()).getAnnotation(SerializedName.class).value();
            } catch (NoSuchFieldException exception) {
                exception.printStackTrace();
            }
            return value;
        }
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
                .addConverterFactory(new EnumRetrofitConverterFactory())
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
