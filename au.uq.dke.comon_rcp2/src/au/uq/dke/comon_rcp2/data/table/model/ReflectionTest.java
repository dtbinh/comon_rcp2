package au.uq.dke.comon_rcp2.data.table.model;

import java.lang.reflect.Field;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;
import org.metawidget.util.ClassUtils;

import au.uq.dke.comon_rcp2.data.utils.SetUtils;

public class ReflectionTest extends TestCase{

	
	@Test
	public void testReflection(){
		ProcessObject processObject = new ProcessObject();
		Field[] declaredFields = processObject.getClass().getDeclaredFields();
		Field[] fields = processObject.getClass().getFields();
		
		for(Field field: declaredFields){
			Object propertyValue = ClassUtils.getProperty(processObject, field.getName());
			int a = 1;
			
			if(Set.class.isAssignableFrom(field.getType())){
		
				Class elementType = SetUtils.getSetElementType(field);
				int a2 = 1;
			}
		}
		
		return ;
		
	}

}
