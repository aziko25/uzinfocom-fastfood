package uzinfocom.uzinfocom.Models.Enums;

import lombok.Getter;

@Getter
public enum OrdersStatuses {

    ORDER_CREATED(1),
    ORDER_IN_PROGRESS(2),
    ORDER_COOKED(3),
    ORDER_DELIVERING(4),
    ORDER_DELIVERED(5),
    ORDER_CANCELED(6);

    private final int code;

    OrdersStatuses(int code) {
        this.code = code;
    }

    public static OrdersStatuses getStatusFromCode(int code) {

        for (OrdersStatuses status : OrdersStatuses.values()) {

            if (status.getCode() == code) {

                return status;
            }
        }

        throw new IllegalArgumentException("Unknown Status For Order: " + code + "\n\nExistingStatuses:" +
                "\nORDER_CREATED(1),\n" +
                "    ORDER_IN_PROGRESS(2),\n" +
                "    ORDER_COOKED(3),\n" +
                "    ORDER_DELIVERING(4),\n" +
                "    ORDER_DELIVERED(5),\n" +
                "    ORDER_CANCELED(6);");
    }
}