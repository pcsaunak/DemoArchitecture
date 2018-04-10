package example.saunak.com.demoarchitecture.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import example.saunak.com.demoarchitecture.model.Posts;

@Dao
public abstract class PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(Posts... posts);

    @Query("SELECT * FROM Posts")
    public abstract LiveData<List<Posts>> getAllPostsFromDb();


    @Query("SELECT * FROM Posts limit 1")
    public abstract LiveData<Posts> getSinglePost();
}
