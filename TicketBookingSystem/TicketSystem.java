import java.util.*;

public class TicketSystem {
    static int availableLowerBerths = 21;
    static int availableUpperBerths = 21;
    static int availableMiddleBerths = 21;
    static int availableRac = 18;
    static int availableWaiting = 10;

    List<Integer> bookedTickets = new ArrayList<>();
    static Queue<Integer> waitingList = new LinkedList<>();
    static Queue<Integer> racList = new LinkedList<>();

    static List<Integer> lowerBerthPositions = new ArrayList<>();
    static List<Integer> middleBerthPositions = new ArrayList<>();
    static List<Integer> upperBerthPositions = new ArrayList<>();
    static List<Integer> racPositions = new ArrayList<>();
    static List<Integer> waitingListPositions = new ArrayList<>();

    static {
        for (int i = 1; i <= 21; i++) {
            lowerBerthPositions.add(i);
        }
        for (int i = 1; i <= 21; i++) {
            middleBerthPositions.add(i);
        }
        for (int i = 1; i <= 21; i++) {
            upperBerthPositions.add(i);
        }
        for (int i = 1; i <= 18; i++) {
            racPositions.add(i);
        }
        for (int i = 1; i <= 10; i++) {
            waitingListPositions.add(i);
        }
    }

    static Map<Integer, Passenger> passenger = new HashMap<>();

    public void bookTicket(Passenger p, int berthInfo, String allotedBerth) {
        p.number = berthInfo;
        p.alloted = allotedBerth;

        passenger.put(p.passengerId, p);
        bookedTickets.add(p.passengerId);
        System.out.println("Ticket has been booked");
    }

    public void addToRac(Passenger p, int racInfo, String allotedRac) {
        p.number = racInfo;
        p.alloted = allotedRac;

        passenger.put(p.passengerId, p);
        racList.add(p.passengerId);
        availableRac--;

        racPositions.remove(0);

        System.out.println("Ticket moved to RAC");
    }

    public void addToWaitingList(Passenger p, int waitingListInfo, String allotedWaitingList) {
        p.number = waitingListInfo;
        p.alloted = allotedWaitingList;

        passenger.put(p.passengerId, p);
        waitingList.add(p.passengerId);
        availableWaiting--;

        waitingListPositions.remove(0);

        System.out.println("Ticket moved to waiting list");
    }

    public void cancelTicket(int passengerId) {
        Passenger p = passenger.get(passengerId);
        passenger.remove(Integer.valueOf(passengerId));

        bookedTickets.remove(Integer.valueOf(passengerId));
        int positionBooked = p.number; // take the booked position which is now free

        if(p.alloted.equals("L")) {
            availableLowerBerths++;
            lowerBerthPositions.add(positionBooked);
        }
        else if(p.alloted.equals("M")) {
            availableMiddleBerths++;
            middleBerthPositions.add(positionBooked);
        }
        else if(p.alloted.equals("U")) {
            availableUpperBerths++;
            upperBerthPositions.add(positionBooked);
        }

        if(racList.size() > 0) {
            Passenger passengerFromRac = passenger.get(racList.poll());
            int positionRac = passengerFromRac.number;
            racPositions.add(positionRac);
            racList.remove(passengerFromRac.passengerId);
            availableRac++;

            if(waitingList.size() > 0) {
                Passenger passengerFromWL = passenger.get(waitingList.poll());
                int positionWL = passengerFromWL.number;
                waitingListPositions.add(positionWL);
                waitingList.remove(passengerFromWL.passengerId);
                availableWaiting++;

                passengerFromWL.number = racPositions.get(0);
                passengerFromWL.alloted = "RAC";
                racPositions.remove(0);
                racList.add(passengerFromWL.passengerId);
                availableRac--;
            }
            BookTicketsHere.bookTicket(passengerFromRac);
        }
    }

    public void printAvailable() {
        System.out.println("Available upper berths: " + availableUpperBerths);
        System.out.println("Available middle berths: " + availableMiddleBerths);
        System.out.println("Available lower berths: " + availableLowerBerths);
        System.out.println("Available RAC tickets: " + availableRac);
        System.out.println("Available waiting list tickets: " + availableWaiting);
    }

    public void printBooked() {
        if(passenger.size() != 0) {
            for(Passenger p : passenger.values()) {
                System.out.println("Passenger name: " + p.name);
                System.out.println("Passenger age: " + p.age);
                System.out.println("Passenger id: " + p.passengerId);
                System.out.println("Seat alloted: " + p.alloted +""+ p.number);
            }
        } else{
            System.out.println("No tickets booked");
        }   
    }


}
