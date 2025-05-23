package fi.vr.exercise.test.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;

/**
 * Provides helper methods related to {@code ObjectMapper}.
 *
 * @author Juha-Pekka Koivisto
 */
public class ObjectMapperHelper {

  private final ObjectMapper objectMapper;

  public ObjectMapperHelper() {
    this(new ObjectMapper());
  }

  public ObjectMapperHelper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public <T> T readValue(InputStream is,
                         Class<T> classType) {
    try {
      return objectMapper.readValue(is, classType);
    } catch (IOException ex) {
      throw new IllegalStateException("Failed to read object from input stream.", ex);
    }
  }

  public <T> T readValue(String resourcePath, Class<T> classType) {
    try {
      var is = getResourceAsStream(resourcePath);
      return objectMapper.readValue(is, classType);
    } catch (IOException ex) {
      throw new IllegalArgumentException(
        String.format("Failed to read object from resource path: %s", resourcePath),
        ex);
    }
  }

  private static InputStream getResourceAsStream(String name) {
    ClassLoader classLoader = getClassLoader();
    return classLoader.getResourceAsStream(name);
  }

  private static ClassLoader getClassLoader() {
    return ObjectMapperHelper.class.getClassLoader();
  }

}
