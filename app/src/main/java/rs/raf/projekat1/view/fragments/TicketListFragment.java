package rs.raf.projekat1.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import rs.raf.projekat1.R;
import rs.raf.projekat1.view.viewpager.PagerAdapterTabs;
import rs.raf.projekat1.viewmodels.MainViewModel;

public class TicketListFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private MainViewModel viewModel;

    public TicketListFragment() {
        super(R.layout.fragment_ticket_list);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //System.out.println("TICKET LIST CREATED");
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity() ).get(MainViewModel.class);
        initView();
        initTabs();
    }

    @Override
    public void onResume() {
        super.onResume();
        //System.out.println("TICKET LIST RESUME");

    }

    private void initTabs() {
        viewPager.setAdapter(new PagerAdapterTabs(getChildFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView() {
        View view = getView();
        if(view != null){
            viewPager = view.findViewById(R.id.viewPagerList);
            tabLayout = view.findViewById(R.id.tabLayout);
        }
    }
}
