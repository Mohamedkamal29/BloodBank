package app.p.bloodbank.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.p.bloodbank.R;
import app.p.bloodbank.data.model.General.publiceData.GeneralResponseData;
import app.p.bloodbank.data.model.General.publiceData.generalResponse.GeneralResponse;

public class SpinnerAdapter extends BaseAdapter {


    private Context context;
    private List<GeneralResponseData> generalResponseDataList = new ArrayList<>();
    private LayoutInflater inflter;
    public int selectedId = 0;

    public SpinnerAdapter(Context applicationContext, List<GeneralResponseData> generalResponseDataList, String hint) {
        this.context = applicationContext;
        this.generalResponseDataList = generalResponseDataList;
        inflter = (LayoutInflater.from(applicationContext));
        generalResponseDataList.add(new GeneralResponseData(0, hint));
    }
    @Override
    public int getCount() {
        return generalResponseDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.items_custom_spinner, null);

        TextView names = (TextView) view.findViewById(R.id.text);

        names.setText(generalResponseDataList.get(i).getName());
        selectedId = generalResponseDataList.get(i).getId();

        return view;
    }
}
