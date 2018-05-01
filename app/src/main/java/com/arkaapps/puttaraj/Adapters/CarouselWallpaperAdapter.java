package com.arkaapps.puttaraj.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arkaapps.puttaraj.Pojo.CarouselWallpaperPojo;
import com.arkaapps.puttaraj.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by CSC on 3/29/2018.
 */

public class CarouselWallpaperAdapter extends BaseAdapter {

    private ArrayList<CarouselWallpaperPojo> data;
    private Activity activity;

    public CarouselWallpaperAdapter(Activity context, ArrayList<CarouselWallpaperPojo> objects) {
        this.activity = context;
        this.data = objects;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CarouselWallpaperPojo getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_flow_wallpaper, null, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.gameImage.setImageResource(data.get(position).getImageSource());
        viewHolder.gameName.setText(data.get(position).getName());

        convertView.setOnClickListener(onClickListener(position));

        return convertView;
    }

    private View.OnClickListener onClickListener(final int position) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(activity);
                try {
                    myWallpaperManager.setResource(+R.drawable.puttaraj_wallpaper_1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(activity, "Wallpaper set", Toast.LENGTH_LONG).show();
                addNotification();
            }
        };
    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(activity)
                        .setSmallIcon(R.drawable.puttaraj_1)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


    private static class ViewHolder {
        private TextView gameName;
        private ImageView gameImage;

        public ViewHolder(View v) {
            gameImage = (ImageView) v.findViewById(R.id.image);
            gameName = (TextView) v.findViewById(R.id.name);
        }
    }
}