package com.taskservice.multitenanttaskservice.serviceclient.registery;

import com.taskservice.multitenanttaskservice.serviceclient.entity.ApiRegistry;
import com.taskservice.multitenanttaskservice.serviceclient.repository.ApiRegistryRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ApiRegisteryService {
    private final ApiRegistryRepository apiRegisteryRepository;

    public ApiRegisteryService(ApiRegistryRepository apiRegisteryRepository)
    {
        this.apiRegisteryRepository = apiRegisteryRepository;
    }

    private final Map<String,ApiRegistry> apiCache = new ConcurrentHashMap<>();

    @EventListener(ApplicationReadyEvent.class)
    public void load()
    {
    reload();
    }

    public synchronized void reload()
    {
        apiCache.clear();
        try{
            List<ApiRegistry> apiList = apiRegisteryRepository.findByIsActive("1");
            for(ApiRegistry api : apiList)
            {
                String key = buildKey(api.getServiceName(),api.getServiceCode());
                apiCache.put(key,api);
            }
            System.out.println("Loaded Api's into cache : "+apiCache.size());
        }
        catch (Exception e)
        {
            System.out.println("Failed to load Api's"+e);
        }
    }

    public ApiRegistry getApi(String serviceName,String serviceCode)
    {

        String key = buildKey(serviceName,serviceCode);
        ApiRegistry api = apiCache.get(key);
        if(api == null)
        {
            throw new RuntimeException("Api not found in cache");
        }
        return  api;
    }
    public ApiRegistry getApiByPath(String httpMethod,String uri)
    {
        for(ApiRegistry api : apiCache.values())
        {
            if(!api.getMethodType().equalsIgnoreCase(httpMethod)) {
                continue;
            }
            String pattern = api.getApiPath().replaceAll("\\{[^/]+}", "[^/]+");
            if(uri.matches(pattern))
            {
                return api;
            }
        }
        return null;
    }
    private String buildKey(String serviceName,String serviceCode)
    {
        return serviceName+"_"+serviceCode;
    }




}
