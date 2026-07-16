package strategy;

import request.RequestContext;

import java.util.LinkedList;
import java.util.Queue;

public class LeakyBucketStrategy implements RateLimitStrategy{
    private final int processRate = 30; //request per second
    public Queue<Integer> queue ;
    private final int capacity = 100;

    public LeakyBucketStrategy(Queue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public boolean isRequestAllowed(RequestContext request) {
        if(queue.size() == 100) return false;

        queue.add(request.getId());

        return true;
    }
}
