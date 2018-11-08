package xyz.stackoverflow.blog.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 控制器基类
 *
 * @author 凉衫薄
 */
public class BaseController {

    /**
     * DTO转VO
     *
     * @param clazzMap
     * @param dto
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    protected Map<String, List<AbstractVO>> dto2vo(Map<String, Class<? extends AbstractVO>> clazzMap, BaseDTO dto) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Map<String, List<AbstractVO>> map = new HashMap<>();
        Set<String> key = clazzMap.keySet();
        Map<String, Map<String,Object>[]> data = dto.getData();
        Iterator<String> it = key.iterator();

        while (it.hasNext()) {
            String name = it.next();

            if (data.containsKey(name)) {
                Field[] fields = clazzMap.get(name).getDeclaredFields();
                List<AbstractVO> vos = new ArrayList<>();

                for (int i = 0; i < data.get(name).length; i++) {
                    AbstractVO vo = (AbstractVO) Class.forName(clazzMap.get(name).getName()).newInstance();
                    for (Field field : fields) {
                        String attr = field.getName();
                        String upName = attr.substring(0, 1).toUpperCase() + attr.substring(1);
                        Method method = clazzMap.get(name).getMethod("set" + upName, field.getType());
                        method.invoke(vo, data.get(name)[i].get(attr));
                    }
                    vos.add(vo);
                }
                map.put(name, vos);
            }
        }

        return map;
    }
}