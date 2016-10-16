package com.android.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_head)
    TextView mTvHead;
    @Bind(R.id.tv_img)
    ImageView mTvImg;
    private ListView mListView;

    private String[] array = new String[30];

    private RelativeLayout mToolbar;

    TextView mTextView;
    private View mHeader;

    private int mHeader_Top;
    private int mHeader_Height = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar = (RelativeLayout) findViewById(R.id.layout_toolbar);

        mListView = (ListView) findViewById(R.id.listview);

        mTextView = (TextView) findViewById(R.id.emptyView);

        mToolbar.getBackground().setAlpha(0);

        for (int i = 0; i < 30; i++) {
            array[i] = i + "";
        }

        mHeader = LayoutInflater.from(this).inflate(R.layout.item_header, null);
        ButterKnife.bind(this,mHeader);
        mListView.addHeaderView(mHeader);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);

        mListView.setAdapter(arrayAdapter);

        //mListView.setEmptyView(mTextView);
        if (arrayAdapter.isEmpty()) {
            mTextView.setVisibility(View.VISIBLE);
        }

        boolean empty = arrayAdapter.isEmpty();

        Toast.makeText(this, String.valueOf(empty), Toast.LENGTH_SHORT).show();

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {


            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                mHeader_Height = mHeader.getHeight() - mToolbar.getHeight();
                mHeader_Top = mHeader.getTop();
                int height = mHeader_Height - mToolbar.getHeight();
                if (height > 0) {


                    System.out.println(mHeader_Height + "               " + mHeader_Top);
                    int alpha = (int) ((double) (Math.abs(mHeader_Top) / (double) mHeader_Height) * 255);
                    System.out.println("alpha      " + alpha);
                    mToolbar.getBackground().setAlpha(alpha);
                    if (alpha > 255) {
                        mToolbar.getBackground().setAlpha(255);
                    }
                }

                //mToolbar.getBackground().setAlpha((Math.abs(mHeader_Top) / mHeader_Height));
            }
        });

        mTvHead.setText("我是ButterKnife");


    }
}
