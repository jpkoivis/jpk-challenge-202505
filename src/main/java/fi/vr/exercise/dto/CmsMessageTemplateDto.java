package fi.vr.exercise.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Value;

/**
 * A JSON object presentation for a 3rd party CMS communication template. Only relevant fields are
 * included.
 *
 * @author Juha-Pekka Koivisto
 */
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CmsMessageTemplateDto {

  private static final String ENTRY_KEY = "Entry";

  @NonNull
  List<CmsMessageTemplateItemDto> items;
  @NonNull
  Map<String, List<CmsMessageTemplateItemDto>> includes;

  @JsonIgnore
  public CmsMessageTemplateItemDto getFirstItem() {
    return items.getFirst();
  }

  public CmsMessageTemplateItemDto getInclude(CmsNodeTypeDto contentType) {
    var items = includes.get(contentType.getLinkType());
    if (items == null) {
      throw new IllegalArgumentException(
        String.format("No items found for linkType: %s", contentType.getLinkType()));
    }

    return items.stream()
         .filter(item -> item.getSysId().equals(contentType.getSysId()))
         .findFirst()
         .orElseThrow(() -> new IllegalArgumentException(
           String.format("No matching include item found {id=%s, linkType=%s}",
                         contentType.getSysId(),
                         contentType.getLinkType())));
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("items", items)
        .add("includes", includes)
        .toString();
  }

}
