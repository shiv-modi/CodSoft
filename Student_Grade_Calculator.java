import java.util.Scanner;

/**
 * Student_Grade_Calculator
 */
public class Student_Grade_Calculator {


    public static void Calculator(){

        Scanner sc = new Scanner(System.in);

        int Marks = 0;

        System.out.print("How many subjects are there : ");
        int s = sc.nextInt();
        int i = 0;
        while (i<s) {
            System.out.print("Enter Marks : ");
            int Input =sc.nextInt();
            if(Input>100){
                System.out.println("Invalid Input Try Again");
            }else{
                Marks+=Input;
                i++;
            }
        }
        s = s * 100;
        
        float avg = (float)Marks/s * 100;

        System.out.println("Total Marks : "+Marks);
        System.out.println("Percentage : "+avg+"%");
        
        if(avg<=100 && avg>=90){

            System.out.println("Grade : A Grade");

        }else if (avg<=89 && avg>=80) {

            System.out.println("Grade : B Grade");
            
        }else if (avg<=79 && avg>=70) {
            
            System.out.println("Grade : C Grade");

        }else if (avg<=69 && avg>=60) {
            
            System.out.println("Grade : D Grade");

        }else if (avg<=59 && avg>=35) {
            
            System.out.println("Grade : E Grade");

        }else{
            System.out.println("Grade : F Grade");
        }
        
        sc.close();
    }

    public static void main(String[] args) {
        System.out.println();
        System.out.println("========== Welcome To Student Grade Calculator ===========");
        Calculator();
    }
}