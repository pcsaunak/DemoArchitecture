package example.saunak.com.demoarchitecture.ui.commons;

import android.support.v4.app.FragmentManager;

import javax.inject.Inject;

import example.saunak.com.demoarchitecture.MainActivity;
import example.saunak.com.demoarchitecture.R;
import example.saunak.com.demoarchitecture.ui.mypost.PostsFragment;

public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToPosts(){
        PostsFragment postsFragment = new PostsFragment();
        fragmentManager.beginTransaction().replace(containerId,postsFragment).commitAllowingStateLoss();
    }
}
