public class CaesarCipher {

    private static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private static String processText(String text, int shift, boolean isEncrypt) {
        if (text.isEmpty()) return "";

        shift = isEncrypt ? shift : -shift;

        StringBuilder result = new StringBuilder();

        for (char originalChar : text.toCharArray()) {
            boolean isUpper = Character.isUpperCase(originalChar);
            char lowerCaseChar = Character.toLowerCase(originalChar);

            String alphabet = getAlphabet(lowerCaseChar);
            if (alphabet == null) {
                result.append(originalChar);
                continue;
            }

            int index = alphabet.indexOf(lowerCaseChar);
            int newIndex = (index + shift) % alphabet.length();
            if (newIndex < 0) newIndex += alphabet.length();

            char newChar = alphabet.charAt(newIndex);
            result.append(isUpper ? Character.toUpperCase(newChar) : newChar);
        }
        return result.toString();
    }

    private static String getAlphabet(char c) {
        if (RUSSIAN_ALPHABET.indexOf(c) >= 0) return RUSSIAN_ALPHABET;
        if (ENGLISH_ALPHABET.indexOf(c) >= 0) return ENGLISH_ALPHABET;
        return null;
    }

    public static String encrypt(String text, int shift) {
        return processText(text, shift, true);
    }

    public static String decrypt(String text, int shift) {
        return processText(text, shift, false);
    }
}

