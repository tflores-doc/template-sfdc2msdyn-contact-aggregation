/**
 * Mule Anypoint Template
 * Copyright (c) MuleSoft, Inc.
 * All rights reserved.  http://www.mulesoft.com
 */

package org.mule.templates.builders;

import java.util.HashMap;
import java.util.Map;

public class ObjectBuilder {

	private Map<String, Object> fields;

	public ObjectBuilder() {
		this.fields = new HashMap<String, Object>();
	}

	public ObjectBuilder with(String field, Object value) {
		ObjectBuilder copy = new ObjectBuilder();
		copy.fields.putAll(this.fields);
		copy.fields.put(field, value);
		return copy;
	}

	public Map<String, Object> build() {
		return fields;
	}

	public static ObjectBuilder aContact() {
		return new ObjectBuilder();
	}
}
