package edu.gatech.seclass.crypto6300.data.viewmodels;

import android.app.Application;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import edu.gatech.seclass.crypto6300.data.entities.Attempts;
import edu.gatech.seclass.crypto6300.data.entities.ChooseCryptogram;
import edu.gatech.seclass.crypto6300.data.entities.Cryptogram;
import edu.gatech.seclass.crypto6300.data.entities.CryptogramAttempt;
import edu.gatech.seclass.crypto6300.data.entities.User;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramAttemptsRepository;
import edu.gatech.seclass.crypto6300.data.repositories.CryptogramRepository;
import edu.gatech.seclass.crypto6300.data.repositories.UserRepository;

public class ChooseCryptogramFragmentViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private CryptogramRepository cryptogramRepository;
    private CryptogramAttemptsRepository attemptsRepository;

    public ChooseCryptogramFragmentViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        cryptogramRepository = new CryptogramRepository(application);
        attemptsRepository = new CryptogramAttemptsRepository(application);
    }

    public LiveData<List<ChooseCryptogram>> getList(String playerId) {
        return attemptsRepository.getChooseList(playerId);
    }

    public LiveData<CryptogramAttempt> getAttemptForPlayer(String playerId, String cryptogramId) {
        return attemptsRepository.getAttemptByUserIdAndCryptogramId(playerId, cryptogramId);
    }

    public void generateAttemptForPlayer(
            @NonNull User player,
            @NonNull ChooseCryptogram cryptogram,
            CryptogramAttemptsRepository.insertAttemptAsyncTask.InsertResponse response) {

        CryptogramAttempt attempt = new CryptogramAttempt(
                player.getId(),
                cryptogram.getCryptogramId(),
                getAttemptsRemaining(player, cryptogram.getAttempts()),
                "",
                generateEncryptedPhrase(cryptogram.getSolution()),
                false,
                false
        );

        attemptsRepository.insert(attempt, response);
    }

    private String generateEncryptedPhrase(String solution) {
        // TODO: implement scrambling
        char c;
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int N = alphabet.length();
        Set<Character> charSet = new HashSet<Character>();
        for (char l : solution.toCharArray()) {
            if (Character.isLetter(l)){charSet.add(l);}

        }
        for (int i=0; i<solution.length();i++){
            c= solution.charAt((i));
            if (charSet.contains(c)){
                Random rand = new Random();
                String r= Character.toString(alphabet.charAt(rand.nextInt(N)));
                if(Character.isUpperCase(c)) {

                    //System.out.println(alphabet.charAt(rand.nextInt(N)));
                    solution= solution.replaceAll(Character.toString(c),r);
                    charSet.remove(c);
                    if (charSet.contains(Character.toLowerCase(c))){
                        solution=solution.replaceAll(Character.toString(Character.toLowerCase(c)),r.toLowerCase());

                        charSet.remove(Character.toLowerCase(c));
                    }

                }
                if(Character.isLowerCase(c)) {

                    solution=solution.replaceAll(Character.toString(c),r.toUpperCase());
                    charSet.remove(c);
                    if (charSet.contains(Character.toUpperCase(c))){
                        solution=solution.replaceAll(Character.toString(Character.toUpperCase(c)),r);
                        charSet.remove(Character.toUpperCase(c));
                    }



                }

            }
        }
        
        return solution;
    }

    private int getAttemptsRemaining(@NonNull User user, @NonNull Attempts attempts) {
        if (user.getCategory() == null) return 0;

        int numAttempts = 0;
        if (user.getCategory() == 1) {
            numAttempts = attempts.getEasy();
        } else if (user.getCategory() == 2) {
            numAttempts = attempts.getNormal();
        } else if (user.getCategory() == 3) {
            numAttempts = attempts.getHard();
        }
        return numAttempts;
    }
}
