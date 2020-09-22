package ir.mlcode.latifiarchitecturelibrary.daggers.retrofit;


import dagger.Component;
import ir.mlcode.latifiarchitecturelibrary.daggers.DaggerScope;
import retrofit2.Retrofit;

@DaggerScope
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    Retrofit getRetrofit();
}
