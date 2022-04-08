package rs.raf.projekat1.view.fragments.listFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import rs.raf.projekat1.R;
import rs.raf.projekat1.view.fragments.TicketDetailFragment;
import rs.raf.projekat1.view.recycler.adapter.TicketAdapter;
import rs.raf.projekat1.view.recycler.differ.TicketDiffItemCallback;
import rs.raf.projekat1.viewmodels.MainViewModel;

public class InProgressFragment extends Fragment {
    private RecyclerView recyclerView;
    private MainViewModel viewModel;
    private TicketAdapter ticketAdapter;

    public InProgressFragment() {
        super(R.layout.fragment_in_progress);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity() ).get(MainViewModel.class);
        init();
    }

    private void init() {
        initView();
        initObservers();
        initRecycler();
    }

    private void initObservers() {
        viewModel.getInProgressTickets().observe(this.getViewLifecycleOwner(), tickets -> {
            ticketAdapter.submitList(tickets);
        });
    }

    private void initRecycler() {
        ticketAdapter = new TicketAdapter(new TicketDiffItemCallback(), /*ticketClicked*/ ticket -> {
            Intent intent = new Intent(getActivity(), TicketDetailFragment.class);
            intent.putExtra("ticketData", ticket);
            startActivity(intent);
            return null;
        }, /*bottomButton */ ticket -> {
            viewModel.deleteInProgressTicket(ticket.getId());
            viewModel.addTodoTicket(ticket);
            return null;
        }, /*onTicketTopButton*/ ticket -> {
            viewModel.deleteInProgressTicket(ticket.getId());
            viewModel.addDoneTicket(ticket);
            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(ticketAdapter);
    }

    private void initView() {
        recyclerView = getView().findViewById(R.id.recyclerViewInProgress);
    }
}
