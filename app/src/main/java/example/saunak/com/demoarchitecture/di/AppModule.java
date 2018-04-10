package example.saunak.com.demoarchitecture.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.saunak.com.demoarchitecture.api.JSONApiService;
import example.saunak.com.demoarchitecture.db.DemoArchitectureDb;
import example.saunak.com.demoarchitecture.db.PostsDao;
import example.saunak.com.demoarchitecture.util.LiveDataCallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton @Provides
    JSONApiService provideJsonApiService(){
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .build()
                .create(JSONApiService.class);
    }

    @Singleton @Provides
    DemoArchitectureDb provideDBObject(Application application){
        return Room.databaseBuilder(application,DemoArchitectureDb.class,"demoarchitecture.db").build();
    }

    @Singleton @Provides
    PostsDao providePostsDao(DemoArchitectureDb db){
        return db.providePostsDao();
    }
}
