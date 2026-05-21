import java.util.*;

public class BookTicketsHere {

    public static void bookTicket(Passenger p) {
        
        TicketSystem booker = new TicketSystem();

        if(TicketSystem.availableWaiting == 0) {
            System.out.println("No more tickets available");
        }

        if((p.berthPreference.equals("L") && TicketSystem.availableLowerBerths > 0) ||
        (p.berthPreference.equals("M") && TicketSystem.availableMiddleBerths > 0) ||
        (p.berthPreference.equals("U") && TicketSystem.availableUpperBerths > 0)) 
        {
            System.out.println("\nPreferred Berth available");
            // add age logic

            if(p.berthPreference.equals("L")) {
                
                booker.bookTicket(p, (TicketSystem.lowerBerthPositions.get(0)), "L");
                TicketSystem.lowerBerthPositions.remove(0);
                TicketSystem.availableLowerBerths--;
            } 
            
            else if(p.berthPreference.equals("M")) {
                
                booker.bookTicket(p, (TicketSystem.middleBerthPositions.get(0)), "M");
                TicketSystem.middleBerthPositions.remove(0);
                TicketSystem.availableMiddleBerths--;
            }

            else if(p.berthPreference.equals("U")) {
                
                booker.bookTicket(p, (TicketSystem.upperBerthPositions.get(0)), "U");
                TicketSystem.upperBerthPositions.remove(0);
                TicketSystem.availableUpperBerths--;
            }

        }
        else if(TicketSystem.availableLowerBerths > 0) {
            System.out.println("Lower berth is given");
            booker.bookTicket(p, (TicketSystem.lowerBerthPositions.get(0)), "L");
            TicketSystem.lowerBerthPositions.remove(0);
            TicketSystem.availableLowerBerths--;
        }
        
        else if(TicketSystem.availableMiddleBerths > 0) {
            System.out.println("Middle berth is given");
            booker.bookTicket(p, (TicketSystem.middleBerthPositions.get(0)), "M");
            TicketSystem.middleBerthPositions.remove(0);
            TicketSystem.availableMiddleBerths--;
        }

        else if(TicketSystem.availableUpperBerths > 0) {
            System.out.println("Upper berth is given ");
            booker.bookTicket(p, (TicketSystem.upperBerthPositions.get(0)), "U");
            TicketSystem.upperBerthPositions.remove(0);
            TicketSystem.availableUpperBerths--;
        }

        else if(TicketSystem.racPositions.size() > 0) {
            System.out.println("Moved to RAC list");
            booker.addToRac(p, (TicketSystem.racPositions.get(0)), "RAC");
        }

        else if(TicketSystem.waitingListPositions.size() > 0) {
            System.out.println("Moving to waiting list");
            booker.addToWaitingList(p, (TicketSystem.waitingListPositions.get(0)), "WL");
        }

        
    }


    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean loop = true;
        
        while(loop)
        {
            System.out.println("\nBook your Tickets! \n 1. Book Ticket \n 2. Cancel Ticket \n 3. See Booked Tickets \n 4. See Available tickets \n 5. Exit");
            int choice = s.nextInt();
            switch(choice)
            {
                case 1:
                    {
                        System.out.print("Enter passenger name: ");
                        String name = s.next();
                        System.out.print("Enter the age: ");
                        int age = s.nextInt();
                        System.out.print("Enter the preference(L,U,M): ");
                        String berthPreference = s.next();
                        Passenger passenger = new Passenger(name, age, berthPreference);
            
                        bookTicket(passenger);
                        break;
                    }
                case 2:
                    {
                        TicketSystem c = new TicketSystem();
                        System.out.print("Enter the passenger Id to cancel: ");
                        int id = s.nextInt();
                        c.cancelTicket(id);
                        System.out.println("Ticket cancelled successfully");
                        break;

                    }
                case 3:
                    {
                        TicketSystem t = new TicketSystem();
                        t.printBooked();
                        break;
                    }
                case 4:
                    {   
                        TicketSystem t = new TicketSystem();
                        t.printAvailable();
                        break;
                    }

                case 5:
                    {  
                        System.out.println("Thank you for booking your tickets \nExiting...");
                        loop = false;
                        break;
                    }
            }
        }
    }
}