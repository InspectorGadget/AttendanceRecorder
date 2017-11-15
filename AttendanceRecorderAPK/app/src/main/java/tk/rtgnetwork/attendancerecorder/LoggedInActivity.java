package tk.rtgnetwork.attendancerecorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.*;
import android.view.*;
import android.content.*;
import android.app.*;

import com.android.volley.*;
import com.android.volley.toolbox.*;

public class LoggedInActivity extends AppCompatActivity implements View.OnClickListener {

    EditText atn;
    Button plcatn, logout;
    TextView aerror;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);

        atn = findViewById(R.id.atn);
        plcatn = findViewById(R.id.plcatn);
        logout = findViewById(R.id.logout);
        aerror = findViewById(R.id.aerror);

        plcatn.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plcatn:

                if (atn.getText().toString().equals("")) {
                   aerror.setText("Empty parameters!");
                } else {

                    String url = "http://rtgnetwork.tk/android/v3/place.php?username=" + atn.getText().toString();
                    final RequestQueue requestQueue = Volley.newRequestQueue(LoggedInActivity.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,

                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("yes")) {
                                        aerror.setText("You have placed attendance for : " + atn.getText().toString());
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            aerror.setText("An error has occurred on our side!");
                        }
                    }
                    );
                    requestQueue.add(stringRequest);

                }

                break;

            case R.id.logout:

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
