package `in`.francl.bassoon.domain.model


data class LinkModel(
    override val target: String,
) : Link {

    companion object {
        fun of(link: Link): Link {
            return LinkModel(link.target)
        }
    }

}
