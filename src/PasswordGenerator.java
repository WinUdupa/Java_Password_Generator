import java.util.Random;

public class PasswordGenerator {
    public static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUV";
    public static final String NUMBERS = "0123456789";
    public static final String SPECIAL_SYMBOLS = "!@#$%^&*~`+=-_?/<>,.:;()[]{}/?";

    private final Random random;

    public PasswordGenerator() {
        random = new Random();
    }

    public String generatePassword(int length, boolean includeUppercase, boolean includeLowercase, 
                                 boolean includeNumbers, boolean includeSpecialSymbols, String customCharacters) {
        StringBuilder passwordBuilder = new StringBuilder();
        
        String validCharacters = "";
        if (customCharacters != null && !customCharacters.isEmpty()) {
            validCharacters = customCharacters;
        } else {
            if (includeUppercase) validCharacters += UPPERCASE_CHARACTERS;
            if (includeLowercase) validCharacters += LOWERCASE_CHARACTERS;
            if (includeNumbers) validCharacters += NUMBERS;
            if (includeSpecialSymbols) validCharacters += SPECIAL_SYMBOLS;
        }

        if (validCharacters.isEmpty()) {
            return "";
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validCharacters.length());
            char randomChar = validCharacters.charAt(randomIndex);
            passwordBuilder.append(randomChar);
        }

        return passwordBuilder.toString();
    }

    public String generatePassword(int length, boolean includeUppercase, boolean includeLowercase, 
                                 boolean includeNumbers, boolean includeSpecialSymbols) {
        return generatePassword(length, includeUppercase, includeLowercase, includeNumbers, 
                              includeSpecialSymbols, null);
    }
}