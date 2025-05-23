package fi.vr.exercise.repo;

import fi.vr.exercise.domain.MessageTemplate;
import fi.vr.exercise.domain.TrafficType;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * A {@link RowMapper} implementation for {@link MessageTemplate}.
 *
 * @author Juha-Pekka Koivisto
 */
enum MessageTemplateRowMapper implements RowMapper<MessageTemplate> {

  INSTANCE;

  @Override
  public MessageTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
    return MessageTemplate.builder()
      .id(rs.getString("id"))
      .trafficType(rs.getString("traffic_type"))
      .createTime(rs.getTimestamp("create_time").toLocalDateTime())
      .updateTime(rs.getTimestamp("update_time").toLocalDateTime())
      .revision(rs.getInt("revision"))
      .content(rs.getString("content"))
      .build();
  }

}
