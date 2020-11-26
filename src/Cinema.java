import  java.util.Scanner;

public class Cinema {
    private int row;
    private int column;
    private char[][] places;

    /* constructor class */
    public Cinema(int row, int column) {
        this.row = row;
        this.column = column;
        this.places = new char[row][column];
    }
    /* get cost one ticket depends row */
    public int getCostOneTicket(int allRow, int allColumn, int coordinateRow) {
        int costOneTicket;
        int allPlace = allRow * allColumn;
        if (allPlace < 60 || coordinateRow <= allRow / 2) {
            costOneTicket = 10;
        } else {
            costOneTicket = 8;
        }
        return costOneTicket;
    }

   public void changeStatusPlace() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seats = scanner.nextInt();
        places[row][seats] = 'B';
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
    public void showPlaces() {
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

    public int getNumberOfPurchasedTicket() {
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

    public float getPercentageNumberOfPurchased() {
        int allRow = row - 1; /* because one row and one column stores header */
        int allColumn = column - 1;
        int countPurchasedTicket = getNumberOfPurchasedTicket();
        int allNumberTicket = allRow  * allColumn;
        return  (float) countPurchasedTicket * 100 / allNumberTicket; /* math formula */
    }

    public void showStatistics() {
        System.out.println("Number of purchased tickets: " + getNumberOfPurchasedTicket());
        System.out.printf("Percentage: %.2f%n",getPercentageNumberOfPurchased());
        System.out.println("Current income: $0");
        System.out.println("Total income: $360");
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
