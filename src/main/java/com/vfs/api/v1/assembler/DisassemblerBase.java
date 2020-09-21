package com.vfs.api.v1.assembler;

import java.lang.reflect.Field;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;

/**
 * Responsável por trabalhar com o de-para entre entidades de domínio e entradas
 * dos serviços
 * 
 * @author vinicius
 *
 */
@RequiredArgsConstructor
@Component
public final class DisassemblerBase {

	private final ModelMapper modelMapper;

	public <K, T> T toDomainObject(K input, Class<T> entity) {
		return modelMapper.map(input, entity);
	}

	/**
	 * Responsável por aplicar os valores de entrada do serviço em ma entidade de
	 * domínio
	 * 
	 * O último parâmetro aceita receber atributos aninhados que precisam ser informados separados com ".".
	 * 
	 * @param <K>
	 * @param <T>
	 * @param input  objeto que de entrada do serviço
	 * @param entity entidade que terá seus valores atualizados
	 * @param map    configurações necessárias na entidade de destino
	 */
	public <K, T> void copyToDomainObject(K input, T entity, final Map<String, Object> map) {
		if (!ObjectUtils.isEmpty(map)) {
			map.forEach((field, newValue) -> {
				set(entity, field, newValue);
			});
		}
		modelMapper.map(input, entity);
	}

	/**
	 * Altera o valor de um objeto de de acordo com a configuração informada.
	 * 
	 * Atributos aninhados precisam ser informados separados com ".", por exemplo
	 * object1.object2.name
	 * 
	 * @param bean      Objeto que será alterado
	 * @param fieldName nome do campo
	 * @param newValue  valor que será atribuído
	 */
	private void set(Object bean, String fieldName, Object newValue) {
		String[] nestedFields = { fieldName };
		if (fieldName.contains(".")) {
			nestedFields = StringUtils.split(fieldName, ".");
		}
		Class<?> componentClass = bean.getClass();
		Object value = bean;
		Field field = null;

		try {
			for (int i = 0; i < nestedFields.length; i++) {
				String nestedField = nestedFields[i];
				field = ReflectionUtils.findRequiredField(componentClass, nestedField);
				field.setAccessible(true);

				if (i == nestedFields.length - 1) {
					break;
				}

				value = field.get(value);
				if (value != null) {
					componentClass = value.getClass();
				}
			}

			field.set(value, newValue);
		} catch (IllegalAccessException iae) {
			throw new IllegalStateException(iae);
		}
	}

}
