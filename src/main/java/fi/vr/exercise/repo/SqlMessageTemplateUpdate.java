package fi.vr.exercise.repo;

import fi.vr.exercise.domain.MessageTemplate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * A SQL prepared statement for updating {@link MessageTemplate}.
 *
 * @author Juha-Pekka Koivisto
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class SqlMessageTemplateUpdate {

  private static final String UPDATE_STMT =
    "UPDATE message_template SET traffic_type = ?, update_time = ?, revision = ?, content = ? " +
      "WHERE id = ?";

  private final MessageTemplate template;

  static String getSql() {
    return UPDATE_STMT;
  }

  public Object[] getArgs() {
    return new Object[] {
      template.getTrafficType(),
      template.getUpdateTime(),
      template.getRevision(),
      template.getContent(),
      template.getId()
    };
  }
}
