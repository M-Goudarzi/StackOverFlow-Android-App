package com.example.retrofit_test.Common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
