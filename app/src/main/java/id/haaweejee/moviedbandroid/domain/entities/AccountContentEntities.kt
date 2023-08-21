package id.haaweejee.moviedbandroid.domain.entities

data class AccountContentEntities(
    val header: AccountDetailEntities,
    val list: List<AccountItemEntities>,
)
