/**
 *  Copyright 2017 georgiopoulos kyriakos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package georgiopoulos.infootball.data.remote.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule{

    private static final String baseUrl="http://www.thesportsdb.com/api/v1/json/1/";

    @Provides
    @Singleton
    public Gson provideGson(){
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        return client;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson,OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                                    .addConverterFactory(GsonConverterFactory.create(gson))
                                    .baseUrl(baseUrl)
                                    .client(okHttpClient)
                                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                                    .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public ServerAPI provideServerApi(Retrofit retrofit){
        return retrofit.create(ServerAPI.class);
    }
}
