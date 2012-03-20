package com.sdsoft.util;

import java.io.Serializable;


public class MultiValueKey implements Serializable {

	private static final long serialVersionUID = 1L;
	public Object[] keys;

	public MultiValueKey(Object... keys){
		this.keys = keys;
	}

	public boolean equals(Object o){
		if (o instanceof MultiValueKey) {
			MultiValueKey mvkey = (MultiValueKey)o;
			if (mvkey.keys.length == this.keys.length){
				for(int i = 0 ; i < this.keys.length; i++){
					if (mvkey.keys[i]== null)
						if (this.keys[i]== null)
							continue;
						else 
							return false;				
					if (!mvkey.keys[i].equals(this.keys[i]))
						return false;
				}
				return true;
			}
		}
		return false;
	}

	public int hashCode(){
		int result = 0;
		for(Object o : keys){
			if (o!=null)
				result += o.hashCode();
		}
		return result;
	}

	public String toString(){
		StringBuffer result = new StringBuffer("[");
		if (keys.length>0){
			for(int i = 0 ; i < keys.length - 1 ; i++){
				if (keys[i] != null)
					result.append(keys[i].toString() + ",");
			}
			result.append(keys[keys.length - 1]);
		}
		result.append("]");
		return result.toString();
	}
}
