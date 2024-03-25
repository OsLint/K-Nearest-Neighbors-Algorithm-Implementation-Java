import java.util.Scanner;

public class DataInput {

    private final Scanner scanner;

    public DataInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printPromt() {
        System.out.println("Wpisz wektor atrybutów(wpisz tab pomiędzy) a następnie prawidłową klasyfikacje");
        System.out.println("Albo koniec aby zakończyć program!");
    }

    public String getInput() throws Exception {
        String input;
        input = scanner.nextLine();
        if(input.equalsIgnoreCase("")){
            throw new Exception("Null Input");
        }
        if (input.equalsIgnoreCase("koniec")) {
            throw new Exception("Zakończono program");
        } else {
            return input;
        }
    }


}
