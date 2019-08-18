package comp3350.amate.business;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import comp3350.amate.objects.Card;

public class CardValidation {
    public boolean validateCard(Card card) throws CardException {
        String number = "" + card.getCardNumber();
        String date = card.getExpiryDate();
        String cvv = "" + card.getCVV();
        String name = card.getCardHolder();

        if (number.length() == 0)
            throw new CardException("Credit Card Number Required");
        else if (number.length() < 16)
            throw new CardException("Credit Card Number needs 16 digits");

        if (date.length() == 0)
            throw new CardException("Date required");
        else {
            String regex = "^(0[1-9]|1[0-2])\\/\\d{2}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(date);
            if (!matcher.matches())
                throw new CardException("Please write Date in the correct format: \'01/99\'");
            else {
                String[] checkDate = date.split("/");
                int month = Integer.parseInt(checkDate[0]);
                int year = Integer.parseInt(checkDate[1]) + 2000;
                Calendar cal = Calendar.getInstance();
                if (year < cal.get(Calendar.YEAR) || (year == cal.get(Calendar.YEAR) && month <= cal.get(Calendar.MONTH)))
                    throw new CardException("Card expired");
                else if (year > cal.get(Calendar.YEAR) + 20)
                    throw new CardException("Are you from a far future or past?");
            }
        }

        if (cvv.length() == 0)
            throw new CardException("CVV required");
        else if (cvv.length() < 3)
            throw new CardException("CVV needs 3 digits");

        if (name.length() == 0)
            throw new CardException("Name in credit card required");
        return true;
    }

}
