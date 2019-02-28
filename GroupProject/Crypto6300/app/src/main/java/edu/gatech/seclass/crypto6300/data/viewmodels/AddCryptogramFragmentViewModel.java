package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramRepository;

public class AddCryptogramFragmentViewModel extends AndroidViewModel {

    private CryptogramRepository cryptogramRepository;

    public AddCryptogramFragmentViewModel(@NonNull Application application) {
        super(application);
        cryptogramRepository = new CryptogramRepository(application);
    }
}
