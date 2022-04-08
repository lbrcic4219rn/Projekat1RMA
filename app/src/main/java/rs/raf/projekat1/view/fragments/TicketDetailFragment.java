package rs.raf.projekat1.view.fragments;

import static rs.raf.projekat1.view.activities.MainActivity.ADMIN_LOGGED;
import static rs.raf.projekat1.view.activities.MainActivity.IS_LOGGED_IN;
import static rs.raf.projekat1.view.activities.MainActivity.TICKET_DETAIL_TAG;
import static rs.raf.projekat1.view.activities.MainActivity.TICKET_EDIT_TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import rs.raf.projekat1.R;
import rs.raf.projekat1.models.Ticket;
import rs.raf.projekat1.models.TicketStatus;
import rs.raf.projekat1.models.TicketType;
import rs.raf.projekat1.viewmodels.MainViewModel;

public class TicketDetailFragment extends Fragment {
    private ImageView ticketLogo;
    private TextView ticketTitle;
    private TextView ticketPriority;
    private TextView estimation;
    private Button loggTimeButton;
    private TextView description;
    private TextView ticketType;
    private Button editButton;
    private boolean admin;

    private MainViewModel viewModel;
    private Ticket ticket;
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
        Bundle arguments = getArguments();
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        ticket = viewModel.getTicketById(arguments.getInt("ticketId"));

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        String message = sharedPreferences.getString(IS_LOGGED_IN, "");
        admin = message.equals(ADMIN_LOGGED) ? true : false;

        initView(view);
        initListeners();
    }

    private void initListeners() {
        loggTimeButton.setOnClickListener(v -> {
            viewModel.updateTicketIncrement(ticket.getId());
            loggTimeButton.setText(String.valueOf(ticket.getLoggedTime()));
        });

        loggTimeButton.setOnLongClickListener(v -> {
            viewModel.updateTicketDecrement(ticket.getId());
            loggTimeButton.setText(String.valueOf(ticket.getLoggedTime()));
            return true;
        });

        editButton.setOnClickListener(v -> {
            EditTicketFragment fragment = new EditTicketFragment();
            Bundle arguments = new Bundle();
            arguments.putInt("ticketId", ticket.getId());
            fragment.setArguments(arguments);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentContainer, fragment, TICKET_EDIT_TAG).addToBackStack(null).commit();
        });
    }

    private void initObservers() {
        viewModel.getTickets().observe(this.getViewLifecycleOwner(), tickets -> {
            Ticket editedTicket = tickets.get(this.ticket.getId());
            ticketTitle.setText(editedTicket.getTitle());
            description.setText(editedTicket.getDescription());
            ticketPriority.setText(editedTicket.getTicketPriority().toString());
            ticketType.setText(editedTicket.getTicketType().toString());
            estimation.setText(String.valueOf(editedTicket.getEstimation()));
            loggTimeButton.setText(String.valueOf(editedTicket.getLoggedTime()));

            if(editedTicket.getTicketType().equals(TicketType.ENHANCEMENT))
                ticketLogo.setImageResource(R.drawable.ic_baseline_keyboard_double_arrow_up_24);
            else
                ticketLogo.setImageResource(R.drawable.ic_baseline_bug_report_24);
        });
    }

    private void initView(View view) {

        ticketLogo = view.findViewById(R.id.editLogo);
        ticketTitle = view.findViewById(R.id.editTicketTitle);
        ticketPriority = view.findViewById(R.id.editTicketPriorityValue);
        estimation = view.findViewById(R.id.editTicketEstimationValue);
        loggTimeButton = view.findViewById(R.id.editTicketLoggedTimeButton);
        description = view.findViewById(R.id.editDescriptionValue);
        ticketType = view.findViewById(R.id.editTicketTypeValue);
        editButton = view.findViewById(R.id.editButton);

        ticketTitle.setText(ticket.getTitle());
        description.setText(ticket.getDescription());
        ticketPriority.setText(ticket.getTicketPriority().toString());
        ticketType.setText(ticket.getTicketType().toString());
        estimation.setText(String.valueOf(ticket.getEstimation()));
        loggTimeButton.setText(String.valueOf(ticket.getLoggedTime()));

        if(ticket.getTicketType().equals(TicketType.ENHANCEMENT))
            ticketLogo.setImageResource(R.drawable.ic_baseline_keyboard_double_arrow_up_24);
        else
            ticketLogo.setImageResource(R.drawable.ic_baseline_bug_report_24);

        if(!admin || ticket.getTicketStatus().equals(TicketStatus.DONE)) editButton.setVisibility(View.GONE);
    }

}