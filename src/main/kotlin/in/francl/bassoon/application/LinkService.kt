package `in`.francl.bassoon.application

import `in`.francl.bassoon.domain.datatranfer.ShortLink
import `in`.francl.bassoon.domain.model.LinkModel
import `in`.francl.bassoon.domain.service.LinkDomainService
import `in`.francl.bassoon.infrastructure.rdb.LinkRepository
import arrow.core.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext
import kotlin.coroutines.suspendCoroutine


class LinkService(
    private val repository: LinkRepository,
    private val serviceDomain: LinkDomainService,
    private val statService: StatService,
) {

    fun create(urlTarget: String, urlOrigin: String): ShortLink {
        return repository.save(LinkModel(urlTarget)).let {
            val shorten = serviceDomain.encode(it.id.value)
            ShortLink("$urlOrigin/$shorten")
        }
    }

    suspend fun getTargetUrl(shorten: String, remoteHost: String): String? {
        val id = serviceDomain.decode(shorten)
        return repository.findById(id)?.apply {
            coroutineScope {
                withContext(Dispatchers.IO){
                    statService.create(this@apply, remoteHost)
                }
            }
        }?.target
    }

}
