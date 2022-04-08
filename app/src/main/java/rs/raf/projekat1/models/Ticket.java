package rs.raf.projekat1.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Ticket implements Parcelable {
    private int id;
    private String title;
    private TicketPriority ticketPriority;
    private int estimation;
    private int loggedTime;
    private String description;
    private TicketType ticketType;
    private TicketStatus ticketStatus;

    public Ticket(int id, TicketType ticketType, String title, TicketPriority ticketPriority, int estimation, int loggedTime, String description, TicketStatus ticketStatus) {
        this.id = id;
        this.title = title;
        this.ticketPriority = ticketPriority;
        this.estimation = estimation;
        this.loggedTime = loggedTime;
        this.description = description;
        this.ticketType = ticketType;
        this.ticketStatus = ticketStatus;
    }

    protected Ticket(Parcel in) {
        id = in.readInt();
        title = in.readString();
        estimation = in.readInt();
        loggedTime = in.readInt();
        description = in.readString();
        ticketPriority = ticketPriority.valueOf(in.readString());
        ticketType = TicketType.valueOf(in.readString());
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TicketPriority getTicketPriority() {
        return ticketPriority;
    }

    public void setTicketPriority(TicketPriority ticketPriority) {
        this.ticketPriority = ticketPriority;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public int getLoggedTime() {
        return loggedTime;
    }

    public void setLoggedTime(int loggedTime) {
        this.loggedTime = loggedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeInt(estimation);
        parcel.writeInt(loggedTime);
        parcel.writeString(description);
        parcel.writeString(ticketPriority.toString());
        parcel.writeString(ticketType.toString());
    }
}
