package com.mobiquity.flicker.injection.network;

import com.google.gson.Gson;
import com.mobiquity.flicker.BuildConfig;
import com.mobiquity.flicker.core.network.NetworkConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by mahmoudelmorabea on 11/10/16.
 */
@Module
public class NetworkModule {

    @Provides @Named("base_url")
    public String provdieBaseUrl() {
        return BuildConfig.base_url;
    }

    @Provides
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    public RxJavaCallAdapterFactory provdieRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    @Provides
    public OkHttpClient provdieOkHttpClient(NetworkConfig config) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.connectTimeout(config.getConnectionTimeout(), TimeUnit.SECONDS)
                .writeTimeout(config.getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(config.getReadTimeout(), TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(Level.BASIC);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    @Provides
    public Retrofit provdieRetrofit(@Named("base_url") String baseUrl, OkHttpClient httpClient, Gson gson, RxJavaCallAdapterFactory rxJavaCallAdapterFactory) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(rxJavaCallAdapterFactory);
        return builder.build();
    }

}
