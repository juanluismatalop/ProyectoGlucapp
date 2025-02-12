import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectoglucapp.data.local.NoticiaDao
import com.example.proyectoglucapp.data.local.PhotoDao
import com.example.proyectoglucapp.data.local.PhotoEntity
import com.example.proyectoglucapp.domain.models.Noticia

@Database(entities = [PhotoEntity::class, Noticia::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao
    abstract fun noticiaDao(): NoticiaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "diabetapp_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
