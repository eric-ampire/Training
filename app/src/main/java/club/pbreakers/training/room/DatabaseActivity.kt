package club.pbreakers.training

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.AsyncTaskLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.RecyclerView
import androidx.room.*
import kotlinx.android.synthetic.main.activity_profile.*
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.Serializable


class ProfileActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<List<User>> {
    override fun onLoadFinished(loader: Loader<List<User>>, data: List<User>    ) {
        val adapter = MyAdapter(data)
        rvUser.adapter = adapter
    }

    override fun onLoaderReset(loader: Loader<List<User>>) { }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<List<User>> {
        val user = args!!.getSerializable("user") as User
        return MyLoader(this, user)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        btnSave.setOnClickListener {
            saveUser()
        }
    }

    private fun saveUser() {

        val user = User(
            id = System.currentTimeMillis().toInt(),
            login = edtLogin.text.toString(),
            avatar_url = edtAvatar.text.toString()
        )

        val bundle = Bundle().apply {
            putSerializable("user", user)
        }

        LoaderManager.getInstance(this).initLoader(1, bundle, this)
    }
}

class MyAdapter(val data: List<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text1.text = data[position].login
        holder.text2.text = data[position].avatar_url
    }

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val text1 = view.findViewById<TextView>(R.id.textView)!!
        val text2 = view.findViewById<TextView>(R.id.textView2)!!
    }
}

class MyLoader(private val ctx: Context, val string: User) : AsyncTaskLoader<List<User>>(ctx) {
    override fun loadInBackground(): List<User> {

        val db = Room.databaseBuilder(ctx, TrainingDatabase::class.java, "user").build()
        db.userDao().insert(string)
        return db.userDao().getAll()
    }
}


@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: Int,
    val login: String,
    var avatar_url: String
) : Serializable

@Database(entities = [User::class], version = 2, exportSchema = true)
abstract class TrainingDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
}

interface WebService {

    @GET("users/")
    fun getAllUser() : List<User>

    @GET("users/{id}")
    fun getOne(@Path("id") id: String)
}

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg user: User)

    @Query("SELECT * FROM user")
    fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id = :id")
    fun getOne(id: Int): User

    @Update
    fun update(user: User)

    @Delete
    fun delete(vararg delete: User)
}


