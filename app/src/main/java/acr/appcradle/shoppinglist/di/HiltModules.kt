package acr.appcradle.shoppinglist.di

import acr.appcradle.shoppinglist.data.Repository
import acr.appcradle.shoppinglist.model.RepositoryInterface
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
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