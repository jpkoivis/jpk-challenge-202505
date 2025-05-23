package fi.vr.exercise.domain;

import com.google.common.base.MoreObjects;
import lombok.Builder;
import lombok.Value;

/**
 * Defines query parameters for querying {@link MessageTemplate}s.
 *
 * @author Juha-Pekka Koivisto
 */
@Value
@Builder
public class MessageTemplateQuery {

  String id;
  String trafficType;

  public static MessageTemplateQuery all() {
    return MessageTemplateQuery.builder()
                               .build();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
      .omitNullValues()
      .add("id", id)
      .add("trafficType", trafficType)
      .toString();
  }

}
