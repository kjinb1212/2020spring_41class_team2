package com.example.bloodwallet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class StoryListViewAdapter extends BaseAdapter {

    private ArrayList<StoryListItem> listViewItemList = new ArrayList<StoryListItem>() ;

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.story_list_item, parent, false);
        }

        TextView storyTitle = (TextView) convertView.findViewById(R.id.story_list_title);
        TextView storySummary = (TextView) convertView.findViewById(R.id.story_list_summary);
        TextView uploadTime = (TextView) convertView.findViewById(R.id.story_list_time);
        TextView percent = (TextView) convertView.findViewById(R.id.story_list_percent);

        StoryListItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        storyTitle.setText(listViewItem.getTitle());
        storySummary.setText(listViewItem.getSummary());
        uploadTime.setText(listViewItem.getUploadTime());
        percent.setText(listViewItem.getPercent());

        return convertView;
    }

    public void addItem(HashMap post, Double score) {
        StoryListItem item = new StoryListItem();

        item.title = post.get("title").toString();
        item.content = post.get("content").toString();
        item.summary = post.get("abstract").toString();
        item.uploadTime = post.get("timestamp").toString();
        item.donatedNum = Integer.parseInt(post.get("donated_num").toString());
        item.goalNum = Integer.parseInt(post.get("goal_num").toString());
        item.score = score;

        listViewItemList.add(item);
    }

    public void sortList(){
        Collections.sort(listViewItemList, Comparator.reverseOrder());
    }
}
