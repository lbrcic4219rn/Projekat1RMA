package rs.raf.projekat1.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.w3c.dom.Text;

import rs.raf.projekat1.R;
import rs.raf.projekat1.models.Ticket;
import rs.raf.projekat1.models.TicketPriority;
import rs.raf.projekat1.models.TicketStatus;
import rs.raf.projekat1.models.TicketType;
import rs.raf.projekat1.viewmodels.MainViewModel;

public class EditTicketFragment extends Fragment {

    private MainViewModel viewModel;

    private Spinner ticketTypeSpinner;
    private Spinner ticketPrioritySpinner;
    private EditText estEditText;
    private EditText ticketTitleEditText;
    private EditText ticketDescriptionText;
    private Button button;
    private Ticket ticket;


    public EditTicketFragment() {
        super(R.layout.fragment_new_ticket);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity() ).get(MainViewModel.class);

        Bundle arguments = getArguments();
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        ticket = viewModel.getTicketById(arguments.getInt("ticketId"));

        initView(view);
        initListeners();
    }

    private void initListeners() {
        button.setOnClickListener(v -> {
            boolean isValid = estEditText.getText().toString().trim().length() != 0
                    && ticketTitleEditText.getText().toString().trim().length() != 0
                    && ticketDescriptionText.getText().toString().trim().length() != 0;
            if(!isValid) {
                Toast.makeText(getContext(), "All field required", Toast.LENGTH_SHORT).show();
                return;
            }

            TicketType ticketType = TicketType.valueOf(ticketTypeSpinner.getSelectedItem().toString().toUpperCase());
            TicketPriority ticketPriority = TicketPriority.valueOf(ticketPrioritySpinner.getSelectedItem().toString().toUpperCase());
            int est = Integer.parseInt(estEditText.getText().toString());
            String ticketTitle = ticketTitleEditText.getText().toString();
            String ticketDesc = ticketDescriptionText.getText().toString();

            viewModel.voidUpdateTicket(ticket.getId(), ticketType, ticketPriority, est, ticketTitle, ticketDesc);


            Toast.makeText(getContext(), "Ticket edited", Toast.LENGTH_LONG).show();

            estEditText.setText("");
            ticketTitleEditText.setText("");
            ticketDescriptionText.setText("");
            ticketTypeSpinner.setSelection(0);
            ticketPrioritySpinner.setSelection(0);
        });
    }

    private void initView(View view) {

        TextView mainHeader = view.findViewById(R.id.mainHeader);
        mainHeader.setText(R.string.editHeader);

        ticketTypeSpinner = view.findViewById(R.id.ticketTypeSpinner);
        ticketPrioritySpinner = view.findViewById(R.id.ticketPrioritySpinner);
        estEditText = view.findViewById(R.id.estEditText);
        ticketTitleEditText = view.findViewById(R.id.ticketTitleEditText);
        ticketDescriptionText =  view.findViewById(R.id.ticketDescriptionEditText);
        button = view.findViewById(R.id.createTicketButton);

        if(ticket.getTicketType().equals(TicketType.ENHANCEMENT))
            ticketTypeSpinner.setSelection(0);
        else
            ticketTypeSpinner.setSelection(1);

        ticketPrioritySpinner.setSelection(getPosition(ticket.getTicketPriority()));
        estEditText.setText(String.valueOf(ticket.getEstimation()));
        ticketTitleEditText.setText(ticket.getTitle());
        ticketDescriptionText.setText(ticket.getDescription());
        button.setText(R.string.save);
    }

    public int getPosition(TicketPriority ticketPriority){
        if (ticketPriority.equals(TicketPriority.HIGHEST)) return 0;
        if (ticketPriority.equals(TicketPriority.HIGH)) return 1;
        if (ticketPriority.equals(TicketPriority.MEDIUM)) return 2;
        if (ticketPriority.equals(TicketPriority.LOW)) return 3;
        return 4;
    }

}
