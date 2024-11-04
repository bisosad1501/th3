package com.example.th3.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.th3.R;
import com.example.th3.data.Covid19;
import com.example.th3.data.CovidDatabase;

import java.util.List;

public class CovidListFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid_list, container, false);
        listView = view.findViewById(R.id.list_view);
        loadCovidStrains();
        return view;
    }

    private void loadCovidStrains() {
        new Thread(() -> {
            CovidDatabase db = CovidDatabase.getDatabase(getContext());
            List<Covid19> covidStrains = db.covidDao().getAllCovidStrains();

            // Chuyển đổi danh sách Covid19 thành danh sách tên để hiển thị
            String[] strainNames = new String[covidStrains.size()];
            for (int i = 0; i < covidStrains.size(); i++) {
                strainNames[i] = covidStrains.get(i).getVirusName();
            }

            // Cập nhật UI trên thread chính
            getActivity().runOnUiThread(() -> {
                adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, strainNames);
                listView.setAdapter(adapter);
            });
        }).start();
    }
}