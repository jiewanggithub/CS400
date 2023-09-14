/**
 * This class is using for the check the validness of ISBN that user typing
 */
public class ISBNValidator extends HashtableMap implements IISBNValidator, IterableMapADT {
    @Override
    /**
     * This method is using for the check the correctness and validness of isbn13 entered
     * by the user.
     * @return true if the position 13 number is equal to the number we calculate by our
     * equation, otherwise false;
     */
    public boolean validate(String isbn13) {
        // if the length of ISBN is not 13, then return false
        if (isbn13.length() != 13) {
            return false;
        }
        int[] number12 = new int[12];

        for (int i = 0; i < number12.length; i++) {
            number12[i] = (isbn13.charAt(i)) - '0';
        }
        int sum = 0;
        for (int i = 0; i < number12.length; i += 2) {
            sum += number12[i] + 3 * number12[i + 1];
        }
        int remainder = 10 - (sum % 10);

        return remainder == isbn13.charAt(12) - '0';
    }
}
