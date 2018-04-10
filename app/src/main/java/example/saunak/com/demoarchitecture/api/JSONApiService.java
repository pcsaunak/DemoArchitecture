package example.saunak.com.demoarchitecture.api;

import android.arch.lifecycle.LiveData;

import java.util.List;

import example.saunak.com.demoarchitecture.model.Posts;
import retrofit2.http.GET;

public interface JSONApiService {
    @GET("posts")
    LiveData<ApiResponse<List<Posts>>> getPosts();
    @GET("posts/1")
    LiveData<ApiResponse<Posts>> getSinglePost();
}
