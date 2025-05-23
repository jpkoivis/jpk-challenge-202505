package fi.vr.exercise.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import lombok.*;

/**
 * A JSON object presentation for CMS node type.
 *
 * @author Juha-Pekka Koivisto
 */
@Value
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class CmsNodeTypeDto {

  private static final CmsNodeTypeDto PARAMETER = contentNode("parameter");
  private static final CmsNodeTypeDto TRAFFIC_TYPE = contentNode("traffic-type");

  @NonNull
  CmsLinkedNodeSys sys;

  public static CmsNodeTypeDto entryType(String id) {
    return new CmsNodeTypeDto(new CmsLinkedNodeSys(id, "Link", "Entry"));
  }

  public static CmsNodeTypeDto contentNode(String id) {
    return new CmsNodeTypeDto(new CmsLinkedNodeSys(id, "Link", "ContentType"));
  }

  public static CmsNodeTypeDto parameter() {
    return PARAMETER;
  }

  public static CmsNodeTypeDto trafficType() {
    return TRAFFIC_TYPE;
  }

  @JsonIgnore
  public String getSysId() {
    return sys.getId();
  }

  @JsonIgnore
  public String getLinkType() {
    return sys.getLinkType();
  }

  @JsonIgnore
  public boolean isTrafficType() {
    return this.equals(TRAFFIC_TYPE);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("sys", sys)
        .toString();
  }

  /**
   * A JSON object presentation for CMS linked node sys metadata.
   */
  @Value
  @NoArgsConstructor(force = true)
  @AllArgsConstructor
  public static class CmsLinkedNodeSys {
    String id;
    String type;
    String linkType;

    @Override
    public String toString() {
      return MoreObjects.toStringHelper(this)
          .add("id", id)
          .add("type", type)
          .add("linkedType", linkType)
          .toString();
    }
  }
}
