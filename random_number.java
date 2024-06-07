package Array;

import java.util.Random;
import java.util.Scanner;

/**
 * random_number
 */
public class random_number {

    public static int NumberGame(){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String v = "Y";
        int score = 0;
        while (v.equalsIgnoreCase("Y")) {
        int RandNum = random.nextInt(100);
        int count = 0 ;
        while (true) {
            // Testing Purpose
            System.out.println(RandNum);
            System.out.print("Enter Number : ");
            int User = scanner.nextInt();
            if (User>100) {
                System.out.println("Number Between 1 to 100");
                break;
            }
            if(User!=RandNum){
                if(User<=RandNum){
                    System.out.println("Too Low");
                }else{
                    System.out.println("Too High");
                }
                count++;
                System.out.println("Total Attempts left : "+(3-count));
                score-=2;
            }
            else{
                System.out.println("congratulation");
                score+=5;
                break;
            }
            if(count>=3){
                System.out.println("No Attempts left");
                break;
                
            }
        }
        System.out.println("Do you want to play again say Y or N :");
        v = scanner.next();
        
    }
        scanner.close();  
        return score;
    }

    public static void main(String[] args) {
        System.out.println("=============== WELCOME TO NUMBER GAME ================");
        int score = NumberGame();
        if(score<=0){
            score = 0;
        }
        System.out.println("Your Score : "+score);

    }
}

