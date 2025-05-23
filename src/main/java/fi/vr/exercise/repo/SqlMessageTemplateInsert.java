package fi.vr.exercise.repo;

import fi.vr.exercise.domain.MessageTemplate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * A SQL prepared statement for inserting {@link MessageTemplate}.
 *
 * @author Juha-Pekka Koivisto
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class SqlMessageTemplateInsert {

  private static final String INSERT_STMT =
    "INSERT INTO message_template " +
      "(id, traffic_type, create_time, update_time, revision, content) " +
      "VALUES (?, ?, ?, ?, ?, ?)";

  @NonNull
  private final MessageTemplate template;

  static String getSql() {
    return INSERT_STMT;
  }

  Object[] getArgs() {
    return new Object[] {
      template.getId(),
      template.getTrafficType(),
      template.getCreateTime(),
      template.getUpdateTime(),
      template.getRevision(),
      template.getContent()
    };
  }
}
