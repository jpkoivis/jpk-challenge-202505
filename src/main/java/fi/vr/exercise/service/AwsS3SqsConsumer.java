package fi.vr.exercise.service;

import com.amazonaws.services.s3.event.S3EventNotification;
import fi.vr.exercise.dto.CmsMessageTemplateDto;
import fi.vr.exercise.mapper.MessageTemplateMapper;
import io.awspring.cloud.s3.S3Template;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Handles S3 bucket notifications. Reads and deserializes the CMS communication template file
 * associated with the event. Converts it to {@code MessageTemplate} and persists it.
 *
 * @author Juha-Pekka Koivisto
 */
@Component
@Profile("aws")
@Slf4j
@AllArgsConstructor
public class AwsS3SqsConsumer {

  @NonNull
  private final S3Template template;
  @NonNull
  private final MessageTemplateService templateService;

  // TODO: check how acknowledgement is handled in case of an error (e.g., database down).
  // Do we need retry with backoff policy.
  @SqsListener(value = "${message-template-service.s3-sqs-consumer.queue-name}")
  public void onS3EventNotification(S3EventNotification notification) {
    log.debug("S3 notification received {recordCount={}}.", notification.getRecords().size());
    for (var record : notification.getRecords()) {
      var bucket = record.getS3().getBucket().getName();
      var fileName = record.getS3().getObject().getKey();
      var cmsMessageTemplate = readCmsMessageTemplate(bucket, fileName);
      var template = MessageTemplateMapper.INSTANCE.apply(cmsMessageTemplate);
      templateService.store(template);
    }
  }

  private CmsMessageTemplateDto readCmsMessageTemplate(String bucketName, String fileName) {
    log.debug("Reading CMS template {bucket={}, fileName={}}.", bucketName, fileName);
    return template.read(bucketName, fileName, CmsMessageTemplateDto.class);
  }

}
