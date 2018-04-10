package example.saunak.com.demoarchitecture.ui.commons;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Objects;

import example.saunak.com.demoarchitecture.R;
import example.saunak.com.demoarchitecture.databinding.RecyclerItemBinding;
import example.saunak.com.demoarchitecture.model.Posts;

public class PostListAdapter extends DataBoundListAdapter<Posts,RecyclerItemBinding> {
    private final android.databinding.DataBindingComponent dataBindingComponent;
    private final PostsClickCallback postsClickCallback;

    public PostListAdapter(DataBindingComponent dataBindingComponent, PostsClickCallback postsClickCallback) {
        this.dataBindingComponent = dataBindingComponent;
        this.postsClickCallback = postsClickCallback;
    }

    @Override
    protected RecyclerItemBinding createBinding(ViewGroup parent) {

        RecyclerItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.recycler_item,
                        parent, false, dataBindingComponent);

        binding.getRoot().setOnClickListener(v -> {
            Posts posts = binding.getPosts();
            if(posts != null && postsClickCallback != null)
                postsClickCallback.onClick(posts);
        });
        return binding;
    }

    @Override
    protected void bind(RecyclerItemBinding binding, Posts item) {
        binding.setPosts(item);
    }

    @Override
    protected boolean areItemsTheSame(Posts oldItem, Posts newItem) {
        return (oldItem.getId() == newItem.getId());
    }

    @Override
    protected boolean areContentsTheSame(Posts oldItem, Posts newItem) {
        return (oldItem.getTitle() == newItem.getTitle());
    }

    public interface PostsClickCallback{
        void onClick(Posts posts);
    }
}