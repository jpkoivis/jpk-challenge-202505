package fi.vr.exercise.mapper;

import fi.vr.exercise.dto.CmsMessageContentNodeDto;
import fi.vr.exercise.dto.CmsMessageTemplateDto;
import java.util.function.Function;

/**
 * Builds a {@code String} message template from {@link CmsMessageTemplateDto}.
 *
 * @author Juha-Pekka Koivisto
 */
enum TextMessageTemplateBuilder implements Function<CmsMessageTemplateDto, String> {
  INSTANCE;

  private static final int DEFAULT_TEMPLATE_SIZE = 200;

  @Override
  public String apply(CmsMessageTemplateDto template) {
    var builder = new StringBuilder(DEFAULT_TEMPLATE_SIZE);
    var item = template.getFirstItem();
    var field = item.getField();
    appendNode(builder, template, field.getSubject());
    appendNode(builder, template, field.getBody());
    return builder.toString();
  }

  private void appendNode(StringBuilder builder,
                          CmsMessageTemplateDto template,
                          CmsMessageContentNodeDto node) {
    switch (node.getNodeType()) {
      case CmsMessageContentNodeDto.NODE_TYPE_DOCUMENT ->
        appendSubNodes(builder, template, node);
      case CmsMessageContentNodeDto.NODE_TYPE_PARAGRAPH ->
        appendParagraphNode(builder, template, node);
      case CmsMessageContentNodeDto.NODE_TYPE_TEXT ->
        appendTextNode(builder, node);
      case CmsMessageContentNodeDto.NODE_TYPE_EMBEDDED_ENTRY_LINE ->
        appendParameterNode(builder, template, node);
    }
  }

  private void appendParameterNode(StringBuilder builder,
                                   CmsMessageTemplateDto template,
                                   CmsMessageContentNodeDto node) {
    var includeNode = template.getInclude(node.getDataTarget());
    builder.append('{');
    builder.append(includeNode.getFieldKey());
    builder.append('}');
  }

  private void appendParagraphNode(StringBuilder builder,
                                   CmsMessageTemplateDto template,
                                   CmsMessageContentNodeDto node) {
    appendSubNodes(builder, template, node);
    builder.append("\n");
  }

  private void appendSubNodes(StringBuilder builder,
                              CmsMessageTemplateDto template,
                              CmsMessageContentNodeDto node) {
    for (var subNode : node.getContent()) {
      appendNode(builder, template, subNode);
    }
  }

  private void appendTextNode(StringBuilder builder, CmsMessageContentNodeDto node) {
    builder.append(node.getValue());
  }

}
