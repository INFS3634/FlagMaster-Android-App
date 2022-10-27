package au.edu.unsw.infs3634.unswlearning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

//SecondFragment for Quiz page
public class QuizFragment extends Fragment {

    /*public QuizFragment() {
        super(R.layout.fragment_quiz);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false);



        //https://www.geeksforgeeks.org/how-to-implement-bottom-navigation-with-activities-in-android/
    }

}
