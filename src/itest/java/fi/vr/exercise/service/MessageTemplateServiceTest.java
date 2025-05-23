package fi.vr.exercise.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import fi.vr.exercise.domain.MessageTemplateQuery;
import fi.vr.exercise.domain.TrafficType;
import fi.vr.exercise.test.data.MessageTemplateTestData;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Integration tests for {@link MessageTemplateService}.
 *
 * @author Juha-Pekka Koivisto
 */
public class MessageTemplateServiceTest extends PostgresIntegrationTestCase {

  private final MessageTemplateTestData testData = new MessageTemplateTestData();

  @Autowired
  MessageTemplateService sut;

  @Test
  public void create_and_get_template_by_id() {
    var template = testData.getFirst();
    var created = sut.store(template);
    var persisted = sut.getById(template.getId());

    assertEquals(created, persisted);
    assertEquals(template, persisted);
  }

  @Test
  public void update_template() {
    var template = testData.getFirst();
    var updatedTemplate = template.toBuilder()
                                  .updateTime(template.getCreateTime().plusMinutes(1))
                                  .revision(template.getRevision() + 1)
                                  .content("{TRAIN_NUMBER} DELAYED")
                                  .build();

    sut.store(template);
    sut.store(updatedTemplate);
    var persisted = sut.getById(template.getId());

    assertEquals(updatedTemplate, persisted);
  }

  @Test
  public void create_template_duplicate_ignored() {
    var template = testData.getFirst();

    sut.store(template);
    sut.store(template);

    var result = sut.query(MessageTemplateQuery.all());

    assertEquals(List.of(template), result);
  }

  @Test
  public void query_templates_by_traffic_type() {
    testData.getTemplates().forEach(sut::store);

    var matches = sut.query(MessageTemplateQuery.builder()
                              .trafficType(TrafficType.SHORT_DISTANCE)
                              .build());

    for (var actual : matches) {
      assertEquals(TrafficType.SHORT_DISTANCE, actual.getTrafficType());
    }
  }

  @Test
  public void get_returns_null_if_template_not_exists() {
    var result = sut.getById("NOT_FOUND");

    assertNull(result);
  }

}
