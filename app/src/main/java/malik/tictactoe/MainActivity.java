package malik.tictactoe;

import android.app.ActionBar;
import android.content.Intent;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.EditText;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public EditText plyr1;
    public EditText plyr2;

    public CharSequence player1 = "Player 1";
    public CharSequence player2 = "Player 2";

    public CharSequence cloneplayer2;
    boolean player1ax = true;
    boolean selectedSinglePlayer;
    boolean easy = true;
    boolean medium = false;
    boolean hard = false;
    boolean impossible = false;
    public CheckBox p1x, p1o, p2x, p2o, singleplayer, twoplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//apply the animation ( fade In ) to your LAyout

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }


        plyr1 = (EditText) findViewById(R.id.playerone);
        plyr2 = (EditText) findViewById(R.id.playertwo);


        p1x = (CheckBox) findViewById(R.id.player1x);
        p1o = (CheckBox) findViewById(R.id.player1o);
        p2x = (CheckBox) findViewById(R.id.player2x);
        p2o = (CheckBox) findViewById(R.id.player2o);
        singleplayer = (CheckBox) findViewById(R.id.splayer);
        twoplayer = (CheckBox) findViewById(R.id.tplayer);

        p1x.setOnClickListener(checkboxClickListener);
        p1o.setOnClickListener(checkboxClickListener);
        p2x.setOnClickListener(checkboxClickListener);
        p2o.setOnClickListener(checkboxClickListener);
        singleplayer.setOnClickListener(checkboxClickListener);
        twoplayer.setOnClickListener(checkboxClickListener);



        p1x.setChecked(true);
        p2o.setChecked(true);
        twoplayer.setChecked(true);



    }



    View.OnClickListener checkboxClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            boolean checked = ((CheckBox) view).isChecked();
            if (checked) {
                switch (view.getId()) {
                    case R.id.player1x:
                        p1o.setChecked(false);
                        p2x.setChecked(false);
                        p2o.setChecked(true);
                        player1ax = true;
                        break;
                    case R.id.player1o:
                        p1x.setChecked(false);
                        p2o.setChecked(false);
                        p2x.setChecked(true);
                        player1ax = false;
                        break;
                    case R.id.player2x:
                        p2o.setChecked(false);
                        p1x.setChecked(false);
                        p1o.setChecked(true);
                        player1ax = false;
                        break;
                    case R.id.player2o:
                        p2x.setChecked(false);
                        p1o.setChecked(false);
                        p1x.setChecked(true);
                        player1ax = true;
                        break;
                    case R.id.splayer:
                        twoplayer.setChecked(false);
                        selectedSinglePlayer = true;
                        cloneplayer2 = player2;
                        plyr2.setText("CPU");

                        plyr1.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        plyr1.setImeActionLabel("DONE", EditorInfo.IME_ACTION_DONE);


                        break;
                    case R.id.tplayer:
                        singleplayer.setChecked(false);
                        selectedSinglePlayer = false;
                        plyr2.setText(cloneplayer2);
                        plyr1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                        plyr1.setImeActionLabel("NEXT", EditorInfo.IME_ACTION_NEXT);

                        break;
                }

            } else {
                switch (view.getId()) {
                    case R.id.player1x:
                        p1o.setChecked(true);
                        p2x.setChecked(true);
                        p2o.setChecked(false);
                        player1ax = false;
                        break;
                    case R.id.player1o:
                        p1x.setChecked(true);
                        p2o.setChecked(true);
                        p2x.setChecked(false);
                        player1ax = true;
                        break;
                    case R.id.player2x:
                        p2o.setChecked(true);
                        p1x.setChecked(true);
                        p1o.setChecked(false);
                        player1ax = true;
                        break;
                    case R.id.player2o:
                        p2x.setChecked(true);
                        p1o.setChecked(true);
                        p1x.setChecked(false);
                        player1ax = false;
                        break;
                    case R.id.splayer:
                        twoplayer.setChecked(true);
                        selectedSinglePlayer = false;
                        plyr2.setText(cloneplayer2);

                        plyr1.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                        plyr1.setImeActionLabel("NEXT", EditorInfo.IME_ACTION_NEXT);
                        break;
                    case R.id.tplayer:
                        singleplayer.setChecked(true);
                        selectedSinglePlayer = true;
                        plyr2.setText("CPU");
                        plyr1.setImeOptions(EditorInfo.IME_ACTION_DONE);
                        plyr1.setImeActionLabel("DONE", EditorInfo.IME_ACTION_DONE);

                        break;
                }

            }

        }
    };


    public void startgame(View view) {

        if (!selectedSinglePlayer){
            if (plyr2.getText().toString().length() == 0) {
                player2 = "player 2";
            }
            else{
                player2=plyr2.getText().toString();
            }
        if (plyr1.getText().toString().length() == 0) {
            player1 = "player 1";
        }
        else{
            player1=plyr1.getText().toString();
        }}
        if (selectedSinglePlayer){

           player2=plyr2.getText().toString();
            if (plyr1.getText().toString().length() == 0) {
                player1 = "YOU";
            }
            else{
                player1=plyr1.getText().toString();
            }}


        CharSequence[] players = {player1, player2};
        Intent i = new Intent(this, Afterstart.class);

        i.putExtra("playersnames", players);
        i.putExtra("player1ax", player1ax);
        i.putExtra("selectedsingleplayer", selectedSinglePlayer);
        startActivity(i);
    }

}