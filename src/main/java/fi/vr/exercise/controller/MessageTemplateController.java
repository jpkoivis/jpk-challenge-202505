package fi.vr.exercise.controller;

import fi.vr.exercise.domain.MessageTemplate;
import fi.vr.exercise.domain.MessageTemplateQuery;
import fi.vr.exercise.domain.MessageTemplateQueryResult;
import fi.vr.exercise.service.MessageTemplateService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * A REST controller for querying {@link MessageTemplate}s. Not in the requirements, for
 * testing purposes.
 *
 * @author Juha-Pekka Koivisto
 */
@RestController
@Profile("rest")
@AllArgsConstructor
public class MessageTemplateController {

  @NonNull
  private final MessageTemplateService service;

  @GetMapping(path = "/templates/{id}",
    produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<MessageTemplate> getById(@PathVariable String id) {
    var result = service.getById(id);
    return ResponseEntity.of(Optional.ofNullable(result));
  }

  @GetMapping(path = "/templates",
              produces = MediaType.APPLICATION_JSON_VALUE)
  MessageTemplateQueryResult query(
      @RequestParam(value = "traffic_type", required = false) String trafficType) {
    var matches = service.query(MessageTemplateQuery.builder()
                                      .trafficType(trafficType)
                                      .build());
    return new MessageTemplateQueryResult(matches);
  }


}
