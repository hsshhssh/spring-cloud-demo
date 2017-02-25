package com.hssh.spring.cloud.filter;

import com.hssh.spring.cloud.zkconf.Value;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hssh on 2017/2/22.
 */
public class RouteFilter extends ZuulFilter {


    @Value(path = "/config/serviceUrl", key=ServiceName.world)
    private String worldUrl;

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
//        System.out.println(context.getRouteHost());
//        System.out.println(worldUrl);
        // TODO待优化
        if(StringUtils.isNotBlank(worldUrl) && ServiceName.world.equals(context.get("proxy").toString())) {
            try {
                context.setRouteHost(new URL(worldUrl));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
