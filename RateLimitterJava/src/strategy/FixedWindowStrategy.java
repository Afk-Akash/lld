package strategy;


import request.RequestContext;

import java.time.LocalDateTime;
import java.util.Date;

public class FixedWindowStrategy implements RateLimitStrategy {
    private int fillRate = 100;
    private int tokenAvailabe = 100;
    private  LocalDateTime lastFillingTimeStamp = LocalDateTime.now();

    @Override
    public boolean isRequestAllowed(RequestContext request) {
        if(currentWindowPassed(lastFillingTimeStamp)){
            tokenAvailabe = fillRate;
            lastFillingTimeStamp = LocalDateTime.now(); // here we can get the seconds and minus it to make it 12:03:00
        }

        if(tokenAvailabe > 0){
            tokenAvailabe--;
            return true;
        }else{
            return false;
        }

    }

    private boolean currentWindowPassed(LocalDateTime lastTimeStamp){
        if(lastTimeStamp.plusSeconds(3).isAfter(LocalDateTime.now()) )
            return false;
        else
            return true;
    }
}
