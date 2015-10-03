/*
 *  This file is part of Tall Worlds, licensed under the MIT License (MIT).
 *
 *  Copyright (c) 2015 Tall Worlds
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package cubicchunks.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionUtil {
	public static final <T> T get(Object inObject, Field field, Class<T> fieldType) {
		try {
			return (T) field.get(inObject);
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			throw new RuntimeException(ex);
		} 
	}
	
	public static void set(Object inObject, Field field, Object newValue) {
		try {
			field.set(inObject, newValue);
		} catch (IllegalArgumentException | IllegalAccessException ex) {
			throw new RuntimeException(ex);
		} 
	}
		
	public static final Field findFieldNonStatic(Class<?> inClass, Class<?> type) {
		Field found = null;
		for(Field f : inClass.getDeclaredFields()) {
			if(f.getType().equals(type) && !Modifier.isStatic(f.getModifiers())) {
				if(found != null) {
					throw new RuntimeException("More than one field of type " + type + " found in class " + inClass);
				}
				found = f;
			}
		}
		if(found == null) {
			throw new RuntimeException("Field of type " + type + " not found in class " + inClass);
		}
		return found;
	}
}
