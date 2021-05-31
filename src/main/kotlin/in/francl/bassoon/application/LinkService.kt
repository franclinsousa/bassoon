package `in`.francl.bassoon.application

import `in`.francl.bassoon.domain.datatranfer.ShortLink
import `in`.francl.bassoon.domain.model.LinkModel
import `in`.francl.bassoon.domain.service.LinkDomainService
import `in`.francl.bassoon.infrastructure.rdb.LinkRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.time.withTimeoutOrNull
import java.time.Duration


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

    fun getTargetUrl(shorten: String, remoteHost: String): String? {
        val id = serviceDomain.decode(shorten)
        return repository.findById(id)?.apply link@ {
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    statService.create(this@link, remoteHost)
                }
            }
        }?.target
    }

}
