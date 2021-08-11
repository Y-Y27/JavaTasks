
public class TextEditor {

    public static void main(String[] args) {
        textEditor("success cacao and coffee the table a an and them moo mooo");
    }

    private static void textEditor(String inputString) {
        StringBuilder stringBuilder = new StringBuilder(inputString);
        String output = inputString;
        for (int i = 0; i < inputString.length(); i++) {

            if (inputString.charAt(i) == 'c' && inputString.charAt(i + 1) == 'c'
                    || inputString.charAt(i) == 'c' && inputString.charAt(i + 1) != 'c'
                        && inputString.charAt(i + 1) == 'k' && inputString.charAt(i + 1) != 'i'
                        && inputString.charAt(i + 1) != 'e' && inputString.charAt(i - 1) == 'k'
                    || inputString.charAt(i) == 'c' && inputString.charAt(i - 1) != 'c'
                        && inputString.charAt(i + 1) != 'k' && inputString.charAt(i + 1) != 'i'
                        && inputString.charAt(i + 1) != 'e'
            ) {
                output = stringBuilder.replace(i, i + 1, "k").toString();
            }

            if (stringBuilder.charAt(i) == 'c' && stringBuilder.charAt(i + 1) == 'e'
                    || stringBuilder.charAt(i) == 'c' && stringBuilder.charAt(i + 1) == 'i') {
                stringBuilder.setCharAt(i, 's');
                output = stringBuilder.toString();
            }

            if (output.contains("ck")) {
                output = output.replace("ck", "k");
            }

            if (output.contains("oo")) {
                output = output.replace("oo", "u");
            }

            if (output.contains("ee")) {
                output = output.replace("ee", "i");
            }

            output = output.replaceAll("(.)\\1", "$1");

            String[] split = output.split("\\s|[,.;:]");
            String emptyString = "";

            for (String subString : split) {
                if (subString.endsWith("e") && subString.length() > 1) {
                    subString = subString.substring(0, subString.length() - 1);
                }
                emptyString += subString + " ";
                output = emptyString;
            }

            if (output.contains(" th ") || output.contains(" a ") || output.contains(" an ")) {
                output = output.replace(" th ", " ");
                output = output.replace(" a ", " ");
                output = output.replace(" an ", " ");
            }
        }
        System.out.println(output);
    }
}