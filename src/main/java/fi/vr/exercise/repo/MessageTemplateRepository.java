package fi.vr.exercise.repo;

import fi.vr.exercise.domain.MessageTemplate;
import fi.vr.exercise.domain.MessageTemplateQuery;
import java.util.List;
import javax.sql.DataSource;
import lombok.NonNull;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * A JDBC repository that provides read, create and update operations on {@link MessageTemplate}.
 *
 * @author Juha-Pekka Koivisto
 */
@Repository
public class MessageTemplateRepository extends NamedParameterJdbcDaoSupport {

  public MessageTemplateRepository(@NonNull DataSource dataSource) {
    setDataSource(dataSource);
  }

  public int create(MessageTemplate template) {
    var jdbcTemplate = getJdbcTemplate();
    assert jdbcTemplate != null;
    var sqlInsert = new SqlMessageTemplateInsert(template);
    var count = jdbcTemplate.update(
      SqlMessageTemplateInsert.getSql(),
      sqlInsert.getArgs());
    assert count == 1;
    return count;
  }

  public int update(MessageTemplate template) {
    var jdbcTemplate = getJdbcTemplate();
    assert jdbcTemplate != null;
    var sqlUpdate = new SqlMessageTemplateUpdate(template);
    var count = jdbcTemplate.update(
      SqlMessageTemplateUpdate.getSql(),
      sqlUpdate.getArgs());
    assert count == 1;
    return count;
  }

  public MessageTemplate getById(String id) {
    var query = MessageTemplateQuery.builder()
                                    .id(id)
                                    .build();
    var templates = query(query);
    assert templates.size() <= 1;
    return !templates.isEmpty() ? templates.getFirst() : null;
  }

  public List<MessageTemplate> query(MessageTemplateQuery query) {
    var jdbcTemplate = getJdbcTemplate();
    assert jdbcTemplate != null;
    var sqlQuery = new SqlMessageTemplateQuery(query);
    return jdbcTemplate.query(sqlQuery.getSql(),
                              MessageTemplateRowMapper.INSTANCE,
                              sqlQuery.getArgs());
  }

}
