package strategy;

import request.RequestContext;

import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;

public class SlidingWindowStrategy implements RateLimitStrategy{
    private final int capacity = 100;
    private final int windowInSecond = 3;
    private int currentWindowReqCount = 0;
    private int lastWindowReqCount = 0;
    private LocalDateTime lastWindowEndTimestamp = LocalDateTime.now().minusSeconds(windowInSecond);

    /*

    12:00:23 -> app start lastWindowEndTimestamp = 11:59:23
    12:00:25 -> now = 12:00:25 -> currSecond =
    12:01:30 ->

     */
    private void jobToUpdateLastWindowEndTimestamp(){
        LocalDateTime now = LocalDateTime.now();
        long totalSecondsPassed = now.toEpochSecond(UTC) - lastWindowEndTimestamp.toEpochSecond(UTC);
        int elapsedWindow = (int)totalSecondsPassed/windowInSecond;

        if(elapsedWindow == 0) return;

        lastWindowEndTimestamp = lastWindowEndTimestamp.plusSeconds(elapsedWindow * windowInSecond);

        if(elapsedWindow > 1){
            lastWindowReqCount = 0;
            currentWindowReqCount = 0;
        }else if(elapsedWindow == 1){
            lastWindowReqCount = currentWindowReqCount;
            currentWindowReqCount = 0;
        }
    }

    @Override
    public boolean isRequestAllowed(RequestContext request){
        jobToUpdateLastWindowEndTimestamp();

        LocalDateTime now = LocalDateTime.now();

        long currSecond = now.toEpochSecond(UTC) - lastWindowEndTimestamp.toEpochSecond(UTC);

        long tailInLastWindow = windowInSecond - currSecond;
        float tailPercentInLastWindow = (float)tailInLastWindow/(float)windowInSecond;
        long expectedRequest = currentWindowReqCount + (long)(tailPercentInLastWindow * lastWindowReqCount);


        boolean isAllow= (expectedRequest <= capacity);
        if(isAllow)currentWindowReqCount++;
        return isAllow;
    }

}
