import  java.util.Scanner;

public class Cinema {
    private int row; /* count row */
    private int column; /* count places in one row */
    private int currentIncome;
    private char[][] places; /* cinema seats as matrix */

    /* constructor class */
    public Cinema(int row, int column) {
        this.row = row;
        this.column = column;
        this.places = new char[row][column];
    }

    private int getCurrentIncome() {
        return currentIncome;
    }

    /* get cost one ticket depends row */
    private int getCostOneTicket(int allRow, int allColumn, int coordinateRow) {
        int costOneTicket;
        int allPlace = allRow * allColumn;
        if (allPlace < 60 || coordinateRow <= allRow / 2) {
            costOneTicket = 10;
        } else {
            costOneTicket = 8;
        }
        currentIncome += costOneTicket; /* bad code for optimize, currentIncome - global variable */
        return costOneTicket;
    }

    /* if place not buy return true, else false */
    private boolean checkPlace(int coordinateRow, int coordinateColumn) {
        return places[coordinateRow][coordinateColumn] == 'S';
    }

    private boolean checkArrayIndexOutOfBoundsException(int coordinateRow, int coordinateColumn) {
        return ((coordinateRow < 0 || coordinateRow > row - 1) || (coordinateColumn < 0 || coordinateColumn > column - 1));
    }

    private void changeStatusPlace() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seats = scanner.nextInt();
        if (checkArrayIndexOutOfBoundsException(row,seats)) {
            System.out.println("Wrong input!");
            changeStatusPlace(); /* use recursion for repeat method */
            return ;
        }
        if (checkPlace(row,seats)) {
            places[row][seats] = 'B';
        } else {
            System.out.println("That ticket has already been purchased!");
            changeStatusPlace(); /* use recursion for repeat method */
            return ;
        }
        System.out.println();
        System.out.println("Ticket price: $" + getCostOneTicket(this.row - 1, this.column - 1,row));
        System.out.println();
        /* comment
           this.row - 1 and this.column - 1, because array stored header row and column;
         */
   }

    /* init matrix places */
    public void fillingPlaces() {
        for (int j = 0; j < column; j++) {
            places[0][j] = Character.forDigit(j,10);
        }
        for (int i = 0; i < row; i++) {
            places[i][0] = Character.forDigit(i,10);
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                places[i][j] = 'S';
            }
        }
    }

    /* show all matrix */
    private void showPlaces() {
        System.out.println("Cinema: ");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                    continue;
                }
                System.out.print(places[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int getNumberOfPurchasedTicket() {
        int countPurchasedTickets = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (places[i][j] == 'B') {
                    countPurchasedTickets++;
                }
            }
        }
        return  countPurchasedTickets;
    }

    private float getPercentageNumberOfPurchased() {
        int allRow = row - 1; /* because one row and one column stores header */
        int allColumn = column - 1;
        int countPurchasedTicket = getNumberOfPurchasedTicket();
        int allNumberTicket = allRow  * allColumn;
        return  (float) countPurchasedTicket * 100 / allNumberTicket; /* math formula */
    }

    private int getTotalIncome() {
        int allRow = row - 1 ;
        int seatsInRow = column - 1;
        int allSeats = allRow * seatsInRow;
        if (allSeats < 60) {
            return 10 * allSeats;
        } else {
            return 10 * (allRow / 2 * seatsInRow) + 8 * (allRow - allRow / 2 * seatsInRow);
        }
    }

    private void showStatistics() {
        System.out.println("Number of purchased tickets: " + getNumberOfPurchasedTicket());
        System.out.printf("Percentage: %.2f%n",getPercentageNumberOfPurchased());
        System.out.println("Current income: $" + getCurrentIncome());
        System.out.println("Total income: $" + getTotalIncome());
    }

    public void chooseAction() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. exit");
        int choose = scanner.nextInt();
        switch (choose) {
            case 1:
                showPlaces();
                break;
            case 2:
                changeStatusPlace();
                break;
            case 3:
                showStatistics();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("You choose don't correct point of menu");
        }
        System.out.println();
    }

}
