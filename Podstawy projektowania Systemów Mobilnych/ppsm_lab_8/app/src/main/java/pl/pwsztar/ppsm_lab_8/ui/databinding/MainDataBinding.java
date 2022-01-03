package pl.pwsztar.ppsm_lab_8.ui.databinding;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pl.pwsztar.ppsm_lab_8.R;

public class MainDataBinding {
    public Button btnSendCity;
    private View view;
    public TextView buttonSearch;
    public Button mainButton;

    public MainDataBinding(View view) {
       this.view = view;
       this.btnSendCity = view.findViewById(R.id.button_weather);
       this.buttonSearch = view.findViewById(R.id.edittext_search);
    }

    public View getView() {
        return view;
    }
}
