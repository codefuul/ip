public class Jeffry {
    public static void main(String[] args) {
        String logo = "   _           __    __          _   _ \n"
                + "  (_)   ___   / _|  / _|  _ __  | | | |\n"
                + "  | |  / _ \\ | |_  | |_  | '__| | |_| |\n"
                + " _/ | |  __/ |  _| |  _| | |     \\__, |\n"
                + "|__/   \\___| |_|   |_|   |_|     |___/ \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("--------------------------------------------------");
        System.out.println("Hello! I'm Jeffry.");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------------");

       
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String userInput;
        do {
            userInput = scanner.nextLine();
            System.out.println(userInput);
        } while (!userInput.equals("bye"));
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("--------------------------------------------------");

    }

}
