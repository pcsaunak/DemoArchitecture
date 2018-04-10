package example.saunak.com.demoarchitecture.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import example.saunak.com.demoarchitecture.ui.mypost.PostsFragment;

@Module
public abstract class FragmentsBuilderModule {
    @ContributesAndroidInjector
    abstract PostsFragment contributePostsFragment();
}
