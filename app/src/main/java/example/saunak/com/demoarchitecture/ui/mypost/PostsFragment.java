package example.saunak.com.demoarchitecture.ui.mypost;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import example.saunak.com.demoarchitecture.R;
import example.saunak.com.demoarchitecture.binding.FragmentDataBindingComponent;
import example.saunak.com.demoarchitecture.databinding.PostFragmentBinding;
import example.saunak.com.demoarchitecture.di.Injectable;
import example.saunak.com.demoarchitecture.ui.commons.NavigationController;
import example.saunak.com.demoarchitecture.ui.commons.PostListAdapter;
import example.saunak.com.demoarchitecture.util.AutoClearedValue;

public class PostsFragment extends Fragment implements Injectable {
    public static String TAG = PostsFragment.class.getSimpleName();
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    NavigationController navigationController;

    DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
    AutoClearedValue<PostFragmentBinding> binding;
    AutoClearedValue<PostListAdapter> adapter;
    PostsViewModel postsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PostFragmentBinding dataBinding = DataBindingUtil.
                inflate(inflater, R.layout.post_fragment,container,false,dataBindingComponent);
        binding = new AutoClearedValue<>(this,dataBinding);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"Inside onActivityCreated");
        postsViewModel = ViewModelProviders.of(this,viewModelFactory).get(PostsViewModel.class);
        initRecyclerView();

        PostListAdapter postListAdapter = new PostListAdapter(dataBindingComponent,
                post -> navigationController.navigateToPosts());
        binding.get().postList.setAdapter(postListAdapter);
        adapter = new AutoClearedValue<>(this,postListAdapter);
    }


    private void initRecyclerView(){
        binding.get().postList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
