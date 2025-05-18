package de.grimsi.gameyfin.messages.templates

import com.vaadin.hilla.Endpoint
import de.grimsi.gameyfin.core.Role
import jakarta.annotation.security.RolesAllowed

@RolesAllowed(Role.Names.ADMIN)
@Endpoint
class MessageTemplateEndpoint(
    private val messageTemplateService: MessageTemplateService
) {
    fun getAll(): List<MessageTemplateDto> {
        return messageTemplateService.getMessageTemplates()
    }

    fun get(key: String): MessageTemplates {
        return messageTemplateService.getMessageTemplate(key)
    }

    fun getDefaultPlaceholders(type: TemplateType): List<String> {
        return messageTemplateService.getDefaultTemplatePlaceholders(type).keys.toList()
    }

    fun read(key: String, templateType: TemplateType): String {
        return messageTemplateService.getMessageTemplateContent(key, templateType)
    }

    fun save(key: String, templateType: TemplateType, content: String) {
        messageTemplateService.setMessageTemplateContent(key, templateType, content)
    }
}