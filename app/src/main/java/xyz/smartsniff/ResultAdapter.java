package xyz.smartsniff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xyz.smartsniff.Model.Device;
import xyz.smartsniff.Model.DeviceType;

/**
 * Custom adapter to control the list view which will contain the results (devices) of a given session
 *
 * Author: Daniel Castro García
 * Email: dandev237@gmail.com
 * Date: 11/09/2016
 */
public class ResultAdapter extends ArrayAdapter<Device> {

    private List<Device> deviceList;
    private Context context;

    public ResultAdapter(List<Device> deviceList, Context context) {
        super(context, R.layout.result_row_layout, deviceList);
        this.deviceList = deviceList;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ResultHolder holder = new ResultHolder();

        //First, check if the view is not null
        if (convertView == null) {
            //New view -> Inflate
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.result_row_layout, parent, false);

            //Fill the layout with values
            TextView ssidTextView = (TextView) v.findViewById(R.id.resultSsidTextView);
            TextView macTextView = (TextView) v.findViewById(R.id.resultMacTextView);
            ImageView typeImageView = (ImageView) v.findViewById(R.id.resultRowImage);

            holder.ssidTextView = ssidTextView;
            holder.macTextView = macTextView;
            holder.typeImageView = typeImageView;

            v.setTag(holder);
        } else {
            holder = (ResultHolder) v.getTag();
        }

        Device device = deviceList.get(position);

        //If it is a bluetooth device, change the image resource
        if (device.getType().equals(DeviceType.BLUETOOTH))
            holder.typeImageView.setImageResource(R.drawable.ic_result_bluetooth);
        else
            holder.typeImageView.setImageResource(R.drawable.ic_result_wifi);

        holder.ssidTextView.setText(device.getSsid());
        holder.macTextView.setText(device.getBssid());

        return v;
    }

    /**
     * ViewHolder Design Pattern to optimize the ListView
     */
    private static class ResultHolder {
        public TextView ssidTextView;
        public TextView macTextView;
        public ImageView typeImageView;
    }
}
