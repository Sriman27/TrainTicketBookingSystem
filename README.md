# Train Ticket Booking System
![Java Version](https://img.shields.io/badge/Java-21%20LTS-blue?style=flat-square)

An application for booking railway ticket reservation system with four functionalities 
- Book
- Cancel
- Print booked tickets
- Print available tickets

There are a total of 63 berths for 63 confirmed tickets, 9 berths for 18 RAC tickets and 10 tickets in waiting-list. If the waiting-list ticket count goes above 10, print as 'No tickets available'. The following passenger details shall be obtained from the user.
- Name
- Age
- Berth Preference

The tickets should not be allocated for children below age 5.But, their details should be stored. Side-lower berths should be allocated for RAC passengers. Conditions for cancelling: Whenever a ticket is cancelled, a ticket from RAC should be confirmed and a waiting-list ticket should move to RAC. Conditions for printing booked tickets: Print all the tickets that are filled along with the passenger details and at the end, print the total number of tickets that are filled. Conditions for printing available tickets: Print all the tickets that are unoccupied and at the end, print the total number of tickets that are unoccupied
