package com.example.classsign_in.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.classsign_in.Adapter.Course;
import com.example.classsign_in.R;
import java.util.List;

public class CourseAdapter extends ArrayAdapter<Course> {

    private List<Course>mCourseData;
    private Context mContext;
    private int resourceId;

    public CourseAdapter(Context context, int resourceId, List<Course>data) {
        super(context, resourceId, data);
        this.mContext = context;
        this.mCourseData = data;
        this.resourceId = resourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Course course = getItem(position);
        View view ;
        final ViewHolder vh;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            vh = new ViewHolder();
            vh.tvTitle  = view.findViewById(R.id.tv_title);
            vh.tvSource = view.findViewById(R.id.tv_subtitle);
            vh.ivImage = view.findViewById(R.id.iv_image);
            vh.ivDelete = view.findViewById(R.id.iv_delete);
            vh.tvPublishTime = view.findViewById(R.id.tv_publish_time);
            view.setTag(vh);
        }
        else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        vh.tvTitle.setText(course.getCollegeName());
        vh.tvSource.setText(course.getCourseId());
        vh.ivDelete.setTag(position);
        vh.tvPublishTime.setText(course.getDate());
        Glide.with(mContext).load(course.getCoursePhoto ()).into(vh.ivImage);
        return view;
    }

    public class ViewHolder {
        TextView tvTitle;
        TextView tvSource;
        ImageView ivImage;
        TextView tvPublishTime;
        ImageView ivDelete;
    }
}