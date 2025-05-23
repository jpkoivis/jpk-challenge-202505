package fi.vr.exercise.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import fi.vr.exercise.test.util.ObjectMapperHelper;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * A unit test that targets JSON deserialization of {@link CmsMessageTemplateDto}.
 *
 * @author Juha-Pekka Koivisto
 */
public class CmsMessageTemplateDtoDeserializeTest {

  private final ObjectMapperHelper objectMapper = new ObjectMapperHelper();
  private final CmsMessageTemplateDto expected = new CmsMessageTemplateDto(
    List.of(
      CmsMessageTemplateItemDto.builder()
        .sys(sys("111-111-111", CmsNodeTypeDto.contentNode("message-template")))
        .field(
          CmsMessageTemplateItemFieldDto.builder()
            .key("TRAIN_DELAYED")
            .name("Juna myöhässä")
            .trafficType(CmsNodeTypeDto.entryType("222-222-222"))
            .subject(
              documentNode(
                paragraphNode(
                  textNode(""),
                  linkNode("333-333-333"),
                  textNode(" "),
                  linkNode("444-444-444"),
                  textNode(""),
                  textNode(" - "),
                  textNode("delayed")
                )
              )
            )
            .body(
              documentNode(
                paragraphNode(
                  textNode("Dear passengers, train "),
                  linkNode("333-333-333"),
                  textNode(" "),
                  linkNode("444-444-444"),
                  textNode(" is delayed due to "),
                  linkNode("555-555-555"),
                  textNode(".")
                ),
                paragraphNode(
                  textNode("We apologise for the inconvenience.")
                )
              )
            )
            .build()
        )
        .build()),
    Map.of(
      "Entry",
      List.of(
        CmsMessageTemplateItemDto.builder()
          .sys(sys("444-444-444", CmsNodeTypeDto.contentNode("parameter")))
          .field(CmsMessageTemplateItemFieldDto.builder()
            .key("TRAIN_NUMBER")
            .name("Junan numero")
            .build())
          .build(),
        CmsMessageTemplateItemDto.builder()
          .sys(sys("222-222-222", CmsNodeTypeDto.trafficType()))
          .field(CmsMessageTemplateItemFieldDto.builder()
            .key("LONG_DISTANCE")
            .name("Kaukoliikenne")
            .build())
          .build(),
        CmsMessageTemplateItemDto.builder()
          .sys(sys("333-333-333", CmsNodeTypeDto.parameter()))
          .field(CmsMessageTemplateItemFieldDto.builder()
            .key("TRAIN_TYPE")
            .name("Junalaji")
            .build())
          .build(),
        CmsMessageTemplateItemDto.builder()
          .sys(sys("555-555-555", CmsNodeTypeDto.contentNode("parameter")))
          .field(CmsMessageTemplateItemFieldDto.builder()
            .key("DELAY_REASON")
            .name("Myöhästymisen syy")
            .build())
          .build()
      )
    )
  );

  @Test
  public void deserialize_from_json() {
    var actual = objectMapper.readValue("template.json",
                                        CmsMessageTemplateDto.class);

    assertEquals(expected, actual);
  }

  private CmsSysDto sys(String id, CmsNodeTypeDto contentType) {
    return CmsSysDto.builder()
      .id(id)
      .createdAt(LocalDateTime.parse("2025-01-01T00:00"))
      .updatedAt(LocalDateTime.parse("2025-01-01T00:00:00.001"))
      .revision(1)
      .contentType(contentType)
      .build();
  }

  private CmsMessageContentNodeDto documentNode(CmsMessageContentNodeDto... nodes) {
    return CmsMessageContentNodeDto.builder()
      .nodeType("document")
      .data(CmsMessageContentDataDto.empty())
      .content(List.of(nodes))
      .build();
  }

  private CmsMessageContentNodeDto paragraphNode(CmsMessageContentNodeDto... nodes) {
    return CmsMessageContentNodeDto.builder()
      .nodeType("paragraph")
      .data(CmsMessageContentDataDto.empty())
      .content(List.of(nodes))
      .build();
  }

  private CmsMessageContentNodeDto textNode(String value) {
    return CmsMessageContentNodeDto.builder()
      .nodeType("text")
      .data(CmsMessageContentDataDto.empty())
      .value(value)
      .build();
  }

  private CmsMessageContentNodeDto linkNode(String id) {
    return CmsMessageContentNodeDto.builder()
      .nodeType("embedded-entry-inline")
      .data(CmsMessageContentDataDto.builder()
        .target(CmsNodeTypeDto.entryType(id))
        .build())
      .content(Collections.emptyList())
      .build();
  }

}
