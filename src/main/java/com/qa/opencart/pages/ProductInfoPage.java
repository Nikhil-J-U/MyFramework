package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By productImages = By.xpath("//div[@class='col-sm-8']//a/img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By inputQty = By.id("input-quantity");
	private By addToCart = By.id("button-cart");

	private Map<String, String> productInfoMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		String header = eleUtil.doGetText(productHeader);
		System.out.println("Product header is :" + header);
		return header;
	}

	public int getProductImagesCount() {
		int imgCount = eleUtil.waitForElementsToBeVisible(productImages, 10).size();
		System.out.println("Product images count is: " + imgCount);
		return imgCount;
	}

	public Map<String,String> getProductInfo() {
		productInfoMap = new HashMap<String, String>();
		productInfoMap.put("Name", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
		
	}

	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock
		
		for (WebElement e: metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
			
		}

	}

	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
//		$2,000.00
//		Ex Tax: $2,000.00
		String price = metaPriceList.get(0).getText().trim();
		String exPrice = metaPriceList.get(1).getText().trim();
		productInfoMap.put("price", price);
		productInfoMap.put("ExTaxPrice", exPrice);

	}

}
