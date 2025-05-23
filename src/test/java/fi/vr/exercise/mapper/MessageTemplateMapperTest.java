package fi.vr.exercise.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fi.vr.exercise.domain.MessageTemplate;
import fi.vr.exercise.domain.TrafficType;
import fi.vr.exercise.test.data.CmsMessageTemplateTestData;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MessageTemplateMapper}.
 *
 * @author Juha-Pekka Koivisto
 */
public class MessageTemplateMapperTest {

  private final CmsMessageTemplateTestData testData = new CmsMessageTemplateTestData();

  private final MessageTemplate expected = MessageTemplate.builder()
    .id("TRAIN_DELAYED")
    .trafficType(TrafficType.LONG_DISTANCE)
    .createTime(LocalDateTime.parse("2025-01-01T00:00"))
    .updateTime(LocalDateTime.parse("2025-01-01T00:00:00.001"))
    .revision(1)
    .content("""
      {TRAIN_TYPE} {TRAIN_NUMBER} - delayed
      Dear passengers, train {TRAIN_TYPE} {TRAIN_NUMBER} is delayed due to {DELAY_REASON}.
      We apologise for the inconvenience.
      """)
    .build();

  @Test
  public void map_cms_message() {
    var sut = MessageTemplateMapper.INSTANCE;
    var template = testData.readTemplate("template.json");

    var actual = sut.apply(template);

    assertEquals(expected, actual);
  }

}
