package org.muromtsev.organizationservice.validation;

public final class ValidationPatterns {

    private ValidationPatterns() {}

    // UUID
    public static final String UUID =
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

    // UUID версии 4 (самый распространенный)
    public static final String UUID_V4 =
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-4[0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";

    // Email
    public static final String EMAIL =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    // Телефон (международный формат E.164)
    public static final String PHONE_E164 =
            "^\\+[1-9]\\d{1,14}$";

    // Только буквы (русские и английские) и пробелы
    public static final String ALPHA_SPACES =
            "^[a-zA-Zа-яА-Я\\s]+$";

    // Без спецсимволов (только буквы, цифры, пробелы и дефисы)
    public static final String NO_SPECIAL_CHARS =
            "^[a-zA-Zа-яА-Я0-9\\s-]+$";

    // Пароль: минимум 8 символов, буквы в разных регистрах, цифры
    public static final String PASSWORD =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";

    // Код организации (например: ORG_001, IT_DEPT)
    public static final String ORGANIZATION_CODE =
            "^[A-Z][A-Z0-9_]{2,19}$";
}
