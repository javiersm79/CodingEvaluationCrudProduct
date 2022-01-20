package cl.falabella.boost.codingevaluation.business.utils;


import org.apache.commons.validator.routines.UrlValidator;

public class CustomUtil {

    private int countChars(String str, char c)
    {
        int count = 0;

        for(int i = 0; i < str.length(); i++)
        {
            char currChar = str.charAt(i);
            if(currChar == c)
                count += 1;
        }

        return count;
    }

    public Boolean validSKU(String sku) {
        // Validate cant of separator
        int countSeparator = this.countChars(sku, '-');
        if (countSeparator != 1) return false;

        String[] parts = sku.split("-");

        // Validate if first part of SKU is "FAL"
        if (!parts[0].contentEquals("FAL")) return false;

        // Validate if second part of SKU is numeric and between 1000000 - 99999999
        boolean isNumeric = parts[1].chars().allMatch( Character::isDigit );
        if (!isNumeric) return false;

        if (Integer.parseInt(parts[1]) <  1000000 || Integer.parseInt(parts[1]) >  99999999) return false;

        return true;

    }

    public Boolean validURL(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }



}
