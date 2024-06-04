package uzinfocom.uzinfocom.Utils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UtilsService {

    public static boolean isValidPhone(String phone) {

        if (phone == null) {

            return false;
        }

        if (!phone.startsWith("+")) {

            phone = "+" + phone;
        }

        if (!phone.startsWith("+") || phone.length() != 13) {

            return false;
        }

        String phoneNumberWithoutPlus = phone.substring(1);

        return phoneNumberWithoutPlus.startsWith("998") && phoneNumberWithoutPlus.matches("\\d{12}");
    }
}