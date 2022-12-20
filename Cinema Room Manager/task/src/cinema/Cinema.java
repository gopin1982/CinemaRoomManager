package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        System.out.print("> ");
        Scanner scanner = new Scanner(System.in);
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        System.out.print("> ");
        int numSeats = scanner.nextInt();
        System.out.println();

//        String hallSize = totalSeats <= 60 ? "Small" : "Big";
        Boolean evenRows = numRows % 2 == 0 ? true : false;
        float stdPrice = 10;
        float bckPrice = 8;
        float currentIncome = 0;
//        int[][] seatMatrix = new int[numRows][numSeats];
        int[] seatMatrix = new int[] {numRows, numSeats}; //total row and seats
        int[][] selectedMatrix = new int[numRows+1][numSeats+1]; //user selected row and seats
        float[] ticketPrice = new float[] {stdPrice, bckPrice, currentIncome};
        int menuSelection;
        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            System.out.print("> ");
            menuSelection = scanner.nextInt();

            switch (menuSelection) {
                case 1:
                    showSeats(seatMatrix, selectedMatrix);
                    break;
                case 2:
                    buyTicket(seatMatrix, ticketPrice, selectedMatrix);
                    break;
                case 3:
                    dispStats(seatMatrix, ticketPrice, selectedMatrix);
                    break;
                case 0:
                    break;
            }
        } while (menuSelection != 0);

        }
        public static void buyTicket(int[] seatMatrix, float[] ticketPrice, int[][] selectedMatrix) {
            int totalSeats = seatMatrix[0] * seatMatrix[1];
            Scanner scanner = new Scanner(System.in);
            Boolean correctInput = false;
            do {
                System.out.println();
                System.out.println("Enter a row number:");
                System.out.print("> ");
                int selRow = scanner.nextInt();
                System.out.println("Enter a seat number in that row:");
                System.out.print("> ");
//            selectedMatrix[1] = scanner.nextInt();
                int selCol = scanner.nextInt();
                if (selRow > seatMatrix[0] || selCol > seatMatrix[1]) {
                    System.out.printf("%nWrong input!%n");
                    continue;
                } else if (selectedMatrix[selRow][selCol] == 1) {
                    System.out.printf("%nThat ticket has already been purchased!%n");
                    continue;
                } else {
                    for (int i = 1; i <= selectedMatrix[0].length; i++) {
                        for (int j = 1; j <= selectedMatrix[1].length; j++) {
                            if (i == selRow && j == selCol) {
                                selectedMatrix[selRow][selCol] = 1;
                                break;
                            }
                        }
                    }
                    System.out.println();
                    if (totalSeats <= 60) {
                        System.out.printf("Ticket price: $%.0f%n", ticketPrice[0]);
                        ticketPrice[2] += ticketPrice[0];
                    } else if (selRow <= (seatMatrix[0] / 2)) {
                        System.out.printf("Ticket price: $%.0f%n", ticketPrice[0]);
                        ticketPrice[2] += ticketPrice[0];
                    } else {
                        System.out.printf("Ticket price: $%.0f%n", ticketPrice[1]);
                        ticketPrice[2] += ticketPrice[1];
                    }
                    correctInput = true;
                }
            } while (!correctInput);

        }
        public static void showSeats(int[] seatMatrix, int[][] selectedMatrix) {
            System.out.println();
            System.out.println("Cinema:");
            for (int rowNum = 0; rowNum <= seatMatrix[0]; rowNum++) {
                for (int colNum = 0; colNum <= seatMatrix[1]; colNum++) {
                    if (rowNum == 0 && colNum == 0) {
                        System.out.print(" ");
                    } else if (rowNum == 0 && colNum > 0) {
                        System.out.print(" " + colNum);
                    } else if (rowNum > 0 && colNum == 0) {
                        System.out.print(rowNum);
                    } else if (selectedMatrix[rowNum][colNum] == 1) {
                        System.out.print(" B");
                    } else if (rowNum > 0 && colNum > 0) {
                        System.out.print(" S");
                    }
                }
                System.out.println();
            }
        }

        public static void dispStats(int[] seatMatrix, float[] ticketPrice, int[][] selectedMatrix) {
            int numTickets = 0;
            float ticketPercent = 0;
            int totalSeats = seatMatrix[0] * seatMatrix[1];
//            int currIncome = int(ticketPrice[2]);
            for (int i = 0; i < selectedMatrix[0].length; i++) {
                for (int j = 0; j < selectedMatrix[1].length; j++) {
                    if (selectedMatrix[i][j] == 1) {
                        numTickets += 1;
                    }
                }
            }
            System.out.println();
            System.out.printf("Number of purchased tickets: %d%n", numTickets);
            ticketPercent = ((float)numTickets/totalSeats)*100;
            System.out.printf("Percentage: %.2f%%%n", ticketPercent);
            System.out.printf("Current income: $%.0f%n", ticketPrice[2]);
            Boolean evenRows = seatMatrix[0] % 2 == 0 ? true : false;
            String hallSize = totalSeats <= 60 ? "Small" : "Big";
            float totalPrice = hallSize.equals("Small") ? ticketPrice[0]*totalSeats :
                    (evenRows ? (totalSeats*ticketPrice[0]/2) + (totalSeats*ticketPrice[1]/2) :
                            (seatMatrix[1] * ((seatMatrix[0]/2*(ticketPrice[0]+ticketPrice[1]))+ticketPrice[1])));
            System.out.printf("Total income: $%.0f%n", totalPrice);
        }
    }
