package br.com.fabriciocurvello.appescolhertema;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvTema;
    private Button btLight;
    private Button btDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvTema = findViewById(R.id.tv_tema);
        btLight = findViewById(R.id.bt_light);
        btDark = findViewById(R.id.bt_dark);

        // Recuperar tema salvo nas preferências
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        boolean isDarkTheme = sharedPref.getBoolean("DARK_THEME", false);

        // Aplicar o tema
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            tvTema.setText("Tema atual: ESCURO");
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            tvTema.setText("Tema atual: CLARO");
        }

        // Configurar os botões para salvar a preferência do usuário
        btLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveThemePreference(false);
            }
        });

        btDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveThemePreference(true);
            }
        });

    } // fim onCreate()

    private void saveThemePreference(boolean isDarkTheme) {
        // Salvar preferência no armazenamento interno usando SharedPreferences
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("DARK_THEME", isDarkTheme);
        editor.apply();

        // Aplicar o tema escolhido
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            tvTema.setText("Tema atual: ESCURO");
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            tvTema.setText("Tema atual: CLARO");
        }
    }
}