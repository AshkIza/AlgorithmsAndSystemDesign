package javeCorePractice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;




/* 
 *  https://dzone.com/articles/creating-custom-annotations-in-java
 * */
public class MyJsonSerializerFramework {
	
	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@interface JsonObject {
		
	}
	
	@Target(ElementType.FIELD)
	@Retention(RetentionPolicy.RUNTIME)
	@interface JsonField{
		String name() default "";
	}
	
	@SuppressWarnings("unchecked")
	public static String serialize(Object obj) {
		String jsonString = "";
		Class clazz = obj.getClass();
		if(clazz.isAnnotationPresent(JsonObject.class)){
			System.out.println("serializing ...");
			Map<String, Method> getterMethods = getAllPublicGetterMethods(clazz);
			try {
				Field[] fields = clazz.getDeclaredFields();
				jsonString+=  "{";
				for(Field field : fields) {
					if(field.isAnnotationPresent(JsonField.class)){
						JsonField jsonField = (JsonField) field.getAnnotation(JsonField.class);
						if("".equals(jsonField.name())){
							jsonString+= field.getName() + " : "; 
						}else {
							jsonString+= jsonField.name() + " : "; 
						}
						
						//jsonString+= field.get(obj).toString(); gets IllegalAccessException since fields are private
						if(getterMethods.containsKey(field.getName().toLowerCase())){
							Method method = getterMethods.get(field.getName().toLowerCase());
							String value =  method.invoke(obj).toString();
							jsonString+= value + ", ";
						}
						
					}
				}
			}catch(Exception e){
				System.out.println("excpetion thrown while serializing " + e.getStackTrace());
			}
			jsonString = jsonString.trim();
			if(jsonString.endsWith(",")) {
				jsonString =  jsonString.substring(0, jsonString.length()-1);
			}
			jsonString+="}";
			
		} else {
			System.out.println(clazz.getName() + " NOT a Json object");
		}
		return jsonString;
	}
	
	private static Map<String, Method> getAllPublicGetterMethods(Class clazz) {
		Method[]  methods = clazz.getMethods();//all PUBLIC methods
		Map<String, Method> methodMap = new HashMap<>();
		Arrays.stream(methods).filter( method -> method.getName().startsWith("get")).forEach( method -> {
			String key = method.getName().toLowerCase().substring(3);
			methodMap.put(key, method);
		});	
		return methodMap;
	}
}
