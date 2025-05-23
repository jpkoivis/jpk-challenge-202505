package fi.vr.exercise.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import java.util.List;
import lombok.*;

/**
 * A JSON presentation for a CMS content node object.
 *
 * @author Juha-Pekka Koivisto
 */
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CmsMessageContentNodeDto {

  public static final String NODE_TYPE_DOCUMENT = "document";
  public static final String NODE_TYPE_PARAGRAPH = "paragraph";
  public static final String NODE_TYPE_TEXT = "text";
  public static final String NODE_TYPE_EMBEDDED_ENTRY_LINE = "embedded-entry-inline";

  @NonNull
  String nodeType;
  CmsMessageContentDataDto data;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  List<CmsMessageContentNodeDto> content;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  String value;

  @JsonIgnore
  public CmsNodeTypeDto getDataTarget() {
    assert data != null;
    return data.getTarget();
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .omitNullValues()
        .add("nodeType", nodeType)
        .add("data", data)
        .add("content", content)
        .add("value", value)
        .toString();
  }

}
