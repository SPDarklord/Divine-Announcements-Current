package pw.spdarklord.divineannouncments.Runnables;

import io.netty.handler.logging.LogLevel;
import pw.spdarklord.divineannouncments.DivineAnnouncments;

import java.util.concurrent.TimeUnit;

/**
 * Created by Thomas on 04/05/2017.
 */
public class Timer {


    public static void runTimer(){
        DivineAnnouncments.getInstance().getProxy().getScheduler().schedule(DivineAnnouncments.instance, new Runnable() {
            public void run() {
                DivineAnnouncments.getInstance().getProxy().getLogger().info("Timer run");
            }
        },1, 5, TimeUnit.SECONDS);
    }


}
