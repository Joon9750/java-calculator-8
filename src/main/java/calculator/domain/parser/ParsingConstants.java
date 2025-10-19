package calculator.domain.parser;

import java.util.regex.Pattern;

public final class ParsingConstants {

    private ParsingConstants() {}

    public static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(.*)(?:\\n|\\\\n)(.+)");
    public static final Pattern VALID_DEFAULT_CONTENT_PATTERN = Pattern.compile("^[0-9,: ]*$");

    public static final String DEFAULT_DELIMITER_REGEX = "[,:]";
}