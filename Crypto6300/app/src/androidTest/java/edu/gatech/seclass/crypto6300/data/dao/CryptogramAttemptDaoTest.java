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
import edu.gatech.seclass.crypto6300.data.entities.Attempts;
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram;
import edu.gatech.seclass.crypto6300.data.entities.CryptogramAttempt;
import edu.gatech.seclass.crypto6300.data.entities.User;

import static org.mockito.Mockito.verify;


@RunWith(AndroidJUnit4.class)
public class CryptogramAttemptDaoTest {
    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    private AppDatabase db;
    private UserDao userDao;
    private CryptogramDao cryptogramDao;
    private CryptogramAttemptDao attemptDao;

    @Mock
    private Observer<List<CryptogramAttempt>> observer;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries().build();
        userDao = db.userDao();
        cryptogramDao = db.cryptogramDao();
        attemptDao = db.cryptogramAttemptDao();
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void insertAttemptAndGetAttempt() throws Exception {
        User user = new User("John", "Smith", "jsmith", "passw0rd", 1, 3, 2);
        Cryptogram cryptogram = new Cryptogram("Caesar", "android", 2, new Attempts(2,3,4));
        CryptogramAttempt attempt = new CryptogramAttempt(1, 1, 3, "fjkdla", "quka kdjv", false, false);

        userDao.insert(user);
        cryptogramDao.insert(cryptogram);

        attemptDao.getAllCryptogramAttempts().observeForever(observer);
        attemptDao.insertAttempt(attempt);

        verify(observer).onChanged(Collections.singletonList(attempt));
    }
}
