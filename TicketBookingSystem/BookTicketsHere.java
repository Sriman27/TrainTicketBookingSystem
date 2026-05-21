import java.util.*;

public class BookTicketsHere {

    public static void bookTicket(Passenger p) {
        
        TicketSystem booker = new TicketSystem();

        if(TicketSystem.availableWaiting == 0) {
            System.out.println("\nNo more tickets available");
        }

        if((p.berthPreference.equals("L") && TicketSystem.availableLowerBerths > 0) ||
        (p.berthPreference.equals("M") && TicketSystem.availableMiddleBerths > 0) ||
        (p.berthPreference.equals("U") && TicketSystem.availableUpperBerths > 0)) 
        {

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
            System.out.println("\nLower berth is given");
            booker.bookTicket(p, (TicketSystem.lowerBerthPositions.get(0)), "L");
            TicketSystem.lowerBerthPositions.remove(0);
            TicketSystem.availableLowerBerths--;
        }
        
        else if(TicketSystem.availableMiddleBerths > 0) {
            System.out.println("\nMiddle berth is given");
            booker.bookTicket(p, (TicketSystem.middleBerthPositions.get(0)), "M");
            TicketSystem.middleBerthPositions.remove(0);
            TicketSystem.availableMiddleBerths--;
        }

        else if(TicketSystem.availableUpperBerths > 0) {
            System.out.println("\nUpper berth is given ");
            booker.bookTicket(p, (TicketSystem.upperBerthPositions.get(0)), "U");
            TicketSystem.upperBerthPositions.remove(0);
            TicketSystem.availableUpperBerths--;
        }

        else if(TicketSystem.racPositions.size() > 0) {
            System.out.println("\nNo tickets available, moving your ticket to RAC list");
            booker.addToRac(p, (TicketSystem.racPositions.get(0)), "RAC");
        }

        else if(TicketSystem.waitingListPositions.size() > 0) {
            System.out.println("\nNo tickets available, moving your ticket to waiting list");
            booker.addToWaitingList(p, (TicketSystem.waitingListPositions.get(0)), "WL");
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean loop = true;
    
        while(loop)
        {   
            System.out.println("\n------ Book your Tickets! ----- \n\n 1. Book Ticket \n 2. Cancel Ticket \n 3. See Booked Tickets \n 4. See Available tickets \n 5. Exit"); 
            TicketSystem c = new TicketSystem();
            int choice = 0;
            System.out.println();

            try {
                choice = s.nextInt();
            } catch(InputMismatchException e) {
                System.out.println("\nOnly 1-5 can be choosen");
                s.next();
            }
            switch(choice)
            {
                case 1:
                    {   
                        System.out.println("----- Passenger details -----");
                        System.out.print("\nEnter passenger name: ");
                        String name = s.next();

                        int age = 0;
                        while (true) {
                            System.out.print("Enter the age: ");
                            try {
                                age = s.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Age must be a number, try again");
                                s.next();
                            }
                        }

                        System.out.print("Enter the preference(L,U,M): ");
                        String berthPreference = s.next();
                        Passenger passenger = new Passenger(name, age, berthPreference);
            
                        bookTicket(passenger);
                        break;
                    }
                case 2:
                    {   
                        if(TicketSystem.passenger.size() != 0){
                            System.out.println("----- Cancel passenger ticket -----");
                            System.out.print("\nEnter the passenger Id to cancel: ");
                            int id = s.nextInt();
                            
                            System.out.println("\nTicket cancelled successfully");
                            c.cancelTicket(id);
                            break;
                        } else {
                            System.out.println("No tickets booked to cancel");
                            break;
                        }
                    }
                case 3:
                    {
                        c.printBooked();
                        break;
                    }
                case 4:
                    {   
                        c.printAvailable();
                        break;
                    }
                case 5:
                    {  
                        System.out.println("Thank you for booking your tickets \nExiting...\n");
                        loop = false;
                        break;
                    }
                default:
                    {
                        System.out.println("Try again(1-5)");
                        break;
                    }
            }
        }
    }
}