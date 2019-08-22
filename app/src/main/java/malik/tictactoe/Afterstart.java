package malik.tictactoe;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Afterstart extends AppCompatActivity {

     Random r = new Random();

    int flag = 0, ax = 10, zero = 1, win = 0, i, game = 1;
    int summ = 0, ctrflag = 0, resetchecker = 1, currentgamedonechecker = 0;
    int score1 = 0, score2 = 0, drawchecker = 0;
    static int[][] tracker = new int[3][3];
    int[] sum = new int[8];
    static int[][] buttonpressed = new int[3][3];

    boolean player1ax;
    boolean selectedsingleplayer;

    TextView p1;
    TextView p2;
    CharSequence player1 = "Player 1";
    CharSequence player2 = "Player 2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_afterstart);

        CharSequence[] players = getIntent().getCharSequenceArrayExtra("playersnames");
        player1ax = getIntent().getBooleanExtra("player1ax", true);
        selectedsingleplayer = getIntent().getBooleanExtra("selectedsingleplayer", true);



        if (player1ax) {//if player 1 selected X then execute this block
            ax = 1;
            zero = 10;
        }


        player1 = Objects.requireNonNull(players)[0];
        player2 = players[1];
        p1 = (TextView) findViewById(R.id.playerone);
        p2 = (TextView) findViewById(R.id.playertwo);

        p1.setText(player1);
        p2.setText(player2);

        Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();

    }


    public void kzz(View view) {//This are the ooClick method of imageviews


        if (win == 0 && buttonpressed[0][0] == 0) {//here we are checking weather we have pressed button
            if (flag % 2 == 0)//if flag=0,2,4,6 then player1's turn else player 2's turn
                tracker[0][0] = ax;
            else
                tracker[0][0] = zero;

            printBoard();//here we are printing x or o depending on p1 , p2
            winchecker();//we are calling after each image press to check weather p1 or p2 won
            cpuplay();//if player 1 is playing with cpu this method's if condition will be executed
            flag++;//here we are incrementing the flag so that next turn will be stored in tracker array player1=ax player2=zero
            Log.e("B buttonpressed[0][0]++", String.valueOf(buttonpressed[0][0]));

            buttonpressed[0][0]++;//here we are incrementing the array buttonpressed[0][0]=0 to buttonpressed[0][0]=1
            //so that if next time user presess same button KzZ() method will not executed
            Log.e("A buttonpressed[0][0]++", String.valueOf(buttonpressed[0][0]));
        }
    }


    public void kzo(View view) {

        if (win == 0 && buttonpressed[0][1] == 0) {
            if (flag % 2 == 0) tracker[0][1] = ax;
            else tracker[0][1] = zero;

            printBoard();
            winchecker();
            cpuplay();
            Log.e("B buttonpressed[0][1]++", String.valueOf(buttonpressed[0][1]));

            buttonpressed[0][1]++;
            Log.e("A buttonpressed[0][1]++", String.valueOf(buttonpressed[0][1]));
            flag++;
        }
    }

    public void kzt(View view) {
        if (win == 0 && buttonpressed[0][2] == 0) {
            if (flag % 2 == 0) tracker[0][2] = ax;
            else tracker[0][2] = zero;

            printBoard();
            winchecker();
            cpuplay();
            Log.e("B buttonpressed[0][2]++", String.valueOf(buttonpressed[0][2]));

            buttonpressed[0][2]++;
            Log.e("A buttonpressed[0][2]++", String.valueOf(buttonpressed[0][2]));

            flag++;
        }
    }

    public void koz(View v) {
        if (win == 0 && buttonpressed[1][0] == 0) {
            if (flag % 2 == 0) tracker[1][0] = ax;
            else tracker[1][0] = zero;

            printBoard();
            winchecker();
            cpuplay();

            ++buttonpressed[1][0];
            flag++;
        }
    }

    public void koo(View v) {
        if (win == 0 && buttonpressed[1][1] == 0) {
            if (flag % 2 == 0) tracker[1][1] = ax;
            else tracker[1][1] = zero;
            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[1][1];
            flag++;
        }
    }

    public void kot(View v) {
        if (win == 0 && buttonpressed[1][2] == 0) {
            if (flag % 2 == 0) tracker[1][2] = ax;
            else tracker[1][2] = zero;

            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[1][2];
            flag++;
        }
    }

    public void ktz(View v) {
        if (win == 0 && buttonpressed[2][0] == 0) {
            if (flag % 2 == 0) tracker[2][0] = ax;
            else tracker[2][0] = zero;

            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[2][0];
            flag++;
        }
    }

    public void kto(View v) {
        if (win == 0 && buttonpressed[2][1] == 0) {
            if (flag % 2 == 0) tracker[2][1] = ax;
            else tracker[2][1] = zero;
            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[2][1];
            flag++;
        }
    }

    public void ktt(View v) {
        if (win == 0 && buttonpressed[2][2] == 0) {
            if (flag % 2 == 0) tracker[2][2] = ax;
            else tracker[2][2] = zero;

            printBoard();
            winchecker();
            cpuplay();
            ++buttonpressed[2][2];
            flag++;
        }
    }

    public void cpuplay() {
        if ((selectedsingleplayer) && (win == 0)) {//here this method will see if we are single player then it will be executed


            if(ifcpuwin()) ;//this method wii be checked if true or else method will be checked
            else emptyany();


             final Handler handler = new Handler();
             Timer t = new Timer();
             t.schedule(new TimerTask() {
             public void run() {
             handler.post(new Runnable() {
             public void run() {

             //we are delaying the cpu print and win by 1 second
                 printBoard();
                 winchecker();
             }
             });
             }
             }, 1000);


            flag++;
            return;
        }
    }

    public boolean ifcpuwin() {//here we only check weather cpu all rows / columns / diagonals is zero{1 or 10}
      // if (!easy) {
            for (i = 0; i < 8; i++) {
                if (sum[i] == 2 * zero) {
                    if (i == 0) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[0][x] == 0)
                                tracker[0][x] = zero;
                    }

                    if (i == 1) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[1][x] == 0)
                                tracker[1][x] = zero;
                    }
                    if (i == 2) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[2][x] == 0)
                                tracker[2][x] = zero;
                    }

                    if (i == 3) {
                        for (int x = 0; x < 3; x++)
                            if (tracker[x][0] == 0)
                                tracker[x][0] = zero;
                    }

                    if (i == 4) {

                        for (int x = 0; x < 3; x++)
                            if (tracker[x][1] == 0)
                                tracker[x][1] = zero;
                    }

                    if (i == 5) {

                        for (int x = 0; x < 3; x++)
                            if (tracker[x][2] == 0)
                                tracker[x][2] = zero;
                    }
                    if (i == 6) {

                        for (int y = 0; y < 3; y++)
                            for (int x = 0; x < 3; x++)
                                if (x == y)
                                    if (tracker[x][y] == 0)
                                        tracker[x][y] = zero;
                    }
                    if (i == 7) {
                        if (tracker[0][2] == 0)
                            tracker[0][2] = zero;
                        else if (tracker[1][1] == 0)
                            tracker[1][1] = zero;
                        else tracker[2][0] = zero;

                    }
                    return true;
                }
            }
        //}
        return false;
    }

    public void emptyany() {//here this is the else part of cpuplay() method as if we not win then we will be executing this method

        if (ctrflag == 0)//if Cpu plays first the this method will be executed
            while (true) {
                int x = rand();
                int y = rand();

                if (tracker[x][y] == 0) {//if tracker array of x,y is 0 then we can initilize zero{1,10} at that index
                    tracker[x][y] = zero;
                    buttonpressed[x][y]++;
                    return;//return from the method

                }
            }

        for (int x = 0; x < 3; x++)//this method will be executing by scanning entire array as above tracker x,y may not be 0
            for (int y = 0; y < 3; y++)
                if (tracker[x][y] == 0) {
                    tracker[x][y] = zero;
                    buttonpressed[x][y]++;
                    return;
                }


    }

    public int rand() {
        return r.nextInt(3);
    }

    public void printBoard() {
        ImageView q1, q2, q3, q4, q5, q6, q7, q8, q9;

        q1 = (ImageView) findViewById(R.id.tzz);
        q2 = (ImageView) findViewById(R.id.tzo);
        q3 = (ImageView) findViewById(R.id.tzt);
        q4 = (ImageView) findViewById(R.id.toz);
        q5 = (ImageView) findViewById(R.id.too);
        q6 = (ImageView) findViewById(R.id.tot);
        q7 = (ImageView) findViewById(R.id.ttz);
        q8 = (ImageView) findViewById(R.id.tto);
        q9 = (ImageView) findViewById(R.id.ttt);


        if (tracker[0][0] == 1) q1.setImageResource(R.drawable.x); //if ax=1 means player1=x or if zero=1 means player2=x
        if (tracker[0][0] == 10) q1.setImageResource(R.drawable.oo);//if ax=10 means player1=O or if zero=10 means player2=O


        if (tracker[0][1] == 1) q2.setImageResource(R.drawable.x);
        if (tracker[0][1] == 10) q2.setImageResource(R.drawable.oo);


        if (tracker[0][2] == 1) q3.setImageResource(R.drawable.x);
        if (tracker[0][2] == 10) q3.setImageResource(R.drawable.oo);


        if (tracker[1][0] == 1) q4.setImageResource(R.drawable.x);
        if (tracker[1][0] == 10) q4.setImageResource(R.drawable.oo);

        if (tracker[1][1] == 1) q5.setImageResource(R.drawable.x);
        if (tracker[1][1] == 10) q5.setImageResource(R.drawable.oo);


        if (tracker[1][2] == 1) q6.setImageResource(R.drawable.x);
        if (tracker[1][2] == 10) q6.setImageResource(R.drawable.oo);

        if (tracker[2][0] == 1) q7.setImageResource(R.drawable.x);
        if (tracker[2][0] == 10) q7.setImageResource(R.drawable.oo);


        if (tracker[2][1] == 1) q8.setImageResource(R.drawable.x);
        if (tracker[2][1] == 10) q8.setImageResource(R.drawable.oo);

        if (tracker[2][2] == 1) q9.setImageResource(R.drawable.x);
        if (tracker[2][2] == 10) q9.setImageResource(R.drawable.oo);

        resetchecker++;
    }


    public void showDialog(String whoWon, String scoreWon, String whoLose, String scoreLose) {

        final Dialog dialog = new Dialog(Afterstart.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        TextView titleText = dialog.findViewById(R.id.title_text);
        dialog.setCancelable(false);
        dialog.show();

        titleText.setText(whoWon);

        Button resetButton = dialog.findViewById(R.id.reset_button);
        Button playAgainButton = dialog.findViewById(R.id.play_again_button);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                doreset();
            }
        });

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                playmore();
            }
        });
    }

    public void winchecker() {
        ctrflag++;
        //here we will add the sum of rows,columns and diagonals
         sum[0] = tracker[0][0] + tracker[0][1] + tracker[0][2];
        sum[1] = tracker[1][0] + tracker[1][1] + tracker[1][2];
        sum[2] = tracker[2][0] + tracker[2][1] + tracker[2][2];
        sum[3] = tracker[0][0] + tracker[1][0] + tracker[2][0];
        sum[4] = tracker[0][1] + tracker[1][1] + tracker[2][1];
        sum[5] = tracker[0][2] + tracker[1][2] + tracker[2][2];
        sum[6] = tracker[0][0] + tracker[1][1] + tracker[2][2];
        sum[7] = tracker[0][2] + tracker[1][1] + tracker[2][0];


        currentgamedonechecker++;
        resetchecker++;
        //if any of the row,column or diagonal is 3 or 30 the player1 or player2 won depending on ax , zero value  else draw

        for (int i = 0; i < 8; i++)
            if (sum[i] == 3 || sum[i] == 30) {
                win++;
                if ((sum[i] == 3) && (ax == 1)) {
                    score1++;
                    TextView q1 = (TextView) findViewById(R.id.p1score);
                    q1.setText("" + score1);
                    showDialog("" + player1 + " won!", "" + score1, "" + player2, "" + score2);

                }
                if ((sum[i] == 3) && (zero == 1)) {
                    score2++;
                    TextView q1 = (TextView) findViewById(R.id.p2score);
                    q1.setText("" + score2);
                    showDialog("" + player2 + " won!", "" + score2, "" + player1, "" + score1);

                }
                if ((sum[i] == 30) && (ax == 10)) {
                    score1++;
                    TextView q1 = (TextView) findViewById(R.id.p1score);
                    q1.setText("" + score1);
                    showDialog("" + player1 + " won!", "" + score1, "" + player2, "" + score2);

                }
                if ((sum[i] == 30) && (zero == 10)) {
                    score2++;
                    TextView q1 = (TextView) findViewById(R.id.p2score);
                    q1.setText("" + score2);
                    showDialog("" + player2 + " won!", "" + score2, "" + player1, "" + score1);

                }

            }

        if ((ctrflag == 9) && (win == 0)) {
            showDialog("This is a draw !", "" + score1, "" + player2, "" + score2);

            drawchecker++;
        }


    }  //end winchecker()

    private void playmore() {
        if ((drawchecker > 0) || (win > 0)) {
            game++;
            TextView qq = (TextView) findViewById(R.id.gamenumber);
            qq.setText("" + game);

            for (int i = 0; i < 8; i++)
                sum[i] = 0;

            drawchecker = 0;


            ImageView q1, q2, q3, q4, q5, q6, q7, q8, q9;
            q1 = (ImageView) findViewById(R.id.tzz);
            q2 = (ImageView) findViewById(R.id.tzo);
            q3 = (ImageView) findViewById(R.id.tzt);
            q4 = (ImageView) findViewById(R.id.toz);
            q5 = (ImageView) findViewById(R.id.too);
            q6 = (ImageView) findViewById(R.id.tot);
            q7 = (ImageView) findViewById(R.id.ttz);
            q8 = (ImageView) findViewById(R.id.tto);
            q9 = (ImageView) findViewById(R.id.ttt);
            q1.setImageDrawable(null);
            q2.setImageDrawable(null);
            q3.setImageDrawable(null);
            q4.setImageDrawable(null);
            q5.setImageDrawable(null);
            q6.setImageDrawable(null);
            q7.setImageDrawable(null);
            q8.setImageDrawable(null);
            q9.setImageDrawable(null);

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    buttonpressed[i][j] = 0;

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    tracker[i][j] = 0;


            if ((game + 1) % 2 == 0)
                Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "" + player2 + "\'s turn", Toast.LENGTH_SHORT).show();
            win = 0;
            summ = 0;
            ctrflag = 0;
            flag = (game + 1) % 2;
            currentgamedonechecker = 0;

            if (selectedsingleplayer && (game % 2 == 0))
                cpuplay();
        }
    }


    public void doreset() {

        TextView qq = (TextView) findViewById(R.id.gamenumber);
        qq.setText("" + 1);


        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                tracker[i][j] = 0;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttonpressed[i][j] = 0;

        ImageView q1, q2, q3, q4, q5, q6, q7, q8, q9;

        q1 = (ImageView) findViewById(R.id.tzz);
        q2 = (ImageView) findViewById(R.id.tzo);
        q3 = (ImageView) findViewById(R.id.tzt);
        q4 = (ImageView) findViewById(R.id.toz);
        q5 = (ImageView) findViewById(R.id.too);
        q6 = (ImageView) findViewById(R.id.tot);
        q7 = (ImageView) findViewById(R.id.ttz);
        q8 = (ImageView) findViewById(R.id.tto);
        q9 = (ImageView) findViewById(R.id.ttt);
        q1.setImageDrawable(null);
        q2.setImageDrawable(null);
        q3.setImageDrawable(null);
        q4.setImageDrawable(null);
        q5.setImageDrawable(null);
        q6.setImageDrawable(null);
        q7.setImageDrawable(null);
        q8.setImageDrawable(null);
        q9.setImageDrawable(null);


        win = 0;
        drawchecker = 0;
        summ = 0;
        resetchecker = 0;
        ctrflag = 0;
        score1 = 0;
        score2 = 0;
        game = 1;
        flag = 0;
        currentgamedonechecker = 0;
        TextView qqq = (TextView) findViewById(R.id.p1score);
        qqq.setText("" + score1);
        TextView qqqq = (TextView) findViewById(R.id.p2score);
        qqqq.setText("" + score2);

        Toast.makeText(this, "" + player1 + "\'s turn", Toast.LENGTH_SHORT).show();


    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    private void showExitDialog() {
        final Dialog dialog = new Dialog(Afterstart.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout_exit);
        dialog.setCancelable(false);

        dialog.show();

        Button exit = dialog.findViewById(R.id.yes_button);
        final Button dismiss = dialog.findViewById(R.id.no_button);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doreset();
                finish();
            }
        });

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

}


