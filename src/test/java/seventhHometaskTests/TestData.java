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

    // Util method to get a random item from an array
    public static String getRandomItemFromArray(String[] array) {
        Random random = new Random();
        int randomIndex = random.nextInt(array.length);
        return array[randomIndex];
    }

    // Custom methods to get random test data
    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return getRandomItemFromArray(genders);
    }

    public static String getRandomSubject() {
        String[] subjects = {"Maths", "Accounting", "Arts", "Social Studies", "Biology", "Physics", "Computer Science", "Chemistry", "Commerce", "Arts", "Economics", "Social Studies", "Civics", "Hindi", "English", "History"};
        return getRandomItemFromArray(subjects);
    }

    public static String getRandomHobby() {
        String[] hobbies = {"Reading", "Sports", "Music"};
        return getRandomItemFromArray(hobbies);
    }

    public static String getRandomMonth() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return getRandomItemFromArray(months);
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
        String[] states = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return getRandomItemFromArray(states);
    }

    public static String getRandomCity(String state) {
        String[] cities = new String[0];
        cities = switch (state) {
            case ("NCR") -> new String[]{"Delhi", "Gurgaon", "Noida"};
            case ("Uttar Pradesh") -> new String[]{"Agra", "Lucknow", "Merrut"};
            case ("Haryana") -> new String[]{"Karnal", "Panipat"};
            case ("Rajasthan") -> new String[]{"Jaipur", "Jaiselmer"};
            default -> cities;
        };
        return getRandomItemFromArray(cities);
    }

    public static String getRandomAvatar() {
        String[] avararPaths = {"AvatarExample1.jpg", "AvatarExample2.PNG", "AvatarExample3.jpg"};
        return getRandomItemFromArray(avararPaths);
    }
}
