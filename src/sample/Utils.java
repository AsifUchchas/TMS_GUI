package sample;

public class Utils {
    // ***************************************************************
    // MAIN FUNCTIONS
    // **************************************************************
    public static boolean isValidEmail(String email) {
        String domainName;
        String[] dData;
        String user;
        String domain;

        try {
            user = email.split("@")[0];
            domain = email.split("@")[1];
        }
        catch (IndexOutOfBoundsException e) {
            return false;
        }


        // count '@' occurrence
        int countAt = countChar(email, '@');
        if (countAt != 1) return false;

        // count '.' occurrence
        int countDot = countChar(domain, '.');
        if (countDot < 1) return false;


        // check special chars
        // checking user name
        if (isContainInvalidChar(user) || isContainInvalidChar(domain)) return false;

        // domain & domain length
        dData = domain.split("\\.");
        domainName = dData[dData.length - 1];
        return domainName.length() >= 2;
    }

    public static boolean isValidPass(String pass) {
        // length
        if (pass.length() < 8) return false;

        // special char & number & chars
        int specialCount = 0;
        int numCount = 0;
        int alphaCount = 0;
        for (int i = 0; i < pass.length(); i++) {
            if (isSpecialChar(pass.charAt(i)))
                specialCount++;
            if (isNum(pass.charAt(i)))
                numCount++;
            if (isAlphabet(pass.charAt(i)))
                alphaCount++;
        }

        if (specialCount < 1 || numCount < 1 || alphaCount < 1) return false;

        return true;
    }


    public static boolean isValidPhone(String phone) {
        for (int i = 0; i < phone.length(); i++) {
            char c = phone.charAt(i);
            if (!isNum(c)) return false;
        }
        return true;
    }

    // **********************************************************
    // HELPER FUNCTIONS
    // **********************************************************

    // this method checks if string contains any special chars
    public static boolean isContainInvalidChar(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // if not
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '.')) return true;
        }
        return false;
    }

    public static boolean isSpecialChar(char c) {
        return (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')));
    }

    public static boolean isNum(char c) {
        return (c >= '0' && c <= '9');
    }

    public static boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    // method to count the occurrence of a char
    public static int countChar(String str, char c) {
        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c)
                count++;
        }
        return count;
    }
}
