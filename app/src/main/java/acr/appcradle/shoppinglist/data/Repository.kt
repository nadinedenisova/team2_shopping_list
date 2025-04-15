package acr.appcradle.shoppinglist.data

import acr.appcradle.shoppinglist.model.RepositoryInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton  //аннотация класса в единственном экземпляре
class Repository @Inject constructor() : RepositoryInterface {


    //Hilt примеры, можно удалить
    var example = 0
    override fun getValue() = example

    override fun setValue() {
        example++
    }

}