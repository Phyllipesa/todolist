package br.com.phyllipesa.todolist.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe Utilitária (Utils).
 *
 * Esta classe contém métodos utilitários que podem ser usados em várias partes da aplicação.
 * Ela fornece métodos para copiar propriedades não nulas de um objeto para outro e para obter
 * os nomes de propriedades nulas em um objeto.
 */
public class Utils {

    /**
     * copyNonNullProperties - Copia propriedades não nulas de um objeto de origem para um objeto de destino.
     *
     * Este método copia propriedades não nulas do objeto de origem para o objeto de destino usando
     * a biblioteca BeanUtils. Propriedades nulas não são copiadas.
     *
     * @param source O objeto de origem a partir do qual as propriedades são copiadas.
     * @param target O objeto de destino para o qual as propriedades são copiadas.
     */
    public static void copyNonNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * getNullPropertyNames - Obtém os nomes das propriedades nulas em um objeto.
     *
     * Este método examina o objeto de origem e retorna um array de strings contendo os nomes das propriedades que têm valores nulos.
     *
     * @param source O objeto a partir do qual os nomes de propriedades nulas são obtidos.
     * @return Um array de strings contendo os nomes de propriedades nulas.
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for (PropertyDescriptor pd: pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
