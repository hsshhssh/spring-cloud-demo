package com.hssh.spring.cloud.component.zkconf;

import com.github.zkclient.IZkDataListener;
import com.github.zkclient.ZkClient;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hssh on 2017/2/15.
 */
public class ValueChangeHandler {

    public static SetMultimap<String, ObjectField> map = HashMultimap.create();

    public static ZkClient zkClient = new ZkClient(System.getenv("spring.cloud.zookeeper.connect-string"));

    public static IZkDataListener iZkDataListener = new IZkDataListener() {
        @Override
        public void handleDataChange(String dataPath, byte[] data) throws Exception {

            PropertiesConfiguration properties = new PropertiesConfiguration();
            properties.load(new ByteArrayInputStream(data));
            Iterator<String> is = properties.getKeys();
            while (is.hasNext()) {
                String key = dataPath + "/" + is.next();
                Set<ObjectField> set = map.get(key);
                for(ObjectField ob : set) {
                    ob.getField().setAccessible(true);
                    Value annotation = ob.getField().getAnnotation(Value.class);
                    ob.getField().set(ob.getObject(), properties.getProperty(annotation.key()));
//                    System.out.println("修改变量成功" + properties.getProperty(annotation.key()));
                }

            }
        }

        @Override
        public void handleDataDeleted(String dataPath) throws Exception {

        }
    };

    public static void add(String path, String key, Object object, Field field)
    {
        StringBuffer pathKey = new StringBuffer();
        pathKey.append(path);
        pathKey.append("/");
        pathKey.append(key);

        map.put(pathKey.toString(), new ObjectField(object, field));
    }

}
