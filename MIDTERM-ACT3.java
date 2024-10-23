import java.util.Random;
import java.util.Scanner;

class SATORRE {
    public static void main(String[] args) {
        Scanner in = new  Scanner(System.in);
        System.out.print("\n                  ===| SATORRE - Midterm - Lab.Act.#03 |===\n"); // NAME AND TITLE OF ACTIVITY
        int attempt = 1;
        String answer;

      do {
          System.out.printf("\n|=========================| START OF ATTEMPT NO.%d |===========================|\n", attempt);
          int menuChoice = Menu(in);

          switch (menuChoice) {
              case 1:
                  ChoiceONE(in);
                  break;
              case 2:
                  ChoiceTWO(in);
                  break;
              case 3:
                  ChoiceTHREE(in);
                  break;
              default:
                  break;
          }

          System.out.printf("\n|=========================| END OF ATTEMPT NO.%d |===========================|\n", attempt);
          answer = getAnswerLoop(in);

          System.out.print("|====================================================================================|\n\n");
          System.out.println();
          attempt++;
      }while(answer.equalsIgnoreCase("yes"));
          System.out.print("                           THANKYOU FOR YOUR TIME\n");
          System.out.printf("                             NO. OF ATTEMPT: %d", attempt -1);
    }
    public static int getRowAndCol(Scanner in, String type) {
        int value = 0; // NUMBER OF ROW / COL
        while (value == 0) {
            System.out.printf("Enter number of %s (2 to 6): ", type);
            if (in.hasNextInt()) {
                value = in.nextInt();
                if (value < 2 || value > 6) {
                    System.out.println("=== NUMBER 2 TO 6 ONLY! -----");
                    value = 0;
                } else {
                    System.out.printf("====----------------------------[ %d %s SAVED ]>>\n", value, type);
                }
            } else {
                System.out.println("=== INVALID INPUT. PLEASE ENTER AN INTEGER. -----");
                in.next();
            }
            in.nextLine();
        }
        return value;
    }

    public static void Header(int rows, int columns) {
        System.out.printf("\n               _________________            _________________\n" +
                          "               |  No of ROWS   |            | No of COLUMNS |\n" +
                          "               |       %d       |            |       %d       |\n" +
                          "               |_______________|            |_______________|\n\n", rows, columns);
    }

    public static int Menu(Scanner in) {
        String[] menu = {"INPUT INTEGERS", "AUTO GENERATE INTEGERS", "3x4 main ACTIVITY"};
        int choice = 0;
        System.out.print("MENU: \n");
        for (int i = 0; i < menu.length; i++) {
            System.out.printf("%d. %s\n", i + 1, menu[i]);
        }
        while (choice == 0) {
            System.out.print("Choose one: ");
            if (in.hasNextInt()) {
                choice = in.nextInt();
                if (choice < 1 || choice > 3) {
                    System.out.println("=== THE NUMBER YOU ENTERED IS NOT ON THE MENU! -----");
                    choice = 0;
                }
            } else {
                System.out.println("=== INVALID INPUT. PLEASE ENTER AN INTEGER. -----");
                in.next();
            }
    }
      return choice;
    }

    public static void ChoiceONE(Scanner in) {
        System.out.print("\n|=========================== 1. INPUT INTEGERS ===========================|\n");
        int rows = getRowAndCol(in, "ROWS");
        int columns = getRowAndCol(in, "COLUMNS");

        int[][] numbers = new int[rows][columns];
        Header(rows, columns);
        System.out.print("|============================= ENTER A INTEGERS ===========================|\n");
        InputIntegers(in, numbers);
        DisplayIntegers(numbers);
        System.out.print("\n|================================== OUTPUT =================================|\n");
        FindDisplayMaxAndRC(numbers);
        in.nextLine();
    }

    public static void ChoiceTWO(Scanner in) {
        System.out.print("\n|======================= 2. AUTO GENERATE INTEGERS =======================|\n");
        int rows = getRowAndCol(in, "ROWS");
        int columns = getRowAndCol(in, "COLUMNS");
        int[][] numbers = new int[rows][columns];
        Header(rows, columns);
        AutoGenerateIntegers(numbers);
        System.out.print("|============================================================================|\n");
        DisplayIntegers(numbers);
        System.out.print("\n|================================== OUTPUT =================================|\n");
        FindDisplayMaxAndRC(numbers);
    }

    public static void ChoiceTHREE(Scanner in) {
        int[][] integers = new int[3][4];
        System.out.print("\n|========================= 3. 3x4 main ACTIVITY ==========================|\n");
        Header(3, 4);
        System.out.print("|============================= ENTER A INTEGERS ===========================|\n");
        InputIntegers(in, integers);
        DisplayIntegers(integers);
        System.out.print("\n|================================== OUTPUT =================================|\n");
        FindDisplayMaxAndRC(integers);
        in.nextLine();
    }

    public static void InputIntegers(Scanner in, int[][] integers) {
        for (int i = 0; i < integers.length; i++) {
            for (int j = 0; j < integers[0].length; j++) {
                while (true) {
                    System.out.printf(" ROW %d, COL %d: ",i + 1, j + 1);
                    if (in.hasNextInt()) {
                        integers[i][j] = in.nextInt(); // GET INTEGERS
                        if (integers[i][j] < 1 || integers[i][j] > 100) {
                            System.out.println("=== NUMBER 1 TO 100 ONLY! -----");
                        } else {
                            System.out.printf("====--------------[ %d SAVED ]>>\n", integers[i][j]); // HIGHLIGHT THE ACCEPTED INTEGERS
                            break;
                        }
                    } else {
                        System.out.println("=== INVALID INPUT. PLEASE ENTER AN INTEGER. -----");
                        in.next();
                    }
                }
            }
            System.out.print("|=======================================================|\n");
        }
    }

    public static void AutoGenerateIntegers(int[][] integers) {
        Random rand = new Random();
        for (int i = 0; i < integers.length; i++) {
            for (int j = 0; j < integers[0].length; j++) {
                integers[i][j] = rand.nextInt(100) + 1;
            }
        }
    }

    public static void DisplayIntegers(int[][] integers) {
        System.out.print("\n              ---- DISPLAY INTEGERS ----\n");
        System.out.println();
        System.out.printf(" %6s", "lance");
        for (int k = 0; k < integers[0].length; k++) {
            System.out.printf("COL %-4d", k + 1);
        }
        for (int i = 0; i < integers.length; i++) {
            System.out.printf("\n ROW %d", i + 1);
                for (int j = 0; j < integers[0].length; j++) {
                    System.out.printf("  %-4d |", integers[i][j]);
                }
                System.out.println();

            System.out.printf("%-3s", " ");
                for (int l = 0; l < integers[0].length; l++) {
                    System.out.printf(" %-2s ----", "");
                }
        }
        System.out.println();
    }

    public static void FindDisplayMaxAndRC(int[][] integers) {
        int max = 0;
        int LocRow = 0; // LOCATION OF ROW
        int LocCol = 0; // LOCATION OF COLUMNS
        for (int i = 0; i < integers.length; i++) {
            for (int j = 0; j < integers[0].length; j++) {
                if ( max <= integers[i][j]) {
                    LocRow = i + 1;
                    LocCol = j + 1;
                    max = integers[i][j];
                }
            }
        }
        System.out.printf(" MAXIMUM NO.: %d\n" +
                          " ROW: %d, COL: %d", max, LocRow, LocCol);
    }

    public static String getAnswerLoop(Scanner in) {
        String answer;
        while (true) {
            System.out.print("DO YOU WANT TO TRY AGAIN? (YES/NO): ");
            answer = in.nextLine().trim();
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) {
                break;
            } else {
                System.out.println("======--- INVALID INPUT! -----");
            }
        }
        return answer;
    }
}

