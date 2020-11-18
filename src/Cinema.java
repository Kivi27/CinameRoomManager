public class Cinema {
    final int row = 8;
    final int column = 9;
    char[][] places  = new char[row][column];

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
}