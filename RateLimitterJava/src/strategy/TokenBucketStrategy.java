package strategy;

import request.RequestContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.lang.Math.min;
import static java.time.ZoneOffset.UTC;

public class TokenBucketStrategy implements RateLimitStrategy {
    private final int fillRate = 30; //per second
    private final int capacity = 100;
    private int availableToken = 100;
    private LocalDateTime lastReqTimestamp = LocalDateTime.now();


    private void fillToken() {
        LocalDateTime now = LocalDateTime.now();

        long totalSecondPassed = now.toEpochSecond(UTC) - lastReqTimestamp.toEpochSecond(UTC);
        int expectedTokens = (int)totalSecondPassed * fillRate;

        availableToken = min(capacity, availableToken + expectedTokens);

        lastReqTimestamp = now;
    }
    @Override
    public synchronized boolean isRequestAllowed(RequestContext request) {
        fillToken();

        boolean isAllow = availableToken > 0;

        if(isAllow)availableToken--;

        return isAllow;

        // if(availableToken>0) availableToken--; return true;
    }
}
