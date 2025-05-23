package fi.vr.exercise.test.data;

import fi.vr.exercise.domain.MessageTemplate;
import fi.vr.exercise.domain.TrafficType;
import fi.vr.exercise.test.util.DateTimeUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Instantiates {@code MessageTemplate}s for testing purposes.
 *
 * @author Juha-Pekka Koivisto
 */
public class MessageTemplateTestData {

  List<MessageTemplate> templates = new ArrayList<>();
  {
    var now = DateTimeUtil.currentUtcTimeInMillis();
    templates.add(MessageTemplate.builder()
                                 .id("TRAIN_DELAYED")
                                 .trafficType(TrafficType.LONG_DISTANCE)
                                 .createTime(now)
                                 .updateTime(now)
                                 .revision(1)
                                 .content("{TRAIN_NUMBER} delayed")
                                 .build());
    templates.add(MessageTemplate.builder()
                                 .id("SD_TRAIN_CANCELLED")
                                 .trafficType(TrafficType.SHORT_DISTANCE)
                                 .createTime(now.plusMinutes(1))
                                 .updateTime(now.plusMinutes(1))
                                 .revision(1)
                                 .content("{TRAIN_NUMBER} cancelled")
                                 .build());
  }

  public MessageTemplate getFirst() {
    return templates.getFirst();
  }

  public List<MessageTemplate> getTemplates() {
    return templates;
  }

}
