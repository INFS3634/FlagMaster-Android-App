package au.edu.unsw.infs3634.unswlearning;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

public class Popup extends AppCompatActivity {
    Button easyQuiz = findViewById(R.id.easyQuiz);
    Button mediumQuiz = findViewById(R.id.mediumQuiz);
    Button hardQuiz = findViewById(R.id.hardQuiz);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_level_popup);
    }

    public void showPopUpWindow() {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.quiz_level_popup, null);

        //
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popup = new PopupWindow(popupView, width, height);

        boolean focusable = true; // lets taps outside the popup also dismiss it
        getWindow().setLayout(width, height);

        //Close the popup window when touching outside
        popup.setBackgroundDrawable(new BitmapDrawable());
        popup.setOutsideTouchable(true);
    }
}

