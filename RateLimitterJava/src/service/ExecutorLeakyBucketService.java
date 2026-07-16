package service;

import java.util.Queue;

public class ExecutorLeakyBucketService {

    public void executeLeakyBucketRequest(Queue<Integer> queue){
        while(true){

            if(!queue.isEmpty()){
                System.out.println("processing the request");
                queue.poll();
            }

            try {
                Thread.sleep(500);
            }catch (Exception e){
                System.out.println("exception occurred while making thread to sleep for 500ms");
            }
        }
    }
}
