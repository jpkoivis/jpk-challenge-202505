package fi.vr.exercise.repo;

import fi.vr.exercise.domain.MessageTemplateQuery;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * A helper class to generate a prepared SQL statement and associated args for
 * {@link MessageTemplateQuery}.
 *
 * @author Juha-Pekka Koivisto
 */
@AllArgsConstructor
class SqlMessageTemplateQuery {

  private static final int DEFAULT_QUERY_SIZE = 120;

  private static final String SELECT_STMT = "SELECT id, traffic_type, create_time, update_time, " +
    "revision, content FROM message_template";

  @NonNull
  private final MessageTemplateQuery query;

   String getSql() {
    var sqlBuilder = new StringBuilder(DEFAULT_QUERY_SIZE);
    sqlBuilder.append(SELECT_STMT);
    var args = getArgs();
    if (args.length > 0) {
      sqlBuilder.append(" WHERE ");
      List<String> conditions = createConditions(args.length);
      sqlBuilder.append(String.join(" AND ", conditions));
    }
    sqlBuilder.append(" ORDER BY id");
    return sqlBuilder.toString();
  }

  private List<String> createConditions(int size) {
    List<String> result = new ArrayList<>(size);
    if (query.getId() != null) {
      result.add("id = ?");
    }
    if (query.getTrafficType() != null) {
      result.add("traffic_type = ?");
    }
    return result;
  }

  Object[] getArgs() {
    List<Object> args = Arrays.asList(
      query.getId(),
      query.getTrafficType());

    return args.stream()
               .filter(Objects::nonNull)
               .toArray();
  }
}
