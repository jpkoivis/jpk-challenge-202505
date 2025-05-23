package fi.vr.exercise.dto;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * A JSON object presentation for a CMS content data.
 */
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class CmsMessageContentDataDto {

  private static final CmsMessageContentDataDto EMPTY = new CmsMessageContentDataDto();

  CmsNodeTypeDto target;

  static CmsMessageContentDataDto empty() {
    return EMPTY;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .omitNullValues()
        .add("target", target)
        .toString();
  }
}
