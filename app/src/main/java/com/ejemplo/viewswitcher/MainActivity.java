package com.ejemplo.viewswitcher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad en la que se prueban dos widgets: ViewSwitcher y RatingBar
 * @version 1.0
 * @author Rafa
 */
public class MainActivity extends AppCompatActivity {

    private float ratingBarResult;
    private TextView textView;
    private Button switchButton;
    private ViewSwitcher viewSwitcher;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //se obtienen las referencias a los widgets de nuestra interfaz para trabajar con ellos
        switchButton = findViewById(R.id.button);
        viewSwitcher = findViewById(R.id.viewSwitcher);
        ratingBar =  findViewById(R.id.ratingBar);
        textView = findViewById(R.id.textView);
        //Valor máximo del RatingBar
        ratingBar.setMax(10);
        //Número de estrellas igual al valor máximo
        ratingBar.setNumStars(10);

        //Método de evento para escuchar los cambios en el RatingBar
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                //Se obtiene el valor correspondiente al desplazamiento
                //del Ratingbar y se guarda en la propiedad de clase para después mostrarlo
                //en un TextView
                setRatingBarResult(v);
            }
        });
        //Método de evento para escuchar el Click sobre el botón
        //, se detecta la vista actual del ViewSwitcher. En caso de que la vista
        //actual sea el TextView se muestra el valor del RatingBar, sino se
        //le dice al ViewSwitcher que muestra la siguiente vista (es decir el RatingBar)
        switchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewSwitcher.getCurrentView() == ratingBar) {
                    viewSwitcher.showNext();//if the current view is the RatingBar, then show
                    //the next one, which is the TextView
                    textView.setText(ratingBarResult + getString(R.string.max_rating_value));

                }
                else {
                    viewSwitcher.showPrevious();
                }
            }
        });
    }
    //Métod para guardar el valor del RatingBar
    public void setRatingBarResult(float l) {
        ratingBarResult = l;
    }
}