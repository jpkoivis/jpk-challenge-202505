package fi.vr.exercise.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import java.time.LocalDateTime;
import lombok.*;

/**
 * A JSON object presentation for a CMS communication template item.
 *
 * @author Juha-Pekka Koivisto
 */
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CmsMessageTemplateItemDto {

  @NonNull
  CmsSysDto sys;
  @NonNull
  @JsonProperty("fields")
  CmsMessageTemplateItemFieldDto field;

  @JsonIgnore
  public String getSysId() {
    return sys.getId();
  }
  @JsonIgnore
  public LocalDateTime getCreatedAt() {
    return sys.getCreatedAt();
  }

  @JsonIgnore
  public LocalDateTime getUpdatedAt() {
    return sys.getUpdatedAt();
  }

  @JsonIgnore
  public CmsNodeTypeDto getContentType() {
    return sys.getContentType();
  }

  @JsonIgnore
  public Integer getRevision() {
    return sys.getRevision();
  }

  @JsonIgnore
  public String getFieldKey() {
    return field.getKey();
  }

  @JsonIgnore
  public CmsNodeTypeDto getFieldTrafficType() {
    return field.getTrafficType();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .omitNullValues()
        .add("sys", sys)
        .add("field", field)
        .toString();
  }

}
