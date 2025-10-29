public class VigenereCipher {

    private static final String RUSSIAN_ALPHABET_UP = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String RUSSIAN_ALPHABET_LOW = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String ENGLISH_ALPHABET_UP = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ENGLISH_ALPHABET_LOW = "abcdefghijklmnopqrstuvwxyz";

    private static final String[] ALPHABETS = {
            ENGLISH_ALPHABET_UP, ENGLISH_ALPHABET_LOW, RUSSIAN_ALPHABET_UP, RUSSIAN_ALPHABET_LOW
    };

    // Key normalization: we leave only letters from supported alphabets.
    private static String normalizeKey(String key) {
        if (key == null || key.isEmpty()) throw new IllegalArgumentException("The key must not be empty");

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : key.toCharArray()) {
            if (isLetterInAnyAlphabet(c)) {
                stringBuilder.append(c);
            }
        }

        if (stringBuilder.isEmpty())
            throw new IllegalArgumentException("The key must contain Latin or Cyrillic letters");

        return stringBuilder.toString();
    }

    // Checking whether a character is a letter from any alphabet
    private static boolean isLetterInAnyAlphabet(char c) {
        for (String alphabet : ALPHABETS) {
            if (alphabet.indexOf(c) >= 0) return true;
        }
        return false;
    }

    // A class for storing which alphabet a symbol belongs to and its index
    private static class CharInfo {
        final String alphabet;
        final int index;
        final int alphabetSize;

        CharInfo(String alphabet, int index) {
            this.alphabet = alphabet;
            this.index = index;
            this.alphabetSize = alphabet.length();
        }
    }

    private static CharInfo getCharInfo(char c) {
        for (String alphabet : ALPHABETS) {
            int index = alphabet.indexOf(c);
            if (index >= 0) {
                return new CharInfo(alphabet, index);
            }
        }
        return null;
    }

    // We get the shift by the key symbol (the index in its alphabet)
    private static int getShiftFromKeyChar(char k) {
        for (String alphabet : ALPHABETS) {
            int index = alphabet.indexOf(k);
            if (index >= 0) return index;
        }

        char upper = Character.toUpperCase(k);
        char lower = Character.toLowerCase(k);

        for (String alphabet : ALPHABETS) {
            int index = alphabet.indexOf(upper);
            if (index >= 0) return index;
            index = alphabet.indexOf(lower);
            if (index >= 0) return index;
        }
        return -1;
    }

    private static String processText(String text, String key, boolean isEncrypt) {
        String normalizedKey = normalizeKey(key);
        StringBuilder result = new StringBuilder(text.length());
        int keyLen = normalizedKey.length();
        int keyIndex = 0;

        for (char c : text.toCharArray()) {
            CharInfo charInfo = getCharInfo(c);
            if (charInfo == null) {
                result.append(c);
                continue;
            }

            char k = normalizedKey.charAt(keyIndex % keyLen);
            keyIndex++;

            int kShift = getShiftFromKeyChar(k);
            if (kShift < 0) kShift = 0;

            int newIndex = (charInfo.index + kShift * (isEncrypt ? 1 : -1)) % charInfo.alphabetSize;
            if (newIndex < 0) newIndex += charInfo.alphabetSize;

            result.append(charInfo.alphabet.charAt(newIndex));
        }
        return result.toString();
    }

    public static String encrypt(String plaintext, String key) {
        return processText(plaintext, key, true);
    }

    public static String decrypt(String ciphertext, String key) {
        return processText(ciphertext, key, false);
    }
}
