package edu.gatech.seclass.crypto6300.data.dao;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import edu.gatech.seclass.crypto6300.data.AppDatabase;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.entities.UserKt;
import kotlin.Unit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class UserDaoTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private AppDatabase db;
    private UserDao dao;
    private User adminUser = new User(
            "admin",
            "admin",
            "admin",
            "admin",
            null,
            0,
            0
    );

    @Mock
    private Observer<List<User>> observer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        prepopulateDb();
                    }
                })
                .allowMainThreadQueries()
                .build();
        dao = db.userDao();
    }

    private void prepopulateDb() {
        new PopulateDbAsync(dao).execute();
    }

    class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        UserDao userDao;

        PopulateDbAsync(UserDao userDao) {
            this.userDao = userDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(adminUser);
            return null;
        }

    }

    @After
    public void tearDown() {
        db.close();
    }


    @Test
    public void testAdminUser() throws InterruptedException {
        dao.getAllUsers().observeForever(observer);
        verify(observer).onChanged(
                Collections.singletonList(adminUser)
        );
    }

    @Test
    public void insertUserAndGetUser() {
        User user = new User("John", "Smith", "jsmith", "passw0rd", 1, 3, 2);
        dao.getAllUsers().observeForever(observer);

        dao.insert(user);

        verify(observer).onChanged(Collections.singletonList(user));
    }
}
