package rs.raf.projekat1.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import rs.raf.projekat1.models.Ticket;
import rs.raf.projekat1.models.TicketPriority;
import rs.raf.projekat1.models.TicketStatus;
import rs.raf.projekat1.models.TicketType;

public class MainViewModel extends ViewModel {

    public static int counter = 100;
    private final MutableLiveData<List<Ticket>> tickets = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> todoTickets = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> inProgressTickets = new MutableLiveData<>();
    private final MutableLiveData<List<Ticket>> doneTickets = new MutableLiveData<>();

    private ArrayList<Ticket> ticketList = new ArrayList<>();
    private ArrayList<Ticket> todoTicketList = new ArrayList<>();
    private ArrayList<Ticket> inProgressTicketList = new ArrayList<>();
    private ArrayList<Ticket> doneTicketList = new ArrayList<>();

    public MainViewModel() {
        for (int i = 0; i < 100; i++) {
            Random r = new Random();
            TicketType ticketType;
            ticketType = r.nextInt() % 2 == 0 ? TicketType.ENHANCEMENT : TicketType.BUG;
            TicketPriority priority;
            int random = r.nextInt() % 5;
            switch (random){
                case 0: priority = TicketPriority.HIGHEST; break;
                case 1: priority = TicketPriority.HIGH; break;
                case 2: priority = TicketPriority.MEDIUM; break;
                case 3: priority = TicketPriority.LOW; break;
                default: priority = TicketPriority.LOWEST; break;
            }
            int statusRandom = r.nextInt() % 3;
            TicketStatus status;
            switch (statusRandom){
                case 0: status = TicketStatus.TODO; break;
                case 1: status = TicketStatus.IN_PROGRESS; break;
                default: status = TicketStatus.DONE; break;
            }
            Ticket ticket = new Ticket(i, ticketType, "Ticket " + i, priority, r.nextInt(5), r.nextInt(4), "wdadw", status);
            switch (statusRandom){
                case 0: todoTicketList.add(ticket); break;
                case 1: inProgressTicketList.add(ticket); break;
                default: doneTicketList.add(ticket); break;
            }
            ticketList.add(ticket);
        }
        ArrayList<Ticket> ticketsToSubmit = new ArrayList<>(ticketList);
        tickets.setValue(ticketsToSubmit);
        ArrayList<Ticket> todoTicketsToSubmit = new ArrayList<>(todoTicketList);
        todoTickets.setValue(todoTicketsToSubmit);
        ArrayList<Ticket> inProgressTicketsToSubmit = new ArrayList<>(inProgressTicketList);
        inProgressTickets.setValue(inProgressTicketsToSubmit);
        ArrayList<Ticket> doneTicketsToSubmit = new ArrayList<>(doneTicketList);
        doneTickets.setValue(doneTicketsToSubmit);
    }



    public LiveData<List<Ticket>> getTodoTickets() {
        return todoTickets;
    }

    public LiveData<List<Ticket>> getInProgressTickets() {
        return inProgressTickets;
    }

    public LiveData<List<Ticket>> getDoneTickets() {
        return doneTickets;
    }

    public LiveData<List<Ticket>> getTickets() {
        return tickets;
    }

    public Ticket getTicketById(int id){
        Optional<Ticket> ticketOptional = ticketList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if(ticketOptional.isPresent()){
            return ticketOptional.get();
        }
        return null;
    }

    public void filterTickets(String filter){
        List<Ticket> filteredList = ticketList.stream().filter(ticket -> ticket.getTitle().toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
        tickets.setValue(filteredList);
    }

    public void addTicket(TicketType ticketType, String title, TicketPriority ticketPriority, int estimation, int loggedTime, String description, TicketStatus ticketStatus){
        int id = counter++;
        Ticket ticket = new Ticket(id, ticketType, title, ticketPriority, estimation, loggedTime, description, ticketStatus);


        ticketList.add(ticket);
        todoTicketList.add(ticket);
        ArrayList<Ticket> ticketsToSubmit = new ArrayList<>(ticketList);
        tickets.setValue(ticketsToSubmit);
        ArrayList<Ticket> toDoTicketsToSubmit = new ArrayList<>(todoTicketList);
        todoTickets.setValue(toDoTicketsToSubmit);
    }

    public void deleteTicket(int id) {
        Optional<Ticket> ticketOptional = ticketList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if(ticketOptional.isPresent()){
            ticketList.remove(ticketOptional.get());
            ArrayList<Ticket> ticketsToSubmit = new ArrayList<>(ticketList);
            tickets.setValue(ticketsToSubmit);
        }
    }

    //TODO
    public void filterTodoTickets(String filter){
        List<Ticket> filteredList = todoTicketList.stream().filter(ticket -> ticket.getTitle().toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
        todoTickets.setValue(filteredList);
    }

    public void addTodoTicket(Ticket ticket){
        ticket.setTicketStatus(TicketStatus.TODO);
        todoTicketList.add(ticket);
        ArrayList<Ticket> toDoTicketsToSubmit = new ArrayList<>(todoTicketList);
        todoTickets.setValue(toDoTicketsToSubmit);

        //NOTIFY
        update();
    }

    public void update() {
        ArrayList<Ticket> ticketsToSubmit = new ArrayList<>(ticketList);
        tickets.setValue(ticketsToSubmit);
    }

    public void deleteTodoTicket(int id) {
        Optional<Ticket> ticketOptional = todoTicketList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if(ticketOptional.isPresent()){
            todoTicketList.remove(ticketOptional.get());
            ArrayList<Ticket> ticketsToSubmit = new ArrayList<>(todoTicketList);
            todoTickets.setValue(ticketsToSubmit);
            //NOTIFY
            update();
        }
    }

    //IN PROGRESS
    public void filterInProgressTickets(String filter){
        List<Ticket> filteredList = inProgressTicketList.stream().filter(ticket -> ticket.getTitle().toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
        inProgressTickets.setValue(filteredList);
    }

    public void addInProgressTicket(Ticket ticket){
        ticket.setTicketStatus(TicketStatus.IN_PROGRESS);

        inProgressTicketList.add(ticket);
        ArrayList<Ticket> ticketsToSubmit = new ArrayList<>(inProgressTicketList);
        inProgressTickets.setValue(ticketsToSubmit);
        //NOTIFY
        update();
    }

    public void deleteInProgressTicket(int id) {
        Optional<Ticket> ticketOptional = inProgressTicketList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if(ticketOptional.isPresent()){
            inProgressTicketList.remove(ticketOptional.get());
            ArrayList<Ticket> ticketsToSubmit = new ArrayList<>(inProgressTicketList);
            inProgressTickets.setValue(ticketsToSubmit);
            //NOTIFY
            update();
        }
    }

    //DONE
    public void filterDoneTickets(String filter){
        List<Ticket> filteredList = doneTicketList.stream().filter(ticket -> ticket.getTitle().toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList());
        doneTickets.setValue(filteredList);
    }

    public void addDoneTicket(Ticket ticket){
        ticket.setTicketStatus(TicketStatus.DONE);

        doneTicketList.add(ticket);
        ArrayList<Ticket> ticketsToSubmit = new ArrayList<>(doneTicketList);
        doneTickets.setValue(ticketsToSubmit);
        //NOTIFY
        update();
    }


    public void updateTicketIncrement(int id){
        Optional<Ticket> ticketOptional = ticketList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if(ticketOptional.isPresent()){
            Ticket ticket = ticketOptional.get();
            ticket.setLoggedTime(ticket.getLoggedTime() + 1);
        }
        ArrayList<Ticket> ticketsToSubmitTodo = new ArrayList<>(todoTicketList);
        inProgressTickets.setValue(ticketsToSubmitTodo);
        ArrayList<Ticket> ticketsToSubmitInProgress = new ArrayList<>(inProgressTicketList);
        inProgressTickets.setValue(ticketsToSubmitInProgress);
        ArrayList<Ticket> ticketsToSubmitDone = new ArrayList<>(doneTicketList);
        inProgressTickets.setValue(ticketsToSubmitDone);
        update();
    }

    public void updateTicketDecrement(int id){
        Optional<Ticket> ticketOptional = ticketList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if(ticketOptional.isPresent()){
            Ticket ticket = ticketOptional.get();
            ticket.setLoggedTime(ticket.getLoggedTime() - 1);
        }
        ArrayList<Ticket> ticketsToSubmitTodo = new ArrayList<>(todoTicketList);
        inProgressTickets.setValue(ticketsToSubmitTodo);
        ArrayList<Ticket> ticketsToSubmitInProgress = new ArrayList<>(inProgressTicketList);
        inProgressTickets.setValue(ticketsToSubmitInProgress);
        ArrayList<Ticket> ticketsToSubmitDone = new ArrayList<>(doneTicketList);
        inProgressTickets.setValue(ticketsToSubmitDone);
        update();
    }

    public void voidUpdateTicket(int id, TicketType ticketType, TicketPriority ticketPriority, int est, String title, String desc) {
        Optional<Ticket> ticketOptional = ticketList.stream().filter(ticket -> ticket.getId() == id).findFirst();
        if(ticketOptional.isPresent()){
            Ticket ticket = ticketOptional.get();
            ticket.setTicketType(ticketType);
            ticket.setTicketPriority(ticketPriority);
            ticket.setEstimation(est);
            ticket.setTitle(title);
            ticket.setDescription(desc);
        }

        ArrayList<Ticket> ticketsToSubmitTodo = new ArrayList<>(todoTicketList);
        inProgressTickets.setValue(ticketsToSubmitTodo);
        ArrayList<Ticket> ticketsToSubmitInProgress = new ArrayList<>(inProgressTicketList);
        inProgressTickets.setValue(ticketsToSubmitInProgress);
        ArrayList<Ticket> ticketsToSubmitDone = new ArrayList<>(doneTicketList);
        inProgressTickets.setValue(ticketsToSubmitDone);
        update();
    }
}
