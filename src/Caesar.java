public class Caesar {

    private static final String RUSSIAN_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String ENGLISH_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String SYMBOLS = " ,!.?\"—-:;()";

    public static String processText(String text, int shift, boolean isEncrypt) {
        if(text.isEmpty()) return "";

        if (!isEncrypt) shift *= -1;

        StringBuilder result = new StringBuilder();

        for (char originalChar : text.toCharArray()) {
            boolean isUpper = Character.isUpperCase(originalChar);
            char lowerCaseChar = Character.toLowerCase(originalChar);

            String alphabet = getAlphabet(lowerCaseChar);
            if (alphabet == null || SYMBOLS.indexOf(lowerCaseChar) >= 0) {
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
}

