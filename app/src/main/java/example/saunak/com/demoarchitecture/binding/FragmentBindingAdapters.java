package example.saunak.com.demoarchitecture.binding;


import android.support.v4.app.Fragment;

import javax.inject.Inject;

public class FragmentBindingAdapters {
    final Fragment fragment;

    @Inject
    public FragmentBindingAdapters(Fragment fragment) {
        this.fragment = fragment;
    }
}
