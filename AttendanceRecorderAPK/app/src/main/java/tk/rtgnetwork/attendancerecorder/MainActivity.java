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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView lerror, reglink, about;
    EditText username, password;
    Button login;

    public String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lerror = findViewById(R.id.lerror); // TextView
        reglink = findViewById(R.id.reglink); // TextView
        about = findViewById(R.id.about);
        username = findViewById(R.id.username); // EditText
        password = findViewById(R.id.password); // EditText
        login = findViewById(R.id.login); // Btn

        reglink.setOnClickListener(this);
        login.setOnClickListener(this);
        about.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reglink:

                startActivity(new Intent(this, RegisterActivity.class));

                break;
            case R.id.login:

                String url = "http://rtgnetwork.tk/android/v3/login.php?username=" + username.getText().toString() + "&&password=" + password.getText().toString();
                final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equals("yes")) {
                                    makeToast("Authenticated!");
                                    // lerror.setText("Authenticated!");
                                    openProfile();
                                } else {
                                    makeToast("Your username doesn't exist!");
                                    // lerror.setText("Your username doesn't exist!");
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        lerror.setText("An error has occurred on our side!");
                    }
                }
                );
                requestQueue.add(stringRequest);

                break;
            case R.id.about:
                final AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Thanks!");
                alert.setMessage("Thanks for downloading this app! Don't forget to test this app and let us know!");
                alert.setCancelable(true);
                alert.setPositiveButton("dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog dg = alert.create();
                dg.show();
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

    public void openProfile() {
        startActivity(new Intent(this, LoggedInActivity.class));
    }
}
