package rs.raf.projekat1.view.recycler.differ;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import rs.raf.projekat1.models.Ticket;

public class TicketDiffItemCallback extends DiffUtil.ItemCallback<Ticket> {
    @Override
    public boolean areItemsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Ticket oldItem, @NonNull Ticket newItem) {
        return oldItem.getDescription().equals(newItem.getDescription())
                && (oldItem.getEstimation() == newItem.getEstimation())
                && oldItem.getTicketPriority().equals(newItem.getTicketPriority())
                && oldItem.getTitle().equals(oldItem.getTitle())
                && (oldItem.getLoggedTime() == newItem.getLoggedTime())
                && oldItem.getTicketType().equals(newItem.getTicketType())
                && oldItem.getTicketStatus().equals(newItem.getTicketStatus());
    }
}
