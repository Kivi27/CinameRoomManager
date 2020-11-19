import java.util.Scanner;

 class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = scanner.nextInt();
        Cinema cinema = new Cinema(rows + 1,seatsInRow + 1); //because array start is 0
        cinema.fillingPlaces();
        cinema.showPlaces();
        cinema.changeStatusPlace();
        cinema.showPlaces();
    }
}
