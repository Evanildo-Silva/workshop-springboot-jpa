package tech.evanildodeveloper.course.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    // Método para copiar toda propriedade que não for nulo
    public static void copyNonNullproperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    // Método que buscar em um objeto todos as propriedades nulas
    public static String[] getNullPropertyNames(Object source) {
        // BeanWrapper interface que permite acessar as propriedades de um objeto
        // BeanWrapperImpl implementa a interface
        final BeanWrapper src = new BeanWrapperImpl(source);

        // Obtendo as propriedades do objeto
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        // Criar conjunto para as propriedades de valor nulos
        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd : pds) {
            // Pegar o nome da propriedade atual na iteração
            Object srcValue = src.getPropertyValue(pd.getName());
            // Se a propriedade estive nula adiciona no conjunto de propriedades com valor
            // nulo
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
