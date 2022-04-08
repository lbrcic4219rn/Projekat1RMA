package rs.raf.projekat1.view.recycler.adapter;

import static rs.raf.projekat1.view.activities.MainActivity.ADMIN_LOGGED;
import static rs.raf.projekat1.view.activities.MainActivity.IS_LOGGED_IN;
import static rs.raf.projekat1.view.activities.MainActivity.USER_LOGGED;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Function;

import rs.raf.projekat1.R;
import rs.raf.projekat1.models.Ticket;
import rs.raf.projekat1.models.TicketStatus;
import rs.raf.projekat1.models.TicketType;
import rs.raf.projekat1.viewmodels.MainViewModel;

public class TicketAdapter extends ListAdapter<Ticket, TicketAdapter.ViewHolder> {

    private final Function<Ticket, Void> onTicketClicked;
    private final Function<Ticket, Void> onTicketBottomButton;
    private final Function<Ticket, Void> onTicketTopButton;

    public TicketAdapter(@NonNull DiffUtil.ItemCallback<Ticket> diffCallback,
                         Function<Ticket, Void> onTicketClicked,
                         Function<Ticket, Void> onTicketBottomButton,
                         Function<Ticket, Void> onTicketTopButton
                         ) {
        super(diffCallback);
        this.onTicketClicked = onTicketClicked;
        this.onTicketBottomButton = onTicketBottomButton;
        this.onTicketTopButton = onTicketTopButton;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_list_item, parent, false);
        return new ViewHolder(view, parent.getContext(), position -> {
            Ticket ticket = getItem(position);
            onTicketClicked.apply(ticket);
            return null;
        }, position -> {
            Ticket ticket = getItem(position);
            onTicketBottomButton.apply(ticket);
            return null;
        }, position -> {
            Ticket ticket = getItem(position);
            onTicketTopButton.apply(ticket);
            return null;
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Ticket ticket = getItem(position);
        holder.bind(ticket);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final Context context;
        private ImageView ticketLogo;
        private TextView ticketTitle;
        private TextView ticketDescription;
        private ImageView topButton;
        private ImageView bottomButton;
        private boolean admin;
        private final Function<Integer, Void> onTicketClicked;
        private final Function<Integer, Void> onTicketBottomButton;
        private final Function<Integer, Void> onTicketTopButton;

        public ViewHolder(@NonNull View itemView, Context context,
                          Function<Integer, Void> onTicketClicked,
                          Function<Integer, Void> onTicketBottomButton,
                          Function<Integer, Void> onTicketTopButton) {
            super(itemView);
            this.context = context;
            this.onTicketClicked = onTicketClicked;
            this.onTicketBottomButton = onTicketBottomButton;
            this.onTicketTopButton = onTicketTopButton;
        }


        //PRAVLJENJE ITEMA
        public void bind(Ticket ticket) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
            String message = sharedPreferences.getString(IS_LOGGED_IN, "");
            admin = message.equals(ADMIN_LOGGED) ? true : false;

            initView(ticket);
            initListeners(ticket);
        }

        private void initView(Ticket ticket) {
            ticketLogo = itemView.findViewById(R.id.ticketLogo);

            if(ticket.getTicketType().equals(TicketType.ENHANCEMENT))
                ticketLogo.setImageResource(R.drawable.ic_baseline_keyboard_double_arrow_up_24);
            else
                ticketLogo.setImageResource(R.drawable.ic_baseline_bug_report_24);

            ticketTitle = itemView.findViewById(R.id.ticketTitle);
            ticketTitle.setText(ticket.getTitle());
            ticketDescription = itemView.findViewById(R.id.ticketDescription);
            ticketDescription.setText(ticket.getDescription());
            topButton = itemView.findViewById(R.id.topButton);

            bottomButton = itemView.findViewById(R.id.bottomButton);
            topButton.setImageResource(R.drawable.ic_baseline_chevron_right_24);

            if(ticket.getTicketStatus().equals(TicketStatus.TODO)){
                if(admin)
                    bottomButton.setImageResource(R.drawable.ic_baseline_close_24);
                else {
                    bottomButton.setVisibility(View.GONE);
                }
            } else if(ticket.getTicketStatus().equals(TicketStatus.IN_PROGRESS)) {
                bottomButton.setImageResource(R.drawable.ic_baseline_chevron_left_24);
            } else {
                topButton.setVisibility(View.GONE);
                bottomButton.setVisibility(View.GONE);
            }
        }

        private void initListeners(Ticket ticket) {
            itemView.setOnClickListener(v -> {
                if(getAbsoluteAdapterPosition() != RecyclerView.NO_POSITION){
                    onTicketClicked.apply(getAbsoluteAdapterPosition());
                }
            });

            bottomButton.setOnClickListener(v -> {
                if(getAbsoluteAdapterPosition() != RecyclerView.NO_POSITION){
                    onTicketBottomButton.apply(getAbsoluteAdapterPosition());
                }
            });

            topButton.setOnClickListener(v -> {
                if(getAbsoluteAdapterPosition() != RecyclerView.NO_POSITION){
                    onTicketTopButton.apply(getAbsoluteAdapterPosition());
                }
            });
        }
    }
}
