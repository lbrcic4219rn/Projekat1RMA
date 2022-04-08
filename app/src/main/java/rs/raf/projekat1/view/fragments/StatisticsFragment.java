package rs.raf.projekat1.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

import java.util.List;

import rs.raf.projekat1.R;
import rs.raf.projekat1.models.Ticket;
import rs.raf.projekat1.models.TicketStatus;
import rs.raf.projekat1.models.TicketType;
import rs.raf.projekat1.viewmodels.MainViewModel;

public class StatisticsFragment extends Fragment {
    private MainViewModel viewModel;
    private TextView todoCount;
    private TextView todoEnhancementCount;
    private TextView todoBugCount;
    private TextView inProgressCount;
    private TextView inProgressEnhancementCount;
    private TextView inProgressBugCount;
    private TextView doneCount;
    private TextView doneEnhancementCount;
    private TextView doneBugCount;

    private int valueTodoCount = 0;
    private int valueTodoEnhancementCount  = 0;
    private int valueTodoBugCount  = 0;
    private int valueInProgressCount  = 0;
    private int valueInProgressEnhancementCount  = 0;
    private int valueInProgressBugCount  = 0;
    private int valueDoneCount  = 0;
    private int valueDoneEnhancementCount  = 0;
    private int valueDoneBugCount  = 0;

    public StatisticsFragment() {
        super(R.layout.fragment_statistics);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        init(view);
    }

    private void init(View view) {
        initView(view);
        initObservers();
    }

    private void initObservers() {
        viewModel.getTickets().observe(getViewLifecycleOwner(), tickets -> {

            setTicketCount(tickets);

            todoCount.setText(String.valueOf(valueTodoCount));
            todoEnhancementCount.setText(String.valueOf(valueTodoEnhancementCount));
            todoBugCount.setText(String.valueOf(valueTodoBugCount));
            inProgressCount.setText(String.valueOf(valueInProgressCount));
            inProgressEnhancementCount.setText(String.valueOf(valueInProgressEnhancementCount));
            inProgressBugCount.setText(String.valueOf(valueInProgressBugCount));
            doneCount.setText(String.valueOf(valueDoneCount));
            doneEnhancementCount.setText(String.valueOf(valueDoneEnhancementCount));
            doneBugCount.setText(String.valueOf(valueDoneBugCount));
        });
    }

    private void setTicketCount(List<Ticket> tickets) {
        valueTodoCount = 0;
        valueTodoEnhancementCount  = 0;
        valueTodoBugCount  = 0;
        valueInProgressCount  = 0;
        valueInProgressEnhancementCount  = 0;
        valueInProgressBugCount  = 0;
        valueDoneCount  = 0;
        valueDoneEnhancementCount  = 0;
        valueDoneBugCount  = 0;
        System.out.println(tickets.size());
        for(Ticket ticket : tickets){
            boolean todoEnh = ticket.getTicketStatus().equals(TicketStatus.TODO) && ticket.getTicketType().equals(TicketType.ENHANCEMENT);
            boolean todoBug = ticket.getTicketStatus().equals(TicketStatus.TODO) && ticket.getTicketType().equals(TicketType.BUG);
            boolean inPEnh = ticket.getTicketStatus().equals(TicketStatus.IN_PROGRESS) && ticket.getTicketType().equals(TicketType.ENHANCEMENT);
            boolean inPBug = ticket.getTicketStatus().equals(TicketStatus.IN_PROGRESS) && ticket.getTicketType().equals(TicketType.BUG);
            boolean doneEnh = ticket.getTicketStatus().equals(TicketStatus.DONE) && ticket.getTicketType().equals(TicketType.ENHANCEMENT);
            boolean doneBug = ticket.getTicketStatus().equals(TicketStatus.DONE) && ticket.getTicketType().equals(TicketType.BUG);

            if(todoEnh) {
                valueTodoCount++;
                valueTodoEnhancementCount++;
                continue;
            }
            if(todoBug) {
                valueTodoCount++;
                valueTodoBugCount++;
                continue;
            }
            if(inPEnh) {
                valueInProgressCount++;
                valueInProgressEnhancementCount++;
                continue;
            }
            if(inPBug) {
                valueInProgressCount++;
                valueInProgressBugCount++;
                continue;
            }
            if(doneEnh) {
                valueDoneCount++;
                valueDoneEnhancementCount++;
                continue;
            }
            if(doneBug) {
                valueDoneCount++;
                valueDoneBugCount++;
            }
        }
    }

    private int getEnhancementsBugs(List<Ticket> tickets, TicketType ticketType) {
        int cnt = 0;
        for(Ticket ticket : tickets){
            if(ticket.getTicketType().equals(ticketType))
                cnt++;
        }
        return cnt;
    }

    private void initView(View view) {
        todoCount = view.findViewById(R.id.todoCount);
        todoEnhancementCount = view.findViewById(R.id.todoEnhancementCount);
        todoBugCount = view.findViewById(R.id.todoBugCount);
        inProgressCount = view.findViewById(R.id.inProgressCount);
        inProgressEnhancementCount = view.findViewById(R.id.inProgressEnhancementCount);
        inProgressBugCount = view.findViewById(R.id.inProgressBugCount);
        doneCount = view.findViewById(R.id.doneCount);
        doneEnhancementCount = view.findViewById(R.id.doneEnhancementCount);
        doneBugCount = view.findViewById(R.id.doneBugCount);
    }
}
