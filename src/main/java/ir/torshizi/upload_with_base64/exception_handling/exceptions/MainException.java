package ir.torshizi.upload_with_base64.exception_handling.exceptions;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class MainException extends RuntimeException{

    public MainException(String message) {
        super(message);
    }

    public static String getClassName(Class<?> clazz){
        if(clazz==null)
            return "";
        if(clazz.getSimpleName().endsWith("Entity")){
            return clazz.getSimpleName().substring(0,clazz.getSimpleName().lastIndexOf("Entity"));
        }else if(clazz.getSimpleName().endsWith("Dto")){
            return clazz.getSimpleName().substring(0,clazz.getSimpleName().lastIndexOf("Dto"));
        }
        return clazz.getSimpleName();
    }

    public static String getClassName(String className){
        if(className==null)
            return "";
        if(className.endsWith("Entity")){
            return className.substring(0,className.lastIndexOf("Entity"));
        }else if(className.endsWith("Dto")){
            return className.substring(0,className.lastIndexOf("Dto"));
        }
        return className;
    }

    public static  <K, V> Map<K, V> toMap(Class<K> keyType, Class<V> valueType, Object... entries) {
        if (entries.length % 2 == 1)
            throw new IllegalArgumentException("Invalid entries");
        return IntStream.range(0, entries.length / 2).map(i -> i * 2)
                .collect(HashMap::new,
                        (m, i) -> m.put(keyType.cast(entries[i]), valueType.cast(entries[i + 1])),
                        Map::putAll);
    }
}
