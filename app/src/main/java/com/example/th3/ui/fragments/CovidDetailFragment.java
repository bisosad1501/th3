package com.example.th3.ui.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.th3.R;
import com.example.th3.data.Covid19;
import com.example.th3.data.CovidDatabase;

public class CovidDetailFragment extends Fragment {
    private ImageView imageView;
    private TextView textVirusName;
    private TextView textVirusInfo;

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_covid_detail, container, false);
        imageView = view.findViewById(R.id.image_view);
        textVirusName = view.findViewById(R.id.virus_name);
        textVirusInfo = view.findViewById(R.id.virus_info);

        // Kiểm tra Bundle có null không
        Bundle args = getArguments();
        if (args != null) {
            int virusId = args.getInt("virus_id", -1); // Sử dụng giá trị mặc định để tránh lỗi

            // Truy vấn thông tin chủng virus từ cơ sở dữ liệu
            CovidDatabase db = CovidDatabase.getDatabase(getContext());
            Covid19 virus = db.covidDao().getCovidStrainById(virusId);

            if (virus != null) {
                // Cập nhật giao diện với thông tin từ cơ sở dữ liệu
                textVirusName.setText(virus.getVirusName());
                textVirusInfo.setText("Cấu trúc: " + getVirusStructureInfo(virus) +
                        "\nNgày xuất hiện: " + virus.getDiscoveredDate() +
                        "\nĐã có vắc xin: " + (virus.isHasVaccine() ? "Có" : "Chưa") +
                        "\nSố ca bệnh toàn cầu: " + virus.getWorldwideCases() +
                        "\nSố ca bệnh tại Việt Nam: " + virus.getVietnamCases());

                // Bạn có thể thêm hình ảnh vào ImageView nếu cần
                // imageView.setImageResource(virus.getImageResource()); // Thay thế bằng hình ảnh thực tế
            }
        } else {
            // Xử lý trường hợp không có arguments
            textVirusName.setText("Thông tin không có sẵn");
            textVirusInfo.setText("Không thể tải thông tin về chủng virus.");
        }

        return view;
    }

    private String getVirusStructureInfo(Covid19 virus) {
        StringBuilder sb = new StringBuilder();
        if (virus.isHasARN()) {
            sb.append("RNA, ");
        }
        if (virus.isHasProteinS()) {
            sb.append("Protein-S, ");
        }
        if (virus.isHasProteinN()) {
            sb.append("Protein-N, ");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 2); // Xóa dấu phẩy cuối cùng
        }
        return sb.toString();
    }
}