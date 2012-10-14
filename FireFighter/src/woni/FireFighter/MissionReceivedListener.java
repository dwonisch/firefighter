package woni.FireFighter;

import java.util.List;

import android.app.Activity;

public interface MissionReceivedListener 
{
    void onCompleted(Activity activity, List<Mission> missions);
    void onFailed(Activity activity, Exception exception);
}
