import BestGymEver.BestGymEver;
import BestGymEver.Person;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BestGymEverTest {
    String fileName = "C:\\Users\\johns\\IdeaProjects\\BestGymEver\\test\\personUppgifterTest.txt";
    BestGymEver b = new BestGymEver();
    protected LocalDate todaysDate = LocalDate.now();
    protected LocalDate localDateThreeHundredDays = todaysDate.minusDays(300);
    protected DateTimeFormatter dateTimeF = DateTimeFormatter.ofPattern("yyyyMMdd");
    protected int dateThreeHundredDays = Integer.parseInt(dateTimeF.format(localDateThreeHundredDays));
    Person p1 = new Person("Johan", "9603030214", 20230514);
    Person p2 = new Person("Pelle", "6004021565", dateThreeHundredDays);
    Person p3 = new Person("Lena", "9003050214", 20200514);


    @Test
    public void getDataTest() {

        String expectedName = "Johan";
        String actualName = p1.getName();
        assertEquals(expectedName, actualName);

        String expectedSocialSecurityNumber = "9603030214";
        String actualSocialSecurityNumber = p1.getSocialSecurityNumber();
        assertEquals(expectedSocialSecurityNumber, actualSocialSecurityNumber);

        int expectedDateForLastPayedFee = 20230514;
        int actualDateForLastPayedFee = p1.getDateForLastPayedFee();
        assertEquals(expectedDateForLastPayedFee, actualDateForLastPayedFee);

    }

    //Testar utifrån olika index i min lista av personer så rätt data kommer ut
    @Test
    public void readDataTest() {
        List<Person> personList = new ArrayList<>();
        b.seperateTwoLinesInFile(personList);
        assert (personList.get(0).getSocialSecurityNumber().equals("7703021234"));
        assert (personList.get(0).getName().equals("Alhambra Aromes"));
        assert (personList.get(0).getDateForLastPayedFee() == 20230701);
        assert (personList.get(4).getSocialSecurityNumber().equals("7605021234"));
        assert (personList.get(4).getName().equals("Elmer Ekorrsson"));
        assert (personList.get(4).getDateForLastPayedFee() == 20221107);
        assert (personList.size() == 14);
    }
    
    //Testar metoden som kollar om en person är medlem eller ej
    @Test
    public void memberOrNorTest() {
        assertTrue(b.checkYearOfPayment(p2));
        assertFalse(b.checkYearOfPayment(p3));
    }

    //Testar så att person p2 skrivs till filen eftersom den är medlem
    @Test
    public void writeToFileTest() {
        b.writeToPTFile(p2, fileName);
        
    }
}