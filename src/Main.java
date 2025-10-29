void main() {
    caesarTest();
}

void caesarTest(){
    String textRu = "Нет хуже причины для выбора имени с, чем та, что имена a и b уже заняты.\n";
    String textEn = "There is no worse reason to choose the name c than the fact that the names a and b are already occupied.\n";

    String encryptedRu = Caesar.processText(textRu, 3, true);
    String encryptedEn = Caesar.processText(textEn, -4, true);

    System.out.println("Зашифровано (русский):\n" + encryptedRu);
    System.out.println("Зашифровано (английский):\n" + encryptedEn);

    String decryptedRu = Caesar.processText(encryptedRu, 3, false);
    String decryptedEn = Caesar.processText(encryptedEn, -4, false);

    System.out.println("Расшифровано (русский):\n" + decryptedRu);
    System.out.println("Расшифровано (английский):\n" + decryptedEn);
}
