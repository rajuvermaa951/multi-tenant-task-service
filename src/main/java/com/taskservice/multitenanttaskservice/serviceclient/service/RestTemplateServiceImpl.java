package com.taskservice.multitenanttaskservice.serviceclient.service;

import com.taskservice.multitenanttaskservice.config.entity.AppParameters;
import com.taskservice.multitenanttaskservice.config.repository.AppParameterRepository;
import com.taskservice.multitenanttaskservice.dto.CommonResponse;
import com.taskservice.multitenanttaskservice.serviceclient.entity.ApiRegistry;
import com.taskservice.multitenanttaskservice.serviceclient.registery.ApiRegisteryService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.ObjectMapper;


@Service
public class RestTemplateServiceImpl implements RestTemplateService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ApiRegisteryService apiRegisteryService;
    private final AppParameterRepository appParameterRepository;

    public RestTemplateServiceImpl(RestTemplate restTemplate,
                                   ObjectMapper objectMapper,
                                   ApiRegisteryService apiRegisteryService,
                                   AppParameterRepository appParameterRepository)
    {
        this.restTemplate=restTemplate;
        this.objectMapper = objectMapper;
        this.apiRegisteryService = apiRegisteryService;
        this.appParameterRepository = appParameterRepository;
    }

    @Override
    public <T, R> R call(String serviceName,String serviceCode, T request, Class<R> responseType) {

//        ApiRegistry api = apiRegisteryService.getApi(serviceName,serviceCode);
        AppParameters param = appParameterRepository.findByParamKey("BASE_URL");
        String baseUrl = param.getParamValue();
        ApiRegistry api = apiRegisteryService.getApi(serviceName,serviceCode);
        String path = api.getApiPath();
        String url =baseUrl+path;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entity = new HttpEntity<>(request,headers);

    ResponseEntity<CommonResponse> response = restTemplate.exchange(
            url,
            HttpMethod.valueOf(api.getMethodType()),
            entity,
            CommonResponse.class
    );
    CommonResponse<?> body = response.getBody();
    if (body == null ) {
        throw new RuntimeException("Empty Response from API");
    }
    if ("0".equals(body.getStatus())) {
        System.err.println(body.getErrmsg());
        throw new RuntimeException(body.getErrmsg());
    }
    if (body.getData() == null) {
        throw new RuntimeException("Data is null");
    }


    Object data = body.getData();

    return convert(data, responseType);

    }

    private  <R> R convert(Object data , Class<R> clazz)
    {

        return objectMapper.convertValue(data,clazz);
    }

    @Override
    public <T,R> R callUserService(String serviceCode , T request,Class<R> responseType)
    {
        return call("USER",serviceCode,request ,responseType);
    }


}
