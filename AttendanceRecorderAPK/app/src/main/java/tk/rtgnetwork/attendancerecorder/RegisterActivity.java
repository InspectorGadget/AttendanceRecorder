package tk.rtgnetwork.attendancerecorder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.*;
import android.view.*;

import com.android.volley.*;
import com.android.volley.toolbox.*;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    TextView rerror;
    EditText username, password;
    Button register, lsend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rerror = findViewById(R.id.rerror); // TextView
        username = findViewById(R.id.username); // EditText
        password = findViewById(R.id.password); // EditText
        register = findViewById(R.id.register); // Btn
        lsend = findViewById(R.id.lsend); // btn

        register.setOnClickListener(this);
        lsend.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:

                if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
                    rerror.setText("Empty parameters!");
                } else {

                    String url = "http://rtgnetwork.tk/android/v3/register.php?username=" + username.getText().toString() + "&&password=" + password.getText().toString();
                    final RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,

                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    rerror.setText(response);
                                    if (response.equals("yes")) {
                                        makeToast("Registered!");
                                        // rerror.setText("Successfully registered!");
                                    } else {
                                        makeToast("An error has occurred on our server side! Please try again :)");
                                        // rerror.setText("An error has occurred on our server side! Please try again :)");
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            rerror.setText("An error has occurred on our side!");
                        }
                    }
                    );
                    requestQueue.add(stringRequest);

                }

                break;

            case R.id.lsend:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    public void makeToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("You sure you want to exit this app?");
        alert.setCancelable(true);
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alert.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        AlertDialog dg = alert.create();
        dg.show();
    }
}
