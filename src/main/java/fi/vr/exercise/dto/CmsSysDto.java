package fi.vr.exercise.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.google.common.base.MoreObjects;
import java.time.LocalDateTime;
import lombok.*;

/**
 * A JSON object presentation for a CMS sys metadata.
 *
 * @author Juha-Pekka Koivisto
 */
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CmsSysDto {
  @NonNull
  String id;
  @NonNull
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  LocalDateTime createdAt;
  @NonNull
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  LocalDateTime updatedAt;
  @NonNull
  Integer revision;
  @NonNull
  CmsNodeTypeDto contentType;

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .omitNullValues()
        .add("id", id)
        .add("createdAt", createdAt)
        .add("updatedAt", updatedAt)
        .add("revision", revision)
        .add("contentType", contentType)
        .toString();
  }

}
