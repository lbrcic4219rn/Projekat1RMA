package rs.raf.projekat1.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import rs.raf.projekat1.R;
import rs.raf.projekat1.view.recycler.adapter.TicketAdapter;
import rs.raf.projekat1.view.viewpager.PageAdapterMenu;
import rs.raf.projekat1.viewmodels.MainViewModel;

public class MainFragment extends Fragment {
    private RecyclerView recyclerView;
    private MainViewModel viewModel;
    private TicketAdapter ticketAdapter;
    private ViewPager viewPager;
    private View itemView;


    public MainFragment() {
        super(R.layout.fragment_main);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("CREATED MAIN FRAGMENT");
        itemView = view;
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        initViewPager(view);
        initNavigation(view);

    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        initViewPager(itemView);
        initNavigation(itemView);
        System.out.println("RESUMED MAIN FRAGMENT");
    }

    private void initNavigation(View view) {
        ((BottomNavigationView) view.findViewById(R.id.bottomNavigation)).setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.statistics: viewPager.setCurrentItem(PageAdapterMenu.FRAGMENT_STATISTICS, false); break;
                case R.id.createTicket: viewPager.setCurrentItem(PageAdapterMenu.FRAGMENT_NEW_TICKET, false); break;
                case R.id.tickets: viewPager.setCurrentItem(PageAdapterMenu.FRAGMENT_TICKET_LIST, false); break;
                default: viewPager.setCurrentItem(PageAdapterMenu.FRAGMENT_PROFILE, false); break;
            }
            return true;
        });
    }

    private void initViewPager(View view) {
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(new PageAdapterMenu(getActivity().getSupportFragmentManager()));
    }
}
