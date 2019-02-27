package edu.gatech.seclass.crypto6300.data.dao;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import edu.gatech.seclass.crypto6300.data.AppDatabase;
import edu.gatech.seclass.crypto6300.data.entities.User;

import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class UserDaoTest {

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private AppDatabase db;
    private UserDao dao;

    @Mock
    private Observer<List<User>> observer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries().build();
        dao = db.userDao();
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void insert() throws Exception {
        // given
        User user = new User("John", "Smith", "jsmith", "passw0rd", 1, 3, 2);
        dao.getAllUsers().observeForever(observer);

        dao.insert(user);

        verify(observer).onChanged(Collections.singletonList(user));
    }
}
