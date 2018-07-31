package cn.jzvd.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import cn.jzvd.JZUserAction;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Nathen on 16/7/22.
 */
public class ActivityMain extends AppCompatActivity {

    LinearLayout videoContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        videoContainer = findViewById(R.id.video_container);

        JZVideoPlayerStandard video1 = new JZVideoPlayerStandard(this);
        video1.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "Video ABC");
        video1.setSeekingEnabled(false);
        videoContainer.addView(video1, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));

        JZVideoPlayer.setJzUserAction((type, url, screen, objects) -> {

            if (type == JZUserAction.ON_AUTO_COMPLETE) {
                Log.e("ActivityMain", "Video completed: " + objects[0]);

                if (video1.getObjects()[0].equals(objects[0])) {
                    video1.setSeekingEnabled(true);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}
