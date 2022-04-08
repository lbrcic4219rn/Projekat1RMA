package rs.raf.projekat1.view.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.projekat1.R;
import rs.raf.projekat1.view.recycler.adapter.TicketAdapter;
import rs.raf.projekat1.viewmodels.MainViewModel;

public class MainFragment extends Fragment {
    private RecyclerView recyclerView;
    private MainViewModel viewModel;
    private TicketAdapter ticketAdapter;


    public MainFragment() {
        super(R.layout.fragment_todo);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity() ).get(MainViewModel.class);
        
    }
}
