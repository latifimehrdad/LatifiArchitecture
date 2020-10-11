package ir.mlcode.latifiarchitecturelibrary.application;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.memory.BaseMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.lang.ref.Reference;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import ir.mlcode.latifiarchitecturelibrary.R;
import ir.mlcode.latifiarchitecturelibrary.daggers.imageloader.DaggerImageLoaderComponent;
import ir.mlcode.latifiarchitecturelibrary.daggers.imageloader.ImageLoaderComponent;
import ir.mlcode.latifiarchitecturelibrary.daggers.imageloader.ImageLoaderModule;
import ir.mlcode.latifiarchitecturelibrary.daggers.retrofit.DaggerRetrofitComponent;
import ir.mlcode.latifiarchitecturelibrary.daggers.retrofit.RetrofitComponent;
import ir.mlcode.latifiarchitecturelibrary.daggers.retrofit.RetrofitModule;
import ir.mlcode.latifiarchitecturelibrary.daggers.utility.DaggerUtilityComponent;
import ir.mlcode.latifiarchitecturelibrary.daggers.utility.UtilityComponent;
import ir.mlcode.latifiarchitecturelibrary.daggers.utility.UtilityModule;

public class APP_Latifi extends MultiDexApplication {

    private RetrofitComponent retrofitComponent;
    private UtilityComponent utilityComponent;
    private ImageLoaderComponent imageLoaderComponent;
    private Context context;
    public String Host;


    //______________________________________________________________________________________________ onCreate
    @Override
    public void onCreate() {
        super.onCreate();
    }
    //______________________________________________________________________________________________ onCreate


    //______________________________________________________________________________________________ getApplicationWMS
    public static APP_Latifi getAPP_Latifi(Context context) {
        return (APP_Latifi) context.getApplicationContext();
    }
    //______________________________________________________________________________________________ getApplicationWMS


    //______________________________________________________________________________________________ configurationRetrofitComponent
    private void configurationRetrofitComponent() {
        retrofitComponent = DaggerRetrofitComponent
                .builder()
                .retrofitModule(new RetrofitModule(getContext(), getHost()))
                .build();
    }
    //______________________________________________________________________________________________ configurationRetrofitComponent


    //______________________________________________________________________________________________ configurationImageLoader()
    private void configurationImageLoader() {

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .resetViewBeforeLoading(true)
                .displayer(new FadeInBitmapDisplayer(100))
                .build();


        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new BaseMemoryCache() {
                    @Override
                    protected Reference<Bitmap> createReference(Bitmap value) {
                        return null;
                    }
                })
                .threadPoolSize(3)
                .diskCacheSize(100 * 1024 * 1024)
                .build();
        ImageLoader.getInstance().init(config);

        imageLoaderComponent = DaggerImageLoaderComponent.builder().imageLoaderModule(new ImageLoaderModule()).build();
    }
    //______________________________________________________________________________________________ configurationImageLoader()


    //______________________________________________________________________________________________ configurationUtilityComponent
    private void configurationUtilityComponent() {
        utilityComponent = DaggerUtilityComponent
                .builder()
                .utilityModule(new UtilityModule())
                .build();
    }
    //______________________________________________________________________________________________ configurationUtilityComponent


    //______________________________________________________________________________________________ getContext
    public Context getContext() {
        return context;
    }
    //______________________________________________________________________________________________ getContext


    //______________________________________________________________________________________________ setContext
    public void setContext(Context context) {
        this.context = context;
        Fresco.initialize(context);
        configurationUtilityComponent();
        configurationImageLoader();
        configurationCalligraphy();
    }
    //______________________________________________________________________________________________ setContext


    //______________________________________________________________________________________________ configurationCalligraphy
    private void configurationCalligraphy() {
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("font/vazir.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

    }
    //______________________________________________________________________________________________ configurationCalligraphy


    //______________________________________________________________________________________________ getHost
    public String getHost() {
        return Host;
    }
    //______________________________________________________________________________________________ getHost


    //______________________________________________________________________________________________ setHost
    public void setHost(String host) {
        Host = host;
        configurationRetrofitComponent();
    }
    //______________________________________________________________________________________________ setHost


    //______________________________________________________________________________________________ getRetrofitComponent
    public RetrofitComponent getRetrofitComponent() {
        return retrofitComponent;
    }
    //______________________________________________________________________________________________ getRetrofitComponent


    //______________________________________________________________________________________________ getUtilityComponent
    public UtilityComponent getUtilityComponent() {
        return utilityComponent;
    }
    //______________________________________________________________________________________________ getUtilityComponent


    //______________________________________________________________________________________________ getImageLoaderComponent
    public ImageLoaderComponent getImageLoaderComponent() {
        return imageLoaderComponent;
    }
    //______________________________________________________________________________________________ getImageLoaderComponent

}
