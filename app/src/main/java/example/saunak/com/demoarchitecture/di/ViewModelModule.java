package example.saunak.com.demoarchitecture.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import example.saunak.com.demoarchitecture.ui.mypost.PostsViewModel;
import example.saunak.com.demoarchitecture.viewmodel.DemoArchitectureViewModelFactory;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    abstract ViewModel bindPostsViewModel(PostsViewModel postsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(DemoArchitectureViewModelFactory viewModelFactory);
}
