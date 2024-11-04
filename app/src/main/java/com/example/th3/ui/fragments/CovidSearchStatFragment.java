package com.example.th3.ui.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.th3.R;
import com.example.th3.data.Covid19;
import com.example.th3.data.CovidDatabase;

import java.util.Collections;
import java.util.List;

public class CovidSearchStatFragment extends Fragment {
    private EditText editTextDateRange;
    private CheckBox checkBoxVN, checkBoxWorld;
    private Button buttonSearch;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid_search_stat, container, false);

        editTextDateRange = view.findViewById(R.id.edit_text_date_range);
        checkBoxVN = view.findViewById(R.id.checkbox_vn);
        checkBoxWorld = view.findViewById(R.id.checkbox_world);
        buttonSearch = view.findViewById(R.id.button_search);

        buttonSearch.setOnClickListener(v -> performSearch());

        return view;
    }

    private void performSearch() {
        String dateRange = editTextDateRange.getText().toString();
        boolean isVNChecked = checkBoxVN.isChecked();
        boolean isWorldChecked = checkBoxWorld.isChecked();

        // Kiểm tra đầu vào
        if (dateRange.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập khoảng thời gian.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tách khoảng thời gian
        String[] dates = dateRange.split("đến");
        if (dates.length != 2) {
            Toast.makeText(getContext(), "Định dạng không hợp lệ. Vui lòng nhập lại.", Toast.LENGTH_SHORT).show();
            return;
        }

        String startDate = dates[0].trim();
        String endDate = dates[1].trim();

        new Thread(() -> {
            CovidDatabase db = CovidDatabase.getDatabase(getContext());
            List<Covid19> results;

            if (isVNChecked && isWorldChecked) {
                // Lấy dữ liệu cho cả Việt Nam và toàn cầu
                results = db.covidDao().getCovidStrainsByAppearanceDate(startDate, endDate);
            } else if (isVNChecked) {
                // Lấy dữ liệu chỉ cho Việt Nam
                results = db.covidDao().getCovidStrainsByAppearanceDate(startDate, endDate);
            } else if (isWorldChecked) {
                // Lấy dữ liệu cho toàn cầu
                results = db.covidDao().getCovidStrainsByAppearanceDate(startDate, endDate);
            } else {
                results = Collections.emptyList(); // Không chọn gì
            }

            getActivity().runOnUiThread(() -> {
                if (results.isEmpty()) {
                    Toast.makeText(getContext(), "Không có dữ liệu cho khoảng thời gian này.", Toast.LENGTH_SHORT).show();
                } else {
                    displayResults(results);
                }
            });
        }).start();
    }

    private void displayResults(List<Covid19> results) {
        StringBuilder resultDisplay = new StringBuilder();
        for (Covid19 strain : results) {
            resultDisplay.append(strain.getVirusName()).append("\n");
            // Thêm thông tin khác nếu cần
        }

        // Hiển thị trong một dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Kết quả tìm kiếm")
                .setMessage(resultDisplay.toString())
                .setPositiveButton("OK", null)
                .show();
    }
}