void main() throws Exception {
    String textRu = "Нет хуже причины для выбора имени с, чем та, что имена a и b уже заняты.\n";
    String textEn = "There is no worse reason to choose the name c than the fact that the names a and b are already occupied.\n";
    caesarTest(textRu, textEn);
    vigenereTest(textRu, textEn);
    rsaTest(textRu);
}

void caesarTest(String textRu, String textEn) {
    IO.println("\nТесты для Шифра Цезаря\n");

    String encryptedRu = CaesarCipher.encrypt(textRu, 3);
    String encryptedEn = CaesarCipher.encrypt(textEn, -4);

    IO.println("Зашифровано (русский):\n" + encryptedRu);
    IO.println("Зашифровано (английский):\n" + encryptedEn);

    String decryptedRu = CaesarCipher.decrypt(encryptedRu, 3);
    String decryptedEn = CaesarCipher.decrypt(encryptedEn, -4);

    IO.println("Расшифровано (русский):\n" + decryptedRu);
    IO.println("Расшифровано (английский):\n" + decryptedEn);
}

void vigenereTest(String textRu, String textEn) {
    IO.println("\nТесты для Шифра Виженера\n");

    String encryptedRu = VigenereCipher.encrypt(textRu, "Ура");
    String encryptedEn = VigenereCipher.encrypt(textEn, "Ehh");

    IO.println("Зашифровано (русский):\n" + encryptedRu);
    IO.println("Зашифровано (английский):\n" + encryptedEn);

    String decryptedRu = VigenereCipher.decrypt(encryptedRu, "Ура");
    String decryptedEn = VigenereCipher.decrypt(encryptedEn, "Ehh");

    IO.println("Расшифровано (русский):\n" + decryptedRu);
    IO.println("Расшифровано (английский):\n" + decryptedEn);
}

void rsaTest(String text) throws Exception {
    IO.println("\nТесты для RSA\n");
    KeyPair keyPair = RSAExample.generateKeyPair();
    PublicKey publicKey = keyPair.getPublic();
    PrivateKey privateKey = keyPair.getPrivate();

    System.out.println("Оригинал: " + text);

    String encryptedMessage = RSAExample.encrypt(text, publicKey);
    System.out.println("Зашифровано: " + encryptedMessage);

    String decryptedMessage = RSAExample.decrypt(encryptedMessage, privateKey);
    System.out.println("Расшифровано: " + decryptedMessage);
}
