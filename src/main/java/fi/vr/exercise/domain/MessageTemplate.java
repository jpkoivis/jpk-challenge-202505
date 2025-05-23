package fi.vr.exercise.domain;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * A domain presentation for a message template.
 *
 * @author Juha-Pekka Koivisto
 */
@Value
@Builder(toBuilder = true)
public class MessageTemplate {

  @NonNull
  String id;
  @NonNull
  String trafficType;
  @NonNull
  LocalDateTime createTime;
  @NonNull
  LocalDateTime updateTime;
  @NonNull
  Integer revision;
  @NonNull
  String content;

}
