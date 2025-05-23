package fi.vr.exercise.mapper;

import fi.vr.exercise.domain.MessageTemplate;
import fi.vr.exercise.domain.TrafficType;
import fi.vr.exercise.dto.CmsMessageTemplateDto;
import fi.vr.exercise.dto.CmsMessageTemplateItemDto;
import java.util.function.Function;

/**
 * Maps {@link CmsMessageTemplateDto} to {@link MessageTemplate}.
 *
 * @author Juha-Pekka Koivisto
 */
public enum MessageTemplateMapper implements Function<CmsMessageTemplateDto, MessageTemplate> {

  INSTANCE;

  @Override
  public MessageTemplate apply(CmsMessageTemplateDto template) {
    assert template.getItems().size() == 1;
    var item = template.getFirstItem();
    return MessageTemplate.builder()
                          .id(getFieldKey(item))
                          .createTime(item.getCreatedAt())
                          .updateTime(item.getUpdatedAt())
                          .revision(item.getRevision())
                          .trafficType(determineTrafficType(template))
                          .content(createTextContent(template))
                          .build();
  }

  private String determineTrafficType(CmsMessageTemplateDto template) {
    var item = template.getFirstItem();
    var contentType = item.getFieldTrafficType();
    var includeItem = template.getInclude(contentType);
    var includeContentType = includeItem.getContentType();
    assert includeContentType.isTrafficType();
    var field = includeItem.getField();
    return field.getKey();
  }

  private String createTextContent(CmsMessageTemplateDto template) {
    return TextMessageTemplateBuilder.INSTANCE.apply(template);
  }

  private String getFieldKey(CmsMessageTemplateItemDto item) {
    var field = item.getField();
    return field.getKey();
  }
}
