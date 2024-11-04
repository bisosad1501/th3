package com.example.th3.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.th3.R;
import com.example.th3.ui.fragments.CovidListFragment;
import com.example.th3.ui.fragments.CovidDetailFragment;
import com.example.th3.ui.fragments.CovidSearchStatFragment;
import com.example.th3.ui.AddCovidActivity; // Thêm activity để thêm chủng mới
import com.example.th3.ui.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TabLayout tabLayout = findViewById(R.id.tab_layout);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ViewPager2 viewPager = findViewById(R.id.view_pager);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) FloatingActionButton fabAdd = findViewById(R.id.fab_add);

        // Thiết lập ViewPager với adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // Thiết lập TabLayout với ViewPager
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Danh sách");
                            break;
                        case 1:
                            tab.setText("Thông tin");
                            break;
                        case 2:
                            tab.setText("Tìm kiếm & Thống kê");
                            break;
                    }
                }
        ).attach();

        // Xử lý sự kiện cho nút nổi
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddCovidActivity.class);
            startActivity(intent);
        });
    }
}