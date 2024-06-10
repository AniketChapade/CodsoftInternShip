import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        
        int[] marks = new int[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            System.out.println("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }

        //For Calculating total marks
        int totalMarks = calculateTotalMarks(marks);
        
        //For Calculating average percentage
        double averagePercentage = calculateAveragePercentage(totalMarks, numSubjects);
        
        //For Calculating grade
        char grade = calculateGrade(averagePercentage);

        //For Displaying results
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
        System.out.println("Grade: " + grade);

        scanner.close();
    }

    private static int calculateTotalMarks(int[] marks) {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    private static double calculateAveragePercentage(int totalMarks, int numSubjects) {
        return (double) totalMarks / numSubjects;
    }

    private static char calculateGrade(double percentage) {
        if (percentage >= 90) {
            return 'A';
        } else if (percentage >= 80) {
            return 'B';
        } else if (percentage >= 70) {
            return 'C';
        } else if (percentage >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }
}
