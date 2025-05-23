package fi.vr.exercise.test.data;

import fi.vr.exercise.dto.CmsMessageTemplateDto;
import fi.vr.exercise.test.util.ObjectMapperHelper;

/**
 * Instantiates and reads {@code CmsMessageTemplate}s for testing purposes.
 *
 * @author Juha-Pekka Koivisto
 */
public class CmsMessageTemplateTestData {

  private final ObjectMapperHelper objectMapper = new ObjectMapperHelper();

  public CmsMessageTemplateDto readTemplate(String name) {
    return objectMapper.readValue(name, CmsMessageTemplateDto.class);
  }

}
