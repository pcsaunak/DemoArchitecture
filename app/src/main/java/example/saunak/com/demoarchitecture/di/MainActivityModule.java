package example.saunak.com.demoarchitecture.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import example.saunak.com.demoarchitecture.MainActivity;

@Module
public abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = FragmentsBuilderModule.class)
    abstract MainActivity contributeMainActivity();
}
