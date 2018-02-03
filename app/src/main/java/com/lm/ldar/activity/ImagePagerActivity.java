package com.lm.ldar.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lm.ldar.R;
import com.lm.ldar.entity.Global;
import com.lm.ldar.entity.Picture;
import com.lm.ldar.entity.PictureDownload;
import com.lm.ldar.util.IsNullOrEmpty;
import com.lm.ldar.view.HackyViewPager;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class ImagePagerActivity extends BaseActivity implements PhotoViewAttacher.OnPhotoTapListener {

    @BindView(R.id.tv_edit)
    TextView tvEdit;
    private String TAG = "ImagePagerActivity";

    private static final String STATE_POSITION = "STATE_POSITION";
    private static final String ISLOCKED_ARG = "isLocked";
    @BindView(R.id.pager)
    HackyViewPager mPager;
    @BindView(R.id.tv_pictype)
    TextView tvPictype;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    private ArrayList<PictureDownload> pictures;
    private int pagerPosition;
    private ImagePagerAdapter adapter;

    private int type;
    private String path;
    private int mCurrentPosition;

    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_image_pager);
        ButterKnife.bind(this);
        initTitleBar("浏览大图");
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        initpictures(bundle);
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
            boolean isLocked = savedInstanceState.getBoolean(ISLOCKED_ARG,
                    false);
            (mPager).setLocked(isLocked);
        }
        initData();

    }

    public void initData() {
        Log.i(TAG, "");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("位置");
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImagePagerActivity.this, MapAvtivity.class);
                intent.putExtra("latitude", pictures.get(pagerPosition).getLatitude() + "");
                intent.putExtra("longitude", pictures.get(pagerPosition).getLongitude() + "");
                startActivity(intent);
            }
        });
        tvEdit.setText("编辑");
        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ImagePagerActivity.this,ImageEditActivity.class);
                intent.putExtra("picture",pictures.get(mCurrentPosition));
                intent.putExtra("type",type);
                startActivity(intent);
            }
        });
        if (pictures != null && !pictures.isEmpty()) {

            adapter = new ImagePagerAdapter(pictures);
            mPager.setAdapter(adapter);
            mPager.setCurrentItem(pagerPosition);
            mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    changeRadioCheckStatus(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
    }

    private void initpictures(Bundle bundle) {
        pagerPosition = bundle.getInt("position", 0);
        pictures = (ArrayList<PictureDownload>) bundle.getSerializable("imagelist");
        type = getIntent().getIntExtra("type", 0);
        tvPictype.setText(getName(pictures.get(pagerPosition)));
        tvPosition.setText(pagerPosition + 1 + "/" + pictures.size());
        mCurrentPosition=pagerPosition;
    }


    private String getName(PictureDownload picture) {
        String name;
        if(type==0){
            name=picture.getPosition() + "_" + picture.getDeviceinfo() + "_" + picture.getMaterial();
        }else{
            name=picture.getElementname();
            if(!IsNullOrEmpty.isEmpty(name)){
                if(name.endsWith(".jpeg")){
                    name=name.replace(".jpeg","");
                }
                if(name.endsWith(".JPEG")){
                    name=name.replace(".JPEG","");
                }
                if(name.endsWith(".jpg")){
                    name=name.replace(".jpg","");
                }
                if(name.endsWith(".JPG")){
                    name=name.replace(".JPG","");
                }
                if(name.endsWith("png")){
                    name=name.replace(".png","");
                }
                if(name.endsWith("PNG")){
                    name=name.replace(".PNG","");
                }

            }
        }
        return name;

    }

    /**
     * 动态改变滑动过程中图片类型
     */
    private void changeRadioCheckStatus(final int position) {
        if (pictures != null && !pictures.isEmpty()) {
            int totalRange = 0;
            for (int i = 0; i < pictures.size(); i++) {
                if (totalRange <= position && position < (totalRange + pictures.size())) {
                    tvPictype.setText(getName(pictures.get(position)));
                    tvPosition.setText(position + 1 + "/" + pictures.size());
                    mCurrentPosition=position;
                    break;
                }
                totalRange += pictures.size();
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    public void onPhotoTap(View view, float v, float v1) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (isViewPagerActive()) {
            outState.putBoolean(ISLOCKED_ARG,
                    ((HackyViewPager) mPager).isLocked());
        }
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }


    private class ImagePagerAdapter extends PagerAdapter {

        private ArrayList<PictureDownload> images;
        private LayoutInflater inflater;

        public ArrayList<PictureDownload> getImages() {
            return images;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return super.getPageTitle(position);
        }

        ImagePagerAdapter(ArrayList<PictureDownload> imageUrls) {
            this.images = imageUrls;
            inflater = getLayoutInflater();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            View imageLayout = inflater.inflate(R.layout.item_pager_image,
                    view, false);
            assert imageLayout != null;
            final PhotoView imageView = imageLayout
                    .findViewById(R.id.image);
            imageView.setOnPhotoTapListener(ImagePagerActivity.this);
            if (type == 0) {
                path = images.get(position).getSketch();
            } else if (type == 1) {
                path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Global.IMAGE_DOWNLOAD_CHECK + images.get(position).getAid() + "/" + images.get(position).getElementname();
            } else if (type == 2) {
                path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + Global.IMAGE_DOWNLOAD_REVIEW + images.get(position).getAid() + "/" + images.get(position).getElementname();
            }
            File file = new File(path);
            if (file.exists()) {
                imageView.setImageURI(Uri.fromFile(file));
            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.default_img));
            }

            view.addView(imageLayout, 0);
            return imageLayout;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }

    private boolean isViewPagerActive() {
        return (mPager != null && mPager instanceof HackyViewPager);
    }


}