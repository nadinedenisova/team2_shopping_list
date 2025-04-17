package acr.appcradle.shoppinglist.di

import acr.appcradle.shoppinglist.ShoppingDatabase
import acr.appcradle.shoppinglist.ShoppingItemsQueries
import acr.appcradle.shoppinglist.ShoppingListQueries
import acr.appcradle.shoppinglist.data.ListRepositoryImpl
import acr.appcradle.shoppinglist.model.ListRepository
import acr.appcradle.shoppinglist.utils.DbFactory
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//region HILT TUTORIAL

//Создание модуля

//@Module
//@InstallIn(SingletonComponent::class) // Зависимости доступны на уровне всего приложения
//object DatabaseModule {
//    @Provides
//    @Singleton
//    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
//        return Room.databaseBuilder(context, AppDatabase::class.java, "db").build()
//    }
//}

//Создание класса

//@Singleton
//class Repository @Inject constructor() : RepositoryInterface {
//    var example = 0
//    override fun getValue() = example
//
//    override fun setValue() {
//        example++
//    }
//}

//Создание вьюмодели

//@HiltViewModel
//class UserViewModel @Inject constructor(
//    private val userRepository: UserRepository
//) : ViewModel() {
//
//}

// Использование в функциях

//@Composable
//fun UserScreen(viewModel: UserViewModel = hiltViewModel()) {
//    val users by viewModel.users.collectAsState()
//    LazyColumn {
//        items(users) { user -> ... }
//    }
//}

//endregion


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDbFactory(
        @ApplicationContext context: Context
    ): DbFactory = DbFactory(context)

    @Provides
    @Singleton
    fun provideShoppingDatabase(
        dbFactory: DbFactory
    ): ShoppingDatabase = dbFactory.createDatabase()

    @Provides
    fun provideShoppingQueries(
        database: ShoppingDatabase
    ): ShoppingListQueries = database.shoppingListQueries
}

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindListRepository(impl: ListRepositoryImpl): ListRepository
}