package fi.vr.exercise.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * Contains results of querying {@link MessageTemplate}s.
 *
 * @author Juha-Pekka Koivisto
 */
@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MessageTemplateQueryResult {

  List<MessageTemplate> values;

}
