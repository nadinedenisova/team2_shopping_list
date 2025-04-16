package acr.appcradle.shoppinglist.di


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