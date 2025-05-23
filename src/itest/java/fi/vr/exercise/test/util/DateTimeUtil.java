package fi.vr.exercise.test.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * Contains helper methods related to date time handling.
 *
 * @author Juha-Pekka Koivisto
 */
public class DateTimeUtil {

  public static LocalDateTime currentUtcTimeInMillis() {
    return LocalDateTime.now(ZoneId.of("UTC"))
      .truncatedTo(ChronoUnit.MILLIS);
  }

}
