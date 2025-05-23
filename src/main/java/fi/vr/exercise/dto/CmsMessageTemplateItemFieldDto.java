package fi.vr.exercise.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * A JSON object presentation for a CMS communication template item.
 *
 * @author Juha-Pekka Koivisto
 */
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class CmsMessageTemplateItemFieldDto {

  String key;
  String name;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  CmsNodeTypeDto trafficType;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  CmsMessageContentNodeDto subject;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  CmsMessageContentNodeDto body;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .omitNullValues()
        .add("key", key)
        .add("name", name)
        .add("trafficType", trafficType)
        .add("subject", subject)
        .add("body", body)
        .toString();
  }

}
