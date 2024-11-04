package com.example.th3.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.th3.R;
import com.example.th3.data.Covid19;
import com.example.th3.data.CovidDatabase;

public class AddCovidActivity extends AppCompatActivity {

    private EditText editVirusName, editDiscoveredDate, editWorldwideCases, editVietnamCases;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_covid);

        editVirusName = findViewById(R.id.edit_virus_name);
        editDiscoveredDate = findViewById(R.id.edit_discovered_date);
        editWorldwideCases = findViewById(R.id.edit_worldwide_cases);
        editVietnamCases = findViewById(R.id.edit_vietnam_cases);
        buttonAdd = findViewById(R.id.button_add);

        buttonAdd.setOnClickListener(v -> addCovidStrain());
    }

    private void addCovidStrain() {
        String virusName = editVirusName.getText().toString();
        String discoveredDate = editDiscoveredDate.getText().toString();
        int worldwideCases = Integer.parseInt(editWorldwideCases.getText().toString());
        int vietnamCases = Integer.parseInt(editVietnamCases.getText().toString());

        Covid19 covid19 = new Covid19(virusName, false, false, false, discoveredDate, false, worldwideCases, vietnamCases);

        // Lưu dữ liệu vào database
        CovidDatabase db = CovidDatabase.getDatabase(this);
        new Thread(() -> {
            db.covidDao().insert(covid19);
            runOnUiThread(() -> {
                Toast.makeText(this, "Thêm chủng Covid thành công", Toast.LENGTH_SHORT).show();
                finish(); // Đóng activity
            });
        }).start();
    }
}