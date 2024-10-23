import java.util.Scanner;
class SatorreAct2
{
    static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.printf("+------------------========== PRELIM-LAB.ACT.#02 ==========------------------+");
        boolean checking;
        int attempt = 1;
        do {
            System.out.printf("\n\n+-------------------____________ ATTEMPT NO.%d ____________-------------------+\n", attempt);
            int noOfGrade = getNumberOfGrades();

            System.out.printf("_________________\n"+
                            "|  No of Grades |\n" +
                            "|       %d       |\n"+
                            "|_______________|\n\n", noOfGrade);

            int[] grade = new int[noOfGrade];
            in.nextLine();
            String choose = ChooseBSorSS();

            if (choose.equals("BUBBLE")) {
                ChooseBubble(grade,choose);
            } else if (choose.equals("SELECTION")) {
                ChooseSelection(grade,choose);
            }
            System.out.printf("\n=_____________________________ END OF ATTEMPT NO.%d _____________________________=", attempt);

            String choice = Looping();

            checking = choice.equalsIgnoreCase("Yes");



        attempt++;
        }while(checking);
        System.out.print("\n____________________________________________________________________________________________\n");
        System.out.printf("                                         ATTEMPTED: %d\n", attempt - 1);
        System.out.print("\n+________________________________ !THANKYOU FOR YOUR TIME! ________________________________+");
    }

    public static int getNumberOfGrades() {
        System.out.print("Enter no. Of Grades: ");

        if (in.hasNextInt()) {
            int noOfGrade = in.nextInt();
            if (noOfGrade > 1 && noOfGrade <= 10) {
                return noOfGrade;
            } else {
                System.out.println("(-- (BETWEEN 2 AND 10 ONLY!) --)");
            }
        } else {
            System.out.println("(-- (INVALID INPUT, MUST BE AN INTEGER!) --)");
            in.next();
        }
        return getNumberOfGrades();
    }
    ///-------------------------------------------------------------------------------///
    // CHOOSING THE TYPE OF SORTING ---
    public static String ChooseBSorSS(){
        String choice;
        do {
            System.out.print("CHOOSE A SORTING ALGORITHMS ( BUBBLE / SELECTION ): ");
            choice = in.nextLine();

            if ( choice.equalsIgnoreCase("Bubble")) {
                choice = "BUBBLE";
            } else if ( choice.equalsIgnoreCase("Selection")) {
                choice = "SELECTION";
            } else {
                System.out.print("_---- (INVALID INPUT!) ----_\n");
            }
        }while(!choice.equalsIgnoreCase("Selection") && !choice.equalsIgnoreCase("Bubble"));
        return choice;
    }
    ///-------------------------------------------------------------------------------///
    // IF YOU CHOOSE BUBBLE ---
    public static void ChooseBubble(int[] grade, String choose) {
        System.out.print("\n\n+______________________=== BUBBLE SORT ===______________________+");
        System.out.print("\n______________________________________________________________\n");
        UserInput(grade);

        int[] graded = new int[grade.length]; // TO GET THE CLONE VALUE OF GRADES THEN USE IT TO PROCESS SORTING
        for (int i = 0; i < graded.length; i++) {
            graded[i] = grade[i];
        }
        System.out.print("______________________________________________________________\n");
        DisplayUnsorted(grade);
        DisplaySorted(grade,choose);
        System.out.print("______________________________________________________________\n");
        DisplayMaxMin(grade);
        DisplayAvFaPasHon(grade);
        System.out.print("______________________________________________________________\n");
        String choice = ProcessChoice();
            if (choice.equals("YES")) {
                ProcessBubble(graded);
            }
    }
    // IF YOU CHOOSE SELECTION ---
    public static void ChooseSelection(int[] grade, String choose) {
        System.out.print("\n\n+______________________=== SELECTION SORT ===______________________+");
        System.out.print("\n______________________________________________________________\n");
        UserInput(grade);

        int[] graded = new int[grade.length]; // TO GET THE CLONE VALUE OF GRADES THEN USE IT TO PROCESS SORTING
        for (int i = 0; i < graded.length; i++) {
            graded[i] = grade[i];
        }
        System.out.print("______________________________________________________________\n");
        DisplayUnsorted(grade);
        DisplaySorted(grade, choose);
        System.out.print("______________________________________________________________\n");
        DisplayMaxMin(grade);
        DisplayAvFaPasHon(grade);
        System.out.print("______________________________________________________________\n");
        String choice = ProcessChoice();
        if ( choice.equals("YES")) {
            ProcessSelection(graded);
        }
    }
    ///-------------------------------------------------------------------------------///
    // GRADE'S USER INPUT ---
    public static void UserInput(int[] grades) {
        boolean check;
        for ( int i = 0; i < grades.length; i++){

            do {
                System.out.printf("Enter grade No.%d: ", i+1);
                if (in.hasNextInt()) {
                    grades[i] = in.nextInt();
                    if ( grades[i] < 70 || grades[i] > 100) {
                        System.out.print("(-- (BETWEEN 70 AND 100 ONLY!) --)\n");
                        check = true;
                    } else {
                        check = false;
                    }
                } else {
                    System.out.print("(-- (INVALID INPUT, MUST BE AN INTEGERS!) --)\n");
                    check = true;
                    in.next();
                }
            }while(check);
        }
    }

    ///-------------------------------------------------------------------------------///
    // GETTING THE FOR THE GRADES OF BOTH TYPE OF SORT ---
    public static void BubGetSorted(int[] grades) {
        int length = grades.length;
        for ( int i = 0; i < length - 1; i++) {
            for ( int j = 0 ; j < length - 1 - i; j++) {
                if ( grades[j] < grades[j + 1]) {
                    int temp = grades[j];
                    grades[j] = grades[j + 1];
                    grades[j + 1] = temp;
                }
            }
        }
    }
    public static void SelectGetSorted(int[] grades) {
        int length = grades.length;
        for ( int i = 0; i < length - 1; i++) {
            for ( int j = i +1 ; j < length; j++) {
                if ( grades[i] < grades[j]) {
                    int temp = grades[i];
                    grades[i] = grades[j];
                    grades[j] = temp;
                }
            }
        }
    }

    ///-------------------------------------------------------------------------------///
    // DISPLAY THE TYPE OF SORTING ---
    public static void DisplayUnsorted(int[] grades) {
        System.out.print("UNSORTED GRADES: ");
        for (int grade : grades) {
            System.out.printf("%d ", grade);
        }
    }
    public static void DisplaySorted(int[] grades, String choose) {

        if ( choose == "BUBBLE") {
            BubGetSorted(grades);
            System.out.print("\nSORTED GRADES: ");
        }
        else if ( choose == "SELECTION"){
            SelectGetSorted(grades);
            System.out.print("\nSORTED GRADES: ");
        }

        for (int grade : grades) {
            System.out.printf("%d ", grade);
        }
        System.out.println();
    }

    ///-------------------------------------------------------------------------------///
    // DECIDE FOR PROCESS ---
    public static String ProcessChoice() {
        String choice;
        in.nextLine();
        do {
            System.out.print("DO YOU WANT TO SEE THE PROCESS OF SORTING ( YES / NO ): ");
            choice = in.nextLine();

            if ( choice.equalsIgnoreCase("YES")) {
                choice = "YES";
            } else if ( choice.equalsIgnoreCase("NO")) {
                choice = "NO";
            } else {
                System.out.print("+_____ (INVALID INPUT!) _____+\n");
            }
        }while(!choice.equalsIgnoreCase("YES") && !choice.equalsIgnoreCase("NO"));
        return choice;
    }
    ///-------------------------------------------------------------------------------///
    // DISPLAY THE GRADED ARRAY ---
    public static void SortingProcess(int[] grades) {
        for ( int i = 0; i < grades.length; i++) {
            System.out.printf("%d ", grades[i]);
        }
    }
    ///-------------------------------------------------------------------------------///
    // PROCESS OF BOTH SORTING ---
    public static void ProcessBubble(int[] grades) {
        System.out.print("\n_______________________= PROCESSING FOR BUBBLE SORT =_________________________\n");
        boolean swapped;
        int length = grades.length;
        System.out.printf("\n------------------------------------------------------- SWAP NO.%d", 1);
        for ( int i = 0; i < length - 1; i++) {
            swapped = false;

            for ( int j = 0 ; j < length - 1 - i; j++) {
                System.out.printf("\nCHECK GRADES:  ", j + 1);
                SortingProcess(grades);
                if ( grades[j] < grades[j + 1]) {
                    int temp = grades[j];
                    grades[j] = grades[j + 1];
                    grades[j + 1] = temp;

                    swapped = true;
                    System.out.print(" THEN SWAP:  ");
                    SortingProcess(grades);
                }
            }
            if (!swapped) {
                break;
            }
            System.out.printf("\n------------------------------------------------------- SWAP NO.%d", i + 2);
        }
        System.out.print("\n\nFINAL SORTING: ");
        DisplaySorted(grades, null);
    }
    public static void ProcessSelection(int[] grades) {
        System.out.print("\n_______________________= PROCESSING FOR SELECTION SORT =_________________________\n");
        int length = grades.length;
        for ( int i = 0; i < length - 1; i++) {
            for ( int j = i +1 ; j < length; j++) {
                System.out.printf("\nCHECK GRADES:  ", j + 1);
                SortingProcess(grades);
                if ( grades[i] < grades[j]) {
                    int temp = grades[i];
                    grades[i] = grades[j];
                    grades[j] = temp;

                    System.out.print(" THEN SWAP:  ");
                    SortingProcess(grades);
                }
            }
            System.out.print("\n-------------------------------------------------------");
        }
        System.out.print("\n\nFINAL SORTING: ");
        DisplaySorted(grades, null);
    }

    ///-------------------------------------------------------------------------------///
    // DISPLAY OF MIN, MAX, AND AVERAGE
    public static void DisplayMaxMin( int[] grade) {
        int min = grade[0];
        int max = grade[grade.length-1];
        System.out.printf("MINIMUM GRADE: %d\n", min);
        System.out.printf("MAXIMUM GRADE: %d\n", max);
    }

    ///-------------------------------------------------------------------------------///
    // GET THE AVERAGE and Failed or Pass and Academic award ---
    public static void DisplayAvFaPasHon(int[] grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        float finalAve =(float) sum / grades.length;

        int toWholeNum = (int)finalAve;
        float check = toWholeNum - finalAve;

        if ( check == 0) {
            System.out.printf("TOTAL AVERAGE: %.0f   (THE TOTAL AVERAGE IS WHOLE NUMBER) \n", finalAve);
        } else {
            System.out.printf("TOTAL AVERAGE: %.2f\n", finalAve);
        }
        GetForPandHonors(finalAve);
    }
    public static void GetForPandHonors(float average) {
            if ( average < 75 ) {
                System.out.print("\n --( YOU FAILED! )--\n");
            } else {
                if ( average >= 91)
                System.out.print("\n  -( YOU PASSED and With ACADEMIC AWARD! )-\n");
                else {
                    System.out.print("\n -( YOU PASSED! )-\n");
                }
            }
    }

    ///-------------------------------------------------------------------------------///
    public static String Looping() {
        boolean loop;
        String choice;
        do {
            System.out.print("\nDO YOU WANT TO INPUT AGAIN? ( YES / NO ): ");
            choice = in.nextLine();
            if ( !choice.equalsIgnoreCase("Yes") && !choice.equalsIgnoreCase("No") ) {
                System.out.print("+_____ ( INVALID INPUT! ) _____+");
                loop = true;
            } else {
                loop = false;
            }
        }while(loop);

        return choice;
    }


}