package org.gameyfin.app.libraries

import com.vaadin.hilla.Endpoint
import org.gameyfin.app.libraries.dto.LibraryDto
import org.gameyfin.app.libraries.dto.LibraryEvent
import jakarta.annotation.security.PermitAll
import jakarta.annotation.security.RolesAllowed
import org.gameyfin.app.core.Role
import org.gameyfin.app.libraries.dto.LibraryScanProgress
import org.gameyfin.app.libraries.dto.LibraryUpdateDto
import org.gameyfin.app.libraries.enums.ScanType
import org.gameyfin.app.users.util.isAdmin
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import reactor.core.publisher.Flux

@Endpoint
@PermitAll
class LibraryEndpoint(
    private val libraryService: LibraryService
) {
    fun subscribeToLibraryEvents(): Flux<List<LibraryEvent>> {
        return LibraryService.subscribeToLibraryEvents()
    }

    fun getAll() = libraryService.getAll()


    fun subscribeToScanProgressEvents(): Flux<List<LibraryScanProgress>> {
        val user = SecurityContextHolder.getContext().authentication.principal as UserDetails
        return if (user.isAdmin()) LibraryService.subscribeToScanProgressEvents()
        else Flux.empty()
    }

    @RolesAllowed(Role.Names.ADMIN)
    fun triggerScan(scanType: ScanType = ScanType.QUICK, libraries: Collection<LibraryDto>?) =
        libraryService.triggerScan(scanType, libraries)

    @RolesAllowed(Role.Names.ADMIN)
    fun createLibrary(library: LibraryDto, scanAfterCreation: Boolean = true) =
        libraryService.create(library, scanAfterCreation)

    @RolesAllowed(Role.Names.ADMIN)
    fun updateLibrary(library: LibraryUpdateDto) = libraryService.update(library)

    @RolesAllowed(Role.Names.ADMIN)
    fun deleteLibrary(libraryId: Long) = libraryService.delete(libraryId)
}