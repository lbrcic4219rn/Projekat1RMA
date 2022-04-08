package rs.raf.projekat1.view.fragments.listFragments;

import static rs.raf.projekat1.view.activities.MainActivity.TICKET_DETAIL_TAG;

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

public class ToDoFragment extends Fragment {

    private RecyclerView recyclerView;
    private MainViewModel viewModel;
    private TicketAdapter ticketAdapter;


    public ToDoFragment() {
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
        init();
    }

    private void init() {
        initView();
        initObservers();
        initRecycler();
    }

    private void initObservers() {
        viewModel.getTodoTickets().observe(this.getViewLifecycleOwner(), tickets -> {
            ticketAdapter.submitList(tickets);
        });
    }

    private void initRecycler() {
        ticketAdapter = new TicketAdapter(new TicketDiffItemCallback(),  /*ticketClicked*/ ticket -> {
            TicketDetailFragment fragment = new TicketDetailFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainLayoutId, fragment, TICKET_DETAIL_TAG).addToBackStack(null).commit();
            return null;
        }, /*onTicketDelete */ ticket -> {
            viewModel.deleteTodoTicket(ticket.getId());
            viewModel.deleteTicket(ticket.getId());
            return null;
        }, /*onTicketTopButton*/ ticket -> {
            viewModel.deleteTodoTicket(ticket.getId());
            viewModel.addInProgressTicket(ticket);
            return null;
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(ticketAdapter);
    }

    private void initView() {
        recyclerView = getView().findViewById(R.id.recyclerViewTodo);
    }
}
