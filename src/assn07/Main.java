package assn07;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String,String> passwordManager = new PasswordManager<>();

        // your code below

        System.out.println("Enter Master Password");
        String input = scanner.nextLine();

        while (!(passwordManager.checkMasterPassword(input))){
            System.out.println("Enter Master Password");
            input = scanner.nextLine();
        }


        input = scanner.nextLine();

        while (!(input.equals("Exit"))){

            if (input.equals("New password")){

                String website = scanner.nextLine();
                String password = scanner.nextLine();
                passwordManager.put(website, password);
                System.out.println("New password added");
            }

            else if (input.equals("Get password")){

                String website = scanner.nextLine();
                String password = passwordManager.get(website);
                if (password == null){
                    System.out.println("Account does not exist");
                }
                else{
                    System.out.println(password);
                }
            }

            else if (input.equals("Delete account")){
                String website = scanner.nextLine();
                if (passwordManager.remove(website) == null){
                    System.out.println("Account does not exist");
                }
                else{
                    System.out.println("Account deleted");
                }
            }

            else if (input.equals("Check duplicate password")){
                String password = scanner.nextLine();
                if (passwordManager.checkDuplicate(password).isEmpty()){
                    System.out.println("No account uses that password");
                }
                else{
                    System.out.println("Websites using that password:");
                    for (String website: passwordManager.checkDuplicate(password)){
                        System.out.println(website);
                    }
                }
            }

            else if (input.equals("Get accounts")){
                System.out.println("Your accounts:");
                for (String website: passwordManager.keySet()){
                    System.out.println(website);
                }
            }
            else if (input.equals("Generate random password")){
                int len = scanner.nextInt();
                System.out.println(passwordManager.generatesafeRandomPassword(len));
                input = scanner.nextLine();
            }

            else{
                System.out.println("Command not found");
            }
            input = scanner.nextLine();
        }

    }
}
