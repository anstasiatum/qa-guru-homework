package seventhHometaskTests;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class TestData {

    private static final Faker faker = new Faker(new Locale("es"));

    // Using Faker
    final static String firstName = faker.name().firstName();
    final static String lastName = faker.name().lastName();
    final static String fullName = firstName + " " + lastName;
    final static String email = faker.internet().emailAddress();
    final static String currentAddress = faker.address().fullAddress();
    final static String phoneNumber = faker.numerify("##########");


    // Custom methods to get random test data
    public static String getRandomGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public static String getRandomSubject() {
        return faker.options().option("Maths", "Accounting", "Arts", "Social Studies", "Biology", "Physics", "Computer Science", "Chemistry", "Commerce", "Arts", "Economics", "Social Studies", "Civics", "Hindi", "English", "History");
    }

    public static String getRandomHobby() {
        return faker.options().option("Reading", "Sports", "Music");
    }

    public static String getRandomMonth() {
        return faker.options().option("January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December");
    }

    public static String getRandomYear() {
        Random random = new Random();
        int randomYear = random.nextInt(1990, 2025);
        return String.valueOf(randomYear);
    }

    public static String getRandomBirthday(String month, String year) {
        Random random = new Random();
        switch (month) {
            case ("January"), ("March"), ("May"), ("July"), ("August"), ("October"), ("December"):
                return String.valueOf(random.nextInt(1, 32));
            case ("April"), ("June"), ("September"), ("November"):
                return String.valueOf(random.nextInt(1, 31));
            case ("February"):
                int integerYear = Integer.parseInt(year);
                if (((integerYear % 4 == 0) && !(integerYear % 100 == 0)) || (integerYear % 400 == 0))
                    return String.valueOf(random.nextInt(1, 30));
                else
                    return String.valueOf(random.nextInt(1, 29));
            default:
                throw new IllegalArgumentException("Wrong month");
        }
    }

    public static String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public static String getRandomCity(String state) {
        switch (state) {
            case ("NCR"):
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case ("Uttar Pradesh"):
                return faker.options().option("Agra", "Lucknow", "Merrut");
            case ("Haryana") :
                return faker.options().option("Karnal", "Panipat");
            case ("Rajasthan"):
                return faker.options().option("Jaipur", "Jaiselmer");
            default: throw new IllegalArgumentException("Wrong state");
        }
    }

    public static String getRandomAvatar() {
        return faker.options().option("AvatarExample1.jpg", "AvatarExample2.PNG", "AvatarExample3.jpg");
    }
}
