package example.saunak.com.demoarchitecture.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import example.saunak.com.demoarchitecture.AppExecutors;
import example.saunak.com.demoarchitecture.api.ApiResponse;
import example.saunak.com.demoarchitecture.api.JSONApiService;
import example.saunak.com.demoarchitecture.db.DemoArchitectureDb;
import example.saunak.com.demoarchitecture.db.PostsDao;
import example.saunak.com.demoarchitecture.model.Posts;
import example.saunak.com.demoarchitecture.model.Resource;
import example.saunak.com.demoarchitecture.util.RateLimiter;

public class PostsRepository {
    private final DemoArchitectureDb db;
    private final PostsDao postsDao;
    private final JSONApiService urlExtension;
    private final RateLimiter<String> rateLimiter = new RateLimiter<>(10, TimeUnit.MINUTES);
    private final AppExecutors appExecutors;

    @Inject
    public PostsRepository(DemoArchitectureDb db, PostsDao postsDao, JSONApiService urlExtension, AppExecutors appExecutors) {
        this.db = db;
        this.postsDao = postsDao;
        this.urlExtension = urlExtension;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<Posts>> loadSinglePost(){
        return new NetworkBoundResource<Posts,Posts>(appExecutors){

            @Override
            protected void saveCallResult(@NonNull Posts item) {
                postsDao.insert(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable Posts data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<Posts> loadFromDb() {
                return postsDao.getSinglePost();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Posts>> createCall() {
                return urlExtension.getSinglePost();
            }
        }.asLiveData();
    }
}
