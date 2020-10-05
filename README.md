# LatifiArchitecture

| Used From |
| --- |
| retrofit2 |
| Dagger |
| universalimageloader |
| butterknife |
| calligraphy |
| rxjava2 |
| multidex |
| CropImage |
| fresco |



## How To Use?

***Step 1***

Add the JitPack repository to your build file. 
Add it in your root build.gradle at the end of repositories:

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

***Step 2***

Add the dependency

```
dependencies {
	        implementation 'com.github.latifimehrdad:LatifiArchitecture:1.10'
	}
```

***Step 3***

Add this code to build.gradle

```
android {
    ...
    defaultConfig {
        ...
        multiDexEnabled true
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    dataBinding {
        enabled = true
    }

}
```

***Step 4***

Create a Interface Class For Use in Retrofit Like this

```
public interface RetrofitApiInterface {

    @FormUrlEncoded
    @POST("/token")
    Call<MD_Token> getToken
            (
                    @Field("client_id") String client_id,
                    @Field("client_secret") String client_secret,
                    @Field("grant_type") String grant_type
            );
    

}

```



***Step 5***

Create a Application Class Like this

```
public class testApplication extends APP_Latifi {

    private Context context;
    public String Host = "http://You Api Link";
    private RetrofitApiInterface retrofitApiInterface;


    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        setContext(this.context);
        setHost(Host);
        configurationRetrofitComponent();
    }

    //______________________________________________________________________________________________ getApplication
    public static testApplication getApplicationWMS(Context context) {
        return (testApplication) context.getApplicationContext();
    }
    //______________________________________________________________________________________________ getApplication



    //______________________________________________________________________________________________ configurationRetrofitComponent
    private void configurationRetrofitComponent() {
        retrofitApiInterface = getRetrofitComponent().getRetrofit().create(RetrofitApiInterface.class);
    }
    //______________________________________________________________________________________________ configurationRetrofitComponent



    //______________________________________________________________________________________________ getRetrofitApiInterface
    public RetrofitApiInterface getRetrofitApiInterface() {
        return retrofitApiInterface;
    }
    //______________________________________________________________________________________________ getRetrofitApiInterface
    
}

```

