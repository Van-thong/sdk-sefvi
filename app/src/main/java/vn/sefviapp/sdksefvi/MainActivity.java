package vn.sefviapp.sdksefvi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnLoginWithSefvi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLoginWithSefvi = findViewById(R.id.btnLoginWithSefvi);
        btnLoginWithSefvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SefviLogin sefviLogin = new SefviLogin(MainActivity.this);
                sefviLogin.sefviAppView("4c19e327c7da8d02d387","https://beta.sefvi.com/","https://beta.sefvi.com/login.php?code=");

            }
        });
    }
}
