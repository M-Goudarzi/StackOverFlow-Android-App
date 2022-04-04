package com.example.StackOverFlow_App.Other;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolManager {

    private static final ThreadPoolManager mInstance;
    public final ExecutorService executorService;

    static {
        mInstance = new ThreadPoolManager();
    }

    private ThreadPoolManager() {
        executorService = Executors.newFixedThreadPool(4);
    }

    public static ThreadPoolManager getInstance() {
        return mInstance;
    }

}
