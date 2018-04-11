package example.saunak.com.demoarchitecture.ui.mypost;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import example.saunak.com.demoarchitecture.model.Posts;
import example.saunak.com.demoarchitecture.model.Resource;
import example.saunak.com.demoarchitecture.repository.PostsRepository;
import example.saunak.com.demoarchitecture.util.AbsentLiveData;

public class PostsViewModel extends ViewModel {
    public static String TAG = PostsViewModel.class.getSimpleName();
    private final MutableLiveData<String> login = new MutableLiveData<>();
//    private final LiveData<Resource<List<Posts>>> postList;
    private final LiveData<Resource<Posts>> singlePost;

    @Inject
    PostsViewModel(PostsRepository postsRepository){
        Log.d(TAG,"Inside constructor !!! ");
        singlePost = Transformations.switchMap(login, login -> {
            Log.d(TAG,"Inside singlepost lambda");
            if(login == null){
                Log.d(TAG,"Creating absent live data");
                return AbsentLiveData.create();
            }else {
                Log.d(TAG,"Fetching Posts from repository");
                return postsRepository.loadSinglePost();
            }
        });
    }


    @VisibleForTesting
    public LiveData<Resource<Posts>> getResults() {
        return singlePost;
    }

}
