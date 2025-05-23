package fi.vr.exercise.service;

import fi.vr.exercise.domain.MessageTemplate;
import fi.vr.exercise.domain.MessageTemplateQuery;
import fi.vr.exercise.repo.MessageTemplateRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Provides a service interface for {@link MessageTemplate} entity providing read, create, update
 * operations.
 *
 * @author Juha-Pekka Koivisto
 */
@Service
@Slf4j
@AllArgsConstructor
public class MessageTemplateService {

  @NonNull
  private final MessageTemplateRepository repository;

  @Transactional
  public MessageTemplate store(MessageTemplate template) {
    var existingTemplate = repository.getById(template.getId());
    if (existingTemplate == null) {
      return create(template);
    } else if (!existingTemplate.equals(template)) {
      return update(template);
    } else {
      log.info(
        "Ignoring template, already persisted {id={}, trafficType={}, createTime={}, " +
          "updateTime={}, revision={}}.",
        template.getId(),
        template.getTrafficType(),
        template.getCreateTime(),
        template.getUpdateTime(),
        template.getRevision());
      return template;
    }
  }

  @Transactional(readOnly = true)
  public MessageTemplate getById(String id) {
    log.info("Getting template {id={}}.", id);
    var result = repository.getById(id);
    if (result != null) {
      log.info("Found template {id={}, trafficType={}, createTime={}, " +
          "updateTime={}, revision={}}.",
        result.getId(),
        result.getTrafficType(),
        result.getCreateTime(),
        result.getCreateTime(),
        result.getUpdateTime());
    } else {
      log.info("No template found {id={}}.", id);
    }
    return result;
  }

  @Transactional(readOnly = true)
  public List<MessageTemplate> query(MessageTemplateQuery query) {
    log.info("Querying templates {{}}.", query);
    var result = repository.query(query);
    log.info("Queried {} templates {{}}.", result.size(), query);
    return result;
  }

  private MessageTemplate create(MessageTemplate template) {
    log.info("Creating template {id={}, trafficType={}, createTime={}, updateTime={}, revision={}}.",
      template.getId(),
      template.getTrafficType(),
      template.getCreateTime(),
      template.getUpdateTime(),
      template.getRevision());
    repository.create(template);
    log.info("Created template {id={}, trafficType={}, createTime={}, updateTime={}, revision={}}.",
      template.getId(),
      template.getTrafficType(),
      template.getCreateTime(),
      template.getUpdateTime(),
      template.getRevision());
    return template;
  }

  private MessageTemplate update(MessageTemplate template) {
    log.info("Updating template {id={}, trafficType={}, createTime={}, updateTime={}, revision={}}.",
      template.getId(),
      template.getTrafficType(),
      template.getCreateTime(),
      template.getUpdateTime(),
      template.getRevision());
    repository.update(template);
    log.info("Updated template {id={}, trafficType={}, createTime={}, updateTime={}, revision={}}.",
      template.getId(),
      template.getTrafficType(),
      template.getCreateTime(),
      template.getUpdateTime(),
      template.getRevision());
    return template;
  }

}
