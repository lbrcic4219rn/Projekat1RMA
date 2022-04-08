package rs.raf.projekat1.view.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import rs.raf.projekat1.R;
import rs.raf.projekat1.viewmodels.MainViewModel;

public class TicketDetailFragment extends Fragment {

    private MainViewModel viewModel;

    public TicketDetailFragment() {
        super(R.layout.fragment_ticket_detail);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

    }

}