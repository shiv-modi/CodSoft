import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/banking_system";
    private static final String username = "root";
    private static final String password = "";


    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner scanner = new Scanner(System.in);
            connection.setAutoCommit(false);

            System.out.println("WELCOME TO ATM MACHINE");
            System.out.print("Enter your E-mail : ");
            String email = scanner.nextLine();
            System.out.print("Enter your Password : ");
            String password = scanner.nextLine();

            if (userExist(email, password, connection)) {
                main_menu(connection, scanner, email);
            } else {
                System.out.println("Logging Failed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main_menu(Connection connection, Scanner scanner, String email) throws SQLException{
        try {
            long account_number = AccountExist(connection, scanner, email);
            if (account_number != 0) {
                System.out.println("1. Withdraw money");
                System.out.println("2. Deposit Money");
                System.out.println("3. Check Balance");
                System.out.println("4. Exist");
                System.out.print("Enter your Choice :");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1 : withdraw(account_number,connection,scanner);
                        main_menu(connection, scanner, email);
                        break;
                    case 2 : deposit(account_number,connection,scanner);
                        main_menu(connection, scanner, email);
                        break;
                    case 3 : double balance = checkbalance(account_number,connection);
                        System.out.println("Your balance is "+balance);
                        main_menu(connection, scanner, email);
                        break;
                    case 4 : exist();
                        break;

                    default:
                        System.out.println("Invalid Choice");

                }
            }else {
                System.out.println("Account Not Exist!");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static boolean userExist(String email, String password, Connection connection) {

        try {
            String query = "select * from user where email = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static long AccountExist(Connection connection, Scanner scanner, String email) throws SQLException {
        try {
            String query = "select account_number from accounts where email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                return resultSet.getLong("account_number");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static void withdraw(long account_number , Connection connection , Scanner scanner) throws SQLException{

        String query = "UPDATE accounts SET balance = balance - ? WHERE account_number = ? and security_pin = ?";

    try {
        System.out.print("Enter Withdraw Amount : ");
        Double amount = scanner.nextDouble();
        System.out.print("Enter your Security Pin : ");
        int pin = scanner.nextInt();
        double balance = checkbalance(account_number,connection);
        if(amount<=balance){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1,amount);
            preparedStatement.setLong(2,account_number);
            preparedStatement.setInt(3,pin);
            int RowAffect = preparedStatement.executeUpdate();
            if(RowAffect>0) {
                connection.commit();
                System.out.println("Withdraw Successfully!");
            }else{
                connection.rollback();
                System.out.println("Withdraw Failed!");



            }
        }else {
            System.out.println("Insufficient Balance");
        }
    }catch (SQLException e){
        System.out.println(e.getMessage());
    }
    }


    public static void deposit(long account_number , Connection connection , Scanner scanner) throws SQLException{

        String query = "UPDATE accounts SET balance = balance + ? WHERE account_number = ? and security_pin = ?";

        try {
            System.out.print("Enter Deposit Amount : ");
            Double amount = scanner.nextDouble();
            System.out.print("Enter your Security Pin : ");
            int pin = scanner.nextInt();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setDouble(1,amount);
                preparedStatement.setDouble(2,account_number);
                preparedStatement.setInt(3,pin);

            int RowAffect = preparedStatement.executeUpdate();
                if(RowAffect>0) {
                    connection.commit();
                    System.out.println("Deposit Successfully!");
                }else{
                    connection.rollback();
                    System.out.println("Deposits Failed!");
                }
            }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public static double checkbalance(long account_number,Connection connection){
        String query = "select balance from accounts where account_number = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1,account_number);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
            while (resultSet.next()){
                return resultSet.getDouble("balance");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public static void exist(){
        try {
            System.out.print("Exist");
            int i = 0;
            while (i<5){
                System.out.print(".");
                Thread.sleep(450);
                i++;
            }
            System.out.println("!");
            return;

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}