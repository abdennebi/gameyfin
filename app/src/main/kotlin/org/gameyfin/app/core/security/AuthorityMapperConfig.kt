package org.gameyfin.app.core.security

import org.gameyfin.app.users.RoleService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper

@Configuration
class AuthorityMapperConfig(
    private val roleService: RoleService
) {
    @Bean
    fun userAuthoritiesMapper(): GrantedAuthoritiesMapper {
        return GrantedAuthoritiesMapper { authorities -> roleService.extractGrantedAuthorities(authorities) }
    }
}