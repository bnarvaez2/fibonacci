package com.proteccion.fibonacci.util;

import static com.proteccion.fibonacci.util.Constants.PATTERN_HOURS;

import com.proteccion.fibonacci.exception.BadRequestException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public final class DateUtil {

  public static boolean validateHours(String hours) {
    if (Objects.isNull(hours)) throw new BadRequestException("Times is null");
    if (hours.isEmpty()) throw new BadRequestException("Times is empty");

    Pattern compiledPattern = Pattern.compile(PATTERN_HOURS);
    Matcher matcher = compiledPattern.matcher(hours);

    if (!matcher.matches()) throw new BadRequestException("Times is invalid");

    return false;
  }
}
