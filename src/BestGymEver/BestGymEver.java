package BestGymEver;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BestGymEver {
    Path fileIn = Paths.get("personuppgifter.txt");
    String fileOut = "PTfile.txt";
    protected List<Person> personList = new ArrayList<>();
    protected LocalDate todaysDate = LocalDate.now();

    //Loopar genom personList, lägger till aktiva kunder i lista och skriver till PT-filen och ett lämpligt meddelande skrivs ut.
    public void mainProgram() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ange personnummer eller namn: ");
        String searchForMember = scan.nextLine();
        seperateTwoLinesInFile(personList);
        boolean search = false;
        for (Person p : personList) {
            if (p.getName().equalsIgnoreCase(searchForMember) || p.getSocialSecurityNumber().equalsIgnoreCase(searchForMember)) {
                search = true;
                if (checkYearOfPayment(p)) {
                    System.out.println(p.name + activeMember());
                    writeToPTFile(p, fileOut);
                } else if (!checkYearOfPayment(p)) {
                    System.out.println(p.name + notActiveMember());

                }
            }
        }
        if (!search) {
            if (searchForMember.isEmpty())
                System.out.println("Du måste skriva in något");
            else {
                System.out.println(searchForMember + notExistingMember());
            }
        }
    }
    //Metod som skriver till filen
    public void writeToPTFile(Person p, String fileName) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
            writer.write("\n" + p.getName() + ", " + p.getSocialSecurityNumber() + ", " + todaysDate);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Filen kunde inte hittas");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Det gick inte att skriva till filen");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Okänt fel inträffade");
        }
    }

    public String getSocialSecurityFromFile(String i) {
        String[] socialSecurity = i.split(",");
        return socialSecurity[0].trim();
    }

    public String getNameFromFile(String i) {
        String[] name = i.split(",");
        return name[1].trim();
    }

    public int getDateFromFile(String i) {
        int k = Integer.parseInt(i.replace("-", ""));
        return k;
    }
  //Metoden kollar om det är en medlem eller inte
    public boolean checkYearOfPayment(Person p) {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        int todaysDate = Integer.parseInt(dateFormat.format(oneYearAgo));
        return p.getDateForLastPayedFee() > todaysDate;
    }
  //Metoden delar upp raderna i filen och adderar personerna till en lista
    public void seperateTwoLinesInFile(List<Person> personList) {
        try (Scanner s = new Scanner(fileIn)) {
            while (s.hasNext()) {
                String firstLine = "";
                String secondLine = "";
                if (s.hasNext()) {
                    firstLine = s.nextLine();
                    if (s.hasNext()) {
                        secondLine = s.nextLine();


                        String socialSecurityNumber = getSocialSecurityFromFile(firstLine);
                        String name = getNameFromFile(firstLine);
                        int dateOfPayment = getDateFromFile(secondLine);
                        Person p = new Person(name, socialSecurityNumber, dateOfPayment);
                        personList.add(p);

                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Okänt fel inträffade");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Det gick inte att skriva till filen");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Okänt fel inträffade");
        }
    }

    public String activeMember() {
        return " är en nuvarande medlem.";
    }

    public String notActiveMember() {
        return " är en före detta medlem.";
    }

    public String notExistingMember() {
        return " finns inte med i filen.";
    }


    public static void main(String[] args) {
        BestGymEver bg = new BestGymEver();
        bg.mainProgram();

    }
}
    
      
