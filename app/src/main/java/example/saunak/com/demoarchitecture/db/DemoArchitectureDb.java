package example.saunak.com.demoarchitecture.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import example.saunak.com.demoarchitecture.model.Posts;

@Database(entities = {Posts.class}, version = 1, exportSchema = false)
public abstract class DemoArchitectureDb extends RoomDatabase {

    public abstract PostsDao providePostsDao();
}
