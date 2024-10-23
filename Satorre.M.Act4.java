import java.util.Random;
import java.util.Scanner;

class Student {
    String name;
    int[] grades;
    Student(int numberOfGrades) {
        this.grades = new int[numberOfGrades];
    }

    float Average() {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (float) sum / grades.length;
    }
}

class MidtermACT4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("\n                  ===| SATORRE - Midterm - Lab.Act.#04 |===\n"); // TITLE
        int attempt = 1;
        String answer;

        do {
            System.out.printf("\n|=========================| START OF ATTEMPT NO.%d |===========================|\n", attempt);
            int choiceMenu = Menu(in);
            System.out.print("\n|==============================================================================|\n");
            int noStud, noGrade;
            if (choiceMenu == 3) {
                noStud = 3; noGrade = 5;
                in.nextLine();
            } else {
                noStud = InputNoOfStudAndGrade(in, "STUDENTS");
                noGrade = InputNoOfStudAndGrade(in, "GRADES");
            }

            Student[][] stud = new Student[noStud][noGrade];
            switch (choiceMenu) {
                case 1: ChoiceFirst(in, noStud, noGrade, stud); break;
                case 2: ChoiceSecond(in, noStud, noGrade, stud); break;
                case 3: ChoiceThird(in, noStud, noGrade, stud); break;
                default: break;
            }

            System.out.printf("\n|=========================| END OF ATTEMPT NO.%d |===========================|\n", attempt);
            answer = getAnswerLoop(in);
            System.out.print("|====================================================================================|\n\n");
            attempt++;
        } while (answer.equalsIgnoreCase("yes"));

        System.out.print("                           THANK YOU FOR YOUR TIME\n");
        System.out.printf("                             NO. OF ATTEMPTS: %d", attempt - 1);
        in.close();
    }

    public static int Menu(Scanner in) {
        String[] menu = {"INPUT STUDENTS and GRADES", "AUTO GENERATE GRADES", "3x5 main ACTIVITY"};
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

    public static void ChoiceFirst(Scanner in, int NoStud, int NoGrade, Student[][] stud) {
        System.out.print("\n|---------------------==| 1. INPUT STUDENTS and GRADES |==---------------------|\n");
        DeSign(NoStud,NoGrade);
        InputNameAndGrade(in,stud,false);
        SortingGrade(stud);
        DisplayNameSortedGradeAverage(stud);
    }

    public static void ChoiceSecond(Scanner in, int NoStud, int NoGrade, Student[][] stud) {
        System.out.print("\n|-----------------------==| 2. AUTO GENERATE GRADES |==------------------------|\n");
        DeSign(NoStud,NoGrade);
        InputNameAndGrade(in,stud,true);
        SortingGrade(stud);
        DisplayNameSortedGradeAverage(stud);
    }

    public static void ChoiceThird(Scanner in, int NoStud, int NoGrade, Student[][] stud){
        System.out.print("\n|-------------------------==| 3. 3x5 main ACTIVITY |==-------------------------|\n");
        DeSign(NoStud,NoGrade);
        InputNameAndGrade(in, stud,false);
        SortingGrade(stud);
        DisplayNameSortedGradeAverage(stud);
    }

    public static void DeSign(int noStud , int noGrade) {
        System.out.printf("\n           __________________                   _________________\n" +
                            "           | No of STUDENTS |                   |  No of GRADES |\n" +
                            "           |       %d        |                   |       %d       |\n" +
                            "           |________________|                   |_______________|\n", noStud, noGrade);
                }

    public static int InputNoOfStudAndGrade(Scanner in, String type) {
        int numbers; // VALUE
               while(true) {
                   System.out.printf("  Enter number of %s: ", type);
                   if (in.hasNextInt()) {
                       numbers = in.nextInt();
                       if (numbers < 2 || numbers > 10) {
                           System.out.println("|-= NUMBER 2 to 10 ONLY! =-|");
                       } else {
                           System.out.printf("====----------------------------[ %d %s SAVED ]>>\n", numbers, type);
                           break;
                       }
                   } else {
                       System.out.println("|-= INVALID INPUT, INTEGERS ONLY! =-|");
                       in.next();
                   }
                }
               in.nextLine();
       return numbers;
    }

    public static void InputNameAndGrade(Scanner in, Student[][] stud, boolean checkIfRandomNum) {
        Random random = new Random();
        for (int i = 0; i < stud.length; i++) {
            stud[i][0] = new Student(stud[0].length);
        }

        for (int i = 0; i < stud.length; i++) {
            System.out.println("\n                  --------------------------------   ");
            System.out.printf("|==================| INPUT NAME of STUDENT no.%d |===================|\n", i + 1);

            while (true) {
                System.out.printf("  Student Name no.%d: ", i + 1);
                stud[i][0].name = in.nextLine().trim().toUpperCase(); // AND UPPERCASE TO AUTO UPPERCASE
                boolean check = false;

                if(stud[i][0].name.isEmpty()) {
                    System.out.println("|-= INVALID INPUT, YOU DIDN'T INPUT ANYTHING =-|");
                    continue;
                }

                for (int k = 0; k < stud[i][0].name.length(); k++) {
                    char c = stud[i][0].name.charAt(k); // LOOKING EVERY INDEX
                    if (!Character.isLetter(c) && c !=' ') { // CHECK IF ALL INPUT IS NOT LETTERS
                        check = true;
                    }
                }

                if (!check) {
                    break;
                } else {
                    System.out.println("|-= INVALID INPUT, PLEASE LETTERS ONLY! =-|");
                }
            }

            if (checkIfRandomNum) {
                System.out.printf("\n       ------==| AUTO GENERATE GRADES for %s |==------", stud[i][0].name);
            } else {
                System.out.printf("\n           ------==| ENTER GRADES for %s |==------\n", stud[i][0].name);
            }

            for (int j = 0; j < stud[0].length; j++) {
                if (checkIfRandomNum) {
                    stud[i][0].grades[j] = random.nextInt(31) + 70; // MIN 70, MAX 100
                    System.out.printf("\n|   Grade no.%d: %d", j + 1, stud[i][0].grades[j]); // DISPLAY RANDOM GRADES
                } else {
                    while (true) {
                        System.out.printf("|   Enter grade no.%d: ", j + 1);
                        if (in.hasNextInt()) {
                            stud[i][0].grades[j] = in.nextInt(); // GET GRADES
                            in.nextLine();
                            if (stud[i][0].grades[j] < 70 || stud[i][0].grades[j] > 100) {
                                System.out.println("|-= NUMBER 70 to 100 ONLY! =-|");
                            } else {
                                System.out.printf("====--------------------[ %d SAVED ]>>\n", stud[i][0].grades[j]); // HIGHLIGHT THE ACCEPTED INTEGERS/GRADES
                                break;
                            }
                        } else {
                            System.out.println("|-= INVALID INPUT, INTEGERS ONLY! =-|");
                            in.next();
                        }
                    }
                }
            }
            System.out.println("\n|===================================================================|");
        }
    }

    public static void SortingGrade(Student[][] stud) {
        for (Student[] students : stud) {
            int[] grades = students[0].grades;
            for (int i = 0; i < grades.length - 1; i++) {
                for (int j = 0; j < grades.length - 1 - i; j++) {
                    if (grades[j] > grades[j + 1]) {
                        int temp = grades[j];
                        grades[j] = grades[j + 1];
                        grades[j + 1] = temp;
                    }
                }
            }
        }
    }

    public static void DisplayNameSortedGradeAverage(Student[][] stud) {
        System.out.print("\n                     ------==| OUTPUT |==------\n");
        int i = 1;
        for (Student[] students : stud) {
            System.out.println("|-------------------------------------------------------------------|\n");
            System.out.printf("|--------------------------| STUDENT NO.%d |-------------------------|\n", i);
            System.out.printf("  SORTED Grades for %s: \n", students[0].name );

            for (int grade : students[0].grades) {
                System.out.printf("| %d | ", grade);
            }
            System.out.printf("\n  AVERAGE Grade of %s: %.2f  ", students[0].name , students[0].Average());
            System.out.println();

            i++;
        }
        System.out.println("|-------------------------------------------------------------------|");
    }

    public static String getAnswerLoop(Scanner in) {
        String answer;
        while (true) {
            System.out.print("DO YOU WANT TO TRY AGAIN? (YES/NO): ");
            answer = in.nextLine().trim();
                if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no")) {
                    break;
                } else if (answer.isEmpty()) {
                    System.out.println("|-= INVALID INPUT, YOU DIDN'T INPUT ANYTHING =-|");
                }else {
                    System.out.println("======--- INVALID INPUT! -----");
                }
            }
        return answer;
    }
}

