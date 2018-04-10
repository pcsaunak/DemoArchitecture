package example.saunak.com.demoarchitecture.binding;


import android.support.v4.app.Fragment;

public class FragmentDataBindingComponent implements android.databinding.DataBindingComponent {
    private final FragmentBindingAdapters adapter;

    public FragmentDataBindingComponent(Fragment fragment) {
        this.adapter = new FragmentBindingAdapters(fragment);
    }

    public FragmentBindingAdapters getAdapter() {
        return adapter;
    }
}
