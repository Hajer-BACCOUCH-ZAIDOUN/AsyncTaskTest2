package sem31.isetsl.asynctasktest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editNumber;
    Button btnCalculer;
    TextView txtResultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultat=(TextView)  findViewById(R.id.textView);
        btnCalculer=(Button)findViewById(R.id.button);
        editNumber=(EditText)findViewById(R.id.editText);


    }

    public void CalculerClick(View v){
        if(editNumber.getText().toString().equals("")){
            Toast.makeText(this, "Nombre inexistant",Toast.LENGTH_LONG).show();
            return;
        }

        int nbr = Integer.parseInt(editNumber.getText().toString());
        // lancer l'asyncTask...
        //....

        //déclaration de l'asyncTask
        MyAsyncTask myTask  = new MyAsyncTask();

        //execution
        myTask.execute(nbr);
    }

    public class MyAsyncTask extends AsyncTask<Integer,Integer,Integer>{
        //au debut de l'execution
        //initialisation de l'asyncTask
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            txtResultat.setText("");
            btnCalculer.setEnabled(false);
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            int nbr = params[0];
            for(int i=0; i<=10;i++){
                int p=0;
                p=nbr*i;
                publishProgress(nbr,i,p);//évoquer onProgressUpdate

                try {
                    Thread.sleep(500);
                }
                catch(Exception ex){

                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            int nbr=values[0];
            int i=values[1];
            int p=values[2];

            String txt = txtResultat.getText().toString();
            String ligne = String.valueOf(nbr) + " x " + String.valueOf(i) + " = " + String.valueOf(p);
            txtResultat.setText(txt + "\n" + ligne);

        }

        //fin de l'execution

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            btnCalculer.setEnabled(true);
            Toast.makeText(getApplicationContext(),"Table multiplication affiché avec succés..", Toast.LENGTH_LONG).show();
        }


    }
}
