public final class TestData {
    private TestData() {
    }

    public static final String BASE_URL = System.getProperty("baseUrl", "https://promopult.ru/");
    public static final String AUTH_EMAIL = System.getProperty("authEmail", "simeon.gory@gmail.com");
    public static final String AUTH_PASSWORD = System.getProperty("authPassword", "QaTest123!");
    public static final String WRONG_PASSWORD = "QaWrong123!";
    public static final String INVALID_EMAIL = "qa.promopult.test";
    public static final String SHORT_PASSWORD = "12345";
    public static final String PHONE = "+79990000011";
    public static final String NAME = "Иван";
    public static final String BLOG_POSITIVE_QUERY = "Реклама";
    public static final String BLOG_NEGATIVE_QUERY = "zzzxqplm_404_lab";
    public static final String HELP_POSITIVE_QUERY = "Регистрация";
    public static final String HELP_NEGATIVE_QUERY = "zzzxqplm_404_lab";

    public static boolean isLiveAuthEnabled() {
        return Boolean.parseBoolean(System.getProperty("enableLiveAuth", "false"));
    }

    public static boolean isLiveRecoveryEnabled() {
        return Boolean.parseBoolean(System.getProperty("enableLiveRecovery", "false"));
    }
}
