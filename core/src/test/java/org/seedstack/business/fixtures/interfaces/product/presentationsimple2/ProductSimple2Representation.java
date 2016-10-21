/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.business.fixtures.interfaces.product.presentationsimple2;

import org.seedstack.business.assembler.MatchingEntityId;
import org.seedstack.business.assembler.MatchingFactoryParameter;

/**
 * Representation that will work with {@link org.seedstack.business.fixtures.domain.product.InternalProductFactory}
 * the {@link org.seedstack.business.fixtures.domain.product.ProductFactory} with the 2 argument constructor.
 */
public class ProductSimple2Representation {

	private Short storeId;
	private String productCode;
	private String name;
	private String description;

	public ProductSimple2Representation() {
	}
	
	public ProductSimple2Representation(Short storeId , String productCode , String name , String description) {
		this.storeId = storeId;
		this.productCode = productCode;
		this.name = name;
		this.description = description;
	}

	/**
	 * method does not start with get so it won't be serialized in json by default.
	 * 
	 * @return the store
	 */
	@MatchingEntityId
	@MatchingFactoryParameter(index=0)
	public Short getStoreId() {
		return storeId;
	}

	@MatchingEntityId
	@MatchingFactoryParameter(index=1)
	public String getProductCode() {
		return productCode;
	}
	
	public String getId() {
		return "" + storeId + "-" + productCode;
	}

	public void fillProductId(short storeId , String productCode) {
		this.storeId = storeId;
		this.productCode = productCode;
	}
	

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	

}
