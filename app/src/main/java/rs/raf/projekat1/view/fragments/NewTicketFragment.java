package rs.raf.projekat1.view.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import rs.raf.projekat1.R;
import rs.raf.projekat1.models.TicketPriority;
import rs.raf.projekat1.models.TicketStatus;
import rs.raf.projekat1.models.TicketType;
import rs.raf.projekat1.viewmodels.MainViewModel;

public class NewTicketFragment extends Fragment {
    private Spinner ticketTypeSpinner;
    private Spinner ticketPrioritySpinner;
    private EditText estEditText;
    private EditText ticketTitleEditText;
    private EditText ticketDescriptionText;
    private MainViewModel viewModel;
    private Button button;

    public NewTicketFragment() {
        super(R.layout.fragment_new_ticket);
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

            viewModel.addTicket(ticketType, ticketTitle, ticketPriority, est, 0, ticketDesc, TicketStatus.TODO);


            Toast.makeText(getContext(), "new Ticket added", Toast.LENGTH_LONG).show();

            estEditText.setText("");
            ticketTitleEditText.setText("");
            ticketDescriptionText.setText("");
            ticketTypeSpinner.setSelection(0);
            ticketPrioritySpinner.setSelection(0);
        });
    }

    private void initView(View view) {
        ticketTypeSpinner = view.findViewById(R.id.ticketTypeSpinner);
        ticketPrioritySpinner = view.findViewById(R.id.ticketPrioritySpinner);
        estEditText = view.findViewById(R.id.estEditText);
        ticketTitleEditText = view.findViewById(R.id.ticketTitleEditText);
        ticketDescriptionText =  view.findViewById(R.id.ticketDescriptionEditText);
        button = view.findViewById(R.id.createTicketButton);

    }
}
