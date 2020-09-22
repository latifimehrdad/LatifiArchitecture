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
	        implementation 'com.github.latifimehrdad:LatifiArchitecture:1.1'
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
public class TestApplication extends MultiDexApplication {

    private Context context;
    private RetrofitApiInterface retrofitApiInterface;
    public  String Host = "http://Yout Link";

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = getApplicationContext();
        ConfigurationRetrofitComponent();
    }


    //______________________________________________________________________________________________ getTestApplication
    public static TestApplication getTestApplication(Context context) {
        return (TestApplication) context.getApplicationContext();
    }
    //______________________________________________________________________________________________ getTestApplication



    //______________________________________________________________________________________________ ConfigurationRetrofitComponent
    private void ConfigurationRetrofitComponent() {
        retrofitApiInterface = DaggerRetrofitComponent
                .builder()
                .retrofitModule(new RetrofitModule(context,Host))
                .build().getRetrofit().create(RetrofitApiInterface.class);
    }
    //______________________________________________________________________________________________ ConfigurationRetrofitComponent


    //______________________________________________________________________________________________ getRetrofitApiInterface
    public RetrofitApiInterface getRetrofitApiInterface() {
        return retrofitApiInterface;
    }
    //______________________________________________________________________________________________ getRetrofitApiInterface
}

```

***Step 5***

in MainActivity

```
RetrofitApiInterface retrofit = TestApplication.getTestApplication(this).getRetrofitApiInterface();
        retrofit.getToken(client_id_value, client_secret_value,grant_type_value)
                .enqueue(new Callback<MD_Token>() {
                    @Override
                    public void onResponse(Call<MD_Token> call, Response<MD_Token> response) {
                        Log.i("latifi", response.message());
                        Log.i("latifi", response.body().getAccess_token());
                    }

                    @Override
                    public void onFailure(Call<MD_Token> call, Throwable t) {
                        Log.i("latifi", t.getMessage());
                    }
                });
		
```

